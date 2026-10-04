package in.codehidder.airbnb.controller;

import in.codehidder.airbnb.dto.room.RoomCreateRequest;
import in.codehidder.airbnb.dto.room.RoomResponse;
import in.codehidder.airbnb.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/admin/hotels/{hotelId}/rooms")
public class RoomAdminController {
    private final RoomService roomService;

    @PostMapping
    public ResponseEntity<RoomResponse> createRoom(@PathVariable Long hotelId,
                                                   @RequestBody RoomCreateRequest request) {
        RoomResponse roomResponse = roomService.createRoom(request, hotelId);
        return new ResponseEntity<>(roomResponse, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<RoomResponse>> getAllRoomsInHotel(@PathVariable Long hotelId) {
        List<RoomResponse> roomResponses = roomService.getAllRoomsInHotel(hotelId);
        return ResponseEntity.ok(roomResponses);
    }

    @GetMapping("/{roomId}")
    public ResponseEntity<RoomResponse> getRoomById(@PathVariable Long roomId) {
        RoomResponse roomResponse = roomService.getRoomById(roomId);
        return ResponseEntity.ok(roomResponse);
    }

    @PatchMapping("/soft-delete/{roomId}")
    public ResponseEntity<Void> softDeleteRoom(@PathVariable Long roomId) {
        roomService.softDeleteRoom(roomId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/hard-delete/{roomId}")
    public ResponseEntity<Void> hardDeleteRoom(@PathVariable Long roomId) {
        roomService.hardDeleteRoom(roomId);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/restore/{roomId}")
    public ResponseEntity<Void> restoreRoom(@PathVariable Long roomId) {
        roomService.restoreRoom(roomId);
        return ResponseEntity.noContent().build();
    }
}
