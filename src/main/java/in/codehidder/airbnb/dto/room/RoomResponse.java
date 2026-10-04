package in.codehidder.airbnb.dto.room;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RoomResponse {
    private Long id;

    private Long hotelId;

    private String roomType;

    private String description;

    private Integer capacity;

    private BigDecimal pricePerNight;

    private String[] amenities;

    private String[] photos;

    private Boolean active;

    private Boolean deleted;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
