package com.step.counter.features.home.presentation

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.step.counter.databinding.FragmentHomeBinding
import androidx.core.graphics.toColorInt
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.model.GradientColor
import com.step.counter.core.utils.RoundedBarChartRenderer


class HomeFragment : Fragment() {

    private val grayColor = "#979797".toColorInt()
    private val gradientStart = "#EF3511".toColorInt()
    private val gradientEnd = "#FF7253".toColorInt()

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupBarChart(binding.barChart)
    }

    private fun setupBarChart(barChart: BarChart) {
        val steps = listOf(4000f, 4500f, 3500f, 5600f, 4900f, 3100f, 3700f)
        val gradientIndices = setOf(0, 2, 3, 5) // Indices that should have gradient

        val entries = ArrayList<BarEntry>()
        steps.forEachIndexed { index, value ->
            entries.add(BarEntry(index.toFloat(), value))
        }

        val dataSet = BarDataSet(entries, "")

        // Set colors for each bar
        val colors = ArrayList<Int>()
        steps.indices.forEach { index ->
            if (index in gradientIndices) {
                colors.add(gradientEnd)
            } else {
                colors.add(grayColor)
            }
        }
        dataSet.colors = colors
        dataSet.setDrawValues(false)

        val barData = BarData(dataSet)
        barData.barWidth = 0.5f

        barChart.data = barData
        barChart.setFitBars(true)

        // Rounded bars renderer with gradient support
        barChart.renderer = RoundedBarChartRenderer(
            barChart,
            barChart.animator,
            barChart.viewPortHandler,
            radius = 20f,
            gradientIndices = gradientIndices,
            gradientStart = gradientStart,
            gradientEnd = gradientEnd
        )

        configureXAxis(barChart)
        configureYAxis(barChart)
        styleChart(barChart)

        barChart.invalidate()
    }

    private fun configureXAxis(barChart: BarChart) {
        val days = listOf("Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat")

        barChart.xAxis.apply {
            valueFormatter = IndexAxisValueFormatter(days)
            position = XAxis.XAxisPosition.BOTTOM
            setDrawGridLines(false)
            granularity = 1f
            textColor = Color.DKGRAY
            textSize = 12f
        }
    }

    private fun configureYAxis(barChart: BarChart) {
        barChart.axisLeft.apply {
            axisMinimum = 0f
            axisMaximum = 8000f
            granularity = 1000f
            setDrawGridLines(false)
            textColor = Color.DKGRAY
        }
        barChart.axisRight.isEnabled = false
    }

    private fun styleChart(barChart: BarChart) {
        barChart.apply {
            description.isEnabled = false
            legend.isEnabled = false
            setTouchEnabled(false)
            setScaleEnabled(false)
            setDrawGridBackground(false)
            setExtraOffsets(10f, 10f, 10f, 10f)
            animateY(800)
        }
    }


}