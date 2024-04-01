package co.roberramirez.runnerz.run;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;

import java.time.LocalDateTime;

public record Run(
        Integer id,
        @NotEmpty
        String title,
        LocalDateTime startedOn,
        LocalDateTime completedOn,
        @Positive
        Integer kilometers,
        Location location
) {

    public Run {
        if (startedOn.isAfter(completedOn)) {
            throw new IllegalArgumentException("The run cannot be completed before it starts");
        }
    }
}
