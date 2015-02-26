package com.thoughtworks.ioc.example.marsrover;

import com.thoughtworks.ioc.annotations.Singleton;

@Singleton
public interface OrientationFactory {

    public Orientation getOrientation(String s);
}
