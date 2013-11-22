package org.youngjin.net;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.youngjin.net.admin.AdminService;
import org.youngjin.net.login.User;

@Controller
public class AdminController {

	@Resource
	private AdminService adminService;
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/{admin}/{admin}", method = RequestMethod.GET)
	public String adminMain(Model model, User user, @PathVariable String admin){
		
		List<User> userList = adminService.getAllUserList();
		
		user.setProcess("admin");
		
		model.addAttribute("allUserList", userList);
		model.addAttribute("user", user);
		
		return admin + "/" + admin;
	}
}
