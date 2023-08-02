package com.bookmyway.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bookmyway.model.Hotel;

/**
 * HotelController handles HTTP requests related to Hotel entities. It provides
 * CRUD operations for hotels and additional methods for searching and checking
 * availability.
 *
 * @Author HoshiyarJyani
 */
@RestController
@RequestMapping("/hotels")
public class HotelController {

	@Autowired
	private HotelService hotelService;

	/**
	 * Adds a new hotel to the database.
	 *
	 * @param hotel The hotel object to be added.
	 * @return The added hotel with HttpStatus.CREATED status if successful.
	 */
	@PostMapping
	public ResponseEntity<Hotel> addHotelHandler(@RequestBody Hotel hotel) {
		Hotel addedHotel = hotelService.addHotel(hotel);
		return new ResponseEntity<>(addedHotel, HttpStatus.CREATED);
	}

	/**
	 * Retrieves a list of all hotels from the database.
	 *
	 * @return A list of all hotels with HttpStatus.OK status if successful.
	 */
	@GetMapping
	public ResponseEntity<List<Hotel>> getAllHotelsHandler() {
		List<Hotel> hotels = hotelService.getAllHotels();
		return new ResponseEntity<>(hotels, HttpStatus.OK);
	}

	/**
	 * Retrieves a hotel by its name from the database.
	 *
	 * @param hotelName The name of the hotel to be retrieved.
	 * @return The hotel with HttpStatus.OK status if found, or HttpStatus.NOT_FOUND
	 *         if not found.
	 */
	@GetMapping("/{hotelName}")
	public ResponseEntity<Hotel> getHotelByNameHandler(@PathVariable String hotelName) {
		Hotel hotel = hotelService.getHotelByName(hotelName);
		if (hotel != null) {
			return new ResponseEntity<>(hotel, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * Updates information of a hotel in the database.
	 *
	 * @param hotelId The ID of the hotel to be updated.
	 * @param hotel   The updated hotel object.
	 * @return The updated hotel with HttpStatus.OK status if successful, or
	 *         HttpStatus.NOT_FOUND if the hotel does not exist.
	 */
	@PutMapping("/{hotelId}")
	public ResponseEntity<Hotel> updateHotelHandler(@PathVariable Integer hotelId, @RequestBody Hotel hotel) {
		Hotel updatedHotel = hotelService.updateHotel(hotelId, hotel);
		if (updatedHotel != null) {
			return new ResponseEntity<>(updatedHotel, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * Deletes a hotel from the database by its ID.
	 *
	 * @param hotelId The ID of the hotel to be deleted.
	 * @return The deleted hotel with HttpStatus.OK status if successful, or
	 *         HttpStatus.NOT_FOUND if the hotel does not exist.
	 */
	@DeleteMapping("/{hotelId}")
	public ResponseEntity<Hotel> deleteHotelHandler(@PathVariable Integer hotelId) {
		Hotel deletedHotel = hotelService.deleteHotelById(hotelId);
		if (deletedHotel != null) {
			return new ResponseEntity<>(deletedHotel, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * Searches for hotels by location.
	 *
	 * @param location The location to search for hotels.
	 * @return A list of hotels with HttpStatus.OK status if found, or
	 *         HttpStatus.NOT_FOUND if no matching hotels are found.
	 */
	@GetMapping("/search/location")
	public ResponseEntity<List<Hotel>> searchHotelsByLocationHandler(@RequestParam String location) {
		List<Hotel> hotels = hotelService.searchHotelsByLocation(location);
		return new ResponseEntity<>(hotels, HttpStatus.OK);
	}

	/**
	 * Searches for hotels by ticket price range.
	 *
	 * @param minPrice The minimum ticket price.
	 * @param maxPrice The maximum ticket price.
	 * @return A list of hotels with HttpStatus.OK status if found, or
	 *         HttpStatus.NOT_FOUND if no matching hotels are found.
	 */
	@GetMapping("/search/price")
	public ResponseEntity<List<Hotel>> searchHotelsByPriceRangeHandler(@RequestParam Double minPrice,
			@RequestParam Double maxPrice) {
		List<Hotel> hotels = hotelService.searchHotelsByPriceRange(minPrice, maxPrice);
		return new ResponseEntity<>(hotels, HttpStatus.OK);
	}

	/**
	 * Checks if a specific hotel has available rooms for a given number of required
	 * rooms.
	 *
	 * @param hotelId       The ID of the hotel to check.
	 * @param requiredRooms The number of rooms required.
	 * @return true with HttpStatus.OK status if rooms are available, false with
	 *         HttpStatus.OK status if rooms are not available, or
	 *         HttpStatus.NOT_FOUND if the hotel does not exist.
	 */
	@GetMapping("/check-availability/{hotelId}")
	public ResponseEntity<Boolean> checkHotelAvailabilityHandler(@PathVariable Integer hotelId,
			@RequestParam Integer requiredRooms) {
		boolean isAvailable = hotelService.checkHotelAvailability(hotelId, requiredRooms);
		return new ResponseEntity<>(isAvailable, HttpStatus.OK);
	}
}