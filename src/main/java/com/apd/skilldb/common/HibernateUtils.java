// ****************************************************************
// * Copyright (c) 2005 Ford Motor Company. All Rights Reserved.
// *
// * $Workfile:  $
// * $Revision:  $
// * $Author:  $
// * $Date:  $
// *
// *****************************************************************
package com.apd.skilldb.common;

import java.util.Collection;
import java.util.List;

import org.apache.commons.collections.Predicate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author alepasana
 *
 */
public class HibernateUtils {
   
   private final static Logger LOGGER = LoggerFactory.getLogger(HibernateUtils.class);

   private HibernateUtils() {}

   public static Predicate attributePredicateFactory(final Class<?> clazz, final String attribute, final Object value) {
      
      return new Predicate() {
         
         public boolean evaluate(Object o) {
            
            String method = BeanUtils.getterFromAttribute(attribute);
            boolean same = false;
            if (method != null) {
               Object valueFromObject = BeanUtils.callMethodWithResult(o, method);
               
               if (valueFromObject != null
                     && clazz != null
                     && clazz.isInstance(o)
                     && value != null) {
                     
                  if (value.getClass() !=  valueFromObject.getClass()) { // check if not comparing incompatible data types
                     throw new IllegalArgumentException("Attempt to compare incombatible types. argument ["+value.getClass()
                           +"], method result ["+valueFromObject.getClass()+"]");
                  }
                  
                  if (value.equals(valueFromObject)) { 
                     same = true;
                  }
                  
               }
            }
            
            return same;
         }
      };
   }
}
