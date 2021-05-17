import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LikeServlet extends BaseServlet {
    @Override
    public String getTemplateName() {
        return "like-page.ftl";
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        render(resp);
    }
}
