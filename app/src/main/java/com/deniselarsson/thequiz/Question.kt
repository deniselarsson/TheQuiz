package com.deniselarsson.thequiz

//No function only data so we will add data before class
//id Int so I know which question I am looking at
data class Question(
    val id : Int,
    val question : String,
    val optionOne : String,
    val optionTwo : String,
    val optionThree : String,
    val optionFour : String,
    val correctAnswer : Int // to store question one,two, three or four to see which one is correct
)