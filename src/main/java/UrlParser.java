
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.tomcat.jdbc.pool.DataSource;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Этот класс парсит URL и определяет как необходимо обработать запрос
 */
public class UrlParser {
    private static Logger log = LogManager.getLogger(UrlParser.class);

    public UrlParser() {
    }

    private final String baseUrl = "http://localhost:8080/";
    private DataSource ds;
    private HttpServletResponse response;

    public UrlParser(DataSource ds , HttpServletResponse resp) {
        this.response = resp;
        this.ds = ds;
    }

    public String getPage(HttpServletRequest request){


        String url = request.getRequestURL().toString().substring(baseUrl.length());
        String [] parseUrl = url.split("/");

        Connection connIsAuthorize = null;
        try {
        connIsAuthorize = ds.getConnection();
        if ((new JDBC(connIsAuthorize).ifAuthorize(request)) // Проверка на авторизацию пользователя
                || (parseUrl[1].equals("logging")) ) {

            if (parseUrl[1].equals("get")) { // Метод get получает данные из БД и возвращает их в виде json/xml
                response.setContentType("json/xml;charset=UTF-8");
                JDBC jdbc = new JDBC(ds.getConnection());
                return jdbc.get();
            } else if (parseUrl[1].equals("set")) { //Метод set получает данные из POST запроса и передает в БД
                JDBC jdbc = new JDBC(ds.getConnection());
                return jdbc.set(request);
            } else if (parseUrl[1].equals("page")) { // Страница для записи данных в БД
                return new MainPage().getHtml();
            } else if (parseUrl[1].equals("logging")) { // Страница регистрации пользователя для работы с данными приложения
                JDBC jdbc = new JDBC(ds.getConnection());
                return jdbc.logging(request, response );
            }
        }

        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            try {
                if (connIsAuthorize != null) {
                    connIsAuthorize.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return new LoggingPage().getHtml();

    }
}
