package com.trinhtv3.fsoft.services.entity.factories;

import com.google.inject.assistedinject.Assisted;
import com.trinhtv3.fsoft.services.entity.references.Reference;


public interface ReferenceFactory<R extends Reference<?>> {
  
  R create(@Assisted("id") String id);
}
