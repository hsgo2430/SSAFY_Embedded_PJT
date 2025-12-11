package com.example.ssafy.ferature.setting.setting

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.content.MediaType.Companion.Text
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.ssafy.R
import com.example.ssafy.ui.component.InputItem
import com.example.ssafy.ui.theme.Purple63
import com.example.ssafy.ui.theme.PurpleE0
import com.example.ssafy.ui.theme.TestCardColor
import com.example.ssafy.ui.theme.TestCardStrokeColor
import com.example.ssafy.ui.theme.TextGray
import com.example.ssafy.ui.theme.TextYellow
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

@Composable
fun SettingScreen(
    modifier: Modifier = Modifier,
    settingViewModel: SettingViewModel = hiltViewModel()
) {
    val state = settingViewModel.collectAsState().value

    SettingScreenImpl(
        modifier = modifier,
        state = state,
        onChangeHostIP = settingViewModel::changeHostIp,
        onChangePath = settingViewModel::changePath,
        testBtnClicked = settingViewModel::testBtnClicked
    )

    //HandleSideEffects(itemMarketViewModel, navigateToItemPriceDetailChartScreen, state.marketableItemIdList)
}


@Composable
fun SettingScreenImpl(
    modifier: Modifier = Modifier,
    state : SettingState = SettingState(),
    onChangeHostIP: (String) -> Unit = {},
    onChangePath: (String) -> Unit = {},
    testBtnClicked: () -> Unit = {}
) {

    Column(
        modifier = modifier
            .fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(93.dp)
                .background(color = Purple63),
            contentAlignment = Alignment.CenterStart
        ){
            Column {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp),
                    verticalAlignment = Alignment.CenterVertically
                ){

                    Image(
                        modifier = Modifier
                            .padding(end = 5.dp)
                            .size(20.dp)
                        ,
                        painter = painterResource(R.drawable.arrow_left),
                        contentDescription = ""
                    )

                    Text(
                        text = "Setting",
                        color = Color.White,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                        .padding(top = 10.dp),
                    text = "MQTT Configuration",
                    color = PurpleE0
                )
            }
        }

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(horizontal = 20.dp)
                .padding(top = 20.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
            ) {
                Row(
                    modifier = Modifier
                        .padding(all = 20.dp)
                        .fillMaxWidth()
                        .height(45.dp)

                ){
                    Image(
                        painter = painterResource(R.drawable.setting_icon),
                        contentDescription = "",
                        modifier = Modifier
                            .size(40.dp)
                            .fillMaxHeight()
                    )

                    Column(
                        modifier = Modifier
                            .padding(start = 10.dp)
                            .wrapContentHeight()
                            .fillMaxHeight(),
                        verticalArrangement = Arrangement.Center
                    ){
                        Text(
                            text = "Connection Settings",
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "Configure MQTT broker connection",
                            fontSize = 12.sp,
                            color = TextGray
                        )
                    }

                }

                InputItem(
                    modifier = Modifier
                        .padding(horizontal = 20.dp),
                    inputInfo = "Host IP Address",
                    info = state.hostIP,
                    changeInfo =  onChangeHostIP,
                    id = R.drawable.baseline_wifi_24
                )

                InputItem(
                    modifier = Modifier
                        .padding(horizontal = 20.dp)
                        .padding(top = 16.dp)
                        .padding(bottom = 20.dp),
                    id = R.drawable.git_branch,
                    inputInfo = "MQTT Topic Path",
                    changeInfo =  onChangePath,
                    info = state.path,
                    hint = "home/raspberry/sensors"
                )
            }

        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(horizontal = 20.dp)
                .padding(top = 20.dp)
                .clip(RoundedCornerShape(15.dp))
                .background(color = TestCardColor)
                .border(1.dp, TestCardStrokeColor, RoundedCornerShape(15.dp))
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier
                        .padding(horizontal = 20.dp)
                        .padding(top = 20.dp)
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    verticalAlignment = Alignment.CenterVertically

                ){
                    Image(
                        painter = painterResource(R.drawable.alert_circle),
                        contentDescription = "",
                        modifier = Modifier
                            .size(20.dp)
                            .fillMaxHeight()
                    )

                    Text(
                        modifier = Modifier.padding(start = 12.dp),
                        text = "Test your connection before saving",
                        fontWeight = FontWeight.Bold,
                        fontSize = 13.sp,
                        color = TextYellow
                    )
                }

                Box(
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .padding(top = 12.dp, bottom = 16.dp)
                        .fillMaxWidth()
                        .height(44.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(color = Color.White)
                        .border(1.dp, TestCardStrokeColor, RoundedCornerShape(8.dp))
                        .clickable {
                            testBtnClicked()
                        }
                    ,
                    contentAlignment = Alignment.Center
                    ,
                ){
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center

                    ) {
                        Image(
                            painter = painterResource(R.drawable.zap),
                            contentDescription = "",
                            modifier = Modifier
                                .size(20.dp)
                                .fillMaxHeight()
                        )

                        Text(
                            modifier = Modifier.padding(start = 8.dp),
                            text = "Test Connection",
                            fontWeight = FontWeight.Bold,
                            fontSize = 13.sp,
                            color = TextYellow
                        )
                    }
                }
            }
        }

        Button(
            onClick = { /* save */ },
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .padding(top = 40.dp)
                .fillMaxWidth()
                .height(56.dp),
            shape = RoundedCornerShape(15.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Purple63
            )
        ) {
            Icon(
                imageVector = Icons.Default.Check,
                contentDescription = null,
                tint = Color.White
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Save Configuration",
                color = Color.White,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium
            )
        }

        OutlinedButton(
            onClick = { /* cancel */ },
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .padding(top = 40.dp)
                .fillMaxWidth()
                .height(56.dp),
            shape = RoundedCornerShape(15.dp),
            border = BorderStroke(1.dp, Color(0xFFE5E7EB))
        ) {
            Icon(
                imageVector = Icons.Default.Close,
                contentDescription = null,
                tint = Color.Gray
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Cancel",
                color = Color.Gray,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium
            )
        }

    }
}

/*@Composable
private fun HandleSideEffects(
    viewModel: SettingViewModel,
    navigateToItemPriceDetailChartScreen: (Triple<Int, Int, String>) -> Unit = {},
) {
    val context = LocalContext.current
    viewModel.collectSideEffect { sideEffect ->
        when (sideEffect) {
            is SettingSideEffect.ShowMessage -> {
                val message = when (sideEffect.type) {
                    ItemMarketErrorType.BlankItemName -> context.getString(R.string.blank_item_name)
                    ItemMarketErrorType.WrongItemName -> context.getString(R.string.wrong_item_name)
                    ItemMarketErrorType.NotSale -> context.getString(R.string.not_sale_item)
                }
                showMessage(message)
            }
            is SettingSideEffect.SuccessTest -> {

            }
        }
    }
}*/

@Preview
@Composable
fun PreviewSettingScreen(
    modifier: Modifier = Modifier
) {
    SettingScreenImpl()
}
