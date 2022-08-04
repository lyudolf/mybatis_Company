package com.example.demo;

import java.util.List;

import lombok.Data;

@Data
public class Company {
	private int id;
	private String name;
	private String address;
	private List<Employee> employeeList;
}
