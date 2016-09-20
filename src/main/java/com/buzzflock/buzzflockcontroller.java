package com.buzzflock;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.buzzflock.ProfileModel.Profile;
import com.buzzflock.ProfileModel.ProfileService;
import com.buzzflock.ProfileRole.ProfileRoleService;

@Controller
public class buzzflockcontroller {

	@Autowired
	ProfileService us;
	
	@Autowired
	ProfileRoleService urs;
	
	@Autowired
	JavaMailSender mail;
	
	@RequestMapping(value="/")	
	public String home()
	{	
		urs.generateUserRoles();
		return "index";
	}
	@RequestMapping(value="/index")	
	public String index()
	{	
		return "index";
	}
	
	@RequestMapping(value="/profile")	
	public String profile()
	{	
		return "profile";
	}
	
	@RequestMapping(value="/signup")
	public ModelAndView signup()
	{
		 
	 ModelAndView mav = new ModelAndView("signup");
     mav.addObject("newuser",new Profile());	 
     	return mav;
	}
	
	@RequestMapping(value="/insertuser", method = RequestMethod.POST)
	public ModelAndView insertuser( @Valid @ModelAttribute ("newuser") Profile i , BindingResult bind, HttpServletRequest req , HttpServletResponse resp )
	{
		ModelAndView mav = new ModelAndView("signup");
		
		System.out.println(i);
		
		if( bind.hasErrors() )
		{
			mav.addObject("newuser", i);
		}
		else
		{
			if( i.getPassword().equals(i.getCPassword()) )
			{
				List<Profile> list = us.getAllUsers();
				
				boolean usermatch= false;
				
				for( Profile u : list )
				{
					System.out.println(u.getUsername());
					System.out.println(i.getUsername());
					
					if( u.getUsername().equals(i.getUsername()) )
					{
						usermatch= true;
						break;
					}
				}
				
				if( usermatch == false )
				{
					us.insertUser(i);
					
					mav.addObject("newuser", new Profile());
					
					mav.addObject("success", "success");
				}	
				else
				{
					mav.addObject("newuser", i);
					
					mav.addObject("useralreadyexists", "useralreadyexists");
				}
			}
			else
			{	
				mav.addObject("newuser", i);
				
				mav.addObject("passwordmismatch", "passwordmismatch");
			}
				
		}
		
		
		String uemail1 = req.getParameter("email");
		System.out.println( uemail1 );
		
		SimpleMailMessage email = new SimpleMailMessage();
		
		email.setTo(uemail1);
		email.setSubject("Welcome to Krystal Watches");
		email.setText(" Thanks for Contacting Us \n We will get back to you soon \n\n Regards, \n The Krystal Watches Team");
		
		
		try
		{
			mail.send(email);
			
			System.out.println("Mail 2 Sent");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return mav ;
	}
	
	@RequestMapping(value="/loginpage" , method = RequestMethod.GET)
	public ModelAndView login() {
		
		ModelAndView mav = new ModelAndView("login");
		
		return mav ;
	}

 
 @RequestMapping(value="/logout", method = RequestMethod.GET)
	public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
	    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    if (auth != null){    
	        
	    	System.out.println("In LogOut");
	    	new SecurityContextLogoutHandler().logout(request, response, auth);
	        
	        
	    }
	    
	    return "index";
	}
}
