package com.example.newsatnow.Utils

import android.text.InputFilter
import android.text.Spanned

class NoSpaceInputFilter : InputFilter {
    override fun filter(
        source: CharSequence?,
        start: Int,
        end: Int,
        dest: Spanned?,
        dstart: Int,
        dend: Int
    ): CharSequence? {
        // Iterate through the input characters
        for (i in start until end) {
            if (Character.isWhitespace(source?.get(i) ?: ' ')) {
                // If a space is found, return an empty string to disallow it
                // or return a modified string without the space
                return "" // Disallow the space
                // return source?.subSequence(start, i).toString() + source?.subSequence(i + 1, end).toString() // Remove the space
            }
        }
        return null // Allow all other characters
    }
}