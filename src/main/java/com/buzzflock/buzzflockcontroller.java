package com.buzzflock;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class buzzflockcontroller {

	@RequestMapping(value="/")	
	public String home()
	{	
		return "index";
	}
	@RequestMapping(value="/index")	
	public String index()
	{	
		return "index";
	}
	
}
