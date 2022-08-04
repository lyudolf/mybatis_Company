package com.example.demo;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface EmployeeMapper {
	
	@Insert("INSERT INTO employee(company_id, employee_name, employee_address) VALUES(#{employee.companyId}, #{employee.name}, #{employee.address})")
	@Options(useGeneratedKeys=true, keyProperty="id")
	int insert(@Param("employee") Employee employee);

	@Select("SELECT * FROM employee")
	@Results(id="EmployeeMap", value= {// id를 지정해서 값을 재활용 할 수 있다.
		@Result(property="name", column="employee_name"),
		@Result(property="address", column="employee_address")
	})
	List<Employee> getAll();
	
	@Select("SELECT * FROM employee WHERE id=#{id}")
	@ResultMap("EmployeeMap")
	Employee getById(@Param("id") int id);
	
	@Select("SELECT * FROM employee WHERE company_id=#{companyId}")
	@ResultMap("EmployeeMap")
	List<Employee> getByCompanyId(@Param("companyId") int companyId); //companyid로 조회한 회사에 재직하는 employee 조회
	
}

