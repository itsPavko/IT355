package org.it355dz02.service.impl;

import org.it355dz02.service.Shape;

public class Circle implements Shape {

    @Override
    public double volume(double radius) {
        return 2 * radius * Math.PI;
    }

    @Override
    public double area(double radius) {
        return radius * radius * Math.PI;
    }
}
