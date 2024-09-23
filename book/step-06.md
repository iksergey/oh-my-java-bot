## Техническое задание на создание игрового бота

### Общее описание

Разработать многопользовательского телеграм-бота для игры в числа с использованием Java Spring Boot. Бот должен поддерживать одновременную игру множества пользователей, иметь различные уровни сложности и использовать элементы искусственного интеллекта для принятия решений.

### Функциональные требования

**1. Инициализация игры**

- При получении команды /start бот генерирует случайное целое число от 1 до N (N - настраиваемый параметр).
- Бот отправляет сгенерированное число пользователю.

**2. Игровой процесс**

- Пользователь и бот по очереди отправляют случайное число от 1 до M (M - настраиваемый параметр).
- Отправленное число вычитается из текущего значения.
- Игра продолжается до тех пор, пока текущее значение не станет равным нулю.
- Побеждает тот, кто своим ходом обнуляет число.

**3. Многопользовательский режим**

- Бот должен поддерживать одновременную игру с несколькими пользователями.
- Каждая игра должна быть независимой и иметь свое состояние.

**4. Уровни сложности**

- Реализовать три уровня сложности: легкий, средний и сложный.
- Уровень сложности влияет на стратегию бота и вероятность его победы.

**5. Искусственный интеллект**

- Разработать алгоритм принятия решений для бота на основе текущего состояния игры.
- Алгоритм должен учитывать уровень сложности и стремиться к победе.

### Алгоритм работы ИИ

1. Для легкого уровня: бот выбирает случайное число от 1 до M.
2. Для среднего уровня: бот анализирует текущее значение и выбирает число, которое не позволит пользователю выиграть следующим ходом.
3. Для сложного уровня: бот использует стратегию выигрыша, основанную на теории чисел и модульной арифметике.

### Дополнительные требования

1. Реализовать механизм сохранения и восстановления игровых сессий при перезапуске бота.
2. Добавить систему рейтинга пользователей на основе их побед и поражений.
3. Реализовать команду /help для вывода правил игры и доступных команд.
4. Добавить возможность настройки параметров N и M для каждой игровой сессии.

### Этапы разработки

1. Настройка проекта и подключение необходимых зависимостей.
2. Реализация базовой структуры бота и обработки команды /start.
3. Разработка основной игровой логики и многопользовательского режима.
4. Реализация различных уровней сложности и алгоритмов ИИ.
5. Интеграция с базой данных для хранения игровых сессий и пользователей.

6. *Документирование кода и API.*
7. \* *Развертывание и тестирование в продакшн-окружении.*
8. *Написание unit-тестов и интеграционных тестов.*
