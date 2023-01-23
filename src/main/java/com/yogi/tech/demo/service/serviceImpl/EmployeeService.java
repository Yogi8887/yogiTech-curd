package com.yogi.tech.demo.service.serviceImpl;

import com.yogi.tech.demo.entity.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {

    Employee save(Employee employee);
    Employee getEmpById(Long id);

    List<Employee> getAllEmp();
    void deleteEmpById(Long id);
    Employee updateEmpById(Employee employee,Long id);
}
