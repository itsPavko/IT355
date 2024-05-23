package org.it355dz03;

import org.it355dz03.aspect.ExceptionAspect;
import org.it355dz03.service.Shape;
import org.it355dz03.service.impl.Circle;
import org.it355dz03.service.impl.EquilateralTriangle;
import org.it355dz03.service.impl.Rectangle;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan("org.it355dz03")
@EnableAspectJAutoProxy
public class AppConfiguration {

    @Bean("circle")
    public Shape getCircle() {
        return new Circle();
    }

    @Bean("rectangle")
    public Shape getRectangle() {
        return new Rectangle();
    }

    @Bean("equilateralTriangle")
    public Shape getEquilateralTriangle() {
        return new EquilateralTriangle();
    }

    @Bean("exceptionAspect")
    public ExceptionAspect getExceptionAspect() {
        return new ExceptionAspect();
    }
}
