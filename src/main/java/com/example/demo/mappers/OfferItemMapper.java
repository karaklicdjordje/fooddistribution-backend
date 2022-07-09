package com.example.demo.mappers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.dto.FoodDTO;
import com.example.demo.dto.OfferItemDTO;
import com.example.demo.entities.Food;
import com.example.demo.entities.Offer;
import com.example.demo.entities.OfferItem;
import com.example.demo.entities.OfferItem.OfferItemBuilder;
import com.example.demo.repositories.FoodRepository;
import com.example.demo.repositories.OfferRepository;
import com.example.demo.utils.EntityHelper;

@Component
public class OfferItemMapper {
	
	@Autowired
	FoodRepository foodRepository;
	
	@Autowired
	OfferRepository offerRepository;

    public OfferItemDTO entityToDTO(OfferItem offerItem) {
        if ( offerItem == null ) {
            return null;
        }

        OfferItemDTO offerItemDTO = new OfferItemDTO();

        offerItemDTO.setFood( offerItemFood( offerItem ) );
        offerItemDTO.setOfferId( offerItemOfferId( offerItem ) );
        offerItemDTO.setId( offerItem.getId() );
        offerItemDTO.setQuantity( offerItem.getQuantity() );

        return offerItemDTO;
    }

    public OfferItem dtoToEntity(OfferItemDTO offerItemDTo) {
        if ( offerItemDTo == null ) {
            return null;
        }

        OfferItemBuilder offerItem = OfferItem.builder();

        offerItem.food( offerItemDTOToFood( offerItemDTo ) );
        offerItem.offer( offerItemDTOToOffer( offerItemDTo ) );
        offerItem.id( offerItemDTo.getId() );
        offerItem.quantity( offerItemDTo.getQuantity() );

        return offerItem.build();
    }

    private FoodDTO offerItemFood(OfferItem offerItem) {
        if ( offerItem == null ) {
            return null;
        }
        Food food = offerItem.getFood();
        if ( food == null ) {
            return null;
        }
        
        FoodDTO foodDto = FoodMapper.INSTANCE.entityToDto(food);

        return foodDto;
    }

    private Long offerItemOfferId(OfferItem offerItem) {
        if ( offerItem == null ) {
            return null;
        }
        Offer offer = offerItem.getOffer();
        if ( offer == null ) {
            return null;
        }
        Long id = offer.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    protected Food offerItemDTOToFood(OfferItemDTO offerItemDTO) {
        if ( offerItemDTO == null ) {
            return null;
        }

        Food food = EntityHelper.getEntity(offerItemDTO.getFood().getId(), foodRepository);

        return food;
    }

    protected Offer offerItemDTOToOffer(OfferItemDTO offerItemDTO) {
        if ( offerItemDTO == null ) {
            return null;
        }

        Offer offer = EntityHelper.getEntity(offerItemDTO.getOfferId(), offerRepository);

        return offer;
    }
}
