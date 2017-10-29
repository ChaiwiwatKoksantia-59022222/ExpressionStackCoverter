package StackExpressionConverter

import java.util.*

class StackConverter {

    fun toStack(string: String): Stack<String> {
        val stack: Stack<String> = Stack()
        var br = false
        //var str = ""
        //var num = false
        //var pass = true
        loop@ for (char in string) {
            when {
                /*char.isDigit() -> {
                    str += char.toString()
                    num = true
                    pass = false
                }*/
                char.toInt() == 8211 -> {
                    stack.push("-")
                    //pass = true
                }
                char.toInt() > 256 -> {
                    println("Please check your input again")
                    br = true
                    break@loop
                }
                char != ' ' -> {
                    stack.push(char.toString())
                    //pass = true
                }
            }
            /*if (num && pass) {
                stack.push(str)
                str = ""
                num = false
            }*/
        }
        return if (br) {
            Stack()
        } else {
            stack
        }
    }

    fun reversed(stack: Stack<String>): Stack<String> {
        val stackResult: Stack<String> = Stack()
        while (!stack.isEmpty()) {
            stackResult.push(stack.pop())
        }
        return stackResult
    }

    fun toString(stack: Stack<String>): String {
        val stackResult: Stack<String> = reversed(stack)
        var string = String()

        while (!stackResult.isEmpty()) {
            string += "" + stackResult.pop()
        }
        return string
    }


}