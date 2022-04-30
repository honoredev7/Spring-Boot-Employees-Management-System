package com.honore.dev.Employe.Controller;

import com.honore.dev.Employe.Entity.Employee;
import com.honore.dev.Employe.Service.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class EmployeeController {

    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        super();
        this.employeeService = employeeService;
    }

    @GetMapping("/employees")
    public String listStudents(Model model) {
        model.addAttribute("employees", employeeService.getAllEmployees());
        return "employees";
    }

    @GetMapping("/employees/new")
    public String createEmployeeForm(Model model) {
        Employee employee = new Employee();
        model.addAttribute("employee", employee);
        return "create_employee";
    }

    @PostMapping("/saveEmployee")
    public String saveEmployeeForm(@ModelAttribute("employee") Employee employee) {
        this.employeeService.saveEmployee(employee);
        return "redirect:/employees";
    }

    @GetMapping("/employee/edit/{id}")
    public String editEmployee(@PathVariable Long id, Model model) {
        model.addAttribute("employee", this.employeeService.getEmployeeById(id));
        return "edit_employee";
    }

    @PostMapping("/employee/{id}")
    public String updateEmployee(@PathVariable Long id, @ModelAttribute("employee") Employee employee, Model model) {
        Employee existingEmployee = this.employeeService.getEmployeeById(id);

        existingEmployee.setFirstName(employee.getFirstName());
        existingEmployee.setLastName(employee.getLastName());
        existingEmployee.setEmail(employee.getEmail());
        this.employeeService.updateEmployee(existingEmployee);

        return "redirect:/employees";
    }

    @GetMapping("/employee/delete/{id}")
    public String deleteEmployee(@PathVariable Long id) {
        this.employeeService.deleteEmployee(id);
        return "redirect:/employees";
    }
}
