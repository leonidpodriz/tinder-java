package Servlets;

import TemplateUtils.TemplateConfiguration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

abstract public class BaseServlet extends HttpServlet {
    abstract public String getTemplateName();

    public Template getTemplate() throws IOException {
        return TemplateConfiguration.getTemplate(getTemplateName());
    }

    public boolean render(Object context, HttpServletResponse httpServletResponse) throws IOException {
        try {
            getTemplate().process(context, httpServletResponse.getWriter());
            return true;
        } catch (TemplateException e) {
            System.out.println(e.getMessage());
            httpServletResponse.sendError(500);
            return false;
        }
    }

    public boolean render(HttpServletResponse httpServletResponse) throws IOException {
        return render(new HashMap<String, String>(), httpServletResponse);
    }
}
