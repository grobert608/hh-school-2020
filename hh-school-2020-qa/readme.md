# Привет, это ДЗ по тестированию)

Конфигурация запуска из папки `code`:
* `pytest -s -l -v tests`
* `--token=dummy_token` (вставляем токен)
* `-n=3` (пишем число - запускаем параллельно)
* `--alluredir=dummy_dir` (вставляем дирректорию для отчета алюра)

Показать алюр отчет `allure serve dummy_dir`