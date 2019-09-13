package com.cg.bookstore.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
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

import com.cg.bookstore.beans.Admin;
import com.cg.bookstore.beans.Books;
import com.cg.bookstore.beans.Category;
import com.cg.bookstore.beans.Customer;
import com.cg.bookstore.beans.OrderedBook;
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
	public List<Category> deleteCategory(@PathVariable int id) throws BookStoreException {
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

	@DeleteMapping("/books/{id}")
	List<Books> deleteBooks(@PathVariable int id) throws BookStoreException {
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
	List<Review> updateReview(@PathVariable int id, @RequestBody Review review) throws BookStoreException {
		return bookstoreService.updateReview(id, review);
	}

	@DeleteMapping("/reviews/{id}")
	List<Review> deleteReview(@PathVariable int id) throws BookStoreException {
		return bookstoreService.deleteReview(id);
	}

	@PostMapping("/addAdmin/{password}/{email}")
	public void addAdmin(@RequestBody Admin admin, @PathVariable("email") String email,
			@PathVariable("password") String pass) throws Exception {
		System.out.println(admin.getEmailId());
		admin.setEmailId(email);
		System.out.println(admin.getName());
		bookstoreService.addAdmin(admin, pass);
		System.out.println(admin.getEmailId());
	}

	@RequestMapping("/adminLogin/{email}/{password}")
	public Admin loginAdmin(@PathVariable("email") String email, @PathVariable("password") String password)
			throws Exception {
		System.out.println(email);
		System.out.println(password);
		return bookstoreService.loginAdmin(email, password);
	}
	
	@GetMapping("/users")
    public List<Admin> viewAdmin() throws Exception {
        return bookstoreService.viewAdmin();
    }
	
	@DeleteMapping("/users/{id}")
	public List<Admin> deleteAdmin(@PathVariable("id") String id) throws Exception{
		return bookstoreService.deleteAdmin(id);
	}
	
	@PutMapping("/{id}")
    public List<Admin> updateAdmin(@PathVariable String id,@RequestBody Admin admin) throws Exception{
        return bookstoreService.updateAdmin(id, admin);
    }
	
	@GetMapping("/admin/{id}")
	public Admin getAdminById(@PathVariable String id) {
		return bookstoreService.getByid(id);
	}
	
	@PostMapping("/addCustomers/{password}")
	public void addCustomer(@RequestBody Customer customer, @PathVariable("password") String pass) throws Exception {
		//System.out.println(admin.getEmailId());
		//admin.setEmailId(email);
		//System.out.println(admin.getName());
		DateFormat df= new SimpleDateFormat("dd/MM/yy");
		java.util.Date date= new java.util.Date();
		String regDate= " ";
		regDate= df.format(date);
		customer.setRegisterDate(regDate);
		bookstoreService.addCustomer(customer, pass);
		//System.out.println(admin.getEmailId());
	}
	
	@GetMapping("/customers")
    public List<Customer> viewCustomer() throws Exception {
        return bookstoreService.viewCustomer();
    }
	
	@DeleteMapping("/customers/{id}")
	public List<Admin> deleteCustomer(@PathVariable("id") String id) throws Exception{
		return bookstoreService.deleteCustomer(id);
	}
	
	@GetMapping("/customer/{id}")
	public Customer getCustomerById(@PathVariable String id) throws Exception {
		return bookstoreService.getCustomerById(id);
	}
	
	@PutMapping("/customer/{id}")
	public List<Customer> updateCustomer(@RequestBody Customer customer, @PathVariable String id)
			throws Exception {
		return bookstoreService.updateCustomer(customer, id);
	}
	
	@PostMapping("/view_cart/{id}")
    public void addtoCart(@PathVariable String id,@RequestBody OrderedBook orderbook) throws BookStoreException {
		bookstoreService.addtoCart(id,orderbook);
    }

}
