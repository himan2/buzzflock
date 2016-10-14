package com.buzzflock.Blog;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BlogServiceImpl implements BlogService 
{
	@Autowired
	BlogDAO dao;
	
	@Transactional
	public void insert(Blog p) 
	{
		dao.insert(p);
	}
	@Transactional
	public void delete(long p) 
	{
		dao.delete(p);
	}
	@Transactional
	public void update(Blog p) 
	{
		dao.update(p);
	}
	
	@Transactional
	public Blog get(String p) 
	{
		return dao.get(p);
	}
	@Transactional
	public List<Blog> getAllBlogs() 
	{
		return dao.getAllBlogs();
	}
	@Transactional
	public Blog getBlogWithMaxId()
	{
		return dao.getBlogWithMaxId();
	}
}