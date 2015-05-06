/**
 * author: trinhtv3
 */

package com.trinhtv3.fsoft.services;

import java.util.ArrayList;
import java.util.List;

import com.google.inject.Inject;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.trinhtv3.fsoft.services.base.MongoAbstractCRUD;
import com.trinhtv3.fsoft.services.entity.Student;
import com.trinhtv3.fsoft.services.entity.factories.StudentFactory;
import com.trinhtv3.fsoft.services.entity.references.SchoolReference;
import com.trinhtv3.fsoft.services.utils.DataFactory;



public class StudentService extends MongoAbstractCRUD<Student> {

  private final String col_name = "students";
  
  @Inject
  private StudentFactory factory;
  
  @Inject
  public StudentService(DataFactory mongo) {
    this.col = mongo.getDatabase().getCollection(col_name);
    
  }
  @Override
  public Student transform(DBObject source) {
    Student student = factory.create((String)source.get("name"), (String)source.get("age"), (String)source.get("score"),(String) source.get("classRoom"), (String)source.get("password"));
    student.put("_id", source.get("_id"));
    
    student.put("school", source.get("school"));
    
    return student;
  }

  public List<Student> findStudentInSchool(SchoolReference ref) {
    
    List<Student> list = new ArrayList<Student>();
    
    BasicDBObject object = new BasicDBObject("school", ref.toJSon());
    
    DBCursor cursor = this.col.find(object);
    
    while (cursor.hasNext()) {
      
      DBObject result = cursor.next();
      
      Student student = transform(result);
      
      list.add(student);
    }
    return list;
    
  }
}




