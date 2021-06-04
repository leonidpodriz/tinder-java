package Tinder;

import Tinder.DB.DbProp;
import Tinder.DB.DbSchema;
import Tinder.Servlets.*;
import Tinder.Users.UserService;
import Tinder.Utils.CookieFilter;
import Tinder.Utils.HerokuEnv;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.FilterHolder;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import javax.servlet.DispatcherType;
import java.util.EnumSet;

public class WebServer {
    public static void main(String[] args) throws Exception {
        UserService userService = new UserService();
        ServletContextHandler handler = new ServletContextHandler();
        handler.addServlet(RedirectServlet.class, "/");
        handler.addServlet(new ServletHolder(new LikedServlet(userService)), "/liked");
        handler.addServlet(new ServletHolder(new LoginServlet(userService)), "/login");
        handler.addServlet(new ServletHolder(new UsersServlet(userService)), "/users");
        handler.addServlet(new ServletHolder(new ChatServlet(userService)), "/chat/");
        handler.addFilter(new FilterHolder(new CookieFilter(userService)), "/users", EnumSet.of(DispatcherType.REQUEST));
        handler.addFilter(new FilterHolder(new CookieFilter(userService)), "/liked",EnumSet.of(DispatcherType.REQUEST));
        handler.addFilter(new FilterHolder(new CookieFilter(userService)), "/messages/",EnumSet.of(DispatcherType.REQUEST));

        int PORT = System.getenv("TYPE") != null && System.getenv("TYPE").equals("PROD") ? HerokuEnv.port() : 8080;
        Server server = new Server(PORT);

        new DbSchema(
                DbProp.conn,
                DbProp.user,
                DbProp.pass
        ).migrate();


        server.setHandler(handler);
        server.start();
        server.join();
    }
}
