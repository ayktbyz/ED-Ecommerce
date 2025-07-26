package com.aytbyz.enuygun.presentation.components.text

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.*
import com.aytbyz.enuygun.presentation.R

@Composable
fun CustomTextInput(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    placeholder: String = stringResource(id = R.string.search_placeholder),
    textStyle: TextStyle = TextStyle(fontSize = 12.sp),
    backgroundColor: Color = Color.White,
    placeholderColor: Color = Color.Gray,
    borderColor: Color = Color.Transparent,
    shape: RoundedCornerShape = RoundedCornerShape(20.dp),
    contentPadding: PaddingValues = PaddingValues(horizontal = 12.dp, vertical = 4.dp)
) {
    BasicTextField(
        value = value,
        onValueChange = onValueChange,
        singleLine = true,
        textStyle = textStyle.merge(TextStyle(color = Color.Black)),
        modifier = modifier
            .shadow(2.dp, shape = shape)
            .background(color = backgroundColor, shape = shape)
            .border(width = 1.dp, color = borderColor, shape = shape),
        decorationBox = { innerTextField ->
            Box(
                modifier = Modifier
                    .padding(contentPadding)
                    .fillMaxWidth(),
                contentAlignment = Alignment.CenterStart
            ) {
                if (value.isEmpty()) {
                    Text(
                        text = placeholder,
                        color = placeholderColor,
                        fontSize = textStyle.fontSize
                    )
                }
                innerTextField()
            }
        }
    )
}