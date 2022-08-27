package com.example.whac_a_mole.presentation.common

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.sp

@Composable
fun ScoreText(modifier: Modifier = Modifier, message: String, score: Int) {
    Text(
        modifier = modifier,
        text = buildAnnotatedString {
            withStyle(SpanStyle(color = MaterialTheme.colors.secondary, fontSize = 20.sp)) {
                append("$message ")
            }
            withStyle(SpanStyle(color = MaterialTheme.colors.secondaryVariant, fontSize = 25.sp)) {
                append(score.toString())
            }
        },
        color = MaterialTheme.colors.surface,
        fontSize = 30.sp
    )
}