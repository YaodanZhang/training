package com.thoughtworks.mvc.container;

import com.google.common.base.Function;
import com.google.common.collect.FluentIterable;
import com.google.inject.*;
import com.thoughtworks.mvc.annotations.Mapping;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GuiceContainer implements IocContainer {
    private Injector injector;
    private Map<String, Method> methodMap;

    @Override
    public void init(String path) {
        methodMap = new HashMap<String, Method>();
        Module module = new ModuleClassLoader().load(path);
        injector = Guice.createInjector(module);
        for (Key key : injector.getAllBindings().keySet()) {
            for (Method method : key.getTypeLiteral().getRawType().getMethods()) {
                if (method.isAnnotationPresent(Mapping.class)) {
                    methodMap.put(method.getAnnotation(Mapping.class).url(), method);
                }
            }
        }
    }

    @Override
    public Method getMethodByMappingUrl(String url) {
        return methodMap.get(url);
    }

    @Override
    public <T> T inject(Class<T> clazz) {
        return injector.getInstance(clazz);
    }

    @Override
    public List<Object> findAllBeans() {
        Map<Key<?>,Binding<?>> allBindings = injector.getBindings();
        return FluentIterable.from(allBindings.keySet()).transform(new Function<Key<?>, Object>() {
            @Override
            public Object apply(Key<?> key) {
                return injector.getInstance(key);
            }
        }).toList();
    }

    @Override
    public void destroy() {
    }
}
