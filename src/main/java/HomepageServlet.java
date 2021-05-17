import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

public class HomepageServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PrintWriter writer = resp.getWriter();
        Template template = TemplateConfiguration.getTemplate("homepage.ftlh");
        HashMap<String, String> stringStringHashMap = new HashMap<>() {{
            put("name", "Leonid");
        }};
        try {
            template.process(stringStringHashMap, writer);
        } catch (TemplateException e) {
            e.printStackTrace();
        }
    }
}
