package com.buzzflock.BlogContent;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.buzzflock.Blog.Blog;
import com.buzzflock.Blog.BlogDAO;

@Service
public class BlogContentServiceImpl implements BlogContentService
{

	@Autowired
	BlogContentDAO dao;
	
	@Transactional
	public void insert(BlogContent p) 
	{
		dao.insert(p);
	}
	
	@Transactional
	public void delete(int p) 
	{
		dao.delete(p);
	}
	
	@Transactional
	public void update(BlogContent p) 
	{
		dao.update(p);
	}
	
	@Transactional
	public BlogContent get(String p) 
	{
		return dao.get(p);
	}
	
	@Transactional
	public List<BlogContent> getAllBlogs() 
	{
		return dao.getAllBlogs();
	}
	
	@Transactional
	public BlogContent getBlogWithMaxId()
	{
		return dao.getBlogWithMaxId();
	}

}