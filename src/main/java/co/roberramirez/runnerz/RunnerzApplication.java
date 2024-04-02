package co.roberramirez.runnerz;

import co.roberramirez.runnerz.run.Location;
import co.roberramirez.runnerz.run.Run;
import co.roberramirez.runnerz.run.RunRepository;
import co.roberramirez.runnerz.user.User;
import co.roberramirez.runnerz.user.UserHttpClient;
import co.roberramirez.runnerz.user.UserResClient;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootApplication
public class RunnerzApplication {

    private static final Logger log = LoggerFactory.getLogger(RunnerzApplication.class);

    public static void main(String[] args) {

        SpringApplication.run(RunnerzApplication.class, args);
    }

    @Bean
    UserHttpClient userHttpClient() {
        RestClient restClient = RestClient.create("https://jsonplaceholder.typicode.com/");
        HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(RestClientAdapter.create(restClient)).build();
        return factory.createClient(UserHttpClient.class);
    }
    @Bean
    CommandLineRunner runner (UserHttpClient client) {
        return args -> {
            List<User> users = client.findAll();
            System.out.println("Users: " + users);

            User user = client.findById(1);
            System.out.println("User with Id 1: " + user);
        };
    }
}
