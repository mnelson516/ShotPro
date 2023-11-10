package com.example.composetest.presentation.insights

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import co.yml.charts.axis.AxisData
import co.yml.charts.common.extensions.formatToSinglePrecision
import co.yml.charts.common.model.Point
import co.yml.charts.ui.linechart.LineChart
import co.yml.charts.ui.linechart.model.GridLines
import co.yml.charts.ui.linechart.model.IntersectionPoint
import co.yml.charts.ui.linechart.model.Line
import co.yml.charts.ui.linechart.model.LineChartData
import co.yml.charts.ui.linechart.model.LinePlotData
import co.yml.charts.ui.linechart.model.LineStyle
import co.yml.charts.ui.linechart.model.SelectionHighlightPoint
import co.yml.charts.ui.linechart.model.SelectionHighlightPopUp
import co.yml.charts.ui.linechart.model.ShadowUnderLine
import com.example.composetest.R
import com.example.composetest.presentation.addworkout.AddWorkoutViewModel
import com.example.composetest.presentation.history.HistoryViewModel
import com.example.composetest.presentation.model.FieldGoalData
import com.example.composetest.presentation.theme.NavyBlue
import com.example.composetest.presentation.theme.NeonOrange
import com.example.composetest.presentation.theme.Typography
import com.example.composetest.presentation.theme.WhiteBackground
import com.example.composetest.presentation.util.DateFormatter
import com.example.composetest.presentation.util.PercentageConverter
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import kotlin.math.roundToInt

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GraphScreen(
    navController: NavController,
    historyViewModel: HistoryViewModel,
    insightsViewModel: InsightsViewModel
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Details",
                        style = Typography.h1
                    )
                },
                colors = TopAppBarColors(
                    containerColor = NavyBlue,
                    scrolledContainerColor = NavyBlue,
                    actionIconContentColor = NavyBlue,
                    navigationIconContentColor = NavyBlue,
                    titleContentColor = NavyBlue
                ),
                navigationIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_back_arrow),
                        contentDescription = "Back Arrow",
                        tint = Color.White,
                        modifier = Modifier
                            .padding(start = 8.dp)
                            .clickable {
                                navController.navigateUp()
                            }
                    )
                }
            )
        }
    ) {
        Column(
            modifier = Modifier
                .background(Color.White)
                .padding(it)
                .fillMaxSize()
        ) {
            val state = historyViewModel.state.collectAsStateWithLifecycle()
            val insightState = insightsViewModel.state.collectAsState()
            var increment = 0f
            val map = hashMapOf<Int, String>()
            val list = state.value.exercises
                .sortedBy {
                    exercise -> exercise.date
                }
                .groupBy { exercise ->
                map[increment.toInt()] = exercise.date.format(DateTimeFormatter.ISO_DATE)
                increment++
            }.toSortedMap()

            val pointList = mutableListOf<Point>()

            for (exercise in list) {
                pointList.add(Point(exercise.key, PercentageConverter.getPointAverage(exercise.value)))
            }

            Text(
                text = state.value.detailsText,
                style = Typography.h2,
                color = Color.Black,
                fontSize = 20.sp,
                modifier = Modifier.padding(start = 12.dp, top = 24.dp, bottom = 12.dp)
            )

            Text(
                text = insightState.value.data?.let { it1 ->
                    val pair = getFieldGoalDataByCategory(state.value.detailsText,
                        it1
                    )
                    pair.first.toString() + "/" + pair.second.toString()
                } ?: run {""},
                style = Typography.h3,
                color = Color.Black,
                fontSize = 18.sp,
                modifier = Modifier.padding(start = 12.dp, top = 24.dp, bottom = 12.dp)
            )

            if (pointList.isNotEmpty()) {
                Column {
                    SingleLineChartWithGridLines(pointList, map)
                }
            }
        }
    }
}

@Composable
fun SingleLineChartWithGridLines(pointsData: List<Point>, pointsMap: HashMap<Int, String>) {
    val steps = 5
    val xAxisData = AxisData.Builder()
        .labelData {
            if (it == 1) {"Date"}
            else ""
        }
        .axisLabelFontSize(20.sp)
        .axisStepSize(30.dp)
        .topPadding(105.dp)
        .steps(pointsData.size - 1)
        .labelAndAxisLinePadding(15.dp)
        .build()
    val yAxisData = AxisData.Builder()
        .steps(steps)
        .labelAndAxisLinePadding(20.dp)
        .labelData { i ->
            val yMin = pointsData.minOf { it.y }
            val yMax = pointsData.maxOf { it.y }
            val yScale = (yMax - yMin) / steps
            ((i * yScale) + yMin).roundToInt().toString() + "%"
        }.build()
    val data = LineChartData(
        linePlotData = LinePlotData(
            lines = listOf(
                Line(
                    dataPoints = pointsData,
                    LineStyle(),
                    IntersectionPoint(),
                    SelectionHighlightPoint(
                        color = NeonOrange
                    ),
                    ShadowUnderLine(),
                    SelectionHighlightPopUp(
                        popUpLabel = { x, y ->
                            pointsMap[x.toInt()]!! + ": " + y.toInt() + "%"
                        },
                        backgroundColor = Color.White,
                        backgroundAlpha = 1.0f
                    )
                )
            )
        ),
        xAxisData = xAxisData,
        yAxisData = yAxisData,
        gridLines = GridLines()
    )
    LineChart(
        modifier = Modifier
            .fillMaxWidth()
            .height(350.dp),
        lineChartData = data
    )
}

fun getFieldGoalDataByCategory(category: String, fieldGoalData: FieldGoalData): Pair<Int, Int> {
    when (category) {
        "" -> {
            return(Pair(fieldGoalData.totalFieldGoalsMade, fieldGoalData.totalFieldGoals))
        }
        "Left Side" -> {
            return(Pair(fieldGoalData.leftSideFieldGoalsMade, fieldGoalData.leftSideFieldGoals))
        }
        "Right Side" -> {
            return(Pair(fieldGoalData.rightSideFieldGoalsMade, fieldGoalData.rightSideFieldGoals))
        }
        "Close Range" -> {
            return(Pair(fieldGoalData.closeRangeFieldGoalsMade, fieldGoalData.closeRangeFieldGoals))
        }
        "Mid Range" -> {
            return(Pair(fieldGoalData.midRangeFieldGoalsMade, fieldGoalData.midRangeFieldGoals))
        }
        "Three Pointer" -> {
            return(Pair(fieldGoalData.threePointFieldGoalsMade, fieldGoalData.threePointFieldGoals))
        }
        "Baseline" -> {
            return(Pair(fieldGoalData.baseLineFieldGoalsMade, fieldGoalData.baseLineFieldGoals))
        }
        "Diagonal" -> {
            return(Pair(fieldGoalData.diagonalFieldGoalsMade, fieldGoalData.diagonalFieldGoals))
        }
        "Elbow" -> {
            return(Pair(fieldGoalData.elbowFieldGoalsMade, fieldGoalData.elbowFieldGoals))
        }
        "Center" -> {
            return(Pair(fieldGoalData.centerFieldGoalsMade, fieldGoalData.centerFieldGoals))
        } else -> {
        return(Pair(fieldGoalData.totalFieldGoalsMade, fieldGoalData.totalFieldGoals))
        }
    }
}