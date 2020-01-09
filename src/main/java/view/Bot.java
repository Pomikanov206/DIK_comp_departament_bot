package view;

import controller.BotController;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Bot extends TelegramLongPollingBot {
    private BotController controller = null;
    public Bot() {
        controller = new BotController(this);
    }

    /**
     * Метод для приема сообщений.
     * @param update Содержит сообщение от пользователя.
     */
    @Override
    public void onUpdateReceived(Update update) {
        String message = update.getMessage().getText();


        controller.commandHandler(update.getMessage().getChatId().toString(), message);
        System.out.println(message);
        /*
        if(message.toLowerCase().equals("/start"))
            sendCoursePick(update.getMessage().getChatId().toString());
        */
        //sendMsg(update.getMessage().getChatId().toString(), message);
    }

    /**
     * Метод для настройки сообщения и его отправки.
     * @param chatId id чата
     * @param message Строка, которую необходимот отправить в качестве сообщения.
     */
    public synchronized void sendMsg(String chatId, String message) {
        SendMessage sendMessage = new SendMessage();

        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(chatId);
        sendMessage.setText(message);
        try {
            sendMessage(sendMessage);
        } catch (TelegramApiException e) {
            Logger log = null;
            log.log(Level.SEVERE, "Exception: ", e.toString());
        }
    }

    /**
     * Метод для настройки и отправки клавиатуры курса.
     * @param chatId id чата
     */
    public synchronized void sendCoursePick(String chatId) {
        SendMessage sendMessage = new SendMessage();

        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(chatId);
        TelegramMenuButtons.setCourseSelectButtons(sendMessage);
        sendMessage.setText("Выберите курс.");
        try {
            sendMessage(sendMessage);
        } catch (TelegramApiException e) {
            Logger log = null;
            log.log(Level.SEVERE, "Exception: ", e.toString());
        }
    }

    /**
     * Метод для настройки и выбора клавиатуры группы.
     * @param chatId id чата
     */
    public synchronized void sendGroupPick(String chatId, String[] groupList) {
        SendMessage sendMessage = new SendMessage();

        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(chatId);
        TelegramMenuButtons.setGroupSelectButtons(sendMessage, groupList);
        sendMessage.setText("Выберите группу:");
        try {
            sendMessage(sendMessage);
        } catch (TelegramApiException e) {
            Logger log = null;
            log.log(Level.SEVERE, "Exception: ", e.toString());
        }
    }

    public synchronized void sendMenuPick(String chatId) {

    }

    public synchronized void sendOtherPick(String chatId) {

    }

    public synchronized void sendTimeTable(String chatId, String timeTable) {
        sendMsg(chatId,timeTable);
        System.out.println("Send TimeTable");
    }

    public synchronized void sendChanges(String chatId, String changesList) {
        sendMsg(chatId,changesList);
    }

    public synchronized void sendExams(String chatId, String examsList) {
        sendMsg(chatId,examsList);
    }

    /**
     * Метод возвращает имя бота, указанное при регистрации.
     * @return имя бота
     */
    @Override
    public String getBotUsername() {
        return "comp_int_tech_dik_bot";
    }

    /**
     * Метод возвращает token бота для связи с сервером Telegram
     * @return token для бота
     */
    @Override
    public String getBotToken() {
        return "961764432:AAGTjCRAoA6uWGQQvTeW_OCCJULyqWuKqcU";
    }
}