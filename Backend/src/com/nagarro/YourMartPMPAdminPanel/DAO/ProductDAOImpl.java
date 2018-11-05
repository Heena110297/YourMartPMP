package com.nagarro.YourMartPMPAdminPanel.DAO;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.FetchMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.search.FullTextQuery;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nagarro.YourMartPMPAdminPanel.entity.Category;
import com.nagarro.YourMartPMPAdminPanel.entity.Product;
import com.nagarro.YourMartPMPAdminPanel.entity.Seller;

@Repository
public class ProductDAOImpl implements ProductDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Product> getProducts() {
		Session currentSession = sessionFactory.getCurrentSession();
		Query<Product> theQuery = currentSession.createQuery("from Product", Product.class);
		List<Product> products = theQuery.getResultList();
		for (Product l : products)
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
		theQuery.setParameter("id", theId);
		theQuery.executeUpdate();
	}

	public FullTextQuery searchResultQuery(String string) {
		// System.out.println(string);
		FullTextSession fullTextSession = Search.getFullTextSession(sessionFactory.getCurrentSession());
		QueryBuilder queryBuilder = fullTextSession.getSearchFactory().buildQueryBuilder().forEntity(Product.class)
				.get();

		org.apache.lucene.search.Query luceneQuery = queryBuilder.keyword().wildcard()
				.onFields("name", "id", "seller.name").matching(string + "*").createQuery();
		FullTextQuery jpaQuery = fullTextSession.createFullTextQuery(luceneQuery);
		return jpaQuery;
	}
	
	public FullTextQuery searchResultForSellerQuery(String string) {
		// System.out.println(string);
		FullTextSession fullTextSession = Search.getFullTextSession(sessionFactory.getCurrentSession());
		QueryBuilder queryBuilder = fullTextSession.getSearchFactory().buildQueryBuilder().forEntity(Product.class)
				.get();

		org.apache.lucene.search.Query luceneQuery = queryBuilder.keyword().wildcard()
				.onFields("id", "name", "category.name","status").matching(string + "*").createQuery();
		FullTextQuery jpaQuery = fullTextSession.createFullTextQuery(luceneQuery);
		return jpaQuery;
	}

	@Override
	public List<Product> searchResults(String string) {
		FullTextQuery jpaQuery = searchResultQuery(string);
		@SuppressWarnings("unchecked")
		List<Product> result = jpaQuery.getResultList();
		return result;
	}

	@SuppressWarnings({ "deprecation", "unchecked" })
	public List<Product> filter(int[] checkedSellerId, String[] checkedStatus, String[] checkedSellerCompany,
			String[] checkedCategory) {
		Session currentSession = sessionFactory.getCurrentSession();
		List<Product> filteredProducts1;
		List<Product> filteredProducts2 = null;
		List<Product> filteredProducts3 = null;
		List<Product> filteredProducts4 = null;
		List<Integer> filteredS = null;
		List<Integer> filteredC = null;
		if (checkedCategory.length!=0) {
			filteredC = currentSession.createCriteria(Category.class).add(Restrictions.in("name", checkedCategory))
					.setProjection(Projections.property("id")).list();
		}
		if (checkedSellerCompany.length!=0) {
			filteredS = currentSession.createCriteria(Seller.class)
					.add(Restrictions.in("companyName", checkedSellerCompany))
					.setProjection(Projections.property("id"))
					.list();
		}

		Integer[] checkedSI = Arrays.stream(checkedSellerId).boxed().toArray(Integer[]::new);
		filteredProducts1 = currentSession.createCriteria(Product.class)
				.add(Restrictions.in("seller.id", checkedSI))
				.list();
		if (filteredC != null) {
			filteredProducts4 = currentSession.createCriteria(Product.class)
					.add(Restrictions.in("category.id", filteredC)).list();
			filteredProducts1.addAll(filteredProducts4);
		}
		if (filteredS != null) {
			filteredProducts2 = currentSession.createCriteria(Product.class)
					.add(Restrictions.in("seller.id", filteredS)).list();
			filteredProducts1.addAll(filteredProducts2);
		}
		if (checkedStatus != null) {
			filteredProducts3 = currentSession.createCriteria(Product.class)
				.add(Restrictions.in("status", checkedStatus)).list();
			filteredProducts1.addAll(filteredProducts3);
		}

		Set<Product> hs = new HashSet<>();
		hs.addAll(filteredProducts1);
		filteredProducts1.clear();
		filteredProducts1.addAll(hs);
		return filteredProducts1;

	}

	@Override
	public List<Product> searchResultsForSeller(String searchText) {
		FullTextQuery jpaQuery = searchResultForSellerQuery(searchText);
		@SuppressWarnings("unchecked")
		List<Product> result = jpaQuery.getResultList();
		return result;
	}

}
