package com.example.gibberishgenerator

import android.icu.lang.UCharacter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.gibberishgenerator.languageobjects.Language
import com.example.gibberishgenerator.languageobjects.Phoneme
import com.example.gibberishgenerator.languageobjects.Step

class GeneratorActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_generator)

        //deal with returned result and communicate with front end

        //in click listener

        //generateWord()
        //use phonemelisttostring in the visual setter, that way you can make one for the IPA ver., and make changing the display an option
        //less neat if i wasnt intending to do that but hey if i dont get lazy i can write 3 lines of code and make it happen :)


    }

}