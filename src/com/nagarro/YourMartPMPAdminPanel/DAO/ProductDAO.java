package com.nagarro.YourMartPMPAdminPanel.DAO;

import java.util.List;

import com.nagarro.YourMartPMPAdminPanel.entity.Product;

public interface ProductDAO {
	public List<Product> getProducts();
	public void saveProduct(Product theProduct);
	public Product getProduct(int theId);
	public void deleteProduct(int theId);
}
