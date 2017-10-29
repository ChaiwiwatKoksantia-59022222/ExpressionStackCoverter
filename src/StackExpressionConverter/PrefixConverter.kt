package StackExpressionConverter

import java.util.*

class PrefixConverter(private val prefix: String) {

    private var currentStack: Stack<String> = Stack()
    private var expressionStack: Stack<String> = Stack()
    private var outputStack: Stack<String> = Stack()

    var showLog: Boolean = true

    private fun calculate(operator: String, a: String, b: String): String {
        if (a.get(0).isDigit() && b.get(0).isDigit()) {
            return when (operator) {
                "+" -> math(a.toDouble() + b.toDouble())
                "-" -> math(a.toDouble() - b.toDouble())
                "*" -> math(a.toDouble() * b.toDouble())
                "/" -> math(a.toDouble() / b.toDouble())
                else -> ""
            }
        } else {
            return "($a$operator$b)"
        }

    }

    private fun popAllExpressingToOutput() {
        while (!expressionStack.isEmpty()) {
            outputStack.push(expressionStack.pop())
        }

    }

    private fun math(double: Double): String {
        val str = double.toString()
        val point = str.indexOf('.')
        val x = str.substring(point + 1)
        return when {
            x.length > 1 -> str
            x.get(0) == '0' -> str.substring(0, point)
            else -> str
        }
    }

    fun toInfix() :String {

        currentStack = StackConverter().toStack(prefix)
        display()
        while (!currentStack.isEmpty()) {
            if (currentStack.peek() == "+" || currentStack.peek() == "-" || currentStack.peek() == "*" || currentStack.peek() == "/") {
                if (expressionStack.size > 1) {
                    val str = calculate(currentStack.pop(), expressionStack.pop(), expressionStack.pop())
                    expressionStack.push(str)
                }
                display()
            } else {
                expressionStack.push(currentStack.pop())
                display()
            }
            if (currentStack.isEmpty()) {
                popAllExpressingToOutput()
                display()
            }
        }
        return StackConverter().toString(outputStack)

    }

    private fun display() {
        if (showLog) {
            println("Current : $currentStack \nStack : $expressionStack\nOutput : $outputStack\n")
        }
    }

}