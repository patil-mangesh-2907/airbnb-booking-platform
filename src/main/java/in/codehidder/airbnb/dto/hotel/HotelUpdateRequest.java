package in.codehidder.airbnb.dto.hotel;

import in.codehidder.airbnb.dto.contact.ContactDetailsDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HotelUpdateRequest {
    @NotBlank(message = "Hotel name is required")
    @Size(max = 100, message = "Hotel name must not exceed 100 characters")
    private String name;

    @NotBlank(message = "Hotel description is required")
    @Size(max = 1000, message = "Hotel description must not exceed 1000 characters")
    private String description;

    @NotBlank(message = "City is required")
    @Size(max = 100, message = "City must not exceed 100 characters")
    private String city;

    @NotBlank(message = "Address is required")
    @Size(max = 255, message = "Address must not exceed 255 characters")
    private String address;

    @NotBlank(message = "Location is required")
    @Size(max = 255, message = "Location must not exceed 255 characters")
    private String location;

    @Valid
    @NotNull(message = "Contact details are required")
    private ContactDetailsDto contactDetails;

    @NotEmpty(message = "At least one amenity is required")
    private String[] amenities;

    @NotNull(message = "Active status is required")
    private Boolean active;
}
