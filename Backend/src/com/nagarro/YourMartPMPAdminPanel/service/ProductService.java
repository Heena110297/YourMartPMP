package com.nagarro.YourMartPMPAdminPanel.service;

import java.util.List;

import com.nagarro.YourMartPMPAdminPanel.entity.Product;



public interface ProductService {
	public List<Product> getProducts();
	public void saveProduct(Product theProduct);
	public Product getProduct(int theId);
	public void deleteProduct(int theId);
	public List<Product> searchResults(String string);
	public void setStatus(String status , int[] checkedIds );
	public List<Product> filter(int[] checkedSellerId, String[] checkedStatus, String[] checkedSellerCompany,String[] checkedCategory);
	public List<Product> searchResultsForSeller(String searchText);
	public void addComment(String comment, int productId);
	public List<Product> getProductBySeller(int sellerId);
}
