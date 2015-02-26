package com.thoughtworks.ioc;

public class SingletonElement extends AbstractElement {
    private final Object mutex = new Object();
    private Object instance;

    public SingletonElement(AbstractElement element) {
        super(element.getKeyClass());
        setValueClass(element.getValueClass());
        setAnnotatedName(element.getAnnotatedName());
    }

    @Override
    public <T> T getInstance(ElementContainer container) {
        synchronized (mutex) {
            if (instance == null) {
                instance = new Assembler(valueClass, container).newInstance();
            }
        }
        return (T) instance;
    }
}
