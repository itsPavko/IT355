package org.it355dz02;

import org.it355dz02.service.impl.Circle;
import org.it355dz02.service.impl.EquilateralTriangle;
import org.it355dz02.service.impl.Rectangle;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfiguration.class);

        Circle circle = (Circle) applicationContext.getBean("circle");
        Rectangle rectangle = (Rectangle) applicationContext.getBean("rectangle");
        EquilateralTriangle equilateralTriangle = (EquilateralTriangle) applicationContext.getBean("equilateralTriangle");

        double side = 4;
        double radius = 3;

        System.out.println("Povrsina kruga: " + circle.area(radius));
        System.out.println("Obim kruga: " + circle.volume(radius));

        System.out.println("Povrsina kvadrata: " + rectangle.area(side));
        System.out.println("Obim kvadrata: " + rectangle.volume(side));

        System.out.println("Povrsina jednakostranicnog trougla: " + equilateralTriangle.area(side));
        System.out.println("Obim jednakostranicnog trougla: " + equilateralTriangle.volume(side));

    }
}