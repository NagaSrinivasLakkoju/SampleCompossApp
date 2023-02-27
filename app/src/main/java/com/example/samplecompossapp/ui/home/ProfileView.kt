package com.example.samplecompossapp.ui.home

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.DrawableRes
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.DefaultAlpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import coil.compose.AsyncImage
import coil.compose.rememberImagePainter
import coil.request.ImageRequest
import com.example.samplecompossapp.BuildConfig
import com.example.samplecompossapp.R
import com.example.samplecompossapp.ui.theme.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalMaterialApi::class)
@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun ProfileView() {
    val mcontext = LocalContext.current
    var imageUri by remember { mutableStateOf<Uri?>(null) }
    var selectImages by remember { mutableStateOf(listOf<Uri>()) }
    var capturedImageUri by remember { mutableStateOf<Uri>(Uri.EMPTY) }
    val coroutineScope = rememberCoroutineScope()
    val bottomSheetState =
        rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)


    val file = mcontext.createImageFile()
    val uri = FileProvider.getUriForFile(
        Objects.requireNonNull(mcontext),
        BuildConfig.APPLICATION_ID + ".provider", file
    )

    //pick single image
    val singleImagelauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        imageUri = uri
        coroutineScope.launch {
            bottomSheetState.hide()
        }
    }

    //pick multiple imasges
    val multipleImageLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.GetMultipleContents()) {
            selectImages = it
            Log.d("SVS", "URI LIST  " + selectImages)
            coroutineScope.launch {
                bottomSheetState.hide()
            }
        }

    //capture from camera
    val cameraLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.TakePicture()) {
            // capturedImageUri = uri
            imageUri = uri

            Log.d("SVS", "CAPTURED  IMAGE  " + imageUri)
            coroutineScope.launch {
                bottomSheetState.hide()
            }
        }

    val permissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) {
        if (it) {
            Toast.makeText(mcontext, "Permission Granted", Toast.LENGTH_SHORT).show()
            // multipleImageLauncher.launch(uri)
            singleImagelauncher.launch("image/*")
        } else {
            Toast.makeText(mcontext, "Permission Denied", Toast.LENGTH_SHORT).show()
        }
    }


    ModalBottomSheetLayout(
        sheetContent = {
            ModalBottomSheetLayout(
                cameraLauncher,
                singleImagelauncher,
                multipleImageLauncher,
                mcontext,
                uri,
                permissionLauncher
            )
        },
        sheetState = bottomSheetState,
        sheetShape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp),
    )
    {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(colorResource(id = R.color.white))
                .wrapContentSize(Alignment.TopCenter)
        ) {
            Spacer(modifier = Modifier.height(50.dp))

            ImageWithBackground(
                painter = imageUri,
                backgroundDrawableResId = R.drawable.user,
                contentDescription = "",
                modifier = Modifier
                    .height(150.dp)
                    .width(150.dp)
                    .padding(8.dp)
                    .clip(CircleShape)
                    .align(Alignment.CenterHorizontally),
                contentScale = ContentScale.FillBounds,
            )

            Text(
                text = "Srinivas",
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colors.primary,
                modifier = Modifier.align(Alignment.CenterHorizontally),
                textAlign = TextAlign.Center,
                fontSize = 25.sp
            )
            Spacer(modifier = Modifier.height(20.dp))

            /*AlertDialogSample(
                singleImagelauncher,
                cameraLauncher,
                mcontext,
                multipleImageLauncher,
                uri,
                permissionLauncher
            )*/

            OutlinedButton(
                modifier = Modifier
                    .height(40.dp)
                    .padding(2.dp)
                    .align(Alignment.CenterHorizontally),
                shape = RoundedCornerShape(10.dp),
                onClick = {
                    coroutineScope.launch {
                        bottomSheetState.show()
                    }
                },
            ) {
                Text(text = "Edit", color = MaterialTheme.colors.primary)
            }
            Spacer(modifier = Modifier.height(20.dp))
            // bottomSheet(bottomSheetScaffoldState,coroutineScope,singleImagelauncher,cameraLauncher,multipleImageLauncher,uri,permissionLauncher,mcontext)

            LazyVerticalGrid(columns = GridCells.Fixed(3)) {
                items(selectImages) { uri ->
                    Image(
                        painter = rememberImagePainter(uri),
                        contentScale = ContentScale.FillWidth,
                        contentDescription = null,
                        modifier = Modifier
                            .padding(16.dp, 8.dp)
                            .size(100.dp)
                            .clickable {

                            }
                    )
                }
            }


        }
    }


}

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Preview(showBackground = true)
@Composable
fun ProfileViewPreview() {
    ProfileView()
}

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun AlertDialogSample(
    singleImagelauncher: ManagedActivityResultLauncher<String, Uri?>,
    cameraLauncher: ManagedActivityResultLauncher<Uri, Boolean>,
    mcontext: Context,
    multipleImagelauncher: ManagedActivityResultLauncher<String, List<@JvmSuppressWildcards Uri>>,
    uri: Uri,
    permissionLauncher: ManagedActivityResultLauncher<String, Boolean>
) {
    val openDialog = remember { mutableStateOf(false) }
    val showSnackbar = remember { mutableStateOf(false) }

    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxWidth()) {
        OutlinedButton(
            modifier = Modifier
                .height(40.dp)
                .padding(2.dp),
            shape = RoundedCornerShape(10.dp),
            onClick = {
                openDialog.value = true
            },
        ) {
            Text(text = "Edit", color = MaterialTheme.colors.primary)
        }
    }

    if (openDialog.value) {
        AlertDialog(
            onDismissRequest = { openDialog.value = false },

            title = { Text(text = "Edit User Profile", color = colorPrimaryDark) },
            text = { Text("Please choose Image from below options..", color = textColor) },

            confirmButton = {
                TextButton(
                    onClick = {
                        openDialog.value = false
                        showSnackbar.value = true

                        singleImagelauncher.launch("image/*")
                        //  multipleImagelauncher.launch("image/*")

                    }
                ) {
                    Text("Gallery", color = colorPrimaryDark)
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        openDialog.value = false

                        val permissionCheckResult =
                            ContextCompat.checkSelfPermission(mcontext, Manifest.permission.CAMERA)
                        if (permissionCheckResult == PackageManager.PERMISSION_GRANTED) {
                            cameraLauncher.launch(uri)
                        } else {
                            permissionLauncher.launch(Manifest.permission.CAMERA)
                        }

                    }
                ) {
                    Text("Camera", color = colorPrimaryDark)
                }
            },
            backgroundColor = Color.White,
            contentColor = colorPrimaryDark
        )


    }
}

@Composable
fun ImageWithBackground(
    painter: Any?,
    @DrawableRes backgroundDrawableResId: Int,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    alignment: Alignment = Alignment.Center,
    contentScale: ContentScale = ContentScale.Fit,
    alpha: Float = DefaultAlpha,
    colorFilter: ColorFilter? = null
) {
    Box(
        modifier = modifier
    ) {
        Image(
            painter = painterResource(backgroundDrawableResId),
            contentDescription = null,
            modifier = Modifier.matchParentSize().clip(RoundedCornerShape(10.dp)),
            contentScale = contentScale
        )

        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(painter)
                .crossfade(true).build(),
            contentDescription = contentDescription,
            contentScale = contentScale,
            modifier = Modifier.matchParentSize().clip(RoundedCornerShape(10.dp))
        )
    }
}

fun Context.createImageFile(): File {
    // Create an image file name
    val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
    val imageFileName = "JPEG_" + timeStamp + "_"
    val image = File.createTempFile(
        imageFileName,
        ".jpg",
        externalCacheDir
    )
    return image
}

@Composable
fun ModalBottomSheetLayout(
    cameraLauncher: ManagedActivityResultLauncher<Uri, Boolean>,
    singleImagelauncher: ManagedActivityResultLauncher<String, Uri?>,
    multipleImageLauncher: ManagedActivityResultLauncher<String, List<@JvmSuppressWildcards Uri>>,
    mcontext: Context,
    uri: Uri,
    permissionLauncher: ManagedActivityResultLauncher<String, Boolean>
) {
    Column(
        Modifier.fillMaxSize(), verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(20.dp))
        Divider(color = lineColor, thickness = 3.dp, modifier = Modifier.width(50.dp))
        Spacer(modifier = Modifier.height(25.dp))
        Text(text = "Choose below options", fontSize = 20.sp, color = Color.Black)
        Spacer(modifier = Modifier.height(30.dp))
        OutlinedButton(
            modifier = Modifier
                .height(40.dp)
                .padding(2.dp),
            shape = RoundedCornerShape(10.dp),
            onClick = {
                val permissionCheckResult =
                    ContextCompat.checkSelfPermission(mcontext, Manifest.permission.CAMERA)
                if (permissionCheckResult == PackageManager.PERMISSION_GRANTED) {
                    cameraLauncher.launch(uri)
                } else {
                    permissionLauncher.launch(Manifest.permission.CAMERA)
                }
            },
        ) {
            Text(text = "Camera", color = MaterialTheme.colors.primary)
        }
        Spacer(modifier = Modifier.height(15.dp))
        OutlinedButton(
            modifier = Modifier
                .height(40.dp)
                .padding(2.dp),
            shape = RoundedCornerShape(10.dp),
            onClick = {
                singleImagelauncher.launch("image/*")
            },
        ) {
            Text(text = "Gallery - Single Image", color = MaterialTheme.colors.primary)
        }
        Spacer(modifier = Modifier.height(15.dp))
        OutlinedButton(
            modifier = Modifier
                .height(40.dp)
                .padding(2.dp),
            shape = RoundedCornerShape(10.dp),
            onClick = {
                multipleImageLauncher.launch("image/*")
            },
        ) {
            Text(text = "Gallery - Multiple Image", color = MaterialTheme.colors.primary)
        }
    }
}




