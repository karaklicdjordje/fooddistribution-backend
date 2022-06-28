package com.example.demo.mappers;

import com.example.demo.dto.AddressDTO;
import com.example.demo.dto.UserDTO;
import com.example.demo.entities.Address;
import com.example.demo.entities.Address.AddressBuilder;
import com.example.demo.entities.User;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-06-28T22:54:40+0200",
    comments = "version: 1.4.2.Final, compiler: Eclipse JDT (IDE) 1.3.1200.v20200916-0645, environment: Java 15.0.1 (Oracle Corporation)"
)
public class UserMapperImpl implements UserMapper {

    @Override
    public UserDTO entityToDto(User user) {
        if ( user == null ) {
            return null;
        }

        UserDTO userDTO = new UserDTO();

        userDTO.setAddress( addressToAddressDTO( user.getAddress() ) );
        userDTO.setEmail( user.getEmail() );
        userDTO.setId( user.getId() );
        userDTO.setRole( user.getRole() );
        userDTO.setUsername( user.getUsername() );

        return userDTO;
    }

    @Override
    public User dtoToEntity(UserDTO userDTO) {
        if ( userDTO == null ) {
            return null;
        }

        User user = new User();

        user.setAddress( addressDTOToAddress( userDTO.getAddress() ) );
        user.setEmail( userDTO.getEmail() );
        user.setId( userDTO.getId() );
        user.setRole( userDTO.getRole() );
        user.setUsername( userDTO.getUsername() );

        return user;
    }

    protected AddressDTO addressToAddressDTO(Address address) {
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
