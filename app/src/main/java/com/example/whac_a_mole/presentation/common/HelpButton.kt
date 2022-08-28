package com.example.whac_a_mole.presentation.common

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HelpButton(modifier: Modifier = Modifier, onClick: () -> Unit) {

    OutlinedButton(
        onClick = onClick,
        modifier= modifier
            .size(70.dp),
        shape = CircleShape,
        border= BorderStroke(3.dp, MaterialTheme.colors.secondary),
        contentPadding = PaddingValues(0.dp),
        colors = ButtonDefaults.outlinedButtonColors(contentColor =  MaterialTheme.colors.secondary)
    ) {
        Text(text = "?", color = MaterialTheme.colors.secondaryVariant)
    }
}