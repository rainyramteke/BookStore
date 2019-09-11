package com.cg.bookstore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.bookstore.beans.Books;
import com.cg.bookstore.beans.Category;
import com.cg.bookstore.dao.BooksRepository;
import com.cg.bookstore.dao.CategoryRepository;
import com.cg.bookstore.exception.BookStoreException;

@Service
public class BookstoreServiceImpl implements BookstoreService {

	@Autowired
	private CategoryRepository categoryRepo;
	@Autowired
	private BooksRepository bookRepo;

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
	public Books addBooks(Books books) throws BookStoreException {

		try {
			return bookRepo.save(books);
		} catch (Exception e) {
			throw new BookStoreException(e.getMessage());
		}
	}

}
