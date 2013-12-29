package org.youngjin.net;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.youngjin.net.login.User;

@Controller
public class InboundController {
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')")
	@RequestMapping(value = "/{process}/freightList", method = RequestMethod.GET)
	public String gblList(Model model, User user,
			@PathVariable String process) {

		user.setSubProcess("freightList");
		
		model.addAttribute("user", user);

		return process + "/freight/list";
	}
}
