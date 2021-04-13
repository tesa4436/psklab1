package com.teo.services;

import javax.enterprise.context.ApplicationScoped;
import java.io.Serializable;
import java.util.Random;

@ApplicationScoped
public class IdService implements Serializable {

    private Long id = 1L;

    public Long getId() {
        return id++;
    }
}