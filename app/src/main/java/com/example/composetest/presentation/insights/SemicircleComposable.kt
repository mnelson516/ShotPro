package com.example.composetest.presentation.insights

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composetest.presentation.theme.NeonOrange
import com.example.composetest.presentation.theme.RedColor
import com.example.composetest.presentation.theme.Typography
import com.example.composetest.presentation.util.PercentageConverter

@Composable
@Preview
fun SemiCirclePreview() {
    FieldGoalGauge(percentage = 50f)
    //SemicircleView(text = "Field Goals", totalShots = 20, shotsMade = 10, false)
}

@Composable
fun SemicircleView(
    text: String,
    totalShots: Int,
    shotsMade: Int,
    isThreePointer: Boolean
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .background(Color.White)
    ) {
        SemiCircleArcs(
            percentage = PercentageConverter.convertToPercentage(shotsMade, totalShots).toFloat(),
            isThreePointer
        )
        Text(
            text = text,
            style = Typography.h4,
            color = Color.Black,
            modifier = Modifier
                .offset(x = 0.dp, y = (-20).dp)
        )
    }
}

@Composable
fun SemiCircleArcs(percentage: Float, isThreePointer: Boolean) {
    Box(
        contentAlignment = Alignment.Center
    ) {
        Canvas(
            modifier = Modifier
                .size(175.dp)
                .clipToBounds()
                .padding(14.dp)
        ) {
            drawArc(
                brush = SolidColor(Color.LightGray),
                size = Size(size.width, size.height),
                startAngle = 180f,
                sweepAngle = 180f,
                topLeft = Offset(0f, size.width.times(0.25f)),
                useCenter = false,
                style = Stroke(35f, cap = StrokeCap.Round)
            )
            drawArc(
                brush = getBrushColor(percentage, isThreePointer),
                size = Size(size.width, size.height),
                startAngle = 180f,
                sweepAngle = 180 * (percentage / 100),
                topLeft = Offset(0f, size.width.times(0.25f)),
                useCenter = false,
                style = Stroke(35f, cap = StrokeCap.Round)
            )
        }
        Row {
            Text(
                text = percentage.toInt().toString(),
                style = Typography.h4,
                color = Color.Black,
                fontSize = 26.sp,
                modifier = Modifier.offset(x = 0.dp, y = (12).dp)
            )
            Text(
                text = "%",
                style = Typography.h4,
                color = Color.Black,
                fontSize = 12.sp,
                modifier = Modifier.offset(x = 0.dp, y = (12).dp)
            )
        }

    }
}

fun getBrushColor(percentage: Float, isThreePointer: Boolean): Brush {
    if (isThreePointer) {
        return if (percentage < 30) {
            SolidColor(RedColor)
        } else if (percentage >= 30 && percentage < 35) {
            SolidColor(Color.Yellow)
        } else {
            SolidColor(Color.Green)
        }
    } else {
        return if (percentage < 40) {
            SolidColor(RedColor)
        } else if (percentage >= 40 && percentage < 50) {
            SolidColor(Color.Yellow)
        } else {
            SolidColor(Color.Green)
        }
    }
}

@Composable
fun FieldGoalGauge(percentage: Float) {
    Box(
        contentAlignment = Alignment.Center
    ) {
        Canvas(
            modifier = Modifier
                .size(250.dp)
                .clipToBounds()
                .padding(14.dp)
        ) {
            drawArc(
                brush = SolidColor(Color.LightGray),
                size = Size(size.width, size.height),
                startAngle = 150f,
                sweepAngle = 240f,
                useCenter = false,
                style = Stroke(40f, cap = StrokeCap.Round)
            )
            drawArc(
                brush = getBrushColor(percentage, false),
                size = Size(size.width, size.height),
                startAngle = 150f,
                sweepAngle = 240 * (percentage / 100),
                useCenter = false,
                style = Stroke(40f, cap = StrokeCap.Round)
            )
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.offset(y = (-12).dp)
        ) {
            Text(
                text = "Field Goals",
                color = Color.White,
                fontSize = 14.sp,
            )
            Row(
                modifier = Modifier
                    .padding(top = 4.dp)
                    .offset(x = 6.dp)
            ) {
                Text(
                    text = percentage.toInt().toString(),
                    style = Typography.h4,
                    color = Color.White,
                    fontSize = 40.sp,
                )
                Text(
                    text = "%",
                    style = Typography.h4,
                    color = Color.White,
                    fontSize = 14.sp,
                )
            }
        }
    }
}
