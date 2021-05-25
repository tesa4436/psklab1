package com.teo.services;

import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.inject.Inject;

@Decorator
public class PhotosControllerDecorator implements IPreMethodCall {
    @Inject
    @Delegate
    private IPreMethodCall preMethodCall;

    @Override
    public void preCall() {
        System.out.println("in decorator pre call!");
        preMethodCall.preCall();
    }
}
