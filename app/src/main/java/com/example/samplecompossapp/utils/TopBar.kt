package com.example.samplecompossapp.utils

import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.samplecompossapp.R
import com.example.samplecompossapp.ui.theme.colorPrimaryDark
import com.example.samplecompossapp.ui.theme.white

@Composable
fun TopBar() {
    TopAppBar(
        title = { Text(text = stringResource(R.string.title), fontSize = 18.sp) },
        backgroundColor = colorPrimaryDark,
        contentColor = white
    )
}

@Preview(showBackground = true)
@Composable
fun TopBarPreview() {
    TopBar()
}