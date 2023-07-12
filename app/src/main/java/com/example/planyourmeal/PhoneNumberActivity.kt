

package com.example.planyourmeal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.material3.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.planyourmeal.ui.theme.PlanYourMealTheme
import kotlinx.coroutines.launch

class PhoneNumberActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val dataStoreManager = DataStoreManager(this)

        setContent {
            PlanYourMealTheme {
                val onClick = {
                    finish()
                }

                val onClickButton = {
                    val intent = Intent(
                        this@PhoneNumberActivity,
                        SmsActivity::class.java)
                    startActivity(intent)
                }

                Surface(
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column() {
                        PhoneNumberScreen(
                            getString(R.string.enter_phone),
                            "+7 926 *** ** 51",
                            getString(R.string.next),
                            onClick,
                            onClickButton,
                            dataStoreManager)
                        TermsOfUseText(
                            onClickTermsOfUse = { /*TODO*/ },
                            onClickPrivacyPolicy = { /*TODO*/ },
                            modifier = Modifier.padding(vertical = 48.dp, horizontal = 10.dp))
                    }
                }
            }
        }
    }
}

@Composable
fun PhoneNumberScreen(text: String, placeholder: String, buttonText: String, onClickIcon: () -> Unit, onClickButton: () -> Unit, dataStoreManager: DataStoreManager, modifier: Modifier = Modifier) {

    Icon(
        painter = painterResource(id = R.drawable.angle_left),
        contentDescription = null,
        modifier = modifier
            .padding(top = 36.dp, start = 16.dp)
            .clickable { onClickIcon() })
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = modifier.height(20.dp))

        Image(
            painter = painterResource(id = R.drawable.food_img),
            contentDescription = null,
            modifier = modifier
                .fillMaxWidth()
                .padding(start = 38.dp, top = 24.dp, end = 38.dp, bottom = 24.dp),
            contentScale = ContentScale.FillWidth

        )

        TextHighlight(text = text)

        TextFieldView(placeholder, dataStoreManager, modifier = Modifier.padding(start = 64.dp, end = 64.dp, top = 48.dp, bottom = 24.dp))

        GradientButton(text = buttonText, onClick = { onClickButton() })
    }
}

@Composable
fun TextFieldView(placeholder: String, dataStoreManager: DataStoreManager, modifier: Modifier = Modifier) {
    var textState by remember {
        mutableStateOf("")
    }
    val coroutine = rememberCoroutineScope()

    BasicTextField(
        value = textState,
        onValueChange =  { newText ->
            textState = newText


            coroutine.launch {
                dataStoreManager.saveSettings(
                    SettingsData(
                        textState,
                        "",
                        ""
                    )
                )
            }
        },
        textStyle = TextStyle(
            fontSize = 20.sp,
            fontWeight = FontWeight.Medium,
            color = Color.Red
        ),
        decorationBox = {innerTextField ->
            Box(
                modifier = modifier
                    .fillMaxWidth()
                    .border(
                        width = 2.dp,
                        color = colorResource(id = R.color.borderColor),
                        shape = RoundedCornerShape(size = 16.dp)
                    )
                    .padding(horizontal = 16.dp, vertical = 12.dp), // inner padding
            ) {
                if (textState.isEmpty()) {
                    Text(
                        text = placeholder,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color.LightGray,
                        textAlign = TextAlign.Center
                    )
                }
                innerTextField()
            }
        }
    )
}

@Composable
fun TermsOfUseText(
    onClickTermsOfUse: () -> Unit,
    onClickPrivacyPolicy: () -> Unit,
    modifier: Modifier = Modifier
) {
    val noto_sans_semi_bold = FontFamily(Font(R.font.noto_sans_semi_bold))

    Column(modifier = modifier.fillMaxSize()) {
        Text(
            text = stringResource(R.string.terms_of_use_text_1),
            fontSize = 12.sp,
            fontFamily = noto_sans_semi_bold,
            color = colorResource(id = R.color.keyWordColor),
            modifier = Modifier
                .padding(start = 24.dp, end = 24.dp))

        Text(
            text = stringResource(R.string.terms_of_use),
            fontSize = 12.sp,
            fontFamily = noto_sans_semi_bold,
            color = colorResource(id = R.color.blue),
            modifier = Modifier
                .padding(start = 24.dp, end = 24.dp)
                .clickable { onClickTermsOfUse() }
            )

        Text(
            text = stringResource(R.string.terms_of_use_text_2),
            fontSize = 12.sp,
            fontFamily = noto_sans_semi_bold,
            color = colorResource(id = R.color.keyWordColor),
            modifier = Modifier
                .padding(start = 24.dp, end = 24.dp))

        Text(
            text = stringResource(R.string.terms_of_use_text_3),
            fontSize = 12.sp,
            fontFamily = noto_sans_semi_bold,
            color = colorResource(id = R.color.keyWordColor),
            modifier = Modifier
                .padding(start = 24.dp, end = 5.dp))

        Text(
            text = stringResource(R.string.privacy_policy),
            fontSize = 12.sp,
            fontFamily = noto_sans_semi_bold,
            color = colorResource(id = R.color.blue),
            modifier = Modifier
                .padding(start = 24.dp, end = 24.dp)
                .clickable { onClickPrivacyPolicy() })

    }
}