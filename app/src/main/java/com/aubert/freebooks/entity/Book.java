package com.aubert.freebooks.entity;

public class Book {
    private String mTitle;
    private String mUrlMediumImage;

    public void setTitle(String title) {
        mTitle = title;
    }

    public void setUrlMediumImage(String urlMediumImage) {
        mUrlMediumImage = urlMediumImage;
    }

    public String getTitle(){
        return mTitle;
    }

    public String getUlrMediumImage(){
        return mUrlMediumImage;
    }
}
