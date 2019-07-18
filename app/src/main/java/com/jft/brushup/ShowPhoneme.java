package com.jft.brushup;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.LinkedList;
import java.util.List;

public class ShowPhoneme extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_phoneme);

        Intent intent = getIntent();
        final String showPhoneme = intent.getStringExtra("showPhoneme");

        Phoneme phoneme = null;
        final DdBb myddbb = new DdBb(this);
        try {
            phoneme = myddbb.getPhoneme(showPhoneme);
            TextView textView1 = (TextView) findViewById(R.id.thisphoneme);
            TextView textView2 = (TextView) findViewById(R.id.thisnote);
            TextView textView3 = (TextView) findViewById(R.id.thisuse);
            TextView textView4 = (TextView) findViewById(R.id.thisexample);
            textView1.setText(phoneme.getPhoneme());
            textView2.setText(phoneme.getNote());
            textView3.setText(phoneme.getUses());
            textView4.setText(phoneme.getExample());
        } catch (NullPointerException e){
            final Intent activity1 = new Intent(getApplicationContext(), GrammarList.class);
            startActivity(activity1);
        }


        Button goback_button = (Button)findViewById(R.id.button_mainMenu_fromphonshow);
        goback_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent activity2 = new Intent(getApplicationContext(), PronunciationList.class);
                startActivity(activity2);
            }
        });
    }
}
