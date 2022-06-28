package com.example.demo.services.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.example.demo.dto.OfferDTO;
import com.example.demo.entities.Offer;
import com.example.demo.entities.OfferItem;
import com.example.demo.exceptions.NotUniqueException;
import com.example.demo.mappers.OfferItemMapper;
import com.example.demo.mappers.OfferMapper;
import com.example.demo.repositories.OfferItemRepository;
import com.example.demo.repositories.OfferRepository;
import com.example.demo.services.OfferService;
import com.example.demo.utils.EntityHelper;

@Service
@EnableAsync
public class OfferServiceImpl implements OfferService {

	@Autowired
	OfferRepository offerRepository;

	@Autowired
	OfferItemRepository offerItemRepository;
	
	@Autowired
	OfferMapper offerMapper;
	
	@Autowired
	OfferItemMapper offerItemMapper;

	@Override
	public List<OfferDTO> findAll() {
		List<Offer> offers = offerRepository.findAll();
		List<OfferDTO> offersDto = new ArrayList<OfferDTO>();

		for (Offer offer : offers) {
			offersDto.add(offerMapper.entityToDTO(offer));

		}

		return offersDto;
	}
	
	@Override
	public List<OfferDTO> findNotExpiredOffer() {
		List<Offer> offers = offerRepository.findByExpired(false);
		List<OfferDTO> offersDto = new ArrayList<OfferDTO>();

		for (Offer offer : offers) {
			offersDto.add(offerMapper.entityToDTO(offer));

		}

		return offersDto;
	}

	@Override
	public OfferDTO findByID(Long id) {
		Offer offer = offerRepository.findById(id).orElseThrow();
		return offerMapper.entityToDTO(offer);
	}

	@Override
	public OfferDTO findByDate(LocalDate date) {
		Offer offer = offerRepository.findByDate(date).orElseThrow();
		OfferDTO offerDTO = offerMapper.entityToDTO(offer);
		return offerDTO;
	}

	@Override
	public OfferDTO createOffer(OfferDTO offerDto) {
		Offer offer = new Offer();
		offerRepository.save(offer);
		offerDto.setId(offer.getId());
		Set<OfferItem> offerItems = new HashSet<>();
		offerDto.getOfferItems().forEach(offerItemDto -> {
			offerItemDto.setOfferId(offerDto.getId());
			OfferItem offerItem = offerItemMapper.dtoToEntity(offerItemDto);
			offerItemRepository.save(offerItem);
			offerItems.add(offerItem);
		});
		offer = offerMapper.dtoToEntity(offerDto);
		offer.setOfferItems(offerItems);
		offerRepository.save(offer);
		return offerMapper.entityToDTO(offer);
	}

	@Override
	public OfferDTO updateOffer(OfferDTO offerDto, Long id) {
		Offer offer = EntityHelper.getEntity(id, offerRepository);
		offerDto.setId(id);
		offer = offerMapper.dtoToEntity(offerDto);
		offerRepository.save(offer);
		return offerMapper.entityToDTO(offer);
	}

	@Override
	public void deleteOffer(Long id) {
		boolean hasOffer = offerRepository.existsById(id);
		if (!hasOffer) {
			throw new NotUniqueException("Ovo nije dozvoljeno!");

		}
		offerRepository.deleteById(id);
	}

	@Override
	public OfferDTO addFoodToOffer(Long offerId, Long offerItemId) {
		Offer offer = EntityHelper.getEntity(offerId, offerRepository);

		OfferItem offerItem = EntityHelper.getEntity(offerItemId, offerItemRepository);
		offerItem.setOffer(offer);
		offerItemRepository.save(offerItem);
		
		offer.getOfferItems().add(offerItem);

		return offerMapper.entityToDTO(offer);
	}

	@Override
	public OfferDTO deleteFoodFromOffer(Long offerId, Long offerItemId) {
		
		Offer offer = EntityHelper.getEntity(offerId, offerRepository);

		OfferItem offerItem = EntityHelper.getEntity(offerItemId, offerItemRepository);

		if(offer.getOfferItems().contains(offerItem)) {
			offerItem.setOffer(null);
			offerItemRepository.save(offerItem);
			offer.getOfferItems().remove(offerItem);
		}
		

		return offerMapper.entityToDTO(offer);
	}
	
	@Async
	@Scheduled(cron = "${cron-to-check-offer-expiration}")
	public void setOffersToExpired() {
		List<Offer> offers = offerRepository.findAll();
		LocalDate date = LocalDate.now();
		offers.forEach(offer -> {
			if(!offer.getDate().isAfter(date)) {
				offer.setExpired(true);
			} 
		});
		offerRepository.saveAll(offers);
	}
	

}
