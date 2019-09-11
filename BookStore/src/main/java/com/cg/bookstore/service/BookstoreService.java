package com.cg.bookstore.service;

import java.util.List;

import com.cg.bookstore.beans.Books;
import com.cg.bookstore.beans.Category;
import com.cg.bookstore.exception.BookStoreException;

public interface BookstoreService {

	List<Category> viewCategory() throws BookStoreException;

	Category addCategory(Category category) throws BookStoreException;

	List<Category> updateCategory(int id, Category category) throws BookStoreException;

	List<Category> deleteCategory(int id) throws BookStoreException;

	Books addBooks(Books books) throws BookStoreException;

}
