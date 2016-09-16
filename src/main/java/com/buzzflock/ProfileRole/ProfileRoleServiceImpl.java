package com.buzzflock.ProfileRole;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.buzzflock.ProfileModel.*;
import com.buzzflock.ProfileRole.ProfileRoleDAO;


@Service
public class ProfileRoleServiceImpl implements ProfileRoleService
{

	
	@Autowired
	ProfileRoleDAO dao;
	@Transactional
	public void insertUserRole(ProfileRole p) {
		// TODO Auto-generated method stub
		
	}
	@Transactional
	public void deleteUserRole(long p) {
		// TODO Auto-generated method stub
		
	}
	@Transactional
	public void updateUserRole(ProfileRole p) {
		// TODO Auto-generated method stub
		
	}
	@Transactional
	public List<ProfileRole> getAllUsers() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Transactional
	public void generateUserRoles() {

		dao.generateUserRoles();

	}


	public ProfileRole getUserRole(int p) {
		// TODO Auto-generated method stub
		return null;
	}
		
	}