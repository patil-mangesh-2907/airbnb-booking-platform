package in.codehidder.airbnb.repository;

import in.codehidder.airbnb.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
}
