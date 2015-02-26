package com.thoughtworks.ioc;

public class PrototypeElement extends AbstractElement {

    public PrototypeElement(Class<?> keyClass) {
        super(keyClass);
    }

    public <T> T getInstance(ElementContainer container) {
        return new Assembler(valueClass, container).newInstance();
    }
}
