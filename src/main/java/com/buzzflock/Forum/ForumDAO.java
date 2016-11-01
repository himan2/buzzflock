package com.buzzflock.Forum;

import java.util.List;

import com.buzzflock.Blog.Blog;

public interface ForumDAO 
{
	public void insert(Forum p);
	public void delete(int p);
	public void update(Forum p);
	public Forum get(String p);
	public List<Forum> getAllForums();
	public Forum getBlogWithMaxId();
	
}