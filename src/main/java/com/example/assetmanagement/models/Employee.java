package com.example.assetmanagement.models;

import javax.persistence.Entity;

@Entity
public class Employee extends BaseClass{
    private String name;
    private String designation;


    public void setName(String name) {
        this.name = name;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }


    public String getName() {
        return name;
    }

    public String getDesignation() {
        return designation;
    }

    @Override
    public String toString() {
        return "Employee{" +
                ", name='" + name + '\'' +
                ", designation='" + designation + '\'' +
                '}';
    }
}
