package com.jft.brushup;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.LinkedList;
import java.util.List;
import android.widget.AdapterView.OnItemClickListener;

public class PronunciationList extends AppCompatActivity {

    private ListView listView;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pronunciation_list);

        DdBb myddbb = new DdBb(this);
        final List<Phoneme> phonemes=myddbb.getPhonemes();
        final List<String> all=new LinkedList<String>();
        for(int i = 0; i < phonemes.size(); ++i) {
            all.add(phonemes.get(i).getPhoneme());
        }

        adapter = new ArrayAdapter<String>(this,
                R.layout.activity_listview1, all);
        listView = (ListView) findViewById(R.id.listviewpron);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent activity = new Intent(getApplicationContext(), ShowPhoneme.class);
                try {
                    activity.putExtra("showPhoneme",phonemes.get(position).getPhoneme());
                    startActivity(activity);
                } catch (Exception e) {}
            }
        });
        Button button = findViewById(R.id.button_mainMenu_fromPronList);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent activity = new Intent(getApplicationContext(), NoteSection.class);
                startActivity(activity);
            }
        });

    }
}
