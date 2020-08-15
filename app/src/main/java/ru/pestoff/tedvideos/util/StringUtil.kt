package ru.pestoff.tedvideos.util

class StringUtil {

    companion object {

        fun getAuthorFromTitle(title: String) : String {
            val strings = title.split("|")

            return strings.get(1).trim()
        }

        fun getThemeFromTitle(title: String) : String {
            val strings = title.split("|")

            return strings.get(0).trim()
        }
    }
}