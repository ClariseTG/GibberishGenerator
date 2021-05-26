package com.example.gibberishgenerator.languageobjects

//full constructor
class Language (
    name : String, //lang name
    //PHONETICS                         Map<String, Boolean> corresponding digraph->bool of if included
    phonemes : MutableList<Phoneme>,
    steps : MutableList<Step>,          //list of steps included in the language
    longWordSyllableLength : Int,       //length in syllables of long/medium/short words
    mediumWordSyllableLength : Int,
    shortWordSyllableLength : Int,
    consonantDensity : Int,             //maximum number of consonants allowed in a cluster before middle consonants get deleted
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
    var consonantDensity = consonantDensity
    var protoSyllableType = protoSyllableType
    var canEndIn = canEndIn
}

