package com.thoughtworks.mvc.example;

public class Nested {
    private int id;
    private String name;
    private Nested next;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Nested getNext() {
        return next;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNext(Nested next) {
        this.next = next;
    }
}
