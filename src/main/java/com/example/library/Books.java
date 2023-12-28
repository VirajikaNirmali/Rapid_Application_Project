package com.example.library;

public class Books {

    int bookID;
    String bookName;
    String isBookAvailable;

    public Books(int id, String name, String isAvailable){
        this.bookID = id;
        this.bookName = name;
        this.isBookAvailable = isAvailable;
    }

    public int getBookID() {
        return bookID;
    }

    public void setBookID(int id){
        this.bookID = id;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String name){
        this.bookName = name;
    }

    public String getIsBookAvailable() {
        return isBookAvailable;
    }

    public void setIsBookAvailable(String isAvailable){
        this.isBookAvailable = isAvailable;
    }
}
