package com.jft.brushup;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ShowStats extends AppCompatActivity {

    private HorizontalBarChart horizontalChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_stats);

        List<BarEntry> entries = new ArrayList<BarEntry>();
        List<String> levelNames= Arrays.asList("0","1","2","3","4","5","6","7","8","9","10");

        Integer total = 0;
        Map<Integer, Integer> stats = null;
        try{
            DdBb myddbb = new DdBb(this);
            stats=myddbb.getStats();
            total = new Integer(stats.get(11));
        } catch (Exception e) {}

        ArrayList<Integer> ids = new ArrayList<>();
        ids.add(R.id.level1);
        ids.add(R.id.level2);
        ids.add(R.id.level3);
        ids.add(R.id.level4);
        ids.add(R.id.level5);
        ids.add(R.id.level6);
        ids.add(R.id.level7);
        ids.add(R.id.level8);
        ids.add(R.id.level9);
        ids.add(R.id.level10);
        Float maxPercent = 1f;
        for (int i = 1; i < 11; i++) {
            TextView textView = (TextView) findViewById(ids.get(i-1));
            String key = new Integer(i).toString();
            try {
                Integer value = stats.get(i);
                Float percent = 100*((float) value)/((float) total);
                textView.setText("Level "+key+": "+value.toString()+"  ("+String.format("%.02f", percent)+" %)");
                entries.add(new BarEntry(i, percent));
                if (percent>maxPercent) {
                    maxPercent=percent+1f;
                    if (maxPercent>100) {
                        maxPercent=100f;
                    }
                }
            } catch (NullPointerException e){
                textView.setText("Level "+key+": 0  (0 %)");
                entries.add(new BarEntry(i,0f));
            }
        }
        System.out.println(levelNames);
        TextView textView = (TextView) findViewById(R.id.totalNumber);
        textView.setText("TOTAL: "+total.toString());

        Button goback_button = (Button)findViewById(R.id.button_mainMenu_fromStat);
        goback_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent activity2 = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(activity2);
            }
        });

        horizontalChart = (HorizontalBarChart)findViewById(R.id.chartH);
        BarDataSet barDataSet = new BarDataSet(entries, "ser");
        barDataSet.setBarBorderWidth(0.9f);
        barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        BarData barData = new BarData(barDataSet);
        XAxis xAxis = horizontalChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        IndexAxisValueFormatter formatter = new IndexAxisValueFormatter(levelNames);
        xAxis.setGranularity(1f);
        xAxis.setValueFormatter(formatter);

        YAxis yAxisl = horizontalChart.getAxisLeft();
        yAxisl.setAxisMinimum(0f);
        yAxisl.setAxisMaximum(maxPercent);
        YAxis yAxisr = horizontalChart.getAxisRight();
        yAxisr.setAxisMinimum(0f);
        yAxisr.setAxisMaximum(maxPercent);

        horizontalChart.getLegend().setEnabled(false);
        horizontalChart.getDescription().setEnabled(false);
        horizontalChart.setTouchEnabled(false);
        horizontalChart.setExtraRightOffset(30f);
        horizontalChart.setData(barData);
        horizontalChart.getXAxis().setLabelCount(10);
        horizontalChart.setFitBars(true);
        horizontalChart.animateXY(5000, 5000);
        horizontalChart.invalidate();
    }
}
