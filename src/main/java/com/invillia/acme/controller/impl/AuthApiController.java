package com.invillia.acme.controller.impl;

import com.invillia.acme.controller.AuthApi;
import com.invillia.acme.dto.LoginDto;
import com.invillia.acme.model.JwtLogin;
import com.invillia.acme.response.ConvertResponse;
import com.invillia.acme.usecases.LoginDigitalUseCase;
import com.invillia.acme.utils.JwtTokenUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;


@Controller
@AllArgsConstructor
@Slf4j
@Api(value = "autenticador", description = "Autenticador API")
public class AuthApiController implements AuthApi {

    private AuthenticationManager authenticationManager;

    private JwtTokenUtil jwtTokenUtil;

    private ConvertResponse convertResponse;

    private UserDetailsService userDetailsService;

    private LoginDigitalUseCase loginUseCase;

    public ResponseEntity<?> auth(@ApiParam(value = "Get token" ,required=true ) @Valid @RequestBody JwtLogin auth) {
        log.info("Begin AuthApiController : auth : user: " + auth.getPassword() + " password : " + auth.getPassword());
        LoginDto loginDto = LoginDto.builder().username(auth.getUsername()).password(auth.getPassword()).build();
        loginUseCase.validate(loginDto);
        final Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(auth.getUsername(),
                        auth.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        final UserDetails userDetails = userDetailsService.loadUserByUsername(auth.getUsername());
        final String token = jwtTokenUtil.generateToken(userDetails);
        log.info("End AuthApiController : auth : user: " + auth.getUsername() + " password : " + auth.getPassword());
        return new ResponseEntity<>(convertResponse.retrieveLogin(token), HttpStatus.OK);
    }
}
