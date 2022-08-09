package com.compass.msbffshop.controllers;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.compass.msbffshop.feignclients.CatalogClient;
import com.compass.msbffshop.feignclients.CustomerClient;
import com.compass.msbffshop.feignclients.OrderClient;
import com.compass.msbffshop.feignclients.PaymentClient;
import com.compass.msbffshop.feignclients.request.catalog.CategoryDTO;
import com.compass.msbffshop.feignclients.request.catalog.ProductDTO;
import com.compass.msbffshop.feignclients.request.customer.AddressDTO;
import com.compass.msbffshop.feignclients.request.customer.AddressFormDTO;
import com.compass.msbffshop.feignclients.request.customer.ChangePasswordDTO;
import com.compass.msbffshop.feignclients.request.customer.CustomerDTO;
import com.compass.msbffshop.feignclients.request.customer.CustomerFormDTO;
import com.compass.msbffshop.feignclients.request.customer.CustomerLoginFormDTO;
import com.compass.msbffshop.feignclients.request.customer.CustomerNewFormDTO;
import com.compass.msbffshop.feignclients.request.enums.Status;
import com.compass.msbffshop.feignclients.request.order.OrderDTO;
import com.compass.msbffshop.feignclients.request.order.OrderFormDTO;
import com.compass.msbffshop.feignclients.request.payment.PaymentDTO;

@RestController
public class BffShopController {

	@Autowired
	private CustomerClient customerClient;
	
	@Autowired
	private CatalogClient catalogClient;
	
	@Autowired
	private PaymentClient paymentClient;
	
	@Autowired
	private OrderClient orderClient;
	
	@PostMapping(value = "/v1/login")
	@Transactional
	public ResponseEntity<CustomerDTO> login(@Valid @RequestBody CustomerLoginFormDTO customerLoginBody) {
		customerClient.login(customerLoginBody);
		return ResponseEntity.status(HttpStatus.ACCEPTED).build();
	}
	
	@PutMapping(value = "/bffshop/v1/customers/login/{id}")
	@Transactional
	public ResponseEntity<CustomerDTO> changePassword(@PathVariable Long id, @Valid @RequestBody ChangePasswordDTO changePasswordBody) {
		CustomerDTO customer = customerClient.changePassword(id, changePasswordBody);
		return ResponseEntity.ok(customer);
	}
	
	
	@PostMapping(value = "/bffshop/v1/customers")
	@Transactional
	public ResponseEntity<CustomerDTO> insertCustomer(@Valid @RequestBody CustomerFormDTO customerBody) {
		CustomerDTO customer = customerClient.insert(customerBody);
		return ResponseEntity.status(HttpStatus.CREATED).body(customer);
	}
	
	@GetMapping(value = "/bffshop/v1/customers/{id}")
	public ResponseEntity<CustomerDTO> findByIdCustomers(@PathVariable Long id) {
		CustomerDTO customer = customerClient.findById(id);
		return ResponseEntity.ok(customer);
	}
	
	@PutMapping(value = "/bffshop/v1/customers/{id}")
	public ResponseEntity<CustomerDTO> updateCustomer(@PathVariable Long id, @Valid @RequestBody CustomerNewFormDTO customerBody) {
		CustomerDTO customer = customerClient.update(id, customerBody);
		return ResponseEntity.ok(customer);
	}
	
	@PostMapping(value = "/bffshop/v1/addresses")
	@Transactional
	public ResponseEntity<AddressDTO> insertAddress(@Valid @RequestBody AddressFormDTO addressBody) {
		AddressDTO address = customerClient.insert(addressBody);
		return ResponseEntity.status(HttpStatus.CREATED).body(address);
	}
	
	@PutMapping(value = "/bffshop/v1/addresses/{id}")
	@Transactional
	public ResponseEntity<AddressDTO> updateAddress(@PathVariable Long id, @Valid @RequestBody AddressFormDTO addressBody) {
		AddressDTO address = customerClient.update(id, addressBody);
		return ResponseEntity.ok(address);
	}
	
	@DeleteMapping(value = "/bffshop/v1/addresses/{id}")
	@Transactional
	public ResponseEntity<Void> deleteAddress(@PathVariable Long id) {
		customerClient.delete(id);
		return ResponseEntity.noContent().build();
		
	}
	
	@GetMapping(value = "/bffshop/v1/products")
	public ResponseEntity<List<ProductDTO>> findAllProducts() {
		List<ProductDTO> list = catalogClient.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/bffshop/v1/products/{id}")
	public ResponseEntity<ProductDTO> findByIdProducts(@PathVariable Long id) {
		ProductDTO product = catalogClient.findById(id);
		return ResponseEntity.ok().body(product);
	}
	
	@GetMapping(value = "/bffshop/v1/categories")
	public ResponseEntity<List<CategoryDTO>> findAllCategories() {
		List<CategoryDTO> list = catalogClient.findAllCategories();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/bffshop/v1/categories/{id}")
	public ResponseEntity<List<ProductDTO>> findProductsByIdCategory(@PathVariable Long id) {
		List<ProductDTO> products = catalogClient.findProductsByIdCategory(id);
		return ResponseEntity.ok(products);
	}
	
	@GetMapping(value = "/bffshop/v1/payments")
	public ResponseEntity<List<PaymentDTO>> findAllPayments() {
		List<PaymentDTO> paymentList = paymentClient.findAllPayments();
		return ResponseEntity.ok().body(paymentList);
	}
	
	@PostMapping(value = "/bffshop/v1/orders")
	@Transactional
	public ResponseEntity<OrderDTO> insertOrder (@RequestBody @Valid OrderFormDTO orderBody) {
		OrderDTO order = orderClient.insertOrder(orderBody);
		return ResponseEntity.status(HttpStatus.CREATED).body(order);
	}
	
	@GetMapping(value = "/bffshop/v1/orders/customers/{id}")
	public ResponseEntity<List<OrderDTO>> findOrderByCustomerId(@PathVariable Long id, @RequestParam(required = false) Date startDate, 
			@RequestParam(required = false) Date endDate, @RequestParam(required = false) Status status){
		List<OrderDTO> listByCustomer = orderClient.findOrderByCustomerId(id, startDate, endDate, status);
		return ResponseEntity.ok().body(listByCustomer);
	}
	
}
