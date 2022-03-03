import java.lang.reflect.Type
import java.lang.reflect.TypeVariable
import kotlin.math.PI   //can we alias the package name?

fun main(args: Array<String>) {
   // println("Hello World!")

    // Try adding program arguments via Run/Debug configuration.
    // Learn more about running applications: https://www.jetbrains.com/help/idea/running-applications.html.
    //println("Program arguments: ${args.joinToString()}")
    var myVar = 42
    println(myVar)
    myVar = 43
    println(myVar)
    myVar += 3  //myVar = myVar + 3
    println(myVar)
    myVar -= 4   // myVar = myVar - 4
    println(myVar)

    var n = 1.2
    n += 1
    println(n)
    val m = 1.3 + 1
    println(m)
    var bool = true

    var a = 10
    val b = a
    a = 33
    println(a)
    println(b)  //prints 10

    println(dub(10))
    println(x5(10))
    checkSign(10)
    checkSign(-10)
    checkSign(0)


    var answer = 42
    println("The value of the answer is $answer")

    val percent = 20
    println(percent in 1..100)  //equivalent to (1 <= percent && percent <= 100)
    println("a" in "blink")  //false

    println(xN(10)(5))
    val x10 = xN(10)
    println(x10(10))

    println(checkPalindrome("racecar")) //true
    println(checkPalindrome("Racecar")) //false
    println(checkPalindrome("truck"))

    println(checkPalindromeNoCase("Racecar"))  //true

    println(reverseInt(1234))

    println(kotlin.math.PI)

    val zero = 0
    val two = 2
    val three = 3

    val s = "Hello World"
    println(s)
    println(s.singleQuotes())  //doesn't change s b/c Kotlin strings are immutable
    println(s.doubleQuotes())  //same as above

    val col = color(red = 76, green = 89, blue = 0)
    println(col)

    println(colorDef(139))
    println(colorDef(blue = 120))
    println(colorDef(139, 200))
    //order doesn't matter for call below b/c we specify which parameters have what value by name
    println(colorDef(blue = 10, green = 20, red = 30))

    val O = Overloading()
    println(O.h())
    println(O.h(23))

    println(greeting(1))
    println(greeting(2))

    println(compute(7).toString())

    var s1 : String? = null
    //can't do this
    //println(s1.length)
    //but can do this
    println(s1?.length)

    //Elvis Operator
    println(s1?.length ?: "This string is null")
    s1 = "hello"
    println(s1?.length ?: "This string is null")

    var i = 0
    val nums = mutableListOf<Int>(0)
    do{
        i+=4
        if(i==3) continue
        if(i==30) break
        nums.add(i)
    } while (i < 100)
    println(nums)

    //maybe not the way they wanted
    //the lists here are immutable which might not be what we want
    val oneToN : (Int) -> List<Int> = {N : Int -> List<Int>(N){it + 1} }
    println(oneToN(10))

    //my attempts (had problems with Type, not sure why)
    //maybe works with TypeVariable<>
    val oneToNL : (List<Type>) -> List<Int> = {L -> List<Int>(L.size){it + 1} }
    val zeros : (List<Type>) -> List<Int> = {L -> List<Int>(L.size){0} }
    val abc : (List<Type>) -> List<Char> = {L -> List<Char>(26){(it + 97).toChar()}}

    //solutions (not very satisfying b/c we can't call them like a function
    val oneToNSol = List(10){it + 1}
    val zerosSol = List(10){0}
    val abcSol = List(26){'a' + it}

    val l : List<Int> = mutableListOf(-3, 0, 1, 2, 4)
    val newL = l.filter { it > 0 }
    println(newL)
    println(newL.count())

    val ll : List<Int> = mutableListOf(-5, 0, 3, 4)
    println(ll.any {it == 5})
    val lll : List<Int> = mutableListOf(0, 1, 2, 3, 4, 5)
    val even = lll.filter { it % 2 == 0 }
    val greaterThanTwo = lll.filter { it > 2 }

    //should use data class
    val names : List<String> = mutableListOf("Alice", "Belle", "Cynthia", "Diana", "Elle", "Fantasia", "Giselle")
    val last4 : List<Int> = mutableListOf(4210, 8725, 3720, 6332, 6290, 9859, 6917)
    //this is an actual map b/c of toMap() call (otherwise it would also just be a list of pairs)
    val nameLast4 : Map<String, Int> = names.zip(last4).toMap()
    println(nameLast4)

    //list of pairs
    val nameLast4data = names.zip(last4) {name, id -> Person(name, id)}
    println(nameLast4data)

    val listOfLists = listOf(listOf(1,2), listOf(3,4) , listOf(5,6))
    println(listOfLists)
    println(listOfLists.flatten())

    val newCustomer1 = Customer("Kevin")
    val newCustomer2 = Customer("Laura")
    println(newCustomer1.toString())
    println(newCustomer2.toString())

    val pCCard = PlatinumCreditCard()
    println(pCCard.info())

    println(talk(Dog()))
    println(talk(Cat()))

    //doesn't work
    //val x = JustOne()
    println(JustOne.n)
    println(JustOne.f())
    println(JustOne.g())

}

//singleton pattern
object JustOne{
    val n = 2
    fun f() = n * 10
    fun g() = this.n * 20
}



data class Person(val name : String, val id : Int)


//assuming that Int type is large enough for result
fun dub(num : Int) : Int {
    return 2 * num
}
//alternate syntax for functions that have a simple expression as their body
//no need for curly braces
//don't like this syntax as much as the normal syntax
fun x5(num : Int) : Int = 5 * num

fun checkSign(num: Int){
    if(num > 0)
        println("positive")
    else
        if(num < 0)
        println("negative")
        else
            println("zero")
}

fun checkPalindrome(s : String) : Boolean {
    return s == s.reversed()
}

fun checkPalindromeNoCase(s : String) : Boolean {
    return s.lowercase() == s.lowercase().reversed()
}

fun reverseInt(i : Int) : Int {
    return i.toString().reversed().toInt()
}

fun divideBy(x : Double , y : Double) : Double {
    if (x == 0.0 && y == 0.0)  //when comparing equality for doubles, should probably have an epsilon tolerance
        throw IllegalArgumentException("Zero over zero")
    if(y == 0.0)
        throw IllegalArgumentException("Divide by zero")
    return x / y

}

//extending the functionality of the string class in our local code
fun String.singleQuotes() = "'$this'"
fun String.doubleQuotes() = "\"$this\""

/* Equivalent way to do this (like this better than above)
fun String.singleQuotes(): String {
    return "'$this'"
}
fun String.doubleQuotes(): String {
    return "\"$this\""
}
*/

fun color(red: Int, green: Int, blue: Int) : String {
    return "($red, $green, $blue)"
}

fun colorDef(red: Int = 0, green: Int = 0, blue: Int = 0) : String{
    return "($red, $green, $blue)"
}

class Overloading{
    fun h(N : Int) : Int {
        return N + 200
    }
    fun h() : Int{
        return 0
    }
}

fun milesPerks(i : Int) : String {
    return when(i){
        1000 -> "Free Pen"
        10000 -> "Free Airline Ticket"
        100000 -> "Free stay in resort"
        else -> ""
    }
}

//can lift the return out of when (like how it is above) (that is what the warning is)
fun greeting(i : Int) : String {
    val m : Map<Int, String> = mapOf(1 to "Barbie",
                                     2 to "Ken")
    when(i) {
        1 -> return "Hi " + m.getValue(i)
        2 -> return "Bye " + m.getValue(i)
        else -> return ""
    }
}

enum class Accounts{
    Checking, Savings, IRA
}

//why does it want to lift the return out of the if???
fun compute(i : Int) : Pair<String, Int> {
    if(i > 5){
        return "Loyal Customer" to 12*i
    }
    else{
        return "Future Loyal Customer" to 12*i
    }
}

private var counter = 0
class Customer(name : String){
    private val uniqueID : String
    init {
        counter++
        uniqueID = "[$counter] $name"
    }

    override fun toString(): String {
        return this.uniqueID
    }
}

open class CreditCard() {
    val expirationYears : Int = 4
    val annualFees : Int = 100
    /* this also works
    fun info() : String{
        return "Expiration Years : $expirationYears , Annual Fees : $annualFees"
    }
     */
}
fun CreditCard.info() : String {
    return "Expiration Years : $expirationYears , Annual Fees : $annualFees"
}

open class PlatinumCreditCard : CreditCard()

abstract class Miles{
    abstract val x : Int
}

abstract class MilesMultiplier{
    abstract fun addMiles() : Int
    abstract fun useMiles(n : Int)
}

open class Pet(){
    open fun speak() : String {
        return "Pet"
    }
}

class Dog : Pet(){
    override fun speak() : String{
        return "Bow"
    }
}

class Cat : Pet() {
    override fun speak(): String {
        return "Meow"
    }
}

fun talk(p : Pet) = p.speak()
//can also do
/*
fun talk(p : Pet) : String {
    return p.speak()
}
*/

fun xN(N : Int) : (Int) -> Int {
    //val f : (Int, Int) -> Int = {n , m -> n * m }
    //return f(N)  //can we not curry lambdas?? no, you cannot partially apply a function
    val g : (Int) -> (Int) -> Int = {n -> {m -> n * m}}  //this kind of lets you curry a lambda
    return g(N)
}


