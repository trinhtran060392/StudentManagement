package com.trinhtv3.fsoft.ServiceTestCase;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import junit.framework.Assert;

import com.trinhtv3.fsoft.services.SchoolService;
import com.trinhtv3.fsoft.services.entity.School;
import com.trinhtv3.fsoft.services.entity.factories.SchoolFactory;

public class SchoolServiceTestCase extends AbstractTestCase {

  private SchoolFactory schoolFactory;
  
  private SchoolService service;
  
  @BeforeMethod
  public void setup() {
    
    super.init();
    
    service = injector.getInstance(SchoolService.class);
    schoolFactory = injector.getInstance(SchoolFactory.class);
  }
  
  @AfterMethod
  public void tearDown() {
    mongoService.dropDatabase();
  }
  
  @Test
  public void testCreateSchool() {
    initData();
    
    Assert.assertEquals(service.count(), 100);
    
    School school = schoolFactory.create("test", "address");
    service.create(school);
    
    Assert.assertEquals(service.count(), 101);
    
  }
  
  @Test
  public void testDeleteSchool() {
    initData();
    
    service.delete("School1");
    
    Assert.assertEquals(service.count(), 99);
  }
  
  @Test
  public void testUpdateSchool() {
    initData();
    
    School school = service.get("School1");
    school.setAddress("UpdatedAddress");
    service.update(school);
    
    Assert.assertEquals(service.get("School1").getAddress(), "UpdatedAddress");
  }
  
  private void initData() {
    
    for (int i = 1; i <= 100; i ++) {
      
      String name = "School"+ i;
      String address = "Address"+ i;
      
      School school = schoolFactory.create(name, address);
      service.create(school);
    }
  }
}
