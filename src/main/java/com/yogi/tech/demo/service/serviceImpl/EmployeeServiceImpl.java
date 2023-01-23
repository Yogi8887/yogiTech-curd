package com.yogi.tech.demo.service.serviceImpl;

import com.yogi.tech.demo.Repository.EmployeeRepository;
import com.yogi.tech.demo.custom.exception.BusinessException;
import com.yogi.tech.demo.custom.exception.EmptyInputException;
import com.yogi.tech.demo.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//@Component
@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    public EmployeeRepository employeeRepository;

    @Override
    public Employee save(Employee employee) {

        if (employee.getName().isEmpty() || employee.getName().length() == 0) {
            throw new EmptyInputException("601", "Please send proper name, It blank");
        }
            return employeeRepository.save(employee);
    }

    @Override
    public Employee getEmpById(Long id) {
            return employeeRepository.findById(id).get();
    }

    @Override
    public List<Employee> getAllEmp() {
        try {
            List<Employee> all = employeeRepository.findAll();
            if (all.isEmpty()) {
                throw new BusinessException("604", "Hey List completely Empty");
            }
            return all;
        } catch (Exception e) {
            throw new BusinessException("605", "Something went wrong in Service layer while saving employee" + e.getMessage());
        }
    }

    @Override
    public void deleteEmpById(Long id) {
        try {
            employeeRepository.deleteById(id);
        } catch (IllegalArgumentException e) {
            throw new BusinessException("608", "Given Employee id  is null, plese send some other id " + e.getMessage());
        }
    }

    @Override
    public Employee updateEmpById(Employee employee, Long id) {
        try {
            Employee employee1 = employeeRepository.findById(id).get();
            employee1.setId(id);
            employee1.setName(employee.getName());
            Employee save = employeeRepository.save(employee1);
            return save;
        } catch (IllegalArgumentException e) {
            throw new BusinessException("609", "Given Employee id  is null, plese send some other id " + e.getMessage());
        } catch (java.util.NoSuchElementException e) {
            throw new BusinessException("6010", "Given Employee id doesn't exist in DB" + e.getMessage());
        }
    }
}
