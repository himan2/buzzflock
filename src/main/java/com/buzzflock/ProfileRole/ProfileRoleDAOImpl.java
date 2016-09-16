package com.buzzflock.ProfileRole;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Repository
@EnableTransactionManagement
public class ProfileRoleDAOImpl  implements ProfileRoleDAO
{
	@Autowired
	private SessionFactory sessionFactory;
 
	public SessionFactory getSessionFactory() 
	{
		return sessionFactory.getCurrentSession().getSessionFactory();
	}

	public void setSessionFactory(SessionFactory sessionFactory) 
	{
		this.sessionFactory = sessionFactory;
	}

	public void insertUserRole(ProfileRole p) {
		sessionFactory.getCurrentSession().save(p);
		
	}

	public void deleteUserRole(long p) {
		// TODO Auto-generated method stub
		
	}

	public void updateUserRole(ProfileRole p) {
		// TODO Auto-generated method stub
		
	}

	public List<ProfileRole> getAllUsersRole() 
	{
		return sessionFactory.getCurrentSession().createQuery("from ProfileRole").list();
		
	}

	public void generateUserRoles() {
		
		//
		
		try
		{
			ProfileRole ur ;//= new UserRole("USER" , 1);
			
			ur = this.getUserRole(1);
			
			if( ur == null )
			{
				ur = new ProfileRole("Profile" , 1);
				
				this.insertUserRole(ur);
			}
			
			ur = this.getUserRole(2);
			
			if( ur == null )
			{
				ur = new ProfileRole("ADMIN" , 2);
				
				this.insertUserRole(ur);
			}
		}
		catch( Exception e )
		{
			e.printStackTrace();
		}
		
		//
		
			}

	public ProfileRole getUserRole(int p) {
		@SuppressWarnings("unchecked")
		List<ProfileRole> l = sessionFactory.getCurrentSession().createQuery("from ProfileRole as p where p.Role = :id").setInteger("id", p).list();
		if (l.size()>0)
		{
			return l.get(0);
		}
		else
		{
			return null;
		}
		
		
		
	}



		}