package com.luv2code.springboot.cruddemo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.luv2code.springboot.cruddemo.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    // that's it ... no need to write any code LOL!

    //add a method to sort by last name
    
    //Spring data JPA will parse the method name and looks for a specific format and pattern 
    //creates appropiate query ... behind the scenes
    //here findAllBy is the part of the pattern
    // then spring jpa will parse this remaining by orderby class for the given the query
    //Query(OrderByLastNameAs)-- from Employee order by lastName asc;
    public List<Employee> findAllByOrderByLastNameAsc(); // query methods for JPA 

}
