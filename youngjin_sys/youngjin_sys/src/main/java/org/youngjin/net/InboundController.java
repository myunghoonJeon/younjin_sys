package org.youngjin.net;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
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
import org.youngjin.net.code.Code;
import org.youngjin.net.code.CodeService;
import org.youngjin.net.inbound.DeclarationList;
import org.youngjin.net.inbound.InboundFilter;
import org.youngjin.net.inbound.InboundInvoice;
import org.youngjin.net.inbound.InboundService;
import org.youngjin.net.inbound.MonthlyReport;
import org.youngjin.net.inbound.OnHandList;
import org.youngjin.net.inbound.OnHandListContent;
import org.youngjin.net.inbound.OnhandSum;
import org.youngjin.net.inbound.Reweight;
import org.youngjin.net.inbound.ReweightContent;
import org.youngjin.net.inbound.TruckManifast;
import org.youngjin.net.inbound.WeightIb;
import org.youngjin.net.login.User;
import org.youngjin.net.memorandum.Memorandum;
import org.youngjin.net.memorandum.MemorandumList;
import org.youngjin.net.memorandum.MemorandumService;
import org.youngjin.net.outbound.Addition;
import org.youngjin.net.process.GBlock;
import org.youngjin.net.process.ProcessService;

@Controller
@PreAuthorize("hasRole('ROLE_LEVEL4') or hasRole('ROLE_LEVEL3') or hasRole('ROLE_LEVEL2') or hasRole('ROLE_LEVEL1') ")
public class InboundController {

	@Resource
	private InboundService inboundService;

	@Resource
	private MemorandumService memorandumService;

	@Resource
	private CodeService codeService;

	@Resource
	private BasicService basicService;
	
	@Resource
	private ProcessService processService;

	@RequestMapping(value = "/{process}/freightList", method = RequestMethod.GET)
	public String freightList(Model model, User user,
			@PathVariable String process,
			@ModelAttribute InboundFilter inboundFilter) {
		inboundFilter.getPagination().setNumItems(
				inboundService.getFreightListCount(inboundFilter, user));

		user.setSubProcess("freightList");

		model.addAttribute("filterMap", inboundService.getFilterMap());
		List<GBL> gblList = inboundService.getFreightList(inboundFilter, user);
		model.addAttribute("gblList",gblList);
		for(int i=0;i<gblList.size();i++){
			System.out.print(" [ shipper : "+gblList.get(i).getShipperName()+"==== NetWeight : "+gblList.get(i).getNetWeight()+"]");
		}
		model.addAttribute("gblStatus",
				inboundService.getGblStatus(inboundFilter));
		
		model.addAttribute("user", user);
		System.out.println("[[[ FREIGHT START LIST ]]]]]]]]");
		return process + "/freight/list";
	}
	
	@RequestMapping(value = "/{process}/freightList", method = RequestMethod.POST)
	public String freightListPost(Model model, User user,
			@PathVariable String process,
			@ModelAttribute InboundFilter inboundFilter) {

		inboundFilter.getPagination().setNumItems(
				inboundService.getFreightListCount(inboundFilter, user));

		user.setSubProcess("freightList");

		model.addAttribute("filterMap", inboundService.getFilterMap());

		model.addAttribute("gblList",
				inboundService.getFreightList(inboundFilter, user));
		model.addAttribute("gblStatus",
				inboundService.getGblStatus(inboundFilter));

		model.addAttribute("user", user);

		return process + "/freight/list";
	}
	
	@RequestMapping(value="/inbound/checkDate.json", method = RequestMethod.POST)
	@ResponseBody
	public boolean checkDate(@RequestBody Map<String,String> map){
		String date = map.get("date");
		System.out.println("````````````````````````date : "+date);
		if(date.length()==8){
			return true;
		}
		else{
			return false;
		}
	}
	
	@RequestMapping(value="/inbound/checkGblNo.json", method = RequestMethod.POST)
	@ResponseBody
	public GBL checkGblNo(@RequestBody GBL gbl){
		
		return inboundService.getGblInfoByNo(gbl);
	}
	
	@RequestMapping(value = "/{process}/weightcertificate1/delete.json", method = RequestMethod.POST)
	@ResponseBody
	public void gblWeightcertificateDelete(@RequestBody WeightIb weightcertificate) {
		inboundService.deleteWeightCertificate(weightcertificate);
	}
	
	@RequestMapping(value = "/{process}/freight/add", method = RequestMethod.GET)
	public String freightAdd(Model model, User user,
			@ModelAttribute(value = "gbl") GBL gbl, @PathVariable String process) {
		model.addAttribute("user", user);

		return process + "/freight/add";
	}	

	@RequestMapping(value = "/{process}/freight/add", method = RequestMethod.POST)
	public String freightAddSubmit(Model model, User user,
			@ModelAttribute(value = "gbl") GBL gbl,
			@PathVariable String process, BindingResult result) {

		new Validator() {

			@Override
			public void validate(Object target, Errors error) {
				GBL gbl = (GBL) target;

				if ("".equals(gbl.getNo())) {
					error.rejectValue("no", "gbl.add.empty");
				}

			}

			@Override
			public boolean supports(Class<?> clazz) {
				return GBL.class.isAssignableFrom(clazz);
			}
		}.validate(gbl, result);

		if (result.hasErrors()) {
			model.addAttribute("user", user);

			return process + "/freight/add";
		} else {
			inboundService.insertFreight(gbl);

			model.addAttribute("inboundFilter", new InboundFilter());
			model.addAttribute("end", true);

			return process + "/freight/list";
		}
	}
	
	@RequestMapping(value = "/{process}/report/{start}/{end}/monthlyReport", method = RequestMethod.GET)
	public String montlyReport(Model model, User user, @PathVariable String process, @PathVariable String start, @PathVariable String end) {
		System.out.println("start : "+start);
		System.out.println("end : "+end);
		String count="";
		
		Map m3 = getMonthlyList("3", start, end);
		Map my3 = getMonthlyListYongsan("3", start, end);
		Map mo3 = getMonthlyListOther("3", start, end);
		model.addAttribute("my3",my3);
		model.addAttribute("mo3",mo3);
		count = getMonthlySitCount("3", start, end)+"";
		model.addAttribute("m3count",count);
		model.addAttribute("m3",m3);
		
		count="";
		
		Map m4 = getMonthlyList("4", start, end);
		Map my4 = getMonthlyListYongsan("4", start, end);
		Map mo4 = getMonthlyListOther("4", start, end);
		model.addAttribute("my4",my4);
		model.addAttribute("mo4",mo4);
		count = getMonthlySitCount("4", start, end)+"";
		model.addAttribute("m4count",count);
		count="";
		model.addAttribute("m4",m4);
		
		Map m5 = getMonthlyList("5", start, end);
		Map my5= getMonthlyListYongsan("5", start, end);
		Map mo5 = getMonthlyListOther("5", start, end);
		model.addAttribute("my5",my5);
		model.addAttribute("mo5",mo5);
		count = getMonthlySitCount("5", start, end)+"";
		model.addAttribute("m5count",count);
		count="";
		model.addAttribute("m5",m5);
		
		Map m6 = getMonthlyList("6",start,end);
		count = getMonthlySitCount("6", start, end)+"";
		Map my6 = getMonthlyListYongsan("6", start, end);
		Map mo6 = getMonthlyListOther("6", start, end);
		model.addAttribute("my6",my6);
		model.addAttribute("mo6",mo6);
		model.addAttribute("m6count",count);
		count="";
		model.addAttribute("m6",m6);
		
		Map mT = getMonthlyList("T",start,end);
		Map myT = getMonthlyListYongsan("T", start, end);
		Map moT = getMonthlyListOther("T", start, end);
		model.addAttribute("myT",myT);
		model.addAttribute("moT",moT);
		count = getMonthlySitCount("T", start, end)+"";
		model.addAttribute("mTcount",count);
		count="";
		model.addAttribute("mT",mT);
		
		Map mJ = getMonthlyList("J",start,end);
		count = getMonthlySitCount("J", start, end)+"";
		Map myJ = getMonthlyListYongsan("J", start, end);
		Map moJ = getMonthlyListOther("J", start, end);
		model.addAttribute("myJ",myJ);
		model.addAttribute("moJ",moJ);
		model.addAttribute("mJcount",count);
		count="";
		model.addAttribute("mJ",mJ);
		
		Map m7 = getMonthlyList("7",start,end);
		Map my7 = getMonthlyListYongsan("7", start, end);
		Map mo7 = getMonthlyListOther("7", start, end);
		model.addAttribute("my7",my7);
		model.addAttribute("mo7",mo7);
		count = getMonthlySitCount("7", start, end)+"";
		model.addAttribute("m7count",count);
		count="";
		model.addAttribute("m7",m7);
		
		Map m8 = getMonthlyList("8",start,end);
		Map my8 = getMonthlyListYongsan("8", start, end);
		Map mo8 = getMonthlyListOther("8", start, end);
		model.addAttribute("my8",my8);
		model.addAttribute("mo8",mo8);
		count = getMonthlySitCount("8", start, end)+"";
		model.addAttribute("m8count",count);
		count="";
		model.addAttribute("m8",m8);
		
		model.addAttribute("user", user);
		return process + "/monthlyReport";
		
	}
	public String getMonthlySitCount(String code,String start,String end){
		int count=0;
		Map<String,String> map = new HashMap<String, String>();
		map.put("code", code);
		map.put("start",start);
		map.put("end", end);
		count= inboundService.getMonthlySitCount(map);
		System.out.println("CODE : "+code);
		System.out.println("COUNT : "+count);
		String tempString="";
		if(count != 0){
			tempString = count+"";
		}
		return tempString;
	}
	public Map<String,String> getMonthlyListOther(String code,String start,String end){
		Map<String,String> map = new HashMap<String, String>();
		map.put("code", code);
		map.put("start",start);
		map.put("end", end);
		List<MonthlyReport> m4 = inboundService.getMontlyListOther(map);
		System.out.println("OTHER CODE : "+code);
		String tempCode="";
		int weight=0;
		for(int i=0;i<m4.size();i++){
			MonthlyReport mr = m4.get(i);
			tempCode = mr.getMode();
			if(getCodeType(mr.getMode())){
				if(Integer.parseInt(mr.getNetWeight())<500){
					System.out.println("[[DETECTED MINIMUM ADD 500]]");
					weight+=500;
				}
				else{
					System.out.println("[[DETECTED : "+Integer.parseInt(mr.getNetWeight())+" ]]");
					weight+=Integer.parseInt(mr.getNetWeight());
				}
			}
			else{
				if(Integer.parseInt(mr.getGrossWeight())<300){
					System.out.println("[[DETECTED MINIMUM ADD 300]]");
					weight+=300;
				}
				else{
					System.out.println("[[DETECTED : "+Integer.parseInt(mr.getNetWeight())+" ]]");
					weight+=Integer.parseInt(mr.getGrossWeight());
				}
			}
		}
		Map<String,String> map2 = new HashMap<String, String>();
		System.out.println("code : "+tempCode);
		System.out.println("size : "+m4.size());
		System.out.println("weight : "+weight);
		String tempSize ="";
		String tempWeight="";
		if(m4.size() != 0){
			tempSize = m4.size()+"";
		}
		if(weight!=0){
			tempWeight=weight+"";
		}
		map2.put("size", tempSize);
		map2.put("weight", tempWeight);
		return map2;
	}
	public Map<String,String> getMonthlyListYongsan(String code,String start,String end){
		
		Map<String,String> map = new HashMap<String, String>();
		map.put("code", code);
		map.put("start",start);
		map.put("end", end);
		List<MonthlyReport> m4 = inboundService.getMontlyListYongsan(map);
		System.out.println("YONGSAN CODE : "+code);
		String tempCode="";
		int weight=0;
		for(int i=0;i<m4.size();i++){
			MonthlyReport mr = m4.get(i);
			tempCode = mr.getMode();
			if(getCodeType(mr.getMode())){
				if(Integer.parseInt(mr.getNetWeight())<500){
					System.out.println("[[DETECTED MINIMUM ADD 500]]");
					weight+=500;
				}
				else{
					System.out.println("[[DETECTED : "+Integer.parseInt(mr.getNetWeight())+" ]]");
					weight+=Integer.parseInt(mr.getNetWeight());
				}
			}
			else{
				if(Integer.parseInt(mr.getGrossWeight())<300){
					System.out.println("[[DETECTED MINIMUM ADD 300]]");
					weight+=300;
				}
				else{
					System.out.println("[[DETECTED : "+Integer.parseInt(mr.getNetWeight())+" ]]");
					weight+=Integer.parseInt(mr.getGrossWeight());
				}
			}
		}
		Map<String,String> map2 = new HashMap<String, String>();
		System.out.println("code : "+tempCode);
		System.out.println("size : "+m4.size());
		System.out.println("weight : "+weight);
		String tempSize ="";
		String tempWeight="";
		if(m4.size() != 0){
			tempSize = m4.size()+"";
		}
		if(weight!=0){
			tempWeight=weight+"";
		}
		map2.put("size", tempSize);
		map2.put("weight", tempWeight);
		return map2;
	}
	public Map<String,String> getMonthlyList(String code,String start,String end){
		Map<String,String> map = new HashMap<String, String>();
		map.put("code", code);
		map.put("start",start);
		map.put("end", end);
		List<MonthlyReport> m4 = inboundService.getMontlyList(map);
		String tempCode="";
		int weight=0;
		for(int i=0;i<m4.size();i++){
			
		}
		for(int i=0;i<m4.size();i++){
			MonthlyReport mr = m4.get(i);
			tempCode = mr.getMode();
			if(getCodeType(mr.getMode())){
				if(Integer.parseInt(mr.getNetWeight())<500){
					System.out.println("[[DETECTED MINIMUM ADD 500]]");
					weight+=500;
				}
				else{
					System.out.println("[[DETECTED : "+Integer.parseInt(mr.getNetWeight())+" ]]");
					weight+=Integer.parseInt(mr.getNetWeight());
				}
			}
			else{
				if(Integer.parseInt(mr.getGrossWeight())<300){
					System.out.println("[[DETECTED MINIMUM ADD 300]]");
					weight+=300;
				}
				else{
					System.out.println("[[DETECTED : "+Integer.parseInt(mr.getNetWeight())+" ]]");
					weight+=Integer.parseInt(mr.getGrossWeight());
				}
			}
		}
		Map<String,String> map2 = new HashMap<String, String>();
		System.out.println("code : "+tempCode);
		System.out.println("size : "+m4.size());
		System.out.println("weight : "+weight);
		String tempSize ="";
		String tempWeight="";
		if(m4.size() != 0){
			tempSize = m4.size()+"";
		}
		if(weight!=0){
			tempWeight=weight+"";
		}
		map2.put("size", tempSize);
		map2.put("weight", tempWeight);
		return map2;
		
	}
	public boolean getCodeType(String code){
		if(code.equals("4")||code.equals("3")||code.equals("5")||code.equals("T")){
			return true;
		}
		else {
			return false;
		}
	}
	@RequestMapping(value = "/{process}/freight/{seq}/update", method = RequestMethod.GET)
	public String freightUpdate(Model model, User user,
			@ModelAttribute(value = "gbl") GBL gbl, @PathVariable String process, @PathVariable Integer seq) {
		model.addAttribute("user", user);
		
		model.addAttribute("gbl", inboundService.getGbl(seq));

		return process + "/freight/add";
	}	

	@RequestMapping(value = "/{process}/freightSubmit.json")
	@ResponseBody
	public void freightUpdateSubmit(@RequestBody GBL gbl){
		inboundService.updateFreight(gbl);
	}	
	
	@RequestMapping(value = "/{process}/freightDelete.json", method = RequestMethod.POST)
	@ResponseBody
	public void gblDelete(@RequestBody GBL gbl){
		inboundService.deleteGBL(gbl);
	}


	@RequestMapping(value = "/{process}/freight/{seq}/weight", method = RequestMethod.GET)
	public String weightAdd(Model model, User user,
			@PathVariable String process, @PathVariable Integer seq,
			@ModelAttribute WeightIb weightIb) {
		model.addAttribute("user", user);

		List<WeightIb> weightList = inboundService.getWeightList(seq);
		model.addAttribute("seq", seq);
		model.addAttribute("weightList", weightList);

		return process + "/freight/weight";
	}

	@RequestMapping(value = "/{process}/freight/{seq}/weight", method = RequestMethod.POST)
	public String weightAddPost(Model model, User user,
			@PathVariable String process, @PathVariable Integer seq,
			@ModelAttribute WeightIb weightIb) {
		inboundService.insertWeightAdd(weightIb);
		model.addAttribute("user", user);
		System.out.println("[[[[[[[[[[[[[[[[[[[[[[[[ ADD WEIGHT CERTIFICATE ]]]]]]]]]]]]]]]]]]]]]");
		model.addAttribute("process", inboundService.getGblProcessAndUpload(seq));
		model.addAttribute("seq", seq);
		model.addAttribute("fileList", inboundService.getGblFileList(seq));

		model.addAttribute("addUpdateCheck", "true");

		return process + "/freight/processAndUpload";
	}

	@RequestMapping(value = "/{process}/freight/checkWeight.json")
	@ResponseBody
	public Boolean checkWeight(@RequestBody Map<String, Integer> param) {
		return inboundService.checkWeight(param);
	}

	@RequestMapping(value = "/{process}/custom/invoice")
	public String customInboundInvoice(Model model, User user,
			@PathVariable String process,
			@ModelAttribute InboundFilter inboundFilter) {

		user.setSubProcess("inboundInvoice");

		inboundFilter.getPagination().setNumItems(
				inboundService.getInboundInvoiceListCount(inboundFilter));

		model.addAttribute("inboundInvoiceList",
				inboundService.getInboundInvoiceList(inboundFilter));

		model.addAttribute("user", user);

		return process + "/custom/inboundInvoice";
	}

	@RequestMapping(value = "/{process}/custom/invoice/add", method = RequestMethod.GET)
	public String customInvoiceAdd(Model model, User user,
			@PathVariable String process,
			@ModelAttribute InboundFilter inboundFilter) {
		inboundFilter.getPagination().setNumItems(
				inboundService.getCustomInvoiceGblListCount(inboundFilter));
		model.addAttribute("filterMap", inboundService.getFilterMap());
		model.addAttribute("gblList", inboundService.getCustomInvoiceGblList(inboundFilter));

		return process + "/custom/invoiceAdd";
	}

	@RequestMapping(value = "/{process}/custom/invoice/{gblSeq}/setting", method = RequestMethod.GET)
	public String customInboundInvoiceAddSetting(Model model, User user,
			@PathVariable String process, @PathVariable Integer gblSeq) {

		model.addAttribute("gblSeq", gblSeq);

		model.addAttribute("settingValueMap",
				inboundService.getInboundInvoiceSettingMap(gblSeq));

		return process + "/custom/invoiceAddSetting";
	}

	@RequestMapping(value = "/{process}/custom/invoice/inboundInvoiceAdd.json", method = RequestMethod.POST)
	@ResponseBody
	public Integer inputCustomInboundInvoiceAddSettingAdd(
			@RequestBody InboundInvoice inboundInvoice) {
		return inboundService
				.inputCustomInboundInvoiceAddSetting(inboundInvoice);
	}

	@RequestMapping(value = "/{process}/custom/invoice/inboundInvoiceDelete.json", method = RequestMethod.POST)
	@ResponseBody
	public void inboundInvoiceDelete(
			@RequestBody Map<String, String> inboundInvoiceMap) {
		inboundService.inboundInvoiceDelete(inboundInvoiceMap);
	}

	@RequestMapping(value = "/{process}/custom/invoice/checkInboundInvoiceWeight.json", method = RequestMethod.POST)
	@ResponseBody
	public Boolean checkInboundInvoiceWeight(
			@RequestBody InboundInvoice inboundInvoice) {
		return inboundService.checkInboundInvoiceWeight(inboundInvoice);
	}

	@RequestMapping(value = "/{process}/custom/invoice/{inboundInvoiceSeq}/selectWeight", method = RequestMethod.GET)
	public String customInboundInvoiceSelectWeight(Model model, User user,
			@PathVariable String process,
			@PathVariable Integer inboundInvoiceSeq) {

		InboundInvoice inboundInvoice = inboundService
				.getInboundInvoiceAddBasicInfo(inboundInvoiceSeq);

		model.addAttribute("inboundInvoiceSeq", inboundInvoiceSeq);

		model.addAttribute("inboundInvoiceBasicInfo", inboundInvoice);

		model.addAttribute("weightList",
				inboundService.getWeightList(inboundInvoice.getGblSeq()));

		return process + "/custom/invoiceAddWeight";
	}

	@RequestMapping(value = "/{process}/custom/invoice/invoiceSelectWeightAdd.json", method = RequestMethod.POST)
	@ResponseBody
	public void inboundInvoiceWeightAdd(
			@RequestBody Map<String, String> inboundInvoiceWeightMap) {
		inboundService.inboundInvoiceWeightAdd(inboundInvoiceWeightMap);
	}

	@RequestMapping(value = "/{process}/custom/invoice/{inboundInvoiceSeq}/customPrintSelect", method = RequestMethod.GET)
	public String customPrintSelect(Model model, User user,
			@PathVariable String process,
			@PathVariable Integer inboundInvoiceSeq) {

		model.addAttribute("seq", inboundInvoiceSeq);

		return process + "/custom/customPrintSelect";
	}

	@RequestMapping(value = "/{process}/custom/invoice/{inboundInvoiceSeq}/inboundInvoicePrint", method = RequestMethod.GET)
	public String inboundInvoicePrint(Model model, User user,
			@PathVariable String process,
			@PathVariable Integer inboundInvoiceSeq) {

		InboundInvoice inboundInvoice = inboundService
				.getInboundInvoiceBasicInfo(inboundInvoiceSeq);

		model.addAttribute("inboundInvoiceBasicInfo", inboundInvoice);

		return process + "/custom/invoicePrint";
	}

	@RequestMapping(value = "/{process}/custom/invoice/{inboundInvoiceSeq}/powerOfAttornyPrint", method = RequestMethod.GET)
	public String powerOfAttornyPrint(Model model, User user,
			@PathVariable String process,
			@PathVariable Integer inboundInvoiceSeq) {

		inboundService.updateStatusCustom(inboundInvoiceSeq);

		InboundInvoice inboundInvoice = inboundService
				.getInboundInvoiceBasicInfo(inboundInvoiceSeq);

		model.addAttribute("company", basicService.getCompanyByCode("YJ"));
		model.addAttribute("inboundInvoiceBasicInfo", inboundInvoice);

		return process + "/custom/powerOfAttornyPrint";
	}

	@RequestMapping(value = "/{process}/custom/declarationList", method = RequestMethod.GET)
	public String declarationList(Model model, User user,
			@PathVariable String process,
			@ModelAttribute InboundFilter inboundFilter) {

//		user.setSubProcess("inboundInvoice");
//
//		inboundFilter.getPagination().setNumItems(
//				inboundService.getInboundInvoiceListCount(inboundFilter));
//
//		model.addAttribute("inboundInvoiceList",
//				inboundService.getInboundInvoiceList(inboundFilter));
//
//		model.addAttribute("user", user);
//
//		return process + "/custom/inboundInvoice";
		
		user.setSubProcess("declare");
		System.out.println("[[[[[[[[ declaration count:"+inboundService.getDeclarationListCount(inboundFilter)+" ]]]]]]]]]");
		inboundFilter.getPagination().setNumItems(inboundService.getDeclarationListCount(inboundFilter));
		List<DeclarationList> declarationList = inboundService.getDeclarationList(inboundFilter);
		System.out.println("[[[[[[[[ Check declaration size : "+declarationList.size()+" ]]]]]]]]]]]]]]]]]]]]");
		model.addAttribute("declarationList", declarationList);
		model.addAttribute("user", user);

		return process + "/custom/declarationList";
	}

	@RequestMapping(value = "/{process}/custom/declarationListSelect", method = RequestMethod.GET)
	public String declarationListSelect(Model model, User user,
			@PathVariable String process,
			@ModelAttribute InboundFilter inboundFilter) {
		
		user.setSubProcess("declare");
		inboundFilter.getPagination().setNumItems(inboundService.getDeclarationGblListCount(inboundFilter));
		List<InboundInvoice> inboundInvoiceList = inboundService.getInboundInvoiceDeclarationList(inboundFilter);
		model.addAttribute("filterMap", inboundService.getFilterMap());
		model.addAttribute("inboundInvoiceList", inboundInvoiceList);
		model.addAttribute("user", user);

		return process + "/custom/declarationSelect";
		//TODO
	}

	@RequestMapping(value = "/{process}/custom/invoice/declarationListSelectAdd.json", method = RequestMethod.POST)
	@ResponseBody
	public void declarationListSelectAdd(
			@RequestBody Map<String, String> inboundInvoiceMap) {
		System.out.println();
		System.out.println("[[[[[[[[[[[[[[[[[[ check Map size : "+inboundInvoiceMap.size()+" ]]]]]]]]]]]]]");
//		System.out.println("[[[[[[[[[[[[[[[[[[ check Cound : "+inboundInvoiceMap.get("count")+" ]]]]]]]]]]]]]");
		System.out.println("[[[[[[[[[[[[[[[[[[ check comma final : "+inboundInvoiceMap.get("inboundInvoiceCommaList")+" ]]]]]]]]]]]]]");
		inboundService.declarationListSelectAdd(inboundInvoiceMap);
	}

	@RequestMapping(value = "/{process}/custom/declarationListDelete.json", method = RequestMethod.POST)
	@ResponseBody
	public void declarationListDelete(
			@RequestBody Map<String, Integer> inboundInvoiceMap) {
		System.out.println("[[[[[[[[[[[[[[[[ Declaration SEQ : "+inboundInvoiceMap.get("seq")+" ]]]]]]]]]]]]]]]]");
		inboundService.declarationListDelete(inboundInvoiceMap);
	}

	@RequestMapping(value = "/{process}/custom/{seq}/declarationListContent", method = RequestMethod.GET)
	public String declarationListContent(Model model, User user,
			@PathVariable String process, @PathVariable Integer seq) {

		user.setSubProcess("declare");

		List<InboundInvoice> inboundInvoiceList = inboundService
				.getInboundInvoiceListDeclaration(seq);
		model.addAttribute("inboundInvoiceList", inboundInvoiceList);
		model.addAttribute("user", user);

		return process + "/custom/declarationListContent";
	}

	// -onHand

	@RequestMapping(value = "/{process}/onHand/onHandList", method = RequestMethod.GET)
	public String onHandList(Model model, User user,
			@PathVariable String process,
			@ModelAttribute InboundFilter inboundFilter) {
		
		inboundService.deleteEmptyOnHandList();

		user.setSubProcess("onHandList");
		inboundFilter.getPagination().setNumItems(
				inboundService.getOnHandListCount(inboundFilter));
		
		List<OnHandList> onhandlist = inboundService.getOnHandList(inboundFilter);
		List<String> spList = new ArrayList<String>();
		int flag=0;
		for(OnHandList onhand: onhandlist){
			String shipperList="[";
			List<String> list = inboundService.getOnhandListCustomerList(onhand.getSeq());
			for(int k=0;k<list.size();k++){
				shipperList += list.get(k);
				if(k!=(list.size()-1)){
					shipperList+="][";
				}
				else{
					shipperList+="]";
				}
			}
			onhand.setShipperList(shipperList);
		}
		for(int i=0;i<onhandlist.size();i++){
			System.out.println(onhandlist.get(i).getShipperList());
		}
		
		model.addAttribute("onHandList",onhandlist);
		model.addAttribute("user", user);
		return process + "/onHand/onHandList";
	}

	@RequestMapping(value = "/{process}/onHand/checkSelectOnHandList.json")
	@ResponseBody
	public boolean checkSelectOnHandList(@RequestBody Map<String, Integer> map) {
		return inboundService.checkSelectonHandList(map);
	}

	@RequestMapping(value = "/{process}/onHand/onHandListAddSetting", method = RequestMethod.GET)
	public String onHandListAddSetting(Model model, User user,
			@PathVariable String process) {

		user.setSubProcess("onHandList");
		model.addAttribute("user", user);

		return process + "/onHand/onHandListAddSetting";
	}

	@RequestMapping(value = "/{process}/onHand/onHandListAdd.json")
	@ResponseBody
	public Integer onHandListAdd(@RequestBody OnHandList onHandList) {
		return inboundService.onHandListAdd(onHandList);
	}

	@RequestMapping(value = "/{process}/onHand/{seq}/onHandListSelect", method = RequestMethod.GET)
	public String onHandListContentList(Model model, User user,
			@PathVariable String process, @PathVariable Integer seq
			) {
//		inboundFilter.getPagination().setNumItems(
//				inboundService.getOnhandGblListCount(inboundFilter));
//		System.out.println(inboundService.getOnhandGblListCount(inboundFilter));
//		System.out.println(inboundFilter.getPagination().getItemSeqBegin());
//		System.out.println(inboundFilter.getPagination().getItemSeqEnd());
//		System.out.println("filter : "+inboundFilter.getArea()+"]");
//		model.addAttribute("filterMap", inboundService.getFilterMap());
		user.setSubProcess("onHandList");
		model.addAttribute("onHandListSeq", seq);
		model.addAttribute("inboundInvoiceList",
				inboundService.getInboundInvoiceOnHandList(seq));
		model.addAttribute("user", user);

		return process + "/onHand/onHandListSelect";
	}

	@RequestMapping(value = "/{process}/onHand/checkOnHandListContentWeight.json")
	@ResponseBody
	public boolean checkSelectOnHandListContentWeight(
			@RequestBody Map<String, Integer> map) {
		return inboundService.checkSelectonHandListContentWeight(map);
	}

	@RequestMapping(value = "/{process}/onHand/{seq}/{gblSeq}/getWeight", method = RequestMethod.GET)
	public String getOnHandListContentWeightList(Model model, User user,
			@PathVariable String process, @PathVariable Integer seq,
			@PathVariable Integer gblSeq) {

		user.setSubProcess("onHandList");
		model.addAttribute("onHandListSeq", seq);
		model.addAttribute("gblSeq", gblSeq);

		model.addAttribute("weightList", inboundService.getWeightList(gblSeq));
		model.addAttribute("user", user);

		return process + "/onHand/onHandListContentWeightSelect";
	}

	@RequestMapping(value = "/{process}/onHand/{seq}/{gblSeq}/{onHandListContentSeq}/getWeight", method = RequestMethod.GET)
	public String getOnHandListContentWeightListByContentSeq(Model model,
			User user, @PathVariable String process, @PathVariable Integer seq,
			@PathVariable Integer gblSeq,
			@PathVariable Integer onHandListContentSeq) {

		user.setSubProcess("onHandList");
		model.addAttribute("onHandListSeq", seq);
		model.addAttribute("onHandListContentSeq", onHandListContentSeq);
		model.addAttribute("gblSeq", gblSeq);

		model.addAttribute("checkWeightList",
				inboundService.getCheckWeightList(onHandListContentSeq));
		model.addAttribute("weightList", inboundService.getWeightList(gblSeq));
		model.addAttribute("user", user);

		return process + "/onHand/onHandListContentWeightSelect";
	}

	@RequestMapping(value = "/{process}/onHand/onHandListContentWeightAdd.json")
	@ResponseBody
	public void onHandListContentWeightAdd(@RequestBody Map<String, String> map) {
		inboundService.onHandListContentWeightAdd(map);
	}

	@RequestMapping(value = "/{process}/onHand/onHandListContentSelectAdd.json")
	@ResponseBody
	public void onHandListContentSelectAdd(@RequestBody Map<String, String> map) {
		inboundService.onHandListContentSelectAdd(map);
	}

	@RequestMapping(value = "/{process}/onHand/onHandListDelete.json")
	@ResponseBody
	public void onHandListDelete(@RequestBody Map<String, String> map) {
		inboundService.onHandListDelete(map);
	}

	@RequestMapping(value = "/{process}/onHand/{seq}/onHandListForm", method = RequestMethod.GET)//inbound.js 에서 왔음
	public String getOnHandListForm(Model model, User user,
		@PathVariable String process, @PathVariable Integer seq) {
		List<OnHandList> ohlList = new ArrayList<OnHandList>();
		OnhandSum os = new OnhandSum();
		System.out.println("input Seq : "+seq);
		ohlList = inboundService.getOnHandListContentListForm(seq);
		int flag=0;
		for(OnHandList ohl : ohlList){
			int tempSeq = ohl.getOnHandListContent().getSeq();
			System.out.println("ONHAND LIST SEQ : " +tempSeq);
			os = inboundService.getOnhandListSum(tempSeq);
			System.out.println("piece : "+os.getPiece());
			System.out.println("gross : "+os.getSumGross());
			System.out.println("net   : "+os.getSumNet());
			System.out.println("cuft  : "+os.getSumCuft());
			ohl.getOnHandListContent().setOs(os);
			
		}
		model.addAttribute("onHandListSeq", seq);
		model.addAttribute("onHandListContentList",ohlList);
		return process + "/onHand/onHandListForm";
	}

	@RequestMapping(value = "/{process}/onHand/onHandListContentByUpdate.json")
	@ResponseBody
	public void onHandListContentByUpdate(
			@RequestBody OnHandListContent onHandListContent) {
		inboundService.onHandListContentByUpdate(onHandListContent);
	}

	@RequestMapping(value = "/{process}/onHand/{seq}/onHandListFormPrint", method = RequestMethod.GET)
	public String getOnHandListFormPrint(Model model, User user,
			@PathVariable String process, @PathVariable Integer seq) {
		List<OnHandList> ohlList = new ArrayList<OnHandList>();
		OnhandSum os = new OnhandSum();
		System.out.println("input Seq : "+seq);
		ohlList = inboundService.getOnHandListContentListForm(seq);
		for(OnHandList ohl : ohlList){
			int tempSeq = ohl.getOnHandListContent().getSeq();
			System.out.println("ONHAND LIST SEQ : " +tempSeq);
			os = inboundService.getOnhandListSum(tempSeq);
			System.out.println("piece : "+os.getPiece());
			System.out.println("gross : "+os.getSumGross());
			System.out.println("net   : "+os.getSumNet());
			System.out.println("cuft  : "+os.getSumCuft());
			ohl.getOnHandListContent().setOs(os);
		}
		model.addAttribute("onHandListContentList",ohlList);
//		model.addAttribute("basicBranch", basicService.getBranch());
		return process + "/onHand/onHandListPrint";
	}

	@RequestMapping(value = "/{process}/onHand/truckManifast", method = RequestMethod.GET)
	public String truckManifastInbound(Model model, User user,
			@PathVariable String process,
			@ModelAttribute InboundFilter inboundFilter) {
		
		inboundService.deleteTruckManifastEmptyTruck();

		user.setSubProcess("truck");
		
		inboundFilter.getPagination().setNumItems(
				inboundService.getTruckManifastListCount(inboundFilter));
		
		List<TruckManifast> truckManifastList = inboundService
				.getTruckManifastList(inboundFilter);

		model.addAttribute("truckList", truckManifastList);

		model.addAttribute("user", user);

		return process + "/onHand/truckManifast";
	}

	@RequestMapping(value = "/{process}/onHand/truckManifastOnhandList", method = RequestMethod.GET)
	public String truckManifastOnhandList(Model model, User user,
			@PathVariable String process,
			@ModelAttribute InboundFilter inboundFilter) {
		inboundFilter.getPagination().setNumItems(
				inboundService.getTruckManifastListCount(inboundFilter));
		model.addAttribute("filterMap", inboundService.getFilterMap());
		List<GBL> onHandListes = inboundService.getOnHandGBLList(inboundFilter);
		model.addAttribute("onHandGblList", onHandListes);
		return process + "/onHand/truckManifastOnhandList";
	}
	
	@RequestMapping(value = "/{process}/onHand/{seq}/truckManifastForm", method = RequestMethod.GET)
	public String truckManifastFormInbound(Model model, User user,
			@PathVariable String process,
			@PathVariable Integer seq) {
		
		model.addAttribute("truckInfo", inboundService.getTruckBasicInfo(seq));

		model.addAttribute("truckList", inboundService.getTruckManifastContentList(seq));

		model.addAttribute("user", user);

		return process + "/onHand/truckManifastForm";
	}	

	@RequestMapping(value = "/{process}/onHand/inputTruckManifast.json", method = RequestMethod.POST)
	@ResponseBody
	public void inputTruckManifast(@RequestBody Map<String, String> resultMap) {
		inboundService.insertTruckManifast(resultMap);
	}

	@RequestMapping(value = "/{process}/onHand/deleteTruckManifast.json", method = RequestMethod.POST)
	@ResponseBody
	public void deleteTruckManifast(@RequestBody Map<String, String> resultMap) {
		inboundService.deleteTruckManifast(resultMap);
	}

	// -process

	@RequestMapping(value = "/{process}/freight/{seq}", method = RequestMethod.GET)
	public String gblSelect(Model model, User user,
			@PathVariable String process, @PathVariable Integer seq) {

		model.addAttribute("process",
				inboundService.getGblProcessAndUpload(seq));
		model.addAttribute("seq", seq);
		model.addAttribute("fileList", inboundService.getGblFileList(seq));
		GBL gbl = inboundService.getGbl(seq);
		model.addAttribute("gblInform",gbl);
		return process + "/freight/processAndUpload";
	}

	@RequestMapping(value = "/{process}/freight/{seq}/delivery", method = RequestMethod.GET)
	public String gblDelivery(Model model, User user,
			@PathVariable String process, @PathVariable String seq,
			@ModelAttribute(value = "gbl") GBL gbl) {

		model.addAttribute("seq", seq);

		return process + "/freight/delivery";
	}

	@RequestMapping(value = "/{process}/freight/{seq}/damage", method = RequestMethod.GET)
	public String damage(Model model, User user,
			@PathVariable String process, @PathVariable Integer seq) {

		//model.addAttribute("seq", seq);
		
		GBL gbl = inboundService.getGbl(seq);
		model.addAttribute("gbl", gbl);
		
		Carrier carrier = basicService.getCarrier(gbl.getTsp());
		model.addAttribute("carrier", carrier);		

		return process + "/freight/damage_at_delivery";
	}

	@RequestMapping(value = "/{process}/freight/{seq}/memorandumList", method = RequestMethod.GET)
	public String memorandumList(Model model, User user,
			@PathVariable String process, @PathVariable String seq) {

		List<MemorandumList> memorandumList = memorandumService
				.getMemorandumAllList(seq, "inbound");

		model.addAttribute("seq", seq);
		model.addAttribute("memroandumList", memorandumList);

		return process + "/freight/memorandumAllList";
	}

	@RequestMapping(value = "/{process}/freight/{seq}/upload", method = RequestMethod.GET)
	public String gblSelectUplaod(Model model, User user,
			@PathVariable String process, @PathVariable String seq,
			@ModelAttribute(value = "gbl") GBL gbl) {

		model.addAttribute("seq", seq);
		return process + "/freight/upload";
	}

	@RequestMapping(value = "/{process}/freight/{seq}/upload/{check}", method = RequestMethod.GET)
	public String checkSelectUplaod(Model model, User user,
			@PathVariable String process, @PathVariable String seq, @PathVariable String check,
			@ModelAttribute(value = "gbl") GBL gbl) {
		
		model.addAttribute("check", check);
		
		model.addAttribute("seq", seq);
		
		return process + "/freight/upload";
	}

	@RequestMapping(value = "/{process}/freight/{seq}/upload", method = RequestMethod.POST)
	public String gblSelectUplaodPost(Model model, User user,
			@PathVariable String process, @PathVariable String seq,
			@ModelAttribute(value = "gbl") GBL gbl) {

		model.addAttribute("seq", seq);
		inboundService.insertGblFile(gbl);
		
		return "redirect:/" + process + "/freight/" + seq + "/upload/check";
	}

	@RequestMapping(value = "/{process}/freight/{seq}/bl", method = RequestMethod.GET)
	public String blSeperate(Model model, User user,
			@PathVariable String process, @PathVariable String seq,
			@ModelAttribute(value = "gbl") GBL gbl) {

		model.addAttribute("seq", seq);
		return process + "/freight/bl";
	}

	@RequestMapping(value = "/{process}/freight/addMemorandumAndDd619.json")
	@ResponseBody
	public MemorandumList addMemorandumAndDd619(
			@RequestBody MemorandumList memorandumList, User user) {
		return memorandumService.addMemorandumAndDd619(memorandumList, user,
				"inbound");
	}

	@RequestMapping(value = "/{process}/freight/deleteMemorandumAllList.json")
	@ResponseBody
	public void deleteMemorandumAllList(
			@RequestBody MemorandumList memorandumList) {
		memorandumService.deleteMemorandumAllList(memorandumList, "inbound");
	}

	@RequestMapping(value = "/{process}/freight/{seq}/{memorandumSeq}/memorandum", method = RequestMethod.GET)
	public String gblMemorandum(Model model, User user,
			@PathVariable String process, @PathVariable String seq,
			@PathVariable Integer memorandumSeq) {

		GBL gbl = inboundService.getGbl(Integer.parseInt(seq));

		List<Code> memorandumList = codeService.getCodeList("06");
		Map<String, Memorandum> checkMemorandumMap = memorandumService
				.getMemorandumMap(seq, memorandumSeq, "inbound");

		Branch branch = basicService.getBranch(gbl.getAreaLocal());

		model.addAttribute("seq", seq);
		model.addAttribute("memorandumSeq", memorandumSeq);
		model.addAttribute("gbl", gbl);
		model.addAttribute("branch", branch);
		model.addAttribute("memorandumList", memorandumList);

		if (checkMemorandumMap.get("02") != null&& checkMemorandumMap.get("02").getArticles() != null){
				model.addAttribute("articles", checkMemorandumMap.get("02").getArticles());
		}
		
		System.out.println("JMH TEST : "+checkMemorandumMap.size()+"@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		model.addAttribute("checkMemorandumMap", checkMemorandumMap);

		return process + "/freight/memorandumList";
	}
	
	
	
	@RequestMapping(value = "/{process}/freight/{seq}/{memorandumSeq}/memorandum/{type}", method = RequestMethod.GET)
	public String gblMemorandumForm(Model model, User user,
			@PathVariable String process, @PathVariable String seq,
			@PathVariable Integer memorandumSeq, @PathVariable String type) {

		GBL gbl = inboundService.getGbl(Integer.parseInt(seq));

		Code code = codeService.getCode("03", type);

		Memorandum memorandom = memorandumService.getMemorandum(seq, type,
				memorandumSeq, "inbound");

		Branch branch = basicService.getBranch(gbl.getAreaLocal());

		model.addAttribute("seq", seq);
		model.addAttribute("gbl", gbl);
		model.addAttribute("memorandumSeq", memorandumSeq);
		model.addAttribute("type", type);
		model.addAttribute("memorandum", code);
		model.addAttribute("branch", branch);
		model.addAttribute("checkMemorandum", memorandom);

		return process + "/freight/memorandumForm";
	}

	@RequestMapping(value = "/{process}/freight/{seq}/{memorandumSeq}/memorandum/{type}/{article}", method = RequestMethod.GET)
	public String gblMemorandumFormArticle(Model model, User user,
			@PathVariable String process, @PathVariable String seq,
			@PathVariable Integer memorandumSeq, @PathVariable String type,
			@PathVariable String article) {

		GBL gbl = inboundService.getGbl(Integer.parseInt(seq));

		Code code = codeService.getCode("03", type);

		Memorandum memorandom = memorandumService.getMemorandum(seq, type,
				memorandumSeq, "inbound");

		Branch branch = basicService.getBranch(gbl.getAreaLocal());

		String[] articles = article.split(",");

		model.addAttribute("seq", seq);
		model.addAttribute("gbl", gbl);
		model.addAttribute("type", type);
		model.addAttribute("memorandumSeq", memorandumSeq);
		model.addAttribute("memorandum", code);
		model.addAttribute("articleComa", article);
		model.addAttribute("articles", articles);
		model.addAttribute("branch", branch);
		model.addAttribute("checkMemorandum", memorandom);

		return process + "/freight/memorandumForm";
	}

	@RequestMapping(value = "/{process}/freight/{seq}/memorandum/memorandumInput.json", method = RequestMethod.POST)
	@ResponseBody
	public void gblMemorandumInput(@RequestBody Memorandum memorandum) {
		memorandumService.insertMemorandum(memorandum, "inbound");
	}

	@RequestMapping(value = "/{process}/freight/{seq}/memorandum/memorandumUpdate.json", method = RequestMethod.POST)
	@ResponseBody
	public void gblMemorandumUpdate(@RequestBody Memorandum memorandum) {
		memorandumService.updateMemorandum(memorandum, "inbound");
	}

	@RequestMapping(value = "/{process}/freight/{seq}/{memorandumSeq}/memorandum/{type}/delete.json", method = RequestMethod.POST)
	@ResponseBody
	public void gblMemorandumDelete(@PathVariable String seq,
			@PathVariable String type, @PathVariable Integer memorandumSeq) {
		memorandumService.deleteMemorandum(seq, type, memorandumSeq, "inbound");
	}

	@RequestMapping(value = "/{process}/freight/memorandum/invoice/{article}/insert.json", method = RequestMethod.POST)
	@ResponseBody
	public void insertInvoiceMemorandum(@RequestBody Memorandum memorandum,
			@PathVariable String article) {
		memorandumService.insertInvoiceMemorandum(memorandum, "inbound");
	}
	@RequestMapping(value = "/{process}/freight/memorandum/invoice/insertMemorandumSit.json", method = RequestMethod.POST)
	@ResponseBody
	public void insertMemorandumSit(@RequestBody Memorandum memorandum) {
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@ CALL MEMORANDUM SIT INPUT ");
		memorandumService.insertMemorandumSitWeight(memorandum, "inbound");
	}
	@RequestMapping(value = "/{process}/freight/memorandum/invoice/sitModify.json", method = RequestMethod.POST)
	@ResponseBody
	public void modifySitMemorandum(@RequestBody Memorandum memorandum) {
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@ CALL MEMORANDUM SIT MODIFY ");
		memorandumService.modifyInvoiceSitMemorandumIb(memorandum, "inbound");
	}
	@RequestMapping(value = "/{process}/freight/memorandum/invoice/{article}/modify.json", method = RequestMethod.POST)
	@ResponseBody
	public void modifyInvoiceMemorandum(@RequestBody Memorandum memorandum,
			@PathVariable String article) {
		memorandumService.modifyInvoiceMemorandum(memorandum, "inbound");
	}
	@RequestMapping(value = "/{process}/freight/{seq}/{memorandumSeq}/memorandum/{type}/print", method = RequestMethod.GET)
	public String gblMemorandumPrint(Model model, User user,
			@PathVariable String process, @PathVariable String seq,
			@PathVariable Integer memorandumSeq, @PathVariable String type) {

		GBL gbl = inboundService.getGbl(Integer.parseInt(seq));

		Code code = codeService.getCode("03", type);

		Memorandum memorandom = memorandumService.getMemorandum(seq, type,
				memorandumSeq, "inbound");

		Branch branch = basicService.getBranch(gbl.getAreaLocal());

		model.addAttribute("seq", seq);
		model.addAttribute("gbl", gbl);
		model.addAttribute("memorandumSeq", memorandumSeq);
		model.addAttribute("type", type);
		model.addAttribute("memorandum", code);
		model.addAttribute("branch", branch);
		model.addAttribute("checkMemorandum", memorandom);

		return process + "/freight/memorandumPrint";
	}

	@RequestMapping(value = "/{process}/freight/{seq}/{memorandumSeq}/memorandum/{type}/{article}/print", method = RequestMethod.GET)
	public String gblMemorandumPrintArticle(Model model, User user,
			@PathVariable String process, @PathVariable String seq,
			@PathVariable Integer memorandumSeq, @PathVariable String type,
			@PathVariable String article) {

		GBL gbl = inboundService.getGbl(Integer.parseInt(seq));

		Code code = codeService.getCode("03", type);

		Memorandum memorandom = memorandumService.getMemorandum(seq, type,
				memorandumSeq, "inbound");

		Branch branch = basicService.getBranch(gbl.getAreaLocal());

		String[] articles = article.split(",");

		model.addAttribute("seq", seq);
		model.addAttribute("gbl", gbl);
		model.addAttribute("memorandumSeq", memorandumSeq);
		model.addAttribute("type", type);
		model.addAttribute("memorandum", code);
		model.addAttribute("branch", branch);
		model.addAttribute("articleComa", article);
		model.addAttribute("articles", articles);
		model.addAttribute("checkMemorandum", memorandom);

		return process + "/freight/memorandumPrint";
	}

	@RequestMapping(value = "/{process}/freight/{seq}/dd619List", method = RequestMethod.GET)
	public String dd619List(Model model, User user,
			@PathVariable String process, @PathVariable String seq) {

		List<Dd619> dd619List = inboundService.getDd619List(seq);

		model.addAttribute("seq", seq);
		model.addAttribute("dd619List", dd619List);

		return process + "/freight/dd619List";
	}

	@RequestMapping(value = "/{process}/freight/{seq}/{dd619Seq}/dd619Modify", method = RequestMethod.GET)
	public String dd619Modify(Model model, User user,
			@PathVariable String process, @PathVariable String seq,
			@PathVariable Integer dd619Seq, @ModelAttribute Dd619 dd619) {

		GBL gbl = inboundService.getGbl(Integer.parseInt(seq));
		dd619 = inboundService.getDd619ListSelectOne(dd619Seq);
		
		Branch branch = basicService.getBranch(gbl.getAreaLocal());
		Company company = basicService.getCompanyByCode("YJ");
		GBlock gblock = processService.getGBlockByGbloc(gbl.getGbloc());
		
		model.addAttribute("branch", branch);
		model.addAttribute("company", company);
		model.addAttribute("gblock", gblock);

		model.addAttribute("user", user);
		model.addAttribute("gbl", gbl);
		model.addAttribute("dd619", dd619);
		model.addAttribute(
				"remarkList",
				memorandumService.getMemorandumList(seq,
						dd619.getMemorandumListSeq(), process));
		model.addAttribute("remarkValue", inboundService.getRemarkValue(seq,
				dd619.getMemorandumListSeq()));
		model.addAttribute("seq", seq);

		return process + "/freight/dd619Update";
	}

	@RequestMapping(value = "/{process}/freight/{seq}/dd619/modify.json", method = RequestMethod.POST)
	@ResponseBody
	public void gblDd619ModifySubmit(@RequestBody Dd619 dd619) {
		inboundService.modifyDd619(dd619);
	}

	@RequestMapping(value = "/{process}/freight/{seq}/dd619/{listSeq}/print", method = RequestMethod.GET)
	public String dd619(Model model, User user, @PathVariable String process,
			@PathVariable String seq, @PathVariable Integer listSeq,
			@ModelAttribute Dd619 dd619) {

		GBL gbl = inboundService.getGbl(Integer.parseInt(seq));
		
		dd619 = inboundService.getDd619ListSelectOne(listSeq);
		Branch branch = basicService.getBranch(gbl.getAreaLocal());
		Company company = basicService.getCompanyByCode("YJ");
		GBlock gblock = processService.getGBlockByGbloc(gbl.getGbloc());
		Map<String, Memorandum> memorandumMap = memorandumService.getMemorandumMap(seq,dd619.getMemorandumListSeq(), process);
		String sitStartDate=null;
		String sitEndDate=null;
		if(memorandumMap.get("06")!=null){
			System.out.println("SIT START NOT NULL");
			sitStartDate = memorandumMap.get("06").getSitStartDate();
			System.out.println("SIT START DATE : "+sitStartDate);
		}
		if(memorandumMap.get("07")!=null){
			System.out.println("SIT START NOT NULL");
			sitEndDate = memorandumMap.get("07").getSitEndDate();
		}
		String diffSitYearJulian="";
		System.out.println("========================================");
		if(sitStartDate!=null && sitEndDate!=null){
			System.out.println("SIT START DATE : "+sitStartDate);
			System.out.println("SIT End DATE : "+sitEndDate);
			if(sitStartDate.length()==8 && sitEndDate.length()==8){
				int flag = getDifferenceJulian(sitStartDate, sitEndDate);
				if(flag!=-1){
					diffSitYearJulian = flag+"";
					System.out.println("DIFF SIT JULIAN STRING CHANGE : "+flag);
				}
				else{
					
				}
			}
		}
		else{
		}
		System.out.println("========================================");
		model.addAttribute("diffSitYearJulian", diffSitYearJulian);
		model.addAttribute("branch", branch);
		model.addAttribute("company", company);
		model.addAttribute("gblock", gblock);
		
		model.addAttribute("user", user);
		model.addAttribute("gbl", gbl);
		model.addAttribute("weight", inboundService.getWeightTotal(seq));
		model.addAttribute("dd619", dd619);
		model.addAttribute("memorandumMap",memorandumMap);
		model.addAttribute("seq", seq);

		return process + "/freight/dd619";
	}
	public int getDifferenceJulian(String date1,String date2){
		int result=-1;
		int startJulian = getJulian(date1);
		int endJulian = getJulian(date2);
		int startYear = Integer.parseInt(date1.substring(0,4));
		int endYear = Integer.parseInt(date2.substring(0,4));
		if(startYear != endYear){
			System.out.println("ANOTHER YEAR "+startYear+" and "+endYear);
			if(getYoonYear(startYear)){
				System.out.println("YOON NYUN");
				result = 366-startJulian+endJulian+1;
			}
			else{
				System.out.println("PYUNG NYUN");
				result = 365-startJulian+endJulian+1;
			}
		}
		else{
			System.out.println("SAME YEAR"+startYear+" and "+endYear);
			System.out.println("DONT TOUCH DATE");
			result = -1;
		}
		return result;
	}
	public int getJulian(String date){
		Calendar cal = Calendar.getInstance();
		int year = Integer.parseInt(date.substring(0,4));
		int month = Integer.parseInt(date.substring(4,6));
		int day = Integer.parseInt(date.substring(6,8));
		System.out.println(year+"-"+month+"-"+day);
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month-1);
		cal.set(Calendar.DATE, day);
		int julian = cal.get(Calendar.DAY_OF_YEAR);
		System.out.println("DATE : "+date+" to JULIAN : "+julian);
		return julian;
	}
	public boolean getYoonYear(int year){
		boolean result=true;
		 if((year % 4 ==0 && year % 100 != 0)||(year % 400 == 0)) {
			result = true;
		 }
		 else{
			 result = false;
		 }
		return result;
	}
	@RequestMapping(value = "/{process}/freight/{seq}/additional", method = RequestMethod.GET)
	public String additionalDecideMain(Model model, User user,
			@PathVariable String process, @PathVariable String seq) {
		Map<String, Memorandum> checkMemorandumMap = memorandumService
				.getMemorandumMap(seq, null, "inbound");

		model.addAttribute("seq", seq);

		if (checkMemorandumMap.get("02") != null
				&& checkMemorandumMap.get("02").getArticles() != null) {
			String articleList[] = checkMemorandumMap.get("02").getArticles()
					.split(",");
			model.addAttribute("articles", articleList);
		}

		List<Addition> additionList = inboundService.getAddtionList(seq);

		model.addAttribute("checkMemorandumMap", checkMemorandumMap);
		model.addAttribute("additionList", additionList);

		return process + "/freight/additionalDecide";
	}

	@RequestMapping(value = "/{process}/freight/additionComplete.json", method = RequestMethod.POST)
	@ResponseBody
	public void additionalComplete(@RequestBody Addition addition) {
		inboundService.additionComplete(addition);
	}

	@RequestMapping(value = "{process}/reweight", method = RequestMethod.GET)
	public String reweightMain(Model model, User user,
			@PathVariable String process,
			@ModelAttribute InboundFilter inboundFilter) {

		user.setSubProcess("reweight");

		model.addAttribute("user", user);
		
		List<Reweight> reweightList = inboundService.getReWeightList(inboundFilter);
		
		model.addAttribute("reweightList", reweightList);

		return process + "/reweight/main";
	}
	
	@RequestMapping(value = "{process}/report", method = RequestMethod.GET)
	public String report(Model model, User user,
			@PathVariable String process,
			@ModelAttribute InboundFilter inboundFilter) {
		System.out.println("[[[[ CALL REPORT CONTROLLER1 ]]]]");
		user.setSubProcess("report");
		model.addAttribute("user", user);
		return process + "/report";
	}

	
	@RequestMapping(value = "{process}/reweight/reweightReport/{seq}", method=RequestMethod.GET)
	public String reweightReportMain(Model model, User user, @PathVariable String process, @PathVariable Integer seq){
		
		Reweight reweight = inboundService.getReweight(seq);
		
		model.addAttribute("reweightBasicInfo", reweight);
		
		List<ReweightContent> reweightList = inboundService.getReweightListBySeq(seq);
		
		model.addAttribute("reweightReportingList", reweightList);
		
		return process + "/reweight/reweightReport";
	}
	
	@RequestMapping(value = "{process}/reweight/gblSelect", method = RequestMethod.GET)
	public String reweightGblSelect(Model model, User user,
			@PathVariable String process,
			@ModelAttribute InboundFilter inboundFilter){
		
		List<ReweightContent> reweightGblList = inboundService.getReweightGblList();
		
		model.addAttribute("reweightGblList", reweightGblList);
		
		return process + "/reweight/reweightGblList";
	}
	
	@RequestMapping(value = "/{process}/reweight/add.json", method = RequestMethod.POST)
	@ResponseBody
	public void reweightAdd(@RequestBody Map<String, String> paramMap) {
		inboundService.reweightAdd(paramMap);
	}
	
	@RequestMapping(value = "/{process}/reweight/delete.json", method = RequestMethod.POST)
	@ResponseBody
	public void reweightDelete(@RequestBody Reweight reweight) {
		inboundService.reweightDelete(reweight);
	}
}
