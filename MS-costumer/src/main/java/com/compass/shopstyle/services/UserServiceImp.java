package com.compass.shopstyle.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.compass.shopstyle.dto.UserDTO;
import com.compass.shopstyle.dto.UserFormDTO;
import com.compass.shopstyle.dto.UserNewFormDTO;
import com.compass.shopstyle.entities.User;
import com.compass.shopstyle.repositories.UserRepository;
import com.compass.shopstyle.services.exceptions.ResourceNotFoundException;

@Service
public class UserServiceImp implements UserService {

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDTO insert(UserFormDTO userBody) {
		userBody.setId(null);
		User user = userRepository.save(mapper.map(userBody, User.class));
		user.setPassword(passwordEncoder.encode(userBody.getPassword()));
		return mapper.map(user, UserDTO.class);
	}
	
	@Override
	public UserDTO findById(Long id) {
		User user = userRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(id));
		return mapper.map(user, UserDTO.class);
	}

	@Override
	public UserDTO update(Long id, UserNewFormDTO userBody) {
		User user = userRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(id));
			user.setFirstName(userBody.getFirstName());
			user.setLastName(userBody.getLastName());
			user.setSex(userBody.getSex());
			user.setCpf(userBody.getCpf());
			user.setBirthDate(userBody.getBirthDate());
			user.setEmail(userBody.getEmail());
			user.setPassword(passwordEncoder.encode(userBody.getPassword()));
			User userUpdated = userRepository.save(user);
			return mapper.map(userUpdated, UserDTO.class);
			
	}
}
