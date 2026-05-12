package model;

public class Book {

    private int id;
    private String name;
    private String edition;
    private double price;
    private Author author;

    @Override
    public String toString() {
        return "Book [id=" + id + ", name=" + name + ", edition=" + edition +
                ", price=" + price + ", author=" + author + "]";
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Book(String name, String edition, double price, Author author) {
        this.name = name;
        this.edition = edition;
        this.price = price;
        this.author = author;
    }

    public int getBookId() {
        return id;
    }
    
    public void setBookId(int id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public double getBookPrice() {
        return price;
    }
    
    public void setBookPrice(double bookPrice) {
        price = bookPrice;
    }
    
    public String getBookEdition() {
        return edition;
    }
    
    public void setBookEdition(String bookEdition) {
        edition = bookEdition;
    }
  
}