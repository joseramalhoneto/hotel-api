package com.booking.recruitment.hotel.service.impl;

import com.booking.recruitment.hotel.exception.BadRequestException;
import com.booking.recruitment.hotel.model.Hotel;
import com.booking.recruitment.hotel.repository.HotelRepository;
import com.booking.recruitment.hotel.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
class DefaultHotelService implements HotelService {
  private final HotelRepository hotelRepository;

  @Autowired
  DefaultHotelService(HotelRepository hotelRepository) {
    this.hotelRepository = hotelRepository;
  }

  @Override
  public List<Hotel> getAllHotels() {
    return hotelRepository.findAll();
  }

  @Override
  public List<Hotel> getHotelsByCity(Long cityId) {
    return hotelRepository.findAll().stream()
        .filter((hotel) -> cityId.equals(hotel.getCity().getId()))
        .collect(Collectors.toList());
  }

  @Override
  public Hotel createNewHotel(Hotel hotel) {
    if (hotel.getId() != null) {
      throw new BadRequestException("The ID must not be provided when creating a new Hotel");
    }

    return hotelRepository.save(hotel);
  }

  @Override
  public Optional<Hotel> getHotelById(Long id) {
    return hotelRepository.findById(id);
  }

  @Override
  public void deleteHotelById(Long id) {
    Optional<Hotel> hotel = hotelRepository.findById(id);
    if (hotel.get() == null) {
      throw new BadRequestException("The ID provided was not found");
    }else{
      hotel.get().setDeleted(true);     //Logically deleted. Not removed from database
    }
  }

  @Override
  public List<Hotel> getClosestHotels(Long id) {
    List<Hotel> hotels = this.getHotelsByCity(id);

    hotels.stream()
            .sorted(Comparator.comparing(Hotel::getDistance))
            .collect(Collectors.toList());

    List<Hotel> result = null;
    result.add(hotels.get(0));
    result.add(hotels.get(1));
    result.add(hotels.get(2));
    return result;
  }
}
