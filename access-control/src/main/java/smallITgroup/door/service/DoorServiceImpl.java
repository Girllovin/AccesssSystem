package smallITgroup.door.service;

import java.util.HashSet;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import smallITgroup.building.dao.BuildingRepository;
import smallITgroup.building.dao.exeption.BuildingNotFoundExeption;
import smallITgroup.building.model.Building;
import smallITgroup.door.dao.DoorRepository;
import smallITgroup.door.dao.exeption.DoorNotFoundExeption;
import smallITgroup.door.dto.DoorDto;
import smallITgroup.door.model.Door;

@Service
@RequiredArgsConstructor
public class DoorServiceImpl implements DoorService {

    private static final Logger logger = LoggerFactory.getLogger(DoorServiceImpl.class);

    final DoorRepository doorRepository;
    final BuildingRepository buildingRepository;
    final ModelMapper modelMapper;

    @Override
    public Boolean createDoor(Integer buildingId, DoorDto doorDto) {
        logger.info("Creating a new door for building with ID {}", buildingId);

        Door door = new Door(doorDto.getDescription());
        Building building = buildingRepository.findById(buildingId)
                .orElseThrow(() -> {
                    logger.error("Building with ID {} not found", buildingId);
                    return new BuildingNotFoundExeption();
                });

        if (building.getDoors() == null) {
            building.setDoors(new HashSet<>());
            logger.debug("Initialized doors set for building ID {}", buildingId);
        }

        building.getDoors().add(door);
     

        buildingRepository.save(building);
        doorRepository.save(door); 

        logger.info("Door created and linked to building ID {}: {}", buildingId, door);
        return true;
    }

    @Override
    public DoorDto removeDoor(String doorId) {
        logger.info("Removing door with ID {}", doorId);
        Door door = doorRepository.findById(doorId)
                .orElseThrow(() -> {
                    logger.error("Door with ID {} not found", doorId);
                    return new DoorNotFoundExeption();
                });

        doorRepository.delete(door);
        logger.info("Door with ID {} successfully removed", doorId);

        return modelMapper.map(door, DoorDto.class);
    }

    @Override
    public DoorDto activateDoor(DoorDto doorDto) {
        logger.info("Activating door with ID {}", doorDto.getDoorId());

        Door existingDoor = doorRepository.findById(doorDto.getDoorId())
                .orElseThrow(() -> {
                    logger.error("Door with ID {} not found", doorDto.getDoorId());
                    return new DoorNotFoundExeption();
                });

        existingDoor = modelMapper.map(doorDto, Door.class);
        doorRepository.save(existingDoor);

        logger.info("Door with ID {} activated/updated", doorDto.getDoorId());

        return modelMapper.map(existingDoor, DoorDto.class);
    }
}
