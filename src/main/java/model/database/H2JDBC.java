package model.database;

import java.sql.*;

public class H2JDBC implements IUser{
    private static H2JDBC instance = null;
    private Connection c = null;
    private Statement stmt = null;

    private H2JDBC(){
        try {
            Class.forName("org.h2.Driver");
            c = DriverManager.getConnection("jdbc:h2:mem:kit_bit_test");
            stmt = c.createStatement();
        } catch (Exception e) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
    }

    public static H2JDBC getInstance(){
        if(instance == null)
            instance = new H2JDBC();
        return instance;
    }

    private void createUserTable(){
        try {
            stmt.execute("CREATE TABLE IF NOT EXISTS users" +
                    "(CHAT_ID Int  PRIMARY KEY" +
                    ", GROUP_NAME VARCHAR(255));");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addUser(String chatId, String group) {
        createUserTable();
        try {
            stmt.execute("INSERT INTO users " +
                    "(CHAT_ID,GROUP_NAME) " +
                    "VALUES (\'" + chatId + "\', \'" + group + "\');");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateUser(String user) {

    }

    @Override
    public String readUser(String chatId) {
        try {
            ResultSet rs = stmt.executeQuery("SELECT GROUP_NAME FROM users WHERE CHAT_ID=\'" + chatId + "\';");
            rs.next();
            return rs.getString("GROUP_NAME");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "Error!";
    }
}
