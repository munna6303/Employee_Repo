package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dao.EmployeeDAO;
import com.model.EmployeeVO;

@RestController("/")
public class EmployeeController 
{
	@Autowired
	EmployeeDAO empDAO;  
	
	@RequestMapping(value="/listEmps")
	public List<EmployeeVO> listEmployee()
	{
		List<EmployeeVO> employeeVOs = null;
		employeeVOs = empDAO.listEmployees();
		return employeeVOs;
	}
	
	@RequestMapping(value="/getEmp/{id}")
	public EmployeeVO getEmployee(@PathVariable("id") String id)
	{
		EmployeeVO vo = null;
		vo = empDAO.getEmployee(id);
		return vo;
	}
}
