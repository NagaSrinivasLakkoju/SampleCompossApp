package com.example.samplecompossapp.ui.registration

import android.annotation.SuppressLint
import android.widget.Toast
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
import androidx.compose.material.icons.rounded.Add
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.samplecompossapp.R
import com.example.samplecompossapp.ui.launchscreens.Screens
import com.example.samplecompossapp.ui.theme.Typography
import com.example.samplecompossapp.ui.theme.textColor
import com.example.samplecompossapp.utils.DummyProgress
import kotlinx.coroutines.CoroutineScope
import androidx.compose.material3.FloatingActionButton
import kotlinx.coroutines.launch


@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun Login(navController: NavHostController, scaffoldState: ScaffoldState) {
    val focusManager = LocalFocusManager.current
    val mcontext = LocalContext.current
    val mCheckedState = remember { mutableStateOf(false) }
    val coroutineScope: CoroutineScope = rememberCoroutineScope()


    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        ClickableText(
            text = AnnotatedString("Don't have a account? Sign up here"),
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(20.dp),
            style = Typography.h2,
            onClick = {
                navController?.navigate(Screens.Signup) /*{
                    popUpTo(Screens.Login) {
                        inclusive = true
                    }
                }*/
            },
        )
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        Row(modifier = Modifier.align(Alignment.BottomEnd)) {
            FloatingActionButton(
                onClick = {
                    coroutineScope.launch {
                        val snackbarResult = scaffoldState.snackbarHostState.showSnackbar(
                            message = "Floating Action Button",
                            actionLabel = "Close",
                            duration = SnackbarDuration.Short
                        )
                        when (snackbarResult) {
                            SnackbarResult.Dismissed -> {
                            }
                            SnackbarResult.ActionPerformed -> {
                            }
                        }
                    }
                },
                containerColor = MaterialTheme.colors.secondary,
                shape = RoundedCornerShape(16.dp),
            ) {
                Icon(
                    imageVector = Icons.Rounded.Add,
                    contentDescription = "Add FAB",
                    tint = Color.White,
                )
            }
        }
    }


    Column(Modifier.padding(20.dp), horizontalAlignment = Alignment.CenterHorizontally) {


        Image(
            painter = painterResource(com.example.samplecompossapp.R.drawable.nb),
            contentDescription = "sample image",
            Modifier
                .height(150.dp)
                .width(200.dp),
            alignment = Alignment.Center,
        )
        Spacer(modifier = Modifier.height(10.dp))

        var usernameET = remember { mutableStateOf(TextFieldValue()) }
        var passwordET = remember { mutableStateOf(TextFieldValue()) }
        var passwordVisible by rememberSaveable { mutableStateOf(false) }

        OutlinedTextField(
            singleLine = true,
            placeholder = { Text("Enter Email-Id") },
            label = { Text(text = "Username") },
            value = usernameET.value,
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
            textStyle = Typography.body1,
            onValueChange = { usernameET.value = it })

        Spacer(modifier = Modifier.height(20.dp))


        OutlinedTextField(

            singleLine = true,
            placeholder = { Text("Enter Password") },
            label = { Text(text = "Password") },
            value = passwordET.value,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            onValueChange = { passwordET.value = it },
            trailingIcon = {
                val image = if (passwordVisible)
                    Icons.Filled.Visibility
                else Icons.Filled.VisibilityOff

                // Localized description for accessibility services
                val description = if (passwordVisible) "Hide password" else "Show password"

                // Toggle button to hide or display password
                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(imageVector = image, description)
                }
            }
        )
        Spacer(modifier = Modifier.height(40.dp))


        Box(modifier = Modifier.padding(40.dp, 0.dp, 40.dp, 0.dp)) {
            Button(
                onClick = {

                    navController?.navigate(Screens.UserData + "/${usernameET.value.text}/${passwordET.value.text}/hello")
                    /* {
                         popUpTo(Screens.Login) {
                             inclusive = true
                         }
                    }*/
                },
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(id = R.color.teal_700)),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(45.dp)
            ) {
                Text(text = "Login", style = TextStyle(color = Color.White))
            }
        }

        Spacer(modifier = Modifier.height(30.dp))


        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Show Dialog",
                style = TextStyle(
                    fontSize = 20.sp,
                    color = textColor
                ),
            )
            Spacer(modifier = Modifier.width(10.dp))

            Switch(
                checked = mCheckedState.value,
                onCheckedChange = { mCheckedState.value = it })

            if (mCheckedState.value) {
                DummyProgress(mCheckedState)

            } else {
                //Toast.makeText(mcontext, "Dialog Stopped", Toast.LENGTH_SHORT).show()
            }
        }
    }
}


/*
@Preview
@Composable
fun LoginPreview() {
    Login(scaffoldState = scaffoldState)
}
*/






