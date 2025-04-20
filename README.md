# Main
This is a backend for game [Minesweeper](https://minesweeper-test.studiotg.ru/)

Specification: https://minesweeper-test.studiotg.ru/swagger/

Value for **URL API**: http://localhost:8080/api/v1

# Docker:
* docker build -t minesweeper:1.0 .
* docker run --name minesweeper -d -p 8080:8080 -t minesweeper:1.0