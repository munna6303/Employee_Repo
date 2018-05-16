package com.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.excepton.EmployeeRecordNotFound;
import com.model.EmployeeVO;

@Repository
public class EmployeeDAO {
	
	@Autowired
	JdbcTemplate jdbc;

	public List<EmployeeVO> listEmployees() 
	{
		String sql = null;
		RowMapper<EmployeeVO> rowMapper = null;
		try 
		{
			sql = "SELECT id, name FROM employee";
			rowMapper = new BeanPropertyRowMapper<EmployeeVO>(EmployeeVO.class);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			throw new RuntimeException("No Employees found");
		}
		
		return jdbc.query(sql, rowMapper);

	}

	public EmployeeVO getEmployee(String id)
	{
		String getSql = null;
		EmployeeVO vo = null;
		
		RowMapper<EmployeeVO> rowMapper = null;
		try 
		{
			getSql ="select id,name from employee where id =?";
			int no = Integer.parseInt(id);
			rowMapper = new BeanPropertyRowMapper<EmployeeVO>(EmployeeVO.class);
			vo = (EmployeeVO)jdbc.queryForObject(getSql, new Object[] {no},rowMapper);
		}
		catch (NumberFormatException e) 
		{
			throw new NumberFormatException("Invalid Employee Number : "+id);
		}
		catch (EmptyResultDataAccessException e) 
		{
			throw new EmployeeRecordNotFound(id);
		}
		catch (CannotGetJdbcConnectionException e) 
		{
			throw new RuntimeException("DB connection error");
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			throw new RuntimeException();
		}
		return vo;
	}

}
