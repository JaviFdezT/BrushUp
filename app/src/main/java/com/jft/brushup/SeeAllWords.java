package com.jft.brushup;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.jft.brushup.R;

import java.util.ArrayList;
import java.util.List;

public class SeeAllWords extends AppCompatActivity {

    private ListView listView;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_all_words);

        Integer count=1;
        DdBb myddbb = new DdBb(this);
        final List<Word> fullwords=myddbb.getAllWords();
        final List<String> words=new ArrayList<String>();
        final List<Integer> absoluteTemporaryList=new ArrayList<Integer>();
        final List<Integer> mTemporaryList=new ArrayList<Integer>();
        for(int i = 0; i < fullwords.size(); ++i) {
            words.add("    "+count.toString()+". "+fullwords.get(i).getWord()+": "+fullwords.get(i).getMeaning());
            count++;
        }

        adapter = new ArrayAdapter<String>(this,R.layout.activity_listview2, words) {
            @Override
            public boolean isEnabled(int position) {
                return false;
            }
        };
        listView = (ListView) findViewById(R.id.listview2);

        listView.setAdapter(adapter);



        Button button1 = findViewById(R.id.button_goAgainToDict);
        Button button2 = findViewById(R.id.button_orderedByLevel);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent activity = new Intent(getApplicationContext(), ShowDict.class);
                startActivity(activity);
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent activity = new Intent(getApplicationContext(), SeeAllWordsByLevel.class);
                startActivity(activity);
            }
        });
    }
}
