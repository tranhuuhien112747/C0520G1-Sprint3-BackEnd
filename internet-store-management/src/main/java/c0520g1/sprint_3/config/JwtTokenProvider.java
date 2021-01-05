package c0520g1.sprint_3.config;

import c0520g1.sprint_3.model.UserDetailsImpl;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * JwtTokenProvider
 *
 * Version 1.0
 *
 * Date: 08/12/2020
 *
 * Copyright
 *
 * Author: Nguyen Huu Quang
 */
@Component
public class JwtTokenProvider {
  private static final Logger log = LoggerFactory.getLogger(JwtTokenProvider.class.getName());

  // This JWT_SECRET is secret, just server know
  private final String JWT_SECRET = "SECRET_KEY";

  //Time life of jwt (1 week)
  private final long JWT_EXPIRATION = 604800000L;

  /**
   * create token when user login successful
   * @param authentication get information of principal
   * @return token
   */
  //create jwt from user information
  public String generateJwtToken(Authentication authentication) {

    UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();

    Date now = new Date();
    Date expiryDate = new Date(now.getTime() + JWT_EXPIRATION);

    return Jwts.builder().setSubject(userPrincipal.getUsername())
      .setIssuedAt(now)
      .setExpiration(expiryDate)
      .signWith(SignatureAlgorithm.HS512, JWT_SECRET)
      .compact();
  }

  /**
   * get user's information via token on header of request
   * @param token token sent by user
   * @return user's information
   */
  //get user information from jwt
  public String getUserNameFromJwtToken(String token) {
    return Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(token).getBody().getSubject();
  }

  /**
   * check token sent by user and token in server is same ?
   * @param authToken token sent by user
   * @return true is same, false is not same
   */
  public boolean validateToken(String authToken) {
    try {
      Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(authToken);
      return true;
    } catch (MalformedJwtException ex) {
      log.error("Invalid JWT token");
    } catch (ExpiredJwtException ex) {
      log.error("Expired JWT token");
    } catch (UnsupportedJwtException ex) {
      log.error("Unsupported JWT token");
    } catch (IllegalArgumentException ex) {
      log.error("JWT claims string is empty.");
    }
    return false;
  }
}
