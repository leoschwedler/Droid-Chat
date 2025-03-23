package com.example.droidchat.commom.components

import android.content.Context
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp
import com.example.droidchat.DroidChatFileProvider
import com.example.droidchat.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfilePictureOptionsModalBottomSheet(
    onDismissRequest: () -> Unit,
    onPictureSelected: (uri: Uri) -> Unit,
    sheetState: SheetState = rememberModalBottomSheetState(),
    context: Context = LocalContext.current,
    modifier: Modifier = Modifier

) {

    var photoUri by remember { mutableStateOf<Uri?>(null) }

    val imagePicker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = {
            it?.let{
                onPictureSelected(it)
            }
        }
    )

     val cameraLauncher = rememberLauncherForActivityResult(
         contract = ActivityResultContracts.TakePicture(),
         onResult = {
             it.let{ sucess ->
                 if (sucess && photoUri != null) {
                     onPictureSelected(photoUri!!)
                 }
             }
         }
     )

    ModalBottomSheet(
        modifier = modifier,
        onDismissRequest = onDismissRequest,
        sheetState = sheetState
    ) {
        ProfilePictureOption(
            label = "Tirar foto",
            icon = R.drawable.ic_photo_camera,
            onCLick = {
                photoUri = DroidChatFileProvider.getImageUri(context.applicationContext)
                cameraLauncher.launch(photoUri!!)
            }
        )
        ProfilePictureOption(
            label = "Galeria",
            icon = R.drawable.ic_photo_library,
            onCLick = {
                imagePicker.launch("image/*")
            }
        )
    }
}

@Composable
fun ProfilePictureOption(
    label: String,
    icon: Int,
    onCLick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .defaultMinSize(minHeight = 48.dp)
            .padding(horizontal = 16.dp).clickable { onCLick() }
        ,
        verticalAlignment = Alignment.CenterVertically

    ) {
        Icon(painter = painterResource(icon), contentDescription = null)
        Spacer(Modifier.width(8.dp))
        Text(text = label, style = MaterialTheme.typography.bodyLarge)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
private fun ProfilePictureOptionsModalBottomSheetPreview() {
    val sheetState = SheetState(
        skipPartiallyExpanded = false,
        density = Density(LocalContext.current),
        initialValue = SheetValue.Expanded
    )

    ProfilePictureOptionsModalBottomSheet(
        onDismissRequest = {},
        sheetState = sheetState,
        onPictureSelected = {}

    )

}