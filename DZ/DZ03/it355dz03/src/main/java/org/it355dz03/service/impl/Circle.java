package org.it355dz03.service.impl;

import org.it355dz03.service.Shape;

public class Circle implements Shape {

    @Override
    public double volume(double radius) {
        if (radius < 0) throw new IllegalArgumentException();
        return 2 * radius * Math.PI;
    }

    @Override
    public double area(double radius) {
        if (radius < 0) throw new IllegalArgumentException();
        return radius * radius * Math.PI;
    }
}
