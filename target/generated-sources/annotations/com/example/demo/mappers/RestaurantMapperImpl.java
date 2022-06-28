package com.example.demo.mappers;

import com.example.demo.dto.AddressDTO;
import com.example.demo.dto.RegistrationDTO;
import com.example.demo.dto.RestaurantDTO;
import com.example.demo.dto.UserDTO;
import com.example.demo.entities.Address;
import com.example.demo.entities.Address.AddressBuilder;
import com.example.demo.entities.Restaurant;
import com.example.demo.entities.User;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-06-28T22:54:40+0200",
    comments = "version: 1.4.2.Final, compiler: Eclipse JDT (IDE) 1.3.1200.v20200916-0645, environment: Java 15.0.1 (Oracle Corporation)"
)
public class RestaurantMapperImpl implements RestaurantMapper {

    @Override
    public UserDTO entityToDto(Restaurant restaurant) {
        if ( restaurant == null ) {
            return null;
        }

        UserDTO userDTO = new UserDTO();

        userDTO.setAddress( addressToAddressDTO( restaurant.getAddress() ) );
        userDTO.setEmail( restaurant.getEmail() );
        userDTO.setId( restaurant.getId() );
        userDTO.setPib( restaurant.getPib() );
        userDTO.setRole( restaurant.getRole() );
        userDTO.setUsername( restaurant.getUsername() );

        return userDTO;
    }

    @Override
    public Restaurant dtoToEntity(UserDTO userDTO) {
        if ( userDTO == null ) {
            return null;
        }

        Restaurant restaurant = new Restaurant();

        restaurant.setAddress( addressDTOToAddress( userDTO.getAddress() ) );
        restaurant.setEmail( userDTO.getEmail() );
        restaurant.setRole( userDTO.getRole() );
        restaurant.setUsername( userDTO.getUsername() );
        restaurant.setId( userDTO.getId() );
        restaurant.setPib( userDTO.getPib() );

        return restaurant;
    }

    @Override
    public RestaurantDTO entitysToDTO(Restaurant retaurant) {
        if ( retaurant == null ) {
            return null;
        }

        RestaurantDTO restaurantDTO = new RestaurantDTO();

        restaurantDTO.setAddress( addressToAddressDTO( retaurant.getAddress() ) );
        restaurantDTO.setEmail( retaurant.getEmail() );
        restaurantDTO.setId( retaurant.getId() );
        restaurantDTO.setName( retaurant.getName() );
        restaurantDTO.setPib( retaurant.getPib() );
        restaurantDTO.setUsers( userListToUserDTOSet( retaurant.getUsers() ) );

        return restaurantDTO;
    }

    @Override
    public Restaurant dtoToEntity(RestaurantDTO restaurantDto) {
        if ( restaurantDto == null ) {
            return null;
        }

        Restaurant restaurant = new Restaurant();

        restaurant.setAddress( addressDTOToAddress( restaurantDto.getAddress() ) );
        restaurant.setEmail( restaurantDto.getEmail() );
        restaurant.setId( restaurantDto.getId() );
        restaurant.setName( restaurantDto.getName() );
        restaurant.setPib( restaurantDto.getPib() );
        restaurant.setUsers( userDTOSetToUserList( restaurantDto.getUsers() ) );

        return restaurant;
    }

    @Override
    public Restaurant dtoToEntity(RegistrationDTO registrationDTO) {
        if ( registrationDTO == null ) {
            return null;
        }

        Restaurant restaurant = new Restaurant();

        restaurant.setAddress( addressDTOToAddress( registrationDTO.getAddress() ) );
        restaurant.setEmail( registrationDTO.getEmail() );
        restaurant.setPassword( registrationDTO.getPassword() );
        restaurant.setRole( registrationDTO.getRole() );
        restaurant.setUsername( registrationDTO.getUsername() );
        restaurant.setName( registrationDTO.getName() );
        restaurant.setPib( registrationDTO.getPib() );

        return restaurant;
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

    protected UserDTO userToUserDTO(User user) {
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

    protected Set<UserDTO> userListToUserDTOSet(List<User> list) {
        if ( list == null ) {
            return null;
        }

        Set<UserDTO> set = new HashSet<UserDTO>( Math.max( (int) ( list.size() / .75f ) + 1, 16 ) );
        for ( User user : list ) {
            set.add( userToUserDTO( user ) );
        }

        return set;
    }

    protected List<User> userDTOSetToUserList(Set<UserDTO> set) {
        if ( set == null ) {
            return null;
        }

        List<User> list = new ArrayList<User>( set.size() );
        for ( UserDTO userDTO : set ) {
            list.add( dtoToEntity( userDTO ) );
        }

        return list;
    }
}
