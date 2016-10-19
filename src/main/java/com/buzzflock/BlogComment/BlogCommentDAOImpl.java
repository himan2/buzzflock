package com.buzzflock.BlogComment;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.buzzflock.BlogContent.BlogContent;

@Repository
@EnableTransactionManagement
public class BlogCommentDAOImpl implements BlogCommentDAO 
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


	public void insert(BlogComment p) 
	{
		
		sessionFactory.getCurrentSession().save(p);
	}

	public void delete(int p) {
		System.out.println("id to be deleted in dao impl  "+p);
		sessionFactory.getCurrentSession().createQuery("delete from BlogComment as p where p.CommentID = :CommentID").setInteger("CommentID", p).executeUpdate();
	}

	public void update(BlogComment p) {
		sessionFactory.getCurrentSession().update(p);
	}

	public BlogComment get(String p) 
	{
		List l = this.getSessionFactory().getCurrentSession().createQuery("from BlogContent as p where p.CommentID = :CommentID").setString("CommentID", p).list();
		if (l.size()>0)
		{
			return (BlogComment)l.get(0);
		}
		else
		{
			return null;
		}
		
	}
	@Transactional
	public List<BlogComment> getAllBlogs() 
	{
		List<BlogComment> list = (List<BlogComment>)sessionFactory.getCurrentSession().createQuery("from BlogComment").list();
		return list;

	}

	
	

}