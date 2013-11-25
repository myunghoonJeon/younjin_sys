package org.youngjin.net;

import javax.annotation.Resource;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.youngjin.net.login.CustomJdbcUserDetailManager;
import org.youngjin.net.login.User;

@Controller
public class LoginController {
	
		@Resource
		private CustomJdbcUserDetailManager customJdbcUserDetailManager;
		
		@RequestMapping( value = "/loginProcess", method = RequestMethod.GET)
		public String loginProcess(){
			return "redirect:/";
		}
		
		@RequestMapping ( value = "/noPermission", method = RequestMethod.GET)
		public String noPermission(){
			return "login/noPermission";
		}
		
		@PreAuthorize("hasRole('ROLE_ADMIN')")
		@RequestMapping( value= "/admin/add", method = RequestMethod.GET)
		public String addUserGet(@ModelAttribute(value = "user") User user){
			
			
			return "redirect:/admin/admin";
		}
}
