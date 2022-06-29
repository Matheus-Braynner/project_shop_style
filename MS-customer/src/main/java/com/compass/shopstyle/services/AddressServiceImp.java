package com.compass.shopstyle.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.compass.shopstyle.dto.AddressDTO;
import com.compass.shopstyle.dto.AddressFormDTO;
import com.compass.shopstyle.entities.Address;
import com.compass.shopstyle.repositories.AddressRepository;
import com.compass.shopstyle.services.exceptions.DatabaseException;
import com.compass.shopstyle.services.exceptions.ResourceNotFoundException;

@Service
public class AddressServiceImp implements AddressService {


	@Autowired
	private AddressRepository addressRepository;

	@Autowired
	private ModelMapper mapper;

	@Override
	public AddressDTO insert(AddressFormDTO addressObj) {
		addressObj.setId(null);
		Address address = addressRepository.save(mapper.map(addressObj, Address.class));
		return mapper.map(address, AddressDTO.class);
	}
	
	@Override
	public List<AddressDTO> findAll() {
		List<Address> list = addressRepository.findAll();
		List<AddressDTO> listDTO = list.stream().map(x -> new AddressDTO(x)).collect(Collectors.toList());
		return listDTO;
	}
	
	@Override
	public AddressDTO findById(Long id) {
		Address address = addressRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Resource not found, id = " + id));
		return mapper.map(address, AddressDTO.class);
	}

	@Override
	public AddressDTO update(Long id, AddressFormDTO addressObj) {
		Address address = addressRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Resource not found, id = " + id));
		address.setState(addressObj.getState());
		address.setCity(addressObj.getCity());
		address.setDistrict(addressObj.getDistrict());
		address.setStreet(addressObj.getStreet());
		address.setNumber(addressObj.getNumber());
		address.setCep(addressObj.getCep());
		address.setComplement(addressObj.getComplement());
		addressRepository.save(address);
		return mapper.map(address, AddressDTO.class);
	}

	@Override
	public void delete(Long id) {

		try {
			Address address = addressRepository.findById(id)
					.orElseThrow(() -> new ResourceNotFoundException("Resource not found, id = " + id));
			addressRepository.delete(address);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Resource not found, id = " + id);
		} catch (DatabaseException e) {
			throw new DatabaseException(e.getMessage());
		}
	}

	
	
}
