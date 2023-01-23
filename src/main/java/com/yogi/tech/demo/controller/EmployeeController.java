package com.yogi.tech.demo.controller;

import com.yogi.tech.demo.Repository.EmployeeRepository;
import com.yogi.tech.demo.custom.exception.BusinessException;
import com.yogi.tech.demo.custom.exception.ControllerException;
import com.yogi.tech.demo.entity.Employee;
import com.yogi.tech.demo.service.serviceImpl.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/yogi/tech")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;
    @Autowired
    private EmployeeRepository employeeRepository;

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody Employee employee) {
            Employee save = employeeService.save(employee);
            return new ResponseEntity<Employee>(save, HttpStatus.OK);
    }

    @GetMapping("all_emp")
    public ResponseEntity<List<Employee>> getAllEmployee() {
        List<Employee> allEmp = employeeService.getAllEmp();
        return new ResponseEntity<List<Employee>>(allEmp, HttpStatus.OK);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
            Employee empById = employeeService.getEmpById(id);
            return new ResponseEntity<Employee>(empById, HttpStatus.OK);
    }

    @DeleteMapping("delete/{id}")
    public void delete(@PathVariable Long id) {
        employeeService.deleteEmpById(id);
    }

    @PutMapping("update/{id}")
    public ResponseEntity<Employee> update(@RequestBody Employee employee, @PathVariable Long id) {
        Employee employee1 = employeeService.updateEmpById(employee, id);
        return new ResponseEntity<Employee>(employee1, HttpStatus.OK);
    }


}
