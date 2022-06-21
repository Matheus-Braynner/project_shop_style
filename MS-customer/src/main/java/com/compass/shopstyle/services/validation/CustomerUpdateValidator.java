package com.compass.shopstyle.services.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import com.compass.shopstyle.controllers.exceptions.FieldMessage;
import com.compass.shopstyle.dto.CustomerNewFormDTO;
import com.compass.shopstyle.entities.Customer;
import com.compass.shopstyle.repositories.CustomerRepository;
import com.compass.shopstyle.services.validation.utils.BR;

public class CustomerUpdateValidator implements ConstraintValidator<CustomerUpdate, CustomerNewFormDTO> {

	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private CustomerRepository CustomerRepository;

	@Override
	public void initialize(CustomerUpdate ann) {
	}

	@SuppressWarnings("unlikely-arg-type")
	@Override
	public boolean isValid(CustomerNewFormDTO obj, ConstraintValidatorContext context) {
			@SuppressWarnings("unchecked")
			Map<String, String> map = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
			Integer uriId = Integer.parseInt(map.get("id"));
			List<FieldMessage> list = new ArrayList<>();

			if(!BR.isValidCPF(obj.getCpf())) {
				list.add(new FieldMessage("cpf", "CPF inválido"));
			}
			
			
			Customer aux = CustomerRepository.findByEmail(obj.getEmail());
			if(aux != null && !(aux.getId().equals(uriId))) {
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
