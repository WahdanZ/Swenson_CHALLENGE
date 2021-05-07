# **Swenson task**

**1- Add arithmetic operators (add, subtract, multiply, divide) to make the following expressions true. You can use any parentheses you’d like. You don’t need to write any code for this question.**

**Answer**: 
**(3 -1) * (-3+9) = 12**

**2- Write a function in Kotlin to determine whether two strings are anagrams or not (examples of anagrams: debit card/bad credit, punishments/nine thumps, etc.)**

```kotlin
    fun isAnagrams(p1:String,p2:String):Boolean{
        return p1.toList().toSet().map {
            if (it.isWhitespace())
                0
            else it.toInt()}.sum() == p2.toList().toSet().map {
            if (it.isWhitespace())
                0
            else it.toInt()}.sum()
    }
```
**3-Write a function in Kotlin to generate the nth Fibonacci number (1, 1, 2, 3, 5, 8, 13, 21, 34)**

A. recursive approach
```kotlin
fun factorial(n: Int): Long {
    return fact(n)
}
tailrec fun fact(n: Int, temp: List<Int> = listOf(1,1)): List<Int> {
    return if (temp.size == n){
        temp
    } else {
        fact(n, temp.plus(temp.takeLast(2).sum()))
    }
}
```

B. iterative approach

```kotlin
fun factorial(n: Int):List<Iny> {
   generateSequence(Pair(1, 1)) { Pair(it.second, it.first + it.second) }.map { it.first }.take(n).toList()
}
```


**4-Create a currency converter by utilizing data from the fixer.io API*

--> fixer directory 
