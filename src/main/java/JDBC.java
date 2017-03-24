
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс выполняет манипуляцию данными из БД
 */
public class JDBC {
    private static Logger log = LogManager.getLogger(JDBC.class);
    private Connection connection = null;

    JDBC(){
    }

    public JDBC(Connection connection) {
        this.connection = connection;
    }

    public String get(){ // Метод get получает данные из БД и возвращает их в виде json/xml
        ObjectMapper objectMapper = new ObjectMapper();

        Statement statement = null;
        ResultSet resultSet = null;

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM staff");
            List<String> result = new ArrayList<String>();

            while (resultSet.next()) {
                String key = resultSet.getString("firstName");
                String value = resultSet.getString("lastName");
                User user = new User(key,value); //
                result.add(objectMapper.writeValueAsString(user));
            }

            return result.toString();

        }catch (SQLException e){
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }


    public String set(HttpServletRequest request){ // Метод set получает данные из POST запроса и передает в БД

        PreparedStatement pstmt = null;

        try {
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            pstmt = connection.prepareStatement(
                    "INSERT INTO staff (firstname, lastname) VALUES (?, ?) "
            );
            pstmt.setString(1,firstName);
            pstmt.setString(2,lastName);
            pstmt.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
                } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public String logging(HttpServletRequest request, HttpServletResponse response){

        PreparedStatement pstmt = null;

        try {
            if (ifAuthorize(request)){ // Если авторизован, то отдает куки с паролем для работы с приложением
                String pass = request.getParameter("pass");
                String md5Pass = DigestUtils.md5Hex(pass);
                Cookie cookie = new Cookie("password", md5Pass);
                response.addCookie(cookie);
            }else { // не авторизован, то запись логина и пароля в базу и выдается куки для работы с приложением
                String login  = request.getParameter("login");
                String pass = request.getParameter("pass");
                String md5Pass = DigestUtils.md5Hex(pass);

                pstmt = connection.prepareStatement(
                        "INSERT INTO users (login, pass) VALUES (?, ?) "
                );
                pstmt.setString(1, login);
                pstmt.setString(2, md5Pass);
                pstmt.executeUpdate();

                Cookie cookie = new Cookie("password", md5Pass);
                response.addCookie(cookie);
            }

        }catch (SQLException e){
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public boolean ifAuthorize(HttpServletRequest request){ //Метод выполняет проверку на авторизацию пользователя

        Cookie [] cookie = request.getCookies();
        String passParam = request.getParameter("pass");
        String passInRequest = "";
        if (passParam != null){
            passInRequest = DigestUtils.md5Hex(passParam);
        }

        String passInCookie = "";

        if (cookie != null) {
            passInCookie = cookie[0].getValue();
        }

        Statement statement = null;
        ResultSet resultSet = null;

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM users");

            while (resultSet.next()) {
                String pass = resultSet.getString("pass");
                if ((pass.equals(passInCookie)) | pass.equals(passInRequest)){
                    return true;
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }



}
