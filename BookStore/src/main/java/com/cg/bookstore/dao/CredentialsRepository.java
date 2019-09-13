package com.cg.bookstore.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cg.bookstore.beans.Credential;


public interface CredentialsRepository extends JpaRepository<Credential, String>{
	
	@Query("select password from Credential where ID=:id")
	public String getPasswordById(@Param("id") String id);

}
