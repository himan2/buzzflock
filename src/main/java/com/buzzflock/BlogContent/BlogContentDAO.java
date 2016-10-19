package com.buzzflock.BlogContent;

import java.util.List;


public interface BlogContentDAO 
{
	public void insert(BlogContent p);
	public void delete(int p);
	public void update(BlogContent p);
	public BlogContent get(String p);
	public List<BlogContent> getAllBlogs();
	public BlogContent getBlogWithMaxId();
	
}