package org.example;

import org.example.config.AppConfig;
import org.example.service.ServerConfigService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {

        var ctx = new AnnotationConfigApplicationContext(AppConfig.class);

        var serverConfig =
                ctx.getBean(ServerConfigService.class);
        var serverConfig1 =
                ctx.getBean(ServerConfigService.class);

        System.out.println(serverConfig.getUtilService().hashCode());
        System.out.println(serverConfig1.getUtilService().hashCode());

//        injectarea de bean prototype in singleton => singleton
    }
}