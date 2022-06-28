package com.example.demo.mappers;

import com.example.demo.dto.AddressDTO;
import com.example.demo.entities.Address;
import com.example.demo.entities.Address.AddressBuilder;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-06-28T22:54:39+0200",
    comments = "version: 1.4.2.Final, compiler: Eclipse JDT (IDE) 1.3.1200.v20200916-0645, environment: Java 15.0.1 (Oracle Corporation)"
)
public class AddressMapperImpl implements AddressMapper {

    @Override
    public AddressDTO entityToDTO(Address address) {
        if ( address == null ) {
            return null;
        }

        AddressDTO addressDTO = new AddressDTO();

        addressDTO.setAddressNumber( address.getAddressNumber() );
        addressDTO.setCity( address.getCity() );
        addressDTO.setId( address.getId() );
        addressDTO.setStreet( address.getStreet() );

        return addressDTO;
    }

    @Override
    public Address dtoToEntity(AddressDTO addressDTo) {
        if ( addressDTo == null ) {
            return null;
        }

        AddressBuilder address = Address.builder();

        address.addressNumber( addressDTo.getAddressNumber() );
        address.city( addressDTo.getCity() );
        address.id( addressDTo.getId() );
        address.street( addressDTo.getStreet() );

        return address.build();
    }
}
