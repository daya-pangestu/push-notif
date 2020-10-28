package com.daya.shared.taha

import kotlin.math.pow

class Sampelclazz {
    fun sum(vararg number :Int): Int {
        if (number.size == 2) {
            return (number[0].toDouble().pow(number[1].toDouble())).toInt()
        }
        return number.reduce { acc, i -> acc+i }
    }
}