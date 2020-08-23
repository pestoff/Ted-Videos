package ru.pestoff.tedvideos.util

class StringUtil {

    companion object {

        fun getAuthorFromTitle(title: String): String {
            val strings = title.split("|")

            return strings.get(1).trim()
        }

        fun getThemeFromTitle(title: String): String {
            val strings = title.split("|")

            return strings.get(0).trim()
        }

        fun timeConvert(time: String): String {
            var result = ""
            val temporary = time.split(":")

            if (temporary[0] != "00") {
                result += temporary[0] + ":"
            }

            result += temporary[1] + ":"
            result += temporary[2]

            return result
        }
    }
}
