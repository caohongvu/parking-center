package com.spm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.spm.dto.EmployeeDto;
import com.spm.dto.ResultObject;
import com.spm.service.EmployeeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * Created by Vincent on 02/10/2018
 */
@RestController
@RequestMapping("/employee")
@Api(value = "Employees Endpoint")
public class EmployeeEndpoint {

	@Autowired
	private EmployeeService employeeService;

//	@RequestMapping(value = "/", method = { RequestMethod.POST })
//	@ApiOperation("Add new employee")
//	public void addMember(@RequestBody EmployeeDto employeeDto) {
//		employeeService.save(employeeDto);
//	}
//
//	@RequestMapping(value = "/", method = { RequestMethod.PUT })
//	@ApiOperation("Update existing card")
//	public void updateExistingCard(@RequestBody EmployeeDto employeeDto) {
//		employeeService.save(employeeDto);
//	}

//	@RequestMapping(value = "", method = { RequestMethod.GET })
//	@ApiOperation("get all employees for search")
//	public @ResponseBody ResultObject<List<EmployeeDto>> getAll(
//			@RequestParam(name="page", required  =  false, defaultValue="0") int page,
//			@RequestParam(name="name", required = false) String name,
//			@RequestParam(name="userName", required = false) String userName,
//			@RequestParam(name="pass", required = false) String pass,
//			@RequestParam(name="position", required = false) String position,
//			@RequestParam(name="sex", required = false) String sex) {
//		
//		Pageable paging = PageRequest.of(page, 100);
//		EmployeeSearchForm employeeSearchForm = new EmployeeSearchForm();
//		if(name != null && !name.isEmpty()) {
//			employeeSearchForm.setName(name);
//		}
//		if(position != null && !position.isEmpty()) {
//			employeeSearchForm.setPosition(position);
//		}
//		return employeeService.findAll(paging, employeeSearchForm);
//	}
	
	@RequestMapping(value="/get-by-id", method = {RequestMethod.GET})
	@ApiOperation("Get employee by id")
	public @ResponseBody ResultObject<List<EmployeeDto>> getCardById(@RequestParam(name="id") Long id) {
		return employeeService.getById(id);
	}
	
	//THANH TUAN
	
	@RequestMapping(value = "/addEmployee", method = {RequestMethod.POST})
	@ApiOperation("Add new EmployeeDto")
	public @ResponseBody ResultObject<List<EmployeeDto>> addEmployee(@RequestBody EmployeeDto employeeDto) {
		return employeeService.save(employeeDto);
	}
	
	@RequestMapping(value = "/addEmployee", method = {RequestMethod.PUT})
	@ApiOperation("Update existing EmployeeDto")
	public @ResponseBody ResultObject<List<EmployeeDto>> updateExistingEmployee(@RequestBody EmployeeDto employeeDto) {
		return employeeService.save(employeeDto);
	}
	
	@RequestMapping(value = "", method = {RequestMethod.GET})
	@ApiOperation("Get all EmployeeDtos for list")
	public @ResponseBody ResultObject<List<EmployeeDto>> getAll() {
		return employeeService.findAll();
	}
	
	@RequestMapping(value = "getEmployeeById", method = {RequestMethod.GET})
	@ApiOperation("Get EmployeeDto")
	public @ResponseBody ResultObject<List<EmployeeDto>> getEmployeeById(@RequestParam(name="id") Long id) {
		return employeeService.findById(id);
	}
	
	@RequestMapping(path="/delete/{id}", method = {RequestMethod.DELETE})
	@ApiOperation("This method support us can delete the specific EmployeeDto by id")
	public void delete(@PathVariable("id") Long id) {
		employeeService.delete(id);
	}

}