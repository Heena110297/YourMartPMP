package com.nagarro.YourMartPMPAdminPanel.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.nagarro.YourMartPMPAdminPanel.entity.Seller;
import com.nagarro.YourMartPMPAdminPanel.service.SellerService;

@Controller
@RequestMapping("/seller")
public class SellerController {

	@Autowired
	private SellerService sellerService;

	@RequestMapping("/list")
	public String listSellers(Model theModel) {
		List<Seller> theSellers = sellerService.getSellers();
		theModel.addAttribute("sellers", theSellers);
		return "list-sellers";
	}

	@RequestMapping("/search")
	public String letsSearchSeller(@RequestParam("searchText") String searchText, Model theModel) {
		System.out.println("text is" + searchText);
		List<Seller> resultSet = sellerService.searchResults(searchText);
		theModel.addAttribute("sellers", resultSet);
		return "list-sellers";
	}
	
	@RequestMapping("/change")
	public String s(@RequestParam("checkedIds") int[] checkedIds, @RequestParam("status") String status) {

		sellerService.setStatus(status, checkedIds);
		return "redirect:/seller/list";
	}
	
	@RequestMapping
	public String getSeller(@RequestParam("sellerId") int theId,Model theModel) {
		Seller seller = sellerService.getSeller(theId);
		theModel.addAttribute("seller" , seller);
		return "detail-seller" ;
	}
}
