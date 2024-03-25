package co.roberramirez.runnerz.run;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class RunRepository {

    private List<Run> runs = new ArrayList<>();


    List<Run> findAll() {
        return runs;
    }

    Optional<Run> findById(Integer id) {
        return runs.stream()
                .filter(run -> run.id().equals(id))
                .findFirst();

    }

    // create a new run
    void create(Run run) {
        runs.add(run);
    }

    // update a run
    void update(Run run) {
        Optional<Run> existingRun = findById(run.id());

        existingRun.ifPresent(value -> runs.set(runs.indexOf(value), run));
    }

    // delete a run
    void delete(Integer id) {
        Optional<Run> existingRun = findById(id);

        existingRun.ifPresent(run -> runs.remove(run));
    }

    @PostConstruct
    private void init() {
        runs.add(new Run(1, "Morning Run", LocalDateTime.now(), LocalDateTime.now().plusHours(2), 10, Location.INDOOR));
        runs.add(new Run(2, "Evening Run", LocalDateTime.now(), LocalDateTime.now().plusHours(1), 5, Location.OUTDOOR));
    }
}
