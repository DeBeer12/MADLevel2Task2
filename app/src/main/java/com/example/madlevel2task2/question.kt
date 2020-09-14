package com.example.madlevel2task2

data class Question (var question: String, var answer: Boolean){
    companion object{
        val QUESTIONS = arrayOf(
            "A val and var are the same",
            "Mobile Application Development grants 12 ECTS",
            "A unit in Kotlin corresponds to a void in Java",
            "In kotlin 'when' replaces the 'switch' operator"
        )

        val ANSWERS = arrayOf(false, true, false, true)
    }
}