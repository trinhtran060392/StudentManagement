/**
 * TrinhTV3@fsoft.com.vn
 */
package com.trinhtv3.fsoft.services;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.trinhtv3.fsoft.MD5;
import com.trinhtv3.fsoft.services.base.AuthenticationService;
import com.trinhtv3.fsoft.services.entity.Student;

/**
 * @author TrinhTV3
 *
 */
@Singleton
public class MongoAuthenticationService implements AuthenticationService<Student> {

  @Inject
  private StudentService service;
  
  @Inject private Logger logger;
  
  private final Map<String, Student> studentAuthenticated = new HashMap<String, Student>();
  
  @Override
  public String logIn(String username, String password) {
    
    logger.info("handle logging in");
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
    
    logger.info("logging failed");
   
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
    Student student = studentAuthenticated.get(token);
    System.out.println(student.getId());
    return student;
  }

}
