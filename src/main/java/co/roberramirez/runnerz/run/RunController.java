package co.roberramirez.runnerz.run;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/runs")
public class RunController {

    private final RunRepository runRepository;

    public RunController(RunRepository runRepository) {
        this.runRepository = runRepository;
    }

    @GetMapping("")
    List<Run> findAll () {
        return runRepository.findAll();
    }

    @GetMapping("/{id}")
    Run findById(@PathVariable Integer id) {
        Optional<Run> run = runRepository.findById(id);
        if (run.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Run not found");
        }
        return run.get();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    void create(@Valid @RequestBody Run run) {
        runRepository.create(run);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    void update(@PathVariable Integer id, @Valid @RequestBody Run run) {
        runRepository.update(run, id);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    void delete(@PathVariable Integer id) {
        runRepository.delete(id);
    }

//
//    @GetMapping("/{id}")
//    Run findById(@PathVariable Integer id) {
//
//        Optional<Run> run = runRepository.findById(id);
//
//        if (run.isEmpty()) {
//            throw new RunNotFoundException();
//        }
//
//        return run.get();
//    }
//
//    @ResponseStatus(HttpStatus.CREATED)
//    @PostMapping("")
//    void create(@Valid @RequestBody Run run) {
//         runRepository.create(run);
//    }
//
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    @PutMapping("/{id}")
//    void update(@PathVariable Integer id, @RequestBody Run run) {
//        Optional<Run> existingRun = runRepository.findById(id);
//
//        if (existingRun.isEmpty()) {
//            throw new RunNotFoundException();
//        }
//
//        runRepository.update(run);
//    }
//
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    @DeleteMapping("/{id}")
//    void delete(@PathVariable Integer id) {
//        Optional<Run> existingRun = runRepository.findById(id);
//
//        if (existingRun.isEmpty()) {
//            throw new RunNotFoundException();
//        }
//
//        runRepository.delete(id);
//    }

}
