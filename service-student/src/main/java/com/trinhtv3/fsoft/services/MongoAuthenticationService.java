/**
 * TrinhTV3@fsoft.com.vn
 */
package com.trinhtv3.fsoft.services;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import com.google.inject.Inject;
import com.trinhtv3.fsoft.MD5;
import com.trinhtv3.fsoft.services.base.AuthenticationService;
import com.trinhtv3.fsoft.services.entity.Student;

/**
 * @author TrinhTV3
 *
 */
public class MongoAuthenticationService implements AuthenticationService<Student> {

  @Inject
  private StudentService service;
  
  @Inject Logger logger;
  
  private Map<String, Student> studentAuthenticated = new HashMap<String, Student>();
  
  @Override
  public String logIn(String username, String password) {
    
    Student student = service.get(username);
    
    if (student == null) {
      logger.info("student is not exit");
      
      return null;
    }
    
    if (password.equals(student.getPassword())) {
      
      logger.info(username + "logged successfully");
      String token = createToken(student);
      
      studentAuthenticated.put(token, student);
      return token;
    }
   
    return null;
  }

  @Override
  public Student logOut() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public String createToken(Student t) {
    
    String token = MD5.digest(t.getId()).toString();
    return token;
  }

  @Override
  public Student findByAuthToken(String token) {
    
    return studentAuthenticated.get(token);
  }

}
