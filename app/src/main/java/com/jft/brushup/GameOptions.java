package com.jft.brushup;


import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

public class GameOptions extends AppCompatActivity {

    private RadioButton langIsEnglish;
    private RadioButton wordIsV;
    private RadioButton wordIsA;
    private RadioButton wordIsS;
    private String lookforlang;
    private String lookfortype;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_options);

        Button button = findViewById(R.id.buttongo);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                langIsEnglish = (RadioButton) findViewById(R.id.radioButton1);
                wordIsV = (RadioButton) findViewById(R.id.radioButton3);
                wordIsS = (RadioButton) findViewById(R.id.radioButton4);
                wordIsA = (RadioButton) findViewById(R.id.radioButton5);
                if(langIsEnglish.isChecked())
                {
                    lookforlang="Word";
                } else {
                    lookforlang="Meaning";
                }
                if(wordIsV.isChecked())
                {
                    lookfortype="v";
                } else if (wordIsS.isChecked()) {
                    lookfortype="s";
                } else if (wordIsA.isChecked()) {
                    lookfortype="adj";
                } else {
                    lookfortype="any";
                }
                Intent activity = new Intent(getApplicationContext(), GameChoose.class);
                activity.putExtra("lookforlang",lookforlang);
                activity.putExtra("lookfortype",lookfortype);
                startActivity(activity);
                finish();
            }
        });

    }
}
