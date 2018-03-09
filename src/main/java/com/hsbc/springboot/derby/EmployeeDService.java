package com.hsbc.springboot.derby;

import com.hsbc.springboot.mysql.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sumit on 07-03-2018.
 */
@Service
public class EmployeeDService {

    @Autowired
    private EmployeeDRepository employeeDRepository;

    public List<EmployeeD> getAllEmployees() {
        return employeeDRepository.findAll();
    }

}
