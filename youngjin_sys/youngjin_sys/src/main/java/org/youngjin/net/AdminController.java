package org.youngjin.net;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.youngjin.net.admin.AdminService;
import org.youngjin.net.code.Code;
import org.youngjin.net.code.CodeService;
import org.youngjin.net.invoice.InvoiceService;
import org.youngjin.net.invoice.Rate;
import org.youngjin.net.login.User;

@Controller
@PreAuthorize("hasRole('ROLE_ADMIN')")
@RequestMapping(value = "/admin")
public class AdminController {

	@Resource
	private AdminService adminService;
	
	@Resource
	private CodeService codeService;
	
	@Resource
	private InvoiceService invoiceService;
	
	@RequestMapping(value = "/adminMain", method = RequestMethod.GET)
	public String adminMain(Model model, User user){
				
		List<User> userList = adminService.getAllUserList();
		List<Code> authList = codeService.getAllAuthList();
		List<Code> areaList = codeService.getAllAreaList();
		
		user.setSubProcess("admin");
		
		//user.setProcess("admin");
		
		model.addAttribute("areaList", areaList);
		model.addAttribute("authList", authList);
		model.addAttribute("allUserList", userList);
		model.addAttribute("user", user);
		
		return "admin/admin";
	}
	
	@RequestMapping(value = "/rate", method = RequestMethod.GET)
	public String rate(Model model, User user){
		
		List<Code> tspList = codeService.getCodeList("04");
		List<Code> codeList = codeService.getCodeList("05");
		
		invoiceService.checkNewYearRate();
		
		Map<String, Map<String, Map<String, Rate>>> basicMap = invoiceService.getBasicMap(null);
		
		Map<String, Rate> etcMap = invoiceService.getEtcMap(null);
		
		Map<String, Map<String, Rate>> containerMap = invoiceService.getContainerMap(null);
		
		Map<String, Map<String, List<Rate>>> sitMap = invoiceService.getSitMap(null);
		
		Map<String, Map<String, List<Rate>>> otherMap = invoiceService.getOtherMap(null);
		
		user.setSubProcess("rate");
		
		model.addAttribute("user", user);
		model.addAttribute("tspList", tspList);
		model.addAttribute("codeList", codeList);
		model.addAttribute("basicMap", basicMap);
		model.addAttribute("etcMap", etcMap);
		model.addAttribute("containerMap", containerMap);
		model.addAttribute("sitMap", sitMap);
		model.addAttribute("otherMap", otherMap);
		
		return "admin/rate";
	}
	
	@RequestMapping(value = "/rate/basic/insert.json", method = RequestMethod.POST)
	@ResponseBody
	public void basicRateInsert(@RequestBody Rate rate) {
		invoiceService.basicInsert(rate);
	}	
	
	@RequestMapping(value = "/rate/etc/insert.json", method = RequestMethod.POST)
	@ResponseBody
	public void etcRateInsert(@RequestBody Rate rate) {
		invoiceService.etcInsert(rate);
	}
	
	@RequestMapping(value = "/rate/container/insert.json", method = RequestMethod.POST)
	@ResponseBody
	public void containerRateInsert(@RequestBody Rate rate) {
		invoiceService.containerInsert(rate);
	}	
	
	@RequestMapping(value = "/rate/sit/insert.json", method = RequestMethod.POST)
	@ResponseBody
	public void sitRateInsert(@RequestBody Rate rate) {
		invoiceService.sitInsert(rate);
	}	
	
	@RequestMapping(value = "/rate/other/insert.json", method = RequestMethod.POST)
	@ResponseBody
	public void otherRateInsert(@RequestBody Rate rate) {
		invoiceService.otherInsert(rate);
	}
	
	@RequestMapping(value = "/container", method = RequestMethod.GET)
	public String container(Model model, User user){
		
		return "admin/container";
	}	
}
