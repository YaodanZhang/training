package com.thoughtworks.ioc;

import com.google.common.base.Objects;
import com.google.common.base.Predicate;
import com.google.common.base.Strings;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

import java.util.List;

public class ElementContainer {
    private List<AbstractElement> elements = Lists.newArrayList();

    public void add(AbstractElement element) {
        if (elements.contains(element)) {
            throw new IllegalStateException();
        }
        elements.add(element);
    }

    public <T> Class<T> findClassWithAnnotatedName(final Class<?> clazz, final String annotatedName) {
        return findElement(clazz, annotatedName).getValueClass();
    }

    public AbstractElement findDefaultElement(final Class<?> clazz) {
        return Iterables.find(elements, new Predicate<AbstractElement>() {
            @Override
            public boolean apply(AbstractElement element) {
                return isDefaultElement(element, clazz);
            }
        });
    }

    public AbstractElement findElement(final Class<?> clazz, final String annotatedName) {
        if (Strings.isNullOrEmpty(annotatedName)) {
            return findDefaultElement(clazz);
        }

        return Iterables.find(elements, new Predicate<AbstractElement>() {
            @Override
            public boolean apply(AbstractElement element) {
                return isElementWithAnnotatedName(element, clazz, annotatedName);
            }
        });
    }

    private static boolean isDefaultElement(AbstractElement element, Class<?> clazz) {
        return element.getKeyClass() == clazz && Strings.isNullOrEmpty(element.getAnnotatedName());
    }

    private static boolean isElementWithAnnotatedName(AbstractElement element, Class<?> clazz, String annotatedName) {
        return element.getKeyClass() == clazz && Objects.equal(element.getAnnotatedName(), annotatedName);
    }

    public <T> T getInstance(Class<?> clazz) {
        return getInstance(clazz, null);
    }

    public <T> T getInstance(Class<?> clazz, String annotationName) {
        return findElement(clazz, annotationName).getInstance(this);
    }
}
