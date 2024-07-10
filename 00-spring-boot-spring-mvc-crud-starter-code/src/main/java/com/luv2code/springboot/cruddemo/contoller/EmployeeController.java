package com.luv2code.springboot.cruddemo.contoller;


import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.luv2code.springboot.cruddemo.entity.Employee;
import com.luv2code.springboot.cruddemo.service.EmployeeService;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    private EmployeeService employeeService;

    public EmployeeController(EmployeeService theEmployeeService){
        employeeService= theEmployeeService;
    }


    //Add mapping for "/list"

    @GetMapping("/list")
    public String listEmployees(Model theModel){
        

        //get employee from the db
        List<Employee> theEmployee = employeeService.findAll();


        //add to the  spring model

        theModel.addAttribute("employees",theEmployee);

        return "employees/list-employees";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model theModel){

        //create model attribute to to bind the form data
        Employee theEmployee =new Employee();

        theModel.addAttribute("employee",theEmployee);

        return "employees/employee-form";


    }

    @PostMapping("/save")
    public String saveEmployee(@ModelAttribute("employee") Employee theEmployee){

        //save the employee
        employeeService.save(theEmployee);

        //use a redirect  to prevent duplicate submissions
        return "redirect:/employees/list";


    }

    @GetMapping("/delete")
    public String delete(@RequestParam("employeeId") int theId){

        //delete the employee
        employeeService.deleteById(theId);

        //redirect to /employees/list
        return "redirect:/employees/list";

    }

    @GetMapping("/showFormForUpdate")

    public String showFormForUpdate(@RequestParam("employeeId") int theId, Model theModel){

        //get the employee form the service
        Employee theEmployee = employeeService.findById(theId);

        //set employee in the model to prepopulate the form
        theModel.addAttribute("employee",theEmployee);

        //send over to our form

        return "/employees/employee-form";
    }
}
