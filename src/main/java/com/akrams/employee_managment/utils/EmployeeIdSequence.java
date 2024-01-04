package com.akrams.employee_managment.utils;

import com.akrams.employee_managment.repository.EmployeeRepository;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;

public class EmployeeIdSequence implements IdentifierGenerator {

    private static final String PREFIX = "P";
    private static final int INITIAL_VALUE = 10000000;

    @Autowired
    EmployeeRepository employeeRepository;

    private int sequenceValue = INITIAL_VALUE;
    @Override
    public Serializable generate(SharedSessionContractImplementor sharedSessionContractImplementor, Object o) {
        return getNextSequenceValue();
    }

    private synchronized String getNextSequenceValue() {
        return String.format("%s%08d", PREFIX, sequenceValue++);
    }

//    public int getNextIntValueFromEmpTable(){
//        String str = employeeRepository.findOneIdOrderByIdDesc();
//        System.out.println(str);
//
//        int INITIAL_VALUE = Integer.parseInt(str.substring(1,str.length()));
//        return INITIAL_VALUE;
//    }
}
