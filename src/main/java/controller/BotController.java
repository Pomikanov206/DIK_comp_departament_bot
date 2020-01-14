package controller;
import model.Timetable;
import model.database.*;
import view.Bot;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BotController {
    private Timetable timetable;
    private Bot view;
    //private SQLiteJDBC dataBase;
    private IUser userDataBase;
    private ILesson lessonDataBase;

    public BotController(Bot view) {
        this.view = view;
        //dataBase = SQLiteJDBC.getInstance();
        userDataBase = H2JDBC.getInstance();
        //lessonDataBase = SQLiteJDBC.getInstance();
        lessonDataBase = new LessonDataBase();
    }

    public void commandHandler(String chatId, String command) {
        String courseRegex = "\\d Курс";
        Pattern pattern = Pattern.compile(courseRegex);
        //  \\w{2}-\\d{2}-\\d/?(\\d{2})?
        Matcher matcher = pattern.matcher(command);

        if(command.equals("/start"))
            view.sendCoursePick(chatId);
        else if(matcher.matches()) {
            // ------- !!!!!!!!!!!!!!!!!!!!!!_______--
            pattern = Pattern.compile("\\d");
            matcher = pattern.matcher(command);

            matcher.find();

            int courseNum = Integer.parseInt(matcher.group());

            //Добавить список групп из внешнего источника
            String[] groupList;

            switch (courseNum) {
                case 1: groupList = new String[]{"КС-19-1", "ЕА-19-1"};break;
                case 2: groupList = new String[]{"КС-18-1", "КС-19-2/11", "ЕА-18-1"};break;
                case 3: groupList = new String[]{"КС-17-1", "КС-18-2/11", "ЕА-17-1"};break;
                case 4: groupList = new String[]{"КС-16-1", "КС-17-2/11", "ЕА-16-1"};break;
                default: groupList = new String[]{"КС-18-1", "КС-19-2/11", "ЕА-18-1"};
            }
            view.sendGroupPick(chatId, groupList);
        }

        String groupRegex = "\\D{2}-\\d{2}-\\d/?(\\d{2})?";
        pattern = Pattern.compile(groupRegex);
        //  \\w{2}-\\d{2}-\\d/?(\\d{2})?
        matcher = pattern.matcher(command);

        if (matcher.matches()){         //Допилить маску надо бы
            userDataBase.addUser(chatId, command);
            System.out.println("User Added");
            view.sendMenuPick(chatId);
        }

        //Main menu
        if(command.equals("Розклад")) {
            System.out.println("Группа: " + userDataBase.readUser(chatId));
            String tibeTable = lessonDataBase.readLesson(userDataBase.readUser(chatId),null);
            view.sendTimeTable(chatId, tibeTable);
        }
        else if(command.equals("Экзамены"))
            view.sendExams(chatId,"Exams 1.0");
        else if(command.equals("Замены"))
            view.sendChanges(chatId, "Changes 1.0");
        else if(command.equals("Другое"))
            view.sendOtherPick(chatId);

        //Other menu
        if(command.equals("Консультации"))
            ;
        else if(command.equals("Кружки"))
            ;
        else if(command.equals("О колледже"))
            ;

        //About college menu
        if(command.equals("Информация"))
            ;
        else if(command.equals("Местоположение"))
            ;
    }

    public void addLesson(){
        lessonDataBase.createLesson();
    }
    public String getTimeTable() {
        return "TimeTable";
    }

}
