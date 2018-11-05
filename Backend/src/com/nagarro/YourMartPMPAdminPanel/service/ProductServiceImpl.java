package com.nagarro.YourMartPMPAdminPanel.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nagarro.YourMartPMPAdminPanel.DAO.ProductDAO;
import com.nagarro.YourMartPMPAdminPanel.entity.Product;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductDAO productDAO;

	@Override
	@Transactional
	public List<Product> getProducts() {
		return productDAO.getProducts();
	}

	@Override
	@Transactional
	public void saveProduct(Product theProduct) {
		theProduct.setStatus("NEW");
		/*DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		LocalDate localDate = LocalDate.now();
		Date date = new Date(localDate.toString());
		theProduct.setCreated(date);*/
		
		System.out.println(theProduct);
		productDAO.saveProduct(theProduct);
	}

	@Override
	@Transactional
	public Product getProduct(int theId) {
		return productDAO.getProduct(theId);
	}

	@Override
	@Transactional
	public void deleteProduct(int theId) {
		productDAO.deleteProduct(theId);
	}

	@Override
	@Transactional
	public List<Product> searchResults(String string) {

		return productDAO.searchResults(string);
	}

	@Transactional
	public void setStatus(String status, int[] checkedIds) {
		for (int i = 0; i < checkedIds.length; i++) {
			Product product = getProduct(checkedIds[i]);
			product.setStatus(status);
		}
	}
	@Transactional
	public List<Product> filter(int[] checkedSellerId, String[] checkedStatus, String[] checkedSellerCompany,String[] checkedCategory){
		return productDAO.filter(checkedSellerId, checkedStatus, checkedSellerCompany, checkedCategory);
	}

	@Override
	@Transactional
	public List<Product> searchResultsForSeller(String searchText) {
		
		return productDAO.searchResultsForSeller(searchText);
	}

	@Override
	@Transactional
	public void addComment(String comment, int productId) {
		Product product = getProduct(productId);
		product.setComment(comment);
		
	}

}
