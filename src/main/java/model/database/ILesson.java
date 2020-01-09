package model.database;

public interface ILesson {
    void createLesson();
    String readLesson(String groupName, String day);
    void updateLesson();
    void deleteLesson();
}
