package com.example.shaheed.p2papp;

/**
 * Created by shaheed on 3/29/18.
 */

public class QuotesClass {

    String quote, author;

    public QuotesClass(String quote, String author) {
        this.quote = quote;
        this.author = author;
    }

    public QuotesClass(){

    }

    public String getquote() {
        return quote;
    }

    public void setquote(String quote) {this.quote = quote; }

    public String getauthor() {
        return author;
    }

    public void setauthor(String author) {
        this.author = author;
    }
}
