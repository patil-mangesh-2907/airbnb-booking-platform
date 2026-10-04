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
    @NotBlank(message = "Room type is required")
    private String roomType;

    @NotBlank(message = "Room description is required")
    private String description;

    @NotNull(message = "Room capacity is required")
    @Positive(message = "Room capacity must be greater than 0")
    private Integer capacity;

    @NotNull(message = "Price per night is required")
    @Positive(message = "Price per night must be greater than 0")
    private BigDecimal pricePerNight;

    @NotEmpty(message = "At least one amenity is required")
    private String[] amenities;

    @NotEmpty(message = "At least one photo is required")
    private String[] photos;
}
