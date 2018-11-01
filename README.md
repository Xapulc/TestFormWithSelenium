# TestFormWithSelenium
Проект запускает 2 теста для формы, используя Firefox, Chrome или Opera.

Запускать с терминала следующим образом:
mvn test -Dtest=TestCauses -Dbrowser=firefox

Браузер по умолчанию: Chrome

При запуске Opera явно указать абсолютный путь до opera.exe (e.g. как у меня):
mvn test -Dtest=TestCauses -Dbrowser=opera -DoperaBinary=C:\Users\Виктор\AppData\Local\Programs\Opera\56.0.3051.52\opera.exe
