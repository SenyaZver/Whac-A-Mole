package com.example.whac_a_mole.presentation.result_screen


import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.whac_a_mole.common.Constants
import com.example.whac_a_mole.presentation.common.ScoreText
import com.example.whac_a_mole.presentation.common.StyledButton
import com.example.whac_a_mole.presentation.theme.Dimensions.spacerHeight

@Composable
fun ResultScreen(
    navController: NavController,
    viewModel: ResultScreenViewModel = hiltViewModel(),
)  {

    val state = viewModel.uiState.collectAsState().value
    val score = state.score
    val recordScore = state.highScore




    Column(
        modifier = Modifier
            .padding(top = 30.dp, start = 10.dp, bottom = 10.dp, end = 10.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "Game Over!",
            color = MaterialTheme.colors.surface,
            fontSize = 30.sp
        )

        Spacer(modifier=Modifier.height(spacerHeight))
        ScoreText(message = "Your Score is", score = score)


        Spacer(modifier=Modifier.height(spacerHeight))
        ScoreText(message = "Your Highscore is", score = recordScore)
        Spacer(modifier=Modifier.height(spacerHeight))


        if (state.isHighScore) {
            Box(Modifier.height(spacerHeight * 2).fillMaxWidth()) {
                Text(
                    modifier = Modifier.align(Alignment.Center),
                    text = "This is a Record!",
                    fontSize = 30.sp,
                    color = MaterialTheme.colors.secondaryVariant
                )
            }
        } else {
            Spacer(modifier = Modifier.height(spacerHeight * 2))
        }


        Spacer(modifier=Modifier.height(spacerHeight))

        StyledButton(
            modifier = Modifier.height(70.dp).width(100.dp),
            text = "Play Again",
            onClick = {
                navController.navigate(Constants.gameScreenRoute)
            }
        )

        Spacer(modifier=Modifier.height(20.dp))
        StyledButton(
            modifier = Modifier.height(60.dp).width(100.dp),
            text = "Menu",
            onClick = {
                navController.navigate(Constants.startScreenRoute)
            }
        )



    }


}


