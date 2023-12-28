package com.example.library;

public class Librarian {
    int LibrarianID;
    String LibrarianName;
    String LibrarianEmail;

    public Librarian(int id, String name, String email){
        this.LibrarianID = id;
        this.LibrarianName = name;
        this.LibrarianEmail = email;
    }

    public int getLibrarianID() {
        return LibrarianID;
    }

    public void setLibrarianID(int librarianID) {
        LibrarianID = librarianID;
    }

    public String getLibrarianName() {
        return LibrarianName;
    }

    public void setLibrarianName(String librarianName) {
        LibrarianName = librarianName;
    }

    public String getLibrarianEmail() {
        return LibrarianEmail;
    }

    public void setLibrarianEmail(String librarianEmail) {
        LibrarianEmail = librarianEmail;
    }

}
