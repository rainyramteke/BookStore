package com.cg.bookstore.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;

import com.cg.bookstore.beans.OrderedBook;

@Repository
public interface OrderedBookRepository extends JpaRepository<OrderedBook, Integer>{
	
	@Query("from OrderedBook where CUSTOMER_ID=:cid")
    public List<OrderedBook> getCartListById(@Param("cid") String cid);

}
