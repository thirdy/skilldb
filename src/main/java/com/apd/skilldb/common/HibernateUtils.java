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
   
   
   public static Predicate attributePredicateFactoryContains(final Class<?> clazz, final String attribute, final String attribute2, final Object value) {
	      
	      return new Predicate() {
	         
	         public boolean evaluate(Object o) {
	            
	            String method = BeanUtils.getterFromAttribute(attribute);
	            String method2 = BeanUtils.getterFromAttribute(attribute2);
	            
	            boolean same = false;
	            if (method != null && method2 != null) {
	               Object valueFromObject = BeanUtils.callMethodWithResult(o, method);
	               Object valueFromObject2 = BeanUtils.callMethodWithResult(o, method2);
	               
	               if (valueFromObject != null
	                     && clazz != null
	                     && clazz.isInstance(o)
	                     && value != null) {
	                     
	                  if (value.getClass() !=  valueFromObject.getClass() || value.getClass() !=  valueFromObject2.getClass()) { // check if not comparing incompatible data types
	                     throw new IllegalArgumentException("Attempt to compare incombatible types. argument ["+value.getClass()
	                           +"], method result ["+valueFromObject.getClass()+"]");
	                  }
	                  
	                  System.out.println("Compare:" + (value.toString().toLowerCase().contains(valueFromObject.toString().toLowerCase())
	                		  || value.toString().toLowerCase().contains(valueFromObject2.toString().toLowerCase())));
	                  
	                  System.out.println("Value:" + value.toString().toLowerCase() + ":" + valueFromObject.toString().toLowerCase() + ":" + valueFromObject2.toString().toLowerCase());
	                  if (value.toString().toLowerCase().contains(valueFromObject.toString().toLowerCase())
	                		  || value.toString().toLowerCase().contains(valueFromObject2.toString().toLowerCase())) { 
	                     same = true;
	                  }
	                  
	               }
	            }
	            
	            return same;
	         }
	      };
	   }
}
