package com.aubert.freebooks.entity;

public class Book {
    private String mTitle;
    private String mUrlMediumImage;
    private String mUrlReaderOneImage;
    private String mUrlReaderTwoImage;

    public void setTitle(String title) {
        mTitle = title;
    }

    public void setUrlMediumImage(String urlMediumImage) {
        mUrlMediumImage = urlMediumImage;
    }

    public void setUrlReaderOneImage(String urlReaderOneImage) {
        mUrlReaderOneImage = urlReaderOneImage;
    }

    public void setUrlReaderTwoImage(String urlReaderTwoImage) {
        mUrlReaderTwoImage = urlReaderTwoImage;
    }

    public String getTitle(){
        return mTitle;
    }

    public String getUlrMediumImage(){
        return mUrlMediumImage;
    }

    public String getUlrReaderOneImage(){
        return mUrlReaderOneImage;
    }

    public String getUlrReaderTwoImage(){
        return mUrlReaderTwoImage;
    }
}
