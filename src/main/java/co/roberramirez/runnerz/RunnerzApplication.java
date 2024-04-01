package co.roberramirez.runnerz;

import co.roberramirez.runnerz.run.Location;
import co.roberramirez.runnerz.run.Run;
import co.roberramirez.runnerz.run.RunRepository;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;

@SpringBootApplication
public class RunnerzApplication {

    private static final Logger log = LoggerFactory.getLogger(RunnerzApplication.class);

    public static void main(String[] args) {

        SpringApplication.run(RunnerzApplication.class, args);
    }
}
