import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;

public class WebServer {
    public static void main(String[] args) throws Exception {
        ServletContextHandler handler = new ServletContextHandler();
        handler.addServlet(HomepageServlet.class, "/");
//        handler.addServlet(PageNotFoundServlet.class, "/*");

        Server server = new Server(8080);
        server.setHandler(handler);
        server.start();
        server.join();
    }
}
