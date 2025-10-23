package com.smartlogi;

import org.apache.catalina.Context;
import org.apache.catalina.startup.Tomcat;
import org.springframework.web.context.support.XmlWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        // Initialize Tomcat
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(8086);
        tomcat.getConnector(); // Ensure connector is initialized

        // Set up the context with the base directory
        String baseDir = new File(".").getAbsolutePath();
        Context ctx = tomcat.addContext("", baseDir);

        // Initialize Spring Web Application Context
        XmlWebApplicationContext webContext = new XmlWebApplicationContext();
        webContext.setConfigLocation("classpath:applicationContext.xml");
        webContext.setServletContext(ctx.getServletContext());
        webContext.refresh();

        // Create DispatcherServlet and register it
        DispatcherServlet dispatcherServlet = new DispatcherServlet(webContext);
        Tomcat.addServlet(ctx, "dispatcher", dispatcherServlet);
        ctx.addServletMappingDecoded("/*", "dispatcher");

        try {
            // Start Tomcat
            tomcat.start();
            System.out.println("=====================================");
            System.out.println("Server started successfully!");
            System.out.println("=====================================");
            System.out.println("\nAPI: http://localhost:8086/api/livreurs");
            System.out.println("Press CTRL+C to stop...\n");

            // Keep the server running
            tomcat.getServer().await();
        } catch (Exception e) {
            System.err.println("Failed to start Tomcat: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                tomcat.stop();
                tomcat.destroy();
            } catch (Exception e) {
                System.err.println("Error stopping Tomcat: " + e.getMessage());
            }
        }
    }
}