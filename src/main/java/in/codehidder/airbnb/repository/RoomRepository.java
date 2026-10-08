package in.codehidder.airbnb.repository;

import in.codehidder.airbnb.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RoomRepository extends JpaRepository<Room, Long> {
    Optional<Room> findByIdAndDeletedFalse(Long roomId);
    List<Room> findByHotelIdAndDeletedFalse(Long hotelId);
    boolean existsById(Long id);
    Long id(Long id);
}
