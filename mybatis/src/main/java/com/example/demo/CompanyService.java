package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CompanyService {
	@Autowired
	private CompanyMapper companyMapper;
	
	@Autowired
	private EmployeeMapper employeeMapper;
	
	public List<Company> getAll(){
		List<Company> companyList = companyMapper.getAll();
		if(companyList != null&& companyList.size()>0) {//company목록이 한개이상 조회되는경우
			for(Company company : companyList) {
				company.setEmployeeList(employeeMapper.getByCompanyId(company.getId()));
			}
		}
		return companyList;
	}
	@Transactional(rollbackFor=Exception.class) //api내에서 런타임익셉션이 있을때 롤백 가능
	public Company add(Company company) throws Exception {
		companyMapper.insert(company);
		// add company into legacy system 레거시 시스템에 컴퍼니를 추가하다 예외가 발견했을때 db테이블의 컴패니도 롤백시키고싶다면
		if(true) {
			throw new RuntimeException("Legacy Exception");
		}
		return company;
	}
	
}
