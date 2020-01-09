package model.database;

import java.sql.*;

public class SQLiteJDBC implements IUser, ILesson{
    private static SQLiteJDBC instance = null;
    private Connection c = null;
    private Statement stmt = null;

    private SQLiteJDBC(){
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:kit_bit_test.db");
            stmt = c.createStatement();
        } catch (Exception e) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
    }

    public static SQLiteJDBC getInstance(){
        if(instance == null)
            instance = new SQLiteJDBC();
        return instance;
    }

    public void createLesson(){

    }

    public String readLesson(String groupName, String day){
        if(groupName.equals("КС-19-2/11"))
            return "Расписание для КС-19-2/11";
        else if (groupName.equals("КС-17-1"))
            return "Расписание для КС-17-1";
        return "Неизвестная группа";
    }

    public void updateLesson(){

    }

    public void deleteLesson(){

    }
    public void createTable(){
        try {
            stmt.execute("CREATE TABLE IF NOT EXISTS user" +
                    "(name varchar(100), id integer primary key auto_increment, nickname varchar(100));");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void createUserTable(){
        try {
            stmt.execute("CREATE TABLE IF NOT EXISTS users" +
                    "(CHAT_ID TEXT  PRIMARY KEY     NOT NULL" +
                    ", GROUP_NAME TEXT     NOT NULL);");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addUser(String chatId, String group) {
        createUserTable();
        try {
            stmt.execute("INSERT OR REPLACE INTO users " +
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
