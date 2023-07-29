package com.bookmyway.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Hotel {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer hotelId;

	@NotBlank(message = "Hotel name cannot be blank")
	private String hotelName;

	@NotBlank(message = "Image URL cannot be blank")
	private String image;

	@NotBlank(message = "Category cannot be blank")
	private String category;

	@NotBlank(message = "Description cannot be blank")
	private String description;

	@DecimalMin(value = "0.0", message = "Price must be a non-negative value")
	private double price;

	@Min(value = 0, message = "Available rooms must be a non-negative integer")
	private int availableRooms;

	@Min(value = 0, message = "Total rooms must be a non-negative integer")
	private int totalRooms;

	@DecimalMin(value = "0.0", message = "Rating must be a non-negative value")
	@DecimalMax(value = "5.0", message = "Rating cannot exceed 5.0")
	private double rating;


    @OneToMany(mappedBy = "hotel", cascade = CascadeType.ALL)
    private List<Booking> bookings = new ArrayList<>();

    @OneToMany(mappedBy = "hotel", cascade = CascadeType.ALL)
    private List<Feedback> feedbacks = new ArrayList<>();

    @OneToMany(mappedBy = "hotel", cascade = CascadeType.ALL)
    private List<Customer> customers = new ArrayList<>();
	
}