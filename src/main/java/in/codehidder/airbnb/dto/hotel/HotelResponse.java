package in.codehidder.airbnb.dto.hotel;

import in.codehidder.airbnb.dto.contact.ContactDetailsDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HotelResponse {
    private Long id;
    private String name;
    private String description;
    private String city;
    private String address;
    private String location;
    private ContactDetailsDto contactDetails;
    private String[] amenities;
    private Boolean active;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
