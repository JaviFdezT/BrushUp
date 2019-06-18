package com.jft.brushup;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class DdBb extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "com.jft.brushup.ddbb.db";

    public DdBb(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_WORDS_TABLE = "CREATE TABLE IF NOT EXISTS WORDS (word varchar(255) NOT NULL, example varchar(255) NOT NULL, meaning varchar(255), syntaxis varchar(255),category int,CONSTRAINT norepeat UNIQUE (word,meaning) ON CONFLICT IGNORE) ";
        String CREATE_NOTES_TABLE = "CREATE TABLE IF NOT EXISTS NOTES (title varchar(255) NOT NULL, note varchar(255) NOT NULL,CONSTRAINT norepeat UNIQUE (title)  ON CONFLICT IGNORE) ";
        try {
            db.execSQL(CREATE_WORDS_TABLE);
            db.execSQL(CREATE_NOTES_TABLE);
        } catch (Exception e) {}
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //db.execSQL("DROP TABLE IF EXISTS WORDS");
        //this.onCreate(db);
    }

    public List<Word> getAllWords() {
        List<Word> words = new LinkedList<Word>();
        String query = "SELECT  * FROM WORDS ORDER BY WORD";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        Word word = null;
        if (cursor.moveToFirst()) {
            do {
                word = new Word();
                word.setWord(cursor.getString(0));
                word.setExample(cursor.getString(1));
                word.setMeaning(cursor.getString(2));
                word.setSyntaxis(cursor.getString(3));
                word.setCategory(Integer.parseInt(cursor.getString(4)));
                words.add(word);
            } while (cursor.moveToNext());
        }
        cursor.close();
        this.close();
        return words;
    }

    public List<Word> getAllWordsByLevel() {
        List<Word> words = new LinkedList<Word>();
        String query = "SELECT  * FROM WORDS ORDER BY CATEGORY DESC, WORD ASC";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        Word word = null;
        if (cursor.moveToFirst()) {
            do {
                word = new Word();
                word.setWord(cursor.getString(0));
                word.setExample(cursor.getString(1));
                word.setMeaning(cursor.getString(2));
                word.setSyntaxis(cursor.getString(3));
                word.setCategory(Integer.parseInt(cursor.getString(4)));
                words.add(word);
            } while (cursor.moveToNext());
        }
        cursor.close();
        this.close();
        return words;
    }

    public List<Word> getAllWordsByType(String type) {
        List<Word> words = new LinkedList<Word>();
        String query = "SELECT  * FROM WORDS WHERE syntaxis='"+type+"' ";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        Word word = null;
        if (cursor.moveToFirst()) {
            do {
                word = new Word();
                word.setWord(cursor.getString(0));
                word.setExample(cursor.getString(1));
                word.setMeaning(cursor.getString(2));
                word.setSyntaxis(cursor.getString(3));
                word.setCategory(Integer.parseInt(cursor.getString(4)));
                words.add(word);
            } while (cursor.moveToNext());
        }
        cursor.close();
        this.close();
        return words;
    }

    public List<String> getWords() {
        List<String> words = new LinkedList<String>();
        try{
            String query = "SELECT  * FROM WORDS ORDER BY WORD";
            SQLiteDatabase db = this.getWritableDatabase();
            Cursor cursor = db.rawQuery(query, null);
            if (cursor.moveToFirst()) {
                do {
                    words.add(cursor.getString(0));
                } while (cursor.moveToNext());
            }
            cursor.close();
            this.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return words;
    }

    public List<String> getNotes() {
        List<String> notes = new LinkedList<String>();
        try{
            String query = "SELECT  * FROM NOTES ORDER BY TITLE";
            SQLiteDatabase db = this.getWritableDatabase();
            Cursor cursor = db.rawQuery(query, null);
            if (cursor.moveToFirst()) {
                do {
                    notes.add(cursor.getString(0));
                } while (cursor.moveToNext());
            }
            cursor.close();
            this.close();
        } catch (Exception e) {}
        return notes;
    }


    public List<Word> getAllWords(String wordName,String wordMeaning) {
        List<Word> words = new LinkedList<Word>();
        try{
            String query = "SELECT  * FROM WORDS WHERE word='"+wordName+"' AND meaning='"+wordMeaning+"'";
            SQLiteDatabase db = this.getWritableDatabase();
            Cursor cursor = db.rawQuery(query, null);
            Word word = null;
            if (cursor.moveToFirst()) {
                do {
                    word = new Word();
                    word.setWord(cursor.getString(0));
                    word.setExample(cursor.getString(1));
                    word.setMeaning(cursor.getString(2));
                    word.setSyntaxis(cursor.getString(3));
                    word.setCategory(Integer.parseInt(cursor.getString(4)));
                    words.add(word);
                } while (cursor.moveToNext());
            }
            cursor.close();
            this.close();
        } catch (Exception e) {}
        return words;
    }


    public List<Note> getAllNotes(String noteTitle) {
        List<Note> notes = new LinkedList<Note>();
        try{
            String query = "SELECT  * FROM NOTES WHERE title='"+noteTitle+"'";
            SQLiteDatabase db = this.getWritableDatabase();
            Cursor cursor = db.rawQuery(query, null);
            Note note = null;
            if (cursor.moveToFirst()) {
                do {
                    note = new Note();
                    note.setTitle(cursor.getString(0));
                    note.setDetails(cursor.getString(1));
                    notes.add(note);
                } while (cursor.moveToNext());
            }
            cursor.close();
            this.close();
        } catch (Exception e) {}
        return notes;
    }

    public void createTable() {
        SQLiteDatabase db = this.getWritableDatabase();
        String CREATE_WORDS_TABLE = "CREATE TABLE IF NOT EXISTS WORDS (word varchar(255) NOT NULL, example varchar(255) NOT NULL, meaning varchar(255), syntaxis varchar(255),category int,CONSTRAINT norepeat UNIQUE (word,meaning) ON CONFLICT IGNORE) ";
        String CREATE_NOTES_TABLE = "CREATE TABLE IF NOT EXISTS NOTES (title varchar(255) NOT NULL, note varchar(255) NOT NULL,CONSTRAINT norepeat UNIQUE (title)  ON CONFLICT IGNORE) ";
        try {
            db.execSQL(CREATE_WORDS_TABLE);
            db.execSQL(CREATE_NOTES_TABLE);
        } catch (Exception e) {}
    }
    public void updateLevel(int newLevel,String word,String meaning) {
        SQLiteDatabase db = this.getWritableDatabase();
        String level = new Integer(newLevel).toString();
        String UPDATE_TABLE = "UPDATE WORDS SET category="+level+" WHERE word='"+word+"' AND meaning='"+meaning+"'";
        try {
            db.execSQL(UPDATE_TABLE);
        } catch (Exception e) {}
        this.close();
    }

    public void delWord(String word,String meaning) {
        SQLiteDatabase db = this.getWritableDatabase();
        String UPDATE_TABLE = "DELETE FROM WORDS WHERE word='"+word+"' AND meaning='"+meaning+"'";
        try {
            db.execSQL(UPDATE_TABLE);
        } catch (Exception e) {}
        this.close();
    }

    public boolean editWord(String oldWord,String oldMeaning,String newWord,String newMeaning,String newExample,String newType) {
        Boolean bool=true;
        SQLiteDatabase db = this.getWritableDatabase();
        String UPDATE_TABLE = "UPDATE WORDS SET WORD='"+newWord+"', meaning='"+newMeaning+"', example='"+newExample+"', syntaxis='"+newType+"' WHERE word='"+oldWord+"' AND meaning='"+oldMeaning+"'";
        try {
            db.execSQL(UPDATE_TABLE);
        } catch (Exception e) {
            bool=false;
        }
        this.close();
        return bool;
    }

    public boolean editNote(String oldTitle,String newTitle,String newNote) {
        Boolean bool=true;
        SQLiteDatabase db = this.getWritableDatabase();
        String UPDATE_TABLE = "UPDATE NOTES SET title='"+newTitle+"', note='"+newNote+"' WHERE title='"+oldTitle+"'";
        try {
            db.execSQL(UPDATE_TABLE);
        } catch (Exception e) {
            bool=false;
        }
        this.close();
        return bool;
    }

    public void delNote(String title) {
        SQLiteDatabase db = this.getWritableDatabase();
        String UPDATE_TABLE = "DELETE FROM NOTES WHERE title='"+title+"'";
        try {
            db.execSQL(UPDATE_TABLE);
        } catch (Exception e) {}
        this.close();
    }

    public boolean insertWord(Word newWord) {
        Boolean bool=true;
        String word=newWord.getWord().toLowerCase().trim();
        String meaning=newWord.getMeaning().trim();
        String example=newWord.getExample().trim();
        String syntaxis=newWord.getSyntaxis().trim();
        Integer category=newWord.getCategory();
        SQLiteDatabase db = this.getWritableDatabase();
        String UPDATE_TABLE = "INSERT INTO WORDS (word, example, meaning, syntaxis,category) VALUES ('"+word+"','"+example+"','"+meaning+"','"+syntaxis+"',"+category+")";
        try {
            db.execSQL(UPDATE_TABLE);
        } catch (SQLiteConstraintException e){
            bool=false;
        }
        this.close();
        return bool;
    }

    public boolean insertNote(Note newNote) {
        Boolean bool=true;
        String title=newNote.getTitle().toLowerCase().trim();
        String note=newNote.getDetails().trim();
        SQLiteDatabase db = this.getWritableDatabase();
        String UPDATE_TABLE = "INSERT INTO NOTES (title, note) VALUES ('"+title+"','"+note+"')";
        try {
            db.execSQL(UPDATE_TABLE);
        } catch (SQLiteConstraintException e){
            bool=false;
        }
        this.close();
        return bool;
    }

    public void restartLevel() {
        SQLiteDatabase db = this.getWritableDatabase();
        String UPDATE_TABLE = "UPDATE WORDS SET category=1";
        db.execSQL(UPDATE_TABLE);
        this.close();
    }

    public void restartTables() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM WORDS");
        db.execSQL("DELETE FROM NOTES");
        this.createTable();
        this.close();
    }

    public boolean run(String query) {
        SQLiteDatabase db = this.getWritableDatabase();
        Boolean bool = true;
        try {
            db.execSQL(query);
        } catch (Exception e){
            bool=false;
            e.printStackTrace();
        }
        this.close();
        return bool;
    }

    public Map<Integer, Integer> getStats() {
        Map<Integer, Integer> stats = new HashMap<Integer, Integer>();
        String query = "SELECT  * FROM WORDS";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        Integer n = 0;
        if (cursor.moveToFirst()) {
            do {
                n++;
                int value=0;
                int key=Integer.parseInt(cursor.getString(4));
                try {
                    value=stats.get(key)+1;
                } catch (NullPointerException e){
                    value=1;
                }
                stats.put(key, value);
            } while (cursor.moveToNext());
        }
        stats.put(11, n);
        cursor.close();
        this.close();
        return stats;
    }

}
