package com.akrams.employee_managment.utils;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;

public class EmployeeIdSequence implements IdentifierGenerator {

    private static final String PREFIX = "P";
    private static final int INITIAL_VALUE = 10000000;

    private int sequenceValue = INITIAL_VALUE;
    @Override
    public Serializable generate(SharedSessionContractImplementor sharedSessionContractImplementor, Object o) {
        return PREFIX + getNextSequenceValue();
    }

    private synchronized String getNextSequenceValue() {
        return String.format("%s%08d", PREFIX, sequenceValue++);
    }
}
