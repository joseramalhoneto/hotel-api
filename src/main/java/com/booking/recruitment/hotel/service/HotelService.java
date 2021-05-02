package com.booking.recruitment.hotel.service;

import com.booking.recruitment.hotel.model.Hotel;

import java.util.List;
import java.util.Optional;

public interface HotelService {
  List<Hotel> getAllHotels();

  List<Hotel> getHotelsByCity(Long cityId);

  Hotel createNewHotel(Hotel hotel);

  Optional<Hotel> getHotelById(Long id);

  void deleteHotelById(Long id);

  List<Hotel> getClosestHotels(Long id);
}
