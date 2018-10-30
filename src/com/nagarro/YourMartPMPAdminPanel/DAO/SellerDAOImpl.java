package com.nagarro.YourMartPMPAdminPanel.DAO;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import com.nagarro.YourMartPMPAdminPanel.entity.Seller;


@Repository
public class SellerDAOImpl implements SellerDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<Seller> getSellers() {
		Session currentSession = sessionFactory.getCurrentSession();
		Query<Seller> theQuery = currentSession.createQuery("from Seller", Seller.class);
		List<Seller> sellers = theQuery.getResultList();
		for(Seller l : sellers)
			System.out.println(l.getName());
		return sellers;
	}

	@Override
	public void saveSeller(Seller theSeller) {
		Session currentSession = sessionFactory.getCurrentSession();
		currentSession.saveOrUpdate(theSeller);
		
	}

	@Override
	public Seller getSeller(int theId) {
		Session currentSession = sessionFactory.getCurrentSession();
		Seller theSeller = currentSession.get(Seller.class, theId);
		return theSeller;
	}

	@Override
	public void deleteSeller(int theId) {
		Session currentSession = sessionFactory.getCurrentSession();
        @SuppressWarnings("rawtypes")
		Query theQuery = currentSession.createQuery("delete from Seller where id=:id");
        theQuery.setParameter("id",theId);
         theQuery.executeUpdate();
	}

	

}
