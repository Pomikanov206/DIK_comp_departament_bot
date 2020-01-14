package view;

import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

public class TelegramMenuButtons {
    public static synchronized void setCourseSelectButtons(SendMessage message) {
        //Create keyboard
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        message.setReplyMarkup(replyKeyboardMarkup);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);

        //Create keyboard line list
        List<KeyboardRow> keyboard = new ArrayList<KeyboardRow>();

        //First keyboard line
        KeyboardRow keyboardFirstRow = new KeyboardRow();
        //Add buttons in new line
        keyboardFirstRow.add(new KeyboardButton("1 Курс"));
        keyboardFirstRow.add(new KeyboardButton("2 Курс"));

        //Add line to list
        keyboard.add(keyboardFirstRow);


        KeyboardRow keyboardSecondRow = new KeyboardRow();

        keyboardSecondRow.add(new KeyboardButton("3 Курс"));
        keyboardSecondRow.add(new KeyboardButton("4 Курс"));

        keyboard.add(keyboardSecondRow);

        // Set list on keyboard
        replyKeyboardMarkup.setKeyboard(keyboard);
    }


    public static synchronized void setGroupSelectButtons(SendMessage message, String[] groups) {
        //Create keyboard
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        message.setReplyMarkup(replyKeyboardMarkup);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);

        //Create keyboard line list
        List<KeyboardRow> keyboard = new ArrayList<KeyboardRow>();


        for (String groupName:
             groups) {
            KeyboardRow keyboardRow = new KeyboardRow();
            keyboardRow.add(groupName);
            keyboard.add(keyboardRow);
        }

        // Set list on keyboard
        replyKeyboardMarkup.setKeyboard(keyboard);
    }

    public static synchronized void setMainMenuButtons(SendMessage message) {
        //Create keyboard
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        message.setReplyMarkup(replyKeyboardMarkup);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);

        //Create keyboard line list
        List<KeyboardRow> keyboard = new ArrayList<KeyboardRow>();

        //First keyboard line
        KeyboardRow keyboardFirstRow = new KeyboardRow();
        //Add buttons in new line
        keyboardFirstRow.add(new KeyboardButton("Розклад"));

        //Add line to list
        keyboard.add(keyboardFirstRow);


        KeyboardRow keyboardSecondRow = new KeyboardRow();
        keyboardSecondRow.add(new KeyboardButton("Екзамени"));
        keyboardSecondRow.add(new KeyboardButton("Заміни"));
        keyboard.add(keyboardSecondRow);

        KeyboardRow keyboardThirdRow = new KeyboardRow();
        keyboardThirdRow.add(new KeyboardButton("Інше"));
        keyboard.add(keyboardThirdRow);

        // Set list on keyboard
        replyKeyboardMarkup.setKeyboard(keyboard);
    }

    public static synchronized void setOtherMenuBottons(SendMessage message) {

    }

    public static synchronized void setColledheInfoButtons(SendMessage message) {

    }
}
