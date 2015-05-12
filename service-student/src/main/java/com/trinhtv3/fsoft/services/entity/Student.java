package com.trinhtv3.fsoft.services.entity;

import java.util.UUID;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import com.mongodb.BasicDBObject;
import com.trinhtv3.fsoft.services.entity.factories.ReferenceFactory;
import com.trinhtv3.fsoft.services.entity.references.SchoolReference;

public class Student extends BasicDBObject{

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  private ReferenceFactory<SchoolReference> schoolRefFactory;
  
  public Student(){}
  
  @Inject
  public Student(ReferenceFactory<SchoolReference> schoolRefFactory,@Assisted("name") String name, @Assisted("age") String age, @Assisted("score") String score,@Assisted("classRoom") String classRoom, @Assisted("password") String password) {
    this.put("_id", name);
    this.put("age", age);
    this.put("score", score);
    this.put("classRoom", classRoom);
    this.put("password", password);
    
    this.schoolRefFactory = schoolRefFactory;
  }
  
  public String getId() {
    return this.getString("_id");
  }
  
  public String getAge() {
    return this.getString("age");
  }
  
  public String getScore() {
    return this.getString("score");
  }
  
  public String getClassRoom() {
    return this.getString("classRoom");
  }
  
  public String getPassword() {
    return this.getString("password");
  }
  
  public void setSchool(SchoolReference school) {
    this.put("school", school.toJSon());
  }
  
  public void setAge(String age) {
    this.put("age", age);
  }
  public SchoolReference getSchool() {
    Object obj = this.get("school");
    
    return obj == null ? null : schoolRefFactory.create(((BasicDBObject) obj).getString("_id"));
    
  }
  
}
