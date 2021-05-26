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




    fun generateWord(lang : Language, length : String, returnsProto : Boolean) : MutableList<Phoneme> {
        //decide if returning proto-word or later word using parameter
        var syllables = if (length == "long"){
            lang.longWordSyllableLength
        } else if (length == "medium"){
            lang.mediumWordSyllableLength
        } else {
            lang.shortWordSyllableLength
        }

        //generate proto-word and set it to proto-word var, which is returned
        var protoWord = generateProtoWord(syllables, lang.protoSyllableType, lang.phonemes)


        //iterate proto-word thru steps; checks if returnsproto is false, if so it will change protoword (the return var) to the iterated version
        if (!returnsProto){
            iterateWord(protoWord, lang)
        }

        //protoword doesnt really make sense as a return name after its iterated, but it makes sense for most of the method and im lazy
        //here protoword refers to the word that is returned and iterated, or returned as a proto-word without iteration
        //
        return protoWord
    }

    private fun generateProtoWord(syllables : Int, syllableType : String, phonemesList : MutableList<Phoneme>) : MutableList<Phoneme> {
        var vowels = emptyList<Phoneme>().toMutableList()
        var consonants = emptyList<Phoneme>().toMutableList()
        for (phoneme in phonemesList){
            if (phoneme.isVowel){vowels.add(phoneme)}
            else {consonants.add(phoneme)}
        }

        var protoWord = emptyList<Phoneme>().toMutableList()

        //tempsyll for temporary syllable, because it is a temporary storage of a syllable to act as a barrier towards syllables
        //we dont like
        var tempSyll = emptyList<Phoneme>().toMutableList()
        for (i in 1..syllables){
            tempSyll = generateSyllable(vowels, consonants, syllableType)
            //basically this whole clause is to guarantee that there wont be repeat phonemes before mutations
            //yes you can have words that in the END are the same phoneme repeatedly but that will be the mutation's fault
            //and therefore naturalistic but absurd
            //some linguist is rolling in their grave rn
            while (protoWord.size > 0 && tempSyll[0] == protoWord[protoWord.size - 1]){
                tempSyll = generateSyllable(vowels, consonants, syllableType)
            }
            protoWord.plus(tempSyll)
        }

        return protoWord
    }

    private fun generateSyllable(vowels : MutableList<Phoneme>, consonants : MutableList<Phoneme>, syllableType: String) : MutableList<Phoneme> {
        //CV / CVC / VC / V / C
        var syllable = emptyList<Phoneme>().toMutableList()
        if (syllableType == "CV"){
            syllable.add(consonants.random())
            syllable.add(vowels.random())
        } else if (syllableType == "CVC"){
            syllable.add(consonants.random())
            syllable.add(vowels.random())
            syllable.add(consonants.random())
        } else if (syllableType == "VC"){
            syllable.add(vowels.random())
            syllable.add(consonants.random())
        } else if (syllableType == "V"){
            syllable.add(vowels.random())
        } else {//should be "C"
            syllable.add(consonants.random())
        }
        return syllable
    }

    private fun iterateWord(word : MutableList<Phoneme>, lang : Language) : MutableList<Phoneme> {
        var tempWord = word

        //applies the mutations of each step in the language to tempword in order
        for (step : Step in lang.steps){
            //mutation executions are stored in the step class
            tempWord = step.mutateWord(tempWord, lang)
        }
        return tempWord
    }


    //deal with returned result and communicate with front end
    fun phonemeListToString(word : MutableList<Phoneme>) : String{
        var finalWord = ""
        for (syllable : Phoneme in word){
            finalWord += syllable.digraph
        }
        return finalWord
    }
}