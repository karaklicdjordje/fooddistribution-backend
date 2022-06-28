package com.example.demo.mappers;

import com.example.demo.dto.AddressDTO;
import com.example.demo.dto.RegistrationDTO;
import com.example.demo.entities.Address;
import com.example.demo.entities.Address.AddressBuilder;
import com.example.demo.entities.User;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-06-28T22:54:40+0200",
    comments = "version: 1.4.2.Final, compiler: Eclipse JDT (IDE) 1.3.1200.v20200916-0645, environment: Java 15.0.1 (Oracle Corporation)"
)
public class RegistrationMapperImpl implements RegistrationMapper {

    @Override
    public User dtoToEntity(RegistrationDTO registrationDTO) {
        if ( registrationDTO == null ) {
            return null;
        }

        User user = new User();

        user.setAddress( addressDTOToAddress( registrationDTO.getAddress() ) );
        user.setEmail( registrationDTO.getEmail() );
        user.setPassword( registrationDTO.getPassword() );
        user.setRole( registrationDTO.getRole() );
        user.setUsername( registrationDTO.getUsername() );

        return user;
    }

    protected Address addressDTOToAddress(AddressDTO addressDTO) {
        if ( addressDTO == null ) {
            return null;
        }

        AddressBuilder address = Address.builder();

        address.addressNumber( addressDTO.getAddressNumber() );
        address.city( addressDTO.getCity() );
        address.id( addressDTO.getId() );
        address.street( addressDTO.getStreet() );

        return address.build();
    }
}
