package com.example.ssafy.ui.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ssafy.R
import com.example.ssafy.ui.theme.TextFieldC

@Composable
fun InputItem(
    modifier: Modifier = Modifier,
    inputInfo: String = "Host IP Address",
    info: String = "",
    changeInfo: (String) -> Unit = {},
    hint: String = "192.168.1.100",
    @DrawableRes id: Int = R.drawable.baseline_wifi_24
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = inputInfo,
            fontWeight =  FontWeight.Bold,
            fontSize = 16.sp
        )

        Box(
            Modifier
                .fillMaxWidth()
                .height(60.dp)
                .padding(top = 10.dp)
                .background(color = TextFieldC, shape = RoundedCornerShape(20)),
            contentAlignment = Alignment.CenterStart
        ){
        
            Row(
                Modifier
                    .padding(horizontal = 12.dp)
                    .fillMaxWidth()
                    .fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically
            ){
                Image(
                    painter = painterResource(id),
                    contentDescription = "",
                    modifier = Modifier
                        .padding(end = 12.dp)

                )

                BasicTextField(
                    value = info,
                    onValueChange = changeInfo,
                    modifier = Modifier
                        .fillMaxWidth(),
                    textStyle = TextStyle(
                        fontSize = 18.sp
                    ),
                    decorationBox = { innerTextField ->
                        if (info.isEmpty()) {
                            Text(
                                text = hint,
                                color = Color.Gray,
                                fontSize = 18.sp
                            )
                        }
                        innerTextField()
                    }
                )
            }

        }

    }
}


@Preview
@Composable
fun PreviewInputItem(
    modifier: Modifier = Modifier
) {
    InputItem()
}