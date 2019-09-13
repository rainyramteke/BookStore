package com.cg.bookstore.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cg.bookstore.beans.Admin;
import com.cg.bookstore.beans.Customer;

public interface CustomerRepository extends JpaRepository<Customer, String> {
	
	@Query("from Customer where EMAILID=:email")
	public Admin getAdminByEmail(@Param("email") String email);

}
