package com.example.demo.mappers;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.dto.OfferDTO;
import com.example.demo.dto.OfferItemDTO;
import com.example.demo.entities.Offer;
import com.example.demo.entities.OfferItem;
import com.example.demo.entities.Restaurant;
import com.example.demo.repositories.RestaurantRepository;
import com.example.demo.utils.EntityHelper;

@Component
public class OfferMapper{
	
	@Autowired
	private RestaurantRepository restaurantRepository;
	
	@Autowired
	OfferItemMapper offerItemMapper;

    public OfferDTO entityToDTO(Offer offer) {
        if ( offer == null ) {
            return null;
        }

        OfferDTO offerDTO = new OfferDTO();

        offerDTO.setRestaurantId( offerRestaurantId( offer ) );
        offerDTO.setDate( offer.getDate() );
        offerDTO.setExpired( offer.isExpired() );
        offerDTO.setId( offer.getId() );
        offerDTO.setOfferItems( offerItemSetToOfferItemDTOSet( offer.getOfferItems() ) );

        return offerDTO;
    }

    public Offer dtoToEntity(OfferDTO offerDTO) {
        if ( offerDTO == null ) {
            return null;
        }

        Offer offer = new Offer();

        offer.setRestaurant( offerDTOToRestaurant( offerDTO ) );
        offer.setDate( offerDTO.getDate() );
        offer.setExpired( offerDTO.isExpired() );
        offer.setId( offerDTO.getId() );
        offer.setOfferItems( offerItemDTOSetToOfferItemSet( offerDTO.getOfferItems() ) );

        return offer;
    }

    private Long offerRestaurantId(Offer offer) {
        if ( offer == null ) {
            return null;
        }
        Restaurant restaurant = offer.getRestaurant();
        if ( restaurant == null ) {
            return null;
        }
        Long id = restaurant.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    protected OfferItemDTO offerItemToOfferItemDTO(OfferItem offerItem) {
        if ( offerItem == null ) {
            return null;
        }

        return offerItemMapper.entityToDTO(offerItem);
    }

    protected Set<OfferItemDTO> offerItemSetToOfferItemDTOSet(Set<OfferItem> set) {
        if ( set == null ) {
            return null;
        }

        Set<OfferItemDTO> set1 = new HashSet<OfferItemDTO>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( OfferItem offerItem : set ) {
            set1.add( offerItemToOfferItemDTO( offerItem ) );
        }

        return set1;
    }

    protected Restaurant offerDTOToRestaurant(OfferDTO offerDTO) {
        if ( offerDTO == null ) {
            return null;
        }

        Restaurant restaurant = EntityHelper.getEntity(offerDTO.getRestaurantId(), restaurantRepository);

        return restaurant;
    }

    protected OfferItem offerItemDTOToOfferItem(OfferItemDTO offerItemDTO) {
        if ( offerItemDTO == null ) {
            return null;
        }

        return offerItemMapper.dtoToEntity(offerItemDTO);
    }

    protected Set<OfferItem> offerItemDTOSetToOfferItemSet(Set<OfferItemDTO> set) {
        if ( set == null ) {
            return null;
        }

        Set<OfferItem> set1 = new HashSet<OfferItem>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( OfferItemDTO offerItemDTO : set ) {
            set1.add( offerItemDTOToOfferItem( offerItemDTO ) );
        }

        return set1;
    }
}
