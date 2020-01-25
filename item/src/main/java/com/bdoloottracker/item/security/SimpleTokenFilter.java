package com.bdoloottracker.item.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import java.io.IOException;
import java.util.Objects;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.filter.GenericFilterBean;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
public class SimpleTokenFilter extends GenericFilterBean {

  final private Algorithm algorithm;
  private final RestTemplate restTemplate;

  public SimpleTokenFilter(RestTemplate restTemplate, String secretKey) {
    Objects.requireNonNull(secretKey, "secret key must be not null");
    this.restTemplate = restTemplate;
    this.algorithm = Algorithm.HMAC512(secretKey);
  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
    String token = resolveToken(request);
    if (token == null) {
      filterChain.doFilter(request, response);
      return;
    }

    try {
      authentication(verifyToken(token));
    } catch (JWTVerificationException e) {
      log.error("verify token error", e);
      SecurityContextHolder.clearContext();
      ((HttpServletResponse) response).sendError(HttpStatus.UNAUTHORIZED.value(), HttpStatus.UNAUTHORIZED.getReasonPhrase());
    }
    filterChain.doFilter(request, response);
  }

  private String resolveToken(ServletRequest request) {
    String token = ((HttpServletRequest) request).getHeader("Authorization");
    if (token == null || !token.startsWith("Bearer ")) {
      return null;
    }
    // remove "Bearer "
    return token.substring(7);
  }

  private DecodedJWT verifyToken(String token) {
    JWTVerifier verifier = JWT.require(algorithm).build();
    return verifier.verify(token);
  }

  private void authentication(DecodedJWT jwt) {
    long userId = Long.parseLong(jwt.getSubject());
    FindUserResponse response = restTemplate
        .getForObject("http://localhost:8081/public/api/user/users/" + userId, FindUserResponse.class);

    if (response == null) {
      throw new ResponseStatusException(HttpStatus.FORBIDDEN);
    }
    UserDetails from = SimpleLoginUser.from(response);
    SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(from, null, from.getAuthorities()));
  }

}
