package com.teo.services;

import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.inject.Inject;

@Decorator
public class MethodPrecallDecorator implements IMethodPrecall {
    @Inject
    @Delegate
    private IMethodPrecall methodPrecall;

    @Override
    public void preCall() {
        System.out.println("in decorator pre call!");
        methodPrecall.preCall();
    }
}
