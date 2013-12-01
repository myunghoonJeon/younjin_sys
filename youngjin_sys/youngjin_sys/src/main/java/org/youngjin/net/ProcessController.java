package org.youngjin.net;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.youngjin.net.login.User;

@Controller
public class ProcessController {
	
	@RequestMapping( value = "/{process}/", method = RequestMethod.GET)
	public String process(Model model, User user, @PathVariable String process){
				
		model.addAttribute("user", user);
		
		return process + "/" + process;
	}
}
