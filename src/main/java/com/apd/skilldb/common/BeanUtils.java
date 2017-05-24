package com.apd.skilldb.common;

import java.lang.reflect.Method;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BeanUtils {
	private final static Logger logger = LoggerFactory.getLogger(BeanUtils.class);

	private BeanUtils() {}

	private static final String GET = "get";

	public static String getterFromAttribute(String attribute) {
		String getter = null;
		if (StringUtils.isNotBlank(attribute)) {
			StringBuffer sb = new StringBuffer();
			sb.append(GET);
			sb.append(hibernateAttributeName(attribute));
			getter = sb.toString();
		}
		return getter;
	}

	/**
	 * Converts an attribute name to one that reflects the hibernate naming conventions.
	 * @param name
	 * @return
	 */
	private static String hibernateAttributeName(String name)
	{
		String hibernateName = org.apache.commons.lang.StringUtils.capitalize(name);
		if (name.contains("_"))
		{
			StringBuilder newName = new StringBuilder();
			// alpha characters after the "_" are capitalised
			for (int i = 0; i < hibernateName.length(); i++)
			{
				if (hibernateName.charAt(i) == '_')
				{
					if (i < hibernateName.length() - 1)
					{
						// Title case for the next character and drop the underscore
						i++;
						newName.append(Character.toTitleCase(hibernateName.charAt(i)));
					}
					else
					{
						newName.append(hibernateName.charAt(i));
					}
				}
				else
				{
					newName.append(hibernateName.charAt(i));
				}
			}
			hibernateName = newName.toString();
		}

		return hibernateName;
	}

	public static Object callMethodWithResult(Object bean, String methodName, Object ... args) {
		return BeanUtils.callMethodWithResultAndError(bean, methodName, false, args);
	}

	@SuppressWarnings("unchecked")
	public static Object callMethodWithResultAndError(Object bean, String methodName, boolean propagateReflectionErrors, Object ... args) {
		Object methodResult = null;
		if (bean != null && StringUtils.isNotBlank(methodName)) {

			boolean goOn = true;
			Class klass = bean.getClass();

			Class [] paramTypes = null;
			if (args != null) {
				paramTypes = new Class [args.length];
				for (int i = 0; i < args.length; i++) {
					if (args[i] == null) {
						throw new IllegalArgumentException("null parameters not supported");
					}
					paramTypes[i] = args[i].getClass();
				}
			}

			while (goOn) {
				try {
					
					// complex field
                    if(methodName.indexOf(".") > 0){
                    	String tempMethodName = methodName;
                    	methodName = methodName.substring(0, (methodName.indexOf(".") + 1));
                    	
                    	Method method = klass.getDeclaredMethod(methodName, paramTypes);    					
    					methodResult = method.invoke(bean, args);
    					
    					methodName = BeanUtils.getterFromAttribute(tempMethodName.substring(0, methodName.length()));
                    	goOn = true;
                    	
                    }else{
                    	Method method = klass.getDeclaredMethod(methodName, paramTypes);    					
    					methodResult = method.invoke(bean, args);
    					goOn = false;
                    }

				} catch (NoSuchMethodException e) {
					//maybe we can try the parent?
							klass = klass.getSuperclass();
							if (klass == null) {
								goOn = false;
								logger.warn("Method ["+methodName+"] cannot be called on bean ["+bean+"]");
								if (logger.isTraceEnabled())
								{
									// log the stack trace - will aid in tracing this error
									logger.trace("tracing 'cannot be called on bean' warning");
								}

								if (propagateReflectionErrors) {
									throw new IllegalArgumentException(e);
								}

							}

				} catch (Exception e) {
					logger.error("", e);
				}
			}

		} else {
			logger.warn("Cannot call method [" + methodName + "]. bean is null.");
		}
		return methodResult;
	}

}
