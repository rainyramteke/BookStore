package com.cg.bookstore.service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;
import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.bookstore.beans.Admin;
import com.cg.bookstore.beans.Books;
import com.cg.bookstore.beans.Category;
import com.cg.bookstore.beans.Credential;
import com.cg.bookstore.beans.Customer;
import com.cg.bookstore.beans.OrderedBook;
import com.cg.bookstore.beans.Review;
import com.cg.bookstore.dao.AdminRepository;
import com.cg.bookstore.dao.BooksRepository;
import com.cg.bookstore.dao.CategoryRepository;
import com.cg.bookstore.dao.CredentialsRepository;
import com.cg.bookstore.dao.CustomerRepository;
import com.cg.bookstore.dao.OrderedBookRepository;
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
	@Autowired
	private AdminRepository adminRepo;
	@Autowired
	private CredentialsRepository credentialRepo;
	@Autowired
	private CustomerRepository customerRepo;
	@Autowired
	private OrderedBookRepository orderedbookRepo;
	
	
	@Override
	public void addtoCart(String custId, OrderedBook orderBook) throws BookStoreException {
		
		try {
			
			Customer customer = getCustomerById(custId);
			
			List<OrderedBook> cartList = customer.getCartItems();
			
			orderBook.setCustomer(customer);
			cartList.add(orderBook);
			customer.setCartItems(cartList);
			
			orderedbookRepo.save(orderBook);
			customerRepo.save(customer);
			
		} catch (Exception e) {
			
			System.out.println(e.getMessage());
		}
		
	}
	

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
			System.out.println(books.getCategory());
			long millis = System.currentTimeMillis();
			Date date = new Date(millis);
			books.setUpdateBookDate(date);
			
			System.out.println(books);
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
			System.out.println(id);
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

	public void addAdmin(Admin admin, String password) throws Exception {
		try {
			Credential cred = new Credential();
			adminRepo.save(admin);
			cred.setEmailId(admin.getEmailId());
			cred.setId(admin.getAdmId());
			cred.setPassword(password);
			credentialRepo.save(cred);
		} catch (Exception ex) {
			throw new Exception("Admin Registration failed");
		}
	}

	public void addCustomer(Customer customer, String password) throws Exception {
		try {
			Credential cred = new Credential();
			customerRepo.save(customer);
			cred.setEmailId(customer.getEmailId());
			cred.setId(customer.getId());
			cred.setPassword(password);
			credentialRepo.save(cred);
		} catch (Exception ex) {
			throw new Exception("Customer Registration failed");
		}
	}

	public Admin loginAdmin(String email, String password) throws RuntimeException {
		try {
			Admin admin = adminRepo.getAdminByEmail(email);
			System.out.println(admin.getEmailId());
			System.out.println(credentialRepo.getPasswordById(admin.getEmailId()));
			System.out.println(password);
			if (credentialRepo.getPasswordById(admin.getAdmId()).equals(password)) {
				return admin;
			} else {
				throw new RuntimeException("Password Incorrect");
			}
		} catch (Error ex) {
			throw new RuntimeErrorException(ex, ex.getMessage());
		}
	}

	public List<Admin> viewAdmin() throws Exception {

		try {
			return adminRepo.findAll();
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	public List<Customer> viewCustomer() throws Exception {

		try {
			return customerRepo.findAll();
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	public List<Admin> deleteAdmin(String id) throws Exception {
		// System.out.println(admin.getEmailId());
		adminRepo.deleteById(id);
		credentialRepo.deleteById(id);
		return viewAdmin();
	}

	public List<Admin> deleteCustomer(String id) throws Exception {
		// System.out.println(admin.getEmailId());
		customerRepo.deleteById(id);
		credentialRepo.deleteById(id);
		return viewAdmin();
	}

	public List<Admin> updateAdmin(String id, Admin admin) throws Exception {

		if (adminRepo.existsById(admin.getAdmId())) {
			addAdmin(admin, credentialRepo.getPasswordById(admin.getAdmId()));
			return viewAdmin();
		} else {
			throw new Exception("Invalid adminid, Cannot be updated");
		}
	}

	public Admin getByid(String id) {
		return adminRepo.findById(id).get();
	}

	public List<Customer> updateCustomer(Customer customer, String id) throws Exception {

		if (customerRepo.existsById(id)) {
			// custdao.delete(customer);
			customerRepo.save(customer);
			return viewCustomer();
		} else {
			throw new Exception("Invalid Customer,cannot be updated");
		}
	}
	
	public Customer getCustomerById(String id) throws Exception {
        try {
            Optional<Customer> data= customerRepo.findById(id);
            if(data.isPresent()) {
                return data.get();
            }
            else {
                throw new Exception("customer with Id "+id+"does not match");
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

	

}
