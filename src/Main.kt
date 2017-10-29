import StackExpressionConverter.InfixConverter
import StackExpressionConverter.PostfixConverter
import StackExpressionConverter.PrefixConverter
import StackExpressionConverter.StackConverter
import java.util.*

fun main(args: Array<String>) {

    //testOne()
    testTwo()

}

fun testOne() {
    //val str = "A-B-C*(D+E/F-G)–H"
    val str = "(A+B)*C+D/(E+F*G)–H"
    val strs = "(1+2)*3+4/(5+6*7)–8"

    val infix = InfixConverter(str)
    infix.showLog = false

    val resultPrefix = infix.toPrefix()
    val resultPostfix = infix.toPostfix()
    //println("\nInfix = $str \nPrefix = $resultPrefix\nPostfix = $resultPostfix")

    //val st = "+*567"
    //val st = "+*ABC"
    val st = "-/+*ABCD/E+FG"
    val prefix = PrefixConverter(st)
    prefix.showLog = false
    val resultPrefixToInfix = prefix.toInfix()
    println("\nPrefix = $st\nInfix = $resultPrefixToInfix")

    val st2 = "ABCD*-E/+F+GH/-"
    val postfix = PostfixConverter(st2)
    postfix.showLog = false
    val resultPostfixToInfix = postfix.toInfix()
    println("\nPostfix = $st\nInfix = $resultPostfixToInfix")
}

fun testTwo() {
    //val str = "A-B-C*(D+E/F-G)–H"
    val str = "((((A+B)*C)+(D/(E+(F*G))))-H)"
    //val str = "(A+B)*C+D/(E+F*G)–H"
    val strs = "(1+2)*3+4/(5+6*7)–8"

    val infix = InfixConverter(str)
    infix.showLog = false

    val resultPrefix = infix.toPrefix()
    val resultPostfix = infix.toPostfix()
    println("\nInfix = $str \nPrefix = $resultPrefix\nPostfix = $resultPostfix")

    val prefix = PrefixConverter(resultPrefix)
    prefix.showLog = false
    val resultPrefixToInfix = prefix.toInfix()
    println("\nPrefix = $resultPrefix\nInfix = $resultPrefixToInfix")

    val postfix = PostfixConverter(resultPostfix)
    postfix.showLog = false
    val resultPostfixToInfix = postfix.toInfix()
    println("\nPostfix = $resultPostfix\nInfix = $resultPostfixToInfix")
}