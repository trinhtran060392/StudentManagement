package com.trinhtv3.fsoft;

public interface DatabaseService<D> {

  public D getDatabase();
  
  public void dropDatabase();
}
