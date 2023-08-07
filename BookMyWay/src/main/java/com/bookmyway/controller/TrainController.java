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

import com.bookmyway.model.Train;
import com.bookmyway.service.TrainService;


/**
 * TrainController handles HTTP requests related to Train entities. It provides
 * CRUD operations for trains and additional methods for searching and checking
 * availability.
 *
 * @Author HoshiyarJyani
 */
@RestController
@RequestMapping("/trains")
public class TrainController {

	@Autowired
	private TrainService trainService;

	/**
	 * Adds a new train to the database.
	 *
	 * @param train The train object to be added.
	 * @return The added train with HttpStatus.CREATED status if successful.
	 */
	@PostMapping
	public ResponseEntity<Train> addTrainHandler(@RequestBody Train train) {
		Train addedTrain = trainService.addTrain(train);
		return new ResponseEntity<>(addedTrain, HttpStatus.CREATED);
	}

	/**
	 * Retrieves a list of all trains from the database.
	 *
	 * @return A list of all trains with HttpStatus.OK status if successful.
	 */
	@GetMapping
	public ResponseEntity<List<Train>> getAllTrainsHandler() {
		List<Train> trains = trainService.getAllTrains();
		return new ResponseEntity<>(trains, HttpStatus.OK);
	}

	/**
	 * Retrieves a train by its name from the database.
	 *
	 * @param trainName The name of the train to be retrieved.
	 * @return The train with HttpStatus.OK status if found, or HttpStatus.NOT_FOUND
	 *         if not found.
	 */
	@GetMapping("/{trainName}")
	public ResponseEntity<Train> getTrainByNameHandler(@PathVariable String trainName) {
		Train train = trainService.getTrainByName(trainName);
		if (train != null) {
			return new ResponseEntity<>(train, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * Retrieves a train by its ID from the database.
	 *
	 * @param trainId The ID of the train to be retrieved.
	 * @return The train with HttpStatus.OK status if found, or HttpStatus.NOT_FOUND
	 *         if not found.
	 */
	@GetMapping("/{trainId}")
	public ResponseEntity<Train> getTrainByIdHandler(@PathVariable Integer trainId) {
		Train train = trainService.getTrainById(trainId);
		if (train != null) {
			return new ResponseEntity<>(train, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * Updates information of a train in the database.
	 *
	 * @param trainId The ID of the train to be updated.
	 * @param train   The updated train object.
	 * @return The updated train with HttpStatus.OK status if successful, or
	 *         HttpStatus.NOT_FOUND if the train does not exist.
	 */
	@PutMapping("/{trainId}")
	public ResponseEntity<Train> updateTrainHandler(@PathVariable Integer trainId, @RequestBody Train train) {
		Train updatedTrain = trainService.updateTrain(trainId, train);
		if (updatedTrain != null) {
			return new ResponseEntity<>(updatedTrain, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * Deletes a train from the database by its ID.
	 *
	 * @param trainId The ID of the train to be deleted.
	 * @return The deleted train with HttpStatus.OK status if successful, or
	 *         HttpStatus.NOT_FOUND if the train does not exist.
	 */
	@DeleteMapping("/{trainId}")
	public ResponseEntity<Train> deleteTrainHandler(@PathVariable Integer trainId) {
		Train deletedTrain = trainService.deleteTrainById(trainId);
		if (deletedTrain != null) {
			return new ResponseEntity<>(deletedTrain, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * Searches for trains by departure and destination stations.
	 *
	 * @param departureStation   The departure station to search for trains.
	 * @param destinationStation The destination station to search for trains.
	 * @return A list of trains with HttpStatus.OK status if found, or
	 *         HttpStatus.NOT_FOUND if no matching trains are found.
	 */
	@GetMapping("/search/route")
	public ResponseEntity<List<Train>> searchTrainsByRouteHandler(@RequestParam String departureStation,
			@RequestParam String destinationStation) {
		List<Train> trains = trainService.searchTrainsByRoute(departureStation, destinationStation);
		return new ResponseEntity<>(trains, HttpStatus.OK);
	}

	/**
	 * Searches for trains by ticket price range.
	 *
	 * @param minPrice The minimum ticket price.
	 * @param maxPrice The maximum ticket price.
	 * @return A list of trains with HttpStatus.OK status if found, or
	 *         HttpStatus.NOT_FOUND if no matching trains are found.
	 */
	@GetMapping("/search/price")
	public ResponseEntity<List<Train>> searchTrainsByPriceRangeHandler(@RequestParam Double minPrice,
			@RequestParam Double maxPrice) {
		List<Train> trains = trainService.searchTrainsByPriceRange(minPrice, maxPrice);
		return new ResponseEntity<>(trains, HttpStatus.OK);
	}

	/**
	 * Checks if a specific train has available seats for a given number of required
	 * seats.
	 *
	 * @param trainId       The ID of the train to check.
	 * @param requiredSeats The number of seats required.
	 * @return true with HttpStatus.OK status if seats are available, false with
	 *         HttpStatus.OK status if seats are not available, or
	 *         HttpStatus.NOT_FOUND if the train does not exist.
	 */
	@GetMapping("/check-availability/{trainId}")
	public ResponseEntity<Boolean> checkTrainAvailabilityHandler(@PathVariable Integer trainId,
			@RequestParam Integer requiredSeats) {
		boolean isAvailable = trainService.checkTrainAvailability(trainId, requiredSeats);
		return new ResponseEntity<>(isAvailable, HttpStatus.OK);
	}
}
