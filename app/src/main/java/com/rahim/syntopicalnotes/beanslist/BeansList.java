package com.rahim.syntopicalnotes.beanslist;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Profile;

@Profile("beanslist")
public class BeansList implements CommandLineRunner {

    private final ApplicationContext ctx;

    public BeansList(ApplicationContext ctx) {
        this.ctx = ctx;
    }

    public static void main(String[] args) {
      SpringApplication app = new SpringApplication(BeansList.class);  
      app.setWebApplicationType(WebApplicationType.NONE);

      app.run(args);
    }

    @Override
    public void run(String... args) throws Exception {
       for (String name : ctx.getBeanDefinitionNames()) {
            System.out.printf("%s -> %s\n", name, ctx.getBean(name).getClass().getName()); 
       }  
    }
}
