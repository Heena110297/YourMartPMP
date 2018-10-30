package com.nagarro.YourMartPMPAdminPanel.DAO;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nagarro.YourMartPMPAdminPanel.entity.Product;
import com.nagarro.YourMartPMPAdminPanel.entity.Seller;

@Repository
public class ProductDAOImpl implements ProductDAO {
    
	@Autowired
	private SessionFactory sessionFactory ;
	
	@Override
	public List<Product> getProducts() {
		Session currentSession = sessionFactory.getCurrentSession();
		Query<Product> theQuery = currentSession.createQuery("from Product", Product.class);
		List<Product> products = theQuery.getResultList();
		for(Product l : products)
			System.out.println(l.getName());
		return products;
	}

	@Override
	public void saveProduct(Product theProduct) {
		Session currentSession = sessionFactory.getCurrentSession();
		currentSession.saveOrUpdate(theProduct);
		
	}

	@Override
	public Product getProduct(int theId) {
		Session currentSession = sessionFactory.getCurrentSession();
		Product theProduct = currentSession.get(Product.class, theId);
		return theProduct;
	}

	@Override
	public void deleteProduct(int theId) {
		Session currentSession = sessionFactory.getCurrentSession();
		Query theQuery = currentSession.createQuery("delete from Product where id=:id");
        theQuery.setParameter("id",theId);
         theQuery.executeUpdate();
	}

}
