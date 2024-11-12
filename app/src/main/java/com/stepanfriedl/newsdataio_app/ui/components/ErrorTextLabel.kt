package com.stepanfriedl.newsdataio_app.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.stepanfriedl.newsdataio_app.ui.theme.ErrorRed

@Composable
fun ErrorTextLabel(
    text: String,
    showError: MutableState<Boolean>
) {
    Text(
        modifier = Modifier
            .padding(top = 15.dp)
            .background(
                color = Color.Black.copy(alpha = if (showError.value) 0.55f else 0f),
                shape = RoundedCornerShape(4.dp)
            )
            .padding(horizontal = 8.dp, vertical = 4.dp)
        ,
        text = text,
        style = TextStyle(
            color = Color(0xFFDB483D).copy(alpha = if (showError.value) 1f else 0f),
            fontSize = 15.sp,
            fontWeight = FontWeight.Bold
        )
    )
}

@Preview(showBackground = true)
@Composable
fun ErrorTextLabelPreview() {
    val showError = remember { mutableStateOf(true) }

    ErrorTextLabel(
        text = "Hello World",
        showError = showError
    )
}