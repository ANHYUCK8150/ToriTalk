package com.toritalk.common.token;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.toritalk.common.dto.StatusResponse;
import com.toritalk.common.exception.ExceptionFactory;
import com.toritalk.common.exception.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

	@Autowired
	private ObjectMapper mapper;

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
		AuthenticationException authException) throws IOException {

		StatusCode statusCode = ExceptionFactory.getInstance(authException);
		StatusResponse message = new StatusResponse(statusCode);

		response.setContentType("application/json;charset=UTF-8");
		response.setStatus(statusCode.getHttpStatus().value());
		response.getWriter().println(mapper.writeValueAsString(message));
	}
}
