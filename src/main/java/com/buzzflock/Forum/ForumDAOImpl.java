package com.buzzflock.Forum;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.buzzflock.Blog.Blog;

@Repository
@EnableTransactionManagement
public class ForumDAOImpl implements ForumDAO
{
	@Autowired
	public SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public void insert(Forum p) 
	{
		sessionFactory.getCurrentSession().save(p);
	}

	public void delete(int p) 
	{
		sessionFactory.getCurrentSession().createQuery("delete from Blog as p where p.ForumID = :ForumID").setInteger("ForumID", p).executeUpdate();
	}

	public void update(Forum p) 
	{
		sessionFactory.getCurrentSession().update(p);
	}

	public Forum get(String p) 
	{
		List l = this.getSessionFactory().getCurrentSession().createQuery("from Forum as p where p.ForumID = :ForumID").setString("ForumID", p).list();
		if (l.size()>0)
		{
			return (Forum)l.get(0);
		}
		else
		{
			return null;
		}

	}

	public List<Forum> getAllForums() 
	{
		System.out.println( "Session Factory:" + sessionFactory);
		System.out.println(sessionFactory.getCurrentSession());
		
		List<Forum> list = (List<Forum>)sessionFactory.getCurrentSession().createQuery("from Forum").list();
		return list;
		
	}

	public Forum getBlogWithMaxId() 
	{
		List<Forum> l = sessionFactory.getCurrentSession().createQuery("from Forum as p where p.ForumID = ( select max(a.ForumID) from Forum as a ) ").list();

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