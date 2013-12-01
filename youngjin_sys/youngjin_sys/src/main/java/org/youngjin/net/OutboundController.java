package org.youngjin.net;

import javax.annotation.Resource;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.youngjin.net.login.User;
import org.youngjin.net.outbound.GBL;
import org.youngjin.net.outbound.OutboundFilter;
import org.youngjin.net.outbound.OutboundService;

@Controller
@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER') or hasRole('ROLE_NORMAL')")
public class OutboundController {
	
	@Resource
	private OutboundService outboundService;
		
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')")
	@RequestMapping(value = "/{process}/gblList", method=RequestMethod.GET)
	public String gblList(Model model, User user, @ModelAttribute OutboundFilter outboundFilter, @PathVariable String process ){
		
		outboundFilter.getPagination().setNumItems(outboundService.getGblListCount(outboundFilter));
		
		model.addAttribute("gblList", outboundService.getGblList(outboundFilter));
		model.addAttribute("user", user);
				
		return process + "/gbl/list";
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')")
	@RequestMapping(value = "/{process}/add", method=RequestMethod.GET)
	public String gblAdd(Model model, User user, @ModelAttribute(value="gbl") GBL gbl, @PathVariable String process ){
		model.addAttribute("user", user);
				
		return process + "/gbl/add";
	}	
}
