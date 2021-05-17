import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;

public class WebServer {
    public static void main(String[] args) throws Exception {
        ServletContextHandler handler = new ServletContextHandler();
        handler.addServlet(UsersServlet.class, "/users/");
        handler.addServlet(LoginServlet.class, "/login/");
        handler.addServlet(LikeServlet.class, "/like/");
        handler.addServlet(ChatServlet.class, "/chat/");

        Server server = new Server(8080);
        server.setHandler(handler);
        server.start();
        server.join();
    }
}
