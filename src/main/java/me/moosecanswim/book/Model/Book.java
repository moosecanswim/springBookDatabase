package me.moosecanswim.book.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
public class Book {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private long ID;

    @NotNull
    private String bookSku;

    @NotNull(message="add a Book Title")
    private String bookTitle;

    @NotNull(message="add an Author")
    private String bookAuthor;

    @NotNull(message="add a description")
    private String bookDescription;

    @NotNull(message="add a price")
    @Min(value=1, message="This isn't a charity, add a price greater than $1")
    private double bookPrice;

    public String getBookSku() {
        return bookSku;
    }

    public void setBookSku(String bookSku) {
        this.bookSku = bookSku;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public String getBookDescription() {
        return bookDescription;
    }

    public void setBookDescription(String bookDescription) {
        this.bookDescription = bookDescription;
    }

    public double getBookPrice() {
        return bookPrice;
    }

    public void setBookPrice(double bookPrice) {
        this.bookPrice = bookPrice;
    }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }
}
