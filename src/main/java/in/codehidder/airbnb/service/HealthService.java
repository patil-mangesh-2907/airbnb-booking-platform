package in.codehidder.airbnb.service;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class HealthService {
    private final JdbcTemplate jdbcTemplate;

    public String checkDBStatus() {
        String dbStatus = "DOWN";

        try {
            Integer result = jdbcTemplate.queryForObject("SELECT 1", Integer.class);
            if (result == 1) {
                dbStatus = "UP";
            }
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }

        return dbStatus;
    }
}
