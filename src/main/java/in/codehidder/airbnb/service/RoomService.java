package in.codehidder.airbnb.service;

import in.codehidder.airbnb.dto.room.RoomCreateRequest;
import in.codehidder.airbnb.dto.room.RoomResponse;

import java.util.List;

public interface RoomService {
    RoomResponse createRoom(RoomCreateRequest request, Long hotelId);

    RoomResponse getRoomById(Long roomId);

    List<RoomResponse> getAllRoomsInHotel(Long hotelId);

    void softDeleteRoom(Long roomId);

    void hardDeleteRoom(Long roomId);

    void restoreRoom(Long roomId);
}
