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
import android.content.DialogInterface;
import android.content.DialogInterface;


public class ShowWord extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_word);

        Intent intent = getIntent();
        final String showWord = intent.getStringExtra("showWord");
        final String showMeaning = intent.getStringExtra("showMeaning");

        List<Word> words = new LinkedList<Word>();
        final DdBb myddbb = new DdBb(this);
        Word word;
        try {
            words = myddbb.getAllWords(showWord,showMeaning);
            word = words.get(0);
            TextView textView1 = (TextView) findViewById(R.id.word);
            TextView textView2 = (TextView) findViewById(R.id.mean);
            TextView textView3 = (TextView) findViewById(R.id.example);
            TextView textView4 = (TextView) findViewById(R.id.levelthisword);
            textView1.setText(word.getWord());
            textView2.setText(word.getMeaning());
            textView3.setText(word.getExample());
            textView4.setText("Level "+(new Integer(word.getCategory()).toString()));
        } catch (Exception e){
            Intent activity = new Intent(getApplicationContext(), ShowDict.class);
            startActivity(activity);
        }

        Button goback_button = (Button)findViewById(R.id.button_mainMenu);
        goback_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent activity2 = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(activity2);
            }
        });
        Button godict_button = (Button)findViewById(R.id.button_dict);
        godict_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent activity2 = new Intent(getApplicationContext(), ShowDict.class);
                startActivity(activity2);
            }
        });
        Button del_button = (Button)findViewById(R.id.button_del);
        del_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                final Intent activity2 = new Intent(getApplicationContext(), ShowDict.class);

                AlertDialog.Builder builder = new AlertDialog.Builder(ShowWord.this);
                builder.setTitle("Delete word");
                builder.setMessage("Are you sure you want to proceed?");
                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        try {
                            myddbb.delWord(showWord,showMeaning);
                            Toast.makeText(getApplicationContext(), "Deleted! ", 20).show();
                            startActivity(activity2);
                        } catch (Exception e) {
                            Toast.makeText(getApplicationContext(), "There has been an error processing your request. Please, try later on. ", 20).show();
                        }
                    }
                });
                builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                AlertDialog alert = builder.create();
                alert.show();



            }
        });
        Button edit_button = (Button)findViewById(R.id.button_edit);
        edit_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent activity = new Intent(getApplicationContext(), EditWord.class);
                activity.putExtra("showWord",showWord);
                activity.putExtra("showMeaning",showMeaning);
                startActivity(activity);
            }
        });

    }
}
