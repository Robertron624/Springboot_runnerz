package co.roberramirez.runnerz.run;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.asm.TypeReference;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.InputStream;

@Component
public class RunJsonDataLoader implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(RunJsonDataLoader.class);
    private final RunRepository runRepository;
    private final ObjectMapper objectMapper;

    public RunJsonDataLoader(RunRepository runRepository, ObjectMapper objectMapper) {
        this.runRepository = runRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("Loading JSON data");

        if(runRepository.count() == 0) {
           try(InputStream inputStream = TypeReference.class.getResourceAsStream("/data/runs.json")) {
               Runs allRuns = objectMapper.readValue(inputStream, Runs.class);
               log.info("Reading {} runs from JSON data and saving it to a database", allRuns.runs().size());
               runRepository.saveAll(allRuns.runs());
           } catch (Exception e) {
               log.error("Failed to load JSON data", e);
           }
        }
    }
}
