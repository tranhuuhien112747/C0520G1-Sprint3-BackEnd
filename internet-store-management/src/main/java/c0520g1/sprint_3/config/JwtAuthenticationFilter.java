package c0520g1.sprint_3.config;

import c0520g1.sprint_3.service.impl.UserDetailsServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * JwtAuthenticationFilter
 *
 * Version 1.0
 *
 * Date: 9/12/2020
 *
 * Copyright
 *
 * Author: Nguyen Huu Quang
 */
public class JwtAuthenticationFilter extends OncePerRequestFilter {
  @Autowired
  private JwtTokenProvider jwtTokenProvider;

  @Autowired
  private UserDetailsServiceImpl userDetailsService;

  private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationFilter.class);

  /**
   * from JWT from request, process check token and set UserDetails in SecurityContext
   * @param request from client
   * @param response reply for client
   * @param filterChain
   * @throws ServletException
   * @throws IOException
   */
  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
    try {
      String jwt = getJwtFromRequest(request);
      if (jwt != null && jwtTokenProvider.validateToken(jwt)) {
        //get username from jwt
        String userName = jwtTokenProvider.getUserNameFromJwtToken(jwt);
        // get user information from userName
        UserDetails userDetails = userDetailsService.loadUserByUsername(userName);
        if (userDetails != null) {
          // if user valid, set information for security context
          UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
          authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
          SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
      }
    } catch (Exception e) {
      logger.error("failed on set user authentication", e);
    }
    filterChain.doFilter(request, response);
  }

  /**
   * get token from request send by client
   * @param request sent by client
   * @return token got from field Authorization of header
   */
  private String getJwtFromRequest(HttpServletRequest request) {
    String headerAuth = request.getHeader("Authorization");

    if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
      return headerAuth.substring(7);
    }
    return null;
  }
}
