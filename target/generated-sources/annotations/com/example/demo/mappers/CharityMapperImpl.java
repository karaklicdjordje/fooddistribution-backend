package com.example.demo.mappers;

import com.example.demo.dto.AddressDTO;
import com.example.demo.dto.CharityDTO;
import com.example.demo.dto.RegistrationDTO;
import com.example.demo.dto.UserDTO;
import com.example.demo.entities.Address;
import com.example.demo.entities.Address.AddressBuilder;
import com.example.demo.entities.Charity;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-06-28T22:54:39+0200",
    comments = "version: 1.4.2.Final, compiler: Eclipse JDT (IDE) 1.3.1200.v20200916-0645, environment: Java 15.0.1 (Oracle Corporation)"
)
public class CharityMapperImpl implements CharityMapper {

    @Override
    public UserDTO entityToDto(Charity charity) {
        if ( charity == null ) {
            return null;
        }

        UserDTO userDTO = new UserDTO();

        userDTO.setAddress( addressToAddressDTO( charity.getAddress() ) );
        userDTO.setEmail( charity.getEmail() );
        userDTO.setId( charity.getId() );
        userDTO.setRole( charity.getRole() );
        userDTO.setUsername( charity.getUsername() );

        return userDTO;
    }

    @Override
    public Charity dtoToEntity(UserDTO userDTO) {
        if ( userDTO == null ) {
            return null;
        }

        Charity charity = new Charity();

        charity.setAddress( addressDTOToAddress( userDTO.getAddress() ) );
        charity.setRole( userDTO.getRole() );
        charity.setUsername( userDTO.getUsername() );
        charity.setEmail( userDTO.getEmail() );
        charity.setId( userDTO.getId() );

        return charity;
    }

    @Override
    public CharityDTO entitysToDto(Charity charity) {
        if ( charity == null ) {
            return null;
        }

        CharityDTO charityDTO = new CharityDTO();

        charityDTO.setEmail( charity.getEmail() );
        charityDTO.setId( charity.getId() );
        charityDTO.setName( charity.getName() );

        return charityDTO;
    }

    @Override
    public Charity dtoToEntity(CharityDTO charityDto) {
        if ( charityDto == null ) {
            return null;
        }

        Charity charity = new Charity();

        charity.setEmail( charityDto.getEmail() );
        charity.setId( charityDto.getId() );
        charity.setName( charityDto.getName() );

        return charity;
    }

    @Override
    public Charity dtoToEntity(RegistrationDTO registrationDTO) {
        if ( registrationDTO == null ) {
            return null;
        }

        Charity charity = new Charity();

        charity.setAddress( addressDTOToAddress( registrationDTO.getAddress() ) );
        charity.setPassword( registrationDTO.getPassword() );
        charity.setRole( registrationDTO.getRole() );
        charity.setUsername( registrationDTO.getUsername() );
        charity.setEmail( registrationDTO.getEmail() );
        charity.setName( registrationDTO.getName() );

        return charity;
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
