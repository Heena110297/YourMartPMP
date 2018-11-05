package com.nagarro.YourMartPMPAdminPanel.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nagarro.YourMartPMPAdminPanel.DAO.SellerDAO;
import com.nagarro.YourMartPMPAdminPanel.entity.Product;
import com.nagarro.YourMartPMPAdminPanel.entity.Seller;

@Service
public class SellerServiceImpl implements SellerService {
	@Autowired
	private SellerDAO sellerDAO;

	@Override
	@Transactional
	public List<Seller> getSellers() {
		return sellerDAO.getSellers();
	}

	@Override
	@Transactional
	public void saveSeller(Seller theSeller) {
		theSeller.setStatus("NEED_APPROVAL");
		sellerDAO.saveSeller(theSeller);
	}

	@Override
	@Transactional
	public Seller getSeller(int theId) {
		return sellerDAO.getSeller(theId);
	}

	@Override
	@Transactional
	public void deleteSeller(int theId) {
		sellerDAO.deleteSeller(theId);
	}

	@Override
	@Transactional
	public List<String> getAllCompanies() {
		return sellerDAO.getAllCompanies();
	}

	@Override
	@Transactional
	public List<Seller> searchResults(String searchText) {
		return sellerDAO.searchResults(searchText);
	}

	@Transactional
	public void setStatus(String status, int[] checkedIds) {
		for (int i = 0; i < checkedIds.length; i++) {
			Seller seller = getSeller(checkedIds[i]);
			seller.setStatus(status);
		}
	}

	@Override
	@Transactional
	public Seller getSellerForLogin(String email) {
		// TODO Auto-generated method stub
		return sellerDAO.getSellerForLogin(email);
	}

}
