package com.nagarro.YourMartPMPAdminPanel.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nagarro.YourMartPMPAdminPanel.entity.Category;
import com.nagarro.YourMartPMPAdminPanel.service.CategoryService;

@Controller
@RequestMapping("/categories")
public class CategoryController {
  
	@Autowired
	CategoryService categoryService ; 
	
	
	@RequestMapping
	public String listCategories(Model theModel) {
		List<Category> theCategories = categoryService.getCategories();
		theModel.addAttribute("categories",theCategories );
		return "category";
	} 
	
	@SuppressWarnings("null")
	@RequestMapping("/add")
	public String addCategory(@RequestParam("name")String name) {
		System.out.println(name);
		Category category = new Category();
		category.setName(name);
		System.out.println(name);
		categoryService.saveCategory(category);
		return "redirect:/categories";
	}
	
	@RequestMapping("/delete")
	public String deleteCategory(@RequestParam("id")int id) {
		categoryService.deleteCategory(id);
		return "redirect:/categories";
	}
}
