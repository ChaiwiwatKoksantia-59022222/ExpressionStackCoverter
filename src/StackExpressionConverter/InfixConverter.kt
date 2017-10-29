package StackExpressionConverter

import java.util.*

class InfixConverter(private val infix: String) {

    private var currentStack: Stack<String> = Stack()
    private var expressionStack: Stack<String> = Stack()
    private var outputStack: Stack<String> = Stack()

    private var id: Int = 0

    var showLog: Boolean = false

    private fun popCurrentToExpression() {
        expressionStack.push(currentStack.pop())
    }

    private fun popCurrentToOutput() {
        outputStack.push(currentStack.pop())
    }

    private fun getImportance(string: String): Int {
        if (id == 1) {
            return when (string) {
                ")" -> 3
                "(" -> 4
                "+" -> 1
                "-" -> 1
                "*" -> 2
                "/" -> 2
                else -> 0
            }
        } else {
            return when (string) {
                ")" -> 4
                "(" -> 3
                "+" -> 1
                "-" -> 1
                "*" -> 2
                "/" -> 2
                else -> 0
            }
        }
    }

    private fun popAllExpressingToOutput() {
        var pass = false
        while (!pass) {
            if (id == 1) {
                if (expressionStack.isEmpty()) {
                    pass = true
                } else if (expressionStack.peek() == ")") {
                    pass = true
                } else {
                    if (!currentStack.isEmpty()) {
                        val a = getImportance(currentStack.peek())
                        val b = getImportance(expressionStack.peek())

                        if (a < b) {
                            outputStack.push(expressionStack.pop())
                        } else {
                            pass = true
                        }
                    } else {
                        outputStack.push(expressionStack.pop())
                    }
                }
            }
            else {
                if (expressionStack.isEmpty()) {
                    pass = true
                } else if (expressionStack.peek().equals("(")) {
                    pass = true
                } else {
                    if (!currentStack.isEmpty()) {
                        val a = getImportance(currentStack.peek())
                        val b = getImportance(expressionStack.peek())

                        if (a <= b) {
                            outputStack.push(expressionStack.pop())
                        } else {
                            pass = true
                        }
                    } else {
                        outputStack.push(expressionStack.pop())
                    }
                }
            }
        }
    }

    private fun popWithFunction() {
        var pass = false
        while (!pass) {
            if (id == 1) {
                if (expressionStack.peek() == ")") {
                    expressionStack.pop()
                    currentStack.pop()
                    pass = true
                } else {
                    outputStack.push(expressionStack.pop())
                }
            } else {
                if (expressionStack.peek() == "(") {
                    expressionStack.pop()
                    currentStack.pop()
                    pass = true
                } else {
                    outputStack.push(expressionStack.pop())
                }
            }
        }
    }

    private fun display() {
        if (showLog) {
            println("Current : $currentStack \nStack : $expressionStack\nOutput : $outputStack\n")
        }
    }

    fun toPrefix(): String {
        id = 1
        currentStack = StackConverter().toStack(infix)
        display()
        while (!currentStack.isEmpty()) {
            if (expressionStack.isEmpty()) {
                val a = getImportance(currentStack.peek())
                if (a == 0) {
                    popCurrentToOutput()
                } else {
                    popCurrentToExpression()
                }
                display()
            } else {
                val a = getImportance(currentStack.peek())
                val b = getImportance(expressionStack.peek())
                when {
                    a == 0 -> popCurrentToOutput()
                    a == 3 -> popCurrentToExpression()
                    a == 4 -> popWithFunction()
                    a >= b -> popCurrentToExpression()
                    a < b -> {
                        popAllExpressingToOutput()
                        popCurrentToExpression()
                    }
                }
                display()
                if (currentStack.isEmpty()) {
                    popAllExpressingToOutput()
                }
            }
        }
        val st = StackConverter().reversed(outputStack)
        return StackConverter().toString(st)
    }

    fun toPostfix(): String {
        id = 2
        val de: Stack<String> = StackConverter().toStack(infix)
        currentStack = StackConverter().reversed(de)
        display()
        while (!currentStack.isEmpty()) {
            if (expressionStack.isEmpty()) {
                val a = getImportance(currentStack.peek())
                if (a == 0) {
                    popCurrentToOutput()
                } else {
                    popCurrentToExpression()
                }
                display()
            } else {
                val a = getImportance(currentStack.peek())
                val b = getImportance(expressionStack.peek())
                when {
                    a == 0 -> popCurrentToOutput()
                    a == 3 -> popCurrentToExpression()
                    a == 4 -> popWithFunction()
                    a > b -> popCurrentToExpression()
                    a <= b -> {
                        popAllExpressingToOutput()
                        popCurrentToExpression()
                    }
                }
                display()
                if (currentStack.isEmpty()) {
                    popAllExpressingToOutput()
                }
            }
        }
        //val st = StackConverter().reversed(outputStack)
        return StackConverter().toString(outputStack)
    }
}