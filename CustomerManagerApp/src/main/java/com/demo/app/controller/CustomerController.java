package com.demo.app.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.demo.app.bean.Customer;
import com.demo.app.customerService.CustomerService;
import com.google.gson.Gson;

@RestController
public class CustomerController {

	@Autowired
	CustomerService customerService;
	
	@GetMapping(path = "/")
	public String home() {
		System.out.println(" insidecustomer controller");
		return "home";
	}
	
	@GetMapping(path = "/customer", produces = "application/json")
	public String getAllCustomer() {
			
			Map<String, Object> resultMap = customerService.getAllCustomerDetails();
			return new Gson().toJson(resultMap);
		}
	
	
	@PostMapping(path = "/customer", consumes = "application/json", produces = "application/json")
	public String saveCustomerDetails(@RequestBody Customer customerObj) {
		
		Map<String, Object> resultMap = customerService.saveCustomerDetails(customerObj);
		
		return new Gson().toJson(resultMap);
	}
	
	@PutMapping(path = "/customer/{customerId}", consumes = "application/json", produces = "application/json")
	public String updateCustomerDetails(@RequestBody Customer customerObj, @PathVariable String customerId) {
		
		Map<String, Object> resultMap = customerService.updateCustomerDetails(customerId,customerObj);
		
		return new Gson().toJson(resultMap);
	}
	
	@GetMapping(path = "/customer/{customerNumber}", produces = "application/json")
	public String getCustomer(@PathVariable String customerNumber) {
		
		Map<String, Object> resultMap = customerService.getCustomerDetails(customerNumber);
		return new Gson().toJson(resultMap);
	}
	
	

	@DeleteMapping(path = "/customer/{customerNumber}", produces = "application/json")
	public String deleteCustomerDetails(@PathVariable String customerNumber) {
		
		Map<String, Object> resultMap = customerService.deleteCustomerDetails(customerNumber);
		return new Gson().toJson(resultMap);
	}
	
}
