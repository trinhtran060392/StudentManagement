package com.trinhtv3.fsoft.services.entity.references;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import com.trinhtv3.fsoft.services.StudentService;
import com.trinhtv3.fsoft.services.entity.Student;


public class StudentReference extends Reference<Student> {

  private StudentService service;
  
  @Inject
  public StudentReference(StudentService service, @Assisted("id") String id) {
    super(id);
    this.service = service;
  }
  
  @Override
  public Student get() {
    return this.service.get(id);
  }

}
