package com.example.planyourmeal

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.planyourmeal.ui.theme.PlanYourMealTheme

class StartActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            PlanYourMealTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val onClick = {
                        val intent = Intent(this@StartActivity, PhoneNumberActivity::class.java)
                        startActivity(intent)
                    }

                    LogInScreen(onButtonClick = onClick)
                }
            }
        }
    }
}

@Composable
fun LogInScreen(modifier: Modifier = Modifier, onButtonClick: () -> Unit) {
    val boldFont = FontFamily(Font(R.font.noto_sans_bold))
    val semiBoldFont = FontFamily(Font(R.font.noto_sans_semi_bold))

    val painter = painterResource(id = R.drawable.spagetti_img)

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
    ) {

        Spacer(modifier = modifier.height(50.dp))
        Text(
            text = stringResource(R.string.food_plan),
            fontFamily = boldFont,
            fontSize = 28.sp,
            modifier = modifier
                .fillMaxWidth(),
            textAlign = TextAlign.Center
        )

        TextHighlight(stringResource(R.string.slogan_1))

        Image(
            painter = painter,
            contentDescription = null,
            modifier = modifier
                .padding(start = 16.dp, end = 16.dp)
                .fillMaxWidth(),
            contentScale = ContentScale.FillWidth
        )

        Text(
            text = stringResource(R.string.slogan_2),
            modifier = modifier
                .padding(24.dp),
            fontFamily = semiBoldFont,
            color = colorResource(id = R.color.keyWordColor),
            textAlign = TextAlign.Center
        )

        GradientButton(stringResource(R.string.login), onButtonClick, modifier)
        ButtonWithBorder(text = stringResource(R.string.registration), onClick = onButtonClick, modifier)

    }
}

@Composable
fun GradientButton(text: String, onClick: () -> Unit, modifier: Modifier = Modifier) {
    Button(
        onClick = { onClick() },
        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
        modifier = modifier
            .width(180.dp)
            .fillMaxWidth()
            .background(
                brush = Brush.verticalGradient(
                    listOf(
                        colorResource(id = R.color.buttonGradient1),
                        colorResource(id = R.color.buttonGradient2)
                    )
                ),
                shape = ButtonDefaults.shape
            )
    ) {
        Text(
            text = text,
            fontFamily = FontFamily(Font(R.font.noto_sans_bold)),
            color = Color.Black,
            fontSize = 16.sp
        )
    }
}

@Composable
fun ButtonWithBorder(text: String, onClick: () -> Unit, modifier: Modifier = Modifier) {
    Button(
        onClick = { onClick() },
        border = BorderStroke(2.dp, colorResource(id = R.color.buttonGradient2)),
        colors = ButtonDefaults.outlinedButtonColors(),
        modifier = modifier
            .width(180.dp)
            .padding(top = 8.dp)
    ) {
        Text(
            text = text,
            color = Color.Black,
            fontSize = 16.sp,
            fontFamily = FontFamily(Font(R.font.noto_sans_bold))
        )
    }
}

@Composable
fun TextHighlight(text: String,
                  modifier: Modifier = Modifier) {
    Text(text = text,
        fontSize = 19.sp,
        fontFamily = FontFamily(Font(R.font.noto_sans_semi_bold)),
        modifier = modifier
            .padding(start = 32.dp, end = 32.dp),
        textAlign = TextAlign.Center,
        color = colorResource(id = R.color.keyWordColor),
        softWrap = true
    )
}


@Preview(showBackground = true)
@Composable
fun LogInScreenPreview() {
    PlanYourMealTheme {
        LogInScreen(onButtonClick = { })
    }
}