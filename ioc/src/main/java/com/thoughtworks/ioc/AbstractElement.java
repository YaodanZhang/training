package com.thoughtworks.ioc;

import com.google.common.base.Objects;
import com.thoughtworks.ioc.annotations.ImplementedBy;

public abstract class AbstractElement {
    protected Class<?> keyClass;
    protected Class<?> valueClass;
    protected String annotatedName;

    public AbstractElement(Class<?> keyClass) {
        this.keyClass = keyClass;
        detectImplementedBy();
    }

    public abstract <T> T getInstance(ElementContainer container);

    public void setValueClass(Class<?> valueClass) {
        this.valueClass = valueClass;
    }

    public void setAnnotatedName(String annotatedName) {
        this.annotatedName = annotatedName;
    }

    public String getAnnotatedName() {
        return annotatedName;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (o instanceof AbstractElement) {
            AbstractElement element = (AbstractElement) o;
            return Objects.equal(element.keyClass, keyClass)
                    && Objects.equal(element.annotatedName, annotatedName);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(keyClass, annotatedName);
    }

    private void detectImplementedBy() {
        if (keyClass.isAnnotationPresent(ImplementedBy.class)) {
            setValueClass(keyClass.getAnnotation(ImplementedBy.class).value());
        }
    }

    public Class<?> getKeyClass() {
        return keyClass;
    }

    public <T> Class<T> getValueClass() {
        return (Class<T>) valueClass;
    }
}
