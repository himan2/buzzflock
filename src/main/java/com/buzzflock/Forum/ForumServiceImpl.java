package com.buzzflock.Forum;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ForumServiceImpl implements ForumService
{
	@Autowired
	ForumDAO dao;
	
	@Transactional
	public void insert(Forum p) 
	{
		dao.insert(p);
	}
	@Transactional
	public void delete(int p) 
	{
		dao.delete(p);
	}
	@Transactional
	public void update(Forum p) 
	{
		dao.update(p);	
	}
	
	@Transactional
	public Forum get(String p) 
	{
		return dao.get(p);
	}
	@Transactional
	public List<Forum> getAllForums() 
	{
		return dao.getAllForums();
	}
	@Transactional
	public Forum getBlogWithMaxId() 
	{
		return dao.getBlogWithMaxId();
	}

}