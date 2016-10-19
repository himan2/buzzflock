package com.buzzflock.BlogComment;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BlogCommentServiceImpl implements BlogCommentService
{
	@Autowired
	BlogCommentDAO dao;
	
	@Transactional
	public void insert(BlogComment p) 
	{
		dao.insert(p);
	}
	@Transactional
	public void delete(int p) 
	{
		dao.delete(p);
	}
	
	@Transactional
	public void update(BlogComment p) 
	{
		dao.update(p);
	}
	
	@Transactional
	public BlogComment get(String p) {
		return dao.get(p);
	}
	
	@Transactional
	public List<BlogComment> getAllBlogs() 
	{
		return dao.getAllBlogs();
	}

}