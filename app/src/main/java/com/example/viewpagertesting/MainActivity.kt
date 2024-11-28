package com.example.viewpagertesting

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.viewpagertesting.ui.theme.ViewPagerTestingTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ViewPagerTestingTheme {
                PagerScreen()
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PagerScreen(modifier: Modifier = Modifier) {
    val items = mutableListOf<OnBoardingItem>()
    items.add(
        OnBoardingItem(
            1,
            R.drawable.baseline_alarm_24,
            "title1",
            "subtitle1"
        )
    )
    items.add(
        OnBoardingItem(
            2,
            R.drawable.baseline_archive_24,
            "title2",
            "subtitle2"
        )
    )
    items.add(
        OnBoardingItem(
            3,
            R.drawable.baseline_anchor_24,
            "title3",
            "subtitle3"
        )
    )
    val pagerState = rememberPagerState {
        items.size
    }
    HorizontalPager(state = pagerState) { currentPage ->
        val alpha by animateFloatAsState(
            targetValue = if (pagerState.currentPage == currentPage) 1f else 0f,
            animationSpec = tween(durationMillis = 300)
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .graphicsLayer(alpha = alpha)
        ) {
            OnBoardingUi(item = items[currentPage])
        }
    }
    Row(
        Modifier
            .wrapContentHeight()
            .fillMaxSize()
            .padding(bottom = 100.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.Bottom
    ) {
        repeat(pagerState.pageCount) { iteration ->
            val color = if (pagerState.currentPage == iteration) Color.DarkGray else Color.LightGray
            val width by animateDpAsState(
                targetValue = if (pagerState.currentPage == iteration) 43.dp else 28.dp,
                animationSpec = tween(durationMillis = 300)
            )
            Box(
                modifier = Modifier
                    .padding(2.dp)
                    .clip(CircleShape)
                    .background(color)
                    .height(5.dp)
                    .width(width)
            )
        }
    }
}

@Composable
fun OnBoardingUi(
    item: OnBoardingItem
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(modifier = Modifier.size(128.dp), painter = painterResource(id = item.image), contentDescription = null)
        Text(text = item.title)
        Text(text = item.subtitle)
    }
}

@Preview(showBackground = true)
@Composable
private fun OnBoardingUiPreview() {
    OnBoardingUi(
        item = OnBoardingItem(
            1,
            R.drawable.baseline_alarm_24,
            "title1",
            "subtitle1"
        )
    )
}

data class OnBoardingItem(
    val id: Int,
    val image: Int,
    val title: String,
    val subtitle: String
)