package com.jft.brushup;


import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GameChoose extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_choose);

        Intent intent = getIntent();
        String lookForLang = intent.getStringExtra("lookforlang");
        String lookForType = intent.getStringExtra("lookfortype");

        List<Word> list = null;

            DdBb myddbb = new DdBb(this);
            if (lookForType.equals("any")) {
                list = myddbb.getAllWords();
            } else if (lookForType.equals("v")) {
                list = myddbb.getAllWordsByType("v");
            } else if (lookForType.equals("s")) {
                list = myddbb.getAllWordsByType("s");
            } else if (lookForType.equals("adj")) {
                list = myddbb.getAllWordsByType("adj");
            }
        int n = list.size();

        ArrayList<Integer> listrRandom = new ArrayList<Integer>();
        Word questionWord=null;
        Word falseWord1=null;
        Word falseWord2=null;
        Word falseWord3=null;
        Word falseWord4=null;
        Word falseWord5=null;
        try {
            for (int i=0; i<n; i++) {
                listrRandom.add(new Integer(i));
            }
            Collections.shuffle(listrRandom);
            questionWord = list.get(listrRandom.get(0));
            falseWord1 = list.get(listrRandom.get(1));
            falseWord2 = list.get(listrRandom.get(2));
            falseWord3 = list.get(listrRandom.get(3));
            falseWord4 = list.get(listrRandom.get(4));
            falseWord5 = list.get(listrRandom.get(5));

            ArrayList<Word> questionList = new ArrayList<Word>();
            questionList.add(falseWord1);
            questionList.add(falseWord2);
            questionList.add(falseWord3);
            questionList.add(falseWord4);
            questionList.add(falseWord5);
            questionList.add(questionWord);
            Collections.shuffle(questionList);

            TextView textView = (TextView) findViewById(R.id.question);
            Button p1_button = (Button)findViewById(R.id.buttoncheck1);
            Button p2_button = (Button)findViewById(R.id.buttoncheck2);
            Button p3_button = (Button)findViewById(R.id.buttoncheck3);
            Button p4_button = (Button)findViewById(R.id.buttoncheck4);
            Button p5_button = (Button)findViewById(R.id.buttoncheck5);
            Button p6_button = (Button)findViewById(R.id.buttoncheck6);
            if (lookForLang.equals("Word")){
                textView.setText(questionWord.getMeaning());
                p1_button.setText(questionList.get(0).getWord());
                p2_button.setText(questionList.get(1).getWord());
                p3_button.setText(questionList.get(2).getWord());
                p4_button.setText(questionList.get(3).getWord());
                p5_button.setText(questionList.get(4).getWord());
                p6_button.setText(questionList.get(5).getWord());
            } else {
                textView.setText(questionWord.getWord());
                p1_button.setText(questionList.get(0).getMeaning());
                p2_button.setText(questionList.get(1).getMeaning());
                p3_button.setText(questionList.get(2).getMeaning());
                p4_button.setText(questionList.get(3).getMeaning());
                p5_button.setText(questionList.get(4).getMeaning());
                p6_button.setText(questionList.get(5).getMeaning());
            }

            final Intent activity = new Intent(getApplicationContext(), GameCheck.class);
            activity.putExtra("rightWord",questionWord.getWord());
            activity.putExtra("rightMeaning",questionWord.getMeaning());
            activity.putExtra("rightExample",questionWord.getExample());
            activity.putExtra("rightCategory",questionWord.getCategory());
            activity.putExtra("lookforlang",lookForLang);
            activity.putExtra("lookfortype",lookForType);
            final int indexInList=questionList.indexOf(questionWord);
            boolean rightAnswer;
            p1_button.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    if (0==indexInList) {
                        activity.putExtra("rightAnswer",true);
                    } else {
                        activity.putExtra("rightAnswer",false);
                    }
                    startActivity(activity);
                    finish();
                }
            });
            p2_button.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    if (1==indexInList) {
                        activity.putExtra("rightAnswer",true);
                    } else {
                        activity.putExtra("rightAnswer",false);
                    }
                    startActivity(activity);
                    finish();
                }
            });
            p3_button.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    if (2==indexInList) {
                        activity.putExtra("rightAnswer",true);
                    } else {
                        activity.putExtra("rightAnswer",false);
                    }
                    startActivity(activity);
                    finish();
                }
            });
            p4_button.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    if (3==indexInList) {
                        activity.putExtra("rightAnswer",true);
                    } else {
                        activity.putExtra("rightAnswer",false);
                    }
                    startActivity(activity);
                    finish();
                }
            });
            p5_button.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    if (4==indexInList) {
                        activity.putExtra("rightAnswer",true);
                    } else {
                        activity.putExtra("rightAnswer",false);
                    }
                    startActivity(activity);
                    finish();
                }
            });
            p6_button.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    if (5==indexInList) {
                        activity.putExtra("rightAnswer",true);
                    } else {
                        activity.putExtra("rightAnswer",false);
                    }
                    startActivity(activity);
                    finish();
                }
            });
        } catch (Exception e){
            Toast.makeText(this, "Too few words in the dictionary. Please, add more", 20).show();
            Intent activity = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(activity);
        }

    }
}
