package com.trinhtv3.fsoft.services.entity.references;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import com.trinhtv3.fsoft.services.SchoolService;
import com.trinhtv3.fsoft.services.entity.School;


public class SchoolReference extends Reference<School>{

  private SchoolService schoolService;
  
  @Inject
  public SchoolReference(@Assisted("id") String id, SchoolService service) {
    
    super(id);
    this.schoolService = service;
  }
  
  @Override
  public School get() {
    return this.schoolService.get(id);
  }

}
