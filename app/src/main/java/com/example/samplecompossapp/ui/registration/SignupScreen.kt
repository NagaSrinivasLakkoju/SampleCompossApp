package com.example.samplecompossapp.ui.registration

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.*
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.samplecompossapp.R
import com.example.samplecompossapp.ui.launchscreens.Screens
import com.example.samplecompossapp.ui.theme.Typography
import com.example.samplecompossapp.ui.theme.textColor

@Composable
fun Signup(navController: NavHostController? = null){
    val focusManager = LocalFocusManager.current
    Box(modifier = Modifier.fillMaxSize()) {
        ClickableText(
            text = AnnotatedString("Already have an account? Login here"),
            modifier = Modifier.align(Alignment.BottomCenter).padding(20.dp),
            style = Typography.h2,
            onClick = {
                navController?.navigate(Screens.Login){
                    popUpTo(Screens.Signup) {
                        inclusive = true
                    }
                }
            },
        )
    }
    Column (Modifier.padding(20.dp,), horizontalAlignment = Alignment.CenterHorizontally,){

        Image(painter = painterResource(R.drawable.nb),
            contentDescription = "sample image",
            Modifier.height(150.dp).width(200.dp),
            alignment = Alignment.Center,
        )
        Spacer(modifier = Modifier.height(10.dp))

        var usernameET = remember { mutableStateOf(TextFieldValue()) }
        var emailET = remember { mutableStateOf(TextFieldValue()) }
        var passwordET = remember { mutableStateOf(TextFieldValue()) }
        var passwordVisible by rememberSaveable { mutableStateOf(false) }

        OutlinedTextField(
            singleLine = true,
            placeholder = { Text("Enter Full Name") },
            label = { Text(text = "Name") },
            value = usernameET.value,
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
            onValueChange = { usernameET.value = it })

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            singleLine = true,
            placeholder = { Text("Enter E-mail Id") },
            label = { Text(text = "E-mail Id") },
            value = emailET.value,
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
            onValueChange = { emailET.value = it })

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            singleLine = true,
            placeholder = { Text("Enter Password") },
            label = { Text(text = "Crete Password") },
            value = passwordET.value,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password, imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(onDone = {focusManager.clearFocus()}),
            shape = RoundedCornerShape(8.dp),
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            onValueChange = { passwordET.value = it },
            trailingIcon = {
                val image = if (passwordVisible)
                    Icons.Filled.Visibility
                else Icons.Filled.VisibilityOff

                // Localized description for accessibility services
                val description = if (passwordVisible) "Hide password" else "Show password"

                // Toggle button to hide or display password
                IconButton(onClick = {passwordVisible = !passwordVisible}){
                    Icon(imageVector  = image, description)
                }
            }
        )
        Spacer(modifier = Modifier.height(20.dp))

        Box(modifier = Modifier.padding(40.dp, 0.dp, 40.dp, 0.dp)) {
            val context = LocalContext.current
            Button(
                onClick = {
                    navController?.navigate(Screens.UserData+"/${usernameET.value.text}/${emailET.value.text}/${passwordET.value.text}"){
                        popUpTo(Screens.Signup) {
                            inclusive = true
                        }
                    }
                },
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(id = R.color.teal_700)),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(45.dp)
            ) {
                Text(text = "Sign Up",style = TextStyle(color = Color.White))
            }
        }
    }
}