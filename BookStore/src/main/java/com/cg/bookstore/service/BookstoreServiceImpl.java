package com.cg.bookstore.service;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.bookstore.beans.Books;
import com.cg.bookstore.beans.Category;
import com.cg.bookstore.beans.Review;
import com.cg.bookstore.dao.BooksRepository;
import com.cg.bookstore.dao.CategoryRepository;
import com.cg.bookstore.dao.ReviewRepository;
import com.cg.bookstore.exception.BookStoreException;

@Service
public class BookstoreServiceImpl implements BookstoreService {

	@Autowired
	private CategoryRepository categoryRepo;
	@Autowired
	private BooksRepository bookRepo;
	@Autowired
	private ReviewRepository reviewRepo;

	@Override
	public List<Category> viewCategory() throws BookStoreException {

		try {
			return categoryRepo.findAll();
		} catch (Exception e) {
			throw new BookStoreException(e.getMessage());
		}
	}

	@Override
	public Category addCategory(Category category) throws BookStoreException {

		try {
			return categoryRepo.save(category);
		} catch (Exception e) {
			throw new BookStoreException(e.getMessage());
		}
	}

	@Override
	public List<Category> deleteCategory(int id) throws BookStoreException {

		categoryRepo.deleteById(id);
		return viewCategory();
	}

	@Override
	public List<Category> updateCategory(int id, Category category) throws BookStoreException {

		if (categoryRepo.existsById(id)) {
			categoryRepo.save(category);
		}
		return viewCategory();
	}

	@Override
	public List<Books> addBooks(Books books) throws BookStoreException {

		try {

			bookRepo.save(books);
			return showAllBooks();
		} catch (Exception e) {
			throw new BookStoreException(e.getMessage());
		}
	}

	@Override
	public List<Books> showAllBooks() throws BookStoreException {

		try {
			return bookRepo.findAll();
		} catch (Exception e) {

			throw new BookStoreException(e.getMessage());
		}
	}

	@Override
	public List<Books> updateBook(int id, Books book) throws BookStoreException {

		if (bookRepo.existsById(book.getId())) {
			addBooks(book);
			return showAllBooks();
		} else {
			throw new BookStoreException("Invalid bookid,Cannot be updated");
		}
	}

	@Override
	public List<Books> deleteBook(int id) throws BookStoreException {

		if (bookRepo.existsById(id)) {
			bookRepo.deleteById(id);
			return showAllBooks();
		} else {
			throw new BookStoreException("Cannot delete. Book with id " + id + " does not exist");
		}
	}

	@Override
	public List<Review> viewReviews() throws BookStoreException {

		try {
			return reviewRepo.findAll();
		} catch (Exception e) {
			throw new BookStoreException(e.getMessage());
		}
	}

	@Override
	public List<Review> addReviews(Review reviews) throws BookStoreException {

		try {
			reviewRepo.save(reviews);

			long millis = System.currentTimeMillis();
			Date date = new Date(millis);
			reviews.setReviewDate(date);
			System.out.println(reviews);

			reviewRepo.save(reviews);

			return viewReviews();
		} catch (Exception e) {
			throw new BookStoreException(e.getMessage());
		}
	}

	@Override
	public Review getByid(int id) throws BookStoreException {

		return reviewRepo.findById(id).get();
	}

	@Override
	public List<Review> updateReview(int id, Review review) throws BookStoreException {

		if (reviewRepo.existsById(id)) {
			reviewRepo.save(review);
		}

		return viewReviews();
	}

	@Override
	public List<Review> deleteReview(int id) throws BookStoreException {

		reviewRepo.deleteById(id);
		return viewReviews();
	}

}
