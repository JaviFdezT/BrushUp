package com.jft.brushup;

public class Word {

    private String word;
    private String example;
    private String meaning;
    private String syntaxis;
    private int category;

    public Word(){}

    public Word(String word, String meaning, String example, String syntaxis, int category) {
        super();
        this.word = word;
        this.example = example;
        this.meaning = meaning;
        this.syntaxis = syntaxis;
        this.category = category;
    }


    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getExample() {
        return example;
    }

    public void setExample(String example) {
        this.example = example;
    }

    public String getMeaning() {
        return meaning;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }

    public String getSyntaxis() {
        return syntaxis;
    }

    public void setSyntaxis(String syntaxis) {
        this.syntaxis = syntaxis;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }
}
