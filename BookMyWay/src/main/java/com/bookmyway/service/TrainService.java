package com.bookmyway.service;

import java.util.List;

import com.bookmyway.exception.TrainException;
import com.bookmyway.model.Train;

public interface TrainService {
	
	Train addTrain(Train train) throws TrainException;

	List<Train> getAllTrains() throws TrainException;

	Train getTrainByName(String trainName) throws TrainException;

	Train getTrainById(Integer trainId) throws TrainException;

	Train updateTrain(Integer trainId, Train train) throws TrainException;

	Train deleteTrainById(Integer trainId) throws TrainException;

	List<Train> searchTrainsByRoute(String departureStation, String destinationStation) throws TrainException;

	List<Train> searchTrainsByPriceRange(Double minPrice, Double maxPrice) throws TrainException;

	boolean checkTrainAvailability(Integer trainId, Integer requiredSeats) throws TrainException;
	

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
