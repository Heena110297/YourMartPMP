package com.nagarro.YourMartPMPAdminPanel.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.YourMartPMPAdminPanel.entity.Category;
import com.nagarro.YourMartPMPAdminPanel.service.CategoryService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/category")
public class CategoryRestController {

	@Autowired
	CategoryService categoryService;

	@GetMapping
	public List<Category> getCategories() {
		return categoryService.getCategories();
	}

}
