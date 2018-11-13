package com.nagarro.YourMartPMPAdminPanel.controller;

import java.util.Date;
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
import com.nagarro.YourMartPMPAdminPanel.customException.SellerNotFoundException;
import com.nagarro.YourMartPMPAdminPanel.entity.Seller;
import com.nagarro.YourMartPMPAdminPanel.service.SellerService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/seller")
public class SellerRestController {
	@Autowired
	SellerService sellerService;

	@GetMapping
	public List<Seller> getSellers() {
		return sellerService.getSellers();
	}

	@GetMapping("/{sellerId}")
	public Seller getSeller(@PathVariable int sellerId) {

		Seller theSeller = sellerService.getSeller(sellerId);
		if (theSeller == null) {
			throw new SellerNotFoundException("Seller id Not Found - " + sellerId);
		}
		return theSeller;
	}

	@PostMapping("/login")
	public Seller getSellerValid(@RequestBody Seller theSeller) {

		Seller newSeller = sellerService.getSellerForLogin(theSeller.getEmail());
		if (newSeller == null) {
			throw new SellerNotFoundException("Seller id Not Found - " + theSeller.getEmail());
		}
		if (theSeller.getPassword().equals(newSeller.getPassword())) {

			return newSeller;
		} else {

			return null;
		}
	}

	@PostMapping()
	public void addSeller(@RequestBody Seller theSeller) {
		theSeller.setRegisteredOn( new Date());
		sellerService.saveSeller(theSeller);
	}

	@PutMapping("/{sellerId}")
	public void updateSeller(@RequestBody Seller theSeller, @PathVariable int sellerId) {
		theSeller.setId(sellerId);
		sellerService.saveSeller(theSeller);
	}

	@DeleteMapping("/{sellerId}")
	public String deleteCustomer(@PathVariable int sellerId) {

		Seller tempseller = sellerService.getSeller(sellerId);
		if (tempseller == null)
			throw new SellerNotFoundException("seller id not found - " + sellerId);
		sellerService.deleteSeller(sellerId);
		return "Deleted customer id - " + sellerId;
	}
}
