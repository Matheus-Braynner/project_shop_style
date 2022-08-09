package com.example.shopstyle.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.compass.shopstyle.ShopStyleApplication;
import com.compass.shopstyle.dto.AddressDTO;
import com.compass.shopstyle.dto.AddressFormDTO;
import com.compass.shopstyle.entities.Address;
import com.compass.shopstyle.entities.Customer;
import com.compass.shopstyle.entities.enums.States;
import com.compass.shopstyle.repositories.AddressRepository;
import com.compass.shopstyle.repositories.CustomerRepository;
import com.compass.shopstyle.services.AddressService;
import com.compass.shopstyle.services.exceptions.ResourceNotFoundException;
import com.example.shopstyle.builder.AddressBuilder;

@SpringBootTest(classes = ShopStyleApplication.class)
public class AddressServiceImpTest {

	@Autowired
	private AddressService service;

	@MockBean
	private AddressRepository repository;

	@MockBean
	private CustomerRepository customerRepository;

	@Test
	@DisplayName("Insert Address")
	public void whenSaveThenReturnAddressDTO() {
		Customer customer = AddressBuilder.getCustomer();
		Address address = AddressBuilder.getAddress();

		when(this.customerRepository.findById(anyLong())).thenReturn(Optional.of(customer));
		when(this.repository.save(any())).thenReturn(address);

		AddressDTO response = this.service.insert(AddressBuilder.getAddressFormDTO());

		assertNotNull(address);
		assertEquals(address.getId(), response.getId());
		assertEquals(address.getState(), response.getState());
		assertEquals(address.getCity(), response.getCity());
		assertEquals(address.getDistrict(), response.getDistrict());
		assertEquals(address.getStreet(), response.getStreet());
		assertEquals(address.getNumber(), response.getNumber());
		assertEquals(address.getCep(), response.getCep());
		assertEquals(address.getComplement(), response.getComplement());
	}
	
	@Test
	@DisplayName("List all Address")
	public void whenSeachForAddressesThenReturnAllAdresses() {
		List<Address> adresses = AddressBuilder.getListAddress();
		
		when(this.repository.findAll()).thenReturn(adresses);
		
		List<AddressDTO> addressesDTO = this.service.findAll();
		
		assertEquals(addressesDTO.size(), adresses.size());
		assertThat(adresses.equals(addressesDTO));
	}
	
	@Test
	@DisplayName("Find address by Id")
	public void whenSearchAddressByIdThenReturnAddressDTO() {
		Customer customer = AddressBuilder.getCustomer();
		Address address = AddressBuilder.getAddress();

		when(this.customerRepository.findById(anyLong())).thenReturn(Optional.of(customer));
		when(this.repository.findById(anyLong())).thenReturn(Optional.of(address));

		AddressDTO response = this.service.findById(address.getId());
		
		assertNotNull(address);
		assertEquals(address.getId(), response.getId());
		assertEquals(address.getState(), response.getState());
		assertEquals(address.getCity(), response.getCity());
		assertEquals(address.getDistrict(), response.getDistrict());
		assertEquals(address.getStreet(), response.getStreet());
		assertEquals(address.getNumber(), response.getNumber());
		assertEquals(address.getCep(), response.getCep());
		assertEquals(address.getComplement(), response.getComplement());
	}
	
	@Test
	@DisplayName("not found address")
	public void whenSearchAddressThenReturnException() {
		Address address = AddressBuilder.getAddress();
		
		when(this.repository.findById(anyLong())).thenReturn(Optional.of(address));
		
		try {
			this.service.findById(address.getId());
		} catch (Exception e) {
			assertThatExceptionOfType(ResourceNotFoundException.class);
			assertEquals("Id not found = " + address.getId(), e.getMessage());
		}
	}
	
	@Test
	@DisplayName("Update Address")
	public void whenUpdateAddresThenReturnAddressDTO() {
		Customer customer = AddressBuilder.getCustomer();
		Address address = AddressBuilder.getAddress();
		AddressFormDTO addressFormDTO = AddressBuilder.getAddressFormDTO();
		
		addressFormDTO.setId(1L);
		addressFormDTO.setState(States.PERNAMBUCO);
		addressFormDTO.setCity("Recife");
		addressFormDTO.setDistrict("Boa viagem");
		addressFormDTO.setStreet("Campo grande");
		addressFormDTO.setNumber("903");
		addressFormDTO.setCep("60530-200");
		addressFormDTO.setComplement("casa");
		addressFormDTO.setCustomerId(customer.getId());
		
		when(this.customerRepository.findById(anyLong())).thenReturn(Optional.of(customer));
		when(this.customerRepository.save(any(Customer.class))).thenReturn(customer);
		when(this.repository.findById(anyLong())).thenReturn(Optional.of(address));
		when(this.repository.save(any(Address.class))).thenReturn(address);
		
		AddressDTO response = this.service.update(address.getId(), addressFormDTO);
		
		assertNotNull(response);
		assertEquals(response.getId(), addressFormDTO.getId());
		assertEquals(response.getState(), addressFormDTO.getState());
		assertEquals(response.getCity(), addressFormDTO.getCity());
		assertEquals(response.getDistrict(), addressFormDTO.getDistrict());
		assertEquals(response.getStreet(), addressFormDTO.getStreet());
		assertEquals(response.getNumber(), addressFormDTO.getNumber());
		assertEquals(response.getCep(), addressFormDTO.getCep());
		assertEquals(response.getComplement(), addressFormDTO.getComplement());
	}
	
	@Test
	@DisplayName("Update by id Exception")
	public void whenTryUpdateAddresThenReturnException() {
		Address address = AddressBuilder.getAddress();
		AddressFormDTO addressFormDTO = AddressBuilder.getAddressFormDTO();
		
		when(this.repository.findById(anyLong())).thenReturn(Optional.empty());
		
		try {
			this.service.update(address.getId(), addressFormDTO);
		} catch (Exception e) {
			assertThatExceptionOfType(ResourceNotFoundException.class);
			assertEquals("Address not found, ID = " + address.getId(), e.getMessage());
		}
	}
	
	@Test
	@DisplayName("Delete Address")
	public void whenDeleteAddressThenVerifyIfWasDeleted() {
		Address address = AddressBuilder.getAddress();
		
		when(this.repository.findById(anyLong())).thenReturn(Optional.of(address));
		
		this.service.delete(address.getId());
		
		verify(this.repository, times(1)).delete(address);;
	}
	
	@Test
	@DisplayName("delete by id Exception")
	public void whenTryDeleteAddresThenReturnException() {
		Address address = AddressBuilder.getAddress();
		
		when(this.repository.findById(anyLong())).thenReturn(Optional.of(address));
		
		try {
			this.service.delete(address.getId());
		} catch (Exception e) {
			assertThatExceptionOfType(ResourceNotFoundException.class);
			assertEquals("Address not found, ID = " + address.getId(), e.getMessage());
		}
	}
	
	

}
