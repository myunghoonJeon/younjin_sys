package org.youngjin.net;

import javax.annotation.Resource;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.youngjin.net.inbound.InboundFilter;
import org.youngjin.net.inbound.InboundService;
import org.youngjin.net.login.User;

@Controller
public class InboundController {
	
	@Resource
	private InboundService inboundService;
	
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')")
	@RequestMapping(value = "/{process}/freightList", method = RequestMethod.GET)
	public String gblList(Model model, User user,
			@PathVariable String process, @ModelAttribute InboundFilter inboundFilter) {

		inboundFilter.getPagination().setNumItems(
				inboundService.getGblListCount(inboundFilter, user));

		user.setSubProcess("freightList");
/*
		model.addAttribute("filterMap", inboundService.getFilterMap());*/

		model.addAttribute("gblList",
				inboundService.getGblList(inboundFilter, user));/*
		model.addAttribute("gblStatus",
				inboundService.getGblStatus(inboundFilter));*/
		
		model.addAttribute("user", user);

		return process + "/freight/list";
	}
}
