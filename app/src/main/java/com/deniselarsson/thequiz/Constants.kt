package com.deniselarsson.thequiz

//create one constant which will hold all my constants
object Constants {

    const val USER_NAME : String = "user_name"
    const val TOTAL_QUESTIONS : String = "total_question"
    const val CORRECT_ANSWER : String = "correct_answer"

    // a function which will return all of the values, and return an arraylist of questions
    fun getQuestions(): ArrayList<Question> {

        val questionList = ArrayList<Question>()//create a question list, and now it is empty

        val que1 = Question(1,"What country is Rome located in?",
            "France", "Italy",
            "Jamaica", "Spain", 2)
        questionList.add(que1)

        val que2 = Question(2,"Which of these famous film/book trilogies is Gollum a part of?",
            "Harry Potter", "The Chronicles of Narnia",
            "Eragon", "The Lord of the Rings", 4)
        questionList.add(que2)

        val que3 = Question(3,"What color is a dandelion before its flowers turn to pollen?",
            "Yellow", "Blue", "White",
            "Purple", 1)
        questionList.add(que3)

        val que4 = Question(4,"George Harrison was a member of which famous band?",
            "The Rolling Stones", "The Beatles", "Led Zeppelin",
            "Pink Floyd", 2)
        questionList.add(que4)

        val que5 = Question(5,"What's the capital city of the United States?",
            "New York City, New York", "Philadelphia, Pennsylvania",
            "Washington, D.C.", "Los Angeles, California", 3)
        questionList.add(que5)

        return questionList
    }
}