package controllers;

import java.util.List;

import play.data.DynamicForm;
import play.data.Form;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.inject.Inject;
import com.trinhtv3.fsoft.services.MongoAuthenticationService;
import com.trinhtv3.fsoft.services.OrganizationContext;
import com.trinhtv3.fsoft.services.StudentService;
import com.trinhtv3.fsoft.services.entity.Student;
import com.trinhtv3.fsoft.services.entity.factories.StudentFactory;
/**
 * TrinhTV3@fsoft.com.vn
 */

/**
 * @author TrinhTV3
 *
 */
public class Application extends Controller {

  @Inject
  private StudentFactory studentFactory;
  
  @Inject
  private StudentService studentService;
  
  @Inject
  private MongoAuthenticationService authService;
  
  @Inject
  private OrganizationContext context;
  
  public Result allStudents() {
    
    List<Student> list = studentService.getAll();
    
    ObjectNode object;
    ArrayNode array = Json.newObject().arrayNode();
    
    for (Student s : list) {
      object = Json.newObject();
      object.put("_id", s.getId());
      object.put("age", s.getAge());
      
      array.add(object);
    }
    
    return ok(array);
  }
  
  public Result createStudent() {
    
    JsonNode json = request().body().asJson();
    String name = json.findPath("name").asText();
    String password = json.findPath("password").asText();
    String age = json.findPath("age").asText();
    String score = json.findPath("score").asText();
    String classRoom = json.findPath("classRoom").asText();
    
    Student student = studentFactory.create(name, age, score, classRoom, password);
    
    studentService.create(student);
    
    return ok();
  }
  
  public Result getStudent(String id) {
    
    Student student = studentService.findById(id);
    
    ObjectNode json = Json.newObject();
    json.put("_id", student.getId());
    json.put("age", student.getAge());
    json.put("score", student.getScore());
    json.put("classRoom", student.getClassRoom());
    json.put("password", student.getPassword());
    return ok(json);
  }
  
  public Result doUpdateStudent(String id) {
    JsonNode json = request().body().asJson();
    String name = json.findPath("name").asText();
    String score = json.findPath("score").asText();
    String age = json.findPath("age").asText();
    String classRoom = json.findPath("classRoom").asText();
    String password = json.findPath("password").asText();
    
    Student student = studentService.findById(id);
    
    student.put("_id", id);
    student.put("score", score);
    student.put("age", age);
    student.put("classRoom", classRoom);
    student.put("password", password);
    studentService.update(student);
    
    return ok();
    
  }
  
  public Result deleteStudent(String id) {
    
    Student student = studentService.findById(id);
    studentService.delete(student);
    
    return ok();
  }
  
  public Result checkLogin() {
    
    System.out.println("Logging in -------------");
    DynamicForm form = Form.form().bindFromRequest();
    
    String name = form.get("name");
    String pass = form.get("password");
    
    String token = authService.logIn(name, pass);
    
    ObjectNode object = Json.newObject();
    object.put("AuthenKey", token);
    return ok(object);
  }
  
  public Result current() {
    
    ObjectNode json = Json.newObject();
    
    System.out.println("+++++++++++");
    if (context.getStudent() != null) {
      
      json.put("student", context.getStudent().toString());
    }
    
    return ok(json);
    
  }
  
  public Result getBoyStudent() {
    return ok();
  }
  
  public Result getGirlStudent() {
    return ok();
  }
  
  public Result getStudentsByPage() {
    return ok();
  }
}