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

public class GrammarAdd extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grammar_add);

        Button button1 = findViewById(R.id.button_insertnote);
        Button button2 = findViewById(R.id.button_mainMenu_fromGramadd);
        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                EditText newTitle = (EditText) findViewById(R.id.edittittle);
                EditText newNote = (EditText) findViewById(R.id.note);
                String strTitle = newTitle.getText().toString();
                String strNote = newNote.getText().toString();
                Note note = new Note(strTitle,strNote);

                if ((strTitle.length()>2)&&(strNote.length()>2)) {
                    try {

                        DdBb myddbb1 = new DdBb(getApplicationContext());
                        List<Note> notes=myddbb1.getAllNotes(strTitle);
                        if (notes.size()>0){
                            Toast.makeText(getApplicationContext(), "\"Title\" already exists. ", 20).show();
                        } else {
                            DdBb myddbb = new DdBb(getApplicationContext());
                            Boolean bool = myddbb.insertNote(note);
                            if (bool) {
                                Toast.makeText(getApplicationContext(), "New note has been added. ", 20).show();
                                Intent activity = new Intent(getApplicationContext(), GrammarList.class);
                                startActivity(activity);
                                finish();
                            } else {
                                Toast.makeText(getApplicationContext(), "There has been an error processing this information. Please, avoid special symbols", 20).show();
                            }
                        }
                    } catch (Exception e) {}
                } else {
                    Toast.makeText(getApplicationContext(), "\"Title\" or \"Note\" are too short. Please, try again. ", 20).show();
                }
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent activity = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(activity);
                finish();
            }
        });


    }
}
