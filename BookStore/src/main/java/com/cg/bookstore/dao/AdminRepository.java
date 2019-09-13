package com.cg.bookstore.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;

import com.cg.bookstore.beans.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin, String>{
	
	@Query("from Admin where EMAILID=:email")
	public Admin getAdminByEmail(@Param("email") String email);

}
