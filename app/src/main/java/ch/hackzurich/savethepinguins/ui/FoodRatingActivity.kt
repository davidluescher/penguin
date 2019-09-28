package com.anychart.sample.charts

import android.os.Bundle
import android.view.View

import androidx.appcompat.app.AppCompatActivity
import ch.hackzurich.savethepinguins.ui.ImpactActivity

import com.anychart.AnyChart
import com.anychart.chart.common.dataentry.SingleValueDataSet
import kotlinx.android.synthetic.main.activity_food_rating.*
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import ch.hackzurich.savethepinguins.R
import android.view.ViewTreeObserver
import ch.hackzurich.savethepinguins.ui.GradientHoleDrawer



class FoodRatingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_food_rating)

        hv.setOnClickListener { object: View.OnClickListener {
            override fun onClick(v: View?) {
                hv.setDrawer(null)
                hv.setVisibility(View.GONE)
            }
        } }


        val mBackgroundColor = 0x80000000

        ll_container.viewTreeObserver.addOnPreDrawListener(object :
            ViewTreeObserver.OnPreDrawListener {
            override fun onPreDraw(): Boolean {
                ll_container.viewTreeObserver.removeOnPreDrawListener(this)

                var width = 0
                var height = 0
                var i = 0
                val count = ll_container.childCount
                while (i < count) {
                    val child = ll_container.getChildAt(i)
                    width = Math.max(width, child.width)
                    height += child.height
                    i++
                }

                var mHoleRadius =
                    (Math.sqrt((width * width + height * height).toDouble()) / 2 + 16).toInt()

                return true
            }
        })

        val score = intent.getIntExtra(ImpactActivity.SCORE, 0)

        any_chart_view.setProgressBar(progress_bar)

        val circularGauge = AnyChart.circular()
        circularGauge.fill("white")
            .stroke(null)
            .padding(0,0,0,0)
            .margin(30,30,30,30)
        circularGauge.startAngle(0)
            .sweepAngle(360)

        /* TODO: Set value of gauge needle */
        val currentValue = 13.8
        circularGauge.data(SingleValueDataSet(arrayOf(currentValue)))

        circularGauge.axis(0)
            .startAngle(-150)
            .radius(80)
            .sweepAngle(300)
            .width(3)
        /*.ticks("{ type: 'line', length: 4, position: 'outside' }")*/

        /*circularGauge.axis(0).labels().position("outside")*/

        /* TODO: Set min and max values for CO2/g" */
        circularGauge.axis(0).scale()
            .minimum(0)
            .maximum(100)

        circularGauge.needle(0)
            .stroke(null)
            .startRadius("6%")
            .endRadius("38%")
            .startWidth("2%")
            .endWidth(0)

        /* Set the cap for the needle */
        circularGauge.cap()
            .radius("4%")
            .enabled(true)
            .stroke(null)


        /* First section of the gauge chart in green */
        circularGauge.range(
            0,
            "{\n" +
                    "    from: 0,\n" +
                    "    to: 10,\n" +
                    "    position: 'inside',\n" +
                    "    fill: '#045D56',\n" +
                    "    stroke: '1 #000',\n" +
                    "    startSize: 6,\n" +
                    "    endSize: 6,\n" +
                    "    radius: 80,\n" +
                    "    zIndex: 1\n" +
                    "  }"
        )

        /* Second section of the gauge chart */
        circularGauge.range(
            1,
            "{\n" +
                    "    from: 10,\n" +
                    "    to: 20,\n" +
                    "    position: 'inside',\n" +
                    "    fill: '#1EB980',\n" +
                    "    stroke: '1 #000',\n" +
                    "    startSize: 6,\n" +
                    "    endSize: 6,\n" +
                    "    radius: 80,\n" +
                    "    zIndex: 1\n" +
                    "  }"
        )

        /* Third section of the gauge chart */
        circularGauge.range(
            2,
            "{\n" +
                    "    from: 20,\n" +
                    "    to: 30,\n" +
                    "    position: 'inside',\n" +
                    "    fill: '#AED581',\n" +
                    "    stroke: '1 #000',\n" +
                    "    startSize: 6,\n" +
                    "    endSize: 6,\n" +
                    "    radius: 80,\n" +
                    "    zIndex: 1\n" +
                    "  }"
        )

        /* Fourth section of the gauge chart */
        circularGauge.range(
            3,
            "{\n" +
                    "    from: 30,\n" +
                    "    to: 40,\n" +
                    "    position: 'inside',\n" +
                    "    fill: '#C0CA33',\n" +
                    "    stroke: '1 #000',\n" +
                    "    startSize: 6,\n" +
                    "    endSize: 6,\n" +
                    "    radius: 80,\n" +
                    "    zIndex: 1\n" +
                    "  }"
        )

        /* Fifth section of the gauge chart */
        circularGauge.range(
            4,
            "{\n" +
                    "    from: 40,\n" +
                    "    to: 50,\n" +
                    "    position: 'inside',\n" +
                    "    fill: '#FFEB3B',\n" +
                    "    stroke: '1 #000',\n" +
                    "    startSize: 6,\n" +
                    "    endSize: 6,\n" +
                    "    radius: 80,\n" +
                    "    zIndex: 1\n" +
                    "  }"
        )

        /* Sixth section of the gauge chart */
        circularGauge.range(
            5,
            "{\n" +
                    "    from: 50,\n" +
                    "    to: 60,\n" +
                    "    position: 'inside',\n" +
                    "    fill: '#FFC107',\n" +
                    "    stroke: '1 #000',\n" +
                    "    startSize: 6,\n" +
                    "    endSize: 6,\n" +
                    "    radius: 80,\n" +
                    "    zIndex: 1\n" +
                    "  }"
        )

        /* Seventh section of the gauge chart */
        circularGauge.range(
            6,
            "{\n" +
                    "    from: 60,\n" +
                    "    to: 70,\n" +
                    "    position: 'inside',\n" +
                    "    fill: '#FFA000',\n" +
                    "    stroke: '1 #000',\n" +
                    "    startSize: 6,\n" +
                    "    endSize: 6,\n" +
                    "    radius: 80,\n" +
                    "    zIndex: 1\n" +
                    "  }"
        )

        /* Eighth section of the gauge chart */
        circularGauge.range(
            7,
            "{\n" +
                    "    from: 70,\n" +
                    "    to: 80,\n" +
                    "    position: 'inside',\n" +
                    "    fill: '#EF6C00',\n" +
                    "    stroke: '1 #000',\n" +
                    "    startSize: 6,\n" +
                    "    endSize: 6,\n" +
                    "    radius: 80,\n" +
                    "    zIndex: 1\n" +
                    "  }"
        )

        /* Ninth section of the gauge chart */
        circularGauge.range(
            8,
            "{\n" +
                    "    from: 80,\n" +
                    "    to: 90,\n" +
                    "    position: 'inside',\n" +
                    "    fill: '#FF8A65',\n" +
                    "    stroke: '1 #000',\n" +
                    "    startSize: 6,\n" +
                    "    endSize: 6,\n" +
                    "    radius: 80,\n" +
                    "    zIndex: 1\n" +
                    "  }"
        )

        /* Tenth section of the gauge chart */
        circularGauge.range(
            9,
            ("{\n" +
                    "    from: 80,\n" +
                    "    to: 100,\n" +
                    "    position: 'inside',\n" +
                    "    fill: '#BF360C',\n" +
                    "    stroke: '1 #000',\n" +
                    "    startSize: 6,\n" +
                    "    endSize: 6,\n" +
                    "    radius: 80,\n" +
                    "    zIndex: 1\n" +
                    "  }")
        )

        any_chart_view.setChart(circularGauge)
    }
}