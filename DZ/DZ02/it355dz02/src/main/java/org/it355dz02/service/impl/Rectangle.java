package org.it355dz02.service.impl;

import org.it355dz02.service.Shape;

public class Rectangle implements Shape {

    @Override
    public double volume(double side) {
        return 4 * side;
    }

    @Override
    public double area(double side) {
        return side * side;
    }
}
