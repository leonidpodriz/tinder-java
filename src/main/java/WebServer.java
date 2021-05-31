import DB.DbProp;
import DB.DbSchema;
import Servlets.ChatServlet;
import Servlets.UsersServlet;
import Servlets.LoginServlet;
import Servlets.LikedServlet;
import Users.UserService;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class WebServer {
    public static void main(String[] args) throws Exception {
        UserService userService = new UserService();
        ServletContextHandler handler = new ServletContextHandler();
        handler.addServlet(new ServletHolder(new LikedServlet(userService)), "/liked");
        handler.addServlet(new ServletHolder(new LoginServlet(userService)), "/login");
        handler.addServlet(new ServletHolder(new UsersServlet(userService)), "/users");
        handler.addServlet(ChatServlet.class, "/chat/");

        new DbSchema(
                DbProp.conn,
                DbProp.user,
                DbProp.pass
        ).migrate();


        Server server = new Server(8080);
        server.setHandler(handler);
        server.start();
        server.join();
    }
}
