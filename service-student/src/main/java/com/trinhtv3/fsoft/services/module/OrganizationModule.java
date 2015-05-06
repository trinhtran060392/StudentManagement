package com.trinhtv3.fsoft.services.module;


import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;
import com.google.inject.assistedinject.FactoryModuleBuilder;
import com.trinhtv3.fsoft.services.SchoolService;
import com.trinhtv3.fsoft.services.StudentService;
import com.trinhtv3.fsoft.services.entity.factories.ReferenceFactory;
import com.trinhtv3.fsoft.services.entity.factories.SchoolFactory;
import com.trinhtv3.fsoft.services.entity.factories.StudentFactory;
import com.trinhtv3.fsoft.services.entity.references.SchoolReference;
import com.trinhtv3.fsoft.services.entity.references.StudentReference;
import com.trinhtv3.fsoft.services.utils.DataFactory;

public class OrganizationModule extends AbstractModule {

  @Override
  protected void configure() {
    
    install(new FactoryModuleBuilder().build(SchoolFactory.class));
    install(new FactoryModuleBuilder().build(StudentFactory.class));
    
    install(new FactoryModuleBuilder().build(new TypeLiteral<ReferenceFactory<SchoolReference>>(){}));
    install(new FactoryModuleBuilder().build(new TypeLiteral<ReferenceFactory<StudentReference>>(){}));
    
    bind(StudentService.class);
    bind(SchoolService.class);
    
    bind(DataFactory.class).toInstance(new DataFactory());
    
  }

}
