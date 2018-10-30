package com.nagarro.YourMartPMPAdminPanel.DAO;

import java.util.List;

import com.nagarro.YourMartPMPAdminPanel.entity.Category;



public interface CategoryDAO {
	public List<Category> getCategories();
	public void saveCategory(Category theCategory);
	public Category getCategory(int theId);
	public void deleteCategory(int theId);
}
