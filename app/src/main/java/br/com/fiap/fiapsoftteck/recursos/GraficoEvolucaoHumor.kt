package br.com.fiap.fiapsoftteck.recursos

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import br.com.fiap.fiapsoftteck.model.DiarioHumor
import android.graphics.Color
import androidx.compose.foundation.layout.*

import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.github.mikephil.charting.charts.LineChart

import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet


@Composable
fun GraficoEvolucaoHumor(diario: List<DiarioHumor>) {
    val context = LocalContext.current

    AndroidView(factory = {
        val chart = LineChart(context)

        val entries = diario.mapIndexed { index, registro ->
            Entry(index.toFloat(), registro.humor.toFloat())
        }

        val dataSet = LineDataSet(entries, "Evolução do Humor").apply {
            color = Color.BLUE.hashCode()
            valueTextColor = Color.BLACK.hashCode()
            lineWidth = 2f
            circleRadius = 4f
            setDrawValues(false)
            setCircleColor(Color.BLUE.hashCode())
        }

        chart.data = LineData(dataSet)
        chart.description.isEnabled = false
        chart.xAxis.isEnabled = false
        chart.axisRight.isEnabled = false
        chart.axisLeft.axisMinimum = 1f
        chart.axisLeft.axisMaximum = 5f
        chart.invalidate()

        chart
    }, modifier = Modifier
        .fillMaxWidth()
        .height(250.dp)
        .padding(top = 16.dp))
}
