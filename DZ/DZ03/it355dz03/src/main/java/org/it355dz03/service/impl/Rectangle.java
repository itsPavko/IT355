package org.it355dz03.service.impl;

import org.it355dz03.service.Shape;

public class Rectangle implements Shape {

    @Override
    public double volume(double side) {
        if (side < 0) throw new IllegalArgumentException();
        return 4 * side;
    }

    @Override
    public double area(double side) {
        if (side < 0) throw new IllegalArgumentException();
        return side * side;

    }
}
