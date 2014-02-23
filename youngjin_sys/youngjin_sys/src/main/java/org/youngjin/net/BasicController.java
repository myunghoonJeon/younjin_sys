package org.youngjin.net;

import java.util.List;

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
import org.youngjin.net.basic.Carrier;
import org.youngjin.net.basic.Company;
import org.youngjin.net.basic.Mileage;
import org.youngjin.net.basic.Pod;
import org.youngjin.net.login.User;
import org.youngjin.net.process.GBlock;
import org.youngjin.net.process.ProcessService;

@Controller
@PreAuthorize("hasRole('ROLE_LEVEL4') or hasRole('ROLE_LEVEL3') or hasRole('ROLE_LEVEL2') or hasRole('ROLE_LEVEL1')")
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
		
		model.addAttribute("flag", "add");		
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
		
		model.addAttribute("flag", "modify");
		model.addAttribute("branch", basicService.getBranch(seq));
		
		model.addAttribute("user", user);
		
		return "basic/branchForm";
	}	
	
	@RequestMapping(value = "/branchModifySubmit", method = RequestMethod.POST)
	public String branchModifySubmit(Model model, User user, @ModelAttribute Branch branch){
		model.addAttribute("check", "yes");
		
		basicService.branchModify(branch);
		
		return "basic/branchForm";
	}
	
	//company
	@RequestMapping(value = "/company", method = RequestMethod.GET)
	public String companyMan(Model model, User user){
		
		user.setSubProcess("company");
		
		model.addAttribute("user", user);
		model.addAttribute("companyList", basicService.getCompanyList());
		
		return "basic/company";
	}
	
	@RequestMapping(value = "/companyAdd", method = RequestMethod.GET)
	public String companyAdd(Model model, User user, @ModelAttribute Company company){
		
		user.setSubProcess("company");

		model.addAttribute("flag", "add");	
		model.addAttribute("user", user);
		
		return "basic/companyForm";
	}	
	
	@RequestMapping(value = "/companyAddSubmit", method = RequestMethod.POST)
	public String companyAddSubmit(Model model, User user, @ModelAttribute Company company){
		model.addAttribute("check", "yes");
		
		basicService.companyAdd(company);
		
		return "basic/companyForm";
	}	
	
	@RequestMapping(value = "/{seq}/companyModify", method = RequestMethod.GET)
	public String companyModify(Model model, User user, @PathVariable Integer seq, @ModelAttribute Company company){
		
		user.setSubProcess("company");
		
		model.addAttribute("flag", "modify");
		model.addAttribute("company", basicService.getCompany(seq));
		
		model.addAttribute("user", user);
		
		return "basic/companyForm";
	}
	
	@RequestMapping(value = "/companyModifySubmit", method = RequestMethod.POST)
	public String companyModifySubmit(Model model, User user, @ModelAttribute Company company){
		model.addAttribute("check", "yes");
		
		basicService.companyModify(company);
		
		return "basic/companyForm";
	}
	
	//pod
	@RequestMapping(value = "/pod", method = RequestMethod.GET)
	public String podMain(Model model, User user){
		
		user.setSubProcess("pod");
		
		model.addAttribute("podList", basicService.getPodList());
		
		model.addAttribute("user", user);
		
		return "basic/pod";
	}
	
	@RequestMapping(value = "/podAdd", method = RequestMethod.GET)
	public String podAdd(Model model, User user, @ModelAttribute Pod pod){
		
		user.setSubProcess("pod");

		model.addAttribute("flag", "add");	
		model.addAttribute("user", user);
		
		return "basic/podForm";
	}
	
	@RequestMapping(value = "/podAddSubmit", method = RequestMethod.POST)
	public String podAddSubmit(Model model, User user, @ModelAttribute Pod pod){
		model.addAttribute("check", "yes");
		
		basicService.podAdd(pod);
		
		return "basic/podForm";
	}		
	
	@RequestMapping(value = "/{seq}/podModify", method = RequestMethod.GET)
	public String podModify(Model model, User user, @PathVariable Integer seq, @ModelAttribute Pod pod){
		
		user.setSubProcess("pod");
		
		model.addAttribute("flag", "modify");
		model.addAttribute("pod", basicService.getPod(seq));
		
		model.addAttribute("user", user);
		
		return "basic/podForm";
	}
	
	@RequestMapping(value = "/podModifySubmit", method = RequestMethod.POST)
	public String podModifySubmit(Model model, User user, @ModelAttribute Pod pod){
		model.addAttribute("check", "yes");
		
		basicService.podModify(pod);
		
		return "basic/podForm";
	}	
	
	//carrier
	@RequestMapping(value = "/carrier", method = RequestMethod.GET)
	public String carrierMan(Model model, User user){
		
		user.setSubProcess("carrier");
		
		model.addAttribute("carrierList", basicService.getCarrierList());
		model.addAttribute("user", user);
		
		return "basic/carrier";
	}
	
	@RequestMapping(value = "/carrierAdd", method = RequestMethod.GET)
	public String carrierAdd(Model model, User user, @ModelAttribute Carrier carrier){
		
		user.setSubProcess("carrier");

		model.addAttribute("flag", "add");	
		model.addAttribute("user", user);
		
		return "basic/carrierForm";
	}
	
	@RequestMapping(value = "/carrierAddSubmit", method = RequestMethod.POST)
	public String carrierAddSubmit(Model model, User user, @ModelAttribute Carrier carrier){
		model.addAttribute("check", "yes");
		
		basicService.carrierAdd(carrier);
		
		return "basic/carrierForm";
	}		
	
	@RequestMapping(value = "/{seq}/carrierModify", method = RequestMethod.GET)
	public String carrierModify(Model model, User user, @PathVariable Integer seq, @ModelAttribute Carrier carrier){
		
		user.setSubProcess("carrier");
		
		model.addAttribute("flag", "modify");
		model.addAttribute("carrier", basicService.getCarrier(seq));
		
		model.addAttribute("user", user);
		
		return "basic/carrierForm";
	}	
	
	
	@RequestMapping(value = "/carrierModifySubmit", method = RequestMethod.POST)
	public String carrierModifySubmit(Model model, User user, @ModelAttribute Carrier carrier){
		model.addAttribute("check", "yes");
		
		basicService.carrierModify(carrier);
		
		return "basic/carrierForm";
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
	
	@RequestMapping( value="/mileage", method = RequestMethod.GET)
	public String mileage(Model model, User user){
		
		user.setSubProcess("mileage");
		
		model.addAttribute("user", user);
		
		List<Mileage> mileageList = basicService.getMileageList();
		
		model.addAttribute("mileageList", mileageList);
		
		return "basic/mileage";
	}
	
	@RequestMapping( value = "/mileage/add.json", method = RequestMethod.POST)
	@ResponseBody
	public Mileage mileageAdd(){
		Mileage mileage = new Mileage();
		
		basicService.mileageAdd(mileage);
		
		return mileage;
	}
	
	@RequestMapping( value = "/mileage/update.json", method = RequestMethod.POST)
	@ResponseBody
	public void mileageUpdate(@RequestBody Mileage mileage){
		basicService.mileageUpdate(mileage);
	}
}
