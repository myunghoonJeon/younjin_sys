package org.youngjin.net;

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
import org.youngjin.net.basic.Company;
import org.youngjin.net.basic.Pod;
import org.youngjin.net.code.Code;
import org.youngjin.net.code.CodeService;
import org.youngjin.net.login.User;
import org.youngjin.net.memorandum.Memorandum;
import org.youngjin.net.memorandum.MemorandumList;
import org.youngjin.net.memorandum.MemorandumService;
import org.youngjin.net.outbound.Addition;
import org.youngjin.net.outbound.DeliveryGbl;
import org.youngjin.net.outbound.OutboundFilter;
import org.youngjin.net.outbound.OutboundService;
import org.youngjin.net.outbound.PreMoveSurvey;
import org.youngjin.net.outbound.Weightcertificate;
import org.youngjin.net.process.GBlock;
import org.youngjin.net.upload.DownloadView;
import org.youngjin.net.util.DateUtil;

@Controller
@PreAuthorize("hasRole('ROLE_LEVEL4') or hasRole('ROLE_LEVEL3') or hasRole('ROLE_LEVEL2') or hasRole('ROLE_LEVEL1') ")
public class OutboundController {

	@Resource
	private OutboundService outboundService;

	@Resource
	private CodeService codeService;

	@Resource
	private MemorandumService memorandumService;
	
	@Resource
	private BasicService basicService;

	@RequestMapping(value = "/{process}/gblList", method = RequestMethod.GET)
	public String gblList(Model model, User user,
			@ModelAttribute OutboundFilter outboundFilter,
			@PathVariable String process) {

		outboundFilter.getPagination().setNumItems(
				outboundService.getGblListCount(outboundFilter, user));

		user.setSubProcess("gblList");

		model.addAttribute("filterMap", outboundService.getFilterMap());

		model.addAttribute("gblList",
				outboundService.getGblList(outboundFilter, user));
		model.addAttribute("gblStatus",
				outboundService.getGblStatus(outboundFilter));
		model.addAttribute("user", user);

		return process + "/gbl/list";
	}

	@RequestMapping(value = "/{process}/gblList", method = RequestMethod.POST)
	public String gblListPost(Model model, User user,
			@ModelAttribute OutboundFilter outboundFilter,
			@PathVariable String process) {

		outboundFilter.getPagination().setNumItems(
				outboundService.getGblListCount(outboundFilter, user));

		user.setSubProcess("gblList");

		model.addAttribute("filterMap", outboundService.getFilterMap());

		model.addAttribute("gblList",
				outboundService.getGblList(outboundFilter, user));
		model.addAttribute("gblStatus",
				outboundService.getGblStatus(outboundFilter));
		model.addAttribute("user", user);

		return process + "/gbl/list";
	}

	@RequestMapping(value = "/{process}/add", method = RequestMethod.GET)
	public String gblAdd(Model model, User user,
			@ModelAttribute(value = "gbl") GBL gbl, @PathVariable String process) {
		model.addAttribute("user", user);

		return process + "/gbl/add";
	}

	@RequestMapping(value = "/{process}/add", method = RequestMethod.POST)
	public String gblAddSubmit(Model model, User user,
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

			return process + "/gbl/add";
		} else {
			outboundService.insertGbl(gbl);

			model.addAttribute("outboundFilter", new OutboundFilter());
			model.addAttribute("end", true);

			return process + "/gbl/list";
		}
	}
	
	@RequestMapping(value = "/{process}/declarationList", method = RequestMethod.GET)
	public String declarationList(Model model, User user,
			@ModelAttribute(value = "gbl") GBL gbl, @PathVariable String process) {
		
		return process + "/gbl/declarationList";
	}	
	

	@RequestMapping(value = "/{process}/{seq}/modify", method = RequestMethod.GET)
	public String gblModify(Model model, User user,
			@ModelAttribute(value = "gbl") GBL gbl, @PathVariable String process, @PathVariable Integer seq) {
		
		GBL modifyGbl =  outboundService.getGbl(seq);
		
		model.addAttribute("user", user);		
		model.addAttribute("gbl", modifyGbl);
		
		return process + "/gbl/modify";
	}	
	

	@RequestMapping(value = "/{process}/gblModify.json")
	@ResponseBody
	public void gblModifySubmit(@RequestBody GBL gbl){
		outboundService.modifyGbl(gbl);
	}	


	@RequestMapping(value = "/{process}/{seq}", method = RequestMethod.GET)
	public String gblSelect(Model model, User user,
			@PathVariable String process, @PathVariable String seq) {

		model.addAttribute("process",
				outboundService.getGblProcessAndUpload(Integer.parseInt(seq)));
		model.addAttribute("seq", seq);
		model.addAttribute("fileList",
				outboundService.getGblFileList(Integer.parseInt(seq)));

		return process + "/gbl/processAndUpload";
	}
	

	@RequestMapping(value = "/{process}/{seq}/powerOfAttorney", method = RequestMethod.GET)
	public String gblPowerOfAttorney(Model model, User user,
			@PathVariable String process, @PathVariable String seq) {
		
		GBL gbl = outboundService.getGbl(Integer.parseInt(seq));
		
		Company company = basicService.getCompanyByCode("YJ");
		
		model.addAttribute("gbl", gbl);
		model.addAttribute("company", company);

		return process + "/gbl/powerOfAttorney";
	}	


	@RequestMapping(value = "/{process}/{seq}/upload", method = RequestMethod.GET)
	public String gblSelectUplaod(Model model, User user,
			@PathVariable String process, @PathVariable String seq,
			@ModelAttribute(value = "gbl") GBL gbl) {

		model.addAttribute("seq", seq);
		return process + "/gbl/upload";
	}


	@RequestMapping(value = "/{process}/{seq}/upload", method = RequestMethod.POST)
	public void gblSelectUplaodPost(Model model, User user,
			@PathVariable String process, @PathVariable String seq,
			@ModelAttribute(value = "gbl") GBL gbl) {

		model.addAttribute("seq", seq);
		outboundService.insertGblFile(gbl);
	}


	@RequestMapping(value = "/{process}/{seq}/preparation", method = RequestMethod.GET)
	public String gblPreparation(Model model, User user,
			@PathVariable String process, @PathVariable String seq) {

		model.addAttribute("seq", seq);

		return process + "/gbl/preparation";
	}


	@RequestMapping(value = "/{process}/{seq}/preMoveSurvey", method = RequestMethod.GET)
	public String gblPreMoveSurvey(
			Model model,
			User user,
			@PathVariable String process,
			@ModelAttribute(value = "preMoveSurvey") PreMoveSurvey preMoveSurvey,
			@PathVariable String seq) {

		PreMoveSurvey paramPreMoveSurvey = outboundService
				.getPreMoveSurvey(Integer.parseInt(seq));

		if (paramPreMoveSurvey == null) {
			paramPreMoveSurvey = new PreMoveSurvey();
		}

		model.addAttribute("seq", seq);
		model.addAttribute("preMoveSurvey", paramPreMoveSurvey);

		return process + "/gbl/preMoveSurvey";
	}


	@RequestMapping(value = "/{process}/{seq}/memorandumList", method = RequestMethod.GET)
	public String memorandumList(Model model, User user,
			@PathVariable String process, @PathVariable String seq){
		
		List<MemorandumList> memorandumList = memorandumService.getMemorandumAllList(seq, "outbound");

		model.addAttribute("seq", seq);
		model.addAttribute("memroandumList", memorandumList);
		
		return process + "/gbl/memorandumAllList";
	}
	

	@RequestMapping(value = "/{process}/addMemorandumAndDd619.json")
	@ResponseBody
	public MemorandumList addMemorandumAndDd619(@RequestBody MemorandumList memorandumList, User user){
		return memorandumService.addMemorandumAndDd619(memorandumList, user, "outbound");
	}
	

	@RequestMapping(value = "/{process}/deleteMemorandumAllList.json")
	@ResponseBody
	public void deleteMemorandumAllList(@RequestBody MemorandumList memorandumList){
		memorandumService.deleteMemorandumAllList(memorandumList, "outbound");
	}	


	@RequestMapping(value = "/{process}/{seq}/{memorandumSeq}/memorandum", method = RequestMethod.GET)
	public String gblMemorandum(Model model, User user,
			@PathVariable String process, @PathVariable String seq, @PathVariable Integer memorandumSeq) {

		GBL gbl = outboundService.getGbl(Integer.parseInt(seq));

		List<Code> memorandumList = codeService.getCodeList("03");
		Map<String, Memorandum> checkMemorandumMap = memorandumService
				.getMemorandumMap(seq, memorandumSeq, "outbound");

		model.addAttribute("seq", seq);
		model.addAttribute("memorandumSeq", memorandumSeq);
		model.addAttribute("gbl", gbl);
		model.addAttribute("memorandumList", memorandumList);

		if (checkMemorandumMap.get("02") != null
				&& checkMemorandumMap.get("02").getArticles() != null)
			model.addAttribute("articles", checkMemorandumMap.get("02")
					.getArticles());

		model.addAttribute("checkMemorandumMap", checkMemorandumMap);

		return process + "/gbl/memorandumList";
	}


	@RequestMapping(value = "/{process}/{seq}/{memorandumSeq}/memorandum/{type}", method = RequestMethod.GET)
	public String gblMemorandumForm(Model model, User user,
			@PathVariable String process, @PathVariable String seq, @PathVariable Integer memorandumSeq,
			@PathVariable String type) {

		GBL gbl = outboundService.getGbl(Integer.parseInt(seq));

		Code code = codeService.getCode("03", type);

		Memorandum memorandom = memorandumService.getMemorandum(seq, type, memorandumSeq, "outbound");
		
		Branch branch = basicService.getBranch(gbl.getAreaLocal());

		model.addAttribute("seq", seq);
		model.addAttribute("gbl", gbl);
		model.addAttribute("memorandumSeq", memorandumSeq);
		model.addAttribute("branch", branch);
		model.addAttribute("type", type);
		model.addAttribute("memorandum", code);
		model.addAttribute("checkMemorandum", memorandom);

		return process + "/gbl/memorandumForm";
	}


	@RequestMapping(value = "/{process}/{seq}/{memorandumSeq}/memorandum/{type}/{article}", method = RequestMethod.GET)
	public String gblMemorandumFormArticle(Model model, User user,
			@PathVariable String process, @PathVariable String seq, @PathVariable Integer memorandumSeq,
			@PathVariable String type, @PathVariable String article) {

		GBL gbl = outboundService.getGbl(Integer.parseInt(seq));

		Code code = codeService.getCode("03", type);

		Memorandum memorandom = memorandumService.getMemorandum(seq, type, memorandumSeq, "outbound");
		
		Branch branch = basicService.getBranch(gbl.getAreaLocal());

		String[] articles = article.split(",");

		model.addAttribute("seq", seq);
		model.addAttribute("gbl", gbl);
		model.addAttribute("type", type);
		model.addAttribute("memorandumSeq", memorandumSeq);
		model.addAttribute("branch", branch);
		model.addAttribute("memorandum", code);
		model.addAttribute("articleComa", article);
		model.addAttribute("articles", articles);
		model.addAttribute("checkMemorandum", memorandom);

		return process + "/gbl/memorandumForm";
	}
	

	@RequestMapping(value = "/{process}/memorandum/invoice/{article}/insert.json", method = RequestMethod.POST)
	@ResponseBody
	public void insertInvoiceMemorandum(@RequestBody Memorandum memorandum, @PathVariable String article){
		memorandumService.insertInvoiceMemorandum(memorandum, "outbound");
	}
	

	@RequestMapping(value = "/{process}/memorandum/invoice/{article}/modify.json", method = RequestMethod.POST)
	@ResponseBody
	public void modifyInvoiceMemorandum(@RequestBody Memorandum memorandum, @PathVariable String article){
		memorandumService.modifyInvoiceMemorandum(memorandum, "outbound");
	}	
	

	@RequestMapping(value = "/{process}/{seq}/{memorandumSeq}/memorandum/{type}/print", method = RequestMethod.GET)
	public String gblMemorandumPrint(Model model, User user,
			@PathVariable String process, @PathVariable String seq, @PathVariable Integer memorandumSeq,
			@PathVariable String type) {

		GBL gbl = outboundService.getGbl(Integer.parseInt(seq));

		Code code = codeService.getCode("03", type);

		Memorandum memorandom = memorandumService.getMemorandum(seq, type, memorandumSeq, "outbound");
		
		Branch branch = basicService.getBranch(gbl.getAreaLocal());

		model.addAttribute("seq", seq);
		model.addAttribute("gbl", gbl);
		model.addAttribute("memorandumSeq", memorandumSeq);
		model.addAttribute("branch", branch);
		model.addAttribute("type", type);
		model.addAttribute("memorandum", code);
		model.addAttribute("checkMemorandum", memorandom);

		return process + "/gbl/memorandumPrint";
	}	
	

	@RequestMapping(value = "/{process}/{seq}/{memorandumSeq}/memorandum/{type}/{article}/print", method = RequestMethod.GET)
	public String gblMemorandumPrintArticle(Model model, User user,
			@PathVariable String process, @PathVariable String seq, @PathVariable Integer memorandumSeq,
			@PathVariable String type, @PathVariable String article) {

		GBL gbl = outboundService.getGbl(Integer.parseInt(seq));

		Code code = codeService.getCode("03", type);

		Memorandum memorandom = memorandumService.getMemorandum(seq, type, memorandumSeq, "outbound");
		
		Branch branch = basicService.getBranch(gbl.getAreaLocal());

		String[] articles = article.split(",");

		model.addAttribute("seq", seq);
		model.addAttribute("gbl", gbl);
		model.addAttribute("memorandumSeq", memorandumSeq);
		model.addAttribute("branch", branch);
		model.addAttribute("type", type);
		model.addAttribute("memorandum", code);
		model.addAttribute("articleComa", article);
		model.addAttribute("articles", articles);
		model.addAttribute("checkMemorandum", memorandom);

		return process + "/gbl/memorandumPrint";
	}	


	@RequestMapping(value = "/{process}/{seq}/dd619List", method = RequestMethod.GET)
	public String dd619List(Model model, User user,
			@PathVariable String process, @PathVariable String seq) {

		List<Dd619> dd619List = outboundService.getDd619List(seq);

		model.addAttribute("seq", seq);
		model.addAttribute("dd619List", dd619List);

		return process + "/gbl/dd619List";
	}


	@RequestMapping(value = "/{process}/{seq}/dd619Add", method = RequestMethod.GET)
	public String dd619Add(Model model, User user,
			@PathVariable String process, @PathVariable String seq,
			@ModelAttribute Dd619 dd619) {

		model.addAttribute("user", user);
		model.addAttribute("gbl", outboundService.getGbl(Integer.parseInt(seq)));
		model.addAttribute("remarkList",
				memorandumService.getMemorandumList(seq, null, process));
		model.addAttribute("seq", seq);

		return process + "/gbl/dd619Add";
	}
	

	@RequestMapping(value = "/{process}/{seq}/{dd619Seq}/dd619Modify", method = RequestMethod.GET)
	public String dd619Modify(Model model, User user,
			@PathVariable String process, @PathVariable String seq, @PathVariable Integer dd619Seq,
			@ModelAttribute Dd619 dd619) {

		GBL gbl = outboundService.getGbl(Integer.parseInt(seq));
		
		dd619 = outboundService.getDd619ListSelectOne(dd619Seq);
		
		Branch branch = basicService.getBranch(gbl.getAreaLocal());
		Pod pod = basicService.getPod(gbl.getPod());
		
		model.addAttribute("user", user);
		model.addAttribute("gbl", gbl);
		model.addAttribute("dd619", dd619);
		model.addAttribute("remarkList",
				memorandumService.getMemorandumList(seq, dd619.getMemorandumListSeq(), process));
		model.addAttribute("remarkValue", outboundService.getRemarkValue(seq, dd619.getMemorandumListSeq()));
		model.addAttribute("seq", seq);
		model.addAttribute("branch", branch);
		model.addAttribute("pod", pod);		

		return process + "/gbl/dd619Update";
	}	


	@RequestMapping(value = "/{process}/{seq}/dd619/{listSeq}/print", method = RequestMethod.GET)
	public String dd619(Model model, User user, @PathVariable String process,
			@PathVariable String seq, @PathVariable Integer listSeq, @ModelAttribute Dd619 dd619) {
		
		System.out.println("test");

		dd619 = outboundService.getDd619ListSelectOne(listSeq);
		
		model.addAttribute("user", user);
		model.addAttribute("gbl", outboundService.getGbl(Integer.parseInt(seq)));
		model.addAttribute("dd619", dd619);
		model.addAttribute("remarkList",
				memorandumService.getMemorandumList(seq, dd619.getMemorandumListSeq(), process));
		model.addAttribute("seq", seq);

		return process + "/gbl/dd619";
	}


	@RequestMapping(value = "/{process}/{seq}/weightcertificate", method = RequestMethod.GET)
	public String weightcertificate(Model model, User user,
			@PathVariable String process, @PathVariable String seq) {

		model.addAttribute("seq", seq);
		model.addAttribute("weightcertificateList",
				outboundService.getWeightcertificateList(seq));
		
		model.addAttribute("containerList", outboundService.getContainerList());
		
		model.addAttribute("gbl", outboundService.getGbl(Integer.parseInt(seq)));

		return process + "/gbl/weightcertificate";
	}
	

	@RequestMapping(value = "/{process}/{seq}/weightcertificate/print", method = RequestMethod.GET)
	public String weightcertificatePrint(Model model, User user,
			@PathVariable String process, @PathVariable String seq) {

		model.addAttribute("seq", seq);
		model.addAttribute("weightcertificateList",
				outboundService.getWeightcertificateList(seq));
		model.addAttribute("gbl", outboundService.getGbl(Integer.parseInt(seq)));

		return process + "/gbl/weightcertificatePrint";
	}


	@RequestMapping(value = "/{process}/{seq}/additional", method = RequestMethod.GET)
	public String additionalDecideMain(Model model, User user,
			@PathVariable String process, @PathVariable String seq) {
		Map<String, Memorandum> checkMemorandumMap = memorandumService
				.getMemorandumMap(seq, null, "outbound");

		model.addAttribute("seq", seq);

		if (checkMemorandumMap.get("02") != null
				&& checkMemorandumMap.get("02").getArticles() != null) {
			String articleList[] = checkMemorandumMap.get("02").getArticles()
					.split(",");
			model.addAttribute("articles", articleList);
		}
		
		List<Addition> additionList = outboundService.getAddtionList(seq);

		model.addAttribute("checkMemorandumMap", checkMemorandumMap);
		model.addAttribute("additionList", additionList);

		return process + "/gbl/additionalDecide";
	}


	@RequestMapping(value = "/{process}/additionComplete.json", method = RequestMethod.POST)
	@ResponseBody
	public void additionalComplete(@RequestBody Addition addition) {
		outboundService.additionComplete(addition);
	}


	@RequestMapping(value = "/{process}/{seq}/weightcertificate/add.json", method = RequestMethod.POST)
	@ResponseBody
	public void gblWeightcertificateSubmit(
			@RequestBody Weightcertificate weightcertificate,
			@PathVariable String seq) {
		outboundService.insertWeightcertificate(weightcertificate);
	}


	@RequestMapping(value = "/{process}/{seq}/dd619/add.json", method = RequestMethod.POST)
	@ResponseBody
	public void gblDd619AddSubmit(@RequestBody Dd619 dd619) {
		outboundService.insertDd619(dd619);
	}
	

	@RequestMapping(value = "/{process}/{seq}/dd619/modify.json", method = RequestMethod.POST)
	@ResponseBody
	public void gblDd619ModifySubmit(@RequestBody Dd619 dd619) {
		outboundService.modifyDd619(dd619);
	}	


	@RequestMapping(value = "/{process}/{seq}/dd619/update.json", method = RequestMethod.POST)
	@ResponseBody
	public void gblDd619UpdateSubmit(@RequestBody Dd619 dd619) {
		outboundService.updateDd619(dd619);
	}


	@RequestMapping(value = "/{process}/{seq}/{memorandumSeq}/memorandum/{type}/delete.json", method = RequestMethod.POST)
	@ResponseBody
	public void gblMemorandumDelete(@PathVariable String seq,
			@PathVariable String type, @PathVariable Integer memorandumSeq) {
		memorandumService.deleteMemorandum(seq, type, memorandumSeq, "outbound");
	}


	@RequestMapping(value = "/{process}/{seq}/memorandum/memorandumInput.json", method = RequestMethod.POST)
	@ResponseBody
	public void gblMemorandumInput(@RequestBody Memorandum memorandum) {
		memorandumService.insertMemorandum(memorandum, "outbound");
	}


	@RequestMapping(value = "/{process}/{seq}/memorandum/memorandumUpdate.json", method = RequestMethod.POST)
	@ResponseBody
	public void gblMemorandumUpdate(@RequestBody Memorandum memorandum) {
		memorandumService.updateMemorandum(memorandum, "outbound");
	}


	@RequestMapping(value = "/{process}/{seq}/preMoveSurveySubmit.json", method = RequestMethod.POST)
	@ResponseBody
	public void gblPreMoveSurveySubmit(@RequestBody PreMoveSurvey preMoveSurvey) {
		outboundService.insertPreMoveSurvey(preMoveSurvey);
	}


	@RequestMapping(value = "/{process}/{seq}/preMoveSurveyEditSubmit.json", method = RequestMethod.POST)
	@ResponseBody
	public void gblPreMoveSurveyEditSubmit(
			@RequestBody PreMoveSurvey preMoveSurvey, @PathVariable String seq) {
		outboundService.updatePreMoveSurvey(preMoveSurvey);
	}


	@RequestMapping(value = "/{process}/findUsNo.json", method = RequestMethod.POST)
	@ResponseBody
	public GBlock findUsNo(@RequestBody GBlock gBlock) {

		return outboundService.findUsNo(gBlock);
	}

	/**
	 * DeliveryControl
	 */
	@PreAuthorize("hasRole('ROLE_LEVEL4')")
	@RequestMapping(value = "/{process}/delivery/main", method = RequestMethod.GET)
	public String deliveryMain(Model model, User user,
			@PathVariable String process) {

		model.addAttribute("user", user);

		user.setSubProcess("delivery");

		model.addAttribute("deliveryList", null);

		return process + "/delivery/main";
	}

	@PreAuthorize("hasRole('ROLE_LEVEL4')")
	@RequestMapping(value = "/{process}/delivery/add", method = RequestMethod.GET)
	public String deliveryAdd(Model model, User user,
			@PathVariable String process) {

		model.addAttribute("user", user);

		return process + "/delivery/add";
	}


	@RequestMapping(value = "/{process}/delivery/truckManifast", method = RequestMethod.GET)
	public String truckManifastMain(Model model, User user,
			@ModelAttribute OutboundFilter outboundFilter,
			@PathVariable String process) {

		outboundFilter.getPagination().setNumItems(
				outboundService.getTruckListCount(outboundFilter));

		model.addAttribute("filterMap", outboundService.getFilterMap());

		user.setSubProcess("truckManifast");

		model.addAttribute("truckList",
				outboundService.getTruckList(outboundFilter));
		model.addAttribute("user", user);

		return process + "/delivery/truckManifast";
	}


	@RequestMapping(value = "/{process}/delivery/truckManifast", method = RequestMethod.POST)
	public String truckManifastMainPost(Model model, User user,
			@ModelAttribute OutboundFilter outboundFilter,
			@PathVariable String process) {

		outboundFilter.getPagination().setNumItems(
				outboundService.getTruckListCount(outboundFilter));

		model.addAttribute("filterMap", outboundService.getFilterMap());

		user.setSubProcess("truckManifast");

		model.addAttribute("truckList",
				outboundService.getTruckList(outboundFilter));
		model.addAttribute("user", user);

		return process + "/delivery/truckManifast";
	}


	@RequestMapping(value = "/{process}/delivery/truckManifastGblList", method = RequestMethod.GET)
	public String truckManifastGblList(Model model, User user,
			@ModelAttribute OutboundFilter outboundFilter,
			@PathVariable String process) {

		outboundFilter.setTruckManifastFlag(true);
		outboundFilter.getPagination().setNumItems(
				outboundService.getGblListCount(outboundFilter, user));

		model.addAttribute("filterMap", outboundService.getFilterMap());

		model.addAttribute("gblList",
				outboundService.getTruckGblList(outboundFilter));
		model.addAttribute("user", user);

		return process + "/delivery/gblList";
	}


	@RequestMapping(value = "/{process}/delivery/{seq}/truckSeperateSetting", method = RequestMethod.GET)
	public String truckSeperateSetting(Model model, User user,
			@ModelAttribute OutboundFilter outboundFilter,
			@PathVariable String process, @PathVariable String seq) {

		model.addAttribute("seq", seq);
		model.addAttribute("weightCertificateList", outboundService.getWeightcertificateList(seq));
		
		return process + "/delivery/truckSeperate";
	}
	

	@RequestMapping(value = "/{process}/delivery/truckManifast/seperate.json")
	@ResponseBody
	public void gblSperateSubmit(@RequestBody Map<String, String> gblSeq) {
		outboundService.seperateGbl(gblSeq);
	}	
	
	
	

	@RequestMapping(value = "/{process}/delivery/truckManifast/merge.json")
	@ResponseBody
	public void gblMergeSubmit(@RequestBody Map<String, String> gblSeq) {
		outboundService.mergeSubmit(gblSeq);
	}	
	

	@RequestMapping(value = "/{process}/delivery/{seq}/deleteTruckManifast", method = RequestMethod.GET)
	public String deleteTruckManifast(Model model, User user,
			@ModelAttribute OutboundFilter outboundFilter,
			@PathVariable String process, @PathVariable Integer seq) {
		
		outboundService.deletManifast(seq);

		outboundFilter.getPagination().setNumItems(
				outboundService.getTruckListCount(outboundFilter));

		model.addAttribute("filterMap", outboundService.getFilterMap());

		user.setSubProcess("truckManifast");

		model.addAttribute("truckList",
				outboundService.getTruckList(outboundFilter));
		model.addAttribute("user", user);

		return process + "/delivery/truckManifast";
	}	


	@RequestMapping(value = "/{process}/delivery/truckAdd.json")
	@ResponseBody
	public void truckAdd(@RequestBody Map<String, String> gblSeq) {
		outboundService.insertTruckManifast(gblSeq);
	}
	

	@RequestMapping(value = "/{process}/delivery/{seq}/truckManifastPrint", method = RequestMethod.GET)
	public String truckManifastPrint(Model model, User user,
			@ModelAttribute OutboundFilter outboundFilter,
			@PathVariable String process, @PathVariable Integer seq) {

		List<DeliveryGbl> list = outboundService.getTruckManifastPrint(seq);
		
		Map<String, Pod> podMap = basicService.getpodMap();

		model.addAttribute("gblList", list);
		
		model.addAttribute("podMap", podMap);
		
		model.addAttribute("truckManisfast", outboundService.getTruckManifastOne(seq));
		
		model.addAttribute("user", user);

		return process + "/delivery/truckManifastPrint";
	}


	@RequestMapping(value = "/{process}/delivery/bookingList", method = RequestMethod.GET)
	public String bookingListMain(Model model, User user,
			@ModelAttribute OutboundFilter outboundFilter,
			@PathVariable String process) {

		outboundFilter.getPagination().setNumItems(
				outboundService.getBookingListCount(outboundFilter));

		model.addAttribute("filterMap", outboundService.getFilterMap());

		model.addAttribute("bookingList",
				outboundService.getBookingList(outboundFilter));

		user.setSubProcess("bookingList");

		model.addAttribute("user", user);

		return process + "/delivery/bookingList";
	}

	@PreAuthorize("hasRole('ROLE_LEVEL4')")
	@RequestMapping(value = "/{process}/delivery/bookingGblList", method = RequestMethod.GET)
	public String bookingListGblList(Model model, User user,
			@ModelAttribute OutboundFilter outboundFilter,
			@PathVariable String process) {

		outboundFilter.getPagination().setNumItems(
				outboundService.getGblListCount(outboundFilter, user));

		model.addAttribute("filterMap", outboundService.getFilterMap());

		model.addAttribute("gblList",
				outboundService.getBookingGblList(outboundFilter));
		model.addAttribute("user", user);

		return process + "/delivery/bookGblList";
	}

	@PreAuthorize("hasRole('ROLE_LEVEL4')")
	@RequestMapping(value = "/{process}/delivery/{seq}/bookingListPrint", method = RequestMethod.GET)
	public String bookingListPrint(Model model, User user,
			@ModelAttribute OutboundFilter outboundFilter,
			@PathVariable String process, @PathVariable Integer seq) {

		List<DeliveryGbl> list = outboundService.getBookingListPrint(seq);
		

		model.addAttribute("gblList", list);
		
		model.addAttribute("bookingList", outboundService.getBookingListOne(seq));
		
		model.addAttribute("user", user);

		return process + "/delivery/bookingListPrint";
	}

	@PreAuthorize("hasRole('ROLE_LEVEL4')")
	@RequestMapping(value = "/{process}/delivery/bookingAdd.json")
	@ResponseBody
	public void bookingAdd(@RequestBody Map<String, String> gblSeq) {
		outboundService.insertBookingList(gblSeq);
	}
	
	@PreAuthorize("hasRole('ROLE_LEVEL4')")
	@RequestMapping(value = "/{process}/delivery/mil/tcmd")
	public String tcmdMain(Model model, User user, @PathVariable String process, @ModelAttribute OutboundFilter outboundFilter){

		user.setSubProcess("tcmd");
		
		model.addAttribute("tcmdList", outboundService.getTcmdList());		
		model.addAttribute("user", user);
		
		return process + "/delivery/tcmd";
	}
	
	@PreAuthorize("hasRole('ROLE_LEVEL4')")
	@RequestMapping(value = "/{process}/delivery/mil/tcmdGblSetting", method=RequestMethod.GET)
	public String tcmdGblSetting(Model model, User user, @PathVariable String process, @ModelAttribute OutboundFilter outboundFilter){

		outboundFilter.getPagination().setNumItems(
				outboundService.getTcmdGblListCount(outboundFilter));

		model.addAttribute("filterMap", outboundService.getFilterMap());

		model.addAttribute("gblList",
				outboundService.getTcmdGblList(outboundFilter));
		model.addAttribute("user", user);
		
		return process + "/delivery/tcmdGblList";
	}
	
	@PreAuthorize("hasRole('ROLE_LEVEL4')")
	@RequestMapping(value = "/{process}/delivery/mil/{seq}/tcmdModify")
	public String tcmdModify(Model model, User user, @PathVariable String process, @PathVariable Integer seq, @ModelAttribute OutboundFilter outboundFilter){			
		model.addAttribute("user", user);
		
		String year = Integer.toString(DateUtil.getYear());
		String getJulianDate = year.substring(3, 4) + DateUtil.getDaysBetween(year + "0101", DateUtil.getToday("YYYYMMDD"));
		
		model.addAttribute("julianDate", getJulianDate);
		model.addAttribute("tcmd", outboundService.getTcmdContent(seq));
		model.addAttribute("gblList", outboundService.getTcmdContentGblList(seq));
		
		return process + "/delivery/tcmdModify";
	}
	
	@PreAuthorize("hasRole('ROLE_LEVEL4')")
	@RequestMapping(value = "/{process}/delivery/mil/{seq}/tcmdPrint")
	public String tcmdPrint(Model model, User user, @PathVariable String process, @PathVariable Integer seq, @ModelAttribute OutboundFilter outboundFilter){			
		model.addAttribute("user", user);
		
		String year = Integer.toString(DateUtil.getYear());
		String getJulianDate = year.substring(3, 4) + DateUtil.getDaysBetween(year + "0101", DateUtil.getToday("YYYYMMDD"));
		
		model.addAttribute("julianDate", getJulianDate);
		model.addAttribute("tcmd", outboundService.getTcmdContent(seq));
		model.addAttribute("gblList", outboundService.getTcmdContentGblList(seq));
		
		return process + "/delivery/tcmdPrint";
	}

	@PreAuthorize("hasRole('ROLE_LEVEL4')")
	@RequestMapping(value = "/{process}/tcmd/tcmdListAdd.json")
	@ResponseBody
	public void tcmdAdd(@RequestBody Map<String, String> gblSeq) {
		outboundService.insertTcmdList(gblSeq);
	}	

	@PreAuthorize("hasRole('ROLE_LEVEL4')")
	@RequestMapping(value = "/{process}/tcmdGblUpdate.json")
	@ResponseBody
	public void tcmdGblUpdate(@RequestBody Map<String, String> map) {
		outboundService.updateTcmdGbl(map);
	}	
	
	@PreAuthorize("hasRole('ROLE_LEVEL4')")
	@RequestMapping(value = "/{process}/tcmdUpdate.json")
	@ResponseBody
	public void tcmdUpdate(@RequestBody Map<String, String> map) {
		outboundService.updateTcmd(map);
	}	
	
	@RequestMapping(value = "/{process}/delivery/house")
	public String houseBl(Model model, User user, @PathVariable String process, @ModelAttribute OutboundFilter outboundFilter){			
		model.addAttribute("user", user);
		
		user.setSubProcess("house");
		
		return process + "/delivery/house";
	}
	
	/**
	 * DownLoadControl
	 */

	@Resource
	private DownloadView downloadView;


	@RequestMapping(value = "/{process}/file/{seq}/{flag}")
	public DownloadView pdfDownView(Model model, @PathVariable String seq,
			@PathVariable String process, @PathVariable String flag) {
		GBLAttachment gblAttachment = outboundService.getFileInfo(seq, flag);

		GBLAttachment attachment = new GBLAttachment();
		attachment.setFilePath(gblAttachment.getFilePath());
		attachment.setFileName(gblAttachment.getFileName() + ".pdf");

		model.addAttribute("attachment", attachment);

		return downloadView;
	}
}
