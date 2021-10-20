package com.deniselarsson.thequiz

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_quiz_question.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class QuizQuestionActivity : AppCompatActivity(), View.OnClickListener {

    //we should start with the first question, therefore it is 1
    private var mCurrentPosition : Int = 1
    private var mQuestionList : ArrayList<Question> ? = null
    private var mSelectecPosition : Int = 0
    private var mCorrectAnswer : Int = 0
    private var mUserName : String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_question)

        var rf = Retrofit.Builder()
            .baseUrl(QuestionApi.baseURL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        var api = rf.create(QuestionApi::class.java)
        var call = api.get()

        call?.enqueue(object : Callback<ArrayList<Question?>?> {
            override fun onResponse(call: Call<ArrayList<Question?>?>, response: Response<ArrayList<Question?>?>) {
                var questions : ArrayList<Question> = response.body() as ArrayList<Question>
                mQuestionList = questions
                setQuestion()
            }

            override fun onFailure(call: Call<ArrayList<Question?>?>, t: Throwable) {
                println("Failed to execute request")
            }
        })

        tv_option_one.setOnClickListener(this)
        tv_option_two.setOnClickListener(this)
        tv_option_three.setOnClickListener(this)
        tv_option_four.setOnClickListener(this)
        btn_submit.setOnClickListener(this)
    }

    private fun setQuestion(){
        //why -1 because we want to start at index 0 and mine first index here is 1
        val question = mQuestionList!![mCurrentPosition -1]
        defaultOptionsView()

        if(mCurrentPosition == mQuestionList!!.size){
            btn_submit.text = "FINISH"
        }else{
            btn_submit.text = "SUBMIT"
        }

        progressBar.max = mQuestionList!!.size;
        progressBar.progress = mCurrentPosition
        tv_progress.text = "$mCurrentPosition" + "/" + progressBar.max
        tv_question.text = question!!.text
        tv_option_one.text = question.optionOne
        tv_option_two.text = question.optionTwo
        tv_option_three.text = question.optionThree
        tv_option_four.text = question.optionFour
    }
    //a function to defined the background color for the selected button and also set the other one to default
    //until one is selected

    private fun defaultOptionsView(){
        val options = ArrayList<TextView>()

        options.add(0,tv_option_one)
        options.add(1,tv_option_two)
        options.add(2,tv_option_three)
        options.add(3,tv_option_four)

        for (option in options){
            option.setTextColor(Color.parseColor("#7A8089"))
            option.typeface = Typeface.DEFAULT
            option.background = ContextCompat.getDrawable(
                this,
                R.drawable.default_option_border_bg
            )
        }
    }

    private fun answerView (answer: Int, drawableView : Int){
        when (answer) {
            1 -> {
                tv_option_one.background = ContextCompat.getDrawable(
                    this, drawableView
                )
            }
            2 -> {
                tv_option_two.background = ContextCompat.getDrawable(
                    this, drawableView
                )
            }
            3 -> {
                tv_option_three.background = ContextCompat.getDrawable(
                    this, drawableView
                )
            }
            4 -> {
                tv_option_four.background = ContextCompat.getDrawable(
                    this, drawableView
                )
            }
        }
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.tv_option_one ->{
                selectedOptionView(tv_option_one,1)
            }
            R.id.tv_option_two ->{
                selectedOptionView(tv_option_two,2)
            }
            R.id.tv_option_three->{
                selectedOptionView(tv_option_three,3)
            }
            R.id.tv_option_four ->{
                selectedOptionView(tv_option_four,4)
            }
            R.id.btn_submit ->{
                if (mSelectecPosition == 0 ){
                    mCurrentPosition ++

                    when {
                        mCurrentPosition <= mQuestionList !!.size ->{
                            setQuestion()
                        }else ->{
                        val intent = Intent(this, ResultActivity::class.java)
                        intent.putExtra(Constants.USER_NAME, mUserName)
                        intent.putExtra(Constants.CORRECT_ANSWER, mCorrectAnswer)
                        intent.putExtra(Constants.TOTAL_QUESTIONS, mQuestionList!!.size)
                        startActivity(intent)
                    }
                    }
                }else{
                    val question = mQuestionList?.get(mCurrentPosition -1)
                    if (question!!.correctAnswer !=mSelectecPosition){
                        answerView(mSelectecPosition, R.drawable.wrong_option_border_bg)
                    }else{
                        mCorrectAnswer ++
                    }
                    answerView(question.correctAnswer, R.drawable.correct_option_border_bg)

                    if (mCurrentPosition == mQuestionList!!.size){
                        btn_submit.text = "FINISH"
                    }else{
                        btn_submit.text = "GO TO NEXT QUESTION"
                    }
                    mSelectecPosition = 0
                }
            }
        }
    }
    private fun selectedOptionView(tv: TextView, selectedOptionNumber: Int){

        //we need to know which option i selected in order to do the right thing
        //therefore we reset to default
        defaultOptionsView()

        mSelectecPosition = selectedOptionNumber
        tv.setTextColor(Color.parseColor("#363A43"))
        tv.setTypeface(tv.typeface, Typeface.BOLD)
        tv.background = ContextCompat.getDrawable(
            this,
            R.drawable.selected_option_border_bg
        )
    }
}