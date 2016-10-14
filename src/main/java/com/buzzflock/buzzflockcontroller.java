package com.buzzflock;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.buzzflock.Blog.Blog;
import com.buzzflock.Blog.BlogService;
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
	
	@Autowired
	BlogService bs;
	
	@Autowired
	ServletContext context;

	
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
	
	@RequestMapping(value="/searchnewfriend")
	public String searchnewfreind()
	{
		return "searchnewfriend";
	}
	
	
	@RequestMapping(value = "/viewprofile/{profileName}")
	public ModelAndView addproduct1(@PathVariable("profileName") String name) {
		ModelAndView mav = new ModelAndView("viewprofile");
		System.out.println(name);
		Profile p = us.getUser(name);
		
		System.out.println("profile"+p);
		JSONArray jarr = new JSONArray();
		JSONObject jobj = new JSONObject();
		if (p != null) 
		{
			jobj.put("ProfileName", p.getUsername());
			jobj.put("ProfileImage", p.getImage());
			jobj.put("ProfileEmail", p.getEmail());
			jobj.put("ProfileGender", p.getGender());
			jobj.put("ProfileLocation", p.getLocation());
			jobj.put("ProfilePhone", p.getPhone());
			jarr.add(jobj);
		}
		
		mav.addObject("mydata", jarr.toString());
		System.out.println("ARRAY"+jarr.toString());
		
		return mav;

	}
	
	@RequestMapping(value="/blog")
	public ModelAndView blog()
	{
		
		ModelAndView mav = new ModelAndView("blog");
		JSONArray jarr = new JSONArray();
		
		List<Blog> list = bs.getAllBlogs();
		
		System.out.println(list);
		for(Blog b: list)
		{
			JSONObject jobj = new JSONObject();	
			jobj.put("BlogImage", b.getImage());
			jobj.put("Topicname",b.getTopicname());
			jobj.put("Description",b.getDescription());
			jobj.put("Dateandtime",b.getTimestamp());
			jobj.put("OwnerID",b.getOwnerID());
			
			jarr.add(jobj);
		}
		mav.addObject("data",jarr.toJSONString());
		System.out.print(jarr);
		
		return mav;
	}
	
	
	@RequestMapping(value="/viewblog/{OwnerID}")
	public ModelAndView viewblog(@PathVariable("OwnerID") String Id)
	{
		
		ModelAndView mav = new ModelAndView("viewblog");
		
		return mav;
		
	}
	
	
	
	@RequestMapping(value="/addblog/")
	public ModelAndView addblog() 
	{
		
		
		ModelAndView mav = new ModelAndView("addblog");
		
		mav.addObject("blog" , new Blog());
		return mav;
	}
	
	@RequestMapping(value = "/insertblog", method = RequestMethod.POST)
	public String insertproduct(@ModelAttribute("blog") Blog p) 
	{
		String user = "";
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null && !auth.getName().equals("anonymousUser")) 
		{
			user = auth.getName();
		}
			Profile p1 = us.getUser(user);
		
			DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
			Date dateobj = new Date();
			System.out.println(df.format(dateobj));
			p.setOwnerID(p1.getID().toString());
			p.setTimestamp(df.format(dateobj));
			bs.insert(p);
			
			/*JSONObject jobj= new JSONObject();
			JSONParser jpar= new JSONParser();
			try
			{
				jobj = (JSONObject) jpar.parse(p.toString());
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		
			String id = jobj.get("Topicname").toString();
			System.out.println(id);
*/			
				

			try {
				
				Blog i1 = bs.getBlogWithMaxId();
				
				System.out.println(i1.getBlogID().toString());
				
			
				String path = context.getRealPath("/");

				System.out.println(path);

				File directory = null;

				// System.out.println(ps.getProductWithMaxId());

				if (p.getProductFile().getContentType().contains("image"))
					
				{
					directory = new File(path + "\\resources\\images");

					System.out.println(directory);
					byte[] bytes = null;
					File file = null;
					bytes = p.getProductFile().getBytes();

					if (!directory.exists())
						directory.mkdirs();

					file = new File(directory.getAbsolutePath() + System.getProperty("file.separator") + "image_" + i1.getBlogID() + ".jpg");

					System.out.println(file.getAbsolutePath());

					BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(file));
					stream.write(bytes);
					stream.close();

				}

				i1.setImage("resources/images/image_" + i1.getBlogID() + ".jpg");

				bs.update(i1);
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
			}

			return "redirect:blog";
		}
	
	@RequestMapping("/profile")
	public ModelAndView prfl() 
	{

		String username = "";
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    if (auth != null && !auth.getName().equals("anonymousUser"))
	    {    
	    	username = auth.getName();
	    	
	    }
	    ModelAndView mav = new ModelAndView("profile");
		
/* 
		Profile p = as.get(username);
		
		System.out.println(p.getUsername());
		
		mav.addObject("dataValue",p );
		   mav.addObject("userName", p.getUsername());
*/
		
/*	JSONArray jarr = new JSONArray();
*/		
		JSONObject jobj = new JSONObject();
		List<Profile> list = us.getAllUsers();
		
		for (Profile p : list) {
			
			if( p.getUsername().equals(username) )
			{
				jobj.put("ProfileName", p.getUsername());
				jobj.put("ProfileImage", p.getImage());
				jobj.put("ProfileGender", p.getGender());
				jobj.put("ProfileLocation", p.getLocation());
				jobj.put("ProfilePhone", p.getPhone());
				
				
				
/*				jarr.add(jobj);
*/			}				
		}
		mav.addObject("data", jobj.toJSONString());

		return mav;

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
			Profile validateuser = us.getUser(i.getUsername());
			
			if( validateuser == null )
			{
				if( i.getGender().equals("M") )
				{
					i.setImage("resources/images/male.jpg");
				}
				else
				{
					i.setImage("resources/images/female.jpg");
				}
				
			}
			if( i.getPassword().equals(i.getCPassword()) )
			{
				List<Profile> list = us.getAllUsers();
				
				boolean usermatch= false;
				
				for( Profile u : list )
				{
					
					
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