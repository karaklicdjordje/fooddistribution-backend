package com.example.demo.dto;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import com.example.demo.entities.OfferItem;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class OfferDTO {

	private Long id;
	
	private Long restaurantId;
	
	private Set<OfferItemDTO> offerItems = new HashSet<>();
	
	private LocalDate date;
	
	private boolean expired;
}