package com.farzin.checklist.ui.screen.add_update

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.farzin.checklist.R
import com.farzin.checklist.ui.components.EditTaskButton
import com.farzin.checklist.ui.theme.blueWithoutDarkTheme
import com.farzin.checklist.ui.theme.darkText
import com.farzin.checklist.ui.theme.softgray

@Composable
fun AddTaskButtonSection(onAddClicked:()->Unit, onClearClicked:()->Unit,loadingState:Boolean) {


    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .padding(top = 20.dp)
            .padding(bottom = 30.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {

        EditTaskButton(
            text =  if (loadingState) stringResource(R.string.please_wait) else stringResource(R.string.edit_task),
            onClick = { onAddClicked() },
            color = MaterialTheme.colorScheme.blueWithoutDarkTheme,
            textColor = Color.White,
            modifier = Modifier
                .weight(0.5f)
                .height(40.dp)
        )

        EditTaskButton(
            text = stringResource(R.string.clear),
            onClick = { onClearClicked() },
            color = MaterialTheme.colorScheme.softgray,
            textColor = MaterialTheme.colorScheme.darkText,
            modifier = Modifier
                .weight(0.5f)
                .height(40.dp)
        )

    }

}