package in.codehidder.airbnb.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "inventory",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_hotel_room_date",
                        columnNames = {
                                "hotel_id",
                                "room_id",
                                "date"
                        }
                )
        }
)
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="hotel_id",nullable = false)
    private Hotel hotel;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="room_id",nullable = false)
    private Room room;

    @Column(nullable = false)
    private LocalDate date;

    @Column(name = "total_rooms", nullable = false)
    private Integer totalRooms;

    @Column(name = "booked_rooms", nullable = false)
    private Integer bookedRooms = 0;

    @Column(name = "available_rooms", nullable = false)
    private Integer availableRooms;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    @Column(precision = 5, scale = 2)
    private BigDecimal surgeFactor = BigDecimal.ONE;

    @Column(nullable = false)
    private Boolean isClosed = false;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
