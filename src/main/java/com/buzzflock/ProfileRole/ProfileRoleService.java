
package com.buzzflock.ProfileRole;

import java.util.List;

public interface ProfileRoleService 
{

	public void insertUserRole(ProfileRole p);
	public void deleteUserRole(long p);
	public void updateUserRole(ProfileRole p);
	public ProfileRole getUserRole(int p);
    public List<ProfileRole> getAllUsers();
	public void generateUserRoles();
	
}