package com.jft.brushup;

public class Phoneme {

    private String phoneme;
    private String note;
    private String uses;
    private String example;


    public Phoneme(){}

    public Phoneme(String phoneme, String note, String uses, String example) {
        this.phoneme = phoneme;
        this.note = note;
        this.uses = uses;
        this.example = example;
    }

    public String getNote() {

        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getUses() {
        return uses;
    }

    public void setUses(String uses) {
        this.uses = uses;
    }

    public String getExample() {
        return example;
    }

    public void setExample(String example) {
        this.example = example;
    }

    public String getPhoneme() {

        return phoneme;
    }

    public void setPhoneme(String phoneme) {
        this.phoneme = phoneme;
    }
}
