package com.example.lab6.Controller;

import com.example.lab6.Api.ApiResponse;
import com.example.lab6.Model.EmployeeModel;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/employee")
public class EmployeeController {
    private final List<EmployeeModel> employees = new ArrayList<>();

    @PostMapping("/add")
    public ResponseEntity<?> addEmployee(@RequestBody @Valid EmployeeModel employee, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.badRequest().body(message);
        }
        employees.add(employee);
        return ResponseEntity.ok(new ApiResponse("Employee added successfully"));
    }

    @GetMapping("/get")
    public ResponseEntity<List<EmployeeModel>> getEmployees() {
        return ResponseEntity.ok(employees);
    }

    @PutMapping("/update/{index}")
    public ResponseEntity<?> updateEmployee(@RequestBody @Valid EmployeeModel employee, Errors errors, @PathVariable int index) {
        if (errors.hasErrors() || index < 0 || index >= employees.size()) {
            return ResponseEntity.badRequest().body(new ApiResponse("Employee not updated or not existing"));
        }
        employees.set(index, employee);
        return ResponseEntity.ok(new ApiResponse("Employee updated successfully"));
    }

    @DeleteMapping("/delete/{index}")
    public ResponseEntity<?> deleteEmployee(@PathVariable int index) {
        if (index < 0 || index >= employees.size()) {
            return ResponseEntity.status(404).body(new ApiResponse("index out of bound!"));
        }
        employees.remove(index);
        return ResponseEntity.ok(new ApiResponse("Employee deleted successfully"));
    }

    @GetMapping("/search/{position}")
    public ResponseEntity<ApiResponse> getByPosition(@PathVariable String position) {
        List<EmployeeModel> filteredEmployees = new ArrayList<>();
        for (EmployeeModel employee : employees) {
            if (employee.getPosition().equalsIgnoreCase(position)) {
                filteredEmployees.add(employee);
            }
        }
        if (filteredEmployees.isEmpty()) {
            return ResponseEntity.status(404).body(new ApiResponse("No employees found for the specified position"));
        }
        return null;
    }

    @GetMapping("/getByAge/{minAge}/{maxAge}")
    public ResponseEntity<List<EmployeeModel>> getByAge(@PathVariable int minAge, @PathVariable int maxAge) {
        if (minAge > maxAge || minAge < 0) {
            return ResponseEntity.badRequest().body(null);
        }
        List<EmployeeModel> filteredEmployees = new ArrayList<>();
        for (EmployeeModel employee : employees) {
            if (employee.getAge() >= minAge && employee.getAge() <= maxAge) {
                filteredEmployees.add(employee);
            }
        }
        return ResponseEntity.ok(filteredEmployees);
    }

    @GetMapping("/getAnnualLeave")
    public ResponseEntity<List<EmployeeModel>> getNoAnnualLeave() {
        List<EmployeeModel> noAnnualLeave = new ArrayList<>();
        for (EmployeeModel employee : employees) {
            if (employee.getAnnualLeave() <= 0) {
                noAnnualLeave.add(employee);
            }
        }
        return ResponseEntity.status(200).body(noAnnualLeave);
    }
}