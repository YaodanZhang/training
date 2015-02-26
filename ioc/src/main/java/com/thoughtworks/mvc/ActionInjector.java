package com.thoughtworks.mvc;

import com.google.common.base.CaseFormat;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class ActionInjector {

    private final Map<String, String> parameterMap;
    private final Map<String, Map<String, String>> nestedSubParameterMap;
    private final Object action;

    private  <T> ActionInjector(Class<T> clazz, Map<String, String> parameterMap)
            throws IllegalAccessException, InstantiationException {
        this(clazz.newInstance(), parameterMap);
    }

    public <T> ActionInjector(T instance, Map<String, String> parameterMap) {
        this.action = instance;
        this.parameterMap = parameterMap;
        nestedSubParameterMap = new HashMap<String, Map<String, String>>();
    }

    public <T> T inject() {
        Method[] setters = action.getClass().getMethods();

        processForNestedParameter();
        for (Method setter : setters) {
            String fieldName = setterNameToFieldName(setter.getName());
            if (isSetter(setter) && parameterMap.containsKey(fieldName)) {
                invokePrimitiveSetter(setter, parameterMap.get(fieldName));
            } else if (isSetter(setter) && nestedSubParameterMap.containsKey(fieldName)) {
                // nested structure
                invokeNestedStructureSetter(setter, fieldName);
            }
        }

        return (T) action;
    }

    private void processForNestedParameter() {
        for (String key : parameterMap.keySet()) {
            if (isNestedKey(key)) {
                Map<String, String> subMap = findOrNewSubParameterMap(getNestedSuperKey(key));
                subMap.put(getNestedSubKey(key), parameterMap.get(key));
            }
        }
    }

    private Map<String, String> findOrNewSubParameterMap(String superKey) {
        Map<String, String> subMap = null;

        if (nestedSubParameterMap.containsKey(superKey)) {
            subMap = nestedSubParameterMap.get(superKey);
        } else {
            subMap = new HashMap<String, String>();
            nestedSubParameterMap.put(superKey, subMap);
        }

        return subMap;
    }

    private void invokeNestedStructureSetter(Method setter, String fieldName) {
        ActionInjector subObjectInjector = null;
        try {
            subObjectInjector = new ActionInjector(setter.getParameterTypes()[0], nestedSubParameterMap.get(fieldName));
        } catch (IllegalAccessException e) {
            throw new IllegalArgumentException(e);
        } catch (InstantiationException e) {
            throw new IllegalArgumentException(e);
        }

        try {
            setter.invoke(action, subObjectInjector.inject());
        } catch (IllegalAccessException e) {
            throw new IllegalArgumentException(e);
        } catch (InvocationTargetException e) {
            throw new IllegalArgumentException(e);
        }
    }

    private void invokePrimitiveSetter(Method setter, String parameter) {
        Class<?> parameterClass = setter.getParameterTypes()[0];
        try {
            if (parameterClass == int.class || parameterClass == Integer.class) {
                setter.invoke(action, Integer.parseInt(parameter));
            } else if (parameterClass == boolean.class || parameterClass == Boolean.class) {
                setter.invoke(action, Boolean.parseBoolean(parameter));
            } else if (parameterClass == byte.class || parameterClass == Byte.class) {
                setter.invoke(action, Byte.parseByte(parameter));
            } else if (parameterClass == char.class || parameterClass == Character.class) {
                setter.invoke(action, parameter.charAt(0));
            } else if (parameterClass == short.class || parameterClass == Short.class) {
                setter.invoke(action, Short.parseShort(parameter));
            } else if (parameterClass == long.class || parameterClass == Long.class) {
                setter.invoke(action, Long.parseLong(parameter));
            } else if (parameterClass == float.class || parameterClass == Float.class) {
                setter.invoke(action, Float.parseFloat(parameter));
            } else if (parameterClass == double.class || parameterClass == Double.class) {
                setter.invoke(action, Double.parseDouble(parameter));
            } else if (parameterClass == String.class) {
                setter.invoke(action, parameter);
            }
        } catch (IllegalAccessException e) {
            throw new IllegalArgumentException(e);
        } catch (InvocationTargetException e) {
            throw new IllegalArgumentException(e);
        }
    }

    private static boolean isSetter(Method setter) {
        return setter.getName().startsWith("set") && setter.getParameterTypes().length == 1;
    }

    private static String setterNameToFieldName(String setterName) {
        return setterName.length() > 3 ?
                CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_CAMEL, setterName.substring(3))
                : setterName;
    }

    private static boolean isNestedKey(String key) {
        return key.contains(".");
    }

    private static String getNestedSuperKey(String key) {
        return key.substring(0, key.indexOf("."));
    }

    private static String getNestedSubKey(String key) {
        return key.substring(key.indexOf(".") + 1);
    }
}
