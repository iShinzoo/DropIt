package com.example.dropit.ui.presentation

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.dropit.R
import com.example.dropit.ui.buttonHeight
import com.example.dropit.ui.navigation.Route
import com.example.dropit.ui.presentation.common.Password
import com.example.dropit.ui.presentation.common.TextField
import com.example.dropit.ui.roundedCornerSize
import com.example.dropit.ui.smallTextSize
import com.example.dropit.ui.standardPadding
import com.example.dropit.ui.theme.ColorOrange
import com.example.dropit.ui.theme.h1TextStyle
import com.example.dropit.ui.theme.h2TextStyle
import com.example.dropit.ui.theme.h3TextStyle
import com.google.accompanist.systemuicontroller.rememberSystemUiController


@Composable
fun LoginScreen(
    navController: NavController
) {
    val context = LocalContext.current
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var passwordHidden by rememberSaveable { mutableStateOf(true) }

    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(color = Color.White, darkIcons = true)

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(16.dp))
                .background(
                    color = Color.White, // Semi-transparent overlay for frosted effect
                    shape = RoundedCornerShape(16.dp)
                )
                .padding(16.dp)
        ) {
            // Content layer with login components (no blur applied)
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState())
                    .padding(top = 80.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                LoginHeaderText()
                email = TextField(
                    icon = Icons.Default.Email,
                    plText = "Enter Your Email",
                    prefixText = ""
                )
                password = Password(
                    icon = Icons.Default.Lock,
                    plText = "sshhh... Keep it Secret!!!",
                    prefixText = ""
                )
                Spacer(modifier = Modifier.height(8.dp))
                ForgotPasswordText()
                Spacer(modifier = Modifier.height(16.dp))
                SignInButton(email, password, navController)
                Spacer(modifier = Modifier.height(16.dp))
                SignUpText(navController)
            }
        }
    }
}

// Header composable for the title text
@Composable
fun LoginHeaderText() {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = "Let's Sign you in",
            color = Color.Black,
            fontSize = 35.sp,
            style = h1TextStyle,
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.align(Alignment.Start)
        )
        Spacer(modifier = Modifier.height(18.dp))
        Text(
            text = "Welcome Back, ",
            color = ColorOrange,
            fontSize = 24.sp,
            style = h3TextStyle,
            fontWeight = FontWeight.W900,
            fontFamily = FontFamily.SansSerif,
            modifier = Modifier.align(Alignment.Start)
        )
        Text(
            text = "You have been missed",
            fontFamily = FontFamily.SansSerif,
            color = ColorOrange,
            style = h3TextStyle,
            fontWeight = FontWeight.W900,
            fontSize = 24.sp,
            modifier = Modifier
                .padding(top = 4.dp)
                .align(Alignment.Start)
        )
    }
}


// Forgot password text
@Composable
fun ForgotPasswordText() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(vertical = 10.dp),
        horizontalAlignment = Alignment.End
    ) {

        Text(text = "Forgot Password ?",
            color = Color.Black,
            fontSize = 18.sp,
            fontFamily = FontFamily.SansSerif,
            modifier = Modifier.clickable {
            })
    }
}

@Composable
fun SendResetPasswordDialogBox(onDismiss: () -> Unit, onConfirm: (String) -> Unit) {

    Dialog(
        onDismissRequest = onDismiss, properties = DialogProperties(
            usePlatformDefaultWidth = false
        )
    ) {
        val context = LocalContext.current

        Box(
            Modifier
                .padding(horizontal = 18.dp)
                .wrapContentHeight()
                .fillMaxWidth()
                .clip(RoundedCornerShape(12.dp))
                .background(color = Color.White)
        ) {
            Column(
                modifier = Modifier
                    .wrapContentHeight()
                    .padding(horizontal = 12.dp, vertical = 18.dp),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Enter Your Email",
                    color = Color.Black,
                    style = h2TextStyle,
                    modifier = Modifier
                )
                val userEmail = TextField(
                    icon = Icons.Filled.AccountCircle, plText = "xyz@gmail.com", prefixText = ""
                )
                Button(
                    onClick = {
                        if (userEmail.isEmpty()) {
                            Toast.makeText(
                                context, "Enter a valid email address", Toast.LENGTH_SHORT
                            ).show()
                        } else {
                            onConfirm(userEmail.trim())
                            onDismiss()
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Black, contentColor = Color.White
                    )
                ) {
                    Text("Submit")
                }
            }
        }
    }
}


@Composable
fun SignInButton(
    email: String, password: String, navController: NavController
) {
    val context = LocalContext.current  // Use LocalContext.current directly

    Button(
        onClick = {
            navController.navigate(Route.HomeScreen.name)
        },
        modifier = Modifier
            .fillMaxWidth()
            .height(buttonHeight)
            .clip(RoundedCornerShape(roundedCornerSize)),
        colors = ButtonDefaults.buttonColors(ColorOrange)
    ) {
        Text(
            text = "Sign In",
            color = Color.White,
            fontSize = smallTextSize,
            fontWeight = FontWeight.Bold
        )
    }
}

// Sign-up text
@Composable
fun SignUpText(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(vertical = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row {

            Text(
                text = "Don't have an Account ?",
                color = Color.DarkGray,
                fontSize = 18.sp,
                fontFamily = FontFamily.SansSerif
            )

            Text(text = "Sign Up",
                color = ColorOrange,
                fontSize = 18.sp,
                fontWeight = FontWeight.ExtraBold,
                fontFamily = FontFamily.SansSerif,
                modifier = Modifier
                    .padding(start = 5.dp)
                    .clickable {
                        navController.navigate(Route.SignUpScreen.name)
                    })
        }
    }
}

@Preview
@Composable
fun LoginScreenPreview() {
    LoginScreen(rememberNavController())
}