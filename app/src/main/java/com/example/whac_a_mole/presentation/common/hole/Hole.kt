package com.example.whac_a_mole.presentation.game_screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.whac_a_mole.R
import com.example.whac_a_mole.presentation.common.hole.HoleState

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun Hole(
    modifier: Modifier = Modifier,
    holeState: HoleState = HoleState(),
    onClick: (() -> Unit)
){
    if (!holeState.moleAppears) {
        Card(
            modifier = modifier.height(55.dp).width(90.dp),
            backgroundColor = MaterialTheme.colors.secondary,
            shape = RoundedCornerShape(100.dp),
            border = BorderStroke(width = 4.dp, color = MaterialTheme.colors.secondaryVariant),
            onClick = onClick
        ) {}

    } else {
        Image(
            modifier = modifier
                .height(55.dp)
                .width(90.dp)
                .clickable (
                    enabled = true,
                    onClick = onClick
                ),
            contentScale = ContentScale.Crop,
            painter = painterResource(R.drawable.ic_mole21),
            contentDescription = "Mole is here!")
    }

}