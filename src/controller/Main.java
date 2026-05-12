package controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

import Exceptions.AuthorNotFoundException;
import dao.AuthorDao;
import dao.BookDao;
import services.BookServices;
import utils.DbConnection;
import utils.ScannerUtil;

public class Main {
    public static void main(String[] args) {

        Scanner sc = ScannerUtil.getScanner();

        while (true) {
            System.out.println("\n+======================================+");
            System.out.println("|      LIBRARY MANAGEMENT SYSTEM       |");
            System.out.println("+======================================+");
            System.out.println("| 1. Author Operations                 |");
            System.out.println("| 2. Book Operations                   |");
            System.out.println("| 3. Exit Program                      |");
            System.out.println("+======================================+");
            System.out.print("\n>> Enter Your Choice: ");

            if (sc.hasNextInt()) {
                int choice = sc.nextInt();
                sc.nextLine();
                if (choice == 3) {
                    System.out.println("\n-------------------------------------------------------------------------");
                    System.out.println("\nThank You For Using Library Management System!\n");
                    System.out.println("-------------------------------------------------------------------------\n");
                    break;
                }
                switch (choice) {
                    case 1:
                        while (true) {
                            System.out.println("\n+======================================+");
                            System.out.println("|             AUTHOR MENU              |");
                            System.out.println("+======================================+");
                            System.out.println("| a. Add Author                        |");
                            System.out.println("| b. Delete Author                     |");
                            System.out.println("| c. Update Author                     |");
                            System.out.println("| d. List All Authors                  |");
                            System.out.println("| e. Exit Author Menu                  |");
                            System.out.println("+======================================+");
                            System.out.print("\n>> Enter Your Option: ");
                            char options = sc.nextLine().charAt(0);
                            if (options == 'e') {
                                System.out.println("\n-------------------------------------------------------------------------");
                                System.out.println("\nExited From Author Menu!\n");
                                System.out.println("-------------------------------------------------------------------------\n");
                                break;
                            }
                            authorCrud(options);
                        }
                        break;

                    case 2:
                        while (true) {
                            System.out.println("\n+======================================+");
                            System.out.println("|             BOOK MENU                |");
                            System.out.println("+======================================+");
                            System.out.println("| a. Add Book                          |");
                            System.out.println("| b. Delete Book                       |");
                            System.out.println("| c. Update Book                       |");
                            System.out.println("| d. List All Book                     |");
                            System.out.println("| e. Exit Book Menu                    |");
                            System.out.println("+======================================+");
                            System.out.print("\n>> Enter Your Option: ");
                            char options = sc.nextLine().charAt(0);
                            if (options == 'e') {
                                System.out.println("\n-------------------------------------------------------------------------");
                                System.out.println("\nExited From Book Menu!\n");
                                System.out.println("-------------------------------------------------------------------------\n");
                                break;
                            }
                            bookCrud(options);
                        }
                        break;

                    default:
                        System.out.println("\n-------------------------------------------------------------------------");
                        System.out.println("\nInvalid Choice! Please try again.\n");
                        System.out.println("-------------------------------------------------------------------------\n");
                }
            } else {
                System.out.println("\n-------------------------------------------------------------------------");
                System.out.println("\nPlease Enter Number Only!\n");
                System.out.println("-------------------------------------------------------------------------\n");

                sc.nextLine(); // buffer clear

            }
        }
    }

    private static void bookCrud(char options) {

        Scanner sc = ScannerUtil.getScanner();
        BookDao bookDao = new BookDao();
        BookServices bookServices = new BookServices(bookDao);

        switch (options) {
            case 'a':
                System.out.println("\n-------------------------------------------------------------------------");
                try {

                    bookServices.addBookService();

                } catch (AuthorNotFoundException e) {

                    System.out.println(e.getMessage());
                }
                System.out.println("-------------------------------------------------------------------------\n");
                break;
            case 'b':
                System.out.println("\n-------------------------------------------------------------------------");
                System.out.print("\n>> Enter Book Name To Be Deleted: ");
                String bookName = sc.nextLine();
                bookServices.deleteBookByNameService(bookName);
                System.out.println("-------------------------------------------------------------------------\n");
                break;
            case 'c':
                System.out.println("\n-------------------------------------------------------------------------");
                bookServices.updateBookService();
                System.out.println("-------------------------------------------------------------------------\n");
                break;
            case 'd':
                System.out.println("\n-------------------------------------------------------------------------");
                bookServices.getAllBookService();
                System.out.println("-------------------------------------------------------------------------\n");
                break;

            default:
                System.out.println("\n-------------------------------------------------------------------------");
                System.out.println("\nInvalid Option! Please try again.\n");
                System.out.println("-------------------------------------------------------------------------\n");
                break;
        }
    }

    public static void authorCrud(char options) {

        Scanner sc = ScannerUtil.getScanner();
        AuthorDao authorDao = new AuthorDao();
        switch (options) {
            case 'a':
                System.out.println("\n-------------------------------------------------------------------------");
                System.out.print("\n>> Enter Author Name: ");
                String input1 = sc.nextLine();

                try (Connection con = DbConnection.getConnection();) {
                    authorDao.addAuthor(input1);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                System.out.println("-------------------------------------------------------------------------\n");
                break;

            case 'b':
                System.out.println("\n-------------------------------------------------------------------------");
                System.out.print("\n>> Enter Author Name To Be Deleted: ");
                String nameEntered = sc.nextLine();
                authorDao.deleteAuthor(nameEntered);
                System.out.println("-------------------------------------------------------------------------\n");
                break;

            case 'c':
                System.out.println("\n-------------------------------------------------------------------------");
                System.out.print("\n>> Enter Previous Name To Be Updated: ");
                String prevName = sc.nextLine();
                System.out.print(">> Enter The New Name: ");
                String updatedName = sc.nextLine();
                authorDao.updateAuthor(prevName, updatedName);
                System.out.println("-------------------------------------------------------------------------\n");
                break;

            case 'd':
                
                authorDao.getAllAuthor();
                
                break;

            default:
                System.out.println("\n-------------------------------------------------------------------------");
                System.out.println("\nInvalid Option! Please try again.\n");
                System.out.println("-------------------------------------------------------------------------\n");
                break;

        }

    }

}
