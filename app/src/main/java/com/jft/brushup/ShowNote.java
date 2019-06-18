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

public class ShowNote extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_note);

        Intent intent = getIntent();
        final String showNote = intent.getStringExtra("showNote");

        List<Note> notes = new LinkedList<Note>();
        final DdBb myddbb = new DdBb(this);
        try {
            notes = myddbb.getAllNotes(showNote);
            Note note = notes.get(0);

            TextView textView1 = (TextView) findViewById(R.id.thistitle);
            TextView textView2 = (TextView) findViewById(R.id.thisnote);
            textView1.setText(note.getTitle());
            textView2.setText(note.getDetails());
        } catch (Exception e){
            final Intent activity1 = new Intent(getApplicationContext(), GrammarList.class);
            startActivity(activity1);
        }


        Button goback_button = (Button)findViewById(R.id.button_mainMenu_fromgramshow);
        goback_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent activity2 = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(activity2);
            }
        });
        Button godict_button = (Button)findViewById(R.id.button_dictnotes);
        godict_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent activity2 = new Intent(getApplicationContext(), GrammarList.class);
                startActivity(activity2);
            }
        });
        Button del_button = (Button)findViewById(R.id.button_delnote);
        del_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                final Intent activity2 = new Intent(getApplicationContext(), GrammarList.class);

                AlertDialog.Builder builder = new AlertDialog.Builder(ShowNote.this);
                builder.setTitle("Delete note");
                builder.setMessage("Are you sure you want to proceed?");
                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        try {
                            myddbb.delNote(showNote);
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
        Button edit_button = (Button)findViewById(R.id.button_editnote);
        edit_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent activity = new Intent(getApplicationContext(), EditNote.class);
                activity.putExtra("showNote",showNote);
                startActivity(activity);
            }
        });

    }
}
