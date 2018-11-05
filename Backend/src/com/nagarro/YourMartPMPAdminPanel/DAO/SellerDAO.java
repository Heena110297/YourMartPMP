package com.nagarro.YourMartPMPAdminPanel.DAO;

import java.util.List;

import com.nagarro.YourMartPMPAdminPanel.entity.Product;
import com.nagarro.YourMartPMPAdminPanel.entity.Seller;



public interface SellerDAO {

	public List<Seller> getSellers();
	public void saveSeller(Seller theSeller);
	public Seller getSeller(int theId);
	public void deleteSeller(int theId);
	public List<String> getAllCompanies();
	public List<Seller> searchResults(String searchText);
	public Seller getSellerForLogin(String email);
}
