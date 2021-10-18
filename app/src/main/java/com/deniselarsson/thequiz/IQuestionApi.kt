package com.deniselarsson.thequiz

import retrofit2.Call
import retrofit2.http.GET

interface QuestionApi {
    @GET("Questions.json")
    fun get() : Call<ArrayList<Question?>?>?
    companion object {
        const val baseURL =
            "https://raw.githubusercontent.com/deniselarsson/APIQuestions/master/app/src/main/assets/"
    }
}
