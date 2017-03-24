
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by raster on 16.03.17.
 */

@WebServlet("/")
public class MainServlet extends HttpServlet {
    private static Logger log = LogManager.getLogger(MainServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");

        PrintWriter pw = resp.getWriter();
        UrlParser pars = new UrlParser(getDataSourse(), resp);
        pw.print(pars.getPage(req));
        pw.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        UrlParser pars = new UrlParser(getDataSourse(), resp);
        pars.getPage(req);
    }

    private DataSource getDataSourse(){

        //Подключение к базе данных через PoolConnection Tomcat

        PoolProperties p = new PoolProperties();
        p.setUrl( "jdbc:postgresql://127.0.0.1:5432/testJob" );
        p.setDriverClassName( "org.postgresql.Driver" );
        p.setUsername( "postgres" );
        p.setPassword( "postgres" );
        p.setMaxActive(200);
        p.setMaxWait(1000);

        DataSource ds = new org.apache.tomcat.jdbc.pool.DataSource( p );
        ds.setPoolProperties(p);

        return ds;
    }
}
