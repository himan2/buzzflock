
package com.buzzflock.ProfileModel;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Repository
@EnableTransactionManagement
public class ProfileDAOImpl implements ProfileDAO
{
	@Autowired
	private SessionFactory sessionFactory;
 
	public SessionFactory getSessionFactory() {
		return sessionFactory.getCurrentSession().getSessionFactory();
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	
	public void insertUser(Profile i) {
		//Session session = getSessionFactory().getCurrentSession();
		sessionFactory.getCurrentSession().saveOrUpdate(i);
		
	}

	public void deleteUser(long i) {
		sessionFactory.getCurrentSession().createQuery("delete from User as i where i.ID = :id").setLong("id", i).executeUpdate();
		
	}

	public void updateUser(Profile i) {
		sessionFactory.getCurrentSession().update(i);
	}

	public List<Profile> getAllUsers() {
		return sessionFactory.getCurrentSession().createQuery("from Profile").list();
	}

	public Profile getUser(String i) {
		List l = sessionFactory.getCurrentSession().createQuery("from Profile as i where i.Username = :username").setString("username", i).list();
		if (l.size()>0)
		{
			return (Profile)l.get(0);
		}
		else
		{
			return null;
		}

	}
	
}