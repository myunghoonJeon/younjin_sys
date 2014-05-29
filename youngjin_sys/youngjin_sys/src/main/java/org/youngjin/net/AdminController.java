package org.youngjin.net;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
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
@PreAuthorize("hasRole('ROLE_LEVEL4') or hasRole('ROLE_LEVEL3')")
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
	public String rate(Model model, User user, @ModelAttribute Rate rate){
		if(rate.getWriteYear() == null){
			rate.setWriteYear("2013");
		}
		List<Code> tspList = codeService.getCodeList("04");
		List<Code> codeList = codeService.getCodeList("05");
		System.out.println("[[[[[[[[[[[[ rate test : "+rate.getWriteYear()+" ]]]]]]]]]]]]]]]");
		invoiceService.checkNewYearRate(rate);
		
		List<String> yearList = invoiceService.getYearList();
		
		Map<String, Map<String, Map<String, Rate>>> basicMap = invoiceService.getBasicMap(rate);
		
		Map<String, Rate> etcMap = invoiceService.getEtcMap(rate);
		
		Map<String, Map<String, Rate>> containerMap = invoiceService.getContainerMap(rate);
		
		Map<String, Map<String, List<Rate>>> sitMap = invoiceService.getSitMap(rate);
		
		Map<String, Map<String, List<Rate>>> otherMap = invoiceService.getOtherMap(rate);
		
		user.setSubProcess("rate");
		
		model.addAttribute("user", user);
		model.addAttribute("yearList", yearList);
		model.addAttribute("tspList", tspList);
		model.addAttribute("codeList", codeList);
		model.addAttribute("basicMap", basicMap);
		model.addAttribute("etcMap", etcMap);
		model.addAttribute("containerMap", containerMap);
		model.addAttribute("sitMap", sitMap);
		model.addAttribute("otherMap", otherMap);
		
		return "admin/rate";
	}
	
	@RequestMapping(value = "/rate", method = RequestMethod.POST)
	public String ratePost(Model model, User user, @ModelAttribute Rate rate){
		System.out.println("[[[[[[[[[[[[ rate test : "+rate.getWriteYear()+" ]]]]]]]]]]]]]]]");
		List<Code> tspList = codeService.getCodeList("04");
		List<Code> codeList = codeService.getCodeList("05");
		
		invoiceService.checkNewYearRate(rate);
		
		List<String> yearList = invoiceService.getYearList();
		
		Map<String, Map<String, Map<String, Rate>>> basicMap = invoiceService.getBasicMap(rate);
		
		Map<String, Rate> etcMap = invoiceService.getEtcMap(rate);
		
		Map<String, Map<String, Rate>> containerMap = invoiceService.getContainerMap(rate);
		
		Map<String, Map<String, List<Rate>>> sitMap = invoiceService.getSitMap(rate);
		
		Map<String, Map<String, List<Rate>>> otherMap = invoiceService.getOtherMap(rate);
		
		user.setSubProcess("rate");
		
		model.addAttribute("user", user);
		model.addAttribute("yearList", yearList);
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
	
	@RequestMapping(value = "/addRateYear.json", method = RequestMethod.POST)
	@ResponseBody
	public void addRateYear(@RequestBody Map<String,String> map) {
		System.out.println("[[[[[[[[[ ADD RATE YEAR JSON CALL ]]]]]]]]]]]]");
		invoiceService.addRateYear(map.get("year"));
	}
	
	@RequestMapping(value = "/removeRateYear.json", method = RequestMethod.POST)
	@ResponseBody
	public void removeRateYear(@RequestBody Map<String,String> map) {
		System.out.println("[[[[[[[[[[ REMOVE RATE YEAR JSON CALL ]]]]]]]]]]]");
		System.out.println("[[[[[[[[[[ YEAR TEST : "+map.get("year"));
		invoiceService.removeRateYear(map);
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
		
		List<Container> containerList = adminService.getContainerList();
		
		model.addAttribute("containerList", containerList);
		
		return "admin/container";
	}	
}
