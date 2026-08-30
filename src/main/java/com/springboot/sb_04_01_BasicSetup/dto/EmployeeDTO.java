package com.springboot.sb_04_01_BasicSetup.dto;

import java.time.LocalDate;

public class EmployeeDTO {
    private Long id;

    private String name;

    private String email;

    private Integer age;

    /*
//    @Pattern(regexp = "^(ADMIN|USER)$", message = "Enter a valid role")
    @EmployeeRoleValidation
    @NotBlank(message = "Role of Employee can't be blank")
    private String role; // ADMIN or USER only

    @Positive(message = "Salary can't be negative")
    @Digits(integer = 5, fraction = 2, message = "Salary Format - xxxxx.xx")
    private Double salary;

    */

    private LocalDate doj;

    private Boolean isActive;

    // Used by model mapper - Creates an empty object internally and then coverts fields later
    // Java creates a default noArgs Constructor only when there is no constructor written manually
    // but in this scenario we wrote an AllArgs constructor below, so we need to write no args as well, manually
    public EmployeeDTO() {}

    public EmployeeDTO(Long id, String name, String email, Integer age, LocalDate doj, Boolean isActive) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.age = age;
        this.doj = doj;
        this.isActive = isActive;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public LocalDate getDoj() {
        return doj;
    }

    public void setDoj(LocalDate doj) {
        this.doj = doj;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setISActive(Boolean active) {
        isActive = active;
    }

    @Override
    public String toString() {
        return "EmployeeDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                ", doj=" + doj +
                ", isActive=" + isActive +
                '}';
    }
}
