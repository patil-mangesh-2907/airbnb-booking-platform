package in.codehidder.airbnb.service;

import in.codehidder.airbnb.dto.hotel.HotelCreateRequest;
import in.codehidder.airbnb.dto.hotel.HotelResponse;
import in.codehidder.airbnb.dto.hotel.HotelUpdateRequest;
import in.codehidder.airbnb.entity.Hotel;
import in.codehidder.airbnb.exception.DuplicateHotelContactException;
import in.codehidder.airbnb.exception.HotelNotFoundException;
import in.codehidder.airbnb.repository.HotelRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
public class HotelServiceImpl implements HotelService {
    private final HotelRepository hotelRepository;
    private final ModelMapper modelMapper;


    @Override
    public HotelResponse createHotel(HotelCreateRequest request) {
        if (hotelRepository.existsByContactDetailsEmail(request.getContactDetails().getEmail())) {
            throw new DuplicateHotelContactException("Hotel email already exists");
        }

        if (hotelRepository.existsByContactDetailsPhone(request.getContactDetails().getPhone())) {
            throw new DuplicateHotelContactException("Hotel phone already exists");
        }

        Hotel hotel = modelMapper.map(request, Hotel.class);
        hotel.setDeleted(false);
        hotel.setActive(false);
        Hotel savedHotel = hotelRepository.save(hotel);

        return modelMapper.map(savedHotel, HotelResponse.class);
    }

    @Override
    public HotelResponse getHotelById(Long hotelId) {
        Hotel hotel = hotelRepository.findByIdAndDeletedFalse(hotelId)
                .orElseThrow(() -> new HotelNotFoundException("Hotel with id: " + hotelId + " not found"));

        return modelMapper.map(hotel, HotelResponse.class);
    }

    @Override
    public List<HotelResponse> getAllHotels() {
        List<Hotel> hotels = hotelRepository.findByDeletedFalse();

        return hotels.stream()
                .map(hotel -> modelMapper.map(hotel, HotelResponse.class))
                .toList();
    }

    @Override
    public HotelResponse updateHotel(HotelUpdateRequest request, Long hotelId) {
        Hotel hotel = hotelRepository.findByIdAndDeletedFalse(hotelId)
                .orElseThrow(() -> new HotelNotFoundException("Hotel with id: " + hotelId + " not found"));

        modelMapper.map(request, hotel);

        Hotel savedHotel = hotelRepository.save(hotel);

        return modelMapper.map(savedHotel, HotelResponse.class);
    }

    @Override
    public void softDeleteHotel(Long hotelId) {
        Hotel hotel = hotelRepository.findByIdAndDeletedFalse(hotelId)
                .orElseThrow(() -> new HotelNotFoundException("Hotel with id: " + hotelId + " not found"));

        hotel.setDeleted(true);
        hotel.setDeletedAt(LocalDateTime.now());

        hotelRepository.save(hotel);
    }

    @Override
    public void hardDeleteHotel(Long hotelId) {
        boolean exists = hotelRepository.existsById(hotelId);

        if (!exists) {
            throw new HotelNotFoundException("Hotel with id: " + hotelId + " not found");
        }

        hotelRepository.deleteById(hotelId);
    }

    @Override
    public void restoreHotel(Long hotelId) {
        Hotel hotel = hotelRepository.findById(hotelId)
                .orElseThrow(() -> new HotelNotFoundException("Hotel with id: " + hotelId + " not found"));

        if (!hotel.getDeleted()) {
            throw new IllegalStateException("Hotel is already active");
        }

        hotel.setDeleted(false);
        hotel.setDeletedAt(null);

        hotelRepository.save(hotel);
    }

    @Override
    public void activeHotel(Long hotelId) {
        Hotel hotel = hotelRepository.findById(hotelId)
                .orElseThrow(() -> new HotelNotFoundException("Hotel with id: " + hotelId + " not found"));

        hotel.setActive(true);
        hotelRepository.save(hotel);
    }
}
