package com.valera.speakhumanish.activitties
import android.app.Activity
import android.content.res.Resources
import android.os.Bundle
import com.valera.speakhumanish.R

class SecurityQuestion : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_security_question)
        window.setLayout(Resources.getSystem().displayMetrics.heightPixels / 2, Resources.getSystem().displayMetrics.heightPixels / 2)
    }
}