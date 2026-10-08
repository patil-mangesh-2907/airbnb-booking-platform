package in.codehidder.airbnb.repository;

import in.codehidder.airbnb.entity.Hotel;
import in.codehidder.airbnb.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface HotelRepository extends JpaRepository<Hotel, Long> {

    boolean existsByContactDetailsEmail(String email);

    boolean existsByContactDetailsPhone(String phone);

    Optional<Hotel> findByIdAndDeletedFalse(Long hotelId);

    List<Hotel> findByDeletedFalse();

    Optional<Hotel> findById(Long hotelId);

    boolean existsById(Long hotelId);
}