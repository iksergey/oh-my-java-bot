# Инструкция по созданию эхо-бота 

## Фонового сервис на Java Spring Boot

### Шаг 1: Создание нового проекта Spring Boot

1. Откройте Spring Initializr (https://start.spring.io/)
2. Выберите:
   - Project: Maven
   - Language: Java
   - Spring Boot: последняя стабильная версия
   - Group: ru.ksergey
   - Artifact: echo-telegram-bot
   - Packaging: Jar
   - Java: 17 (или новее)
   - Package name: ru.ksergey.telebot 

3. Добавьте следующие зависимости:
   - Spring Web
   - Spring Boot DevTools


4. Нажмите "Generate" и распакуйте полученный архив

### Шаг 2: Добавление зависимостей

Откройте файл `pom.xml` и добавьте следующую зависимость:

```xml
<!-- https://mvnrepository.com/artifact/org.telegram/telegrambots-spring-boot-starter -->
<dependency>
    <groupId>org.telegram</groupId>
    <artifactId>telegrambots-spring-boot-starter</artifactId>
    <version>6.9.7.1</version>
</dependency>
```

### Шаг 3: Настройка приложения

Откройте файл `application.properties` и добавьте:

```
bot.token=YOUR_BOT_TOKEN
bot.username=YOUR_BOT_USERNAME
```

Замените `YOUR_BOT_TOKEN` и `YOUR_BOT_USERNAME` на реальные данные вашего бота.

### Шаг 4: Создание класса бота

Создайте новый класс `TelegramBot.java` в `ru.ksergey.telebot.bot`:

```java
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
```

### Шаг 5. Конфигурация приложения

Создайте новый класс `BotInitializer.java` в `ru.ksergey.telebot.config`:

```java
@Component
public class BotInitializer {
    @Autowired
    TelegramBot bot;

    @EventListener({ContextRefreshedEvent.class})
    public void init() throws TelegramApiException {
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
        try {
            telegramBotsApi.registerBot(bot);
        } catch (TelegramApiException e) {
            System.out.println(e.getMessage());
        }
    }
}
```

1. Аннотация `@EventListener({ContextRefreshedEvent.class})` гарантирует, что метод `init()` будет вызван после того, как Spring полностью инициализирует контекст приложения.

2. `DefaultBotSession.class` используется для создания стандартной сессии бота.


### Шаг 6: Запуск приложения

Запустите приложение и отправьте боту сообщение `/start`

Эта инструкция создаст простого эхо-бота на Spring Boot. Для расширения функциональности вы можете добавить обработку различных типов сообщений (фото, документы и т.д.), реализовать взаимодействие с базой данных или внешними API.
