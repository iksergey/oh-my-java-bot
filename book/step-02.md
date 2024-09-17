# Второй шаг. HTTP-запрос к Telegram Bot Api

Гайд на странице https://core.telegram.org/bots/api#making-requests

Все запросы к Telegram Bot API должны выполняться по HTTPS и должны быть представлены в таком виде: `https://api.telegram.org/bot<token>/METHOD_NAME`. 

## Шаг 1: Подготовка запроса

1. Замените `<token>` в URL на токен вашего бота, который вы получили от `@BotFather`.
2. Выберем метод `getMe` для первого запроса, так как он простой и не требует дополнительных параметров.

## Шаг 2: Выполнение запроса

1. Откройте веб-браузер.
2. Вставьте подготовленный URL в адресную строку (например, `https://api.telegram.org/bot123456:ABC-DEF1234ghIkl-zyx57W2v1u123ew11/getMe`).
3. Нажмите Enter.

## Шаг 3: Анализ ответа

Вы должны увидеть JSON-ответ, похожий на такой:

```json
{
  "ok": true,
  "result": {
    "id": 1234567890,
    "is_bot": true,
    "first_name": "OhMyBot",
    "username": "OhMyJavaBot",
    "can_join_groups": true,
    "can_read_all_group_messages": false,
    "supports_inline_queries": false,
    "can_connect_to_business": false,
    "has_main_web_app": false
  }
}
```

Этот ответ содержит информацию о вашем боте.

## Шаг 4: Изучение документации API

1. Ознакомьтесь с доступными типами данных в API: https://core.telegram.org/bots/api#available-types
2. Изучите список доступных методов: https://core.telegram.org/bots/api#available-methods
3. Обратите особое внимание на методы:
   - Отправка сообщений: https://core.telegram.org/bots/api#sendmessage
   - Получение обновлений (в том числе сообщений): https://core.telegram.org/bots/api#getting-updates

## Шаг 5: Эксперименты с API

1. Отправьте какой-то сообщение вашему боту через телеграм, например, содержащее слово `hello`.
2. Попробуйте выполнить запрос `getUpdates`, чтобы получить последние сообщения, отправленные вашему боту.

Вы должны увидеть JSON-ответ, похожий на такой:

```json
{
  "ok": true,
  "result": [
    {
      "update_id": 6045198,
      "message": {
        "message_id": 2,
        "from": {
          "id": 111222333,
          "is_bot": false,
          "first_name": "Sergei",
          "username": "iksergey",
          "language_code": "ru"
        },
        "chat": {
          "id": 111222333,
          "first_name": "Sergei",
          "username": "iksergey",
          "type": "private"
        },
        "date": 1726605924,
        "text": "hello"
      }
    }
  ]
}
```

Внимательно ознакомьтесь с моделью и ответьте на вопросы:
- Какой тип у поля `result`?
- Изучите документацию и ответьте на второй вопрос: Для чего нужен `update_id`?

2. Используйте метод `sendMessage`, чтобы отправить сообщение через бота (потребуется указать `chat_id` и текст сообщения).

## Важные замечания:

- Всегда используйте HTTPS для запросов к API.
- Храните токен бота в секрете.
- Изучите ограничения и правила использования Bot API.

Теперь вы готовы начать разработку вашего бота на Java, используя эти базовые знания о Telegram Bot API.

[Далее \>](./step-03.md)
