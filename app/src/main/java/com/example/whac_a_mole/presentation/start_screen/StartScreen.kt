package com.example.whac_a_mole.presentation.start_screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.whac_a_mole.common.Constants.gameScreenRoute
import com.example.whac_a_mole.presentation.common.StyledButton
import com.example.whac_a_mole.presentation.theme.Dimensions.spacerHeight
import com.example.whac_a_mole.presentation.theme.WhacAMoleTheme

@Composable

fun StartScreen(
    navController: NavController,
    viewModel: StartScreenViewModel = hiltViewModel()
) {
    val state = viewModel.state.collectAsState().value

    val highscore = state.highscore

    Column(
        modifier = Modifier.fillMaxSize().padding(top = spacerHeight),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        WhacAMoleTitle()

        Spacer(modifier = Modifier.height(spacerHeight))
        //add a Mole image
        Spacer(modifier = Modifier.height(330.dp))

        Text(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            text = "HighScore: ${highscore}",
            color = MaterialTheme.colors.secondaryVariant,
            fontSize = 25.sp
        )

        Spacer(modifier = Modifier.height(spacerHeight))

        StyledButton(
            modifier = Modifier
                .height(50.dp)
                .width(100.dp),
            text = "Play",
            onClick = {
                navController.navigate(gameScreenRoute)
            }
        )

        Spacer(modifier = Modifier.height(spacerHeight))


        Box(modifier = Modifier
            .fillMaxWidth()
            .weight(1f)
            .background(MaterialTheme.colors.primary)) {

        }




    }

}


