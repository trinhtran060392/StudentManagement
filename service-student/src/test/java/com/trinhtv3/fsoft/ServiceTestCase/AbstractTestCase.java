package com.trinhtv3.fsoft.ServiceTestCase;

import com.trinhtv3.fsoft.MongoDBService;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.trinhtv3.fsoft.services.module.OrganizationModule;

public class AbstractTestCase {

  protected Injector injector;
  
  protected MongoDBService mongoService;
  
  public void init() {
    
    injector = Guice.createInjector(new OrganizationModule());
    
    mongoService = injector.getInstance(MongoDBService.class);
    
  }
}
