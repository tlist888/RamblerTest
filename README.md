# RamblerTest

### Программа проверяет страницу https://www.rambler.ru/
### Используемые технологии: Selenium, Firefox, JUnit.

Класс RamblerHomePage - инкапсулирует работу с отдельными элементами страницы rambler.ru.

Класс SectionData - содержит данные для проверки разделов на сайте.

Класс Test - содержит следующие тесты:

- [x] checkTitle() - программа заходит в разделы сайта и делает сверку заголовка вкладки с данными из класса SectionData.

- [x] checkCorrectSearchQuery() - проверка поисковой строки, путем ввода запроса "политика". Если найдены результаты для этого запроса, то на странице должно отобразиться "Надено N статей по вашему запросу". Тест пройден успешно если послe поиска на странице найден текст "статей по вашему запросу".
    
- [x] checkUncorrectSearchQuery() - проверка поисковой строки, путем ввода запроса "пАлитика". Рамблер захочет поправить запрос и выведет: "Возможно, вы искали:". Тест пройден успешно после нахождения этой фразы.
    
- [x] checkGarbageSearchQuery() - проверка поисковой строки, путем ввода запроса "+ХЖЭЪХЮ_№Й)ШК ЗЦЩУОЕЭ()№;ЬХСЗУЩОП УЦЗЩ". Рамблер ничего не найдет и выведет: "Искомая комбинация слов нигде не встречается". Тест пройден успешно после нахождения этой фразы.




