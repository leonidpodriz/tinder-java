package TemplateUtils;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;

import java.io.IOException;

public class TemplateConfiguration {
    static String ENCODING = "UTF-8";
    static Configuration configuration;

    static Configuration getConfiguration() {
        return new Configuration(Configuration.VERSION_2_3_29) {{
            setClassForTemplateLoading(TemplateConfiguration.class, "/templates/");
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
