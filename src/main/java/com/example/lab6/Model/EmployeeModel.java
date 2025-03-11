/*
package com.example.lab6.Model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@AllArgsConstructor
public class EmployeeModel {

    @NotEmpty
    @Size(min = 2, max =11)
    private String id;

    @NotEmpty
    @Size(min = 4, max =15)
    //Must contain only characters (no numbers).
    private String name;

    @Email
    private String email;
    //Must start with "05"
    @Max(10)
    private int phoneNumber;

    @NotEmpty
   // @Pattern(regexp ="0-9")
    private int age;

    @NotEmpty
    private String position;

    @NotEmpty
    boolean onLeave=false;
    @DateTimeFormat(pattern ="yyyy-mm-dd")
    Date hireDate;


}
*/
package com.example.lab6.Model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeModel {

    @NotEmpty
    @Size(min = 2, max = 11)
    private String id;

    @NotEmpty
    @Size(min = 4, max = 15)
    @Pattern(regexp = "^[a-zA-Z- ]+$", message = "just letters")
    private String name;

    @Email
    private String email;

    @Pattern(regexp = "^05\\d{8}$", message = "Phone number must start with '05' and consist of exactly 10 digits")
    private String phoneNumber;

    @NotNull
    @Min(26)
    private Integer age;

    @NotEmpty
    @Pattern(regexp = "^(supervisor|coordinator)$", message = "Position must be either 'supervisor' or 'coordinator'")
    private String position;

    private boolean onLeave = false;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date hireDate;

    @NotNull
    @Min(1)
    private Integer annualLeave;
}
