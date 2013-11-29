package org.youngjin.net;

import javax.annotation.Resource;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.youngjin.net.login.User;
import org.youngjin.net.outbound.OutboundFilter;
import org.youngjin.net.outbound.OutboundService;

@Controller
@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER') or hasRole('ROLE_NORMAL')")
@RequestMapping("/outbound")
public class OutboundController {
	
	@Resource
	private OutboundService outboundService;
		
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')")
	@RequestMapping(value = "/gblList", method=RequestMethod.GET)
	public String gblList(Model model, User user, @ModelAttribute OutboundFilter outboundFilter ){
		
		user.setProcess("outbound");
		
		outboundFilter.getPagination().setNumItems(outboundService.getGblListCount(outboundFilter));
		
		model.addAttribute("gblList", outboundService.getGblList(outboundFilter));
		model.addAttribute("user", user);
				
		return "outbound/gbl/list";
	}
}
