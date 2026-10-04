package in.codehidder.airbnb.service;

import in.codehidder.airbnb.dto.room.RoomCreateRequest;
import in.codehidder.airbnb.dto.room.RoomResponse;
import in.codehidder.airbnb.entity.Hotel;
import in.codehidder.airbnb.entity.Room;
import in.codehidder.airbnb.exception.HotelNotFoundException;
import in.codehidder.airbnb.exception.ResourceNotFoundException;
import in.codehidder.airbnb.repository.HotelRepository;
import in.codehidder.airbnb.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
public class RoomServiceImpl implements RoomService {
    private final RoomRepository roomRepository;
    private final HotelRepository hotelRepository;
    private final ModelMapper modelMapper;

    @Override
    public RoomResponse createRoom(RoomCreateRequest request, Long hotelId) {
        Hotel hotel = hotelRepository.findByIdAndDeletedFalse(hotelId)
                .orElseThrow(() -> new HotelNotFoundException("Hotel with id: " + hotelId + " not found"));

        Room room = modelMapper.map(request, Room.class);
        room.setHotel(hotel);
        room = roomRepository.save(room);
        //TODO create inventory as soon as room is created and if hotel is active
        return modelMapper.map(room, RoomResponse.class);
    }

    @Override
    public RoomResponse getRoomById(Long roomId) {
        Room room = roomRepository.findByIdAndDeletedFalse(roomId)
                .orElseThrow(() -> new ResourceNotFoundException("Room with id: " + roomId + " not found"));

        return modelMapper.map(room, RoomResponse.class);
    }

    @Override
    public List<RoomResponse> getAllRoomsInHotel(Long hotelId) {
        Hotel hotel = hotelRepository.findByIdAndDeletedFalse(hotelId)
                .orElseThrow(() -> new HotelNotFoundException("Hotel with id: " + hotelId + " not found"));

        List<Room> rooms = roomRepository.findByHotelAndDeletedFalse(hotelId);

        return rooms.stream()
                .map(room -> modelMapper.map(room, RoomResponse.class))
                .toList();
    }

    @Override
    public void softDeleteRoom(Long roomId) {
        Room room = roomRepository.findByIdAndDeletedFalse(roomId)
                .orElseThrow(() -> new ResourceNotFoundException("Room with id: " + roomId + " not found"));

        room.setDeleted(true);
        roomRepository.save(room);
    }

    @Override
    public void hardDeleteRoom(Long roomId) {
        boolean exists = roomRepository.existsById(roomId);

        if (!exists) {
            throw new ResourceNotFoundException("Room with id: " + roomId + " not found");
        }

        roomRepository.deleteById(roomId);
    }

    @Override
    public void restoreRoom(Long roomId) {
        Room room = roomRepository.findByIdAndDeletedFalse(roomId)
                .orElseThrow(() -> new ResourceNotFoundException("Room with id: " + roomId + " not found"));

        room.setDeleted(false);
        roomRepository.save(room);

        //TODO delete all inventories in future this room
    }
}
