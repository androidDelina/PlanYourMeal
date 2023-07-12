package com.example.planyourmeal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.planyourmeal.ui.theme.PlanYourMealTheme

class NameSurnameActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}

@Composable
fun NameSurnameScreen(modifier: Modifier = Modifier) {
    Icon(
        painter = painterResource(id = R.drawable.angle_left),
        contentDescription = null,
        modifier = Modifier
            .padding(top = 36.dp, start = 16.dp))
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Spacer(modifier = Modifier.height(60.dp))

        Image(
            painter = painterResource(id = R.drawable.breakfast_img),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 38.dp, top = 24.dp, end = 38.dp, bottom = 24.dp),
            contentScale = ContentScale.FillWidth

        )

        TextHighlight(text = "Укажите имя и фамилию, чтобы создать аккаунт", modifier = Modifier.padding(bottom = 24.dp))

        TextFieldWithName(name = "Имя", placeholder = "")
        TextFieldWithName(name = "Фамилия", placeholder = "", modifier = Modifier.padding(vertical = 12.dp))

        GradientButton(text = "Далее", onClick = { /*TODO*/ }, modifier = Modifier.padding(vertical = 18.dp))
    }
}

@Composable
fun TextFieldWithName(name: String, placeholder: String, modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Text(
            text = name,
            modifier = Modifier
                .padding(start = 78.dp, bottom = 6.dp),
            fontSize = 16.sp,
            fontFamily = FontFamily(Font(R.font.noto_sans_medium))
        )
//        TextFieldView(placeholder = placeholder, modifier = Modifier.padding(start = 64.dp, end = 64.dp))
    }
}

@Composable
@Preview (showBackground = true)
fun NameSurnameScreenPreview() {
    PlanYourMealTheme() {
        NameSurnameScreen()
    }
}