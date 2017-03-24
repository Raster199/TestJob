/**
 * Страница регистрации пользователя для работы с данными приложения
 */
public class LoggingPage implements Page {

    public String getHtml() {
        String str = "<!DOCTYPE html>\n" +
                "<html><ul>\n" +
                "<head>" +
                "  <meta charset=\"utf-8\">\n" +
                " </head>" +
                "<ul>\n" +
                "<body>\n"+
                "<h4> Регистрация для работы с данными  </h4>"+
                "    <form action=\"logging\" method=\"post\">" +
                "    <li>" +
                "       <label for=\"username\">Login</label>\n"  +
                "       <input charset=\"UTF-8\" type=\"text\" name=\"login\" placeholder=\"Введите логин\" required></li>\n" +
                "    <li>" +
                "       <label for=\"password\">Password</label>\n" +
                "       <input type=\"password\" name=\"pass\" placeholder=\"Введите пароль\" required></li>\n" +
                "    <li>\n" +
                "       <input type=\"submit\" value=\"Login\"></li>\n" +
                "  </ul>\n" +
                "</form>" +
                "</html>";
        return str;
    }
}
