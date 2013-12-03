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
import org.youngjin.net.code.Code;
import org.youngjin.net.code.CodeService;
import org.youngjin.net.login.User;

@Controller
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class AdminController {

	@Resource
	private AdminService adminService;
	
	@Resource
	private CodeService codeService;
	
	@RequestMapping(value = "/{process}/admin/", method = RequestMethod.GET)
	public String adminMain(Model model, User user, @PathVariable String process){
		
		List<User> userList = adminService.getAllUserList();
		List<Code> authList = codeService.getAllAuthList();
		List<Code> areaList = codeService.getAllAreaList();
		
		//user.setProcess("admin");
		
		model.addAttribute("areaList", areaList);
		model.addAttribute("authList", authList);
		model.addAttribute("allUserList", userList);
		model.addAttribute("user", user);
		
		return "admin/admin";
	}
}