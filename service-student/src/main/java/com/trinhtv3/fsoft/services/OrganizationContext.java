/**
 * TrinhTV3@fsoft.com.vn
 */
package com.trinhtv3.fsoft.services;

import com.google.inject.Singleton;
import com.trinhtv3.fsoft.services.entity.School;
import com.trinhtv3.fsoft.services.entity.Student;

/**
 * @author TrinhTV3
 *
 */
@Singleton
public class OrganizationContext {

  private static final ThreadLocal<Student> currentStudent = new ThreadLocal<Student>();
  
  private static final ThreadLocal<School> currentSchool = new ThreadLocal<School>();
  
  public void setStudent(Student student) {
    
    currentStudent.set(student);
  }
  
  public Student getStudent() {
    return currentStudent.get();
  }
  
  public void setSchool(School school) {
    currentSchool.set(school);
  }
  
  public School getSchool() {
    return currentSchool.get();
  }
}
