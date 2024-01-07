@file:OptIn(ExperimentalMaterial3Api::class)

package com.yogi.stagenativeapp.moduels.upcoming

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import com.yogi.stagenativeapp.R

//@Preview(showSystemUi = true)
@Composable
fun UpcomingScreenPreview(
    data: List<UpcomingItemData> = (1..5).map {
        UpcomingItemData(
            image = "",
            title = "Title $it",
            actor = "Actor $it"
        )
    }
) {
    UpcomingScreen(items = data)
}

@Preview(showSystemUi = true)
@Composable
fun PreviewUpcomingItem() {
    UpcomingItem(
        item = UpcomingItemData(
            "",
            "Title X",
            "Actor X"
        ),
        onClickRemindMe = {

        }
    )
}


@Composable
fun UpcomingScreen(items: List<UpcomingItemData>) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Coming Soon",
                        color = colorResource(id = R.color.white)
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = colorResource(id = R.color.black_status_bar)
                ),
                modifier = Modifier.background(
                    color = colorResource(id = R.color.black_status_bar)
                ),
                navigationIcon = {
                    IconButton(onClick = { }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.White
                        )
                    }
                }
            )
        }
    ) { innerPadding ->

        LazyColumn(
            contentPadding = innerPadding,
            modifier = Modifier
                .fillMaxSize()
                .background(
                    color = colorResource(id = R.color.black_window_background)
                )
                .padding(horizontal = 10.dp)
        ) {
            items(items) { item ->
                UpcomingItem(
                    item = item,
                    onClickRemindMe = {}
                )
            }
        }
    }
}

@Composable
fun UpcomingItem(
    item: UpcomingItemData,
    onClickRemindMe: () -> Unit
) {
    Card(
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
    ) {
        Column(
            modifier = Modifier
                .background(color = colorResource(id = R.color.black_bottom_navigation))
                .fillMaxWidth()
                .clip(RectangleShape),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Image(
                painter = painterResource(id = R.drawable.ic_launcher_background),
                contentScale = ContentScale.FillBounds,
                contentDescription = "Item poster",
                modifier = Modifier
                    .padding(5.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .fillMaxWidth()
                    .height(180.dp)
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp, vertical = 5.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = item.title,
                        color = colorResource(id = R.color.white),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = item.actor,
                        fontSize = 14.sp,
                        color = colorResource(id = R.color.white),
                        letterSpacing = 0.sp
                    )
                }

                Text(
                    text = "Coming Soon",
                    color = Color("#D4D4D4".toColorInt()),
                    fontSize = 16.sp,
                )
                Spacer(modifier = Modifier.width(5.dp))
                Icon(
                    imageVector = Icons.Filled.DateRange,
                    contentDescription = "Calendar",
                    tint = Color("#D4D4D4".toColorInt())
                )
            }

            Spacer(modifier = Modifier.height(5.dp))
            Button(
                onClick = onClickRemindMe,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Red
                ),
                border = BorderStroke(0.5.dp, Color.White),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp),
            ) {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        modifier = Modifier
                            .clip(CircleShape)
                            .background(color = Color.DarkGray.copy(alpha = 0.4f))
                            .size(30.dp)
                            .padding(5.dp),
                        imageVector = Icons.Outlined.Notifications,
                        contentDescription = "Notification",
                        tint = Color.White
                    )
                    Spacer(modifier = Modifier.width(5.dp))
                    Text(
                        text = "Remind Me",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}