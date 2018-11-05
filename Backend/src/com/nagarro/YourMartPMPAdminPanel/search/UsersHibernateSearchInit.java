package com.nagarro.YourMartPMPAdminPanel.search;

import org.hibernate.SessionFactory;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


@Component
public class UsersHibernateSearchInit implements ApplicationListener<ContextRefreshedEvent> {

	@Autowired
	private SessionFactory sessionFactory ;
	

	@Override
	@Transactional
	public void onApplicationEvent(ContextRefreshedEvent event) {
		
		FullTextSession fullTextSession = Search.getFullTextSession(sessionFactory.getCurrentSession());
		try {
			fullTextSession.createIndexer().startAndWait();
		} catch (InterruptedException e) {
			System.out.println("Error occured trying to build Hibernate Search indexes "
					+ e.toString());
		}
	}
	

}