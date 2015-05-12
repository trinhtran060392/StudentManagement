/**
 * TrinhTV3@fsoft.com.vn
 */
package com.trinhtv3.fsoft.services.base;


/**
 * @author TrinhTV3
 *
 */
public interface AuthenticationService<T> {

  /** .*/
  public static final String AUTH_TOKEN_HEADER = "X-AUTH-TOKEN";

  /** .*/
  public static final String SPACE_HEADER = "X-SPACE";
  
  /** .*/
  public static final String AUTH_TOKEN = "authToken";

  public abstract String logIn(String username, String password);
  
  public abstract T logOut();
  
  public abstract String createToken(T t);
  
  public abstract T findByAuthToken(String token);
}
