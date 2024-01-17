package com.farzin.checklist.ui.screen.add_update

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.CheckCircle
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Shapes
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.farzin.checklist.ui.theme.blueWithDarkTheme
import com.farzin.checklist.ui.theme.blueWithoutDarkTheme
import com.farzin.checklist.ui.theme.darkText

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddUpdateTextField(
    textValue: String,
    onTextValueChanged: (String) -> Unit,
    icon:Painter? = null,
    onClick:()->Unit,
    @SuppressLint("ModifierParameter") modifier: Modifier = Modifier,
    isFromDescription:Boolean = false
) {


    if (icon != null) {
        OutlinedTextField(
            value = textValue,
            onValueChange = onTextValueChanged,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                cursorColor = MaterialTheme.colorScheme.darkText,
                focusedLeadingIconColor = MaterialTheme.colorScheme.blueWithDarkTheme,
                unfocusedLeadingIconColor = MaterialTheme.colorScheme.blueWithDarkTheme,
                unfocusedBorderColor = MaterialTheme.colorScheme.blueWithDarkTheme,
                focusedBorderColor = MaterialTheme.colorScheme.blueWithoutDarkTheme,
            ),
            shape = Shapes().large,
            leadingIcon = {
                Icon(
                    painter = icon,
                    contentDescription = "",
                    modifier = Modifier
                        .size(22.dp)
                        .clickable { onClick() }
                )
            },
            modifier = modifier
                .fillMaxWidth()
                .height(52.dp)
                .clickable { onClick() },
            textStyle = MaterialTheme.typography.titleLarge.copy(
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
                shadow = Shadow(MaterialTheme.colorScheme.darkText, blurRadius = 1f)
            ),
            maxLines = if (isFromDescription) Int.MAX_VALUE else 1
        )
    }else{
        OutlinedTextField(
            value = textValue,
            onValueChange = onTextValueChanged,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                cursorColor = MaterialTheme.colorScheme.darkText,
                focusedLeadingIconColor = MaterialTheme.colorScheme.blueWithDarkTheme,
                unfocusedLeadingIconColor = MaterialTheme.colorScheme.blueWithDarkTheme,
                unfocusedBorderColor = MaterialTheme.colorScheme.blueWithDarkTheme,
                focusedBorderColor = MaterialTheme.colorScheme.blueWithoutDarkTheme,
            ),
            shape = Shapes().large,
            modifier = modifier
                .fillMaxWidth()
                .height(52.dp)
                .clickable { onClick() },
            textStyle = MaterialTheme.typography.titleLarge.copy(
                fontWeight = FontWeight.Bold,
                fontSize = 15.sp,
                shadow = Shadow(MaterialTheme.colorScheme.darkText, blurRadius = 1f)
            ),
            maxLines = if (isFromDescription) Int.MAX_VALUE else 1
        )
    }


}