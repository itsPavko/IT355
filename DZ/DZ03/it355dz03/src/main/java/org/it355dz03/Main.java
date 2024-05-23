package org.it355dz03;

import org.it355dz03.aspect.ExceptionAspect;
import org.it355dz03.service.Shape;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfiguration.class);

        Shape circle = (Shape) applicationContext.getBean("circle");
        Shape rectangle = (Shape) applicationContext.getBean("rectangle");
        Shape equilateralTriangle = (Shape) applicationContext.getBean("equilateralTriangle");
        ExceptionAspect exceptionAspect = (ExceptionAspect) applicationContext.getBean("exceptionAspect");

        double side = 4;
        double radius = -2;

        double volume;
        double area;

        volume = rectangle.volume(side);
        area = rectangle.area(side);
        System.out.println("Povrsina kvadrata: " + area);
        System.out.println("Obim kvadrata: " + volume);

        volume = equilateralTriangle.volume(side);
        area = equilateralTriangle.area(side);
        System.out.println("Povrsina jednakostranicnog trougla: " + area);
        System.out.println("Obim jednakostranicnog trougla: " + volume);

        volume = circle.volume(radius);
        area = circle.area(radius);
        System.out.println("Povrsina kruga: " + area);
        System.out.println("Obim kruga: " + volume);
    }
}