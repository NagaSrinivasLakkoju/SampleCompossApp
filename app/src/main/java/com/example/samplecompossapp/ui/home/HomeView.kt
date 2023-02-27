package com.example.samplecompossapp.ui.home

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Build
import android.widget.DatePicker
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.samplecompossapp.ui.theme.*
import java.sql.Time
import java.text.Format
import java.text.SimpleDateFormat
import java.util.*

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HomeView() {

    Column(
        modifier = Modifier.padding(5.dp)
    ) {
        Text(
            text = "Date and Time Picker",
            fontWeight = FontWeight.Normal,
            color = colorPrimaryDark,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            textAlign = TextAlign.Center,
            fontSize = 20.sp
        )
        Spacer(modifier = Modifier.height(15.dp))
        val sdf = SimpleDateFormat("'Today Date Time :  'dd-MMM-yyyy ', 'hh:mm a")
        val currentDateAndTime = sdf.format(Date())
        Text(
            text = currentDateAndTime,
            fontWeight = FontWeight.Normal,
            color = black,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            textAlign = TextAlign.Left,
            fontSize = 18.sp
        )

        Spacer(modifier = Modifier.height(25.dp))
        DateTimePicker()

        Spacer(modifier = Modifier.height(50.dp))
        Divider(color = lineColor, thickness = 1.dp)
        Spacer(modifier = Modifier.height(25.dp))
        TimePicker()
        Spacer(modifier = Modifier.height(25.dp))
        Divider(color = lineColor, thickness = 1.dp)
        Spacer(modifier = Modifier.height(25.dp))
        alertDialogSample()
        Spacer(modifier = Modifier.height(10.dp))
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
            Button(onClick = { /*TODO*/ }, modifier = Modifier.weight(1f)) {
                Text(text = "Next")
            }

            Button(onClick = { /*TODO*/ }, modifier = Modifier.weight(1f)) {
                Text(text = "Previous")
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun HomeViewPreview() {
    HomeView()
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DateTimePicker() {
    val mContext = LocalContext.current
    val mYear: Int
    val mMonth: Int
    val mDay: Int

    val mCalendar = Calendar.getInstance()

    mYear = mCalendar.get(Calendar.YEAR)
    mMonth = mCalendar.get(Calendar.MONTH)
    mDay = mCalendar.get(Calendar.DAY_OF_MONTH)
    mCalendar.time = Date()

    val mDate = remember { mutableStateOf("") }

    val mDatePickerDialog = DatePickerDialog(
        mContext,
        { _: DatePicker, mYear: Int, mMonth: Int, mDay: Int ->
            //  mDate.value = "$mDay/${mMonth+1}/$mYear"
            mDate.value = DateFormater(mDay, mMonth, mYear).toString()

        }, mYear, mMonth, mDay
    )

    Column(horizontalAlignment = Alignment.CenterHorizontally) {

        Button(onClick = {
            mDatePickerDialog.show()
        }, colors = ButtonDefaults.buttonColors(backgroundColor = colorPrimaryDark)) {
            Text(text = "Open Date Picker", color = Color.White)
        }

        Spacer(modifier = Modifier.height(20.dp))
        Box(contentAlignment = Alignment.TopStart, modifier = Modifier.fillMaxWidth())
        {
            Row() {
                Text(text = "Selected Date :", fontSize = 22.sp, textAlign = TextAlign.Left)
                Spacer(modifier = Modifier.width(15.dp))
                Text(
                    text = mDate.value,
                    fontSize = 25.sp,
                    textAlign = TextAlign.Left,
                    color = colorPrimaryDark
                )
            }

        }
    }
}


@Composable
fun TimePicker() {
    val mContext = LocalContext.current

    val mCalendar = Calendar.getInstance()
    val mHour = mCalendar[Calendar.HOUR_OF_DAY]
    val mMinute = mCalendar[Calendar.MINUTE]

    val mTime = remember { mutableStateOf("") }

    val mTimePickerDialog = TimePickerDialog(
        mContext,
        { _, mHour: Int, mMinute: Int ->
            // mTime.value = "$mHour:$mMinute"
            mTime.value = getTime(mHour, mMinute).toString()

        }, mHour, mMinute, false
    )

    Column(horizontalAlignment = Alignment.CenterHorizontally) {

        Button(
            onClick = { mTimePickerDialog.show() },
            colors = ButtonDefaults.buttonColors(backgroundColor = colorPrimaryDark)
        ) {
            Text(text = "Open Time Picker", color = Color.White)
        }
        Spacer(modifier = Modifier.height(20.dp))

        Box(contentAlignment = Alignment.TopStart, modifier = Modifier.fillMaxWidth())
        {
            Row() {
                Text(text = "Selected Time :", fontSize = 22.sp, textAlign = TextAlign.Left)
                Spacer(modifier = Modifier.width(15.dp))
                Text(
                    text = mTime.value,
                    fontSize = 25.sp,
                    textAlign = TextAlign.Left,
                    color = colorPrimaryDark
                )
            }

        }
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun alertDialogSample() {
    val openDialog = remember { mutableStateOf(false) }
    val showSnackbar = remember { mutableStateOf(false) }
    val mcontext = LocalContext.current

    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxWidth()) {
        Button(onClick = {
            openDialog.value = true
        }, colors = ButtonDefaults.buttonColors(backgroundColor = colorPrimaryDark)) {
            Text(text = "Open Alert Dialog", color = Color.White)
        }
    }

    SimpleSnackbarDemo(
        show = showSnackbar.value,
        onHideSnackbar = {
            showSnackbar.value = false
        }
    )


    if (openDialog.value) {
        AlertDialog(
            onDismissRequest = { openDialog.value = false },

            title = { Text(text = "Alert Dialog", color = colorPrimaryDark) },
            text = { Text("Hello! This is our Alert Dialog..", color = textColor) },

            confirmButton = {
                TextButton(
                    onClick = {
                        openDialog.value = false
                        showSnackbar.value = true
                    }
                ) {
                    Text("Confirm", color = colorPrimaryDark)
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        openDialog.value = false
                        Toast.makeText(mcontext, "Dismiss Button Click", Toast.LENGTH_LONG).show()
                    }
                ) {
                    Text("Dismiss", color = colorPrimaryDark)
                }
            },
            backgroundColor = Color.White,
            contentColor = colorPrimaryDark


        )
    }
}

private fun getTime(hr: Int, min: Int): String? {
    val tme = Time(hr, min, 0)
    val formatter: Format
    formatter = SimpleDateFormat("hh:mm a")
    return formatter.format(tme)
}

fun DateFormater(day: Int, month: Int, year: Int): String? {
    val calendar = Calendar.getInstance()
    calendar[year, month] = day
    val format = SimpleDateFormat("dd-MMM-yyyy")
    return format.format(calendar.time)


}


@Composable
fun SimpleSnackbarDemo(
    show: Boolean,
    onHideSnackbar: () -> Unit,
) {
    ConstraintLayout(
        modifier = Modifier.fillMaxSize()
    ) {
        val snackbar = createRef()
        if (show) {
            Snackbar(
                modifier = Modifier.constrainAs(snackbar) {
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },

                action = {
                    Text(
                        "Hide",
                        modifier = Modifier.clickable(onClick = onHideSnackbar),
                        style = TextStyle(color = colorPrimary)
                    )
                }
            ) {
                Text(text = "Hey look a snackbar")
            }
        }
    }
}


