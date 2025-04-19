package smallITgroup.event.dto;

import java.time.LocalDate;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor // Automatically generates a constructor with required (final) fields
@Getter // Generates getters for all fields
public class EventDateDto {

    LocalDate date; // The date for which event history is requested
}
