package com.stepanfriedl.newsdataio_app.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.stepanfriedl.newsdataio_app.ui.theme.LighterForestGreen

@Composable
fun ConfirmationButton(
    title: String,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)
    ) {
        Text(
            text = title,
            modifier = Modifier
                .background(
                    LighterForestGreen,
                    shape = RoundedCornerShape(10.dp)
                )
                .border(1.dp, Color.Gray, shape = RoundedCornerShape(10.dp))
                .padding(horizontal = 20.dp, vertical = 10.dp),
            style = TextStyle(
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ConfirmationButtonPreview() {

    ConfirmationButton(
        title = "Hello World",
        onClick = {}
    )

}