import mysql.connector
from Animals.config import MYSQL_PASSWORD  # Импортируйте пароль из файла конфигурации

# Подключение к базе данных
conn = mysql.connector.connect(
    host='localhost',
    user='root',  # Замените на ваше имя пользователя
    password=MYSQL_PASSWORD,  # Используйте пароль из файла конфигурации
    database='human friends'  # Замените на вашу базу данных
)

cursor = conn.cursor()

# Выполнение запроса
cursor.execute("SELECT * FROM `Human Friends`")

# Получение заголовков столбцов
column_names = [i[0] for i in cursor.description]

# Получение всех строк
rows = cursor.fetchall()

# Запись в файл
with open(r'C:\Users\ildar\OneDrive\Desktop\pitomnik\Animals\Human Friends.txt', 'w', encoding='utf-8') as f:
    # Запись заголовков столбцов
    f.write(', '.join(column_names) + '\n')
    
    # Запись данных
    for row in rows:
        f.write(', '.join(map(str, row)) + '\n')

# Закрытие соединения
cursor.close()
conn.close()
