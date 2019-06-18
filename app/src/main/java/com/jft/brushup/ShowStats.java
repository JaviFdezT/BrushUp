package com.jft.brushup;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ShowStats extends AppCompatActivity {

    private LineChart mChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_stats);

        List<Entry> entries = new ArrayList<Entry>();

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
        for (int i = 1; i < 11; i++) {
            TextView textView = (TextView) findViewById(ids.get(i-1));
            String key = new Integer(i).toString();
            try {
                Integer value = stats.get(i);
                Float percent = 100*((float) value)/((float) total);
                textView.setText("Level "+key+": "+value.toString()+"  ("+String.format("%.02f", percent)+" %)");
                entries.add(new Entry(i, value));
            } catch (NullPointerException e){
                textView.setText("Level "+key+": 0  (0 %)");
                entries.add(new Entry(i, 0));
            }
        }
        TextView textView = (TextView) findViewById(R.id.totalNumber);
        textView.setText("TOTAL: "+total.toString());

        Button goback_button = (Button)findViewById(R.id.button_mainMenu_fromStat);
        goback_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent activity2 = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(activity2);
            }
        });


        mChart = findViewById(R.id.chart);
        mChart.setTouchEnabled(true);
        mChart.setPinchZoom(true);
        LineDataSet dataSet = new LineDataSet(entries, "Number of words per level"); // add entries to dataset
        dataSet.setLineWidth(5);
        dataSet.setValueTextSize(15);
        LineData lineData = new LineData(dataSet);
        mChart.setData(lineData);
        mChart.getDescription().setEnabled(false);
        mChart.getLegend().setEnabled(false);
        mChart.invalidate(); // refresh


    }
}
