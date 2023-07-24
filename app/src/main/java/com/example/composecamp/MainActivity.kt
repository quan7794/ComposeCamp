package com.example.composecamp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composecamp.ui.theme.ComposeCampTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeCampTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    LemonadeApp()
                }
            }
        }
    }
}

@Composable
fun LemonadeApp(modifier: Modifier = Modifier) {
    var currentStep by remember { mutableStateOf(1) }
    var squeezeCount by remember { mutableStateOf(0) }
    Surface(
        modifier = modifier
            .fillMaxSize()
            .padding(12.dp)
            .background(MaterialTheme.colorScheme.tertiaryContainer)
    ) {
        when (currentStep) {
            1 -> {
                LemonTextAndImage(
                    textResId = R.string.tap_lemon_tree,
                    imageResId = R.drawable.lemon_tree,
                    contentDescResId = R.string.lemon_tree,
                    onImageClick = {
                        currentStep = 2
                        squeezeCount = (2..4).random()
                    }
                )
            }

            2 -> LemonTextAndImage(
                textResId = R.string.tap_to_squeeze,
                imageResId = R.drawable.lemon_squeeze,
                contentDescResId = R.string.lemon,
                onImageClick = {
                    squeezeCount--
                    if (squeezeCount == 0) currentStep = 3
                }
            )

            3 -> LemonTextAndImage(textResId = R.string.tap_to_drink,
                imageResId = R.drawable.lemon_drink,
                contentDescResId = R.string.glass_of_lemonade,
                onImageClick = { currentStep = 4 }
            )

            4 -> LemonTextAndImage(
                textResId = R.string.tap_empty_glass,
                imageResId = R.drawable.lemon_restart,
                contentDescResId = R.string.empty_glass,
                onImageClick = { currentStep = 1 }
            )
        }
    }
}

@Composable
fun LemonTextAndImage(
    textResId: Int,
    imageResId: Int,
    contentDescResId: Int,
    onImageClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Button(
            onClick = onImageClick,
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.tertiaryContainer)
        ) {
            Image(
                painter = painterResource(id = imageResId),
                contentDescription = stringResource(contentDescResId),
                modifier = Modifier.padding(20.dp)
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = stringResource(id = textResId),
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Preview(showBackground = true)
@Composable
fun LemonPreview() {
    ComposeCampTheme {
        LemonadeApp()
    }
}