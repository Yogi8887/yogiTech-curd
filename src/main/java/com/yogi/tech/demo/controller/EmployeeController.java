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
        try {
            Employee save = employeeService.save(employee);
            return new ResponseEntity<Employee>(save, HttpStatus.OK);
        } catch (BusinessException e) {
            ControllerException ce = new ControllerException(e.getErrorCode(), e.getErrorMessage());
            return new ResponseEntity<ControllerException>(ce, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            ControllerException ce = new ControllerException("611", "Something went wrong in controller" + e.getMessage());
            return new ResponseEntity<ControllerException>(ce, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("all_emp")
    public ResponseEntity<List<Employee>> getAllEmployee() {
        List<Employee> allEmp = employeeService.getAllEmp();
        return new ResponseEntity<List<Employee>>(allEmp, HttpStatus.OK);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        try {
            Employee empById = employeeService.getEmpById(id);
            return new ResponseEntity<Employee>(empById, HttpStatus.OK);
        } catch (BusinessException e) {
            ControllerException ce = new ControllerException(e.getErrorCode(), e.getErrorMessage());
            return new ResponseEntity<ControllerException>(ce, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            ControllerException ce = new ControllerException("612", "Something went wrong in controller" + e.getMessage());
            return new ResponseEntity<ControllerException>(ce, HttpStatus.BAD_REQUEST);
        }

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
