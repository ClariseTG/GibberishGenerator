package com.example.gibberishgenerator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import com.example.gibberishgenerator.helpers.LanguageSelectAdapter
import com.example.gibberishgenerator.helpers.LanguageSelectWrapper
import kotlinx.android.synthetic.main.activity_language_select.*
import com.example.gibberishgenerator.languageobjects.Language
import com.example.gibberishgenerator.languageobjects.Phoneme
import com.example.gibberishgenerator.languageobjects.Step
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

val TAG = "LanguageSelectActivity"

class LanguageSelectActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_language_select)

        //Load in the phonemes
        val inputStream = resources.openRawResource(R.raw.ipa)
        val inputString = inputStream.bufferedReader().use {
            it.readText()
        }
        Log.d(TAG, "onCreate | " + inputString)

        val gson = Gson()

        val sType = object : TypeToken<List<Phoneme>>() { }.type
        validPhonemes = gson.fromJson<List<Phoneme>>(inputString, sType)



        //TODO: beta language masterlist
        langMasterList.add(
            Language(
                "betaLang1",
                validPhonemes.toMutableList(),
                listOf<Step>(
                    Step("is","replace", arrayOf(validPhonemes[4]), validPhonemes[9]),
                    Step("is","replace", arrayOf(validPhonemes[6]), validPhonemes[3])
                ).toMutableList(),
                8, 5, 2,
                "CV",
                "V"
            )
        )
        langMasterList.add(
            Language(
                "betaLang2",
                validPhonemes.toMutableList(),
                listOf<Step>(
                    Step("is","replace", arrayOf(validPhonemes[5]), validPhonemes[1]),
                    Step("is","replace", arrayOf(validPhonemes[2]), validPhonemes[3])
                ).toMutableList(),
                8, 5, 2,
                "CVC",
                "C"
            )
        )

        //load masterlist to recyclerview
        recycler_select_languages.apply {
            setHasFixedSize(true)
            layoutManager = GridLayoutManager(this@LanguageSelectActivity, 1)
            adapter = LanguageSelectAdapter(LanguageSelectWrapper(langMasterList.size, langMasterList))
        }


        var barText : CharSequence = "Select a Language"
        supportActionBar?.title = barText


        //put the onclicklisteners here

        //recyclerview (choosing a language)

        //delete/edit dropdown menu

        //new language button


    }

    companion object LanguageSelect {
        //master list of all of the languages the user has saved
        var langMasterList : MutableList<Language> = emptyList<Language>().toMutableList()
        lateinit var validPhonemes : List<Phoneme>
    }
}