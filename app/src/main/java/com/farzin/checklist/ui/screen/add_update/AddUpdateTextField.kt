package com.farzin.checklist.ui.screen.add_update

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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.farzin.checklist.ui.theme.blueWithDarkTheme
import com.farzin.checklist.ui.theme.blueWithoutDarkTheme
import com.farzin.checklist.ui.theme.darkText

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddUpdateTextField(
    textValue:String,
    onTextValueChanged:(String)->Unit,
    isHaveIcon:Boolean
) {


    OutlinedTextField(
        value = textValue,
        onValueChange = onTextValueChanged,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            cursorColor = MaterialTheme.colorScheme.darkText,
            focusedLeadingIconColor = MaterialTheme.colorScheme.blueWithDarkTheme,
            unfocusedLeadingIconColor =  MaterialTheme.colorScheme.blueWithDarkTheme,
            unfocusedBorderColor = MaterialTheme.colorScheme.blueWithDarkTheme,
            focusedBorderColor = MaterialTheme.colorScheme.blueWithoutDarkTheme,
        ),
        shape = Shapes().large,
        leadingIcon = {
            if (isHaveIcon){
                Icon(
                    imageVector = Icons.Sharp.CheckCircle,
                    contentDescription = "",
                    modifier = Modifier
                        .size(26.dp)
                )
            }
        },
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp),
        textStyle = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold, fontSize = 15.sp, shadow = Shadow(MaterialTheme.colorScheme.darkText, blurRadius = 1f)),
    )


}