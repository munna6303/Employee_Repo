package com.excepton;

public class EmployeeRecordNotFound extends RuntimeException
{

	private static final long serialVersionUID = 1L;
	int record;
	
	public EmployeeRecordNotFound(String record) 
	{
		super("No Record Found for Employee : "+record);
	}
	
}
