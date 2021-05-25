package com.teo.services;

import javax.enterprise.inject.Specializes;
import javax.inject.Inject;
import java.util.Random;

@Specializes
public class NumericNameService extends NameService {

    @Inject
    private NameLengthSource nameLengthSource;

    @Override
    public String generateRandomName() {
        int leftLimit = 48; // '0'
        int rightLimit = 57;
        int targetStringLength = nameLengthSource.getLength();
        Random random = new Random();

        return random.ints(leftLimit, rightLimit + 1)
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }
}
