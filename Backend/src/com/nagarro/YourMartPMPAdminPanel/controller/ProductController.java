package com.nagarro.YourMartPMPAdminPanel.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nagarro.YourMartPMPAdminPanel.entity.Category;
import com.nagarro.YourMartPMPAdminPanel.entity.Product;
import com.nagarro.YourMartPMPAdminPanel.entity.Seller;
import com.nagarro.YourMartPMPAdminPanel.service.CategoryService;
import com.nagarro.YourMartPMPAdminPanel.service.ProductService;
import com.nagarro.YourMartPMPAdminPanel.service.SellerService;

@Controller
@RequestMapping("/")
public class ProductController {

	@Autowired
	private SellerService sellerService;

	@Autowired
	private ProductService productService;

	@Autowired
	private CategoryService categoryService;
	
	@Autowired
    private JavaMailSender mailSender;

	@RequestMapping("/product/list")
	public String listProducts(Model theModel) {
        if(theModel.containsAttribute("products")) {
        	List<Seller> theSellers = sellerService.getSellers();
    		List<String> theCompanies = sellerService.getAllCompanies();
    		List<Category> theCategories = categoryService.getCategories();
    		theModel.addAttribute("sellers", theSellers);
    		theModel.addAttribute("companies", theCompanies);
    		theModel.addAttribute("categories", theCategories);
        }
        else {
		List<Product> theProducts = productService.getProducts();
		List<Seller> theSellers = sellerService.getSellers();
		List<String> theCompanies = sellerService.getAllCompanies();
		List<Category> theCategories = categoryService.getCategories();
		theModel.addAttribute("sellers", theSellers);
		theModel.addAttribute("companies", theCompanies);
		theModel.addAttribute("categories", theCategories);
		theModel.addAttribute("products", theProducts);
		
        }
		
		return "list-products";

	}

	@RequestMapping("/product/s")
	public String s(@RequestParam("checkedIds") int[] checkedIds, @RequestParam("status") String status) {

		productService.setStatus(status, checkedIds);
		return "redirect:/product/list";
	}

	@RequestMapping("/product/filter")
	public String filterRequest(Model theModel,@RequestParam("checkedSellerId") int[] checkedSellerId,
			@RequestParam("checkedStatus") String[] checkedStatus,
			@RequestParam("checkedSellerCompany") String[] checkedSellerCompany,
			@RequestParam("checkedCategory") String[] checkedCategory) {

		//System.out.println(checkedSellerId[0] + checkedStatus[0] + checkedSellerCompany[0] + checkedCategory[0]);
		List<Product> filtered = productService.filter(checkedSellerId, checkedStatus, checkedSellerCompany, checkedCategory);
        theModel.addAttribute("products",filtered);
        List<Seller> theSellers = sellerService.getSellers();
		List<String> theCompanies = sellerService.getAllCompanies();
		List<Category> theCategories = categoryService.getCategories();
		theModel.addAttribute("sellers", theSellers);
		theModel.addAttribute("companies", theCompanies);
		theModel.addAttribute("categories", theCategories);

        return "list-products";
	}

	@RequestMapping("/product/search")
	public String letsSearch(@RequestParam("searchText") String searchText, Model theModel) {
		System.out.println("text is" + searchText);
		List<Product> resultSet = productService.searchResults(searchText);
		theModel.addAttribute("products", resultSet);
		List<Seller> theSellers = sellerService.getSellers();
		List<String> theCompanies = sellerService.getAllCompanies();
		List<Category> theCategories = categoryService.getCategories();
		theModel.addAttribute("sellers", theSellers);
		theModel.addAttribute("companies", theCompanies);
		theModel.addAttribute("categories", theCategories);
		// theModel.addAttribute("searchText", searchText);
		return "list-products";
	}
	
	@RequestMapping("/product")
	public String getProduct(@RequestParam("productId") int theId,Model theModel) {
		Product product = productService.getProduct(theId);
		theModel.addAttribute("product" , product);
		return "detail-product" ;
	}
	
	@RequestMapping("/product/addComment")
	public String getProduct(@RequestParam("comment") String comment,@RequestParam("productId") int productId) {
		SimpleMailMessage email = new SimpleMailMessage();
        email.setTo("nimitjohri5@gmail.com");
        email.setSubject("New Comment Recieved");
        email.setText("YourMart admin has commented on your product : " + comment);
        mailSender.send(email);
		productService.addComment(comment,productId);
		return "redirect:/product/?productId="+productId ;
	}
	
	

}
