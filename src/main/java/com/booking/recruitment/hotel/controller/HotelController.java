package com.booking.recruitment.hotel.controller;

import com.booking.recruitment.hotel.model.Hotel;
import com.booking.recruitment.hotel.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/hotel")
public class HotelController {
  private final HotelService hotelService;

  @Autowired
  public HotelController(HotelService hotelService) {
    this.hotelService = hotelService;
  }

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public List<Hotel> getAllHotels() {
    return hotelService.getAllHotels();
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Hotel createHotel(@RequestBody Hotel hotel) {
    return hotelService.createNewHotel(hotel);
  }

  @GetMapping("/hotel/{id}")
  @ResponseStatus(HttpStatus.OK)
  public Optional<Hotel> getHotelById(@PathVariable("id") Long id) {
    return hotelService.getHotelById(id);
  }

  @DeleteMapping("/delete/{id}")
  @ResponseStatus(HttpStatus.OK)
  public void deleteHotelById(@PathVariable("id") Long id) {
    hotelService.deleteHotelById(id);
  }

  @GetMapping("/search/{cityId}/?sortBy=(distance)")
  @ResponseStatus(HttpStatus.OK)
  public List<Hotel> getClosestHotels(@PathVariable("cityId") Long id) {
    return hotelService.getClosestHotels(id);
  }

}
