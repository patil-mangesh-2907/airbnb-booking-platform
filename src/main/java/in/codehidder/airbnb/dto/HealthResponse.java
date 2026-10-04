package in.codehidder.airbnb.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HealthResponse {
    private String appStatus;
    private String dbStatus;
    private LocalDateTime timestamp;
    private String welcomeMessage;
}
