# Третий шаг. Инструкция по выполнению запроса к Telegram Bot API на Java

### Шаг 1: Создание нового проекта
Создайте новый Java-проект в вашей среде разработки.

### Шаг 2: Написание кода
Создайте новый класс `Program` и вставьте следующий код:

```java
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Program {

    public static void main(String[] args) throws IOException, InterruptedException {

        String tgUrlRequest = "https://api.telegram.org/bot123456:ABC-DEF1234ghIkl-zyx57W2v1u123ew11/getupdates";
        
        HttpClient client = HttpClient.newHttpClient();
        
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(tgUrlRequest)).build();
        
        String response = client.send(request, HttpResponse.BodyHandlers.ofString()).body();
        System.out.println(response);
    }
}
```

### Шаг 3: Пояснения по коду

1. **Определение URL**:
   ```java
   String tgUrlRequest = "https://api.telegram.org/bot123456:ABC-DEF1234ghIkl-zyx57W2v1u123ew11/getupdates";
   ```
   Замените `123456:ABC-DEF1234ghIkl-zyx57W2v1u123ew11` на ваш реальный токен бота.

2. **Создание HTTP-клиента**:
   ```java
   HttpClient client = HttpClient.newHttpClient();
   ```
   Создаем новый экземпляр HTTP-клиента для отправки запросов.

3. **Создание HTTP-запроса**:
   ```java
   HttpRequest request = HttpRequest.newBuilder().uri(URI.create(tgUrlRequest)).build();
   ```
   Формируем GET-запрос к указанному URL.

4. **Отправка запроса и получение ответа**:
   ```java
   String response = client.send(request, HttpResponse.BodyHandlers.ofString()).body();
   ```
   Отправляем запрос и получаем ответ в виде строки.

5. **Вывод результата**:
   ```java
   System.out.println(response);
   ```
   Выводим полученный JSON-ответ в консоль.

### Шаг 4: Запуск программы
Запустите программу. В консоли вы увидите JSON-ответ от Telegram API, содержащий набор сообщений (обновлений) для вашего бота.

### Шаг 5: Анализ результата
Полученный JSON-ответ будет содержать информацию о последних сообщениях, отправленных вашему боту. Изучите структуру этого ответа, чтобы понять, как извлекать нужную информацию в будущем.

### Важные замечания:
- Убедитесь, что вы заменили URL в переменной `tgUrlRequest` на актуальный URL с вашим токеном бота.
- Этот код использует `HttpClient` из Java 11+.
- Обработка исключений в этом примере минимальна. В реальном приложении следует добавить более robust обработку ошибок.

[Далее \>](./step-04.md)
