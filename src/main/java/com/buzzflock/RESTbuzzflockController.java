
package com.buzzflock;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriComponentsBuilder;

import com.buzzflock.ProfileModel.Profile;
import com.buzzflock.ProfileModel.ProfileService;


@CrossOrigin(origins = "http://localhost:9001", maxAge = 3600)
@RestController

public class RESTbuzzflockController {

	@Autowired
	ProfileService ps;
	
	@Autowired
	ServletContext context;


	@CrossOrigin
    @RequestMapping(value = "/getUserDetails/", method = RequestMethod.POST )
    public ResponseEntity<String> getUserDetails(HttpServletRequest request , HttpServletResponse response , UriComponentsBuilder ucBuilder) {
        
		String user = null;
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    if (auth != null && !auth.getName().equals("anonymousUser"))
	    {   
	    	System.out.println(auth.getName());
	    	user = auth.getName();
	    }
	    
	    JSONObject json = new JSONObject();
	    
	    System.out.println(user);
	    
	    if( user != null )
	    {
	    	Profile p = ps.getUser(user);
	    	
	    	json.put("ProfileName", p.getUsername());
			json.put("ProfileEmail", p.getEmail());
			json.put("ProfileGender", p.getGender());
			json.put("ProfilePhone", p.getPhone());
			json.put("ProfileLocation", p.getLocation());
			json.put("ProfileImage", p.getImage());
	    	
	    }
		
		System.out.println(json.toString());
        
        return new ResponseEntity<String>(json.toString(), HttpStatus.CREATED);
    }
		
	
	@CrossOrigin
    @RequestMapping(value = "/updateUserDetails/", method = RequestMethod.POST)
	
    public ResponseEntity<String> updateUserDetails(HttpServletResponse response,@RequestBody String data, UriComponentsBuilder ucBuilder) {
        
		System.out.println(data);
		
		JSONObject jobjin = new JSONObject();
		
		JSONParser jpar = new JSONParser();
		
		try
		{
			jobjin = (JSONObject)jpar.parse(data);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		System.out.println( jobjin.toJSONString() );
		
		String user = null;
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    if (auth != null && !auth.getName().equals("anonymousUser"))
	    {   
	    	System.out.println(auth.getName());
	    	user = auth.getName();
	    }
	    
	    System.out.println(user);
	    
	    if( user != null )
	    {
	    	Profile p = ps.getUser(user);
	    	
	    	System.out.println(p);
	    	
	    	p.setUsername( jobjin.get("ProfileName").toString() );
	    	p.setEmail( jobjin.get("ProfileEmail").toString() );
	    	p.setGender( jobjin.get("ProfileGender").toString() );
	    	
	    	System.out.println(jobjin.get("ProfilePhone").toString());
	    	
	    	p.setPhone( jobjin.get("ProfilePhone").toString() );
	    	p.setLocation( jobjin.get("ProfileLocation").toString() );
	    	p.setCPassword(p.getPassword());
	    	
	    	ps.updateUser(p);
	    	
	    }
		
		JSONObject json = new JSONObject();
        	        
        json.put("status", "Updated");
        
        System.out.println(json.toString());
        
        return new ResponseEntity<String>(json.toString(), HttpStatus.CREATED);
    }

	@CrossOrigin
    @RequestMapping(value = "/updatePassword/", method = RequestMethod.POST)
	
    public ResponseEntity<String> updatePassword(HttpServletResponse response,@RequestBody String data, UriComponentsBuilder ucBuilder) {
        
		System.out.println(data);
		
		
		JSONObject json = new JSONObject();
        
		JSONParser jpar = new JSONParser();
		
		try
		{
			json = (JSONObject)jpar.parse(data);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		String user = null;
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    if (auth != null && !auth.getName().equals("anonymousUser"))
	    {   
	    	System.out.println(auth.getName());
	    	user = auth.getName();
	    }
	    
	    if( user != null )
	    	
	   {
	    	Profile p = ps.getUser(user);
	    	
	    	String pass = json.get("OldPassword").toString();
	    	
	    	if(p.getPassword().equals(pass) )
	    	{
	    		String npass =  json.get("NewPassword").toString(); 
	    		p.setPassword(npass);
	    		p.setUsername(p.getUsername());
		    	p.setGender( p.getGender());
		    	p.setPhone( p.getPhone() );
		    	p.setLocation( p.getLocation());
		    	p.setCPassword(p.getPassword());
		    	
	    		ps.updateUser(p);
	    		
	    		json.put("status", "Updated");
	    	}
	    	else
		    {
		    	json.put("status", "Password Incorrect");
		    }
	     
	   }
	    else
	    {
	    	json.put("status", "Password Incorrect");
	    }
	    
        System.out.println(json.toString());
        
        return new ResponseEntity<String>(json.toString(), HttpStatus.CREATED);
    }
	
	@CrossOrigin
    @RequestMapping(value = "/updateProfilePicture/", method = RequestMethod.POST )
    public ResponseEntity<String> updateProfilePicture(MultipartHttpServletRequest request , HttpServletResponse response , UriComponentsBuilder ucBuilder) 
	{
		System.out.println( request.getHeader("user") );
		
		System.out.println( request.getFile("file").getName() );
		System.out.println( request.getFile("file").getSize() );
		System.out.println( request.getFile("file").getContentType() );
		System.out.println( request.getFile("file").getOriginalFilename() );
		
		String hashname[] = request.getFile("file").getOriginalFilename().split(",");
		
		JSONObject json = new JSONObject();
		BufferedOutputStream stream = null;
		
		try
	    {
			String path = context.getRealPath("/");
	        
	        System.out.println(path);
	        
	        File directory = null;
	        
	        System.out.println( request.getFile("file") );
	       
	        if (request.getFile("file").getContentType().contains("image"))
	        {
	            directory = new File(path + "\\resources\\images");
	            
	            System.out.println(directory);
	            
	            byte[] bytes = null;
	            File file = null;
	            bytes = request.getFile("file").getBytes();
	            
	            if (!directory.exists()) directory.mkdirs();
	            {
	            	file = new File(directory.getAbsolutePath() + System.getProperty("file.separator") +hashname[0] );
		            
		            System.out.println(file.getAbsolutePath());
		            
		            stream = new BufferedOutputStream(new FileOutputStream(file));
		            stream.write(bytes);
		            stream.close();
		            
		            Profile p = ps.getUser(request.getHeader("user"));
		            
		            if( p != null )
		            {
		            	p.setImage("resources/images/" +hashname[0]);
		            	System.out.println(p.getPassword());
		            	p.setPassword(p.getPassword());
		            	
		            	p.setCPassword(p.getPassword());
		            	ps.updateUser(p);
		            	
		            	json.put("status", "Uploaded");
		            	
		            	json.put("imagesrc", "resources/images/" +hashname[0]);
		            	
		            	
		            }
	            }

	        }
	    }
	    catch (Exception e)
	    {
	    	e.printStackTrace();
	    }
		
		System.out.println(json.toString());
        
        return new ResponseEntity<String>(json.toString(), HttpStatus.CREATED);
    }

	@CrossOrigin
	@RequestMapping(value = "/fetchAllItems/", method = RequestMethod.POST)
	public ResponseEntity<String> fetchAllItems(HttpServletRequest request, HttpServletResponse response,
			UriComponentsBuilder ucBuilder) {
		
		String user = null;
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    if (auth != null && !auth.getName().equals("anonymousUser"))
	    {   
	    	System.out.println(auth.getName());
	    	user = auth.getName();
	    }
	    
	    JSONArray jarr = new JSONArray();
		

		List<Profile> list = ps.getAllUsers();
		
		
		for (Profile p : list) 

		{
			if(!p.getUsername().equals(user))
	{
			JSONObject jobj = new JSONObject();
			System.out.println(p.getUsername());
			jobj.put("ProfileID", p.getID());
			jobj.put("ProfileName", p.getUsername());
			jobj.put("ProfileEmail", p.getEmail());
			jobj.put("ProfileImage", p.getImage());

			jarr.add(jobj);
		}else
		{
			System.out.println("match");
			
		}
		
		}
		return new ResponseEntity<String>(jarr.toString(), HttpStatus.CREATED);
	}
	
	@CrossOrigin
    @RequestMapping(value = "/AddFriend/", method = RequestMethod.POST )
    public ResponseEntity<String> AddFriend(HttpServletRequest request , HttpServletResponse response, @RequestBody String data, UriComponentsBuilder ucBuilder) 
	{

	/*String subject = request.getParameter("FriendID");
		System.out.println(subject);
*/		
		System.out.println(data);
		String user = null;

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null && !auth.getName().equals("anonymousUser"))
		{   
				System.out.println(auth.getName());
				user = auth.getName();
		}

		
		
		JSONObject json = new JSONObject();
		JSONParser jpar = new JSONParser();
		 
		try
		{
			json = (JSONObject)jpar.parse(data);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

		String pass = json.get("FriendID").toString();
		String IDfriend = json.get("ProfileID").toString();
    	System.out.println(pass);
    	System.out.println(IDfriend);
    	
    	List <Profile> list = ps.getAllUsers();
    	  
	    if( user != null )
	  {
	    	Profile p = ps.getUser(pass);
	    	
    	if(p.getUsername().equals(pass))	
    	{
    		System.out.print(p.getUsername());
    		p.setPendingFriendList("IDfriend");
           	ps.updateUser(p);
		   
         }
    	
    }
    	json.put("status", "Updated");
    	
        System.out.println(json.toString());
	
		return new ResponseEntity<String>(json.toString(), HttpStatus.CREATED);
    }
	
	
}

	
