package com.bookmyway.service;

import java.util.List;

import com.bookmyway.model.Hotel;

public interface HotelService {
	Hotel addHotel(Hotel hotel);

	List<Hotel> getAllHotels();

	Hotel getHotelByName(String hotelName);

	Hotel updateHotel(Integer hotelId, Hotel hotel);

	Hotel deleteHotelById(Integer hotelId);

	List<Hotel> searchHotelsByLocation(String location);

	List<Hotel> searchHotelsByPriceRange(Double minPrice, Double maxPrice);

	boolean checkHotelAvailability(Integer hotelId, Integer requiredRooms);
}
