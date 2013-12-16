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
import org.youngjin.net.login.User;
import org.youngjin.net.process.GBlock;
import org.youngjin.net.process.ProcessService;

@Controller
@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER') or hasRole('ROLE_NORMAL')")
public class ProcessController {
	
	@Resource
	private ProcessService processService;
	
	@RequestMapping( value = "/{process}/", method = RequestMethod.GET)
	public String process(Model model, User user, @PathVariable String process){
		
		user.setSubProcess("none");
				
		model.addAttribute("user", user);
		
		return process + "/" + process;
	}	
	
	@RequestMapping( value = "/{process}/gblock/main", method = RequestMethod.GET)
	public String gblock(Model model, User user, @PathVariable String process){
		
		user.setSubProcess("gBlock");
				
		model.addAttribute("user", user);
		model.addAttribute("gblockList", processService.getGBlockList());
		
		return process + "/main";
	}	
	
	@RequestMapping( value = "/gblock/gblock/add.json", method = RequestMethod.POST)
	@ResponseBody
	public GBlock gblockAdd(){
		GBlock gblock = new GBlock();
		
		processService.gblockAdd(gblock);
		
		return gblock;
	}

	@RequestMapping( value = "/gblock/gblock/updateGBlock.json", method = RequestMethod.POST)
	@ResponseBody
	public GBlock gblockUpdate(@RequestBody GBlock gblock){		
		processService.gblockUpdate(gblock);
		
		return gblock;
	}	
}
