package com.thoughtworks.mvc.container;

import java.lang.reflect.Method;
import java.util.List;

public interface IocContainer {
    void init(String path);

    List<Object> findAllBeans();

    Method getMethodByMappingUrl(String url);

    <T> T inject(Class<T> clazz);

    void destroy();
}
