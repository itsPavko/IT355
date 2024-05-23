package org.it355dz02.service.impl;

import org.it355dz02.service.Shape;

public class EquilateralTriangle implements Shape {

    @Override
    public double volume(double side) {
        return 3 * side;
    }

    @Override
    public double area(double side) {
        return side * side * (Math.sqrt(3) / 4);
    }
}
