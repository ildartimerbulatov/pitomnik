import mysql.connector
import csv
from config import MYSQL_PASSWORD  # Импортируйте пароль из файла конфигурации

try:
    # Подключение к базе данных
    conn = mysql.connector.connect(
        host='localhost',
        user='root',  # Замените на ваше имя пользователя
        password=MYSQL_PASSWORD,  # Используйте пароль из файла конфигурации
        database='animalshelter'  # Замените на вашу базу данных
    )

    cursor = conn.cursor()

    # Выполнение запроса к таблице human_friends
    cursor.execute("SELECT * FROM `human_friends`")

    # Получение заголовков столбцов
    column_names = [i[0] for i in cursor.description]

    # Получение всех строк
    rows = cursor.fetchall()

    # Запись в CSV файл
    with open(r'C:\Users\ildar\OneDrive\Desktop\pitomnik\Animals\Human_Friends.csv', 'w', newline='', encoding='utf-8') as f:
        csv_writer = csv.writer(f)
        
        # Запись заголовков столбцов
        csv_writer.writerow(column_names)
        
        # Запись данных
        csv_writer.writerows(rows)

except mysql.connector.Error as err:
    print(f"Ошибка: {err}")
finally:
    # Закрытие соединения
    if cursor:
        cursor.close()
    if conn:
        conn.close()
