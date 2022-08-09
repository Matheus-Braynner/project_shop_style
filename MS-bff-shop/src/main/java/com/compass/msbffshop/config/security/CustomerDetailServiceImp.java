package com.compass.msbffshop.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.compass.msbffshop.feignclients.CustomerClient;
import com.compass.msbffshop.feignclients.response.CustomerLogin;

@Service
public class CustomerDetailServiceImp implements UserDetailsService {

	@Autowired
	private CustomerClient customerClient;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		CustomerLogin customer = customerClient.findByEmail(username);
		if(customer == null) {
			throw new UsernameNotFoundException("User login" + username + "not found");
		}
		return new CustomerDetailsData(customer);
	}

}
