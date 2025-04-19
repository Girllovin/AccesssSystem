package smallITgroup.door.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.HashSet;

import smallITgroup.building.dao.BuildingRepository;
import smallITgroup.door.dao.DoorRepository;
import smallITgroup.building.model.Building;
import smallITgroup.door.model.Door;

@Service
public class DoorSyncService {

    @Autowired
    private DoorRepository doorRepository;

    @Autowired
    private BuildingRepository buildingRepository;

    public void syncDoorsToBuilding(Integer buildingId) {

        List<Door> allDoors = doorRepository.findAll();

        Building building = buildingRepository.findById(buildingId)
                .orElseThrow(() -> new RuntimeException("Building not found"));

        building.setDoors(new HashSet<>(allDoors));

        buildingRepository.save(building);

        System.out.println("Synchronisation well done");
    }
}
