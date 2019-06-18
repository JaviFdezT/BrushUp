package com.jft.brushup;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.os.Build;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.channels.FileChannel;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;import java.util.*;
import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;

public class ResetDatabase extends AppCompatActivity {
    // Storage Permissions
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    private static void verifyStoragePermissions(Activity activity) {
        // Check if we have write permission
        int permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(
                    activity,
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
            );
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (Exception e){}
        }
    }

    /* Checks if external storage is available for read and write */
    static boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }
    /* Checks if external storage is available to at least read */
    static boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state) ||
                Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            return true;
        }
        return false;
    }

    static public File getSD(String fileName) {
        // Get the directory for the user's public pictures directory.
        File file = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_DOWNLOADS), fileName);
        return file;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        final Activity act = this;
        final Intent activity = new Intent(getApplicationContext(), MainActivity.class);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_database);
        Button button1 = findViewById(R.id.button_resetlevel);
        Button button2 = findViewById(R.id.button_resettable);
        Button button3 = findViewById(R.id.button_mainMenu_fromStat);
        Button button4 = findViewById(R.id.button_resetapp);
        Button button5 = findViewById(R.id.button_backup);
        Button button6 = findViewById(R.id.button_in);

        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ResetDatabase.this);
                builder.setTitle("Warning");
                builder.setMessage("All changes will be irreversible. Are you sure you want to proceed?");
                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        try {
                            try {
                                DdBb myddbb = new DdBb(getApplicationContext());
                                myddbb.restartLevel();
                                Toast.makeText(getApplicationContext(), "Done!", 20).show();
                            } catch (Exception e) {
                                Toast.makeText(getApplicationContext(), "There has been an error processing your request. Please, try later on. ", 20).show();
                            }
                        } catch (Exception e) {}
                        startActivity(activity);
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
        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ResetDatabase.this);
                builder.setTitle("Warning");
                builder.setMessage("All changes will be irreversible. Are you sure you want to proceed?");
                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        try {
                            DdBb myddbb = new DdBb(getApplicationContext());
                            myddbb.restartTables();
                            Toast.makeText(getApplicationContext(), "Done!", 20).show();
                        } catch (Exception e) {
                            Toast.makeText(getApplicationContext(), "There has been an error processing your request. Please, try later on. ", 20).show();
                        }
                        startActivity(activity);
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
        button3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(activity);
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ResetDatabase.this);
                builder.setTitle("Warning");
                builder.setMessage("All changes will be irreversible. Are you sure you want to proceed?");
                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        DdBb myddbb = new DdBb(getApplicationContext());
                        try {
                            BufferedReader reader = new BufferedReader( new InputStreamReader(getAssets().open("ddbb.sql")));
                            String mLine = reader.readLine();
                            Boolean bool1=myddbb.run(mLine);
                            mLine = reader.readLine();
                            Boolean bool2=myddbb.run(mLine);
                            if (bool1 && bool2) {
                                Toast.makeText(getApplicationContext(), "Done!", 20).show();
                                startActivity(activity);
                            } else {
                                Toast.makeText(getApplicationContext(), "There has been an error processing your request. Please, check your code. ", 20).show();
                            }
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
        button5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ResetDatabase.verifyStoragePermissions(act);
                if (ResetDatabase.isExternalStorageWritable()) {
                    try {

                        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
                        Calendar calendar = Calendar.getInstance();
                        String backupDBPath="brushup_"+sdf.format(calendar.getTime())+".db";

                        String currentDBPath = "/data/data/" + getApplicationContext().getPackageName() + "/databases/com.jft.brushup.ddbb.db";
                        File currentDB = new File(currentDBPath);
                        File backupDB = ResetDatabase.getSD(backupDBPath);

                        if (currentDB.exists()) {
                            System.out.println(backupDB);
                            backupDB.createNewFile();
                            FileChannel src = new FileInputStream(currentDB).getChannel();
                            FileChannel dst = new FileOutputStream(backupDB).getChannel();
                            dst.transferFrom(src, 0, src.size());
                            src.close();
                            dst.close();
                            Toast.makeText(getApplicationContext(), "Done!", 20).show();
                            startActivity(activity);
                        } else {
                            Toast.makeText(getApplicationContext(), "Read permissions not granted. ", 20).show();
                        }
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                        Toast.makeText(getApplicationContext(), "Permissions denied. ", 20).show();
                    } catch (IOException e) {
                        e.printStackTrace();
                        Toast.makeText(getApplicationContext(), "Permissions denied. ", 20).show();
                    }

                } else {
                    Toast.makeText(getApplicationContext(), "Permissions denied. ", 20).show();
                }
            }
        });
        button6.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ResetDatabase.verifyStoragePermissions(act);
                if (ResetDatabase.isExternalStorageReadable()) {
                    try {
                        File importFile = ResetDatabase.getSD("brushup.sql");
                        FileReader in = new FileReader(importFile);
                        BufferedReader br = new BufferedReader(in);
                        String line = br.readLine();
                        Boolean bool1=true;
                        while (line != null) {
                            DdBb myddbb = new DdBb(getApplicationContext());
                            bool1=myddbb.run(line);
                            line = br.readLine();
                        }
                        br.close();
                        if (bool1) {
                            Toast.makeText(getApplicationContext(), "Done!", 20).show();
                            startActivity(activity);
                        } else {
                            Toast.makeText(getApplicationContext(), "There has been an error processing your request. Please, check your code. ", 20).show();
                        }
                    } catch (Exception e){
                        e.printStackTrace();
                        Toast.makeText(getApplicationContext(), "Permissions denied or file not found.  ", 20).show();
                    }

                } else {
                    Toast.makeText(getApplicationContext(), "Permissions denied. ", 20).show();
                }
            }
        });


    }

}
