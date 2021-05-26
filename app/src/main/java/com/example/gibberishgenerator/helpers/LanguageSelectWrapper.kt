package com.example.gibberishgenerator.helpers

import com.example.gibberishgenerator.languageobjects.Language

data class LanguageSelectWrapper(
    val count: Int,
    val results: List<Language>
) {
}