package com.farzin.checklist.ui.screen.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.farzin.checklist.model.home.CalendarUiModel
import com.farzin.checklist.ui.components.MySpacerHeight
import com.farzin.checklist.utils.DateHelper
import com.farzin.checklist.utils.DigitHelper

@Composable
fun CalenderItem(
    date: CalendarUiModel.Date,
    onClickListener: (CalendarUiModel.Date) -> Unit, // still, callback should be registered from outside
) {

    val year = date.date.year.toString()
    val month = date.date.month.toString()
    val day = date.date.dayOfMonth.toString()


    val gregorianYear = DateHelper.splitDateOneByOne(year, month, day)[0]
    val gregorianMonth = DateHelper.splitDateOneByOne(year, month, day)[1]
    val gregorianDay = DateHelper.splitDateOneByOne(year, month, day)[2]

    val completeJalaliCalendar = DateHelper.gregorianToJalali(gregorianYear, gregorianMonth, gregorianDay)

    val persianYear = DigitHelper.digitByLang(DateHelper.splitWholeDate(completeJalaliCalendar)[0].toString())
    val persianMonth = DigitHelper.digitByLang(DateHelper.splitWholeDate(completeJalaliCalendar)[1].toString())
    val persianDay = DigitHelper.digitByLang(DateHelper.splitWholeDate(completeJalaliCalendar)[2].toString())


    Card(
        modifier = Modifier
            .padding(vertical = 4.dp, horizontal = 4.dp)
            .clickable { onClickListener(date) },
        colors = CardDefaults.cardColors(
            containerColor = if (date.isSelected) MaterialTheme.colorScheme.primary
            else
                MaterialTheme.colorScheme.secondary
        ),
    ) {
        Column(
            modifier = Modifier
                .width(50.dp)
                .height(52.dp)
                .padding(4.dp)
        ) {
            Text(
                text = date.day,
                modifier = Modifier.align(Alignment.CenterHorizontally),
                style = MaterialTheme.typography.bodySmall
            )

            MySpacerHeight(height = 4.dp)

            Text(
                text = persianDay,
                modifier = Modifier.align(Alignment.CenterHorizontally),
                style = MaterialTheme.typography.bodyMedium,
            )
        }
    }

}