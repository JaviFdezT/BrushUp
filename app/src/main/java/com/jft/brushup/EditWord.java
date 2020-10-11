package com.jft.brushup;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.LinkedList;
import java.util.List;

public class EditWord extends AppCompatActivity {

    private String newSyntaxis=null;
    private EditText textView1 = null;
    private EditText textView2 = null;
    private EditText textView3 = null;
    private RadioButton wordIsV = null;
    private RadioButton wordIsS = null;
    private RadioButton wordIsA = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_word);

        Intent intent = getIntent();
        final String showWord = intent.getStringExtra("showWord");
        final String showMeaning = intent.getStringExtra("showMeaning");

        List<Word> words = new LinkedList<Word>();
        final DdBb myddbb = new DdBb(this);
        Word word=null;
        try {
            words = myddbb.getAllWords(showWord,showMeaning);
            word = words.get(0);

            textView1 = (EditText) findViewById(R.id.edit1);
            textView2 = (EditText) findViewById(R.id.edit2);
            textView3 = (EditText) findViewById(R.id.edit3);
            wordIsV = (RadioButton) findViewById(R.id.radioButton31);
            wordIsS = (RadioButton) findViewById(R.id.radioButton32);
            wordIsA = (RadioButton) findViewById(R.id.radioButton33);
            RadioGroup radioType = (RadioGroup) findViewById(R.id.radioGroup4);
            Integer radioButton=0;
            textView1.setText(word.getWord());
            textView2.setText(word.getMeaning());
            textView3.setText(word.getExample());
            if (word.getSyntaxis().equals("v")) {
                radioButton=R.id.radioButton31;
            } else if (word.getSyntaxis().equals("s")) {
                radioButton=R.id.radioButton32;
            } else if (word.getSyntaxis().equals("adj")) {
                radioButton=R.id.radioButton33;
            } else if (word.getSyntaxis().equals("other")) {
                radioButton=R.id.radioButton34;
            }
            radioType.check(radioButton);
        } catch (Exception e){
            Intent activity = new Intent(getApplicationContext(), ShowDict.class);
            startActivity(activity);
        }

        final Intent activity = new Intent(getApplicationContext(), ShowWord.class);

        Button button1 = findViewById(R.id.button_updateword);
        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {


                AlertDialog.Builder builder = new AlertDialog.Builder(EditWord.this);
                builder.setTitle("Update word");
                builder.setMessage("Are you sure you want to proceed?");
                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();

                        String newWord = textView1.getText().toString().replace("'","`").replace("\"","`");
                        String newMean = textView2.getText().toString().replace("'","`").replace("\"","`");
                        String newExample = textView3.getText().toString().replace("'","`").replace("\"","`");
                        if (wordIsV.isChecked())
                        {
                            newSyntaxis="v";
                        } else if (wordIsS.isChecked()) {
                            newSyntaxis="s";
                        } else if (wordIsA.isChecked()) {
                            newSyntaxis="adj";
                        } else {
                            newSyntaxis="other";
                        }
                        try {
                            DdBb myddbb = new DdBb(getApplicationContext());
                            myddbb.editWord(showWord,showMeaning,newWord,newMean,newExample,newSyntaxis);
                            activity.putExtra("showWord",newWord);
                            activity.putExtra("showMeaning",newMean);
                            Toast.makeText(getApplicationContext(), "Updated. ", 20).show();
                            startActivity(activity);
                            finish();
                        } catch (Exception e){
                            Toast.makeText(getApplicationContext(), "There has been an error processing your request. Please, try later on. ", 20).show();
                            Intent activity = new Intent(getApplicationContext(), ShowDict.class);
                            startActivity(activity);
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

        Button button2 = findViewById(R.id.button_gobacktodict);
        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                activity.putExtra("showWord",showWord);
                activity.putExtra("showMeaning",showMeaning);
                startActivity(activity);
                finish();
            }
        });
    }
}
