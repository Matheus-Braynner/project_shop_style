package com.compass.shopstyle.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CustomerLoginFormDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@NotNull
	@NotEmpty
	private String email;
	@NotNull
	@NotEmpty
	private String password;

}
