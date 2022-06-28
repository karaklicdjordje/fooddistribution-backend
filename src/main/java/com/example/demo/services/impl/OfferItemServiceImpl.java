package com.example.demo.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.OfferItemDTO;
import com.example.demo.entities.Food;
import com.example.demo.entities.OfferItem;
import com.example.demo.mappers.OfferItemMapper;
import com.example.demo.mappers.OfferMapper;
import com.example.demo.repositories.FoodRepository;
import com.example.demo.repositories.OfferItemRepository;
import com.example.demo.services.OfferItemService;

@Service
public class OfferItemServiceImpl implements OfferItemService {

	@Autowired
	private OfferItemRepository offerItemRepository;
	
	@Autowired
	private FoodRepository foodRepository;
	
	@Autowired
	private OfferItemMapper offerItemMapper;

	@Override
	public List<OfferItemDTO> findAll() {
		List<OfferItem> offerItems = offerItemRepository.findAll();
		return offerItems.stream().map(offerItem -> offerItemMapper.entityToDTO(offerItem))
				.collect(Collectors.toList());

	}

	@Override
	public OfferItemDTO findByID(Long id) {
		OfferItem offerItem = offerItemRepository.getById(id);
		return offerItemMapper.entityToDTO(offerItem);
	}

	@Override
	public OfferItemDTO createOfferItem(Integer quantity, Long foodId) {
		Food food = foodRepository.findById(foodId).orElseThrow(IllegalArgumentException::new);
		OfferItem offerItem = OfferItem.builder().food(food).quantity(quantity).build();
		offerItemRepository.save(offerItem);
		return offerItemMapper.entityToDTO(offerItem);
	}

	@Override
	public OfferItemDTO updateOfferItem(OfferItemDTO offerItemDto, Long id) {
		OfferItem offerItem = offerItemRepository.getById(id);
		offerItemDto.setId(offerItem.getId());
		offerItem = offerItemMapper.dtoToEntity(offerItemDto);
		offerItemRepository.save(offerItem);
		Food food = offerItem.getFood();
		food.getOfferItems().add(offerItem);
		foodRepository.save(food);
		return offerItemMapper.entityToDTO(offerItem);
	}

	@Override
	public void deleteOfferItem(Long id) {
		if (!offerItemRepository.existsById(id)) {
			throw new IllegalArgumentException();
		}
		offerItemRepository.deleteById(id);
	}
}
