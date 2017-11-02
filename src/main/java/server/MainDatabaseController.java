package server;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainDatabaseController {
    public static void main(String[] args) {
        ApplicationContext bf = new ClassPathXmlApplicationContext("server-config.xml");
        System.out.println("Server start...");
    }
}
