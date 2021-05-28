package com.example.gibberishgenerator

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_generator.*
import kotlinx.android.synthetic.main.fragment_generator.view.*
import kotlinx.android.synthetic.main.fragment_iterator.view.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [GeneratorFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class GeneratorFragment : Fragment() {


    //Here's all of the saved variables for the settings!!
    //They reset every time you open because im lazy lmaoooooo
    val currentWordLength = "Short"






    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            //param1 = it.getString(ARG_PARAM1)
            //param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        //when language selected, selected language is moved to pos 0 in lang masterlist
        //conveniently also means languages will always be sorted by when they were last opened
        //is this a terrible way to do it? yes!
        //do i care? it's 6:58 PM the day before this is due.
        val currentLang = LanguageSelectActivity.langMasterList[0]

        val rootView = inflater.inflate(R.layout.fragment_generator, container, false)



        //manipulate widgets here

        rootView.button_generator_generate.setOnClickListener() {
            //generate word
            var genWord = currentLang.generateWord(currentWordLength, true)
            //set proto word display
            textView_generate_protoWord.text = currentLang.phonemeListToString(genWord)
            //iterate the word and then set it onto the iterated textview
            genWord = currentLang.iterateWord(genWord)
            textView_generate_finalWord.text = currentLang.phonemeListToString(genWord)
        }

        rootView.textView_generator_editlanguage.setOnClickListener(){
            //aware it says SPELL intent but its getting progressively closer to morning
            val specificSpellIntent = Intent(this.context, LanguageEditActivity::class.java)
            //specificSpellIntent.putExtra(GeneratorActivity.EXTRA_SPELL_INDEX, spell.index)
            this.context?.startActivity(specificSpellIntent)
        }


        // Inflate the layout for this fragment
        return rootView
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment GeneratorFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            GeneratorFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}