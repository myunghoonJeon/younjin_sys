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
import org.youngjin.net.code.Code;
import org.youngjin.net.code.CodeService;
import org.youngjin.net.login.User;
import org.youngjin.net.memorandum.Memorandum;
import org.youngjin.net.memorandum.MemorandumService;
import org.youngjin.net.outbound.OutboundFilter;
import org.youngjin.net.outbound.OutboundService;
import org.youngjin.net.outbound.PreMoveSurvey;
import org.youngjin.net.process.GBlock;
import org.youngjin.net.upload.DownloadView;

@Controller
@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER') or hasRole('ROLE_NORMAL')")
public class OutboundController {

	@Resource
	private OutboundService outboundService;
	
	@Resource
	private CodeService codeService;
	
	@Resource
	private MemorandumService memorandumService;

	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')")
	@RequestMapping(value = "/{process}/gblList", method = RequestMethod.GET)
	public String gblList(Model model, User user,
			@ModelAttribute OutboundFilter outboundFilter,
			@PathVariable String process) {

		outboundFilter.getPagination().setNumItems(
				outboundService.getGblListCount(outboundFilter));

		model.addAttribute("filterMap", outboundService.getFilterMap());

		model.addAttribute("gblList",
				outboundService.getGblList(outboundFilter));
		model.addAttribute("gblStatus", outboundService.getGblStatus(outboundFilter));
		model.addAttribute("user", user);

		return process + "/gbl/list";
	}

	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')")
	@RequestMapping(value = "/{process}/gblList", method = RequestMethod.POST)
	public String gblListPost(Model model, User user,
			@ModelAttribute OutboundFilter outboundFilter,
			@PathVariable String process) {

		outboundFilter.getPagination().setNumItems(
				outboundService.getGblListCount(outboundFilter));

		model.addAttribute("filterMap", outboundService.getFilterMap());

		model.addAttribute("gblList",
				outboundService.getGblList(outboundFilter));
		model.addAttribute("user", user);

		return process + "/gbl/list";
	}

	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')")
	@RequestMapping(value = "/{process}/add", method = RequestMethod.GET)
	public String gblAdd(Model model, User user,
			@ModelAttribute(value = "gbl") GBL gbl, @PathVariable String process) {
		model.addAttribute("user", user);

		return process + "/gbl/add";
	}

	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')")
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

	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')")
	@RequestMapping(value = "/{process}/{seq}", method = RequestMethod.GET)
	public String gblSelect(Model model, User user,
			@PathVariable String process, @PathVariable String seq) {
		
		model.addAttribute("process", outboundService.getGblProcessAndUpload(Integer.parseInt(seq)));
		model.addAttribute("seq", seq);
		model.addAttribute("fileList", outboundService.getGblFileList(Integer.parseInt(seq)));
		
		return process + "/gbl/processAndUpload";
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')")
	@RequestMapping(value = "/{process}/{seq}/upload", method = RequestMethod.GET) 
	public String gblSelectUplaod(Model model, User user,
			@PathVariable String process, @PathVariable String seq, @ModelAttribute(value="gbl") GBL gbl){
		
			model.addAttribute("seq", seq);
		return process + "/gbl/upload";
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')")
	@RequestMapping(value = "/{process}/{seq}/upload", method = RequestMethod.POST) 
	public void gblSelectUplaodPost(Model model, User user,
			@PathVariable String process, @PathVariable String seq, @ModelAttribute(value="gbl") GBL gbl){
		
			model.addAttribute("seq", seq);
			outboundService.insertGblFile(gbl);
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')")
	@RequestMapping(value = "/{process}/{seq}/preparation", method = RequestMethod.GET) 
	public String gblPreparation(Model model, User user,
			@PathVariable String process, @PathVariable String seq){
		
			model.addAttribute("seq", seq);
			
			return process + "/gbl/preparation";			
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')")
	@RequestMapping(value = "/{process}/{seq}/preMoveSurvey", method = RequestMethod.GET) 
	public String gblPreMoveSurvey(Model model, User user,
			@PathVariable String process, @ModelAttribute(value="preMoveSurvey") PreMoveSurvey preMoveSurvey, @PathVariable String seq){
		
			PreMoveSurvey paramPreMoveSurvey = outboundService.getPreMoveSurvey(Integer.parseInt(seq));
			
			if ( paramPreMoveSurvey == null ){
				paramPreMoveSurvey = new PreMoveSurvey();
			}
		
			model.addAttribute("seq", seq);
			model.addAttribute("preMoveSurvey", paramPreMoveSurvey);		
			
			return process + "/gbl/preMoveSurvey";			
	}	
	
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')")
	@RequestMapping(value = "/{process}/{seq}/memorandum", method = RequestMethod.GET) 
	public String gblMemorandum(Model model, User user,
			@PathVariable String process, @PathVariable String seq){
	
			GBL gbl = outboundService.getGbl(Integer.parseInt(seq));
			
			List<Code> memorandumList = codeService.getCodeList("03");
			Map<String, Memorandum> checkMemorandumMap = memorandumService.getMemorandumMap(seq);
		
			model.addAttribute("seq", seq);
			model.addAttribute("gbl", gbl);
			model.addAttribute("memorandumList", memorandumList);	
			
			if( checkMemorandumMap.get("02") != null && checkMemorandumMap.get("02").getArticles() != null )
				model.addAttribute("articles", checkMemorandumMap.get("02").getArticles());
			
			model.addAttribute("checkMemorandumMap", checkMemorandumMap);
			
			return process + "/gbl/memorandumList";			
	}	
	
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')")
	@RequestMapping(value = "/{process}/{seq}/memorandum/{type}", method = RequestMethod.GET) 
	public String gblMemorandumForm(Model model, User user,
			@PathVariable String process, @PathVariable String seq, @PathVariable String type){
	
			GBL gbl = outboundService.getGbl(Integer.parseInt(seq));
			
			Code code = codeService.getCode("03", type);
			
			Memorandum memorandom = memorandumService.getMemorandum(seq, type);
		
			model.addAttribute("seq", seq);
			model.addAttribute("gbl", gbl);
			model.addAttribute("type", type);
			model.addAttribute("memorandum", code);		
			model.addAttribute("checkMemorandum", memorandom);
			
			return process + "/gbl/memorandumForm";			
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')")
	@RequestMapping(value = "/{process}/{seq}/memorandum/{type}/{article}", method = RequestMethod.GET) 
	public String gblMemorandumFormArticle(Model model, User user,
			@PathVariable String process, @PathVariable String seq, @PathVariable String type, @PathVariable String article){
		
		GBL gbl = outboundService.getGbl(Integer.parseInt(seq));
		
		Code code = codeService.getCode("03", type);
		
		Memorandum memorandom = memorandumService.getMemorandum(seq, type);
		
		String [] articles = article.split(",");
		
		model.addAttribute("seq", seq);
		model.addAttribute("gbl", gbl);
		model.addAttribute("type", type);
		model.addAttribute("memorandum", code);	
		model.addAttribute("articleComa", article);
		model.addAttribute("articles", articles);
		model.addAttribute("checkMemorandum", memorandom);	
		
		return process + "/gbl/memorandumForm";			
	}	
	
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')")
	@RequestMapping(value = "/{process}/{seq}/dd619List", method = RequestMethod.GET) 
	public String dd619List(Model model, User user,
			@PathVariable String process, @PathVariable String seq){
		
		List<Dd619> dd619List = outboundService.getDd619List(seq);
		
		model.addAttribute("seq", seq);
		model.addAttribute("dd619List", dd619List);
		
		return process + "/gbl/dd619List";			
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')")
	@RequestMapping(value = "/{process}/{seq}/dd619Add", method = RequestMethod.GET) 
	public String dd619Add(Model model, User user,
			@PathVariable String process, @PathVariable String seq, @ModelAttribute Dd619 dd619){
		
		model.addAttribute("user", user);
		model.addAttribute("gbl", outboundService.getGbl(Integer.parseInt(seq)));
		model.addAttribute("remarkList", memorandumService.getMemorandumList(seq));
		model.addAttribute("seq", seq);
		
		return process + "/gbl/dd619Add";			
	}	
	
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')")
	@RequestMapping(value = "/{process}/{seq}/dd619", method = RequestMethod.GET) 
	public String dd619(Model model, User user,
			@PathVariable String process, @PathVariable String seq){
		
		model.addAttribute("seq", seq);
		
		return process + "/gbl/dd619";			
	}		
	
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')")
	@RequestMapping(value = "/{process}/{seq}/dd619/add.json", method = RequestMethod.POST)
	@ResponseBody
	public void gblDd619AddSubmit(@RequestBody Dd619 dd619) {
		outboundService.insertDd619(dd619);
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')")
	@RequestMapping(value = "/{process}/{seq}/dd619/update.json", method = RequestMethod.POST)
	@ResponseBody
	public void gblDd619UpdateSubmit(@RequestBody Dd619 dd619) {
		outboundService.updateDd619(dd619);
	}	
	

	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')")
	@RequestMapping(value = "/{process}/{seq}/memorandum/{type}/delete.json", method = RequestMethod.POST)
	@ResponseBody
	public void gblMemorandumDelete(@PathVariable String seq, @PathVariable String type) {
		memorandumService.deleteMemorandum(seq, type);
	}

	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')")
	@RequestMapping(value = "/{process}/{seq}/memorandum/memorandumInput.json", method = RequestMethod.POST)
	@ResponseBody
	public void gblMemorandumInput(@RequestBody Memorandum memorandum) {
		memorandumService.insertMemorandum(memorandum);
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')")
	@RequestMapping(value = "/{process}/{seq}/memorandum/memorandumUpdate.json", method = RequestMethod.POST)
	@ResponseBody
	public void gblMemorandumUpdate(@RequestBody Memorandum memorandum) {
		memorandumService.updateMemorandum(memorandum);
	}	
	
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')")
	@RequestMapping(value = "/{process}/{seq}/preMoveSurveySubmit.json", method = RequestMethod.POST)
	@ResponseBody
	public void gblPreMoveSurveySubmit(@RequestBody PreMoveSurvey preMoveSurvey) {
		outboundService.insertPreMoveSurvey(preMoveSurvey);
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')")
	@RequestMapping(value = "/{process}/{seq}/preMoveSurveyEditSubmit.json", method = RequestMethod.POST)
	@ResponseBody
	public void gblPreMoveSurveyEditSubmit(@RequestBody PreMoveSurvey preMoveSurvey, @PathVariable String seq) {
		outboundService.updatePreMoveSurvey(preMoveSurvey);
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')")
	@RequestMapping(value = "/{process}/findUsNo.json", method = RequestMethod.POST)
	@ResponseBody
	public GBlock findUsNo(@RequestBody GBlock gBlock) {

		return outboundService.findUsNo(gBlock);
	}
	
	/**
	 * DeliveryControl
	 */
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')")
	@RequestMapping(value = "/{process}/delivery/main", method = RequestMethod.GET)	
	public String DeliveryMain(Model model, User user, @PathVariable String process) {
		
		model.addAttribute("user", user);
		
		model.addAttribute("deliveryList", null);

		return process + "/delivery/main";
	}	
	
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')")
	@RequestMapping(value = "/{process}/delivery/add", method = RequestMethod.GET)	
	public String DeliveryAdd(Model model, User user, @PathVariable String process) {
		
		model.addAttribute("user", user);

		return process + "/delivery/add";
	}		
	
	/**
	 * DownLoadControl
	 */

	@Resource
	private DownloadView downloadView;

	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')")
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
