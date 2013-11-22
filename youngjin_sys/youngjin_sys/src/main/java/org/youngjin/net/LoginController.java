package org.youngjin.net;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {
		
		@RequestMapping( value = "/loginProcess", method = RequestMethod.GET)
		public String loginProcess(){
			return "redirect:/";
		}
		
		@RequestMapping ( value = "/noPermission", method = RequestMethod.GET)
		public String noPermission(){
			return "login/noPermission";
		}
}
