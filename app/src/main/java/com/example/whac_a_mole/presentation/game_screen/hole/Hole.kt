package com.example.whac_a_mole.presentation.game_screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun Hole(
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)
){
    val brownColor = MaterialTheme.colors.secondary


    Card(
        modifier = modifier.height(55.dp).width(90.dp),
        backgroundColor = MaterialTheme.colors.secondary,
        shape = RoundedCornerShape(100.dp),
        border = BorderStroke(width = 4.dp, color = MaterialTheme.colors.secondaryVariant),
        onClick = onClick
    ) {

    }



}