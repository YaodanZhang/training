package com.thoughtworks.mvc.container;

public class ModuleClassLoader {
    public <T> T load(String modulePath) {
        try {
            return (T) Class.forName(modulePath).newInstance();
        } catch (Exception e) {
        }

        throw new IllegalArgumentException("Couldn't found module.");
    }
}
