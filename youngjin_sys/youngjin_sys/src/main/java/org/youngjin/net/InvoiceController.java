package org.youngjin.net;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.youngjin.net.login.User;

@Controller
public class InvoiceController {
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/{process}/invoice")
	public String invoiceMain(Model model, User user, @PathVariable String process){
		
		user.setSubProcess("invoice");
		
		model.addAttribute("user", user);
		
		return process + "/invoice/main";
	}
}
