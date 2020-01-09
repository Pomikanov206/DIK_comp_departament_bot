package model.database;

public interface IUser {
    void addUser(String chatId, String group);
    void updateUser(String user);
    String readUser(String chatId);
}
