package com.daya

class SampleClass {
    fun sum(vararg number :Int) = number.reduce { acc, i -> acc+i }
}