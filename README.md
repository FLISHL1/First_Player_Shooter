# First_Player_Shooter 🎮

![GitHub repo size](https://img.shields.io/github/repo-size/FLISHL1/First_Player_Shooter?style=flat-square)
![GitHub issues](https://img.shields.io/github/issues/FLISHL1/First_Player_Shooter?style=flat-square)

## 📜 Описание

**First_Player_Shooter** — это базовый проект бродилки от первого лица, написанный на **Java**. В игре реализованы базовые механики, такие как движение персонажа. Проект предоставляет возможность дальнейшей модификации и разработки более сложных механик.

---

## 🚀 Возможности

- Движение персонажа в стиле FPS (First Person Shooter)
- Простая архитектура для изучения и расширения

---

## 📂 Структура проекта

```plaintext
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   ├── ru.flish1.fps_game/
│   │   │   │   ├── FpsGameApplication.java          # Точка входа в приложение
│   │   │   │   ├── entity/         # Реализация игровых объектов
│   │   │   │   ├── exception/      # Кастомные ошибки
│   │   │   │   ├── gameThread/        # Логика движения и отрисовки
│   │   │   │   ├── view/              # Интерфейс            
│   │   └── resources/                # Ресурсы игры (например, текстуры и конфигурации)
├── .gitignore                         # Исключения для Git
├── pom.xml                            # Зависимости и настройки Maven
└── README.md                          # Документация проекта
```

---

## 🛠 Технологии

- **Java**: основной язык разработки
- **Maven**: управление зависимостями
- **Lanterna**: для эмуляции консоли

---

## 🏁 Как запустить проект

### 1. Клонирование репозитория

```bash
git clone https://github.com/FLISHL1/First_Player_Shooter.git
cd First_Player_Shooter
```

### 2. Сборка проекта

Убедитесь, что у вас установлен **Maven**:

```bash
mvn clean install
```

### 3. Запуск приложения

Запустите приложение с помощью команды:

```bash
java -jar target/First_Player_Shooter-1.0-SNAPSHOT.jar
```

---

## ✅ Уже сделано

- [x] Реализовано базовое движение персонажа
- [x] Создана структура для изменения пользовательской карты

---

## 📝 Задачи на будущее

- [ ] ⚙️ Добавить отрисовку углов
- [ ] 🎨 Добавить текстуры
- [ ] 🐛 Оптимизировать производительность для больших карт

---

