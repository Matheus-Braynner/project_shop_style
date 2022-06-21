package com.compass.shopstyle.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.compass.shopstyle.controllers.exceptions.FieldMessage;
import com.compass.shopstyle.dto.CustomerFormDTO;
import com.compass.shopstyle.entities.Customer;
import com.compass.shopstyle.repositories.CustomerRepository;
import com.compass.shopstyle.services.validation.utils.BR;

public class CustomerInsertValidator implements ConstraintValidator<CustomerInsert, CustomerFormDTO> {

	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public void initialize(CustomerInsert ann) {
	}

	@Override
	public boolean isValid(CustomerFormDTO obj, ConstraintValidatorContext context) {
			List<FieldMessage> list = new ArrayList<>();

			if(!BR.isValidCPF(obj.getCpf())) {
				list.add(new FieldMessage("cpf", "Invalid CPF"));
			}
			
			Customer aux = customerRepository.findByEmail(obj.getEmail());
			if(aux != null) {
				list.add(new FieldMessage("email", "Email already exist"));
			}

			for (FieldMessage e : list) {
				context.disableDefaultConstraintViolation();
				context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
						.addConstraintViolation();
			}
			return list.isEmpty();
	}
}
