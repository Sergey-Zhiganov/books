package com.example.bd;

public class Book {
    private int ID_Book;
    private String Name;
    private String Author;

    public Book(int ID_Book, String Name, String Author) {
        this.ID_Book = ID_Book;
        this.Name = Name;
        this.Author = Author;
    }

    public int getID_Book() {
        return ID_Book;
    }

    public String getName() {
        return Name;
    }

    public String getAuthor() {
        return Author;
    }

    public void setID_Book(int ID_Book) {
        this.ID_Book = ID_Book;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public void setAuthor(String Author) {
        this.Author = Author;
    }
}
