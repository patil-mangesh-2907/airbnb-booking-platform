package in.codehidder.airbnb.controller;

import in.codehidder.airbnb.dto.hotel.HotelCreateRequest;
import in.codehidder.airbnb.dto.hotel.HotelResponse;
import in.codehidder.airbnb.dto.hotel.HotelUpdateRequest;
import in.codehidder.airbnb.service.HotelService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/hotels")
public class HotelController {
    private final HotelService hotelService;

    @PostMapping
    public ResponseEntity<HotelResponse> createHotel(@RequestBody @Valid HotelCreateRequest request) {
        HotelResponse response = hotelService.createHotel(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{hotelId}")
    public ResponseEntity<HotelResponse> getHotelById(@PathVariable Long hotelId) {
        HotelResponse response = hotelService.getHotelById(hotelId);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<HotelResponse>> getAllHotels() {
        List<HotelResponse> hotelResponses = hotelService.getAllHotels();
        return ResponseEntity.ok(hotelResponses);
    }

    @PostMapping("/{hotelId}")
    public ResponseEntity<HotelResponse> updateHotel(@RequestBody @Valid HotelUpdateRequest request, @PathVariable Long hotelId) {
        HotelResponse response = hotelService.updateHotel(request, hotelId);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/soft-delete/{hotelId}")
    public ResponseEntity<Void> softDeleteHotel(@PathVariable Long hotelId) {
        hotelService.softDeleteHotel(hotelId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{hotelId}")
    public ResponseEntity<Void> hardDeleteHotel(@PathVariable Long hotelId) {
        hotelService.hardDeleteHotel(hotelId);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/restore/{hotelId}")
    public ResponseEntity<Void> restoreHotel(@PathVariable Long hotelId) {
        hotelService.restoreHotel(hotelId);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/activate/{hotelId}")
    public ResponseEntity<Void> activateHotel(@PathVariable Long hotelId) {
        hotelService.activeHotel(hotelId);
        return ResponseEntity.noContent().build();
    }
}
