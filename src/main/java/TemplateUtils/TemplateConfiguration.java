package TemplateUtils;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;

import java.io.File;
import java.io.IOException;

public class TemplateConfiguration {
    static File TEMPLATES_ROOT = new File("src/main/resources/templates/");
    static String ENCODING = "UTF-8";
    static Configuration configuration;

    static Configuration getConfiguration() throws IOException {
        return new Configuration(Configuration.VERSION_2_3_29) {{
            setDirectoryForTemplateLoading(TEMPLATES_ROOT);
            setDefaultEncoding(ENCODING);
            setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
            setLogTemplateExceptions(false);
            setWrapUncheckedExceptions(true);
            setFallbackOnNullLoopVariable(false);
        }};
    }

    public static  Configuration getTemplateConfiguration() throws IOException {
        if (configuration == null) {
            configuration = getConfiguration();
        }

        return configuration;
    }


    public static Template getTemplate(String templateName) throws IOException {
        return getTemplateConfiguration().getTemplate(templateName);
    }
}
