package dao;

import java.sql.*;

import Exceptions.AuthorNotFoundException;
import model.Author;
import utils.DbConnection;

public class AuthorDao {
    public void addAuthor(String author_name) {
        String sql = "insert into author_tb (name) values (?)";
        try (Connection con = DbConnection.getConnection();
                PreparedStatement pst = con.prepareStatement(sql);) {

            pst.setString(1, author_name);
            int row = pst.executeUpdate();
            System.out.println("\n-------------------------------------------------------------------------");
            System.out.print("\n[+] ");
            System.out.println(row + " Author Added Successfully!\n");

        } catch (Exception e) {
            System.out.println("\n-------------------------------------------------------------------------");
            System.out.println("Exception occured in addAuthor Block");
            System.out.println("-------------------------------------------------------------------------\n");
            e.printStackTrace();
        }
    }

    public void getAllAuthor() {
        try (
                Connection con = DbConnection.getConnection();
                Statement st = con.createStatement();) {

            String sql = "select * from author_tb";
            ResultSet rs = st.executeQuery(sql);

            int srNo = 1;

            System.out.println("\n----------------------------------------");
            System.out.printf("%-5s %-20s %-10s%n",
                    "#", "Author-Name", "Author-ID");
            System.out.println("----------------------------------------");

            while (rs.next()) {

                System.out.printf("%-5d %-20s %-10d%n",
                        srNo++,
                        rs.getString("name"),
                        rs.getInt("id"));
            }

            System.out.println("----------------------------------------");
            System.out.println("----------------------------------------\n");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateAuthor(String prevName, String updatedName) {
        String sql = "update author_tb set name = (?) where name = (?)";

        try (
                Connection con = DbConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(sql);) {

            ps.setString(2, prevName);
            ps.setString(1, updatedName);
            int rowUpdated = ps.executeUpdate();
            System.out.println("\n-------------------------------------------------------------------------");
            System.out.print("\n[^] ");
            System.out.println(rowUpdated + " Author Updated Successfully!\n");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteAuthor(String author_name) {
        String sql = "delete from author_tb where name = ?";
        try (Connection con = DbConnection.getConnection()) {

            PreparedStatement pst = con.prepareStatement(sql);

            pst.setString(1, author_name);
            int row = pst.executeUpdate();

            System.out.println("\n-------------------------------------------------------------------------");
            System.out.print("\n[-] ");
            System.out.println(row + " Author Deleted Successfully!\n");

        } catch (Exception e) {
            System.out.println("\n-------------------------------------------------------------------------");
            System.out.println("Exception occured in delAuthor Block");
            System.out.println("-------------------------------------------------------------------------\n");
            e.printStackTrace();
        }
    }

    public static Author getAuthorByName(String authorName) throws AuthorNotFoundException {

        Author retrieveAuthor = null;

        String sql = "select * from author_tb where name = ?";

        try (Connection con = DbConnection.getConnection();
                PreparedStatement preparedStatement = con.prepareStatement(sql);) {
            preparedStatement.setString(1, authorName);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {

                int authorId = resultSet.getInt("id");
                String nameRetrieved = resultSet.getString("name");

                retrieveAuthor = new Author(nameRetrieved, authorId);

            } else {

                System.out.println("\n-------------------------------------------------------------------------");
                throw new AuthorNotFoundException("\n[?] Author Not Found!\n");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return retrieveAuthor;
    }

}
