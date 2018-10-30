package com.nagarro.YourMartPMPAdminPanel.service;

import java.util.List;

import com.nagarro.YourMartPMPAdminPanel.entity.Product;



public interface ProductService {
	public List<Product> getProducts();
	public void saveProduct(Product theProduct);
	public Product getProduct(int theId);
	public void deleteProduct(int theId);
}
