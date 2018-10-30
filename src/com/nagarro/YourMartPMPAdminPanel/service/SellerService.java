package com.nagarro.YourMartPMPAdminPanel.service;

import java.util.List;

import com.nagarro.YourMartPMPAdminPanel.entity.Seller;


public interface SellerService {
	public List<Seller> getSellers();
	public void saveSeller(Seller theSeller);
	public Seller getSeller(int theId);
	public void deleteSeller(int theId);
}
