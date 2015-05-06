package com.trinhtv3.fsoft.ServiceTestCase;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.trinhtv3.fsoft.services.module.OrganizationModule;
import com.trinhtv3.fsoft.services.utils.DataFactory;

public class AbstractTestCase {

  protected Injector injector;
  
  protected DataFactory mongoService;
  
  public void init() {
    
    injector = Guice.createInjector(new OrganizationModule());
    
    mongoService = injector.getInstance(DataFactory.class);
    
  }
}
