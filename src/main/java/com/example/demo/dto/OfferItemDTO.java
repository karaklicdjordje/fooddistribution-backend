package com.example.demo.dto;



import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OfferItemDTO {

	private Long id;
	
	private Long foodId;
	
	private int quantity;
	
	private Long offerId;
}
