package com.jft.brushup;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.jft.brushup.R;

import java.util.LinkedList;
import java.util.List;
import android.app.AlertDialog;

public class EditNote extends AppCompatActivity {

    private EditText editText1 = null;
    private EditText editText2 = null;
    private Note note = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);


        Intent intent = getIntent();
        final String showNote = intent.getStringExtra("showNote");

        final DdBb myddbb = new DdBb(this);
        final List<Note> notes = myddbb.getAllNotes(showNote);
        final Intent activity = new Intent(getApplicationContext(), ShowNote.class);

        try {
            note = notes.get(0);
            editText1 = (EditText) findViewById(R.id.updatetittle);
            editText2 = (EditText) findViewById(R.id.updatenote);

            editText1.setText(note.getTitle());
            editText2.setText(note.getDetails());

        } catch (Exception e){
            startActivity(activity);
        }


        Button button1 = findViewById(R.id.button_insertnote2);
        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {


                AlertDialog.Builder builder = new AlertDialog.Builder(EditNote.this);
                builder.setTitle("Update note");
                builder.setMessage("Are you sure you want to proceed?");
                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();

                        String newTitle = editText1.getText().toString().replace("'","`").replace("\"","`");
                        String newDetails = editText2.getText().toString().replace("'","`").replace("\"","`");

                        try {
                            DdBb myddbb = new DdBb(getApplicationContext());
                            Boolean bool = myddbb.editNote(note.getTitle(),newTitle,newDetails);
                            if (bool) {
                                activity.putExtra("showNote",newTitle);
                                Toast.makeText(getApplicationContext(), "Updated!", 20).show();
                                startActivity(activity);
                            } else {
                                Toast.makeText(getApplicationContext(), "There has been an error processing this information. Please, avoid special symbols", 20).show();
                            }
                        } catch (Exception e){
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

        Button button2 = findViewById(R.id.button_mainMenu_fromGramadd2);
        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                activity.putExtra("showNote",showNote);
                startActivity(activity);
            }
        });


    }
}
