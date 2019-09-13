package com.cg.bookstore.service;

import java.util.List;

import com.cg.bookstore.beans.Admin;
import com.cg.bookstore.beans.Books;
import com.cg.bookstore.beans.Category;
import com.cg.bookstore.beans.Customer;
import com.cg.bookstore.beans.OrderedBook;
import com.cg.bookstore.beans.Review;
import com.cg.bookstore.exception.BookStoreException;

public interface BookstoreService {

	List<Category> viewCategory() throws BookStoreException;

	Category addCategory(Category category) throws BookStoreException;

	List<Category> updateCategory(int id, Category category) throws BookStoreException;

	List<Category> deleteCategory(int id) throws BookStoreException;

	List<Books> addBooks(Books books) throws BookStoreException;

	List<Books> showAllBooks() throws BookStoreException;

	List<Books> updateBook(int id, Books book) throws BookStoreException;

	List<Books> deleteBook(int id) throws BookStoreException;

	List<Review> viewReviews() throws BookStoreException;

	List<Review> addReviews(Review reviews) throws BookStoreException;

	Review getByid(int id) throws BookStoreException;

	List<Review> updateReview(int id, Review review) throws BookStoreException;

	List<Review> deleteReview(int id) throws BookStoreException;
	
	public void addAdmin(Admin admin, String password) throws Exception;
	
	public void addCustomer(Customer customer, String password) throws Exception;
	
	public Admin loginAdmin(String email, String password) throws RuntimeException;
	
	public List<Admin> viewAdmin() throws Exception;
	
	public List<Customer> viewCustomer() throws Exception;
	
	public List<Admin> deleteAdmin(String id) throws Exception;
	
	public List<Admin> deleteCustomer(String id) throws Exception;
	
	public List<Admin> updateAdmin(String id, Admin admin) throws Exception;
	
	public Admin getByid(String id);
	
	public List<Customer> updateCustomer(Customer customer, String id) throws Exception;
	
	public Customer getCustomerById(String id) throws Exception;
	
	public void addtoCart(String custId, OrderedBook orderBook) throws BookStoreException;

}
