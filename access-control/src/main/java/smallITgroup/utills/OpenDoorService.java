package smallITgroup.utills;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import smallITgroup.door.dao.DoorRepository;
import smallITgroup.door.dao.exeptions.DoorNotFoundExeption;
import smallITgroup.door.model.Door;

import java.time.Duration;
import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class OpenDoorService {

    private final DoorRepository doorRepository;

    @Async
    public void openDoorTemporarily(String doorId, long millis) {
        try {
            log.info("Asynchronously processing door open request for doorId {}", doorId);

            Door door = doorRepository.findById(doorId)
                    .orElseThrow(() -> new DoorNotFoundExeption());

            // Change door state
            door.setIsOpen(true);
            door.setIsActive(false);
            doorRepository.save(door);
            log.info("Door {} state changed to: open={}, active={}", doorId, door.getIsOpen(), door.getIsActive());

           
            Thread.sleep(millis*1000);
           

            // Revert door state
            door.setIsOpen(false);
            door.setIsActive(true);
            doorRepository.save(door);
            log.info("Door {} state reverted to: open={}, active={}", doorId, door.getIsOpen(), door.getIsActive());

        } catch (Exception e) {
            log.error("Error during async door operation: {}", e.getMessage(), e);
        }
    }
}