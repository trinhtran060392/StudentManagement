package com.trinhtv3.fsoft.services.entity.factories;


import com.google.inject.assistedinject.Assisted;
import com.trinhtv3.fsoft.services.entity.Student;

public interface StudentFactory {

  public Student create(@Assisted("name") String name, @Assisted("age") String age,@Assisted("score") String score,@Assisted("classRoom") String classRoom, @Assisted("password") String password);
  
}
