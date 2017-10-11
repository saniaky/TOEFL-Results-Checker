package notification;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.TelegramBotAdapter;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.ParseMode;
import com.pengrad.telegrambot.model.request.ReplyKeyboardRemove;
import com.pengrad.telegrambot.request.GetUpdates;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.GetUpdatesResponse;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Alexander Kohonovsky
 * @since 10.10.2017
 */
class TelegramNotification implements NotificationService {

    private static final String BOT_TOKEN = "426384021:AAEPFBGWRmM7q-CKo78cKPMNJyh8cDK4g6Y";

    private final TelegramBot bot;
    private final Set<Long> chatIds;
    private final Long meId = 2126468L;

    public TelegramNotification() {
        bot = TelegramBotAdapter.build(BOT_TOKEN);
        chatIds = new HashSet<>();
    }

    @Override
    public void sendNotification(String text, NotificationType type) {
        GetUpdates getUpdates = new GetUpdates().limit(100).offset(0).timeout(0);
        GetUpdatesResponse response = bot.execute(getUpdates);

        for (Update update : response.updates()) {
            Message message = update.message();
            if (message == null) {
                message = update.editedMessage();
            }
            chatIds.add(message.chat().id());
        }

        for (Long chatId : chatIds) {
            SendMessage request = new SendMessage(chatId, text)
                    .parseMode(ParseMode.HTML)
                    .disableWebPagePreview(true)
                    .disableNotification(false)
                    .replyToMessageId(0)
                    .replyMarkup(new ReplyKeyboardRemove());

            bot.execute(request);
        }
    }

}
