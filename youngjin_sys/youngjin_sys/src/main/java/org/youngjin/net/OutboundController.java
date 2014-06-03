package org.youngjin.net;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

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
import org.youngjin.net.basic.Pod;
import org.youngjin.net.code.Code;
import org.youngjin.net.code.CodeService;
import org.youngjin.net.login.User;
import org.youngjin.net.memorandum.Memorandum;
import org.youngjin.net.memorandum.MemorandumList;
import org.youngjin.net.memorandum.MemorandumService;
import org.youngjin.net.outbound.Addition;
import org.youngjin.net.outbound.BookingList;
import org.youngjin.net.outbound.DeliveryGbl;
import org.youngjin.net.outbound.OutboundFilter;
import org.youngjin.net.outbound.OutboundService;
import org.youngjin.net.outbound.PowerOfAttornyList;
import org.youngjin.net.outbound.PreMoveSurvey;
import org.youngjin.net.outbound.Tcmd;
import org.youngjin.net.outbound.TruckManifast;
import org.youngjin.net.outbound.Weightcertificate;
import org.youngjin.net.process.GBlock;
import org.youngjin.net.process.ProcessService;
import org.youngjin.net.upload.DownloadView;
import org.youngjin.net.util.DateUtil;

@Controller
@PreAuthorize("hasRole('ROLE_LEVEL4') or hasRole('ROLE_LEVEL3') or hasRole('ROLE_LEVEL2') or hasRole('ROLE_LEVEL1') or hasRole('ROLE_LEVEL3') or hasRole('ROLE_LEVEL2') or hasRole('ROLE_LEVEL1') ")
public class OutboundController {

	@Resource
	private OutboundService outboundService;

	@Resource
	private CodeService codeService;

	@Resource
	private MemorandumService memorandumService;
	
	@Resource
	private BasicService basicService;
	
	@Resource
	private ProcessService processService;

	@RequestMapping(value = "/{process}/gblList", method = RequestMethod.GET)
	public String gblList(Model model, User user,
			@ModelAttribute OutboundFilter outboundFilter,
			@PathVariable String process) {
		
		int count = outboundService.getGblListCount(outboundFilter, user);
		
		outboundFilter.getPagination().setNumItems(count);
		
		user.setSubProcess("gblList");
		
		model.addAttribute("filterMap", outboundService.getFilterMap());
		
		List<GBL> gblList = outboundService.getGblList(outboundFilter, user);
		model.addAttribute("gblList",gblList);
		
		model.addAttribute("gblStatus",
				outboundService.getGblStatus(outboundFilter));
		model.addAttribute("user", user);

		return process + "/gbl/list";
	}
	@RequestMapping(value = "/{process}/addRateYear.json", method = RequestMethod.POST)
	@ResponseBody
	public void addRateYear(@RequestBody Map<String,String> map) {
		System.out.println("gg : "+map.get("year"));
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
	
	@RequestMapping(value="/outbound/checkGblNo.json", method = RequestMethod.POST)
	@ResponseBody
	public GBL checkGblNo(@RequestBody GBL gbl){
		
		return outboundService.getGblInfoByNo(gbl);
	}
	
	@RequestMapping(value = "/{process}/add", method = RequestMethod.GET)
	public String gblAdd(Model model, User user,
			@ModelAttribute(value = "gbl") GBL gbl, @PathVariable String process) {
		model.addAttribute("user", user);

		return process + "/gbl/add";
	}
	
	@RequestMapping(value = "/{process}/gblDelete.json", method = RequestMethod.POST)
	@ResponseBody
	public void gblDelete(@RequestBody GBL gbl){
		outboundService.deleteGBL(gbl);
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

	@RequestMapping(value = "/{process}/{seq}/modify", method = RequestMethod.GET)
	public String gblModify(Model model, User user,
			@ModelAttribute(value = "gbl") GBL gbl, @PathVariable String process, @PathVariable Integer seq) {
		
		GBL modifyGbl =  outboundService.getGbl(seq);
		String td= outboundService.getGblTruckDate(modifyGbl.getSeq()); 
		modifyGbl.setTruckmanifastDate(td);
		
		System.out.println("[[[[[[[[[[[[[[[[[ truck date : "+modifyGbl.getTruckmanifastDate()+"]]]]]]]]]]]]]]]]]]]]]]]]]");
		model.addAttribute("user", user);		
		model.addAttribute("gbl", modifyGbl);
		
		return process + "/gbl/modify";
	}	
	

	@RequestMapping(value = "/{process}/gblModify.json")
	@ResponseBody
	public void gblModifySubmit(@RequestBody GBL gbl){
		outboundService.modifyGbl(gbl);
	}	
	@RequestMapping(value= "/{process}/bookinglistUpdate.json")
	@ResponseBody
	public void bookinglistUpdate(@RequestBody Map map){
		System.out.println("***************BOOK SEQ : "+map.get("bookSeq"));
		System.out.println("***************COLUMN : "+map.get("column"));
		System.out.println("***************VALUE : "+map.get("value"));
		outboundService.updateBookingListUpdate(map);
	}
	@RequestMapping(value="/{process}/test.json", method = RequestMethod.POST)
	@ResponseBody
	public void test(Model model,@PathVariable String process, @RequestBody Map map){
		String input = map.get("value")+"";
		System.out.println("input : "+input);
		String result = test2(input)+"kk";
		System.out.println("rrr : "+result+"??");
		model.addAttribute("data", result);
	}
	
	@RequestMapping(value= "/{process}/bookinglistUpdate1.json")
	@ResponseBody
	public void bookinglistUpdate1(@RequestBody Map map){
		System.out.println(map.get("value"));
	}
	
	@RequestMapping(value = "/{process}/{seq}", method = RequestMethod.GET)
	public String gblSelect(Model model, User user,
			@PathVariable String process, @PathVariable String seq) {

		model.addAttribute("process",
				outboundService.getGblProcessAndUpload(Integer.parseInt(seq)));
		model.addAttribute("seq", seq);
		model.addAttribute("fileList",
				outboundService.getGblFileList(Integer.parseInt(seq)));
		int tempSeq = Integer.parseInt(seq);
		GBL gbl = outboundService.getGbl(tempSeq);
		model.addAttribute("gblInform",gbl);
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

	@RequestMapping(value = "/{process}/{seq}/upload/{check}", method = RequestMethod.GET)
	public String checkGblSelectUplaod(Model model, User user,
			@PathVariable String process, @PathVariable String seq, @PathVariable String check,
			@ModelAttribute(value = "gbl") GBL gbl) {

		model.addAttribute("check", check);
		
		model.addAttribute("seq", seq);
		return process + "/gbl/upload";
	}


	@RequestMapping(value = "/{process}/{seq}/upload", method = RequestMethod.POST)
	public String gblSelectUplaodPost(Model model, User user,
			@PathVariable String process, @PathVariable String seq,
			@ModelAttribute(value = "gbl") GBL gbl) {

		model.addAttribute("seq", seq);
		outboundService.insertGblFile(gbl);
		
		return "redirect:/" + process + "/" + seq + "/upload/check";
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
		Carrier carrier = basicService.getCarrier(gbl.getTsp());
		model.addAttribute("carrier", carrier);
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
		
		GBL gbl = outboundService.getGbl(Integer.parseInt(seq));
		dd619 = outboundService.getDd619ListSelectOne(listSeq);
		Branch branch = basicService.getBranch(gbl.getAreaLocal());
		Pod pod = basicService.getPod(gbl.getPod());
		Carrier carrier = basicService.getCarrier(gbl.getTsp());
		GBlock gblock = processService.getGBlockByGbloc(gbl.getDestGBlock());
		model.addAttribute("carrier",carrier);
		model.addAttribute("branch",branch);
		model.addAttribute("gblock", gblock);
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
		
		GBL gbl = outboundService.getGbl(Integer.parseInt(seq));
		
		model.addAttribute("gblock", processService.getGBlockByGbloc(gbl.getDestGBlock()));
		
		model.addAttribute("branch", basicService.getBranch(gbl.getAreaLocal()));
		
		model.addAttribute("containerList", outboundService.getContainerList());
		
		model.addAttribute("gbl", gbl);

		return process + "/gbl/weightcertificate";
	}
	

	@RequestMapping(value = "/{process}/{seq}/weightcertificate/print", method = RequestMethod.GET)//웨이서티피 프린트
	public String weightcertificatePrint(Model model, User user,
			@PathVariable String process, @PathVariable String seq) {
		
		model.addAttribute("seq", seq);
		model.addAttribute("weightcertificateList",
				outboundService.getWeightcertificateList(seq));
		GBL gbl = outboundService.getGbl(Integer.parseInt(seq));
		
		model.addAttribute("gblock", processService.getGBlockByGbloc(gbl.getDestGBlock()));
		
		model.addAttribute("branch", basicService.getBranch(gbl.getAreaLocal()));
		
		model.addAttribute("gbl", outboundService.getGbl(Integer.parseInt(seq)));
		System.out.println("[[[[[[[[[ branch test : "+basicService.getBranch(gbl.getAreaLocal()).getBranch()+" ]]]]]]");
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
	
	@RequestMapping(value = "/{process}/weightcertificate/delete.json", method = RequestMethod.POST)
	@ResponseBody
	public void gblWeightcertificateDelete(
			@RequestBody Weightcertificate weightcertificate) {
		outboundService.deleteWeightCertificate(weightcertificate);
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
	@PreAuthorize("hasRole('ROLE_LEVEL4') or hasRole('ROLE_LEVEL3') or hasRole('ROLE_LEVEL2') or hasRole('ROLE_LEVEL1') or hasRole('ROLE_LEVEL3') or hasRole('ROLE_LEVEL2') or hasRole('ROLE_LEVEL1')")
	@RequestMapping(value = "/{process}/delivery/main", method = RequestMethod.GET)
	public String deliveryMain(Model model, User user,
			@PathVariable String process) {

		model.addAttribute("user", user);

		user.setSubProcess("delivery");

		model.addAttribute("deliveryList", null);

		return process + "/delivery/main";
	}

	@PreAuthorize("hasRole('ROLE_LEVEL4') or hasRole('ROLE_LEVEL3') or hasRole('ROLE_LEVEL2') or hasRole('ROLE_LEVEL1') or hasRole('ROLE_LEVEL3') or hasRole('ROLE_LEVEL2') or hasRole('ROLE_LEVEL1')")
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
		List<TruckManifast> truckList = outboundService.getTruckList(outboundFilter);
		for(TruckManifast tl : truckList){
			int truckSeq = tl.getSeq();
			System.out.println("TRUCK SEQ : "+truckSeq);
			List<String> gblNoList = outboundService.getTruckGblNo(truckSeq);
			String str = "[";
			for(int i=0;i<gblNoList.size();i++){
				str+=gblNoList.get(i);
				if(i!=(gblNoList.size()-1)){
					str+="][ ";
				}
				else{
					str+="]";
				}
			}
			tl.setGblList(str);
		}
		System.out.println("======= PAGINATION SIZE : "+outboundFilter.getPagination().getNumItems()+" =====");
		System.out.println("======== LIST SIZE : "+truckList.size()+"============");
		model.addAttribute("truckList", truckList);
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
		List<GBL> gblList = outboundService.getTruckGblList(outboundFilter);
		outboundFilter.getPagination().setNumItems(gblList.size());
		System.out.println("[[[[[[[[[[[[ TRUCK GBL COUNT : "+outboundService.getGblListCount(outboundFilter, user)+" ]]]]]]]]]");
		System.out.println("[[[[[[[[[[[[ GBL LIST COUNT : "+gblList.size()+" ]]]]]]");
		model.addAttribute("filterMap", outboundService.getFilterMap());
		model.addAttribute("gblList",gblList);
		model.addAttribute("user", user);

		return process + "/delivery/gblList";
	}
	
	@RequestMapping(value = "/{process}/delivery/truckManifastGblList", method = RequestMethod.POST)
	public String truckManifastGblListPost(Model model, User user,
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
		List<TruckManifast> truckList = outboundService.getTruckList(outboundFilter);
		for(TruckManifast tl : truckList){
			int truckSeq = tl.getSeq();
			System.out.println("TRUCK SEQ : "+truckSeq);
			List<String> gblNoList = outboundService.getTruckGblNo(truckSeq);
			String str = "[";
			for(int i=0;i<gblNoList.size();i++){
				str+=gblNoList.get(i);
				if(i!=(gblNoList.size()-1)){
					str+="][ ";
				}
				else{
					str+="]";
				}
			}
			tl.setGblList(str);
		}
		model.addAttribute("filterMap", outboundService.getFilterMap());
		user.setSubProcess("truckManifast");
		model.addAttribute("truckList",truckList);
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

		TruckManifast tm = outboundService.getTruckManifastOne(seq);
		System.out.println("[[[[[[[[[test truckmanifast : "+tm.getDate()+" ]]]]]]]]]]]]");
		String truckDate = tm.getDate();
		model.addAttribute("gblList", list);
		model.addAttribute("truckDate",truckDate);
		model.addAttribute("podMap", podMap);
		
		model.addAttribute("truckManisfast", tm);
		
		model.addAttribute("user", user);
		System.out.println("[[[[[[[[[test truckmanifast : CALL PAPER ]]]]]]]]]]]]");
		return process + "/delivery/truckManifastPrint";
	}


	@RequestMapping(value = "/{process}/delivery/bookingList", method = RequestMethod.GET)
	public String bookingListMain(Model model, User user,
			@ModelAttribute OutboundFilter outboundFilter,
			@PathVariable String process) {

		outboundFilter.getPagination().setNumItems(
				outboundService.getBookingListCount(outboundFilter));

		model.addAttribute("filterMap", outboundService.getFilterMap());
		List<BookingList> bookingList = outboundService.getBookingList(outboundFilter);
		model.addAttribute("bookingList",bookingList);
		
		for(BookingList bl:bookingList){
			int seq = bl.getSeq();
			System.out.println("BOOKINGLIST SEQ : "+seq);
			List<String> gblNoList = outboundService.getBookingListGblNo(seq);
			String str = "[";
			for(int i=0;i<gblNoList.size();i++){
				str+=gblNoList.get(i);
				if(i!=(gblNoList.size()-1)){
					str+="][ ";
				}
				else{
					str+="]";
				}
			}
			bl.setGblList(str);
		}
		
		user.setSubProcess("bookingList");
		model.addAttribute("user", user);
		return process + "/delivery/bookingList";
	}

	@PreAuthorize("hasRole('ROLE_LEVEL4') or hasRole('ROLE_LEVEL3') or hasRole('ROLE_LEVEL2') or hasRole('ROLE_LEVEL1') or hasRole('ROLE_LEVEL3') or hasRole('ROLE_LEVEL2') or hasRole('ROLE_LEVEL1')")
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
	
	@PreAuthorize("hasRole('ROLE_LEVEL4') or hasRole('ROLE_LEVEL3') or hasRole('ROLE_LEVEL2') or hasRole('ROLE_LEVEL1') or hasRole('ROLE_LEVEL3') or hasRole('ROLE_LEVEL2') or hasRole('ROLE_LEVEL1')")
	@RequestMapping(value = "/{process}/delivery/bookingGblList", method = RequestMethod.POST)
	public String bookingListGblListPost(Model model, User user,
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

	@PreAuthorize("hasRole('ROLE_LEVEL4') or hasRole('ROLE_LEVEL3') or hasRole('ROLE_LEVEL2') or hasRole('ROLE_LEVEL1') or hasRole('ROLE_LEVEL3') or hasRole('ROLE_LEVEL2') or hasRole('ROLE_LEVEL1')")
	@RequestMapping(value = "/{process}/delivery/{seq}/bookingListPrint", method = RequestMethod.GET)
	public String bookingListPrint(Model model, User user,
			@ModelAttribute OutboundFilter outboundFilter,
			@PathVariable String process, @PathVariable Integer seq) {

		List<DeliveryGbl> list = outboundService.getBookingListPrint(seq);
		List<Weightcertificate> c;
		for(int i=0;i<list.size();i++){
			 c = list.get(i).getContainerList();
			 for(int k=0;k<c.size();k++){
				 int cuft = Integer.parseInt(c.get(k).getCuft());
				 if(cuft>=40 && cuft<71){
					 list.get(i).setTypeOf2();
				 }
				 else if(cuft>=71 && cuft<101){
					 list.get(i).setTypeOf1();
				 }
				 else if(cuft>=101 && cuft<131){
					 list.get(i).setType49();
				 }
				 else if(cuft>=131 && cuft<161){
					 list.get(i).setType91();
				 }
				 else if(cuft>=161 && cuft<180){
					 list.get(i).setType125();
				 }
				 else if(cuft>179){
					 list.get(i).setType11();
				 }
			 }
		}
		
		BookingList bookingList = outboundService.getBookingListOne(seq);
		model.addAttribute("gblList", list);
		model.addAttribute("bookingList", bookingList);
		model.addAttribute("user", user);
		return process + "/delivery/bookingListPrint";
	}
	
	@PreAuthorize("hasRole('ROLE_LEVEL4') or hasRole('ROLE_LEVEL3') or hasRole('ROLE_LEVEL2') or hasRole('ROLE_LEVEL1') or hasRole('ROLE_LEVEL3') or hasRole('ROLE_LEVEL2') or hasRole('ROLE_LEVEL1')")
	@RequestMapping(value = "/{process}/delivery/{seq}/powerOfAttornyPrint", method = RequestMethod.GET)
	public String powerOfAttornyPrint(Model model, User user,
			@ModelAttribute OutboundFilter outboundFilter,
			@PathVariable String process, @PathVariable Integer seq) {
		
		List<DeliveryGbl> list = outboundService.getBookingListPrint(seq);
		List<PowerOfAttornyList> poalist = outboundService.getPowerOfAttornyList(list);
		model.addAttribute("powerOfAttornyList", poalist);
		
//		model.addAttribute("gblList", list);
//		
//		model.addAttribute("bookingList", outboundService.getBookingListOne(seq));
//		
//		model.addAttribute("user", user);

		return process + "/delivery/powerOfAttornyListPrint";
	}
	
	
	@PreAuthorize("hasRole('ROLE_LEVEL4') or hasRole('ROLE_LEVEL3') or hasRole('ROLE_LEVEL2') or hasRole('ROLE_LEVEL1') or hasRole('ROLE_LEVEL3') or hasRole('ROLE_LEVEL2') or hasRole('ROLE_LEVEL1')")	
	@RequestMapping(value = "/{process}/delivery/{seq}/declarationList", method = RequestMethod.GET)
	public String declarationList(Model model, User user, @PathVariable String process, @PathVariable Integer seq) {

		List<DeliveryGbl> list = outboundService.getBookingListPrint(seq);		

		model.addAttribute("item_list", list);
		
		return process + "/delivery/declarationList";
	}	

	@PreAuthorize("hasRole('ROLE_LEVEL4') or hasRole('ROLE_LEVEL3') or hasRole('ROLE_LEVEL2') or hasRole('ROLE_LEVEL1') or hasRole('ROLE_LEVEL3') or hasRole('ROLE_LEVEL2') or hasRole('ROLE_LEVEL1')")
	@RequestMapping(value = "/{process}/delivery/bookingAdd.json")
	@ResponseBody
	public void bookingAdd(@RequestBody Map<String, String> gblSeq) {
		outboundService.insertBookingList(gblSeq);
	}

	@PreAuthorize("hasRole('ROLE_LEVEL4') or hasRole('ROLE_LEVEL3') or hasRole('ROLE_LEVEL2') or hasRole('ROLE_LEVEL1') or hasRole('ROLE_LEVEL3') or hasRole('ROLE_LEVEL2') or hasRole('ROLE_LEVEL1')")
	@RequestMapping(value = "/{process}/delivery/bookingListDelete.json")
	@ResponseBody
	public void bookingDelete(@RequestBody Map<String, String> bookingSeq) {
		outboundService.deleteBookingList(bookingSeq);
	}
	
	@PreAuthorize("hasRole('ROLE_LEVEL4') or hasRole('ROLE_LEVEL3') or hasRole('ROLE_LEVEL2') or hasRole('ROLE_LEVEL1') or hasRole('ROLE_LEVEL3') or hasRole('ROLE_LEVEL2') or hasRole('ROLE_LEVEL1')")
	@RequestMapping(value = "/{process}/delivery/mil/tcmdListDelete.json")
	@ResponseBody
	public void tcmdListDelete(@RequestBody Map<String, String> tcmdSeq) {
		outboundService.deleteTcmdList(tcmdSeq);
	}
	
	@PreAuthorize("hasRole('ROLE_LEVEL4') or hasRole('ROLE_LEVEL3') or hasRole('ROLE_LEVEL2') or hasRole('ROLE_LEVEL1') or hasRole('ROLE_LEVEL3') or hasRole('ROLE_LEVEL2') or hasRole('ROLE_LEVEL1')")
	@RequestMapping(value = "/{process}/delivery/mil/tcmd")
	public String tcmdMain(Model model, User user, @PathVariable String process, @ModelAttribute OutboundFilter outboundFilter){
		
		user.setSubProcess("tcmd");
		
		outboundFilter.getPagination().setNumItems(
				outboundService.getTcmdListCount(outboundFilter));
		
		List<Tcmd> tcmdList = outboundService.getTcmdList();
		for(Tcmd tl:tcmdList){
			int tcmdSeq = tl.getSeq();
			List<String> shipperList = outboundService.getTcmdGblSeqList(tcmdSeq);
			String str="[";
			for(int i=0;i<shipperList.size();i++){
				if(i!=(shipperList.size()-1)){
					str+=shipperList.get(i)+"] [";
				}
				else{
					str+=shipperList.get(i)+"]";
				}
			}
			System.out.println("[[[[ CALL TCMD SEQ : "+tcmdSeq+" ]]]]");
			System.out.println("[[[[ SHIPPER LIST : "+str+" ]]]]");
			tl.setShipperList(str);
		}
		
		model.addAttribute("tcmdList", tcmdList);
		model.addAttribute("user", user);
		
		return process + "/delivery/tcmd";
	}
	
	@PreAuthorize("hasRole('ROLE_LEVEL4') or hasRole('ROLE_LEVEL3') or hasRole('ROLE_LEVEL2') or hasRole('ROLE_LEVEL1') or hasRole('ROLE_LEVEL3') or hasRole('ROLE_LEVEL2') or hasRole('ROLE_LEVEL1')")
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
	
	@PreAuthorize("hasRole('ROLE_LEVEL4') or hasRole('ROLE_LEVEL3') or hasRole('ROLE_LEVEL2') or hasRole('ROLE_LEVEL1') or hasRole('ROLE_LEVEL3') or hasRole('ROLE_LEVEL2') or hasRole('ROLE_LEVEL1')")
	@RequestMapping(value = "/{process}/delivery/mil/tcmdGblSetting", method=RequestMethod.POST)
	public String tcmdGblSettingPost(Model model, User user, @PathVariable String process, @ModelAttribute OutboundFilter outboundFilter){

		outboundFilter.getPagination().setNumItems(
				outboundService.getTcmdGblListCount(outboundFilter));

		model.addAttribute("filterMap", outboundService.getFilterMap());

		model.addAttribute("gblList",
				outboundService.getTcmdGblList(outboundFilter));
		model.addAttribute("user", user);
		
		return process + "/delivery/tcmdGblList";
	}
	
	@PreAuthorize("hasRole('ROLE_LEVEL4') or hasRole('ROLE_LEVEL3') or hasRole('ROLE_LEVEL2') or hasRole('ROLE_LEVEL1')")
	@RequestMapping(value = "/{process}/delivery/mil/{seq}/tcmdModify")
	public String tcmdModify(Model model, User user, @PathVariable String process, @PathVariable Integer seq, @ModelAttribute OutboundFilter outboundFilter){			
		model.addAttribute("user", user);
		
		String year = Integer.toString(DateUtil.getYear());
		String getJulianDate = year.substring(3, 4) + DateUtil.getDaysBetween(year + "0101", DateUtil.getToday("YYYYMMDD"));
		System.out.println("[[[[ JULIAN TEST YEAR : "+year +" substring 3,4 "+ year.substring(3,4)+" ]]]]");
		List<GBL> gblList = outboundService.getTcmdContentGblList(seq);
		for(GBL g:gblList){
			String rddJulian="";
			rddJulian = g.getRdd().substring(3, 4) + DateUtil.getDaysBetween(g.getRdd().substring(0,4) + "0101", g.getRdd());
			System.out.println("[[[[[[[[[ GBL : "+g.getNo()+"   RDD : "+g.getRdd().substring(0,4)+"yesr "+g.getRdd()+"   JULIAN : "+rddJulian+" ]]]]]]]]]");
			g.setTcmdRddJulianDate(rddJulian);
			if(g.getCode().equals("T")){
				g.setJk("KXX");
			}
			else if(g.getCode().equals("J")){
				g.setJk("JXX");
			}
			if(g.getRemarkTac() == null){
				String temp = g.getNo().substring(0,4);
				System.out.println(" [[[[[[[[[[[[ STRING GBL NO CUT TEST : "+temp+" ]]]]]]]]]]]]");
				if(temp.equals("QXAK")){
					g.setRemarkTac("CGA4");
				}
				else if(temp.equals("QNFL") || temp.equals("QMFL")){
					g.setRemarkTac("F48D");
				}
			}
			g.setTcmdConsignee3(processService.getGBlockByGbloc(g.getDestGBlock()).getDodaac());
		}
		Tcmd tcmd = outboundService.getTcmdContent(seq);
		System.out.println("TURN IN DATE : "+tcmd.getTurnindate());
		if(tcmd.getTurnindate() == null){
			Date today = new Date();
	        Date seldate = new Date();
	        SimpleDateFormat simple = new SimpleDateFormat("dd-MMM-yy",Locale.ENGLISH);
	        seldate.setTime( today.getTime() + (1000 * 60 * 60 * 24)* 1);
	        String date = simple.format(seldate);
	        tcmd.setTurnindate(date);
	        System.out.println("DATE : "+date);
		}
		int size = gblList.size();
		int pageNum = size/7;
		
		if(size%7 != 0){
			pageNum++;
		}
		System.out.println("pageNum : "+pageNum);
		model.addAttribute("julianDate", getJulianDate);
		model.addAttribute("tcmd", tcmd);
		model.addAttribute("gblList", gblList);
		model.addAttribute("pageNum",pageNum);
		return process + "/delivery/tcmdModify";
	}
	
	@PreAuthorize("hasRole('ROLE_LEVEL4') or hasRole('ROLE_LEVEL3') or hasRole('ROLE_LEVEL2') or hasRole('ROLE_LEVEL1')")
	@RequestMapping(value = "/{process}/delivery/mil/{seq}/tcmdPrint")
	public String tcmdPrint(Model model, User user, @PathVariable String process, @PathVariable Integer seq, @ModelAttribute OutboundFilter outboundFilter){			
		model.addAttribute("user", user);
		
		String year = Integer.toString(DateUtil.getYear());
		String getJulianDate = year.substring(3, 4) + DateUtil.getDaysBetween(year + "0101", DateUtil.getToday("YYYYMMDD"));
		System.out.println("[[[[ JULIAN TEST YEAR : "+year +" substring 3,4 "+ year.substring(3,4)+" ]]]]");
		List<GBL> gblList = outboundService.getTcmdContentGblList(seq);
		for(GBL g:gblList){
			String rddJulian="";
			rddJulian = g.getRdd().substring(3, 4) + DateUtil.getDaysBetween(g.getRdd().substring(0,4) + "0101", g.getRdd());
			System.out.println("[[[[[[[[[ GBL : "+g.getNo()+"   RDD : "+g.getRdd().substring(0,4)+"yesr "+g.getRdd()+"   JULIAN : "+rddJulian+" ]]]]]]]]]");
			g.setTcmdRddJulianDate(rddJulian);
			if(g.getCode().equals("T")){
				g.setJk("KXX");
			}
			else if(g.getCode().equals("J")){
				g.setJk("JXX");
			}
			if(g.getRemarkTac() == null){
				String temp = g.getNo().substring(0,4);
				System.out.println(" [[[[[[[[[[[[ STRING GBL NO CUT TEST : "+temp+" ]]]]]]]]]]]]");
				if(temp.equals("QXAK")){
					g.setRemarkTac("CGA4");
				}
				else if(temp.equals("QNFL") || temp.equals("QMFL")){
					g.setRemarkTac("F48D");
				}
			}
			g.setTcmdConsignee3(processService.getGBlockByGbloc(g.getDestGBlock()).getDodaac());
		}
		Tcmd tcmd = outboundService.getTcmdContent(seq);
		int size = gblList.size();
		int pageNum = size/7;
		
		if(size%7 != 0){
			pageNum++;
		}
		System.out.println("pageNum : "+pageNum);
		model.addAttribute("julianDate", getJulianDate);
		model.addAttribute("tcmd", tcmd);
		model.addAttribute("gblList", gblList);
		model.addAttribute("pageNum",pageNum);
		return process + "/delivery/tcmdPrint";
	}

	@PreAuthorize("hasRole('ROLE_LEVEL4') or hasRole('ROLE_LEVEL3') or hasRole('ROLE_LEVEL2') or hasRole('ROLE_LEVEL1') or hasRole('ROLE_LEVEL3') or hasRole('ROLE_LEVEL2') or hasRole('ROLE_LEVEL1')")
	@RequestMapping(value = "/{process}/tcmd/tcmdListAdd.json")
	@ResponseBody
	public void tcmdAdd(@RequestBody Map<String, String> gblSeq) {
		outboundService.insertTcmdList(gblSeq);
	}	

	@PreAuthorize("hasRole('ROLE_LEVEL4') or hasRole('ROLE_LEVEL3') or hasRole('ROLE_LEVEL2') or hasRole('ROLE_LEVEL1') or hasRole('ROLE_LEVEL3') or hasRole('ROLE_LEVEL2') or hasRole('ROLE_LEVEL1')")
	@RequestMapping(value = "/{process}/tcmdGblUpdate.json")
	@ResponseBody
	public void tcmdGblUpdate(@RequestBody Map<String, String> map) {
		outboundService.updateTcmdGbl(map);
	}	
	
	@PreAuthorize("hasRole('ROLE_LEVEL4') or hasRole('ROLE_LEVEL3') or hasRole('ROLE_LEVEL2') or hasRole('ROLE_LEVEL1') or hasRole('ROLE_LEVEL3') or hasRole('ROLE_LEVEL2') or hasRole('ROLE_LEVEL1')")
	@RequestMapping(value = "/{process}/tcmdUpdate.json")
	@ResponseBody
	public void tcmdUpdate(@RequestBody Map<String, String> map) {
		outboundService.updateTcmd(map);
	}	

	@PreAuthorize("hasRole('ROLE_LEVEL4') or hasRole('ROLE_LEVEL3') or hasRole('ROLE_LEVEL2') or hasRole('ROLE_LEVEL1') or hasRole('ROLE_LEVEL3') or hasRole('ROLE_LEVEL2') or hasRole('ROLE_LEVEL1')")
	@RequestMapping(value = "/{process}/delivery/house")
	public String houseBl(Model model, User user, @PathVariable String process, @ModelAttribute OutboundFilter outboundFilter){			
		
		outboundFilter.getPagination().setNumItems(outboundService.getHouseListCount(outboundFilter));
		
		model.addAttribute("user", user);
		
		user.setSubProcess("house");
		
		model.addAttribute("houseList", outboundService.getHouseList(outboundFilter));
		
		return process + "/delivery/house";
	}

	@PreAuthorize("hasRole('ROLE_LEVEL4') or hasRole('ROLE_LEVEL3') or hasRole('ROLE_LEVEL2') or hasRole('ROLE_LEVEL1')")
	@RequestMapping(value = "/{process}/delivery/house/gblSelect", method = RequestMethod.GET)
	public String houseBlGblSelectGet(Model model, User user, @PathVariable String process, @ModelAttribute OutboundFilter outboundFilter){	
		outboundFilter.setHouseBlFlag(true);
		outboundFilter.getPagination().setNumItems(
				outboundService.getGblListCount(outboundFilter, user));

		model.addAttribute("filterMap", outboundService.getFilterMap());

		model.addAttribute("gblList",
				outboundService.getHouseGblList(outboundFilter));
		model.addAttribute("user", user);		
		
		return process + "/delivery/houseSelect";
	}

	@PreAuthorize("hasRole('ROLE_LEVEL4') or hasRole('ROLE_LEVEL3') or hasRole('ROLE_LEVEL2') or hasRole('ROLE_LEVEL1')")
	@RequestMapping(value = "/{process}/delivery/house/gblSelect", method = RequestMethod.POST)
	public String houseBlGblSelectPost(Model model, User user, @PathVariable String process, @ModelAttribute OutboundFilter outboundFilter){	
		outboundFilter.setHouseBlFlag(true);
		outboundFilter.getPagination().setNumItems(
				outboundService.getGblListCount(outboundFilter, user));

		model.addAttribute("filterMap", outboundService.getFilterMap());

		model.addAttribute("gblList",
				outboundService.getHouseGblList(outboundFilter));
		model.addAttribute("user", user);		
		
		return process + "/delivery/houseSelect";
	}


	@RequestMapping(value = "/{process}/delivery/{seq}/houseSeperateSetting", method = RequestMethod.GET)
	public String houseSeperateSetting(Model model, User user,
			@ModelAttribute OutboundFilter outboundFilter,
			@PathVariable String process, @PathVariable String seq) {

		model.addAttribute("seq", seq);
		model.addAttribute("weightCertificateList", outboundService.getWeightcertificateList(seq));
		
		return process + "/delivery/houseSeperate";
	}	

	@RequestMapping(value = "/{process}/delivery/house/seperate.json")
	@ResponseBody
	public void gblHouseSperateSubmit(@RequestBody Map<String, String> gblSeq) {
		outboundService.houseSeperateGbl(gblSeq);
	}		
	

	@RequestMapping(value = "/{process}/delivery/house/merge.json")
	@ResponseBody
	public void gblHouseMergeSubmit(@RequestBody Map<String, String> gblSeq) {
		outboundService.houseMergeSubmit(gblSeq);
	}	


	@RequestMapping(value = "/{process}/delivery/house/add.json")
	@ResponseBody
	public void houseAdd(@RequestBody Map<String, String> gblSeq) {
		outboundService.insertHouse(gblSeq);
	}


	@RequestMapping(value = "/{process}/delivery/house/delete.json")
	@ResponseBody
	public void houseDelete(@RequestBody Map<String, String> gblSeq) {
		outboundService.deleteHouse(gblSeq);
	}
	
	@RequestMapping(value = "/{process}/delivery/house/{seq}/housePop", method = RequestMethod.GET)
	public String housePop(Model model, User user,
			@ModelAttribute OutboundFilter outboundFilter,
			@PathVariable String process, @PathVariable String seq) {

		model.addAttribute("seq", seq);
		
		List<GBL> gblList = outboundService.getGblListHouse(seq);
		
		Map<String, Company> companyMap = basicService.getCompanyMap();
		Map<String, Pod> podMap = basicService.getpodMap();
		
		model.addAttribute("gblList", gblList);
		model.addAttribute("podMap", podMap);
		model.addAttribute("companyMap", companyMap);
		model.addAttribute("house", outboundService.getHouse(seq));
		
		return process + "/delivery/housePop";
	}	
	
	@RequestMapping(value = "/{process}/delivery/house/{seq}/housePopPdf", method = RequestMethod.GET)
	public String housePopPdf(Model model, User user, HttpServletResponse response, 
			@ModelAttribute OutboundFilter outboundFilter,
			@PathVariable String process, @PathVariable String seq) throws IOException {
		
		URL url = new URL("localhost:8080/youngjin_sys/outbound/delivery/house/" + seq + "/housePop");  
		URLConnection urlc = url.openConnection();  
		InputStream in = urlc.getInputStream();  
		   
		OutputStream out = response.getOutputStream();  
		response.setContentType( "application/pdf" );   
		response.setHeader("Content-Disposition","inline; filename=\"test.pdf\"");  
		byte[] buf = new byte[8192];  
		   
		int c;  
		while ((c = in.read(buf, 0, buf.length)) > 0) {  
		  out.write(buf, 0, c);  
		}  
		out.flush();  
		out.close();  
		// return null from the action to tell struts   
		// that we have already handled the response.  
		return null;   
	}	
	public String test2(String input){
		String aa="";

//	    String test ="QXAK01234";
//		double typeIICharge = 321.9;
//		int a=1;
//		int intk = (int)typeIICharge;
//		test = new DecimalFormat("######.00").format(typeIICharge);
//		System.out.println("?? : "+test);
//		
//		Double returnValue = Math.round(Double.parseDouble(test)*100d)/100d;
//		System.out.println(returnValue);
//		test = new DecimalFormat("######.00").format(returnValue);
//		System.out.println("fianl : "+test);
//		
//	   String pud = "20131215";
//	   String year = pud.substring(0,4);
//		String month = pud.substring(4,6);
//		String day = pud.substring(6,8);
//		System.out.println(year + " "+month+" "+day);
//	   String re = getYear(pud);
//	   System.out.println(re);
	   String str="ㅈㅎㅈㅈㅎㅎㅎㅎㅈㅎㅈㅎㅈㅎㅈㅈㅎㅎㅎㅎㅈㅎㅎㅈㅈㅈㅎㅎㅈㅎㅈㅎㅎㅎㅎㅎㅎㅎㅈㅎㅈㅈㅈㅈㅎㅈㅈㅈㅎㅈㅈㅈㅈㅈㅎㅈㅎㅈㅎㅈㅎㅈㅎㅈㅈㅈㅎㅎㅈㅎㅎㅈㅎㅎㅈㅈㅈㅎㅈㅈㅎㅎㅈㅎㅎㅎㅈㅎㅎㅈㅎㅎㅈㅎㅎㅈㅎㅈㅎㅎㅈㅎㅎㅈㅎㅎㅎㅈㅈㅎㅈㅎㅎㅈㅈㅈㅈㅎㅎㅈㅈㅎㅎㅎㅎㅈㅈㅎㅎㅈㅎㅈㅈㅎㅎㅎㅎㅎㅈㅈㅈㅎㅈㅈㅈㅈㅈㅈㅎㅎㅈㅎㅎㅎㅎㅈㅎㅎㅎㅎㅈㅎㅎㅈㅈㅎㅈㅈㅈㅈㅈㅈㅈㅈㅎㅎㅎㅈㅈㅎㅎㅈㅈㅈㅎㅈㅎㅎㅈㅈㅈㅈㅈㅎㅈㅎㅎㅎㅈㅈㅈㅈㅈㅎㅈㅈㅈㅎㅎㅈㅎㅎㅈㅎㅎㅎㅈㅈㅎㅈㅎㅈㅈㅈㅎㅎㅎㅎㅈㅈㅎㅎㅈㅎㅎㅈㅈㅈㅈㅈㅎㅈㅈㅈㅈㅈㅈㅈㅈㅎㅈㅎㅎㅈㅈㅈㅎㅈㅎㅈㅎㅎㅈㅎㅎㅎㅎㅎㅈㅎㅎㅎㅈㅎㅈㅎㅎㅎㅈㅎㅎㅎㅎㅎㅎㅈㅈㅈㅈㅎㅎㅈㅈㅈㅎㅈㅈㅈㅎㅎㅈㅎㅈㅈㅎㅈㅎㅎㅈㅎㅎㅈㅎㅈㅈㅎㅈㅈㅎㅈㅎㅎㅈㅈㅈㅎㅎㅎㅎㅈㅈㅎㅈㅎㅎㅈㅈㅎㅎㅈㅈㅈㅈㅈㅈㅎㅎㅎㅎㅎㅎㅈㅈㅎㅈㅈㅈㅎㅈㅎㅎㅎㅈㅎㅎㅈㅎㅈㅈㅈㅈㅎㅎㅎㅎㅈㅎㅎㅎㅈㅎㅎㅈㅈㅎㅎㅎㅎㅈㅈㅎㅎㅈㅈㅈㅎㅈㅈㅎㅈㅎㅎㅈㅈㅈㅈㅎㅎㅎㅎㅈㅎㅈㅈㅈㅈㅈㅈㅈㅈㅈㅈㅎㅎㅈㅈㅈㅈㅎㅈㅎㅇㅇㅇㅇㅈㅎㅎㅎㅎㅎㅎㅈㅈㅈㅈㅈㅈㅎㅎㅈㅎㅈㅎㅈㅎㅈㅈㅈㅈㅈㅎㅈㅈㅈㅈㅈㅈㅈㅎㅈㅈㅎㅈㅈㅎㅈㅎㅎㅈㅈㅎㅎㅈㅎㅎㅈㅈㅎㅎㅈㅎㅈㅎㅎㅎㅎㅎㅎㅎㅈㅈㅈㅈㅈㅈㅈㅈㅈㅈㅎㅈㅎㅎㅈㅎㅈㅈㅈㅈㅈㅎㅈㅈㅈㅎㅎㅈㅈㅎㅎㅈㅎㅈㅈㅈㅎㅎㅈㅈㅈㅈㅈㅈㅎㅈㅎㅈㅈㅈㅎㅈㅎㅈㅈㅎㅈㅈㅈㅈㅎㅈㅈㅈㅎㅈㅈㅈㅈㅎㅈㅎㅎㅎㅈㅈㅈㅎㅈㅈㅈㅈㅎㅎㅎㅈㅈㅈㅎㅈㅎㅈㅈㅎㅎㅈㅈㅈㅈㅈㅈㅈㅈㅈㅈㅎㅎㅈㅎㅈㅎㅈㅎㅈㅎㅈㅎㅎㅎㅈㅎㅈㅈㅎㅈㅈㅇㅇㅇㅇㅇㅇㅎㅈㅎㅈㅎㅎㅈㅈㅎㅈㅎㅈㅈㅈㅈㅎㅈㅈㅈㅎㅎㅈㅎㅎㅎㅎㅈㅈㅎㅎㅈㅈㅈㅎㅈㅈㅈㅎㅎㅈㅈㅈㅎㅎㅎㅎㅈㅎㅎㅈㅎㅈㅈㅈㅎㅎㅈㅎㅈㅎㅈㅎㅈㅎㅈㅈㅈㅈㅎㅎㅈㅎㅈㅈㅎㅎㅎㅎㅎㅈㅈㅎㅎㅎㅎㅎㅎㅎㅎㅈㅈㅈㅈㅈㅎㅎㅎㅎㅈㅈㅈㅎㅈㅈㅎㅈㅈㅈㅎㅈㅈㅎㅈㅈㅎㅎㅈㅎㅎㅎㅎㅎㅎㅈㅈㅎㅎㅈㅎㅈㅎㅎㅈㅎㅈㅎㅈㅎㅈㅈㅎㅈㅎㅈㅈㅎㅈㅎㅎㅈㅎㅎㅈㅈㅎㅎㅈㅎㅎㅎㅎㅈㅎㅎㅎㅈㅎㅈㅎㅎㅈㅈㅈㅎㅎㅈㅈㅎㅎㅎㅈㅎㅈㅎㅎㅈㅎㅎㅎㅎㅈㅎㅎㅎㅈㅈㅈㅈㅈㅈㅎㅈㅈㅈㅎㅎㅈㅎㅈㅈㅈㅎㅎㅎㅈㅈㅈㅈㅎㅈㅎㅈㅎㅈㅎㅎㅎㅈㅎㅈㅎㅎㅎㅎㅎㅈㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅈㅎㅎㅎㅈㅈㅎㅈㅈㅎㅎㅈㅈㅈㅈㅈㅎㅎㅎㅎㅎㅎㅈㅈㅈㅎㅈㅎㅎㅈㅈㅎㅈㅎㅎㅎㅈㅈㅈㅎㅎㅈㅎㅎㅈㅎㅎㅈㅈㅎㅎㅈㅈㅎㅈㅈㅈㅈㅈㅈㅎㅎㅈㅎㅈㅈㅎㅎㅎㅈㅈㅈㅈㅈㅎㅎㅈㅎㅈㅎㅈㅈㅎㅈㅈㅎㅈㅈㅈㅎㅎㅈㅈㅈㅈㅎㅈㅈㅈㅎㅈㅈㅎㅈㅎㅈㅎㅎㅈㅈㅎㅈㅎㅎㅎㅎㅈㅈㅎㅈㅎㅈㅎㅈㅈㅎㅈㅈㅈㅎㅈㅈㅈㅈㅎㅈㅈㅎㅎㅎㅎㅈㅈㅈㅈㅎㅈㅈㅈㅎㅈㅎㅈㅎㅈㅎㅎㅎㅎㅈㅎㅎㅈㅎㅈㅎㅈㅎㅈㅎㅎㅈㅈㅈㅈㅎㅈㅈㅈㅎㅈㅈㅈㅈㅎㅎㅈㅎㅎㅎㅎㅈㅎㅎㅎㅈㅎㅎㅈㅈㅎㅎㅎㅈㅎㅎㅈㅎㅈㅎㅈㅎㅎㅎㅈㅈㅎㅎㅎㅈㅈㅈㅎㅎㅎㅈㅈㅎㅎㅈㅈㅈㅈㅎㅈㅈㅈㅎㅈㅎㅎㅎㅈㅎㅎㅎㅎㅎㅎㅈㅎㅎㅎㅎㅎㅎㅈㅈㅈㅈㅈㅈㅈㅎㅈㅈㅎㅎㅈㅈㅎㅎㅎㅈㅈㅈㅎㅈㅎㅎㅈㅈㅈㅈㅈㅎㅎㅈㅈㅎㅈㅎㅎㅈㅎㅎㅈㅎㅈㅎㅈㅈㅈㅈㅎㅎㅎㅈㅈㅈㅎㅎㅈㅈㅎㅈㅈㅎㅈㅈㅈㅎㅈㅎㅎㅎㅎㅎㅈㅎㅈㅈㅈㅈㅈㅎㅎㅈㅈㅈㅎㅈㅎㅈㅎㅈㅈㅎㅈㅎㅈㅎㅈㅎㅈㅈㅎㅎㅎㅎㅈㅈㅎㅈㅈㅎㅎㅈㅎㅎㅎㅈㅎㅈㅎㅎㅈㅎㅎㅎㅈㅈㅎㅎㅎㅈㅈㅎㅈㅈㅈㅎㅈㅈㅎㅎㅎㅎㅎㅎㅎㅈㅈㅎㅈㅎㅈㅈㅎㅎㅎㅎㅈㅎㅈㅎㅈㅎㅎㅈㅈㅎㅈㅈㅈㅈㅈㅈㅎㅈㅈㅈㅈㅈㅎㅎㅎㅎㅎㅈㅈㅎㅎㅈㅈㅎㅎㅈㅎㅈㅈㅎㅎㅈㅎㅈㅎㅎㅈㅎㅈㅈㅎㅈㅈㅈㅈㅎㅈㅎㅈㅈㅈㅎㅎㅎㅎㅈㅎㅎㅎㅈㅈㅈㅎㅈㅎㅈㅈㅎㅈㅎㅈㅎㅈㅎㅈㅎㅈㅈㅈㅈㅈㅈㅎㅎㅈㅎㅈㅎㅈㅈㅎㅈㅈㅈㅎㅎㅎㅈㅎㅎㅈㅎㅈㅈㅎㅈㅎㅎㅎㅈㅎㅇㅈㅎㅎㅈㅎㅎㅎㅎㅈㅎㅎㅎㅎㅈㅎㅈㅎㅎㅎㅈㅎㅎㅈㅎㅎㅎㅈㅎㅎㅎㅈㅎㅈㅈㅈㅎㅎㅈㅎㅎㅎㅈㅈㅈㅎㅈㅎㅈㅎㅈㅈㅈㅎㅈㅈㅎㅈㅎㅎㅎㅈㅎㅈㅈㅎㅈㅎㅎㅈㅈㅈㅎㅎㅈㅎㅎㅈㅎㅎㅈㅎㅎㅎㅈㅎㅈㅎㅎㅎㅈㅈㅎㅈㅎㅈㅎㅎㅎㅎㅈㅈㅈㅎㅈㅎㅎㅈㅈㅈㅎㅈㅈㅈㅈㅈㅎㅈㅈㅎㅎㅎㅈㅈㅈㅎㅈㅎㅈㅈㅎㅎㅈㅎㅎㅈㅈㅈㅈㅈㅈㅈㅈㅈㅎㅎㅈㅎㅎㅈㅈㅎㅎㅈㅈㅈㅈㅎㅎㅈㅎㅎㅈㅈㅈㅈㅎㅎㅎㅈㅎㅎㅎㅈㅎㅎㅎㅈㅈㅈㅈㅎㅈㅈㅎㅈㅈㅎㅎㅈㅎㅎㅎㅈㅎㅎㅈㅎㅎㅈㅈㅎㅎㅎㅎㅈㅎㅈㅎㅈㅈㅎㅈㅎㅈㅎㅎㅎㅈㅈㅈㅎㅎㅈㅈㅈㅎㅎㅈㅈㅎㅈㅎㅈㅈㅈㅎㅈㅎㅎㅈㅎㅎㅎㅈㅈㅎㅎㅎㅈㅎㅈㅎㅎㅎㅎㅎㅈㅈㅎㅈㅈㅎㅎㅎㅈㅎㅈㅎㅎㅈㅎㅎㅈㅈㅎㅎㅎㅈㅈㅎㅎㅈㅈㅎㅎㅈㅎㅎㅎㅈㅎㅎㅈㅎㅎㅎㅈㅎㅈㅈㅎㅈㅎㅎㅈㅈㅎㅎㅈㅈㅎㅎㅎㅈㅈㅎㅎㅈㅈㅈㅈㅈㅈㅈㅎㅎㅎㅈㅎㅈㅈㅎㅈㅎㅈㅈㅎㅎㅈㅈㅈㅈㅈㅈㅈㅎㅎㅈㅈㅈㅈㅎㅎㅈㅎㅎㅎㅈㅈㅈㅎㅎㅈㅈㅈㅎㅈㅎㅈㅈㅎㅎㅎㅎㅎㅈㅈㅎㅈㅈㅎㅈㅈㅈㅈㅈㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅈㅎㅎㅎㅈㅎㅈㅎㅎㅎㅎㅎㅈㅎㅎㅈㅈㅎㅎㅈㅈㅎㅈㅎㅈㅈㅎㅈㅎㅎㅎㅎㅎㅈㅈㅈㅎㅈㅎㅎㅈㅈㅈㅎㅈㅈㅎㅎㅈㅈㅈㅈㅈㅈㅎㅎㅎㅎㅎㅈㅈㅈㅎㅎㅎㅈㅈㅈㅈㅈㅎㅎㅎㅈㅈㅎㅈㅎㅎㅈㅈㅎㅎㅈㅎㅈㅎㅎㅈㅎㅎㅈㅈㅎㅈㅈㅈㅈㅎㅎㅎㅎㅈㅎㅎㅎㅎㅎㅎㅎㅎㅈㅈㅎㅈㅈㅎㅈㅈㅈㅎㅎㅈㅎㅈㅈㅈㅈㅎㅎㅎㅈㅎㅎㅈㅎㅈㅈㅈㅎㅈㅎㅎㅎㅎㅎㅈㅈㅈㅎㅈㅎㅎㅎㅎㅎㅎㅈㅈㅎㅈㅈㅈㅎㅎㅎㅎㅈㅎㅎㅈㅈㅎㅎㅎㅈㅎㅈㅎㅈㅈㅈㅈㅈㅈㅎㅎㅎㅈㅎㅈㅈㅎㅎㅎㅈㅎㅎㅈㅈㅎㅈㅎㅈㅎㅈㅎㅈㅎㅎㅈㅈㅎㅈㅈㅈㅈㅎㅈㅈㅎㅈㅈㅈㅎㅎㅈㅈㅎㅎㅎㅎㅎㅎㅈㅎㅈㅎㅈㅈㅎㅎㅈㅎㅈㅎㅎㅎㅈㅈㅈㅎㅈㅎㅎㅎㅎㅈㅎㅈㅎㅎㅎㅎㅈㅎㅈㅎㅎㅈㅎㅎㅈㅎㅈㅎㅎㅈㅎㅈㅎㅎㅎㅈㅈㅎㅎㅎㅈㅎㅈㅈㅈㅈㅈㅎㅈㅈㅎㅎㅎㅎㅈㅈㅈㅎㅈㅎㅎㅎㅎㅎㅎㅎㅈㅈㅈㅈㅈㅈㅎㅈㅈㅎㅈㅎㅎㅎㅈㅈㅎㅈㅈㅎㅈㅈㅈㅈㅎㅈㅈㅎㅈㅎㅈㅎㅈㅎㅈㅎㅎㅎㅎㅈㅈㅈㅈㅈㅎㅈㅎㅎㅎㅎㅈㅎㅈㅎㅈㅈㅈㅈㅈㅎㅈㅈㅈㅎㅎㅎㅎㅈㅈㅈㅈㅎㅎㅎㅎㅎㅈㅎㅈㅈㅈㅎㅈㅎㅈㅎㅎㅎㅎㅎㅈㅎㅎㅈㅈㅈㅎㅈㅎㅈ";

//	   String str="ㅈㅎㅎㅈㅈㅈㅎㅎㅈㅎㅎㅈㅈㅎㅈㅈㅈㅈㅈㅎㅈㅎㅎㅈㅈㅎㅎㅎㅎㅈㅈㅎㅈㅈㅈㅎㅎㅎㅎㅎㅈㅎㅈㅎㅎㅎㅈㅎㅈㅈㅈㅈㅎㅈㅎㅎㅈㅎㅎㅈㅎㅎㅈㅈㅈㅎㅈㅈㅈㅎㅎㅎㅈㅈㅎㅈㅈㅈㅎㅈㅈㅎㅈㅎㅎㅎㅈㅎㅈㅈㅈㅈㅎㅎㅈㅈㅎㅎㅎㅎㅈㅎㅈㅎㅎㅈㅈㅎㅎㅎㅎㅈㅎㅈㅎㅈㅈㅎㅈㅎㅎㅎㅈㅎㅎㅈㅈㅈㅎㅈㅈㅎㅈㅈㅈㅎㅎㅎㅈㅈㅈㅎㅎㅈㅎㅎㅈ"
//	   		+ "ㅈㅈㅈㅈㅎㅎㅈㅎㅈㅈㅎㅈㅈㅎㅎㅎㅈㅎㅎㅎㅎㅎㅈㅈㅈㅎㅈㅎㅈㅎㅎㅎㅈㅎㅎㅎㅎㅈㅎㅎㅎㅎㅈㅎㅈㅈㅎㅎㅈㅎㅈㅈㅎㅈㅎㅈㅎㅎㅈㅎㅎㅎㅈㅈㅎㅎㅈㅈㅎㅈㅎㅎㅎㅎㅈㅎㅎㅎㅎㅈㅈㅎㅈㅈㅈㅈㅈㅎㅎㅎㅎㅎㅈㅈㅈㅈㅎㅈㅈㅎㅎㅈㅎㅈㅎㅈㅎㅈㅎㅎㅈㅈㅎㅎㅎㅈㅎㅎㅎㅎㅎㅈㅈㅎㅎㅈㅈㅈㅈㅎㅎㅎㅎㅈㅎㅎㅎㅈㅈㅎㅈ"	   //5월18일 288
//	   		String str = "ㅎㅎㅈㅎㅎㅎㅎㅈㅈㅈㅎㅈㅈㅈㅈㅈㅎㅎㅈㅎㅈㅈㅈㅈㅈㅈㅎㅈㅎㅎㅈㅎㅈㅈㅎㅈㅈㅎㅈㅈㅈㅎㅎㅈㅈㅈㅈㅎㅈㅈㅎㅈㅈㅈㅈㅎㅎㅎㅈㅈㅎㅈㅎㅈㅈㅈㅎㅎㅈㅈㅈㅈㅎㅎㅈㅈㅈㅈㅈㅎㅎㅈㅎㅈㅎㅎㅎㅎㅈㅈㅎㅈㅎㅈㅎㅎㅈㅈㅎㅈㅈㅎㅎㅈㅈㅈㅈㅈㅎㅈㅎㅈㅎㅎㅈㅎㅈㅎㅎㅎㅎㅈㅎㅈㅎㅈㅎㅎㅈㅎㅎㅈㅈㅈㅈㅈㅎㅎㅈㅈㅈㅎㅎㅈㅈㅎㅈㅎㅎㅎㅈㅎㅎㅎ";//5월18일 1회
	   	
	   String str2="";
	   StringBuffer sb = new StringBuffer(str);

	   sb.append(str);
//	   str2 = sb.reverse().toString();
//	   System.out.println("reverse: "+str2);
	   System.out.println(str.length());
////	  str = sb.reverse().toString();
////    System.out.println(str);
//      Scanner sc = new Scanner(System.in);
//      
//      String token = "";
//   
//      System.out.print("input : ");
//      token = sc.nextLine();
	  String token = input;
      int size = token.length(); //비교 문자열 크기
      int index = 0; //찾은 문자열 인덱스
      for(int k=0;k<2;k++){
      if(k==0){
      String result = ""; //찾은 문자열 합한 스트링
      
      ArrayList<String> slist = new ArrayList<String> (); //리스트 문자열
      ArrayList<Integer> ilist = new ArrayList<Integer> (); //리스트 문자열의 카운트 (slist와 같은 인덱스 )
//      System.out.println(str.length());
      
      while(true) {
         index = str.indexOf(token, index); //문자열에서 토큰 찾은 인덱스
////         System.out.println("index : "+index);
         if(index == -1 || index == (str.length() -1)){ //못찾았을때나 맨 마지막 토큰뒤에는 문자가 없으므로 종료
            break;
         }
         
         else {
	        	if(str.length() > index+size){
		        	Character crt = new Character(str.charAt(index + size)); //찾은 토큰 뒤에 문자 하나(char)를 케릭터클래스변경
		        
		            String s = crt.toString(); //케릭터 클래스를 스트링으로 변경
		//            System.out.print(s); //토큰 뒤에 문자 하나 출력 부분
		            
		            result = result.concat(s); //찾은 문자열을 하나의 스트링으로 붙여서 합침
		            
		            if(slist.indexOf(s) == -1) { //새로 문자를 찾았을때
		               slist.add(s);
		               ilist.add(1);
		            }
		            else {  //기존의 문자를 1증가 시킬때
		               int index2 = slist.indexOf(s);
		               ilist.set(index2, ilist.get(index2) + 1);
		            }
		            index ++;
	        	}
	        	else{
	        		break;
	        	}
         }
      }
      System.out.println();
      	//확인 부분
      	for(int i = 0; i < slist.size(); i++) {
            System.out.print("value : " + slist.get(i));
            System.out.println("count : " + ilist.get(i));
        }
        System.out.println("result1 : " + result);
      }//if 1
      else{

          String result = ""; //찾은 문자열 합한 스트링
          
          ArrayList<String> slist = new ArrayList<String> (); //리스트 문자열
          ArrayList<Integer> ilist = new ArrayList<Integer> (); //리스트 문자열의 카운트 (slist와 같은 인덱스 )
//          System.out.println(str.length());
          
          while(true) {
             index = str2.indexOf(token, index); //문자열에서 토큰 찾은 인덱스
////             System.out.println("index : "+index);
             if(index == -1 || index == (str.length() -1)){ //못찾았을때나 맨 마지막 토큰뒤에는 문자가 없으므로 종료
                break;
             }
             
             else {
    	        	if(str2.length() > index+size){
    		        	Character crt = new Character(str2.charAt(index + size)); //찾은 토큰 뒤에 문자 하나(char)를 케릭터클래스변경
    		        
    		            String s = crt.toString(); //케릭터 클래스를 스트링으로 변경
    		//            System.out.print(s); //토큰 뒤에 문자 하나 출력 부분
    		            
    		            result = result.concat(s); //찾은 문자열을 하나의 스트링으로 붙여서 합침
    		            
    		            if(slist.indexOf(s) == -1) { //새로 문자를 찾았을때
    		               slist.add(s);
    		               ilist.add(1);
    		            }
    		            else {  //기존의 문자를 1증가 시킬때
    		               int index2 = slist.indexOf(s);
    		               ilist.set(index2, ilist.get(index2) + 1);
    		            }
    		            index ++;
    	        	}
    	        	else{
    	        		break;
    	        	}
             }
          }
          System.out.println();
          	//확인 부분
          	for(int i = 0; i < slist.size(); i++) {
                System.out.print("one index " + i + "  value : " + slist.get(i));
                System.out.println("   count : " + ilist.get(i));
            }
            System.out.println("result2 : " + result);
            aa = result;
      }
   }
		return aa; 
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
