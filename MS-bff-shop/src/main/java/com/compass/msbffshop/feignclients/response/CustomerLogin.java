package com.compass.msbffshop.feignclients.response;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerLogin implements Serializable {
	private static final long serialVersionUID = 1L;

	private String email;
	private String password;
	
}
