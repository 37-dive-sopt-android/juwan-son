package com.sopt.dive.core.component.button

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sopt.dive.core.util.noRippleClickable

@Preview(showBackground = true)
@Composable
private fun ReviewSoptButton() {
    SoptButton(
        label = "Button",
        isEnalbed = true,
        onClick = {}
    )
}

@Composable
fun SoptButton(
    label: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    isEnalbed: Boolean = false,
) {
    val (backgroundColor, textColor) = remember(isEnalbed) {
        when (isEnalbed) {
            true -> Color.Red to Color.White
            false -> Color.Gray to Color.Black
        }
    }
    Box(
        modifier = modifier
            .noRippleClickable(onClick)
            .fillMaxWidth()
            .background(
                color = backgroundColor,
                shape = RoundedCornerShape(20.dp)
            )
            .padding(vertical = 12.dp, horizontal = 16.dp)
    ) {
        Text(
            text = label,
            color = textColor,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}