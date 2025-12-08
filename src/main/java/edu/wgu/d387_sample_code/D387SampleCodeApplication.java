package edu.wgu.d387_sample_code;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Locale;
import java.util.ResourceBundle;

@SpringBootApplication
public class D387SampleCodeApplication {

    public static void main(String[] args) {
        SpringApplication.run(D387SampleCodeApplication.class, args);
    }

    @Bean
    public CommandLineRunner welcomeMessagesRunner() {
        return args -> {
            Runnable enTask = () -> {
                ResourceBundle rb = ResourceBundle.getBundle("messages", Locale.ENGLISH);
                System.out.println("=== B1 EN === " + rb.getString("welcome"));
            };

            Runnable frTask = () -> {
                ResourceBundle rb = ResourceBundle.getBundle("messages", Locale.FRENCH);
                System.out.println("=== B1 FR === " + rb.getString("welcome"));
            };

            Thread t1 = new Thread(enTask);
            Thread t2 = new Thread(frTask);

            t1.start();
            t2.start();

            t1.join();
            t2.join();
        };
    }
}
