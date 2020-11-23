package com.example.hikaioffline.walkthrough;

public class ScreenItem {
    String Title,Description;
    int ScreenImage;

    public ScreenItem(String title, String description, int screenImage){
        Title=title;
        Description=description;
        ScreenImage=screenImage;
    }
    public void setTitle(String title){
        Title=Title;
    }
    public void setDescription(String description){
        Description=description;
    }
    public void setImage(int screenImage){
        ScreenImage=screenImage;
    }
    public String getTitle(){
        return Title;
    }
    public String getDescription(){
        return Description;
    }
    public int getScreenImage(){
        return ScreenImage;
    }
}
