package in.codehidder.airbnb.service;

import in.codehidder.airbnb.dto.hotel.HotelCreateRequest;
import in.codehidder.airbnb.dto.hotel.HotelResponse;
import in.codehidder.airbnb.dto.hotel.HotelUpdateRequest;

import java.util.List;

public interface HotelService {
    HotelResponse createHotel(HotelCreateRequest request);

    HotelResponse getHotelById(Long hotelId);

    List<HotelResponse> getAllHotels();

    HotelResponse updateHotel(HotelUpdateRequest request, Long hotelId);

    void softDeleteHotel(Long hotelId);

    void hardDeleteHotel(Long hotelId);

    void restoreHotel(Long hotelId);

    void activeHotel(Long hotelId);
}
