package com.teo;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class TestComponent {

    @PostConstruct
    public void init() {
        System.out.println("Post construct: " + toString());
    }

    @PreDestroy
    public void beforeDeath() {
        System.out.println("Right before death: " + toString());
    }

    public String sayHello() {
        return "This is " + toString() + " saying hello!";
    }
}