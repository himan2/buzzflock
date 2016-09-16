package com.buzzflock.ProfileModel;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class ProfileServiceImpl implements ProfileService 
{
	@Autowired
	ProfileDAO dao;
    
    @Transactional
    public void insertUser(Profile i) {
		dao.insertUser(i);
	}

    @Transactional
	public void deleteUser(long i) {
		dao.deleteUser(i);
	}

    @Transactional
	public void updateUser(Profile i) {
		dao.updateUser(i);
	}

	@Transactional
	public List<Profile> getAllUsers() {
		return dao.getAllUsers();
	}
	
	@Transactional
	public Profile getUser(String i) {
		return dao.getUser(i);
	}
}