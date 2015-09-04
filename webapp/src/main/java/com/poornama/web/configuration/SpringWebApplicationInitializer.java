package com.poornama.web.configuration;

import com.poornama.api.logging.GlobalLogger;
import org.apache.log4j.Logger;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration.Dynamic;

/**
 * @author dedunu
 */
public class SpringWebApplicationInitializer implements WebApplicationInitializer {

    private static Logger log = GlobalLogger.getLogger();
    private static String className = SpringWebApplicationInitializer.class.getName();

    /**
     * Called by Application server on application startup
     *
     * @param servletContext servletContext
     * @throws ServletException
     */
    public void onStartup(ServletContext servletContext) throws ServletException {
        log.debug("[" + className + "] onStartup()");
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.register(SpringConfiguration.class);
        context.setServletContext(servletContext);
        Dynamic servlet = servletContext.addServlet("dispatcher", new DispatcherServlet(context));
        servlet.addMapping("/");
        servlet.setLoadOnStartup(1);
        log.debug("[" + className + "] onStartup : completed successfully.");
    }
}