package com.buzzflock.BlogComment;

import java.util.List;

public interface BlogCommentDAO 
{
	public void insert(BlogComment p);
	public void delete(int p);
	public void update(BlogComment p);
	public BlogComment get(String p);
	public List<BlogComment> getAllBlogs();
	
	
	
}