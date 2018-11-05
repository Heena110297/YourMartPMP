package com.nagarro.YourMartPMPAdminPanel.controller;

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
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.YourMartPMPAdminPanel.customException.ProductNotFoundException;
import com.nagarro.YourMartPMPAdminPanel.entity.Product;
import com.nagarro.YourMartPMPAdminPanel.service.ProductService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins="http://localhost:4200")
public class ProductRestController {

	@Autowired
	ProductService productService;

	@GetMapping("/product")
	public List<Product> getProducts() {
		System.out.println(" here ehere");
		return productService.getProducts();
	}

	@GetMapping("/product/{productId}")
	public Product getProduct(@PathVariable int productId) {
		Product theProduct = productService.getProduct(productId);
		if (theProduct == null)
			throw new ProductNotFoundException("Product id Not Found - " + productId);
		return theProduct;
	}

	@PostMapping("/product")
	//@ResponseStatus(value=HttpStatus.OK)
	public Product addProduct(@RequestBody Product theProduct) {
		System.out.println(theProduct.getSeller().getId());
		productService.saveProduct(theProduct);
		return theProduct;
	}

	@PutMapping("/product/{productId}")
	public void updateProduct(@RequestBody Product theProduct,@PathVariable int productId) {
		theProduct.setId(productId);
		theProduct.setStatus("REVIEW");
		productService.saveProduct(theProduct);
	}

	@DeleteMapping("/product/{productId}")
	public String deleteCustomer(@PathVariable int productId) {

		Product tempProduct = productService.getProduct(productId);
		if (tempProduct == null)
			throw new ProductNotFoundException("Product id not found - " + productId);
		productService.deleteProduct(productId);
		return "Deleted customer id - " + productId;
	}
	
	@GetMapping("product/search/{searchText}")
	public List<Product> letsSearch(@PathVariable("searchText") String searchText) {
		System.out.println("text is" + searchText);
		List<Product> resultSet = productService.searchResultsForSeller(searchText);
		return resultSet ;
		
	}
}
