package com.example.demo.mappers;

import com.example.demo.dto.FoodDTO;
import com.example.demo.entities.Food;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-06-28T22:54:39+0200",
    comments = "version: 1.4.2.Final, compiler: Eclipse JDT (IDE) 1.3.1200.v20200916-0645, environment: Java 15.0.1 (Oracle Corporation)"
)
public class FoodMapperImpl implements FoodMapper {

    @Override
    public FoodDTO entityToDto(Food food) {
        if ( food == null ) {
            return null;
        }

        FoodDTO foodDTO = new FoodDTO();

        foodDTO.setId( food.getId() );
        foodDTO.setName( food.getName() );
        foodDTO.setPrice( food.getPrice() );
        foodDTO.setTypeOfFood( food.getTypeOfFood() );

        return foodDTO;
    }

    @Override
    public Food dtoToEntity(FoodDTO foodDTO) {
        if ( foodDTO == null ) {
            return null;
        }

        Food food = new Food();

        food.setId( foodDTO.getId() );
        food.setName( foodDTO.getName() );
        food.setPrice( foodDTO.getPrice() );
        food.setTypeOfFood( foodDTO.getTypeOfFood() );

        return food;
    }
}
