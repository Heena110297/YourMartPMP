package com.nagarro.YourMartPMPAdminPanel.DAO;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.search.FullTextQuery;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nagarro.YourMartPMPAdminPanel.entity.Seller;

@SuppressWarnings("deprecation")
@Repository
public class SellerDAOImpl implements SellerDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Seller> getSellers() {
		Session currentSession = sessionFactory.getCurrentSession();
		Query<Seller> theQuery = currentSession.createQuery("from Seller", Seller.class);
		List<Seller> sellers = theQuery.getResultList();
		for (Seller l : sellers)
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
		theQuery.setParameter("id", theId);
		theQuery.executeUpdate();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> getAllCompanies() {
		Session currentSession = sessionFactory.getCurrentSession();
		@SuppressWarnings("rawtypes")
		Query query = currentSession.createQuery("SELECT  DISTINCT companyName FROM Seller");
		return (List<String>) query.list();
	}

	public FullTextQuery searchResultQuery(String string) {
		FullTextSession fullTextSession = Search.getFullTextSession(sessionFactory.getCurrentSession());
		QueryBuilder queryBuilder = fullTextSession.getSearchFactory().buildQueryBuilder().forEntity(Seller.class)
				.get();

		org.apache.lucene.search.Query luceneQuery = queryBuilder.keyword().wildcard()
				.onFields("name", "owner", "status").matching("*" + string + "*").createQuery();
		FullTextQuery jpaQuery = fullTextSession.createFullTextQuery(luceneQuery);
		return jpaQuery;
	}

	@Override
	public List<Seller> searchResults(String searchText) {
		FullTextQuery jpaQuery = searchResultQuery(searchText);
		@SuppressWarnings("unchecked")
		List<Seller> result = jpaQuery.getResultList();
		return result;
	}

	@Override
	public Seller getSellerForLogin(String email) {

		Session cs = sessionFactory.getCurrentSession();
		Criteria criteria = cs.createCriteria(Seller.class);
		criteria.add(Restrictions.eq("email", email));

		Seller seller = (Seller) criteria.uniqueResult();

		return seller;
	}

}
