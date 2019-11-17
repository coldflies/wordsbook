package com.example.wordsbook;

public class wordsList {
    private String English;
    private String Chinese;
    private String Exmaple;
    public  wordsList(String English,String Chinese,String Exmaple){
        this.Chinese = Chinese;
        this.English = English;
        this.Exmaple = Exmaple;
    }
    public String getEnglish(){
        return  English;
    }
    public String getChinese(){
        return  Chinese;
    }
    public String getExmaple(){
        return  Exmaple;
    }
}
