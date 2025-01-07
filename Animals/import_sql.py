import mysql.connector

# Подключение к базе данных
conn = mysql.connector.connect(
    host='localhost',
    user='your_username',  # Замените на ваше имя пользователя
    password='your_password',  # Замените на ваш пароль
    database='your_database'  # Замените на вашу базу данных
)

cursor = conn.cursor()

# Выполнение запроса
cursor.execute("SELECT * FROM `Human Friends`")

# Получение всех строк
rows = cursor.fetchall()

# Запись в файл
with open(r'C:\Users\ildar\OneDrive\Desktop\pitomnik\Animals\Human Friends.txt', 'w', encoding='utf-8') as f:
    for row in rows:
        f.write(', '.join(map(str, row)) + '\n')

# Закрытие соединения
cursor.close()
conn.close()
