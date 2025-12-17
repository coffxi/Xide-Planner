package com.example.xide_planner.app.ui.components

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.buildAnnotatedString

fun applyStyleToSelection(
    noteText: TextFieldValue,
    style: SpanStyle
): TextFieldValue {
    val start = noteText.selection.start
    val end = noteText.selection.end
    if (start == end) return noteText // nada seleccionado

    val oldText = noteText.annotatedString
    val newText = buildAnnotatedString {
        append(oldText.subSequence(0, start).toString())
        append(AnnotatedString(oldText.subSequence(start, end).toString(), style))
        append(oldText.subSequence(end, oldText.length).toString())
    }

    return TextFieldValue(
        annotatedString = newText,
        selection = TextRange(end) // mantener cursor al final
    )
}