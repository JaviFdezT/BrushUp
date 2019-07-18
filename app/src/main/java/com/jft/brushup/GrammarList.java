package com.jft.brushup;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;

public class GrammarList extends AppCompatActivity {

    private ListView listView;
    ArrayAdapter<String> adapter;
    EditText inputSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grammar_list);

        DdBb myddbb = new DdBb(this);
        final List<String> notes=myddbb.getNotes();
        final List<Integer> absoluteTemporaryList=new ArrayList<Integer>();
        final List<Integer> mTemporaryList=new ArrayList<Integer>();
        for(int i = 0; i < notes.size(); ++i) {
            mTemporaryList.add(i);
            absoluteTemporaryList.add(i);
        }


        if (notes.size()==0) {
            Toast.makeText(getApplicationContext(), "There are no notes. ", 20).show();
            Intent activity = new Intent(getApplicationContext(), NoteSection.class);
            startActivity(activity);
        }

        adapter = new ArrayAdapter<String>(this,
                R.layout.activity_listview1, notes);
        listView = (ListView) findViewById(R.id.listview);
        listView.setAdapter(adapter);


        listView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent activity = new Intent(getApplicationContext(), ShowNote.class);
                try {
                    activity.putExtra("showNote",notes.get(mTemporaryList.get(position)));
                    startActivity(activity);
                } catch (Exception e) {}
            }
        });

        inputSearch = (EditText) findViewById(R.id.inputSearch2);

        inputSearch.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence charSequence, int arg1, int arg2, int arg3) {
                // When user changed the Text
                adapter.getFilter().filter(charSequence);
                String searchNote = charSequence.toString().toLowerCase();
                if (notes.size() > 0) {
                    if (searchNote.length() > 0) {
                        mTemporaryList.clear();
                        for (int i = 0; i < notes.size(); i++) {
                            if ((notes.get(i).startsWith(searchNote))||(notes.get(i).contains(" "+searchNote))) {
                                mTemporaryList.add(i);

                            }
                        }
                    } else {
                        mTemporaryList.clear();
                        mTemporaryList.addAll(absoluteTemporaryList);
                    }
                }
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
                                          int arg3) {
                // TODO Auto-generated method stub

            }

            @Override
            public void afterTextChanged(Editable arg0) {
                // TODO Auto-generated method stub
            }
        });

        Button button = findViewById(R.id.button_mainMenu_fromGrammarList);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent activity = new Intent(getApplicationContext(), NoteSection.class);
                startActivity(activity);
            }
        });

    }
}
