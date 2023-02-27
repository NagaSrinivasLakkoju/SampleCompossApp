package com.example.samplecompossapp.utils

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties

    @Composable
    fun DummyProgress(mCheckedState: MutableState<Boolean>) {

        var dialogState by remember { mutableStateOf(false) }

        if (!dialogState) {
           Dialog(onDismissRequest = { dialogState = true
               mCheckedState.value=false},
                DialogProperties(dismissOnBackPress = true, dismissOnClickOutside = true)
            ) {
                Box(
                    contentAlignment= Alignment.Center,
                    modifier = Modifier
                        .size(120.dp)
                        .background(Color.White, shape = RoundedCornerShape(8.dp))
                ) {
                    Column {
                        CircularProgressIndicator(modifier = Modifier.padding(6.dp, 0.dp, 0.dp, 0.dp))
                        Text(text = "Loading...", Modifier.padding(0.dp, 8.dp, 0.dp, 0.dp))

                        Button(onClick = {
                            //stopDialog(dialogState,)
                            dialogState = !dialogState
                            mCheckedState.value=false
                        }) {

                            Text(text = "Stop")

                        }
                    }
                }
            }
        }
    }

//@Composable
//fun stopDialog(){
//    dialogState = !dialogState
//    mCheckedState=false
//}
