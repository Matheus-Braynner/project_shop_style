package com.compass.shopstyle.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.compass.shopstyle.controllers.exceptions.FieldMessage;
import com.compass.shopstyle.dto.UserFormDTO;
import com.compass.shopstyle.entities.User;
import com.compass.shopstyle.repositories.UserRepository;
import com.compass.shopstyle.services.validation.utils.BR;

public class UserInsertValidator implements ConstraintValidator<UserInsert, UserFormDTO> {

	@Autowired
	private UserRepository userRepository;

	@Override
	public void initialize(UserInsert ann) {
	}

	@Override
	public boolean isValid(UserFormDTO obj, ConstraintValidatorContext context) {
			List<FieldMessage> list = new ArrayList<>();

			if(!BR.isValidCPF(obj.getCpf())) {
				list.add(new FieldMessage("cpf", "Invalid CPF"));
			}
			
			User aux = userRepository.findByEmail(obj.getEmail());
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
