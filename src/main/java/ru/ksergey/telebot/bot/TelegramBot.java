package ru.ksergey.telebot.bot;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class TelegramBot extends TelegramLongPollingBot {

    private final String botName;

    public TelegramBot(
            @Value("${bot.token}") String botToken,
            @Value("${bot.username}") String botName) {
        super(botToken);
        this.botName = botName;
    }

    @Override
    public String getBotUsername() {
        return botName;
    }

    @Override
    public void onUpdateReceived(Update update) {
        try {
            if (update.hasMessage() && update.getMessage().hasText()) {
                Long chatId = update.getMessage().getChatId();
                String messageText = update.getMessage().getText();
                String userName = update.getMessage().getFrom().getUserName();

                if (messageText.equals("/start")) {
                    startCommandReceived(chatId, userName);
                    return;
                }

                sendResponseMessage(chatId, "Получено: " + messageText);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void startCommandReceived(long chatId, String userName) {
        String response = "Привет, " + userName + "!\n" +
                "Я могу предоставить базовую информацию\n" +
                "о любой стране мира.\n" +
                "Просто отправьте мне слово на английском (например, Vakanda)";
        sendResponseMessage(chatId, response);
    }

    private void sendResponseMessage(long chatId, String message) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText(message);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            System.out.println(e.getMessage());
        }
    }
}
