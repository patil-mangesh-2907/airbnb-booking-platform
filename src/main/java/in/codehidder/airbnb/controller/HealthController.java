package in.codehidder.airbnb.controller;

import in.codehidder.airbnb.dto.HealthResponse;
import in.codehidder.airbnb.service.HealthService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@RestController
@RequestMapping("/health")
public class HealthController {
    private final HealthService healthService;
    @Value("${app.welcome.message}")
    private String message;

    @GetMapping
    public ResponseEntity<HealthResponse> health() {
        HealthResponse response = new HealthResponse(
                "UP",
                healthService.checkDBStatus(),
                LocalDateTime.now(),
                message
        );

        return ResponseEntity.ok(response);
    }
}
