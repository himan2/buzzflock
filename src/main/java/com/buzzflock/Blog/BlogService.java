package com.buzzflock.Blog;

import java.util.List;

public interface BlogService 
{
	public void insert(Blog p);
	public void delete(long p);
	public void update(Blog p);
	public Blog get(String p);
	public List<Blog> getAllBlogs();
	public Blog getBlogWithMaxId();
}