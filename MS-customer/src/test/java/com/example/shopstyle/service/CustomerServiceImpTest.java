package com.example.shopstyle.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.Date;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.compass.shopstyle.ShopStyleApplication;
import com.compass.shopstyle.dto.CustomerDTO;
import com.compass.shopstyle.dto.CustomerNewFormDTO;
import com.compass.shopstyle.entities.Customer;
import com.compass.shopstyle.entities.enums.Gender;
import com.compass.shopstyle.repositories.CustomerRepository;
import com.compass.shopstyle.services.CustomerService;
import com.compass.shopstyle.services.exceptions.EmailNotFoundException;
import com.compass.shopstyle.services.exceptions.ResourceNotFoundException;
import com.example.shopstyle.builder.CustomerBuilder;

@SpringBootTest(classes = ShopStyleApplication.class)
public class CustomerServiceImpTest {
	
	public static final Date BIRTHDAY = new Date();
	
	@Autowired
	private CustomerService service;
	
	@MockBean
	private CustomerRepository repository;
	
	@Test
	@DisplayName("Save Customer")
	public void whenSaveACustomer() {
		
		Customer customer = CustomerBuilder.getCustomer();
		
		when(this.repository.save(any(Customer.class))).thenReturn(customer);
		
		CustomerDTO customerDTO = this.service.insert(CustomerBuilder.getCustomerFormDTO());
		
		assertThat(customerDTO.getId()).isEqualTo(customer.getId());
		assertThat(customerDTO.getFirstName()).isEqualTo(customer.getFirstName());
		assertThat(customerDTO.getLastName()).isEqualTo(customer.getLastName());
		assertThat(customerDTO.getSex()).isEqualTo(customer.getSex());
		assertThat(customerDTO.getEmail()).isEqualTo(customer.getEmail());
		assertThat(customerDTO.getBirthDate()).isEqualTo(customer.getBirthDate());
		assertThat(customerDTO.getFirstName()).isEqualTo(customer.getFirstName());
		
	}
	
	@Test
	@DisplayName("Findy Customer By Id")
	public void whenSearchCustomerByIdReturnACustomer() {
		Customer customer = CustomerBuilder.getCustomer();
		
		when(this.repository.findById(anyLong())).thenReturn(Optional.of(customer));
		
		CustomerDTO customerDTO = this.service.findById(customer.getId());
		
		assertThat(customerDTO.getId()).isEqualTo(customer.getId());
		assertThat(customerDTO.getFirstName()).isEqualTo(customer.getFirstName());
		assertThat(customerDTO.getLastName()).isEqualTo(customer.getLastName());
		assertThat(customerDTO.getSex()).isEqualTo(customer.getSex());
		assertThat(customerDTO.getEmail()).isEqualTo(customer.getEmail());
		assertThat(customerDTO.getBirthDate()).isEqualTo(customer.getBirthDate());
		assertThat(customerDTO.getFirstName()).isEqualTo(customer.getFirstName());
	}
	
	@Test
	@DisplayName("Find By Id Exception")
	public void whenFindByIdThrowException() {
		Customer customer = CustomerBuilder.getCustomer();
		
		when(this.repository.findById(anyLong())).thenReturn(Optional.empty());
		
		try {
			this.service.findById(customer.getId());
		} catch (Exception e) {
			assertThatExceptionOfType(ResourceNotFoundException.class);
			assertEquals("Customer not found, ID = " +  1L, e.getMessage());
		}
	}
	
	
	
	@Test
	@DisplayName("Update customer")
	public void whenUpdateByIdCustomerReturnACustomer() {
		Customer customer = CustomerBuilder.getCustomer();
		CustomerNewFormDTO customerNewFormDTO = CustomerBuilder.getCustomerNewFormDTO();
		customerNewFormDTO.setId(1L);
		customerNewFormDTO.setFirstName("Matheus");
		customerNewFormDTO.setLastName("Braynner");
		customerNewFormDTO.setSex(Gender.MAN);
		customerNewFormDTO.setCpf("28006222029");
		customerNewFormDTO.setBirthDate(BIRTHDAY);
		customerNewFormDTO.setEmail("matheus1@email.com");
		customerNewFormDTO.setPassword("12345678");
		customerNewFormDTO.setActive(true);
		
		when(this.repository.findById(anyLong())).thenReturn(Optional.of(customer));
		when(this.repository.save(any(Customer.class))).thenReturn(customer);
		
		CustomerDTO customerDTO = this.service.update(customer.getId(), customerNewFormDTO);
		
		assertThat(customerDTO.getId()).isEqualTo(customerNewFormDTO.getId());
		assertThat(customerDTO.getFirstName()).isEqualTo(customerNewFormDTO.getFirstName());
		assertThat(customerDTO.getLastName()).isEqualTo(customerNewFormDTO.getLastName());
		assertThat(customerDTO.getSex()).isEqualTo(customerNewFormDTO.getSex());
		assertThat(customerDTO.getEmail()).isEqualTo(customerNewFormDTO.getEmail());
		assertThat(customerDTO.getBirthDate()).isEqualTo(customerNewFormDTO.getBirthDate());
		assertThat(customerDTO.getFirstName()).isEqualTo(customerNewFormDTO.getFirstName());
		
	}
	
	@Test
	@DisplayName("Update customer Exception")
	public void whenTryUpdateCustomerThenReturnException() {
		Customer customer = CustomerBuilder.getCustomer();
		CustomerNewFormDTO customerNewFormDTO = CustomerBuilder.getCustomerNewFormDTO();
		
		when(this.repository.findById(anyLong())).thenReturn(Optional.empty());
		
		try {
			this.service.update(customer.getId(), customerNewFormDTO);
		} catch(Exception e) {
			assertThatExceptionOfType(ResourceNotFoundException.class);
			assertEquals("Customer not found, ID = " +  customer.getId(), e.getMessage());
		}
	}
	
	@Test
	@DisplayName("Find By Email")
	public void whenFindByEmailReturnCustomer() {
		Customer customer = CustomerBuilder.getCustomer();
		
		when(this.repository.findByEmail(anyString())).thenReturn(customer);
		
		Customer response = this.service.findByEmail(customer.getEmail());
		
		assertNotNull(response);
		assertEquals(1L, response.getId());
		assertEquals("Matheus", response.getFirstName());
		assertEquals("Souza", response.getLastName());
		assertEquals(Gender.MAN, response.getSex());
		assertEquals("28006222029", response.getCpf());
		assertEquals(BIRTHDAY, BIRTHDAY);
		assertEquals("matheus@email.com", response.getEmail());
		assertEquals("123456", response.getPassword());
		assertEquals(true, response.getActive());
		
	}
	
	@Test
	@DisplayName("Email not Found")
	public void whenExistingEmailThenThrowException() {
		when(this.repository.findByEmail(null)).thenReturn(null);
		
		assertThatExceptionOfType(EmailNotFoundException.class);
	}
	
//	@Test
//	@DisplayName("Login")
//	public void whenLoginReturnCustomerDTO() {
//		Customer customer = CustomerBuilder.getCustomer();
//		CustomerLoginFormDTO customerLogin = CustomerBuilder.getCustomerLogin();
//		
//		when(this.repository.findByEmail(anyString())).thenReturn(customer);
//		
//		CustomerDTO customerDTO = this.service.login(customerLogin);
//		
//		assertNotNull(customerDTO);
//		assertEquals(customerDTO, customerDTO.getClass());
//		assertEquals(1L, customerDTO.getId());
//		assertEquals("Matheus", customerDTO.getFirstName());
//		assertEquals("Souza", customerDTO.getLastName());
//		assertEquals(Gender.MAN, customerDTO.getSex());
//		assertEquals(BIRTHDAY, customerDTO.getBirthDate());
//		assertEquals("matheus@email.com", customerDTO.getEmail());
//		
//	}
}
