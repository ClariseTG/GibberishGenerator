package com.example.gibberishgenerator.languageobjects

class Step(
    var condition : String, //condition of when the mutation happens
    //{before X, after X, is X} "before","after","is"

    var operation : String, //the mutation that happens
    //addBefore, addAfter, add+shortenBefore, add+shortenAfter, delete, delete+lengthenBefore, delete+lengthenAfter, replace

    var targetPhoneme : Array<Phoneme>, //phoneme that is searched for
    var secondPhoneme : Phoneme //phoneme that gets replaced

) {
    //function that applies the step to a word
    fun mutateWord(w : MutableList<Phoneme>, lang : Language) : MutableList<Phoneme> {
        val wordLength = w.size - 1
        var word = w
        val tM = if (condition == "before"){-1} else if (condition == "after"){1} else {0} //target Modifier

        //if statement chooses the actual operation to be done, based on what operation the step is
        if (operation == "addBefore"){
            for (i in -tM..wordLength-tM){
                for (phoneme in targetPhoneme){
                    //match found for any of the target phonemes
                    if (word[i+tM] == phoneme) {
                        word.add(i+tM, secondPhoneme)
                    }
                }

            }

        } else if (operation == "addAfter"){
            for (i in -tM..wordLength-tM){
                for (phoneme in targetPhoneme){
                    //match found for any of the target phonemes
                    if (word[i+tM] == phoneme) {
                        word.add(i+1+tM, secondPhoneme)
                    }
                }

            }

        } else if (operation == "add+shortenBefore"){
            for (i in -tM..wordLength-tM){
                for (phoneme in targetPhoneme){
                    //match found for any of the target phonemes
                    if (word[i+tM] == phoneme) {
                        if (word[i+tM].isVowel || !phoneme.isVowel){word[i-1+tM].isLong = false}
                        word.add(i+tM, secondPhoneme)
                    }
                }

            }

        } else if (operation == "add+shortenAfter"){
            for (i in -tM..wordLength-tM){
                for (phoneme in targetPhoneme){
                    //match found for any of the target phonemes
                    if (word[i+tM] == phoneme) {
                        if (word[i+1].isVowel || !phoneme.isVowel){word[i-1+tM].isLong = false}
                        word.add(i+tM, secondPhoneme)
                    }
                }

            }
        } else if (operation == "delete"){
            for (i in -tM..wordLength-tM){
                for (phoneme in targetPhoneme){
                    //match found for any of the target phonemes
                    if (word[i+tM] == phoneme) {
                        word.removeAt(i+tM)
                    }
                }

            }
        } else if (operation == "delete+lengthenBefore"){
            for (i in -tM..wordLength-tM){
                for (phoneme in targetPhoneme){
                    //match found for any of the target phonemes
                    if (word[i+tM] == phoneme) {
                        if (word[i-1+tM].isVowel || !phoneme.isVowel){word[i-1+tM].isLong = true}
                        word.removeAt(i+tM)
                    }
                }

            }
        } else if (operation == "delete+lengthenAfter"){
            for (i in -tM..wordLength-tM){
                for (phoneme in targetPhoneme){
                    //match found for any of the target phonemes
                    if (word[i+tM] == phoneme) {
                        if (word[i-1+tM].isVowel || !phoneme.isVowel){word[i-1+tM].isLong = true}
                        word.removeAt(i+tM)
                    }
                }

            }
        } else {//else should only be "replace", maybe put if statement to log if stuff runs here thats not "replace"? catch typos
                //eh ill do it if i start getting errors everything is fine i swear
            for (i in -tM..wordLength-tM){
                for (phoneme in targetPhoneme){
                    //match found for any of the target phonemes
                    if (word[i+tM] == phoneme) {
                        word[i+tM] = secondPhoneme
                    }
                }

            }

        }

        //finish off with last-minute adjustments

        //delete the last phoneme if it isnt the correct type
        //add instead if there arent enough phonemes total
        if (lang.canEndIn == "C" && word[wordLength].isVowel){
            if (wordLength > 1){
                word.removeAt(wordLength)
            } else {
                var tempVowelList = emptyList<Phoneme>().toMutableList()
                for (phoneme in lang.phonemes){
                    if (phoneme.isVowel) {tempVowelList.add(phoneme)}
                }

                word.add(tempVowelList.random())
            }
        }

        return word
    }


}