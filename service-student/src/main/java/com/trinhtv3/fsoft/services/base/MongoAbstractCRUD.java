package com.trinhtv3.fsoft.services.base;

import java.util.ArrayList;
import java.util.List;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

public abstract class MongoAbstractCRUD<T extends DBObject> {

  protected DBCollection col;
  
  public long count() {
    return this.col.count();
  }
  
  public void create(T... obj) {
    
    this.col.insert(obj);
  }
  
  public void update(T obj) {
    
    this.col.save(obj);
  }
  
  public void delete(T obj) {
    this.col.remove(obj);
  }
  
  public void delete(String id) {
    this.col.remove(new BasicDBObject("_id", id));
  }
  
  public T get(String id) {
    DBObject obj = this.col.findOne(new BasicDBObject("_id", id));
    
    return obj == null ? null : transform(obj);
  }
  
  public abstract T transform(DBObject source);
  
  public List<T> find(DBObject query) {
    
    List<T> list = new ArrayList<T>();
    DBCursor cursor = this.col.find(query);
    try {
      while (cursor.hasNext()) {
        
        DBObject object = cursor.next();
        T instance = transform(object);
        
        list.add(instance);
      }
    }
    finally {
      cursor.close();
    }
    return list;
    
  }
  
  public List<T> getAll() {
    
    List<T> list = new ArrayList<T>();
    DBCursor cursor = this.col.find();
    
    while (cursor.hasNext()) {
      DBObject object = cursor.next();
      T instance = transform(object);
      list.add(instance);
    }
    return list;
  }
  
  public T findById(String id) {
    
    DBObject object = new BasicDBObject().append("_id", id);
    DBObject result = this.col.findOne(object);
    T instance = transform(result);
    
    return instance;
    
  }
}
