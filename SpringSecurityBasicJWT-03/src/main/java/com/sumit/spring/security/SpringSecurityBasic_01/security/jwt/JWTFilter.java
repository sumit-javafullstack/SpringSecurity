package com.sumit.spring.security.SpringSecurityBasic_01.security.jwt;

import com.sumit.spring.security.SpringSecurityBasic_01.security.service.BookUserDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Service
public class JWTFilter extends OncePerRequestFilter {

  @Autowired private JWTValidateTokenService jwtValidateTokenService;

  @Autowired ApplicationContext applicationContext;

  @Override
  protected void doFilterInternal(
      HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {

    String authHeader = request.getHeader("Authorization");
    String token = null;
    String username = null;

    if (authHeader != null && authHeader.startsWith("Bearer")) {
      token = authHeader.substring(7);
      username = jwtValidateTokenService.extractUserName(token);
    }
    if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
      // SecurityContextHolder.getContext().getAuthentication() means if the user is not
      // authenticated before.

      UserDetails userDetails =
          applicationContext.getBean(BookUserDetailsService.class).loadUserByUsername(username);
      // to remove cyclic dependency

      if (jwtValidateTokenService.validateToken(token, userDetails)) {
        UsernamePasswordAuthenticationToken authToken =
            new UsernamePasswordAuthenticationToken(
                userDetails, null, userDetails.getAuthorities()); //validating user cred with database
        authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authToken);
      }
    }
    filterChain.doFilter(request, response);
  }
}
