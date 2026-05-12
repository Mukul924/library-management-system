package services;

import java.util.Scanner;

import dao.AuthorDao;
import dao.BookDao;
import model.Author;
import model.Book;
import utils.ScannerUtil;

public class BookServices {
    private BookDao bookDao;

public BookServices(BookDao bookDao){
        this.bookDao = bookDao;
    }

public void addBookService(){
    Scanner sc = ScannerUtil.getScanner();

    System.out.print("\n>> Enter Book Name: ");
    String name = sc.nextLine();

    
    System.out.print("\n>> Enter Book Edition: ");
    String edition = sc.nextLine();

    
    System.out.print("\n>> Enter Book Price: ");
    double price = sc.nextDouble();
    sc.nextLine();
    
    System.out.print("\n>> Enter Book Author Name: ");
    String authorName= sc.nextLine();

    Author author = AuthorDao.getAuthorByName(authorName);

    Book newBook = new Book(name, edition, price, author);
    bookDao.addBook(newBook);
    
}

public void getBookByNameService(String bookName){
    Book retrived = BookDao.getBookByName(bookName);
    System.out.println("Book in library" + retrived);

}

public void deleteBookByNameService(String bookName){
    bookDao.deleteBookByName(bookName);
}

public void getAllBookService(){
    bookDao.getAllBook();
}

public void updateBookService(){
    String bookName, bookEdition;
    double bookPrice;
    Scanner sc = ScannerUtil.getScanner();
    System.out.print("\n>> Enter Book Name To Be Updated: ");
    bookName = sc.nextLine();

    System.out.print("\n>> Enter Book Edition To Be Updated: ");
    bookEdition = sc.nextLine();

    System.out.print("\n>> Enter Book Price To Be Updated: ");
    bookPrice = sc.nextDouble();
    sc.nextLine();

    bookDao.updateBookByName(bookName,bookEdition,bookPrice);

}

}
