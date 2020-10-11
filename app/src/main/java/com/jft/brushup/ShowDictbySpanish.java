package com.jft.brushup;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View.OnClickListener;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ShowDictbySpanish extends AppCompatActivity {


    private ListView listView;
    ArrayAdapter<String> adapter;
    EditText inputSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_dict_sp);

        DdBb myddbb = new DdBb(this);
        final List<Word> fullwords=myddbb.getAllWordsByMeaning();
        if (fullwords.size()==0) {
            Toast.makeText(getApplicationContext(), "There are no words. ", 20).show();
            Intent activity = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(activity);
        }

        final List<String> words=new ArrayList<String>();
        final List<Integer> absoluteTemporaryList=new ArrayList<Integer>();
        final List<Integer> mTemporaryList=new ArrayList<Integer>();
        for(int i = 0; i < fullwords.size(); ++i) {
            words.add(fullwords.get(i).getMeaning());
            mTemporaryList.add(i);
            absoluteTemporaryList.add(i);
        }

        adapter = new ArrayAdapter<String>(this,R.layout.activity_listview1, words);
        listView = (ListView) findViewById(R.id.listview);

        listView.setAdapter(adapter);
        inputSearch = (EditText) findViewById(R.id.inputSearch);

        inputSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence charSequence, int arg1, int arg2, int arg3) {
                adapter.getFilter().filter(charSequence);

                String searchWord = charSequence.toString().toLowerCase();
                if (words.size() > 0) {
                    if (searchWord.length() > 0) {
                        mTemporaryList.clear();
                        for (int i = 0; i < words.size(); i++) {
                            if ((words.get(i).startsWith(searchWord))||(words.get(i).contains(" "+searchWord))) {
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

        listView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
                Intent activity = new Intent(getApplicationContext(), ShowWord.class);
                try {
                    activity.putExtra("showMeaning",words.get(mTemporaryList.get(position)));
                    activity.putExtra("showWord",fullwords.get(mTemporaryList.get(position)).getWord());
                    startActivity(activity);
                } catch (Exception e) {}
            }
        });


        Button button0 = findViewById(R.id.button_byEnglish);
        button0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent activity = new Intent(getApplicationContext(), ShowDict.class);
                startActivity(activity);
            }
        });
        Button button1 = findViewById(R.id.button_seeAllWords);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent activity = new Intent(getApplicationContext(), SeeAllWords.class);
                startActivity(activity);
            }
        });
        Button button = findViewById(R.id.button_mainMenu_fromDict);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent activity = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(activity);
            }
        });
    }

}
