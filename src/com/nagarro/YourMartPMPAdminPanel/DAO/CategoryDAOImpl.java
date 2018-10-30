package com.nagarro.YourMartPMPAdminPanel.DAO;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nagarro.YourMartPMPAdminPanel.entity.Category;
import com.nagarro.YourMartPMPAdminPanel.entity.Seller;

@Repository
public class CategoryDAOImpl implements CategoryDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Category> getCategories() {
		Session currentSession = sessionFactory.getCurrentSession();
		Query<Category> theQuery = currentSession.createQuery("from Category", Category.class);
		List<Category> categories = theQuery.getResultList();
		for (Category l : categories)
			System.out.println(l.getName());
		return categories;
	}

	@Override
	public void saveCategory(Category theCategory) {
		Session currentSession = sessionFactory.getCurrentSession();
		currentSession.saveOrUpdate(theCategory);
	}

	@Override
	public Category getCategory(int theId) {
		Session currentSession = sessionFactory.getCurrentSession();
		Category theCategory = currentSession.get(Category.class, theId);
		return theCategory;
	}

	@Override
	public void deleteCategory(int theId) {
		Session currentSession = sessionFactory.getCurrentSession();
		@SuppressWarnings("rawtypes")
		Query theQuery = currentSession.createQuery("delete from Category where id=:id");
		theQuery.setParameter("id", theId);
		theQuery.executeUpdate();

	}

}
