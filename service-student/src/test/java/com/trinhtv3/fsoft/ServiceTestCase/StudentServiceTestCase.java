package com.trinhtv3.fsoft.ServiceTestCase;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.google.inject.Key;
import com.google.inject.TypeLiteral;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.trinhtv3.fsoft.services.StudentService;
import com.trinhtv3.fsoft.services.entity.Student;
import com.trinhtv3.fsoft.services.entity.factories.ReferenceFactory;
import com.trinhtv3.fsoft.services.entity.factories.StudentFactory;
import com.trinhtv3.fsoft.services.entity.references.SchoolReference;
import com.trinhtv3.fsoft.services.entity.references.StudentReference;

public class StudentServiceTestCase extends AbstractTestCase {

  private StudentService service;
  
  private StudentFactory factory;
  
  private ReferenceFactory<SchoolReference> schoolRef;
  
  private ReferenceFactory<StudentReference> studentRef;
  
  @BeforeMethod
  public void setUp() {
    
    super.init();
    service = injector.getInstance(StudentService.class);
    
    schoolRef = injector.getInstance(Key.get(new TypeLiteral<ReferenceFactory<SchoolReference>>(){}));
    studentRef = injector.getInstance(Key.get(new TypeLiteral<ReferenceFactory<StudentReference>>(){}));
    
    factory = injector.getInstance(StudentFactory.class);
  }
  
  @AfterMethod
  public void tearDown() {
    mongoService.dropDatabase();
  }
  
  @Test
  public void testCreateStudent() {
    
    initData();
    
    Assert.assertEquals(service.count(), 100);
    
  }
  
  @Test
  public void testUpdateStudent() {

    initData();
    DBObject object = new BasicDBObject("name", "student10");
    
    List<Student> list = service.find(object);
    Assert.assertEquals(list.size(), 1);
    
    Student student = list.get(0);
    
    student.setName("UpdatedName");;
    
    service.update(student);
    
    Assert.assertEquals(service.find(object).size(), 0);
    
    object = new BasicDBObject("name", "UpdatedName");
    Assert.assertEquals(service.find(object).size(), 1);
    
  }
  
  @Test
  public void testDeleteStudent() {
    
    initData();
    DBObject object = new BasicDBObject("name", "student10");
    
    List<Student> list = service.find(object);
    Assert.assertEquals(list.size(), 1);
    
    Student student = list.get(0);
    
    service.delete(student);
    
    Assert.assertEquals(service.count(), 99);
  }
  
  @Test
  public void testFindStudentInSchool() {
    
    initData();
    
    SchoolReference ref = schoolRef.create("THPT-GiaoThuy");
    
    List<Student> list = service.findStudentInSchool(ref);
    
    Assert.assertEquals(list.size(), 100);
    
  }
  
  @Test
  public void testGetAllStudents() {
    initData();
    Assert.assertEquals(service.getAll().size(), 100);
  }
  
  private void initData() {
    
    for (int i = 1; i <= 100; i ++) {
      
      String name = "student" + i;
      
      String age = "age" + i;
      
      String score = "10";
      
      String classRoom = "40";
      
      String pass = "pass";
      
      Student student = factory.create(name, age, score, classRoom, pass);
      
      SchoolReference ref = schoolRef.create("THPT-GiaoThuy");
      student.setSchool(ref);
      
      service.create(student);
      
    }
  }
}
