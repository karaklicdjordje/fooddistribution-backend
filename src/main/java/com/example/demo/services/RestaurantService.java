package com.example.demo.services;

import java.util.List;

import com.example.demo.dto.RestaurantDTO;
import com.example.demo.dto.UserDTO;

public interface RestaurantService {
	
	List<RestaurantDTO> findAll ();
	
	RestaurantDTO findById (Long id);
	
	RestaurantDTO createRestaurant(RestaurantDTO restaurantDto);
	
	RestaurantDTO updateRestaurant(RestaurantDTO restaurantDto, Long id);
	
	void deleteResaurant (Long id);

	void subscribeUserToRestaurant(Long restaurantId, Long userId); 
	
	public List<UserDTO> getAllSubscribers(Long restaurantId);
	
	

}
