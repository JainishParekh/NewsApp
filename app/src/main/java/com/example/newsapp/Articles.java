package com.example.newsapp;

public class Articles {
    private static final String TAG = "Image:";
    private String author;
    private String title;
    private String desc;
    private String url;
    private String urlToImg;
    private String content;

    public Articles(String author, String title, String desc, String url, String urlToImg, String content) {
        this.author = author;
        this.title = title;
        this.desc = desc;
        this.url = url;
        this.urlToImg = urlToImg;
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setUrlToImg(String urlToImg) {
        this.urlToImg = urlToImg;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDesc() {
        return desc;
    }

    public String getUrl() {
        return url;
    }

    public String getUrlToImg() {
//        Log.e(TAG, urlToImg);
        return urlToImg;
    }

    public String getContent() {
        return content;
    }
}
