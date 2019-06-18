package com.jft.brushup;


import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.res.AssetManager;
import android.net.Uri;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    private boolean running = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //ActionBar actionBar = getSupportActionBar(); // or getActionBar();
        //getSupportActionBar().setDisplayShowHomeEnabled(true);
        //getSupportActionBar().setLogo(R.mipmap.ic_launcher);
        //getSupportActionBar().setDisplayUseLogoEnabled(true);

        Button button1 = findViewById(R.id.buttonplay);
        Button button2 = findViewById(R.id.buttonstats);
        Button button3 = findViewById(R.id.buttonreset);
        Button button4 = findViewById(R.id.buttondict);
        Button button5 = findViewById(R.id.buttoninsert);
        Button button6 = findViewById(R.id.buttongrammar);
        Button button7 = findViewById(R.id.buttoncredits);
        Button button8 = findViewById(R.id.buttonexit);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent activity = new Intent(getApplicationContext(), GameOptions.class);
                    startActivity(activity);
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent activity = new Intent(getApplicationContext(), ShowStats.class);
                    startActivity(activity);
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent activity = new Intent(getApplicationContext(), ResetDatabase.class);
                    startActivity(activity);
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent activity = new Intent(getApplicationContext(), ShowDict.class);
                    startActivity(activity);
            }
        });
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent activity = new Intent(getApplicationContext(), InsertWord.class);
                    startActivity(activity);
            }
        });
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent activity = new Intent(getApplicationContext(), GrammarSection.class);
                    startActivity(activity);
            }
        });
        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent activity = new Intent(getApplicationContext(), ShowCredits.class);
                    startActivity(activity);
            }
        });
        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent homeIntent = new Intent(Intent.ACTION_MAIN);
                    homeIntent.addCategory( Intent.CATEGORY_HOME );
                    homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(homeIntent);
            }
        });
    }

}
