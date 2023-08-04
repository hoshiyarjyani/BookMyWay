package com.bookmyway.service;

import java.util.List;

import com.bookmyway.model.Bus;

public interface BusService {
	Bus addBus(Bus bus);

	List<Bus> getAllBuses();

	Bus getBusById(Integer busId);

	Bus updateBus(Integer busId, Bus bus);

	Bus deleteBus(Integer busId);

	List<Bus> searchBusesByRoute(String departureCity, String destinationCity);

	List<Bus> searchBusesByPriceRange(Double minPrice, Double maxPrice);

	boolean checkBusAvailability(Integer busId, Integer requiredSeats);
}
