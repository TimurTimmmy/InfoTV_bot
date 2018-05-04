import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.exceptions.TelegramApiException;

public class StartBot{
    public static void main(String[] args) {
        TelegramBotsApi newBotApi = new TelegramBotsApi();
        ApiContextInitializer.init();
        try {
            newBotApi.registerBot(new TvBot());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
