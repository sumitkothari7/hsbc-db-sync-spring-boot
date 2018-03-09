package com.hsbc.springboot.derby;

import com.hsbc.springboot.mysql.Employee;
import com.hsbc.springboot.mysql.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

/**
 * Created by Sumit on 07-03-2018.
 */
@RestController
@RequestMapping("/api")
public class EmployeeDController {


    @Autowired
    private EmployeeService employeeService;

    @RequestMapping(value = "/employeesD", method = RequestMethod.GET)
    public ResponseEntity<List<Employee>> getAllEmployees() {

        return new ResponseEntity<List<Employee>>( employeeService.getAllEmployees(), HttpStatus.OK);

    }


}
