package in.codehidder.airbnb.dto.hotel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HotelSummaryResponse {
    private Long id;
    private String name;
    private String city;
    private String location;
    private Boolean active;
}
