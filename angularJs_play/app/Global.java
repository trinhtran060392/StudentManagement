
import play.Application;
import play.GlobalSettings;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Key;
import com.google.inject.TypeLiteral;
import com.trinhtv3.fsoft.MongoDBService;
import com.trinhtv3.fsoft.services.SchoolService;
import com.trinhtv3.fsoft.services.StudentService;
import com.trinhtv3.fsoft.services.entity.Student;
import com.trinhtv3.fsoft.services.entity.factories.ReferenceFactory;
import com.trinhtv3.fsoft.services.entity.factories.SchoolFactory;
import com.trinhtv3.fsoft.services.entity.factories.StudentFactory;
import com.trinhtv3.fsoft.services.entity.references.SchoolReference;
import com.trinhtv3.fsoft.services.module.OrganizationModule;

/**
 * TrinhTV3@fsoft.com.vn
 */

/**
 * @author TrinhTV3
 *
 */
public class Global extends GlobalSettings{

  private Injector injector;
  
  @Override
  public void onStart(Application application) {
    injector = Guice.createInjector(new OrganizationModule());
    
    StudentService studentService = injector.getInstance(StudentService.class);
    SchoolService schoolService = injector.getInstance(SchoolService.class);
    StudentFactory studentFactory = injector.getInstance(StudentFactory.class);
    SchoolFactory schoolFactory = injector.getInstance(SchoolFactory.class);
    
    ReferenceFactory<SchoolReference> schoolRef = injector.getInstance(Key.get(new TypeLiteral<ReferenceFactory<SchoolReference>>(){}));
    if (studentService.count() == 0 || schoolService.count() == 0) {
      for (int i = 1; i <= 100; i ++) {
        
        String name = "student" + i;
        
        String age = "age" + i;
        
        String score = "10";
        
        String classRoom = "40";
        
        String pass = "pass";
        
        Student student = studentFactory.create(name, age, score, classRoom, pass);
        
        SchoolReference ref = schoolRef.create("THPT-GiaoThuy");
        student.setSchool(ref);
        
        studentService.create(student);
        
      }
    }
    
  }
  
  @Override
  public void onStop(Application app) {
    
    MongoDBService service = injector.getInstance(MongoDBService.class);
    service.dropDatabase();
  }
  
  @Override
  public <T> T getControllerInstance(Class<T> aClass) throws Exception {
    
    return injector.getInstance(aClass);
  }
  
}
