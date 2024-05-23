package org.it355dz02;

import org.it355dz02.service.impl.Circle;
import org.it355dz02.service.impl.EquilateralTriangle;
import org.it355dz02.service.impl.Rectangle;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("org.it355dz02")
public class AppConfiguration {

    @Bean("circle")
    public Circle getCircle() {
        return new Circle();
    }

    @Bean("rectangle")
    public Rectangle getRectangle() {
        return new Rectangle();
    }

    @Bean("equilateralTriangle")
    public EquilateralTriangle getEquilateralTriangle() {
        return new EquilateralTriangle();
    }
}
