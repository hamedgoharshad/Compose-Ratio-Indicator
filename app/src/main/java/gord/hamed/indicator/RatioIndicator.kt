package gord.hamed.indicator

import androidx.compose.animation.core.animateIntAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ContentAlpha
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.LocalContentColor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import kotlin.math.pow

@OptIn(ExperimentalPagerApi::class)
@Composable
fun RatioIndicator(
    pagerState: PagerState,
    modifier: Modifier = Modifier,
    activeColor: Color = LocalContentColor.current.copy(alpha = LocalContentAlpha.current),
    inactiveColor: Color = activeColor.copy(ContentAlpha.disabled),
    indicatorWidth: Int = 8,
    indicatorHeight: Dp,
    spacing: Dp,
    indicatorShape: Shape = CircleShape,
    indicatorCount: Int,
    ratio: Int
) {
    val spacingPx = LocalDensity.current.run { spacing.roundToPx() }

    Box(
        modifier = modifier,
        contentAlignment = Alignment.CenterStart
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(spacing),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            val indicatorModifier = Modifier
                .background(color = inactiveColor, shape = indicatorShape)

            repeat(indicatorCount) {
                Box(
                    indicatorModifier.size(
                        (indicatorWidth * (2.toDouble().pow(it))).dp,
                        indicatorHeight
                    )
                )
            }
        }
        val scrollPosition = (pagerState.currentPage + pagerState.currentPageOffset)
            .coerceIn(
                0f,
                (pagerState.pageCount - 1)
                    .coerceAtLeast(0)
                    .toFloat()
            )
        val width = pagerState.run {
            indicatorWidth * (ratio.toDouble()
                .pow(currentPage)) * (1 + currentPageOffset / if (targetPage > currentPage) 1 else ratio)
        }
        val indicatorWidthPx by animateIntAsState(
            LocalDensity.current.run { indicatorWidth.dp.roundToPx() }
        )
        Box(
            Modifier
                .offset {
                    IntOffset(
                        x = (spacingPx * scrollPosition + indicatorWidthPx * (ratio
                            .toFloat()
                            .pow(scrollPosition) - 1)).toInt(),
                        y = 0
                    )
                }
                .size(width = width.dp, height = indicatorHeight)
                .background(
                    color = activeColor,
                    shape = indicatorShape,
                )
        )
    }
}

