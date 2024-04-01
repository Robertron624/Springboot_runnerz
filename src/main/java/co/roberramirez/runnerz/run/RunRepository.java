package co.roberramirez.runnerz.run;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

@Repository
public class RunRepository {

    private static final Logger log = LoggerFactory.getLogger(RunRepository.class);
    private final JdbcClient jdbcClient;

    public RunRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    public List<Run> findAll() {
        return jdbcClient.sql("SELECT * FROM Run")
                .query(Run.class)
                .list();

    }

    public Optional<Run> findById(Integer id) {
        return jdbcClient.sql("SELECT id, title, started_on, completed_on, kilometers, location FROM Run WHERE id = :id")
                .param("id", id)
                .query(Run.class)
                .optional();
    }

    public void create(Run run) {
        var updated = jdbcClient.sql("INSERT INTO Run (id, title, started_on, completed_on, kilometers, location) VALUES (?, ?, ?, ?, ?, ?)")
                .params(List.of(run.id(), run.title(), run.startedOn(), run.completedOn(), run.kilometers(), run.location().toString()))
                .update();

        Assert.state(updated == 1, "Failed to create run " + run.title());
    }

    public void update(Run run, Integer id) {
        var updated = jdbcClient.sql("UPDATE Run SET title = ?, started_on = ?, completed_on = ?, kilometers = ?, location = ? WHERE id = ?")
                .params(List.of(run.title(), run.startedOn(), run.completedOn(), run.kilometers(), run.location().toString(), id))
                .update();

        Assert.state(updated == 1, "Failed to update run " + run.title());
    }

    public void delete(Integer id) {
        var updated = jdbcClient.sql("DELETE FROM Run WHERE id = ?")
                .params(id)
                .update();

        Assert.state(updated == 1, "Failed to delete run " + id);
    }

    public int count() {
        List<?> rows = jdbcClient.sql("SELECT * FROM Run")
                .query()
                .listOfRows();

        return rows.size();
    }

    public void saveAll(List<Run> runs) {
        runs.stream().forEach(this::create);
    }

    public List<Run> findByLocation(String location) {
        return jdbcClient.sql("SELECT * FROM Run WHERE location = :location")
                .param("location", location)
                .query(Run.class)
                .list();
    }

//    List<Run> findAll() {
//        return runs;
//    }
//
//    Optional<Run> findById(Integer id) {
//        return runs.stream()
//                .filter(run -> run.id().equals(id))
//                .findFirst();
//
//    }
//
//    // create a new run
//    void create(Run run) {
//        runs.add(run);
//    }
//
//    // update a run
//    void update(Run run) {
//        Optional<Run> existingRun = findById(run.id());
//
//        existingRun.ifPresent(value -> runs.set(runs.indexOf(value), run));
//    }
//
//    // delete a run
//    void delete(Integer id) {
//        Optional<Run> existingRun = findById(id);
//
//        existingRun.ifPresent(run -> runs.remove(run));
//    }
//
//    @PostConstruct
//    private void init() {
//        runs.add(new Run(1, "Morning Run", LocalDateTime.now(), LocalDateTime.now().plusHours(2), 10, Location.INDOOR));
//        runs.add(new Run(2, "Evening Run", LocalDateTime.now(), LocalDateTime.now().plusHours(1), 5, Location.OUTDOOR));
//    }
}
