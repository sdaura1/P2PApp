package com.example.shaheed.p2papp;

/**
 * Created by shaheed on 4/26/18.
 */

public class ArticleClass {

    String article, author;

    public ArticleClass(String quote, String author) {
        this.article = quote;
        this.author = author;
    }

    public ArticleClass(){

    }

    public String getArticle() {
        return article;
    }

    public void setArticle(String quote) {this.article = quote; }

    public String getAuthor() { return author; }

    public void setAuthor(String author) {
        this.author = author;
    }
}
