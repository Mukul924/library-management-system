package dao;
import java.sql.*;

import model.Author;
import model.Book;
import utils.DbConnection;


public class BookDao {

    public void addBook(Book bookObject){
        try {
            Connection con = DbConnection.getConnection();
            String insertQuery = "insert into book_tb(name, edition, price, author_id) values(?,?,?,?)";

            PreparedStatement ps =con.prepareStatement(insertQuery);
            ps.setString(1, bookObject.getName());
             ps.setString(2, bookObject.getBookEdition());
              ps.setDouble(3, bookObject.getBookPrice());
               ps.setInt(4, bookObject.getAuthor().getId());

               int rowUpdate = ps.executeUpdate();
               System.out.println("\n-------------------------------------------------------------------------");
               System.out.print("\n[+] ");
               System.out.println(rowUpdate + " Book Added Successfully!\n");
               
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteBookByName(String book_name){
        String sql = "delete from book_tb where name = ?";
        try (Connection con = DbConnection.getConnection()) {

            PreparedStatement pst = con.prepareStatement(sql);

            pst.setString(1, book_name);
            int row = pst.executeUpdate();

            System.out.println("\n-------------------------------------------------------------------------");
            System.out.print("\n[-] ");
            System.out.println(row + " Book Deleted Successfully!\n");
                    
        } catch (Exception e) {
            System.out.println("Exception occured in deleteBookService Block");
            e.printStackTrace();
        }
    }

    public static Book getBookByName(String bookName){
        String sql = "select * from book_tb where name = ?";
        Book retrieveBook = null;
        String bookNameRetrieved = null;
        String bookEditionRetrieved = null;
        double bookPriceRetrieved = 0.0;
        Author authorRetrieved = null;

        try (Connection con = DbConnection.getConnection();
            PreparedStatement preparedStatement = con.prepareStatement(sql)) {
            preparedStatement.setString(1, bookName);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                bookNameRetrieved = resultSet.getString("name");
                bookEditionRetrieved = resultSet.getString("edition");
                bookPriceRetrieved = resultSet.getDouble("price");
                authorRetrieved = (Author)resultSet.getObject("author");
       
            }
          
            retrieveBook = new Book(bookNameRetrieved, bookEditionRetrieved, bookPriceRetrieved, authorRetrieved);
            

        }catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
            return retrieveBook;

    }
    
    public void getAllBook(){
        String query = "select * from book_tb";
        try (
            Connection con = DbConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(query);
        ) {
            ResultSet rs = ps.executeQuery();

            System.out.printf("%-20s %-20s %-20s %-20s%n", "Book-Name", "Book-Price", "Book-Edition", "Author-ID");
            System.out.println("-------------------------------------------------------------------------");

            while (rs.next()) {

                System.out.printf("%-20s %-20.2f %-20s %-20d%n",  
                rs.getString("name"),
                rs.getDouble("price"),
                rs.getString("edition"),
                rs.getInt("author_id"));
                
            }

            System.out.println("-------------------------------------------------------------------------");

            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateBookByName(String bookName, String bookEdition, double bookPrice){
        String query = "update book_tb set edition = ?, price=? where name=?";
        try (
            Connection con = DbConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(query);

        ) {
            ps.setString(1, bookEdition);
            ps.setDouble(2, bookPrice);
            ps.setString(3, bookName);

            int rowUpdated = ps.executeUpdate();
            System.out.println("\n-------------------------------------------------------------------------");
            System.out.print("\n[^] ");
            System.out.println(rowUpdated + " Book Updated Successfully!\n");

            
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
