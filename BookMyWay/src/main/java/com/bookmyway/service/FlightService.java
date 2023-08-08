package com.bookmyway.service;

import java.util.List;

import com.bookmyway.model.Flight;

public interface FlightService {
	Flight addFlight(Flight flight);

	List<Flight> getAllFlights();

	Flight getFlightByName(String flightName);

	Flight getFlightById(Integer flightId);

	Flight updateFlight(Integer flightId, Flight flight);

	Flight deleteFlightById(Integer flightId);

	List<Flight> searchFlightsByRoute(String departureAirport, String destinationAirport);

	List<Flight> searchFlightsByAirline(String airlineName);

	List<Flight> searchFlightsByPriceRange(Double minPrice, Double maxPrice);

	boolean checkFlightAvailability(Integer flightId, Integer requiredSeats);
}
