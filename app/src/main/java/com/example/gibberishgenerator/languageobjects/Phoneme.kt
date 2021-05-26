package com.example.gibberishgenerator.languageobjects

data class Phoneme (
    var digraph : String, //english digraph that is printed by the system + used as map index
    var symbol : String, //IPA symbol. could be printed if settings are changed
    var isVowel : Boolean, //whether or not this phoneme is a vowel; false is consonant, true is vowel
    var rarity : Int, //Numeric Rarity, 0 is common, 1 is medium, 2 is rare, and 3 is a placeholder/technical phoneme
    var isLong : Boolean = false
) {}