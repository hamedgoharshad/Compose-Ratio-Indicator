package gord.hamed.indicator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import gord.hamed.indicator.ui.theme.ComposeRatioIndicatorTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalPagerApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeRatioIndicatorTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val pagerState = rememberPagerState()

                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.SpaceBetween
                    ) {
                        HorizontalPager(
                            count = 5,
                            state = pagerState,
                            contentPadding = PaddingValues(0.dp),
                            verticalAlignment = Alignment.Top,
                            itemSpacing = 0.dp,
                            modifier = Modifier
                                .fillMaxHeight(.7f)
                                .padding(0.dp)
                        ) { page: Int ->

                            Column(
                                verticalArrangement = Arrangement.Top,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Image(
                                    painter = painterResource(R.drawable.ic_launcher_background),
                                    contentDescription = "",
                                    Modifier
                                        .fillMaxWidth()
                                        .aspectRatio(1f)
                                )
                                Greeting(name = "Hamed!")
                            }
                        }

                        Spacer(Modifier.fillMaxHeight(.1f))
                        RatioIndicator(
                            pagerState = pagerState,
                            modifier = Modifier
                                .align(Alignment.Start)
                                .padding(horizontal = 40.dp),
                            activeColor = MaterialTheme.colors.primary,
                            inactiveColor = Color.LightGray,
                            indicatorWidth = 20,
                            indicatorHeight = 7.dp,
                            spacing = 3.dp,
                            indicatorCount = 3,
                            ratio = 2
                        )

                        Greeting("Android")
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeRatioIndicatorTheme {
        Greeting("Android")
    }
}
