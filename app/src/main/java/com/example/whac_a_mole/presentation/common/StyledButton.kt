package com.example.whac_a_mole.presentation.common

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun StyledButton(modifier: Modifier, text: String, onClick: () -> Unit) {
    OutlinedButton(
        modifier = modifier,
        content = {
            Text(
                modifier = Modifier.align(Alignment.CenterVertically),
                text = text,
                color = MaterialTheme.colors.secondaryVariant,
                fontSize = 20.sp,
                textAlign = TextAlign.Center
            )
        },
        border = BorderStroke(3.dp, MaterialTheme.colors.secondary),
        onClick = onClick
    )
}