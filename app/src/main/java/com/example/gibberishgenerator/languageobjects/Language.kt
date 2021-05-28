package com.example.gibberishgenerator.languageobjects

import android.util.Log
import com.example.gibberishgenerator.LanguageSelectActivity

//full constructor
class Language (
    name : String, //lang name
    //PHONETICS                         Map<String, Boolean> corresponding digraph->bool of if included
    phonemes : MutableList<Phoneme>,
    steps : MutableList<Step>,          //list of steps included in the language
    longWordSyllableLength : Int,       //length in syllables of long/medium/short words
    mediumWordSyllableLength : Int,
    shortWordSyllableLength : Int,
    protoSyllableType : String,         //way proto syllables are put together; CV / CVC / VC / V / C are acceptable values; C = Consonant, V = Vowel
    canEndIn: String                    //things words can end in, violators deleted; V / C / VC are acceptable values, with VC meaning both
) {
    //storing the data
    var name = name
    var phonemes = phonemes
    var steps = steps
    var longWordSyllableLength = longWordSyllableLength
    var mediumWordSyllableLength = mediumWordSyllableLength
    var shortWordSyllableLength = shortWordSyllableLength
    var protoSyllableType = protoSyllableType
    var canEndIn = canEndIn

    var vowels = emptyList<Phoneme>().toMutableList()
    var consonants = emptyList<Phoneme>().toMutableList()



    val TAG = "Language"



    fun generateWord(length : String, returnsProto : Boolean) : MutableList<Phoneme> {

        setVowelsAndConsonants()

        //decide if returning proto-word or later word using parameter
        var syllables = if (length == "long"){
            longWordSyllableLength
        } else if (length == "medium"){
            mediumWordSyllableLength
        } else {
            shortWordSyllableLength
        }
        Log.d(TAG, "GenerateWord ran")

        //generate proto-word and set it to proto-word var, which is returned
        var protoWord = generateProtoWord(syllables)


        //iterate proto-word thru steps; checks if returnsproto is false, if so it will change protoword (the return var) to the iterated version
        if (!returnsProto){
            iterateWord(protoWord)
        }

        //protoword doesnt really make sense as a return name after its iterated, but it makes sense for most of the method and im lazy
        //here protoword refers to the word that is returned and iterated, or returned as a proto-word without iteration
        //
        Log.d(TAG, "Protoword = ${protoWord.toString()}")
        Log.d(TAG, "phonemes: ${LanguageSelectActivity.validPhonemes.toString()}")
        return protoWord
    }

    private fun generateProtoWord(syllables : Int) : MutableList<Phoneme> {

        var protoWord = emptyList<Phoneme>().toMutableList()

        //tempsyll for temporary syllable, because it is a temporary storage of a syllable to act as a barrier towards syllables
        //we dont like
        var tempSyll : MutableList<Phoneme> = emptyList<Phoneme>().toMutableList()
        for (i in 1..syllables){
            tempSyll = generateSyllable()
            //basically this whole clause is to guarantee that there wont be repeat phonemes before mutations
            //yes you can have words that in the END are the same phoneme repeatedly but that will be the mutation's fault
            //and therefore naturalistic but absurd
            //some linguist is rolling in their grave rn
            while (protoWord.size > 0 && tempSyll[0] == protoWord[protoWord.size - 1]){
                tempSyll = generateSyllable()
            }
            protoWord.addAll(tempSyll)
        }

        return protoWord
    }

    private fun generateSyllable() : MutableList<Phoneme> {
        //CV / CVC / VC / V / C
        var syllable = emptyList<Phoneme>().toMutableList()
        if (protoSyllableType == "CV"){
            syllable.add(consonants.random())
            syllable.add(vowels.random())
        } else if (protoSyllableType == "CVC"){
            syllable.add(consonants.random())
            syllable.add(vowels.random())
            syllable.add(consonants.random())
        } else if (protoSyllableType == "VC"){
            syllable.add(vowels.random())
            syllable.add(consonants.random())
        } else if (protoSyllableType == "V"){
            syllable.add(vowels.random())
        } else {//should be "C"
            syllable.add(consonants.random())
        }

        Log.d(TAG, "returned syllable: ${syllable}")
        return syllable
    }

    fun iterateWord(word : MutableList<Phoneme>) : MutableList<Phoneme> {
        var tempWord = word

        //applies the mutations of each step in the language to tempword in order
        for (step : Step in steps){
            //mutation executions are stored in the step class
            tempWord = step.mutateWord(tempWord, canEndIn, phonemes)
        }
        return tempWord
    }
    private fun setVowelsAndConsonants(){
        for (phoneme in phonemes){
            if (phoneme.isVowel) {
                vowels.add(phoneme)
            } else {
                consonants.add(phoneme)
            }
        }
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

