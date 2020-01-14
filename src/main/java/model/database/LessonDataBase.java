package model.database;

public class LessonDataBase implements ILesson{
    @Override
    public void createLesson() {

    }

    @Override
    public String readLesson(String groupName, String day) {
        if(groupName.equals("КС-19-2/11"))
            return "Расписание для КС-19-2/11";
        else if (groupName.equals("КС-17-1"))
            return "Расписание для КС-17-1";
        return "Неизвестная группа";
    }

    @Override
    public void updateLesson() {

    }

    @Override
    public void deleteLesson() {

    }
}
