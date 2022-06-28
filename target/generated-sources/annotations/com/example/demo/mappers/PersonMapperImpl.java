package com.example.demo.mappers;

import com.example.demo.dto.AddressDTO;
import com.example.demo.dto.PersonDTO;
import com.example.demo.dto.RegistrationDTO;
import com.example.demo.dto.UserDTO;
import com.example.demo.entities.Address;
import com.example.demo.entities.Address.AddressBuilder;
import com.example.demo.entities.Person;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-06-28T22:54:39+0200",
    comments = "version: 1.4.2.Final, compiler: Eclipse JDT (IDE) 1.3.1200.v20200916-0645, environment: Java 15.0.1 (Oracle Corporation)"
)
public class PersonMapperImpl implements PersonMapper {

    @Override
    public UserDTO entityToDto(Person person) {
        if ( person == null ) {
            return null;
        }

        UserDTO userDTO = new UserDTO();

        userDTO.setAddress( addressToAddressDTO( person.getAddress() ) );
        userDTO.setEmail( person.getEmail() );
        userDTO.setId( person.getId() );
        userDTO.setJmbg( person.getJmbg() );
        userDTO.setRole( person.getRole() );
        userDTO.setUsername( person.getUsername() );

        return userDTO;
    }

    @Override
    public Person dtoToEntity(UserDTO userDTO) {
        if ( userDTO == null ) {
            return null;
        }

        Person person = new Person();

        person.setAddress( addressDTOToAddress( userDTO.getAddress() ) );
        person.setEmail( userDTO.getEmail() );
        person.setRole( userDTO.getRole() );
        person.setUsername( userDTO.getUsername() );
        person.setId( userDTO.getId() );
        person.setJmbg( userDTO.getJmbg() );

        return person;
    }

    @Override
    public PersonDTO entitysToDto(Person person) {
        if ( person == null ) {
            return null;
        }

        PersonDTO personDTO = new PersonDTO();

        personDTO.setEmail( person.getEmail() );
        personDTO.setId( person.getId() );
        personDTO.setJmbg( person.getJmbg() );
        personDTO.setName( person.getName() );
        personDTO.setPassword( person.getPassword() );
        personDTO.setSurname( person.getSurname() );
        personDTO.setUsername( person.getUsername() );

        return personDTO;
    }

    @Override
    public Person dtoToEntity(PersonDTO personDTO) {
        if ( personDTO == null ) {
            return null;
        }

        Person person = new Person();

        person.setEmail( personDTO.getEmail() );
        person.setPassword( personDTO.getPassword() );
        person.setUsername( personDTO.getUsername() );
        person.setId( personDTO.getId() );
        person.setJmbg( personDTO.getJmbg() );
        person.setName( personDTO.getName() );
        person.setSurname( personDTO.getSurname() );

        return person;
    }

    @Override
    public Person dtoToEntity(RegistrationDTO registrationDTO) {
        if ( registrationDTO == null ) {
            return null;
        }

        Person person = new Person();

        person.setAddress( addressDTOToAddress( registrationDTO.getAddress() ) );
        person.setEmail( registrationDTO.getEmail() );
        person.setPassword( registrationDTO.getPassword() );
        person.setRole( registrationDTO.getRole() );
        person.setUsername( registrationDTO.getUsername() );
        person.setJmbg( registrationDTO.getJmbg() );
        person.setName( registrationDTO.getName() );
        person.setSurname( registrationDTO.getSurname() );

        return person;
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
