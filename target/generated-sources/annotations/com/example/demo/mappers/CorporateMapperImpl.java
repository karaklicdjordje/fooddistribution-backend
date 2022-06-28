package com.example.demo.mappers;

import com.example.demo.dto.AddressDTO;
import com.example.demo.dto.CorporateDTO;
import com.example.demo.dto.RegistrationDTO;
import com.example.demo.dto.UserDTO;
import com.example.demo.entities.Address;
import com.example.demo.entities.Address.AddressBuilder;
import com.example.demo.entities.Corporate;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-06-28T22:54:39+0200",
    comments = "version: 1.4.2.Final, compiler: Eclipse JDT (IDE) 1.3.1200.v20200916-0645, environment: Java 15.0.1 (Oracle Corporation)"
)
public class CorporateMapperImpl implements CorporateMapper {

    @Override
    public UserDTO entityToDto(Corporate corporate) {
        if ( corporate == null ) {
            return null;
        }

        UserDTO userDTO = new UserDTO();

        userDTO.setAddress( addressToAddressDTO( corporate.getAddress() ) );
        userDTO.setEmail( corporate.getEmail() );
        userDTO.setId( corporate.getId() );
        userDTO.setPib( corporate.getPib() );
        userDTO.setRole( corporate.getRole() );
        userDTO.setUsername( corporate.getUsername() );

        return userDTO;
    }

    @Override
    public Corporate dtoToEntity(UserDTO userDTO) {
        if ( userDTO == null ) {
            return null;
        }

        Corporate corporate = new Corporate();

        corporate.setAddress( addressDTOToAddress( userDTO.getAddress() ) );
        corporate.setRole( userDTO.getRole() );
        corporate.setUsername( userDTO.getUsername() );
        corporate.setEmail( userDTO.getEmail() );
        corporate.setId( userDTO.getId() );
        corporate.setPib( userDTO.getPib() );

        return corporate;
    }

    @Override
    public Corporate dtoToEntity(CorporateDTO corporateDto) {
        if ( corporateDto == null ) {
            return null;
        }

        Corporate corporate = new Corporate();

        corporate.setEmail( corporateDto.getEmail() );
        corporate.setId( corporateDto.getId() );
        corporate.setName( corporateDto.getName() );
        corporate.setPib( corporateDto.getPib() );

        return corporate;
    }

    @Override
    public CorporateDTO entitysToDto(Corporate corporate) {
        if ( corporate == null ) {
            return null;
        }

        CorporateDTO corporateDTO = new CorporateDTO();

        corporateDTO.setEmail( corporate.getEmail() );
        corporateDTO.setId( corporate.getId() );
        corporateDTO.setName( corporate.getName() );
        corporateDTO.setPib( corporate.getPib() );

        return corporateDTO;
    }

    @Override
    public Corporate dtoToEntity(RegistrationDTO registrationDTO) {
        if ( registrationDTO == null ) {
            return null;
        }

        Corporate corporate = new Corporate();

        corporate.setAddress( addressDTOToAddress( registrationDTO.getAddress() ) );
        corporate.setPassword( registrationDTO.getPassword() );
        corporate.setRole( registrationDTO.getRole() );
        corporate.setUsername( registrationDTO.getUsername() );
        corporate.setEmail( registrationDTO.getEmail() );
        corporate.setName( registrationDTO.getName() );
        corporate.setPib( registrationDTO.getPib() );

        return corporate;
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
