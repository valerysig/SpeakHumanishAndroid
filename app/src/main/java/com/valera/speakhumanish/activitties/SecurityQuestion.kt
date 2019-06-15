package com.valera.speakhumanish.activitties
import android.app.Activity
import android.content.res.Resources
import android.graphics.Color
import android.graphics.Paint
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.RectShape
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.TextView
import com.valera.speakhumanish.R
import kotlinx.android.synthetic.main.activity_security_question.*
import kotlin.random.Random

class SecurityQuestion : Activity() {
    private var number1 : Int = 0
    private var number2 : Int = 0
    private lateinit var answerTextEdit : EditText
    private var errorMessageViewIndex : Int? = null

    private val answer : Int?
        get() = if (answerTextEdit.editableText.toString() == "") null else answerTextEdit.editableText.toString().toInt()

    private val textSize : Float?
        get() = answerTextEdit.textSize

    override fun onCreate(savedInstanceState: Bundle?) {
        // Hook up the xml to the class
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_security_question)
        //Set the layout params
        //TODO: We need to set the right width and height so that the pop up window will function properly
        window.setLayout(Resources.getSystem().displayMetrics.heightPixels / 2, Resources.getSystem().displayMetrics.heightPixels / 2)
        //Hook up the controls
        answerTextEdit = findViewById(R.id.securityQuestionAnswerEditText)
        submit_security_question_button.setOnClickListener { Thread{submitSecurityQuestionButtonPressed()}.start() }
        initSecurityQuestion()
    }
    private fun initSecurityQuestion() {
        number1 = Random.nextInt(100) + 10
        number2 = Random.nextInt(100) + 10
        val securityQuestion : TextView = findViewById(R.id.securityQuestionText)
        securityQuestion.text = "$number1 + $number2"
    }
    private fun submitSecurityQuestionButtonPressed() {
        if (answer == null) {
            addErrorMessage("Please enter your answer")
        }
        else if (!(number1 + number2).equals(answer)) {
            addErrorMessage("Wrong answer")
        } else {
            //TODO: Present the parent screen
            Log.d("SecurityQuestion", "Right")
        }
    }
    private fun addErrorMessage(errorMessage : String) {
        runOnUiThread {
            //Remove the previous message if exists
            errorMessageViewIndex?.let {
                securityQuestionLayout.removeViewAt(errorMessageViewIndex!!)
            }
            val errorMessageView = TextView(this)
            //Init the Border (Box) of the error message
            val sd = ShapeDrawable()
            sd.shape = RectShape()
            sd.paint.color = Color.RED
            sd.paint.strokeWidth = 10f
            sd.paint.style = Paint.Style.STROKE
            sd.setPadding(15, 5, 15, 5)
            //Set all oif the params
            errorMessageView.text = errorMessage
            errorMessageView.setTextColor(Color.parseColor("#FF0000"))
            errorMessageView.background = sd
            errorMessageView.textSize = textSize ?: 25f
            //Add the message to the layout
            securityQuestionLayout.addView(errorMessageView)
            errorMessageViewIndex = securityQuestionLayout.indexOfChild(errorMessageView)
        }
    }
}