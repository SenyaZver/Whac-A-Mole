package com.example.whac_a_mole.presentation.game_screen

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.layoutId
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.whac_a_mole.common.Constants.amountOfHoles
import com.example.whac_a_mole.common.Constants.resultScreenRoute
import com.example.whac_a_mole.domain.DataSource
import com.example.whac_a_mole.presentation.game_screen.hole.HoleState
import com.example.whac_a_mole.presentation.theme.Dimensions.spacerHeight


@OptIn(ExperimentalFoundationApi::class)
@SuppressLint("UnrememberedMutableState")
@Composable
fun GameScreen(
    navController: NavController,
    viewModel: GameScreenViewModel = hiltViewModel(),
) {
    val state = viewModel.uiState.collectAsState()


    val constrains = ConstraintSet {
        val infoBox = createRefFor("infoBox")
        val hole1 = createRefFor("holes")

        constrain(infoBox) {
            top.linkTo(parent.top)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }

        constrain(hole1) {
            top.linkTo(infoBox.bottom, margin = spacerHeight)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }
    }

    ConstraintLayout(constraintSet = constrains, modifier = Modifier.background(MaterialTheme.colors.primary).fillMaxSize()) {
        Box(modifier = Modifier
            .layoutId("infoBox")
            .height(50.dp)
            .background(MaterialTheme.colors.primaryVariant)
            .fillMaxWidth()
        ) {
            Row(
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Row {
                        Text(text = "Score: ", color = MaterialTheme.colors.surface)
                        Text(text = state.value.score.toString(), color = MaterialTheme.colors.surface)
                }
                Row {
                        Text(text = "Time left: ", color = MaterialTheme.colors.surface)
                        Text(text = state.value.timeLeft.toString(), color = MaterialTheme.colors.surface)
                }

            }
        }


        LazyVerticalGrid(
            modifier = Modifier.layoutId("holes"),
            cells = GridCells.Fixed(3),
            contentPadding = PaddingValues(20.dp)
        ) {
            items(amountOfHoles) {index ->
                Hole(
                    modifier = Modifier.padding(start = 20.dp, end = 20.dp, bottom = 100.dp),
                    holeState = state.value.holeStates[index],
                    onClick = {
                        viewModel.holeClicked(index)
                    }
                )
            }


        }




        if (state.value.timeLeft == 0L) {
            val score = viewModel.getScore()
            viewModel.saveScore(score)

            LaunchedEffect(Unit) {
                navController.navigate(resultScreenRoute)
            }
        }





    }


}