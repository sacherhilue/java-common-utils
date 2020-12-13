package com.wuwei;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.env.Environment;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author wuwei
 * @date 2018/7/7 10:40
 * @description
 */
@SpringBootApplication
public class Application {

    @Autowired
    private Environment env;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @EventListener
    public void afterApplicationStarted(ApplicationReadyEvent event) {
        try {
            String host = InetAddress.getLocalHost().getHostAddress();
            String port = this.env.getProperty("server.port");
            System.out.println("Application startup at host:" + host + "; port:" + port);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}
