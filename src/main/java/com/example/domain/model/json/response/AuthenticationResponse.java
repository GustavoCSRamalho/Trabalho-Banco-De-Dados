package com.example.domain.model.json.response;


import com.example.domain.base.ModelBase;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

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
