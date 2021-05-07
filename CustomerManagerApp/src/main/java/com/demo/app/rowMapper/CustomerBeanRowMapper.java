package com.demo.app.rowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.demo.app.bean.Customer;

public class CustomerBeanRowMapper implements RowMapper<Customer> {

	@Override
	public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		Customer customerBean = new Customer();

		customerBean.setCustomerId( rs.getString("CUSTOMER_ID") );
		customerBean.setName( rs.getString("NAME") );
		customerBean.setAge( rs.getString("AGE") );

		return customerBean;
	
	}

}
