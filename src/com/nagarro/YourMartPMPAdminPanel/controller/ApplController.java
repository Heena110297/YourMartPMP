package com.nagarro.YourMartPMPAdminPanel.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nagarro.YourMartPMPAdminPanel.entity.Product;
import com.nagarro.YourMartPMPAdminPanel.entity.Seller;
import com.nagarro.YourMartPMPAdminPanel.service.ProductService;
import com.nagarro.YourMartPMPAdminPanel.service.SellerService;

@Controller
@RequestMapping("/")
public class ApplController {

	@Autowired
	private SellerService sellerService;

	@Autowired
	private ProductService productService;

	@RequestMapping("/seller/list")
	public String listSellers(Model theModel) {
		List<Seller> theSellers = sellerService.getSellers();
		theModel.addAttribute("sellers", theSellers);
		return "list-sellers";
	}
	
	@RequestMapping("/product/list")
	public String listProducts(Model theModel) {
		List<Product> theProducts = productService.getProducts();
		theModel.addAttribute("products", theProducts);
		return "list-products";
	}
	

}
