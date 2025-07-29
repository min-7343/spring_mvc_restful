package kr.co.sist.rest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MemberController {

//	@GetMapping("/")
	@RequestMapping(value="/",method= {RequestMethod.GET,RequestMethod.POST})
	public String index() {
		return "index";
	}//index
	
}//class
