package com.trinhtv3.fsoft.services.utils;

import java.net.UnknownHostException;

import com.mongodb.DB;
import com.mongodb.MongoClient;

public class DataFactory {
  private static final MongoClient client;
  
  private final String dbname = "angularTest";
  static {
    try {
      client = new MongoClient();
    } catch (UnknownHostException e) {
      throw new RuntimeException(e);
    }
  }
  
  public DB getDatabase() {
    return client.getDB(this.dbname);
  }
  
  public void dropDatabase() {
    client.dropDatabase(this.dbname);
  }
}
