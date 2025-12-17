package com.example.xide_planner.app.ui.notes

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.*
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.xide_planner.app.ui.components.CenteredInputField
import com.example.xide_planner.app.ui.components.NoteEditorBottomBar
import com.example.xide_planner.app.ui.components.TextFormatPanel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateNoteScreen() {

    var noteTitle by remember { mutableStateOf("") }
    var noteText by remember { mutableStateOf(TextFieldValue("")) }

    var showTextFormatPanel by remember { mutableStateOf(false) }

    var isBold by remember { mutableStateOf(false) }
    var isItalic by remember { mutableStateOf(false) }
    var isUnderline by remember { mutableStateOf(false) }

    var textColor by remember { mutableStateOf(Color.Black) }
    var highlightColor by remember { mutableStateOf(Color.Transparent) }

    val undoStack = remember { mutableStateListOf<TextFieldValue>() }
    val redoStack = remember { mutableStateListOf<TextFieldValue>() }

    val currentTextStyle = SpanStyle(
        color = textColor,
        fontWeight = if (isBold) FontWeight.Bold else FontWeight.Normal,
        fontStyle = if (isItalic) FontStyle.Italic else FontStyle.Normal,
        textDecoration = if (isUnderline) TextDecoration.Underline else TextDecoration.None,
        background = highlightColor
    )

    fun applyStyleToSelection(style: SpanStyle) {
        val selection = noteText.selection

        if (selection.start == selection.end) return // nada seleccionado

        val newAnnotated = buildAnnotatedString {
            append(noteText.annotatedString)

            addStyle(
                style = style,
                start = selection.start,
                end = selection.end
            )
        }

        noteText = noteText.copy(annotatedString = newAnnotated)
    }
    Scaffold(
        containerColor = Color.Transparent,
        topBar = {
            TopAppBar(
                title = {},
                navigationIcon = {
                    IconButton(onClick = { }) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Cerrar",
                            tint = Color(0xFF8B1D3D)
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Transparent
                )
            )
        },
        bottomBar = {
            Column {
                NoteEditorBottomBar(
                    onToggleTextCase = { showTextFormatPanel = !showTextFormatPanel },
                    onUndo = {if (undoStack.isNotEmpty()) {
                        redoStack.add(noteText)
                        noteText = undoStack.removeAt(undoStack.lastIndex) } },
                    onRedo = { if (redoStack.isNotEmpty()) {
                        undoStack.add(noteText)
                        noteText = redoStack.removeAt(redoStack.lastIndex)
                    } },
                    onSave = {
                        val savedAnnotatedText = noteText.annotatedString
                        val title = noteTitle
                        // guardar luego
                    }
                )

                AnimatedVisibility(showTextFormatPanel) {
                    TextFormatPanel(
                        isBold = isBold,
                        isItalic = isItalic,
                        isUnderline = isUnderline,
                        onBoldClick = { isBold = !isBold
                            applyStyleToSelection(SpanStyle(fontWeight = FontWeight.Bold))},
                        onItalicClick = { isItalic = !isItalic
                            applyStyleToSelection(SpanStyle(fontStyle = FontStyle.Italic))},
                        onUnderlineClick = { isUnderline = !isUnderline
                            applyStyleToSelection(SpanStyle(textDecoration = TextDecoration.Underline))},
                        onBulletsClick = {},
                        onNumbersClick = {},
                        onImageClick = {},
                        selectedTextColor = textColor,
                        onTextColorSelected = { selectedColor ->
                            textColor = selectedColor
                            applyStyleToSelection(SpanStyle(color = selectedColor)) },
                                selectedHighlightColor = highlightColor,
                        onHighlightColorSelected = { selectedColor ->
                            highlightColor = selectedColor
                            applyStyleToSelection(SpanStyle(background = selectedColor)) }
                    )
                }
            }
        }
    ) { padding ->

        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
                .fillMaxSize()
        ) {
            CenteredInputField(
                value = noteTitle,
                placeholder = "Título de la nota",
                onValueChange = { noteTitle = it },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))

            BasicTextField(
                value = noteText,
                onValueChange = { newValue ->
                    // 🔁 Guardar estado previo SOLO si el texto cambia
                    if (newValue.text != noteText.text) {
                        undoStack.add(noteText)
                        redoStack.clear()
                    }
                    val oldValue = noteText

                    // 🔒 CASO 1: solo mover cursor o enfocar/desenfocar
                    if (newValue.text == oldValue.text) {
                        noteText = oldValue.copy(selection = newValue.selection)
                        return@BasicTextField
                    }

                    // 🔒 CASO 2: borrar texto (mantener formatos)
                    if (newValue.text.length < oldValue.text.length) {

                        val start = newValue.selection.start
                        val end = oldValue.selection.end

                        val newAnnotated = buildAnnotatedString {

                            // Texto antes del borrado
                            append(
                                oldValue.annotatedString.subSequence(0, start)
                            )

                            // Texto después del borrado
                            append(
                                oldValue.annotatedString.subSequence(end, oldValue.annotatedString.length)
                            )
                        }

                        noteText = TextFieldValue(
                            annotatedString = newAnnotated,
                            selection = TextRange(start)
                        )
                        return@BasicTextField
                    }

                    // ✍️ CASO 3: escribir texto nuevo
                    val addedText = newValue.text.takeLast(
                        newValue.text.length - oldValue.text.length
                    )

                    val newAnnotated = buildAnnotatedString {
                        append(oldValue.annotatedString)
                        withStyle(currentTextStyle) {
                            append(addedText)
                        }
                    }

                    noteText = TextFieldValue(
                        annotatedString = newAnnotated,
                        selection = TextRange(newAnnotated.length)
                    )
                },
                modifier = Modifier.fillMaxSize(),
                textStyle = TextStyle(fontSize = 16.sp),
                cursorBrush = SolidColor(Color(0xFF8B1D3D)),
                decorationBox = { innerTextField ->
                    if (noteText.text.isEmpty()) {
                        Text(
                            text = "Ingresa tu nota…",
                            color = Color.Gray
                        )
                    }
                    innerTextField()
                }
            )
        }
    }
}
