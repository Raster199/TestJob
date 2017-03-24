import java.io.FileNotFoundException;

/**
 * Страница для записи данных в БД
 */
public class MainPage implements Page {

    public String getHtml() {

        String str = "<!DOCTYPE html>\n" +
                "<html>" +
                "<head>\n" +
                "  <meta charset=\"utf-8\">\n" +
                " </head>" +
                "<ul>\n" +
                "<body>\n"+
                "<h4> Добавление данных в БД  </h4>"+
                "    <form action=\"set\" method=\"post\" >" +
                "    <li>" +
                "       <label for=\"firstName\">Имя</label>\n"  +
                "       <input charset=\"UTF-8\" type=\"text\" name=\"firstName\" required></li>\n" +
                "    <li>" +
                "       <label for=\"lastName\">Фамилия</label>\n" +
                "       <input name=\"lastName\" required></li>\n" +
                "    <li>\n" +
                "       <input type=\"submit\" value=\"Отправить\"></li>\n" +
                "  </ul>\n" +
                "</form>" +

                "</body>\n"+
                "</html>";
        return str;
    }
}
