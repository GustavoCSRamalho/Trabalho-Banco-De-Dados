package com.example.controller;

import com.example.model.entity.User;
import com.example.model.json.request.AuthenticationRequest;
import com.example.model.json.response.AuthenticationResponse;
import com.example.model.entity.EntityUser;
import com.example.security.TokenUtils;
import com.example.service.impl.UserDetailsServiceImpl;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collection;

@RestController
@RequestMapping("/api")
public class AuthenticationController {

    private final Logger logger = Logger.getLogger(this.getClass());

    @Value("${gustavo.token.header}")
    private String tokenHeader;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenUtils tokenUtils;

    //TODO
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private UserDetailsServiceImpl userDetailsServiceImp;

    @RequestMapping(value = "/auth", method = RequestMethod.POST)
    public ResponseEntity<?> authenticationRequest(@RequestBody AuthenticationRequest authenticationRequest) throws AuthenticationException {

        // Perform the authentication
         try{
        Authentication authentication = this.authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.getUsername(),
                        authenticationRequest.getPassword()
                )
        );


        SecurityContextHolder.getContext().setAuthentication(authentication);

            UserDetails userDetails = this.userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
            String token = this.tokenUtils.generateTokenByUserDetails(userDetails);

            // Return the token
            return ResponseEntity.ok(new AuthenticationResponse(token, userDetails.getUsername(), userDetails.getAuthorities()));

        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
        // Reload password post-authentication so we can generate token

    }

    @RequestMapping(path = "/auth", method = RequestMethod.GET)
    public ResponseEntity<AuthenticationResponse> refreshToken(HttpServletRequest request) {
        String token = request.getHeader(this.tokenHeader);
        String usernameFromToken = this.tokenUtils.getUsernameFromToken(token);
        EntityUser user = (EntityUser) this.userDetailsService.loadUserByUsername(usernameFromToken);
        Collection<? extends GrantedAuthority>  authorities = user.getAuthorities();
        String username = user.getUsername();

        if (this.tokenUtils.validateToken(token, user)) {

            return ResponseEntity.ok(new AuthenticationResponse(token, username, authorities));
        } else {
            return ResponseEntity.badRequest().body(null);

        }
    }


    @RequestMapping(value = {"/register"}, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> create(@RequestBody User user)  {

        try {
            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            this.userDetailsServiceImp.saveUser(user);
            return new ResponseEntity(user, HttpStatus.OK);
        } catch (Error e) {
            return new ResponseEntity(user, HttpStatus.CONFLICT);
        }
    }


    @RequestMapping(value = {"/logout"}, method = RequestMethod.GET)
    public ResponseEntity<?> logout(HttpServletRequest request, HttpServletResponse response){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(auth != null){
            new SecurityContextLogoutHandler().logout(request,response,auth);
            return new ResponseEntity(auth, HttpStatus.OK);
        }
        return new ResponseEntity(auth, HttpStatus.CONFLICT);
    }

}
