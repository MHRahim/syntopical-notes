package com.rahim.syntopicalnotes.utiltestt;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.rahim.syntopicalnotes.utiltestt.components.UtilComponent;

@SpringBootApplication(scanBasePackages = "com.rahim.syntopicalnotes")
@EnableJpaRepositories(basePackages = "com.rahim.syntopicalnotes.repositories")
@EntityScan(basePackages = "com.rahim.syntopicalnotes.domains")
@Profile("utils")
public class Utility implements CommandLineRunner {

    private UtilComponent utilComponent;

    public Utility(UtilComponent utilComponent) {
		this.utilComponent = utilComponent;
	}

	public static void main(String[] args) {
        System.out.println("Running the static void main function");

        SpringApplication app = new SpringApplication(Utility.class);
        app.setWebApplicationType(WebApplicationType.NONE);
        app.run(args);

    }
    
    public void run(String... args) {
        System.out.println("Running run commandline method");
        this.utilComponent.display();
    }
}
