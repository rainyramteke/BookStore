package com.cg.bookstore.service;

import java.util.List;

import com.cg.bookstore.beans.Books;
import com.cg.bookstore.beans.Category;
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

}
