package com.example.dropit.ui.presentation

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.dropit.ui.buttonHeight
import com.example.dropit.ui.navigation.Route
import com.example.dropit.ui.presentation.common.Password
import com.example.dropit.ui.presentation.common.TextField
import com.example.dropit.ui.smallTextSize
import com.example.dropit.ui.standardPadding
import com.example.dropit.ui.theme.h1TextStyle
import com.example.dropit.ui.theme.h3TextStyle
import com.example.dropit.ui.theme.infoTextStyle

@Composable
fun SignUpScreen(navController: NavController) {
    val context = LocalContext.current
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordHidden by remember { mutableStateOf(true) }

    BackHandler { navController.navigate(Route.WelcomeScreen.name) }


    Box(
        modifier = Modifier
            .background(Color.White)
            .fillMaxSize()
            .padding(standardPadding)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 80.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            // Header Section
            SignUpHeader()

            Spacer(modifier = Modifier.height(32.dp))

            email= TextField(icon = Icons.Default.Email, plText = "Enter Your Email", prefixText = "" )
            password= Password(icon = Icons.Default.Lock, plText = "sshhh... Keep it Secret!!!", prefixText = "" )

            Spacer(modifier = Modifier.height(22.dp))

            // Sign Up Button
            SignUpButton {

            }

            Spacer(modifier = Modifier.height(12.dp))

            // Navigation to Sign In
            SignInNavigation(navController)
        }
    }
}

// Reusable header composable for sign-up
@Composable
fun SignUpHeader() {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = "Let's Register",
            style = h1TextStyle,
            fontSize = 35.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Account",
            style = h1TextStyle,
            fontSize = 35.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(18.dp))
        Text(
            text = "Hello Champ, hope you",
            style = h3TextStyle,
            fontSize = 20.sp,
            color = Color.DarkGray
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "have a grateful journey",
            style = h3TextStyle,
            fontSize = 20.sp,
            color = Color.DarkGray
        )
    }
}


// Sign Up button with rounded corners
@Composable
fun SignUpButton(onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(buttonHeight)
            .clip(RoundedCornerShape(12.dp)),
        colors = ButtonDefaults.buttonColors(containerColor = Color.Black)
    ) {
        Text(
            text = "Sign Up",
            color = MaterialTheme.colorScheme.onPrimary,
            fontWeight = FontWeight.Bold,
            fontSize = smallTextSize
        )
    }
}

// Reusable composable for sign-in navigation
@Composable
fun SignInNavigation(navController: NavController) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Already have an Account?",
            fontSize = 15.sp,
            style = infoTextStyle,
            color = Color.DarkGray
        )
        Spacer(Modifier.width(8.dp))
        Text(
            text = "Sign In",
            modifier = Modifier.clickable {
                navController.navigate(Route.LoginScreen.name)
            },
            fontSize = 16.sp,
            fontWeight = FontWeight.ExtraBold,
            color = Color.Black
        )
    }
}