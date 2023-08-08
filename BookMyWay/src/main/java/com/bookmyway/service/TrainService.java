package com.bookmyway.service;

import java.util.List;

import com.bookmyway.model.Train;

public interface TrainService {
	Train addTrain(Train train);

	List<Train> getAllTrains();

	Train getTrainByName(String trainName);

	Train getTrainById(Integer trainId);

	Train updateTrain(Integer trainId, Train train);

	Train deleteTrainById(Integer trainId);

	List<Train> searchTrainsByRoute(String departureStation, String destinationStation);

	List<Train> searchTrainsByPriceRange(Double minPrice, Double maxPrice);

	boolean checkTrainAvailability(Integer trainId, Integer requiredSeats);
}
