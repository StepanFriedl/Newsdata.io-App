package com.stepanfriedl.newsdataio_app.utils

import androidx.compose.ui.text.intl.Locale
import java.text.SimpleDateFormat

object DateUtils {

    fun formatDate(
        inputDate: String,
        inputFormat: String = "yyyy-MM-dd HH:mm:ss",
        outputFormat: String = "dd .MM. yy"

    ): String {
        val inputFormat = SimpleDateFormat(inputFormat, java.util.Locale.getDefault())
        val date = inputFormat.parse(inputDate)

        val outputFormat = SimpleDateFormat(outputFormat, java.util.Locale.getDefault())
        return  date?.let {
            outputFormat.format(it)
        } ?: ""
    }
}