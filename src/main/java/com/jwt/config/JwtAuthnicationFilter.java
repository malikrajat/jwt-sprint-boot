package com.jwt.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.jwt.helper.JwtUtil;
import com.jwt.services.CustomUserDetailsServices;

@Component
public class JwtAuthnicationFilter extends OncePerRequestFilter {

	@Autowired
	private CustomUserDetailsServices customUserDetailsServices;

	@Autowired
	private JwtUtil jwtUtil;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String requestToken = request.getHeader("Authorization");
		String userName = null;
		String jwtToken = null;
		if (requestToken != null && requestToken.startsWith("Bearer ")) {
			jwtToken = requestToken.substring(7);

			try {
				userName = this.jwtUtil.extractUsername(jwtToken);
			} catch (Exception e) {
				e.printStackTrace();
			}

			UserDetails userDetails = this.customUserDetailsServices.loadUserByUsername(userName);
			if (userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {

				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities());

				usernamePasswordAuthenticationToken
						.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

			} else {
				System.out.println("Invalid Token");
			}

		} else {
			System.out.println("Not valid user");
		}
		filterChain.doFilter(request, response);

	}

}
