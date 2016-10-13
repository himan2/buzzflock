
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
		if (auth != null && !auth.getName().equals("anonymousUser")) {
			// System.out.println(auth.getName());
			user = auth.getName();
		}

		Profile P1 = ps.getUser(user);
		JSONArray jarr = new JSONArray();
		JSONParser jpartemp = new JSONParser();

		try {

			// System.out.println("Displaying List: "+
			// P1.getPendingFriendList());
			// System.out.println("Pending List ID"+ e);
			List<Profile> list = ps.getAllUsers();
			for (Profile p : list) {
				// System.out.println("Verifying" );
				if (!p.getUsername().equals(user)) {
					JSONObject jobj = new JSONObject();
					jobj.put("ProfileID", p.getID());
					jobj.put("ProfileName", p.getUsername());
					jobj.put("ProfileEmail", p.getEmail());
					jobj.put("ProfileImage", p.getImage());

					boolean check = true;
					JSONArray jarr1;
					jarr1 = new JSONArray();
					if (check) 
					{
					if(P1.getPendingFriendList() != null)	
						{
						jarr1 = (JSONArray) jpartemp.parse(P1.getPendingFriendList());// login
																						// user
																						// array
						for (Object e : jarr1) {
							if (e.equals(p.getID().toString())) {
								jobj.put("ProfileAssociation", "pendingrequest");
								check = false;
								break;
							}
						}
					
						}}
					System.out.println(p.getID());
					System.out.println(check);
					if (check) 
					{
						if(P1.getRequestSent()!= null)
							{
								jarr1 = new JSONArray();
								jarr1 = (JSONArray) jpartemp.parse(P1.getRequestSent());// login// user// request// sent
								System.out.println(jarr1);
								for (Object e : jarr1) 
									{
										System.out.println(e);
										System.out.println(p.getID());
											if (e.equals(p.getID().toString())) 
												{
													System.out.println("request sent");
													jobj.put("ProfileAssociation", "Sent");
													check = false;
													break;
												}
									}
							}
					}
					if (check) 
					{
						if(P1.getFriendList()!= null)
							{
								jarr1 = new JSONArray();
								jarr1 = (JSONArray) jpartemp.parse(P1.getFriendList());// login// user// request// sent
								System.out.println(jarr1);
								for (Object e : jarr1) 
									{
										System.out.println(e);
										System.out.println(p.getID());
											if (e.equals(p.getID().toString())) 
												{
													System.out.println("Friends");
													jobj.put("ProfileAssociation", "Friend");
													check = false;
													break;
												}
									}
							}
					}

					if (check) {

						jobj.put("ProfileAssociation", "notfriend");
						check = false;

					}

		
		
					jarr.add(jobj);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		/*finally
		{
			List<Profile> list1 = ps.getAllUsers();
			
			for (Profile p1 : list1) {
				
			JSONObject jobj = new JSONObject();
			jobj.put("ProfileID", p1.getID());
			jobj.put("ProfileName", p1.getUsername());
			jobj.put("ProfileEmail", p1.getEmail());
			jobj.put("ProfileImage", p1.getImage());
			jobj.put("Profileassociation","Empty");
			jarr.add(jobj);
		}
	}
*/		System.out.println(jarr);

		return new ResponseEntity<String>(jarr.toString(), HttpStatus.CREATED);
	}

	
	@CrossOrigin
	@RequestMapping(value = "/AddFriend/", method = RequestMethod.POST)
	public ResponseEntity<String> AddFriend(HttpServletRequest request, HttpServletResponse response,@RequestBody String data, UriComponentsBuilder ucBuilder) 
	{
			System.out.println(data);
			String user = null;
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			if (auth != null && !auth.getName().equals("anonymousUser")) 
				{
					// System.out.println(auth.getName());
					user = auth.getName();
				}
			JSONObject json = new JSONObject();
			JSONParser jpar = new JSONParser();
			try 
				{
					json = (JSONObject) jpar.parse(data);
				} 
			catch (Exception e) 
				{
					e.printStackTrace();
				}
			String pass = json.get("FriendID").toString();
			String IDfriend = json.get("ProfileID").toString();
			System.out.println(pass);
			System.out.println(IDfriend);
			// List <Profile> list = ps.getAllUsers();
			Profile p = ps.getUser(pass);
			Profile myp = ps.getUser(user);
		
			if (user != null) 
				{
						if (p.getPendingFriendList() == null && myp.getRequestSent() == null) 
							{
								System.out.println("1");
								JSONArray jarr = new JSONArray();
								JSONArray jarr1 = new JSONArray();
								jarr.add(myp.getID().toString());
								jarr1.add(IDfriend.toString());
								p.setPendingFriendList(jarr.toJSONString());
								myp.setRequestSent(jarr1.toJSONString());
								p.setCPassword(p.getPassword());
								myp.setCPassword(myp.getPassword());
								ps.updateUser(p);
								ps.updateUser(myp);	
							} 
			else
				{
					System.out.println("3");
					JSONArray jarr = new JSONArray();
					JSONParser jpartemp = new JSONParser();
					JSONArray jarr1 = new JSONArray();
					JSONParser jpartemp1 = new JSONParser();

					try 
						{
							jarr = (JSONArray) jpartemp.parse(p.getPendingFriendList());// friend
							jarr1 = (JSONArray) jpartemp1.parse(myp.getRequestSent());// user
							System.out.println("array 1" + jarr);
							System.out.println("array2" + jarr1);
						}
					catch(Exception e)
						{
							e.printStackTrace();
						}
					if (!jarr.contains(myp.getID().toString())) 
						{
						if (!jarr1.contains(p.getID().toString()))
					
							{	
								System.out.println("last loop");
								jarr.add(myp.getID().toString());
								jarr1.add(p.getID().toString());
								p.setPendingFriendList(jarr.toJSONString());
								myp.setRequestSent(jarr1.toString());
							}
						
						}
					try 
					{
						p.setCPassword(p.getPassword());
						myp.setCPassword(myp.getPassword());
						ps.updateUser(p);
						ps.updateUser(myp);
					}
					catch(Exception e)
					{
						e.printStackTrace();
					}
				}
			}
				JSONObject rjson = new JSONObject();
				rjson.put("status", "Updated");
				rjson.put("ProfileAssociation", "Sent");
				rjson.put("ProfileID",p.getID());
				
				System.out.println(rjson.toString());
				return new ResponseEntity<String>(rjson.toString(), HttpStatus.CREATED);
		}
	
	@CrossOrigin
	@RequestMapping(value="/AcceptRequest/",method=RequestMethod.POST)
	public ResponseEntity<String> AcceptRequest(HttpServletRequest req, HttpServletResponse res, @RequestBody String data , UriComponentsBuilder uri)
	{
			System.out.println(data);
			String user=null;
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			if(authentication != null && !authentication.getName().equals("anonymouseUser"))
			{
				user = authentication.getName();
				System.out.println("login User "+user);
			}
			JSONObject json = new JSONObject();
			JSONParser jpar = new JSONParser();
			try
			{	
				json =  (JSONObject)jpar.parse(data);
			}
			catch(Exception e)
			{
			e.printStackTrace();	
			}
			System.out.println(json);
			String usertoadd = json.get("FriendName").toString();
			String idtoadd =json.get("ProfileID").toString();
			System.out.println("USERNAME TO ACCEPT REQUEST"+ usertoadd);
			System.out.println("USERID TO ACCEPT REQUEST"+ idtoadd);
			
			Profile p1 = ps.getUser(usertoadd);//profiletoadd
			Profile p2 = ps.getUser(user);//userprofile
			System.out.println("Profile of Friend "+ p1);
			System.out.println("User PRofile of login user"+ p2);
			System.out.println(p2.getFriendList());
			System.out.println(p1.getFriendList());
			
			if(user != null)
			{
				if(p2.getFriendList()==null && p1.getFriendList()==null)
				{
					System.out.println("If friend list is empty");
					JSONArray jarr = new JSONArray();
					JSONArray jarr2 = new JSONArray();
					JSONArray jarr3 = new JSONArray();
					JSONArray jarr4 = new JSONArray();
					
					jarr.add(p2.getID().toString());
					jarr2.add(p1.getID().toString());
					jarr3.remove(p2.getID().toString());
					jarr4.remove(p1.getID().toString());
					
					p1.setFriendList(jarr.toString());
					p2.setPendingFriendList(jarr3.toString());
					p1.setRequestSent(jarr4.toString());
					p1.setCPassword(p1.getPassword());
					p2.setFriendList(jarr2.toString());
					p2.setCPassword(p2.getPassword());
					ps.updateUser(p1);
					ps.updateUser(p2);

				}
			else
			{
				System.out.println("else part");
				JSONArray jarr = new JSONArray();
				JSONArray jarr1 = new JSONArray();
				JSONParser jpar0 = new JSONParser();
				JSONParser jpar1 = new JSONParser();
			try
				{
					jarr= (JSONArray)jpar0.parse(p1.getFriendList());
					jarr1=(JSONArray)jpar1.parse(p2.getFriendList());
					System.out.println(jarr);
					System.out.println(jarr1);
				}
			catch(Exception e)
			{
				e.printStackTrace();
			}
				if(!jarr.contains(p2.getID()))
							{
							if(!jarr1.contains(p1.getID()))
								{
								try
								{
								
									jarr.add(p2.getID());
									jarr1.add(p1.getID());
									p1.setFriendList(jarr.toString());
									p2.setFriendList(jarr1.toString());
									p1.setCPassword(p1.getPassword());
									p2.setCPassword(p2.getPassword());
									System.out.println("qqq "+p1.getFriendList());
									System.out.println("dfdsfsd "+p2.getFriendList());
							
										ps.updateUser(p1);
									ps.updateUser(p2);
									}

								catch(Exception e)
									{
										e.printStackTrace();
									}
								
								}
							
							}

									
							}
					
						
					}
				
					
			
			JSONObject json1 = new JSONObject();
			json1.put("status","Updated");
			json1.put("ProfileAssociation", "friend");
			json1.put("ProfileID",p1.getID());
			System.out.println(json.toString());
			
			return  new ResponseEntity<String>(json1.toString() , HttpStatus.CREATED);
		
	}
		
	@CrossOrigin
	@RequestMapping(value = "/Delete/", method = RequestMethod.POST)
	public ResponseEntity<String> DeleteFriend(HttpServletRequest req, HttpServletResponse res,@RequestBody String data, UriComponentsBuilder uri) {
		System.out.println(data);
		String user = "null";
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null && !authentication.getName().equals("anonymousUser")) 
			{
				System.out.println(authentication.getName());
				user = authentication.getName();
			}

		JSONObject json = new JSONObject();
		JSONParser jpar = new JSONParser();
		try {
				json = (JSONObject) jpar.parse(data);
			} 
		catch (Exception e) 	
			{
				e.printStackTrace();
			}
		System.out.println(json);
		String pass = json.get("ProfileID").toString();
		String name = json.get("FriendID").toString();
		

		System.out.println(name);
		System.out.println(pass);
		Profile P1 = ps.getUser(user);
		Profile P2 = ps.getUser(name);
		// System.out.println(P1.getPendingFriendList().toString());

		if (user != null) 
			{
				System.out.println("test 1 passed");
				JSONArray jarr = new JSONArray();
				JSONArray jarr1 = new JSONArray();
				JSONParser jpartemp = new JSONParser();
				JSONParser jpartemp1 = new JSONParser();
			try 
				{
					System.out.println("test 2 passed");
					jarr = (JSONArray) jpartemp.parse(P1.getRequestSent());
					jarr1 = (JSONArray) jpartemp1.parse(P2.getPendingFriendList());
					
					System.out.println("before test 3"+jarr.toJSONString());
					if (jarr.contains(pass)) 
						{
							System.out.println("test 3 passed");
							System.out.println(jarr.toJSONString());
							System.out.println(jarr1.toJSONString());
							jarr.remove(pass);
							jarr1.remove(P1.getID());
							System.out.println(jarr.toJSONString());
							System.out.println(jarr1.toJSONString());
							
							P1.setRequestSent(jarr.toString());
							P1.setCPassword(P1.getPassword());
							
							P2.setPendingFriendList(jarr1.toJSONString());
							P2.setCPassword(P2.getPassword());
							
							ps.updateUser(P1);
							ps.updateUser(P2);
						}
				}

			catch (Exception e) 
				{
					e.printStackTrace();
				}

			
			json.put("status", "Deleted");
			json.put("ProfileAssociation","notfriend");
			json.put("ProfileID", P2.getID());

		}

		return new ResponseEntity<String>(json.toString(), HttpStatus.CREATED);
	}

}