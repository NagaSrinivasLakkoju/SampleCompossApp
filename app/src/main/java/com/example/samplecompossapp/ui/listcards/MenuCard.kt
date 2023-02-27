package com.example.samplecompossapp.ui.listcards

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.samplecompossapp.data.menuItems
import com.example.samplecompossapp.ui.theme.SampleCompossAppTheme


@Composable
fun MenuCard(menu: String, desc: String) {
    Card(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
            .wrapContentHeight(),
        shape = MaterialTheme.shapes.medium,
        elevation = 5.dp,
        backgroundColor = MaterialTheme.colors.surface
    ) {
        Column(
            Modifier
                .padding(8.dp)
                .clickable {

                }
        ) {
            Spacer(modifier = Modifier.height(5.dp))
            Text(
                text = menu,
                style = TextStyle(fontSize = 22.sp),
                modifier = Modifier
                    .padding(bottom = 8.dp)
                    .fillMaxWidth(),
                color = MaterialTheme.colors.onSurface,
            )
            Text(
                text = desc,
                style = TextStyle(fontSize = 16.sp),
                modifier = Modifier.padding(bottom = 4.dp)
            )
        }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO, showBackground = true, name = "Light mode")
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true, name = "Dark mode")
@Composable
fun MenuCardPreview() {
    SampleCompossAppTheme() {
        MenuCard(menuItems[0].menu, menuItems[0].desc)
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO, showBackground = true, name = "Light mode")
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true, name = "Dark mode")
@Composable
fun MenuCard1Preview() {
    SampleCompossAppTheme {
        MenuCard(menuItems[1].menu, menuItems[1].desc)
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO, showBackground = true, name = "Light mode")
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true, name = "Dark mode")
@Composable
fun MenuCard2Preview() {
    SampleCompossAppTheme {
        MenuCard(menuItems[2].menu, menuItems[2].desc)
    }
}