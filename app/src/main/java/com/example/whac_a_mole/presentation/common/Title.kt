package com.example.whac_a_mole.presentation.start_screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun WhacAMoleTitle(modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .fillMaxWidth(0.5f)
            .fillMaxHeight(0.1f)
            .padding(5.dp),
        shape = RoundedCornerShape(15.dp),
        backgroundColor = MaterialTheme.colors.surface,
        border = BorderStroke(width = 5.dp, color = MaterialTheme.colors.onBackground),
        elevation = 10.dp
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Text(
                text = buildAnnotatedString {
                    withStyle(SpanStyle(color = MaterialTheme.colors.secondaryVariant, fontSize = 25.sp)) {
                        append("W")
                    }

                    withStyle(SpanStyle(color = MaterialTheme.colors.secondary, fontSize = 20.sp)) {
                        append("hac")
                    }
                    withStyle(SpanStyle(color = MaterialTheme.colors.secondaryVariant, fontSize = 25.sp)) {
                        append("-A-M")
                    }
                    withStyle(SpanStyle(color = MaterialTheme.colors.secondary, fontSize = 20.sp)) {
                        append("ole")
                    }

                },
                fontSize = 20.sp,
                modifier = Modifier
                    .align(Alignment.Center)

            )
        }
    }
}