package org.youngjin.net;

import javax.annotation.Resource;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
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
		
		@RequestMapping( value= "/admin/add.json", method = RequestMethod.POST)
		@ResponseBody
		public User addUser(@RequestBody User user){
			System.out.println("USER CREATE=====================================================================================");
			user = customJdbcUserDetailManager.createUser();
									
			return user;
		}
		
		@RequestMapping( value = "/admin/delete.json", method = RequestMethod.POST)
		@ResponseBody
		public void deleteUser(@RequestBody User user){
			customJdbcUserDetailManager.deleteUser(user.getUsername());
		}
		
		@RequestMapping( value = "/admin/updateUserInfo.json", method = RequestMethod.POST)
		@ResponseBody
		public User updateUserByAdmin(@RequestBody User user){
			customJdbcUserDetailManager.updateUserByAdmin(user);
			
			return customJdbcUserDetailManager.selectUser(user);
		}
		
		@RequestMapping( value = "/admin/{seq}/changePasswordPop", method = RequestMethod.GET)
		public String changePasswordPop(Model model, @PathVariable Integer seq ){
			
			model.addAttribute("seq", seq);
			
			return "admin/changePasswordPop";
		}
		@RequestMapping( value = "/admin/{seq}/changeIdPop", method = RequestMethod.GET)
		public String changeIdPop(Model model, @PathVariable Integer seq ){
			
			model.addAttribute("seq", seq);
			
			return "admin/changeIdPop";
		}
		@RequestMapping( value = "/admin/clearPassword.json", method = RequestMethod.POST)
		@ResponseBody
		public User clearPasword(@RequestBody User user){
			customJdbcUserDetailManager.changePassword(user);
			
			return customJdbcUserDetailManager.selectUser(user);
		}
		@RequestMapping( value = "/admin/clearId.json", method = RequestMethod.POST)
		@ResponseBody
		public User clearId(@RequestBody User user){
			System.out.println("user new Id : "+user.getNewId());
			customJdbcUserDetailManager.changeId(user);
			return customJdbcUserDetailManager.selectUser(user);
		}
		@RequestMapping( value = "/admin/authChange.json", method = RequestMethod.POST)
		@ResponseBody
		public void authChange(@RequestBody User user){
			customJdbcUserDetailManager.updateUserByAdmin(user);
		}
		
		@RequestMapping( value = "/admin/areaChange.json", method = RequestMethod.POST)
		@ResponseBody
		public void areaChange(@RequestBody User user){
			customJdbcUserDetailManager.updateUserByAdmin(user);
		}
		
		@RequestMapping( value = "/admin/enabledChange.json", method = RequestMethod.POST)
		@ResponseBody
		public void enabledChange(@RequestBody User user){
			
			customJdbcUserDetailManager.updateUserByAdmin(user);
		}
		
}
