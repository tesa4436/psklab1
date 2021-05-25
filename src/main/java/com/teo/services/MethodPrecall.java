package com.teo.services;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class MethodPrecall implements IMethodPrecall {

    public void preCall() {
        System.out.println("precall in regular class");
    }
}
