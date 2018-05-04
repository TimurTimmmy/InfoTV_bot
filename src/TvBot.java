import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TvBot extends TelegramLongPollingBot {
    String token = "555289887:AAGlrPpG8Opm6sgxXKITGDQ4xSlGQKU96nA";

    @Override
    public void onUpdateReceived(Update update) {
        Message msg = update.getMessage();
        String txt = msg.getText();

        switch (txt) {
            case "/start":
                sendMsg(msg, "Добро пожаловать!");
                break;

            case "Первый":
                try {
                    TvInfo perviy = new TvInfo(0);
                    sendMsg(msg, perviy.getTvinfo());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;

            case "Россия 1":
                try {
                    TvInfo wt = new TvInfo(2);
                    sendMsg(msg, wt.getTvinfo());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            default:
                sendMsg(msg, "Используйте клавиатуру");
        }
    }

    @Override
    public String getBotUsername() {
        return "InfoTV_bot";
    }

    @Override
    public String getBotToken() {
        return token;
    }

    private void sendMsg(Message msg, String text) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);

        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);

        replyKeyboardMarkup.setOneTimeKeyboard(false);

        List<KeyboardRow> keyboard = new ArrayList<>();

        KeyboardRow keyboardFirstRow = new KeyboardRow();

        keyboardFirstRow.add("Первый");
        keyboardFirstRow.add("Россия 1");
        keyboardFirstRow.add("ТВ Центр");

        KeyboardRow keyboardSecondRow = new KeyboardRow();

        keyboardSecondRow.add("НТВ");
        keyboardSecondRow.add("ТНТ");
        keyboardSecondRow.add("СТС");

        keyboard.add(keyboardFirstRow);
        keyboard.add(keyboardSecondRow);

        replyKeyboardMarkup.setKeyboard(keyboard);

        sendMessage.setChatId(String.valueOf(msg.getChatId()));
        sendMessage.setText(text);
        try {
            sendMessage(sendMessage);
        } catch (TelegramApiException e){
            e.printStackTrace();
        }
    }
}