package com.invillia.acme.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.invillia.acme.response.ResponseErrorLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDateTime;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint, Serializable {

	private static final long serialVersionUID = -8970718410437077606L;

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private MessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource =
				new ReloadableResourceBundleMessageSource();
		messageSource.setBasename("classpath:/ValidationMessages");
		messageSource.setDefaultEncoding("UTF-8");
		return messageSource;
	}

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException {
		response.addHeader("Content-Type", "application/json;charset=UTF-8");
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		ResponseErrorLogin unauthorized = new ResponseErrorLogin();
		if (authException instanceof BadCredentialsException) {
			unauthorized.setMessage(messageSource().getMessage("validator.login.credential.invalid", null, null));
		} else if( authException instanceof InsufficientAuthenticationException) {
			InsufficientAuthenticationException i = (InsufficientAuthenticationException) authException;
			unauthorized.setMessage(messageSource().getMessage("validator.login.notpermit", null, null));
		} else {
			unauthorized.setMessage(messageSource().getMessage("validator.login.fail", null, null));
		}
		unauthorized.setTimestamp(LocalDateTime.now());
		objectMapper.writeValue(response.getOutputStream(), unauthorized);
		response.flushBuffer();
	}
}