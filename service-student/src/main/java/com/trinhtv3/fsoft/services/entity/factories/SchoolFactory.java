package com.trinhtv3.fsoft.services.entity.factories;


import com.google.inject.assistedinject.Assisted;
import com.trinhtv3.fsoft.services.entity.School;

public interface SchoolFactory {

  public School create(@Assisted("_id") String name, @Assisted("address") String address);
}
