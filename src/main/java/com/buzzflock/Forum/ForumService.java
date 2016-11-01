package com.buzzflock.Forum;

import java.util.List;

public interface ForumService 
{
	

	public void insert(Forum p);
	public void delete(int p);
	public void update(Forum p);
	public Forum get(String p);
	public List<Forum> getAllForums();
	public Forum getBlogWithMaxId();
	

}
