package com.example.whac_a_mole.presentation.start_screen


import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.whac_a_mole.common.Constants.gameScreenRoute
import com.example.whac_a_mole.presentation.common.StyledButton
import com.example.whac_a_mole.presentation.theme.Dimensions.spacerHeight
import com.example.whac_a_mole.R
import com.example.whac_a_mole.presentation.common.HelpButton


@Composable

fun StartScreen(
    navController: NavController,
    viewModel: StartScreenViewModel = hiltViewModel()
) {
    val state = viewModel.state.collectAsState().value
    val context: Context = LocalContext.current

    val highscore = state.highscore

    Column(
        modifier = Modifier.fillMaxSize().padding(top = spacerHeight),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        WhacAMoleTitle()


        Spacer(modifier = Modifier.height(spacerHeight))

        Image(painter = painterResource(R.drawable.ic_mole21), contentDescription = "Mole")

        Spacer(modifier = Modifier.height(spacerHeight * 2))

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
                navController.navigate(gameScreenRoute) },
        )



        Box(modifier = Modifier
            .fillMaxWidth()
            .weight(1f)
        ) {
            Image(
                painter = painterResource(R.drawable.ic_grass),
                contentScale = ContentScale.Crop,
                contentDescription = "Grass"
            )
            HelpButton(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(20.dp),
                onClick = {
                    Toast.makeText(context, "The goal of the game is to hit the mole as many times, as you can!", Toast.LENGTH_SHORT).show()
                }
            )

        }




    }

}


