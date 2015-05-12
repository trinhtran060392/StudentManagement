package com.trinhtv3.fsoft.services;

import com.trinhtv3.fsoft.MongoDBService;

import com.google.inject.Inject;
import com.mongodb.DBObject;
import com.trinhtv3.fsoft.services.base.MongoAbstractCRUD;
import com.trinhtv3.fsoft.services.entity.School;
import com.trinhtv3.fsoft.services.entity.factories.SchoolFactory;


public class SchoolService extends MongoAbstractCRUD<School> {

  private final String col_name = "schools";
  
  @Inject
  private SchoolFactory factory;
  
  @Inject
  public SchoolService(MongoDBService mongo) {
    
    this.col = mongo.getDatabase().getCollection(col_name);
  
  }
  
  @Override
  public School transform(DBObject source) {
    School school = factory.create((String) source.get("_id"), (String) source.get("address"));
    
    return school;
  }

}
