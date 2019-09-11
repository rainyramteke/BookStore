package com.cg.bookstore.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.bookstore.beans.Books;

@Repository
public interface BooksRepository extends JpaRepository<Books, Integer>{

}
