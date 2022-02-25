fun main(args: Array<String>) {
    val nameNumMap = mapOf("Abe" to 323532342,
                            "Ben" to 234295435,
                            "Cathy" to 939534012,
                            "Don" to 202934543,
                            "Elle" to 330394011,
                            "Frances" to 839047202,
                            "Gaby" to 901239409)

    val numPurchList = listOf(323532342 to 52.81,
                            234295435 to 61.62,
                            939534012 to 91.11,
                            202934543 to 29.54,
                            330394011 to 29.82,
                            839047202 to 14.34,
                            901239409 to 3.10,
                            939534012 to 53.92,
                            202934543 to 68.73,
                            330394011 to 35.94,
                            839047202 to 7.55,
                            901239409 to 2.70)
    printPurchases(nameNumMap, numPurchList)
}

fun printPurchases(nameNum : Map<String, Int> , numPurch : List<Pair<Int , Double>>) {
    for(currName in nameNum.keys){
        var currNum = nameNum.getValue(currName)
        var currTotal = 0.0
        for(currPair in numPurch){
            if(currPair.first == currNum){
                currTotal += currPair.second
            }
        }
        println("$currName total purchases =  $currTotal")
    }

}