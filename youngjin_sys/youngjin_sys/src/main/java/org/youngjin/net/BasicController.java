package org.youngjin.net;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.youngjin.net.login.User;

@Controller
@PreAuthorize("hasRole('ROLE_ADMIN')")
@RequestMapping(value = "/basic")
public class BasicController {
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String basicMan(Model model, User user){
		
		user.setSubProcess("basic");
		
		model.addAttribute("user", user);
		
		return "basic/main";
	}
}
