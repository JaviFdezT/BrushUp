package com.jft.brushup;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import java.util.List;

public class InsertWord extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_word);

        final Intent activity = new Intent(getApplicationContext(), MainActivity.class);
        Button button = findViewById(R.id.button_add);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String wordType="";
                RadioButton wordIsV = (RadioButton) findViewById(R.id.radioButton21);
                RadioButton wordIsS = (RadioButton) findViewById(R.id.radioButton22);
                RadioButton wordIsA = (RadioButton) findViewById(R.id.radioButton23);
                RadioButton wordIsOther = (RadioButton) findViewById(R.id.radioButton24);
                EditText newWord = (EditText) findViewById(R.id.editText1);
                EditText newMeaning = (EditText) findViewById(R.id.editText2);
                EditText newExample = (EditText) findViewById(R.id.editText3);
                String strWord = newWord.getText().toString().toLowerCase();
                String strMean = newMeaning.getText().toString();
                String strExample = newExample.getText().toString();
                if (wordIsV.isChecked())
                {
                    wordType="v";
                } else if (wordIsS.isChecked()) {
                    wordType="s";
                } else if (wordIsA.isChecked()) {
                    wordType="adj";
                } else {
                    wordType="other";
                }

                if ((strMean.length()>1)&&(strWord.length()>1)) {
                    try {
                        DdBb myddbb1 = new DdBb(getApplicationContext());
                        List<Word> words=myddbb1.getAllWords(strWord, strMean);
                        if (words.size()>0){
                            Toast.makeText(getApplicationContext(), "\"Word\" and \"Meaning\" already exist. ", 20).show();
                        } else {
                            DdBb myddbb2 = new DdBb(getApplicationContext());
                            Word word=new Word(strWord, strMean, strExample, wordType,1);
                            Boolean bool = myddbb2.insertWord(word);
                            if (bool) {
                                Toast.makeText(getApplicationContext(), "New word has been added to the dictionary. ", 20).show();
                                startActivity(activity);
                                finish();
                            } else {
                                Toast.makeText(getApplicationContext(), "There has been an error processing this information. Please, avoid special symbols", 20).show();
                            }
                        }
                    } catch (Exception e) {}
                } else {
                    Toast.makeText(getApplicationContext(), "\"Word\" or \"Meaning\" are too short. Word has not been added. ", 20).show();
                }
            }
        });
        Button button1 = findViewById(R.id.button_mainMenu_fromInsert);
        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(activity);
                finish();
            }
        });
    }
}
