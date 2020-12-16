package edu.netcracker.menugenerator.security.jwt;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class AuthEntryPointJwt implements AuthenticationEntryPoint {

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException {
		log.error("Unauthorized error: {}", authException.getMessage());
		response.resetBuffer();
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		response.setHeader("Content-Type", "application/json");
		response.getOutputStream().print("{\"errorMessage\":\"Unauthorized: check username and password\"}");
		response.flushBuffer();
	}

}
