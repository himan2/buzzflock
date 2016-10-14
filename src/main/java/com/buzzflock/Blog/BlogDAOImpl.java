package com.buzzflock.Blog;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.buzzflock.ProfileModel.Profile;

@Repository
@EnableTransactionManagement
public class BlogDAOImpl implements BlogDAO  
{
	@Autowired
	private SessionFactory sessionFactory;
 
	public SessionFactory getSessionFactory() 
	{
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	public void insert(Blog p) 
	{
		sessionFactory.getCurrentSession().save(p);
		
	}

	public void delete(long p) 
	{
		sessionFactory.getCurrentSession().delete(p);
		
	}

	public void update(Blog p) 
	{
		sessionFactory.getCurrentSession().update(p);
	}

	public Blog get(String p) 
	{
		
		List l = this.getSessionFactory().getCurrentSession().createQuery("from Blog as p where p.BlogID = :BlogID").setString("BlogID", p).list();
		if (l.size()>0)
		{
			return (Blog)l.get(0);
		}
		else
		{
			return null;
		}

	}
	
	public List<Blog> getAllBlogs() 
	{
		List<Blog> list = (List<Blog>)sessionFactory.getCurrentSession().createQuery("from Blog").list();
		return list;
		
	}
	
	public Blog getBlogWithMaxId() 
	{
				//  select max(Blogid) from Blog
		List<Blog> l = sessionFactory.getCurrentSession().createQuery("from Blog as p where p.BlogID = ( select max(a.BlogID) from Blog as a ) ").list();

		if (l.size() > 0) 
		{
			return l.get(0);
		} 
		else 
		{
			return null;
		}
	}

	
}