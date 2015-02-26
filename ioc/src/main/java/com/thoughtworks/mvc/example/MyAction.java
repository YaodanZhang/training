package com.thoughtworks.mvc.example;

import com.thoughtworks.mvc.annotations.Mapping;

public class MyAction {

    @Mapping(url = "/in", forward = "/out")
    public void testMapping() {
    }
}
