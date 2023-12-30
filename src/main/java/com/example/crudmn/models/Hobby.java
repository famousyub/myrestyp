package com.example.crudmn.models;

public class Hobby {

    private String name;

    public Hobby(final String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Hobby{" +
                "name='" + name + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
