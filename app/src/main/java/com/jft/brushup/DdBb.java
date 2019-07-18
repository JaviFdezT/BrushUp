package com.jft.brushup;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
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
            this.insertPhonemes();
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

    public boolean insertPhonemes() {
        Boolean bool=true;
        SQLiteDatabase db = this.getWritableDatabase();
        String CREATE_PHONEMES_TABLE = "CREATE TABLE IF NOT EXISTS PHONEMES (ind int PRIMARY KEY, phoneme varchar(255) NOT NULL, note varchar(255) NOT NULL, uses varchar(255), example varchar(255),CONSTRAINT norepeat UNIQUE (phoneme)  ON CONFLICT IGNORE) ";
        String UPDATE_TABLE_1 = "INSERT INTO PHONEMES (phoneme, note, uses, example) VALUES ('|æ|','The mouth has to have a similar degree of opening as for the Spanish |a|, but the position of the tongue is similar to Spanish |e|.','-a-','bad, can, fan')";
        String UPDATE_TABLE_2 = "INSERT INTO PHONEMES (phoneme, note, uses, example) VALUES ('|ʌ|','Similar to Spanish |a| but shorter.','-u-; -o-; -oo-','fun, cut, come, none, blood')";
        String UPDATE_TABLE_3 = "INSERT INTO PHONEMES (phoneme, note, uses, example) VALUES ('|ɑː|','As if yawning, open mouth, long sound.','-ar-; -au-; -al-; -ah-','farm, laugh, psalm, shah')";
        String UPDATE_TABLE_4 = "INSERT INTO PHONEMES (phoneme, note, uses, example) VALUES ('|e|,|ɛ|','Galician sound in the word «terra». More open than the Spanish |e|.','-ea-; -ai-','dead, said')";
        String UPDATE_TABLE_5 = "INSERT INTO PHONEMES (phoneme, note, uses, example) VALUES ('|ɜː|','Similar to the sound Spanish speakers produce when they think aloud «Ehhhh!».','-ir-; -ur-; -er-; -ear-','sir, fur, verdant, pearl')";
        String UPDATE_TABLE_6 = "INSERT INTO PHONEMES (phoneme, note, uses, example) VALUES ('|ɪ|','Quality closer to Spanish |e| than to Spanish |i|.','-age; -y-; -e-; -u-','this, luggage, hymn, reflect, reliable, business, busy, lettuce')";
        String UPDATE_TABLE_7 = "INSERT INTO PHONEMES (phoneme, note, uses, example) VALUES ('|iː|','Similar to Spanish |i| but longer.','-eo-; -ee-; -ea-; -ie-; -i-; -ey-; -e-','these, beach, people, meet, read, piece, machine, key, theme')";
        String UPDATE_TABLE_8 = "INSERT INTO PHONEMES (phoneme, note, uses, example) VALUES ('|ɒ|','Galician sound in the word «ola». More open than the Spanish |o|.','-o-; -a-; -ou-','lot, shop, wash, cough')";
        String UPDATE_TABLE_9 = "INSERT INTO PHONEMES (phoneme, note, uses, example) VALUES ('|ɔː|','Similar to Spanish |o| but longer.','-or-; -a-; -ou-; -au-; -aw-; -oa-; -al-','north, all, thought, fraud, prawn, broad, talk')";
        String UPDATE_TABLE_10 = "INSERT INTO PHONEMES (phoneme, note, uses, example) VALUES ('|ʊ|','Quality between Spanish |u| and |e|','-o-; -oo-; -u-; -ou-','to, woman, wolf, cook, put, could')";
        String UPDATE_TABLE_11 = "INSERT INTO PHONEMES (phoneme, note, uses, example) VALUES ('|uː|','Similar to Spanish |u| but longer.','-oo-; -ue-; -wo-; -ew-; -u-; -ui-; -o-; -ou-','goose, choose, boot, moon, clue, two, screw, flute, fruit, do, who, soup, you')";
        String UPDATE_TABLE_12 = "INSERT INTO PHONEMES (phoneme, note, uses, example) VALUES ('|ə|','Short |ɜː|','an unstressed syllable. Weak vowel.','comfortable')";
        String UPDATE_TABLE_13 = "INSERT INTO PHONEMES (phoneme, note, uses, example) VALUES ('|i|','Similar to Spanish |i|. Weak vowel.','-y-;-i-;-ie-;-e-','lorry, happy, media, happier, married, apostrophe')";
        String UPDATE_TABLE_14 = "INSERT INTO PHONEMES (phoneme, note, uses, example) VALUES ('|u|','Similar to Spanish |u|. Weak vowel.','-u-','circulate, curator, ingenuity')";
        String UPDATE_TABLE_15 = "INSERT INTO PHONEMES (phoneme, note, uses, example) VALUES ('|aɪ|','Spanish |a| + |ɪ|','-i-; -ie-; -y-; -igh-; -uy-','mine, tie, cry, light, buy')";
        String UPDATE_TABLE_16 = "INSERT INTO PHONEMES (phoneme, note, uses, example) VALUES ('|eɪ|','Spanish |e| + |ɪ|','-a-; -ay-; -ai','bake, pay, pain')";
        String UPDATE_TABLE_17 = "INSERT INTO PHONEMES (phoneme, note, uses, example) VALUES ('|ɔɪ|','Spanish |o| + |ɪ|','-oi-; -oy-','soil, boy')";
        String UPDATE_TABLE_18 = "INSERT INTO PHONEMES (phoneme, note, uses, example) VALUES ('|aʊ|','Spanish |a| + |ʊ|','-ou-; -ow-','out, now')";
        String UPDATE_TABLE_19 = "INSERT INTO PHONEMES (phoneme, note, uses, example) VALUES ('|əʊ|','|ə| + |ʊ|','-oa-; -ow-; -o-','boat, oat, coach, road, low, know, bone, ocean, soldier')";
        String UPDATE_TABLE_20 = "INSERT INTO PHONEMES (phoneme, note, uses, example) VALUES ('|ɪə|','|ɪ| + |ə|','-ear-; -ier-; -ea-; -eer-; -ere-','dear, pier, idea, deer, here')";
        String UPDATE_TABLE_21 = "INSERT INTO PHONEMES (phoneme, note, uses, example) VALUES ('|eə|','Spanish |e| + |ə|','-are-; -eir-; -air-; -ere-','mare, their, pair, there')";
        String UPDATE_TABLE_22 = "INSERT INTO PHONEMES (phoneme, note, uses, example) VALUES ('|ʊə|','|ʊ| + |ə|','-oor-; -our-; -ure-','poor, tourist, pure')";
        String UPDATE_TABLE_23 = "INSERT INTO PHONEMES (phoneme, note, uses, example) VALUES ('|aɪə|','|aɪ| + |ə|','-ire; -ier; -oir-; -yre-; -iro-','fire, drier, choir, tyre, iron')";
        String UPDATE_TABLE_24 = "INSERT INTO PHONEMES (phoneme, note, uses, example) VALUES ('|eɪə|','|eɪ| + |ə|','-ayer-; -eyor-; -eyer-','layer, conveyor, greyer')";
        String UPDATE_TABLE_25 = "INSERT INTO PHONEMES (phoneme, note, uses, example) VALUES ('|ɔɪə|','|ɔɪ| + |ə|','-oya-; -oyou-; -oyer-','royal, joyous, Moyer')";
        String UPDATE_TABLE_26 = "INSERT INTO PHONEMES (phoneme, note, uses, example) VALUES ('|aʊə|','|aʊ| + |ə|','-ower-; -owar-; -our-','power, coward, sour')";
        String UPDATE_TABLE_27 = "INSERT INTO PHONEMES (phoneme, note, uses, example) VALUES ('|əʊə|','|əʊ| + |ə|','-ower-','lower')";
        try {
            db.execSQL("DROP TABLE IF EXISTS PHONEMES");
            db.execSQL(CREATE_PHONEMES_TABLE);
            db.execSQL(UPDATE_TABLE_1);
            db.execSQL(UPDATE_TABLE_2);
            db.execSQL(UPDATE_TABLE_3);
            db.execSQL(UPDATE_TABLE_4);
            db.execSQL(UPDATE_TABLE_5);
            db.execSQL(UPDATE_TABLE_6);
            db.execSQL(UPDATE_TABLE_7);
            db.execSQL(UPDATE_TABLE_8);
            db.execSQL(UPDATE_TABLE_9);
            db.execSQL(UPDATE_TABLE_10);
            db.execSQL(UPDATE_TABLE_11);
            db.execSQL(UPDATE_TABLE_12);
            db.execSQL(UPDATE_TABLE_13);
            db.execSQL(UPDATE_TABLE_14);
            db.execSQL(UPDATE_TABLE_15);
            db.execSQL(UPDATE_TABLE_16);
            db.execSQL(UPDATE_TABLE_17);
            db.execSQL(UPDATE_TABLE_18);
            db.execSQL(UPDATE_TABLE_19);
            db.execSQL(UPDATE_TABLE_20);
            db.execSQL(UPDATE_TABLE_21);
            db.execSQL(UPDATE_TABLE_22);
            db.execSQL(UPDATE_TABLE_23);
            db.execSQL(UPDATE_TABLE_24);
            db.execSQL(UPDATE_TABLE_25);
            db.execSQL(UPDATE_TABLE_26);
            db.execSQL(UPDATE_TABLE_27);
        } catch (NullPointerException e){
            bool=false;
        }
        this.close();
        return bool;
    }

    public List<Phoneme> getPhonemes() {
        List<Phoneme> phonemes = new LinkedList<Phoneme>();
        Cursor cursor=null;
        String query = "SELECT  * FROM PHONEMES ORDER BY ind";
        SQLiteDatabase db = null;
        try {
            db=this.getWritableDatabase();
            cursor = db.rawQuery(query, null);
        } catch (SQLiteException e1) {
            this.insertPhonemes();
            try {
                cursor = db.rawQuery(query, null);
            } catch (Exception e2) {}
        }
        try {
            //if (cursor.getCount()==0){
            //    this.insertPhonemes();
            //}

            if (cursor.moveToFirst()) {
                do {
                    Phoneme phoneme = new Phoneme();
                    phoneme.setPhoneme(cursor.getString(1));
                    phoneme.setNote(cursor.getString(2));
                    phoneme.setUses(cursor.getString(3));
                    phoneme.setExample(cursor.getString(4));
                    phonemes.add(phoneme);

                } while (cursor.moveToNext());
            }
            cursor.close();
            this.close();
        } catch (Exception e3) {}
        return phonemes;
    }


    public Phoneme getPhoneme(String showPhoneme) {
        List<Phoneme> phonemes = new LinkedList<Phoneme>();
        Phoneme finalPhoneme =  new Phoneme();
        Cursor cursor=null;
        String query = "SELECT  * FROM PHONEMES WHERE phoneme='"+showPhoneme+"'";
        SQLiteDatabase db = null;
        try {
            db=this.getWritableDatabase();
            cursor = db.rawQuery(query, null);
            if (cursor.moveToFirst()) {
                do {
                    Phoneme phoneme = new Phoneme();
                    phoneme.setPhoneme(cursor.getString(1));
                    phoneme.setNote(cursor.getString(2));
                    phoneme.setUses(cursor.getString(3));
                    phoneme.setExample(cursor.getString(4));
                    phonemes.add(phoneme);
                } while (cursor.moveToNext());
            }
            cursor.close();
            System.out.println(showPhoneme);
            System.out.println(query);
            System.out.println(cursor.getCount());
            finalPhoneme=phonemes.get(0);
            this.close();
        } catch (NullPointerException e3) {}
        return finalPhoneme;
    }

}
