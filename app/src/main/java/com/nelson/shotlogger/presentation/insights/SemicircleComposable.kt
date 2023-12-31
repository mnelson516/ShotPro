package com.nelson.shotlogger.presentation.insights

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.nelson.shotlogger.presentation.theme.NavyBlue
import com.nelson.shotlogger.presentation.theme.RedColor
import com.nelson.shotlogger.presentation.theme.WhiteBackground
import com.nelson.shotlogger.presentation.util.PercentageConverter
import com.nelson.shotlogger.R
import com.nelson.shotlogger.presentation.theme.Typography

@Composable
@Preview
fun SemiCirclePreview() {
    SemicircleView("DUMMY",shotsMade = 10, totalShots = 20, isThreePointer = false, onClick = {})
}

@Composable
fun SemicircleView(
    text: String,
    totalShots: Int,
    shotsMade: Int,
    isThreePointer: Boolean,
    onClick: () -> Unit
) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .border(1.dp, NavyBlue, RoundedCornerShape(10.dp))
            .background(Color.White)
            .clickable { onClick() }
    ) {
        val (gauge, textview, arrow) = createRefs()
        SemiCircleArcs(
            percentage = PercentageConverter.convertToPercentage(shotsMade, totalShots).toFloat(),
            isThreePointer,
            modifier = Modifier.constrainAs(gauge) {
                bottom.linkTo(parent.bottom)
                top.linkTo(parent.top)
                end.linkTo(parent.end)
                start.linkTo(parent.start)
            }
        )
        Text(
            text = text,
            style = Typography.h1,
            fontSize = 24.sp,
            color = Color.Black,
            modifier = Modifier
                .offset(y = (-20).dp)
                .constrainAs(textview) {
                    linkTo(
                        start = parent.start,
                        end = parent.end
                    )
                    linkTo(
                        top = gauge.bottom,
                        bottom = parent.bottom
                    )
                }
        )
        Image(
            painter = painterResource(id = R.drawable.forward_arrow),
            contentDescription = "Forward Arrow",
            modifier = Modifier
                .size(30.dp)
                .constrainAs(arrow) {
                    top.linkTo(parent.top)
                    end.linkTo(parent.end, margin = 12.dp)
                    bottom.linkTo(parent.bottom)
                }
            )
    }
}

@Composable
fun SemiCircleArcs(percentage: Float, isThreePointer: Boolean, modifier: Modifier) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
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
                style = Typography.h2,
                color = Color.Black,
                fontSize = 26.sp,
                modifier = Modifier.offset(x = 0.dp, y = (12).dp)
            )
            Text(
                text = "%",
                style = Typography.h2,
                color = Color.Black,
                fontSize = 12.sp,
                modifier = Modifier.offset(x = 0.dp, y = (12).dp)
            )
        }

    }
}

@Composable
fun DetailsArc(shotsMade: Int, totalShots: Int, isThreePointer: Boolean) {
    val percentage = PercentageConverter.convertToPercentage(shotsMade, totalShots).toFloat()
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .border(1.dp, NavyBlue, RoundedCornerShape(10.dp))
            .background(Color.White)
    ) {
        val (gauge, text) = createRefs()
        Text(
            text = "Results:",
            style = Typography.h2,
            color = Color.Black,
            fontSize = 26.sp,
            modifier = Modifier.constrainAs(text) {
                top.linkTo(parent.top, margin = 16.dp)
                end.linkTo(parent.end)
                start.linkTo(parent.start)
            }
        )
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .constrainAs(gauge) {
                    bottom.linkTo(parent.bottom)
                    top.linkTo(parent.top, margin = 16.dp)
                    end.linkTo(parent.end)
                    start.linkTo(parent.start)
                }
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

            Text(
                text = "$shotsMade/$totalShots",
                style = Typography.h2,
                color = Color.Black,
                fontSize = 26.sp,
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
fun FieldGoalGauge(percentage: Float, text: String) {
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
                style = Stroke(45f, cap = StrokeCap.Round)
            )
            drawArc(
                brush = getBrushColor(percentage, false),
                size = Size(size.width, size.height),
                startAngle = 150f,
                sweepAngle = 240 * (percentage / 100),
                useCenter = false,
                style = Stroke(45f, cap = StrokeCap.Round)
            )
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.offset(y = (-16).dp)
        ) {
            Text(
                text = text,
                color = WhiteBackground,
                style = Typography.h4,
                fontSize = 14.sp,
                modifier = Modifier.offset(y = 10.dp)
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .offset(x = 6.dp)
            ) {
                Text(
                    text = percentage.toInt().toString(),
                    style = Typography.h2,
                    color = Color.White,
                    fontSize = 60.sp,
                )
                Text(
                    text = "%",
                    style = Typography.h2,
                    color = Color.White,
                    fontSize = 14.sp,
                )
            }
        }
    }
}
