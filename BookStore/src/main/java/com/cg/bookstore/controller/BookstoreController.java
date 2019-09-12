package com.cg.bookstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cg.bookstore.beans.Books;
import com.cg.bookstore.beans.Category;
import com.cg.bookstore.beans.Review;
import com.cg.bookstore.exception.BookStoreException;
import com.cg.bookstore.service.BookstoreService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class BookstoreController {

	@Autowired
	BookstoreService bookstoreService;

	@GetMapping("/categories/")
	public List<Category> viewCategory() throws BookStoreException {
		return bookstoreService.viewCategory();
	}

	@PostMapping("/addCategories/")
	public Category addCategory(@RequestBody Category category) throws BookStoreException {
		return bookstoreService.addCategory(category);
	}
	
	@PutMapping("/categories/updateCategory/{id}")
	List<Category> updateCategory(@PathVariable int id, @RequestBody Category category) throws BookStoreException {
		return bookstoreService.updateCategory(id, category);
	}
	
	@DeleteMapping("/categories/{id}")
	public List<Category> deleteCategory(@PathVariable int id) throws BookStoreException{
		return bookstoreService.deleteCategory(id);
	}
	
	@PostMapping("/addBooks")
	List<Books> addBooks(@RequestBody Books books) throws BookStoreException {
		return bookstoreService.addBooks(books);
	}
	
	@GetMapping("/books")
	List<Books> showAllBooks() throws BookStoreException {
		return bookstoreService.showAllBooks();
		
	}
	
	List<Books> updateBook(int id, Books book) throws BookStoreException {
		return bookstoreService.updateBook(id, book);
	}
	
	@DeleteMapping("/")
	List<Books> deleteBook(int id) throws BookStoreException {
		return bookstoreService.deleteBook(id);
	}
	
	@GetMapping("/reviews")
	List<Review> viewReviews() throws BookStoreException {
		return bookstoreService.viewReviews();
	}
	
	@PostMapping("/customer_review/new")
	List<Review> addReviews(@RequestBody Review reviews) throws BookStoreException {
		return bookstoreService.addReviews(reviews);
	}
	
	@GetMapping("/reviews/{id}")
	Review getReviewByid(@PathVariable int id) throws BookStoreException {
		return bookstoreService.getByid(id);
		
	}
	
	@PutMapping("/updateReview/{id}")
	List<Review> updateReview(@PathVariable int id,@RequestBody Review review) throws BookStoreException {
		return bookstoreService.updateReview(id, review);
	}
	
	@DeleteMapping("/reviews/{id}")
	List<Review> deleteReview(@PathVariable int id) throws BookStoreException {
		return bookstoreService.deleteReview(id);
	}

}
