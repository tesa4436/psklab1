package com.teo.services;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Alternative;

@RequestScoped
@Alternative
public class FirstNameLengthSource implements NameLengthSource {
    public int getLength() {
        return 10;
    }
}
