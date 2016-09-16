
package com.buzzflock.ProfileModel;

import java.util.List;

public interface ProfileDAO
{
	public void insertUser(Profile i);
	public void deleteUser(long i);
	public void updateUser(Profile i);
	public Profile getUser(String i);
    public List<Profile> getAllUsers();
    
}
