package com.thoughtworks.ioc;

import com.thoughtworks.ioc.annotations.Inject;
import com.thoughtworks.ioc.annotations.Named;

import java.lang.annotation.Annotation;
import java.lang.reflect.*;

import static com.google.common.base.Preconditions.checkNotNull;

public class Assembler {
    private Class<?> clazz;
    private ElementContainer container;

    public Assembler(Class<?> clazz, ElementContainer container) {
        checkNotNull(clazz);
        checkNotNull(container);

        this.clazz = clazz;
        this.container = container;
    }

    public <T> T newInstance() {
        try {
            T instance = constructInstance();
            findAndInjectMethods(instance);
            findAndInjectFields(instance);
            return instance;
        } catch (Exception e) {
            throw new IllegalStateException("Can't initiate the class.", e);
        }
    }

    private void findAndInjectFields(Object instance) throws IllegalAccessException {
        Field[] declaredFields = clazz.getDeclaredFields();
        for (Field field : declaredFields) {
            if (field.isAnnotationPresent(Inject.class)) {
                Named named = field.getAnnotation(Named.class);
                singleFieldInject(instance, field, named == null ? null : named.value());
            }
        }
    }

    private void findAndInjectMethods(Object instance) throws IllegalAccessException, InvocationTargetException {
        Method[] declaredMethods = clazz.getDeclaredMethods();
        for (Method method : declaredMethods) {
            if (method.isAnnotationPresent(Inject.class)) {
                injectMethod(instance, method);
            }
        }
    }

    private void injectMethod(Object instance, Method method)
            throws InvocationTargetException, IllegalAccessException {
        Class<?>[] argTypes = method.getParameterTypes();
        Object[] argsInstances = getInstancesOfMethodParameters(argTypes, method.getParameterAnnotations());
        method.invoke(instance, argsInstances);
    }

    private <T> T constructInstance() throws IllegalAccessException, InstantiationException, InvocationTargetException {
        Constructor[] declaredConstructors = clazz.getDeclaredConstructors();
        Constructor injectConstructor = null;
        for (Constructor constructor : declaredConstructors) {
            if (constructor.isAnnotationPresent(Inject.class) &&
                    constructor.getParameterTypes().length > 0) {
                injectConstructor = constructor;
                break;
            }
        }
        return (T) ((null == injectConstructor) ? clazz.newInstance() : injectConstructor(injectConstructor));
    }

    private <T> T injectConstructor(Constructor<T> constructor)
            throws IllegalAccessException, InvocationTargetException, InstantiationException {
        Class<?>[] argsTypes = constructor.getParameterTypes();
        Object[] argsInstances = getInstancesOfMethodParameters(argsTypes, constructor.getParameterAnnotations());
        return constructor.newInstance(argsInstances);
    }

    private Object[] getInstancesOfMethodParameters(Class<?>[] argsTypes, Annotation[][] parameterAnnotations) {
        Object[] argsInstances = new Object[argsTypes.length];
        for (int i = 0, length = argsTypes.length; i < length; i++) {
            Named named = findAnnotationInArraysOrReturnNull(Named.class, parameterAnnotations[i]);
            argsInstances[i] = newInjectInstance(argsTypes[i], named == null ? null : named.value());
        }
        return argsInstances;
    }

    private <T> T findAnnotationInArraysOrReturnNull(Class<?> annotationClass, Annotation[] annotations) {
        Annotation result = null;
        for (Annotation annotation : annotations) {
            if (annotationClass.isInstance(annotation)) {
                result = annotation;
                break;
            }
        }
        return (T) result;
    }

    private void singleFieldInject(Object instance, Field field, String annotatedName) throws IllegalAccessException {
        if (!Modifier.isPublic(field.getModifiers())) {
            field.setAccessible(true);
        }
        field.set(instance, newInjectInstance(field.getType(), annotatedName));
    }

    private Object newInjectInstance(Class<?> fieldType, String annotatedName) {
        return container.findElement(fieldType, annotatedName).getInstance(container);
    }
}
