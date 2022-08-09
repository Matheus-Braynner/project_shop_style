package com.example.shopstyle.builder;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.compass.shopstyle.dto.AddressDTO;
import com.compass.shopstyle.dto.AddressFormDTO;
import com.compass.shopstyle.entities.Address;
import com.compass.shopstyle.entities.Customer;
import com.compass.shopstyle.entities.enums.Gender;
import com.compass.shopstyle.entities.enums.States;

public final class AddressBuilder {
	
	public static final Long ID = 1L;
	
	public static final Date BIRTHDAY = new Date();
	
	public static Customer getCustomer() {
		Customer customer = new Customer();
		customer.setId(ID);
		customer.setFirstName("Matheus");
		customer.setLastName("Souza");
		customer.setSex(Gender.MAN);
		customer.setCpf("28006222029");
		customer.setBirthDate(BIRTHDAY);
		customer.setEmail("matheus@email.com");
		customer.setPassword("123456");
		customer.setActive(true);
		return customer;
	}
	
	public static List<Address> getListAddress() {
		Address address1 = new Address();
		
		address1.setId(ID);
		address1.setState(States.PERNAMBUCO);
		address1.setCity("Recife");
		address1.setDistrict("Boa viagem");
		address1.setStreet("Av. Boa viagem");
		address1.setNumber("902");
		address1.setCep("60530-200");
		address1.setComplement("casa");
		address1.setCustomerId(getCustomer());
		
		Address address2 = new Address();
		
		address2.setId(ID);
		address2.setState(States.PERNAMBUCO);
		address2.setCity("Recife");
		address2.setDistrict("Campo grande");
		address2.setStreet("Hipodromo");
		address2.setNumber("902");
		address2.setCep("60530-200");
		address2.setComplement("casa");
		address2.setCustomerId(getCustomer());
		
		List<Address> addresses = new ArrayList<>();
		addresses.add(address1);
		addresses.add(address2);
		
		return addresses;
		
	}
	
	public static AddressFormDTO getAddressFormDTO() {
		AddressFormDTO addressFormDTO = new AddressFormDTO();
		addressFormDTO.setId(ID);
		addressFormDTO.setState(States.PERNAMBUCO);
		addressFormDTO.setCity("Recife");
		addressFormDTO.setDistrict("Boa viagem");
		addressFormDTO.setStreet("Av. Boa viagem");
		addressFormDTO.setNumber("902");
		addressFormDTO.setCep("60530-200");
		addressFormDTO.setComplement("casa");
		addressFormDTO.setCustomerId(getCustomer().getId());
		return addressFormDTO;
	}
	
	public static Address getAddress() {
		Address address = new Address();
		address.setId(ID);
		address.setState(States.PERNAMBUCO);
		address.setCity("Recife");
		address.setDistrict("Boa viagem");
		address.setStreet("Av. Boa viagem");
		address.setNumber("902");
		address.setCep("60530-200");
		address.setComplement("casa");
		address.setCustomerId(getCustomer());
		return address;
	}
	
	public static AddressDTO getAddressDTO() {
		AddressDTO addressDTO = new AddressDTO();
		addressDTO.setId(ID);
		addressDTO.setState(States.PERNAMBUCO);
		addressDTO.setCity("Recife");
		addressDTO.setDistrict("Boa viagem");
		addressDTO.setStreet("Av. Boa viagem");
		addressDTO.setNumber("902");
		addressDTO.setCep("60530-200");
		addressDTO.setComplement("casa");
		return addressDTO;
	}
}
