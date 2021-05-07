package com.demo.app.customerService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.app.bean.Customer;
import com.demo.app.dao.CustomerDao;

@Service
public class CustomerService {

	@Autowired
	CustomerDao customerDao;
	
	public Map<String, Object> getCustomerDetails(String customerNumber) {
		
		Map<String, Object> map= new HashMap<String,Object>();
		Customer customer = customerDao.getCustomerDetails(customerNumber);
		if(null!= customer) {
			map.put("data", customer);
			map.put("message", "data found");
		}else {
			map.put("data", customer);
			map.put("message", "Customer Id not found");
		}
		
		return map;
	}

	public Map<String, Object> getAllCustomerDetails() {
		Map<String, Object> map= new HashMap<String,Object>();
		List<Map<String, Object>> customersList = customerDao.getAllCustomerDetails();
		if(null!= customersList && customersList.size()>0) {
			map.put("data", customersList);
			map.put("message", "data found");
		}else {
			map.put("message", "Customers data not found");
		}
		
		return map;
	}

	public Map<String, Object> deleteCustomerDetails(String customerNumber) {
		
		boolean isDeleted = customerDao.deleteCustomerDetails(customerNumber);
		Map<String, Object> customersDetailsMap = getAllCustomerDetails();
		if(isDeleted) {
			customersDetailsMap.put("message", "Customer details Deleted Successfully");
		}else {
			customersDetailsMap.put("message", "Customer details Failed to Delete");
		}
		return customersDetailsMap;
	}

	public Map<String, Object> saveCustomerDetails(Customer customerObj) {
		Map<String, Object> map= new HashMap<String,Object>();
		boolean isSaved = customerDao.saveCustomerDetails(customerObj);
		if(isSaved) {
			map.put("message", "Customer Details Saved Successfully");
		}else {
			map.put("message", "Customer Details Failed to Save");
		}
		
		return map;
	}

	public Map<String, Object> updateCustomerDetails(String customerId, Customer customerObj) {
		Map<String, Object> map= new HashMap<String,Object>();
		boolean isSaved = customerDao.updateCustomerDetails(customerId, customerObj);
		if(isSaved) {
			map.put("message", "Customer Details Updated Successfully");
		}else {
			map.put("message", "Customer Details updated to Save");
		}
		
		return map;
	}

}
