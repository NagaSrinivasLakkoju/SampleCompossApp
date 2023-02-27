package com.example.samplecompossapp.ui.registration

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.samplecompossapp.R
import com.example.samplecompossapp.ui.launchscreens.Screens
import com.example.samplecompossapp.ui.theme.Typography

@Composable
fun UserDataScreen(navController: NavHostController? = null, name: String?, email: String?, password: String?) {
    Column(
        Modifier.padding(20.dp)) {

        Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxWidth())
        {
            Text(text = "User Details", style = Typography.h3)
        }

        Spacer(modifier = Modifier.height(30.dp))
        Text(text = "User Name :  $name", style = TextStyle(fontSize = 18.sp))
        Spacer(modifier = Modifier.height(20.dp))
        Text(text = "E-mail ID :  $email", style = TextStyle(fontSize = 18.sp))
        Spacer(modifier = Modifier.height(20.dp))
        Text(text = "Password :  $password", style = TextStyle(fontSize = 18.sp))

        Spacer(modifier = Modifier.height(20.dp))

        Box(modifier = Modifier.padding(40.dp, 0.dp, 40.dp, 0.dp)) {
            val context = LocalContext.current
            Button(

                onClick = {
                    navController?.navigate(Screens.Home)
                },
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(id = R.color.teal_700)),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(45.dp)
            ) {
                Text(text = "Home Screen",style = TextStyle(color = Color.White))
            }
        }


    }

}
