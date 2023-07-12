package com.example.planyourmeal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.planyourmeal.ui.theme.PlanYourMealTheme

class SmsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val dataStoreManager = DataStoreManager(this)

        setContent {
            PlanYourMealTheme {
                val onClick = {
                    finish()
                }
                val onClickButton = {

                }
                val phoneNumberState = remember {
                    mutableStateOf("")
                }
                LaunchedEffect(key1 = true) {
                    dataStoreManager?.getSettings()?.collect { settings ->
                        phoneNumberState.value = settings.phoneNumber
                    }
                }
                Surface(
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column {
                        PhoneNumberScreen(
                            getString(R.string.enter_sms_code) + "+${phoneNumberState.value}",
                            "",
                            getString(R.string.next),
                            onClick,
                            onClickButton,
                            dataStoreManager
                        )
                        Timer(modifier = Modifier.padding(top = 16.dp))
                    }
                }
            }
        }
    }
}

@Composable
fun Timer(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Отправить еще код",
            fontFamily = FontFamily(Font(R.font.noto_sans_medium)),
            color = colorResource(id = R.color.keyWordColor),
            fontSize = 14.sp
        )
        Text(
            text = "00:30",
            fontFamily = FontFamily(Font(R.font.noto_sans_medium)),
            color = colorResource(id = R.color.keyWordColor),
            fontSize = 14.sp
        )
    }
}

