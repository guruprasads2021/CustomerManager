package com.demo.app.dao;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.demo.app.bean.Customer;
import com.demo.app.rowMapper.CustomerBeanRowMapper;


@Transactional
@Repository("CustomerDao")
public class CustomerDao {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	private static Logger logger = LoggerFactory.getLogger(CustomerDao.class);

	public Customer getCustomerDetails(String customerNumber) {
		
		Customer customer = null;
		try {
			
			String sql = "SELECT CUSTOMER_ID , NAME , AGE  FROM CUSTOMERS WHERE CUSTOMER_ID=?";
			customer = jdbcTemplate.queryForObject(sql, new CustomerBeanRowMapper(), customerNumber);
		}catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		return customer;
	}

	public List<Map<String, Object>> getAllCustomerDetails() {
		
		List<Map<String, Object>> customersList = null;
		try {
			
			String sql = "SELECT CUSTOMER_ID AS customerId, NAME AS name, AGE AS age FROM CUSTOMERS ORDER BY CUSTOMER_ID";
			customersList = jdbcTemplate.queryForList(sql );
		}catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
		return customersList;
	}

	public boolean deleteCustomerDetails(String customerNumber) {
		boolean isDeleted = false;
		try {
			String sql = "DELETE FROM CUSTOMERS WHERE CUSTOMER_ID = ?";
			
			int deletedRowsCount = jdbcTemplate.update(sql, customerNumber );
			if(deletedRowsCount>0) {
				isDeleted = true;
			}
			
		}catch (Exception e) {
			logger.error(e.getMessage());
		}
		return isDeleted;
	}

	public boolean saveCustomerDetails(Customer customerObj) {
		
		boolean isSaved = false;
		try {
			String sql = "INSERT INTO CUSTOMERS (`NAME`, `AGE`, `CREATED_DATE`)"
					+ " VALUES (?, ?, NOW())";
			int savedRecordsCount = jdbcTemplate.update(sql, customerObj.getName(),customerObj.getAge());
			if(savedRecordsCount>0) {
				isSaved = true;
			}
		}catch (Exception e) {
			logger.error(e.getMessage());
		}
		 return isSaved;
	}

	public boolean updateCustomerDetails(String customerId, Customer customerObj) {
		boolean isUpdated = false;
		try {
			String sql = "UPDATE CUSTOMERS SET NAME=?, AGE=?";
			int savedRecordsCount = jdbcTemplate.update(sql, customerObj.getName(),customerObj.getAge());
			if(savedRecordsCount>0) {
				isUpdated = true;
			}
		}catch (Exception e) {
			logger.error(e.getMessage());
		}
		
		return isUpdated;
	}

}
