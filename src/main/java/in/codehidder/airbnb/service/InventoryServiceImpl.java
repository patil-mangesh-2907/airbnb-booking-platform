package in.codehidder.airbnb.service;

import in.codehidder.airbnb.entity.Room;
import in.codehidder.airbnb.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Slf4j
public class InventoryServiceImpl implements InventoryService{
    private final InventoryRepository inventoryRepository;
    @Override
    public void initializeRoomForAYear(Room room) {

    }
}
