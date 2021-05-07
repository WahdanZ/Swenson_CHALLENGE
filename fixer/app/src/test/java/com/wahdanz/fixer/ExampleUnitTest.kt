package com.wahdanz.fixer

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        print(fact(10))
    }
    fun isAnagrams(p1:String,p2:String):Boolean{
        return p1.toList().toSet().map {
            if (it.isWhitespace())
                0
        else it.toInt()}.sum() == p2.toList().toSet().map {
            if (it.isWhitespace())
                0
            else it.toInt()}.sum()
    }
    fun factorial(n: Int) {
        print(generateSequence(Pair(1, 1)) { Pair(it.second, it.first + it.second) }.map { it.first }.take(n).toList())
    }
    tailrec fun fact(n: Int, temp: List<Int> = listOf(1,1)): List<Int> {
        return if (temp.size == n){
            temp
        } else {
            fact(n, temp.plus(temp.takeLast(2).sum()))
        }
    }
}
