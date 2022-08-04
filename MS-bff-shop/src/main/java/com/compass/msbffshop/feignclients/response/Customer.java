package com.compass.msbffshop.feignclients.response;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.compass.msbffshop.feignclients.request.enums.Gender;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer implements Serializable, UserDetails, GrantedAuthority {
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String firstName;
	private String lastName;
	private Gender sex;
	private String cpf;
	private Date birthDate;
	private String email;
	private String password;
	private Boolean active;
	private List<Address> adresses = new ArrayList<>();
	
	@Override
	public String getAuthority() {
		return this.email;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.getAuthorities();
	}
	
	@Override
	public String getPassword() {
		return this.password;
	}
	@Override
	public String getUsername() {
		return this.email;
	}
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	@Override
	public boolean isEnabled() {
		return true;
	}
	
}
