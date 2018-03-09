package com.hsbc.springboot.mysql;

import com.hsbc.springboot.derby.EmployeeD;
import com.hsbc.springboot.derby.EmployeeDRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

/**
 * Created by Sumit on 07-03-2018.
 */
@RestController
@RequestMapping("/api")
public class EmployeeController {


    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private EmployeeDRepository employeeDRepository;

    private EmployeeD getEmployeeD(Employee employee) {
        EmployeeD employeeD = new EmployeeD();
        employeeD.setBirthDate(employee.getBirthDate());
        employeeD.setFirstName(employee.getFirstName());
        employeeD.setLastName(employee.getLastName());
        employeeD.setDepartmentName(employee.getDepartmentName());
        employeeD.setGender(employee.getGender());
        employeeD.setDepartmentCode(employee.getDepartmentCode());
        return employeeD;
    }

    @RequestMapping(value = "/employees", method = RequestMethod.GET)
    public ResponseEntity<List<Employee>> getAllEmployees() {

        return new ResponseEntity<List<Employee>>( employeeService.getAllEmployees(), HttpStatus.OK);

    }

    @RequestMapping(value = "/sync", method = RequestMethod.GET)
    public ResponseEntity<List<EmployeeD>> homePage() {

        List<Employee> employeeList = employeeService.getAllEmployees();

        for (Employee employee : employeeList) {
            employeeDRepository.save(getEmployeeD(employee));
        }

        List<EmployeeD> employeeDList = employeeDRepository.findAll();


        return new ResponseEntity<List<EmployeeD>>( employeeDList, HttpStatus.OK);
    }
}
