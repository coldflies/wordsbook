package com.example.wordsbook;

public class wordsList {
    private String English;
    private String Chinese;
    private String Example;
    public  wordsList(String English,String Chinese,String Example){
        this.Chinese = Chinese;
        this.English = English;
        this.Example = Example;
    }
    public String getEnglish(){ return  English; }
    public void setEnglish(String English){this.English = English;}
    public String getChinese(){
        return  Chinese;
    }
    public void  setChinese(String Chinese){this.Chinese = Chinese;}
    public String getExample(){
        return  Example;
    }
    public void  setExample(String Example){this.Example = Example;}
}
