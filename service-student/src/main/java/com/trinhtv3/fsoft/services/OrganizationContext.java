/**
 * TrinhTV3@fsoft.com.vn
 */
package com.trinhtv3.fsoft.services;

import com.trinhtv3.fsoft.services.entity.School;
import com.trinhtv3.fsoft.services.entity.Student;

/**
 * @author TrinhTV3
 *
 */
public class OrganizationContext {

  private static final ThreadLocal<Student> currentStudent = new ThreadLocal<Student>();
  
  private static final ThreadLocal<School> currentSchool = new ThreadLocal<School>();
  
  public void setStudent(Student student) {
    
    currentStudent.set(student);
  }
  
  public void getStudent() {
    currentStudent.get();
  }
  
  public void setSchool(School school) {
    currentSchool.set(school);
  }
  
  public void getSchool() {
    currentSchool.get();
  }
}
