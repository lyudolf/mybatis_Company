package com.example.demo;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface CompanyMapper {
	
	@Insert("INSERT INTO company(company_name, company_address) VALUES(#{company.name}, #{company.address})")
	@Options(useGeneratedKeys=true, keyProperty="id")
	int insert(@Param("company") Company company);

	@Select("SELECT * FROM company")
	@Results(id="CompanyMap", value= {// id를 지정해서 값을 재활용 할 수 있다.
		@Result(property="name", column="company_name"), //mapping
		@Result(property="address", column="company_address"),
		@Result(property="employeeList", column="id", many=@Many(select="com.example.demo.EmployeeMapper.getByCompanyId"))//id라는 컬럼을 파라미터로해서 api를 호출하고 전달된 결과를 employeelist에 매핑해라
	})
	List<Company> getAll();
	
	@Select("SELECT * FROM company WHERE id=#{id}")
	@ResultMap("CompanyMap")
	Company getById(@Param("id") int id);
	
}
