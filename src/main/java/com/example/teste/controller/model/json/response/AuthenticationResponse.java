package com.example.teste.controller.model.json.response;


import com.example.teste.controller.model.base.ModelBase;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import java.util.Collection;
import java.util.Set;

@Data
public class AuthenticationResponse extends ModelBase {

	private static final long serialVersionUID = -6624726180748515507L;
	private String token;
	private String username;
    private Collection<? extends GrantedAuthority> authorities;

	public AuthenticationResponse() {
		super();
	}

	public AuthenticationResponse(String token,String username,Collection<? extends GrantedAuthority> authorities) {
		this.setToken(token);
		this.username = username;
		this.authorities = authorities;
	}



}
