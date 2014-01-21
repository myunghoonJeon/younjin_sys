package org.youngjin.net;

import javax.annotation.Resource;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.youngjin.net.basic.BasicService;
import org.youngjin.net.basic.Branch;
import org.youngjin.net.login.User;
import org.youngjin.net.process.GBlock;
import org.youngjin.net.process.ProcessService;

@Controller
@PreAuthorize("hasRole('ROLE_ADMIN')")
@RequestMapping(value = "/basic")
public class BasicController {
	
	@Resource
	private ProcessService processService;
	
	@Resource
	private BasicService basicService;

	//barnch
	@RequestMapping(value = "/branch", method = RequestMethod.GET)
	public String branchMain(Model model, User user){
		
		user.setSubProcess("branch");
		
		model.addAttribute("branchList", basicService.getBranchList());
		
		model.addAttribute("user", user);
		
		return "basic/branch";
	}
	
	@RequestMapping(value = "/branchAdd", method = RequestMethod.GET)
	public String branchAdd(Model model, User user, @ModelAttribute Branch branch){
		
		user.setSubProcess("branch");
		
		model.addAttribute("user", user);
		
		return "basic/branchForm";
	}	
	
	@RequestMapping(value = "/branchAddSubmit", method = RequestMethod.POST)
	public String branchAddSubmit(Model model, User user, @ModelAttribute Branch branch){
		model.addAttribute("check", "yes");
		
		basicService.branchAdd(branch);
		
		return "basic/branchForm";
	}	
	
	@RequestMapping(value = "/{seq}/branchModify", method = RequestMethod.GET)
	public String branchModify(Model model, User user, @PathVariable Integer seq, @ModelAttribute Branch branch){
		
		user.setSubProcess("branch");
		
		model.addAttribute("branch", basicService.getBranch(seq));
		
		model.addAttribute("user", user);
		
		return "basic/branchForm";
	}	
	
	//company
	@RequestMapping(value = "/company", method = RequestMethod.GET)
	public String companyMan(Model model, User user){
		
		user.setSubProcess("company");
		
		model.addAttribute("user", user);
		
		return "basic/company";
	}
	
	//pod
	@RequestMapping(value = "/pod", method = RequestMethod.GET)
	public String podMain(Model model, User user){
		
		user.setSubProcess("pod");
		
		model.addAttribute("user", user);
		
		return "basic/pod";
	}
	
	//carrier
	@RequestMapping(value = "/carrier", method = RequestMethod.GET)
	public String carrierMan(Model model, User user){
		
		user.setSubProcess("carrier");
		
		model.addAttribute("user", user);
		
		return "basic/carrier";
	}
	
	//gbloc
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
