/**
 * TrinhTV3@fsoft.com.vn
 */
package com.trinhtv3.fsoft.services;

import com.trinhtv3.fsoft.services.base.AuthenticationService;
import com.trinhtv3.fsoft.services.entity.Student;

/**
 * @author TrinhTV3
 *
 */
public class MongoAuthenticationService implements AuthenticationService<Student> {

  @Override
  public String logIn(String username, String password) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Student logOut() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public String createToken(Student t) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Student findByAuthToken(String token) {
    // TODO Auto-generated method stub
    return null;
  }

}
