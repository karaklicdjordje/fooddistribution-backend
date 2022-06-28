package com.example.demo.mappers;

import com.example.demo.dto.CityDTO;
import com.example.demo.entities.Address;
import com.example.demo.entities.City;
import java.util.HashSet;
import java.util.Set;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-06-28T22:54:39+0200",
    comments = "version: 1.4.2.Final, compiler: Eclipse JDT (IDE) 1.3.1200.v20200916-0645, environment: Java 15.0.1 (Oracle Corporation)"
)
public class CityMapperImpl implements CityMapper {

    @Override
    public CityDTO entityToDTO(City city) {
        if ( city == null ) {
            return null;
        }

        CityDTO cityDTO = new CityDTO();

        Set<Address> set = city.getAddress();
        if ( set != null ) {
            cityDTO.setAddress( new HashSet<Address>( set ) );
        }
        cityDTO.setId( city.getId() );
        cityDTO.setName( city.getName() );
        cityDTO.setZipCode( city.getZipCode() );

        return cityDTO;
    }

    @Override
    public City dtoToEntity(CityDTO cityDto) {
        if ( cityDto == null ) {
            return null;
        }

        City city = new City();

        Set<Address> set = cityDto.getAddress();
        if ( set != null ) {
            city.setAddress( new HashSet<Address>( set ) );
        }
        city.setId( cityDto.getId() );
        city.setName( cityDto.getName() );
        city.setZipCode( cityDto.getZipCode() );

        return city;
    }
}
