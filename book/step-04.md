# Четвёртый шаг. Установка библиотеки Jackson и её использование.

Для установки Jackson в ваш проект, добавьте зависимость в файл `pom.xml`

## Модель получения сообщения

Создайте модель для получения сообщений от пользователей

```java
class TelegramUpdate {
    long updateId;
    long fromId;
    String username;
    String text;

    TelegramUpdate(long updateId, long fromId, String username, String text) {
        this.updateId = updateId;
        this.fromId = fromId;
        this.username = username;
        this.text = text;
    }

    @Override
    public String toString() {
        return String.format("Update ID: %d, From ID: %d, Username: %s, Text: %s\n",
                updateId, fromId, username, text);
    }
}
```

Эта модель представляет структуру обновления от Telegram API, содержащую ID обновления, ID отправителя, имя пользователя и текст сообщения.

## Код

```java
class Main {
    public static void main(String[] args) throws Exception {
        String tgUrlRequest = "...";

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest
                .newBuilder()
                .uri(URI.create(tgUrlRequest))
                .build();

        String jsonResponse = client
                .send(request, HttpResponse.BodyHandlers.ofString())
                .body();
        // System.out.println(jsonResponse);

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = objectMapper.readTree(jsonResponse);
        JsonNode resultArray = rootNode.get("result");

        List<TelegramUpdate> updates = new ArrayList<>();

        for (JsonNode updateNode : resultArray) {
            System.out.println("+");
            long updateId = updateNode.get("update_id").asLong();
            JsonNode messageNode = updateNode.get("message");
            JsonNode fromNode = messageNode.get("from");

            long fromId = fromNode.get("id").asLong();
            String username = fromNode.get("username").asText();
            String text = messageNode.get("text").asText();

            updates.add(new TelegramUpdate(updateId, fromId, username, text));
        }

        for (TelegramUpdate update : updates) {
            System.out.println(update);
        }
    }
}
```

Этот код выполняет следующие действия:
1. Отправляет HTTP-запрос к Telegram API для получения обновлений.
2. Получает JSON-ответ и выводит его в консоль.
3. Использует Jackson для парсинга JSON-ответа.
4. Извлекает нужные данные из JSON и создает объекты `TelegramUpdate`.
5. Сохраняет все обновления в список.
6. Выводит обработанные обновления в консоль.

В терминале будет отображаться перечень полученных сообщений

```text
Update ID: 1234567, From ID: 111222333, Username: iksergey, Text: сообщение 1
Update ID: 1234568, From ID: 111222333, Username: iksergey, Text: сообщение 2
```

*Обратите внимание, что в реальном приложении вам следует обрабатывать возможные исключения и проверять наличие полей в JSON, так как некоторые поля могут отсутствовать в зависимости от типа обновления.*

**Если программу перезапустить, то сообщения полученные раньше придут ещё раз. Вопрос: как избавиться от сообщений, полученных ранее?**
