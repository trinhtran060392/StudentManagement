package com.trinhtv3.fsoft.services.module;


import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;
import com.google.inject.assistedinject.FactoryModuleBuilder;
import com.trinhtv3.fsoft.MongoDBService;
import com.trinhtv3.fsoft.services.MongoAuthenticationService;
import com.trinhtv3.fsoft.services.OrganizationContext;
import com.trinhtv3.fsoft.services.SchoolService;
import com.trinhtv3.fsoft.services.StudentService;
import com.trinhtv3.fsoft.services.base.AuthenticationService;
import com.trinhtv3.fsoft.services.entity.Student;
import com.trinhtv3.fsoft.services.entity.factories.ReferenceFactory;
import com.trinhtv3.fsoft.services.entity.factories.SchoolFactory;
import com.trinhtv3.fsoft.services.entity.factories.StudentFactory;
import com.trinhtv3.fsoft.services.entity.references.SchoolReference;
import com.trinhtv3.fsoft.services.entity.references.StudentReference;
/**
 * TrinhTV3@fsoft.com.vn
 */

/**
 * @author TrinhTV3
 *
 */
public class OrganizationModule extends AbstractModule {

  @Override
  protected void configure() {
    
    install(new FactoryModuleBuilder().build(SchoolFactory.class));
    install(new FactoryModuleBuilder().build(StudentFactory.class));
    
    install(new FactoryModuleBuilder().build(new TypeLiteral<ReferenceFactory<SchoolReference>>(){}));
    install(new FactoryModuleBuilder().build(new TypeLiteral<ReferenceFactory<StudentReference>>(){}));
    
    bind(StudentService.class);
    bind(SchoolService.class);
    
    bind(MongoDBService.class);
    
    bind(new TypeLiteral<AuthenticationService<Student>>(){})
    .to(new TypeLiteral<MongoAuthenticationService>(){});
    
    bind(OrganizationContext.class);
    
  }

}
