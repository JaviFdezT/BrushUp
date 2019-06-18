package com.jft.brushup;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class GameCheck extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_check);

        Intent intent = getIntent();
        String rightWord = intent.getStringExtra("rightWord");
        String rightMeaning = intent.getStringExtra("rightMeaning");
        String rightExample = intent.getStringExtra("rightExample");
        int rightCategory = intent.getExtras().getInt("rightCategory");
        boolean rightAnswer = intent.getExtras().getBoolean("rightAnswer");
        final String lookForLang = intent.getStringExtra("lookforlang");
        final String lookForType = intent.getStringExtra("lookfortype");
        int newLevel=1;

        TextView textView1 = (TextView) findViewById(R.id.answer);
        TextView textView2 = (TextView) findViewById(R.id.word);
        TextView textView3 = (TextView) findViewById(R.id.mean);
        TextView textView4 = (TextView) findViewById(R.id.example);
        TextView textView5 = (TextView) findViewById(R.id.levelthisword);
        try {
            DdBb myddbb = new DdBb(this);
            if (rightAnswer) {
                textView1.setText("Right answer!");
                if (rightCategory < 10) {
                    newLevel = rightCategory + 1;
                    textView5.setTextColor(Color.parseColor("#4C9900"));
                    myddbb.updateLevel(newLevel, rightWord, rightMeaning);
                } else {
                    textView5.setTextColor(Color.parseColor("#000000"));
                }
            } else {
                textView1.setText("Wrong answer. \n The right answer is:");
                if (rightCategory > 1) {
                    newLevel = rightCategory - 1;
                    textView5.setTextColor(Color.parseColor("#FF3333"));
                    myddbb.updateLevel(newLevel, rightWord, rightMeaning);
                } else {
                    textView5.setTextColor(Color.parseColor("#000000"));
                }
            }
            textView5.setText(Integer.toString(newLevel));
        } catch (Exception e) {}
        textView2.setText(rightWord);
        textView3.setText(rightMeaning);
        textView4.setText(rightExample);



        Button cont_button = (Button)findViewById(R.id.button_cont);
        Button goback_button = (Button)findViewById(R.id.button_mainMenu);
        cont_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent activity1 = new Intent(getApplicationContext(), GameChoose.class);
                activity1.putExtra("lookforlang",lookForLang);
                activity1.putExtra("lookfortype",lookForType);
                startActivity(activity1);
            }
        });
        goback_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent activity2 = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(activity2);
            }
        });

    }
}
