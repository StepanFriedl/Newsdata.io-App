package com.stepanfriedl.newsdataio_app.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.IconButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.stepanfriedl.newsdataio_app.ui.theme.SlatePurple
import com.stepanfriedl.newsdataio_app.R


@Composable
fun LoginTextInput(
    text: MutableState<String>,
    isPassword: Boolean = false,
    onTextChangedAction: () -> Unit = {}
) {
    val isVisible = remember { mutableStateOf(!isPassword) }

    Box {
        TextField(
            value = text.value,
            onValueChange = {
                text.value = it
                onTextChangedAction()
            },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = SlatePurple.copy(alpha = 0.65f),
                unfocusedContainerColor = SlatePurple.copy(alpha = 0.65f),
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            textStyle = TextStyle(
                color = Color.White,
                fontSize = 16.sp,
                textAlign = TextAlign.Center
            ),
            modifier = Modifier
                .size(250.dp, 50.dp),
            shape = RoundedCornerShape(10.dp),
            singleLine = true,
            maxLines = 1,
            visualTransformation = if (isVisible.value) VisualTransformation.None else PasswordVisualTransformation()
        )

        if (isPassword) {
            IconButton(
                onClick = { isVisible.value = !isVisible.value },
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .size(40.dp)
                    .padding(end = 15.dp)
            ) {
                Image(
                    painter = painterResource(
                        id = if (isVisible.value) R.drawable.visibility_off else R.drawable.visibility
                    ),
                    contentDescription = "Toggle visibility",
                    contentScale = ContentScale.Fit
                )
            }
        }
    }


}

@Preview(showBackground = true)
@Composable
fun LoginTextInputPreview() {

    val text = remember {
        mutableStateOf("asd")
    }
    LoginTextInput(text = text)
}
