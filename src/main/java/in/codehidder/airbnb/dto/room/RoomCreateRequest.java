package in.codehidder.airbnb.dto.room;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RoomCreateRequest {
    @NotBlank(message = "Room name is required")
    private String name;

    @NotBlank(message = "Room type is required")
    private String type;

    @NotBlank(message = "Room description is required")
    private String description;

    @NotNull(message = "Room capacity is required")
    @Positive(message = "Room capacity must be greater than 0")
    private Integer capacity;

    @NotNull(message = "Total rooms is required")
    @Positive(message = "Total rooms must be greater than 0")
    private Integer totalRooms;

    @NotNull(message = "Base price is required")
    @Positive(message = "Base price must be greater than 0")
    private BigDecimal basePrice;

    @NotEmpty(message = "At least one amenity is required")
    private String[] amenities;

    @NotEmpty(message = "At least one photo is required")
    private String[] photos;
}
