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
import org.youngjin.net.inbound.InboundFilter;
import org.youngjin.net.inbound.InboundInvoice;
import org.youngjin.net.inbound.InboundService;
import org.youngjin.net.inbound.OnHandList;
import org.youngjin.net.inbound.WeightIb;
import org.youngjin.net.login.User;
import org.youngjin.net.memorandum.Memorandum;
import org.youngjin.net.memorandum.MemorandumList;
import org.youngjin.net.memorandum.MemorandumService;

@Controller
public class InboundController {

	@Resource
	private InboundService inboundService;

	@Resource
	private MemorandumService memorandumService;

	@Resource
	private CodeService codeService;

	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')")
	@RequestMapping(value = "/{process}/freightList", method = RequestMethod.GET)
	public String freightList(Model model, User user,
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

	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')")
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

	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')")
	@RequestMapping(value = "/{process}/freight/add", method = RequestMethod.GET)
	public String freightAdd(Model model, User user,
			@ModelAttribute(value = "gbl") GBL gbl, @PathVariable String process) {
		model.addAttribute("user", user);

		return process + "/freight/add";
	}

	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')")
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

	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')")
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

	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')")
	@RequestMapping(value = "/{process}/freight/{seq}/weight", method = RequestMethod.POST)
	public String weightAddPost(Model model, User user,
			@PathVariable String process, @PathVariable Integer seq,
			@ModelAttribute WeightIb weightIb) {
		inboundService.insertWeightAdd(weightIb);
		model.addAttribute("user", user);

		model.addAttribute("process",
				inboundService.getGblProcessAndUpload(seq));
		model.addAttribute("seq", seq);
		model.addAttribute("fileList", inboundService.getGblFileList(seq));

		return process + "/freight/processAndUpload";
	}

	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')")
	@RequestMapping(value = "/{process}/freight/checkWeight.json")
	@ResponseBody
	public Boolean checkWeight(@RequestBody Map<String, Integer> param) {
		return inboundService.checkWeight(param);
	}

	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')")
	@RequestMapping(value = "/{process}/custom/invoice", method = RequestMethod.GET)
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

	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')")
	@RequestMapping(value = "/{process}/custom/invoice/add", method = RequestMethod.GET)
	public String customInvoiceAdd(Model model, User user,
			@PathVariable String process,
			@ModelAttribute InboundFilter inboundFilter) {
		inboundFilter.getPagination().setNumItems(
				inboundService.getCustomInvoiceGblListCount(inboundFilter));

		model.addAttribute("gblList",
				inboundService.getCustomInvoiceGblList(inboundFilter));

		return process + "/custom/invoiceAdd";
	}

	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')")
	@RequestMapping(value = "/{process}/custom/invoice/{gblSeq}/setting", method = RequestMethod.GET)
	public String customInboundInvoiceAddSetting(Model model, User user,
			@PathVariable String process, @PathVariable Integer gblSeq) {

		model.addAttribute("gblSeq", gblSeq);

		model.addAttribute("settingValueMap",
				inboundService.getInboundInvoiceSettingMap(gblSeq));

		return process + "/custom/invoiceAddSetting";
	}

	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')")
	@RequestMapping(value = "/{process}/custom/invoice/inboundInvoiceAdd.json", method = RequestMethod.POST)
	@ResponseBody
	public Integer inputCustomInboundInvoiceAddSettingAdd(
			@RequestBody InboundInvoice inboundInvoice) {
		return inboundService
				.inputCustomInboundInvoiceAddSetting(inboundInvoice);
	}

	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')")
	@RequestMapping(value = "/{process}/custom/invoice/inboundInvoiceDelete.json", method = RequestMethod.POST)
	@ResponseBody
	public void inboundInvoiceDelete(
			@RequestBody Map<String, Integer> inboundInvoiceMap) {
		inboundService.inboundInvoiceDelete(inboundInvoiceMap);
	}

	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')")
	@RequestMapping(value = "/{process}/custom/invoice/checkInboundInvoiceWeight.json", method = RequestMethod.POST)
	@ResponseBody
	public Boolean checkInboundInvoiceWeight(
			@RequestBody InboundInvoice inboundInvoice) {
		return inboundService.checkInboundInvoiceWeight(inboundInvoice);
	}

	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')")
	@RequestMapping(value = "/{process}/custom/invoice/{inboundInvoiceSeq}/selectWeight", method = RequestMethod.GET)
	public String customInboundInvoiceSelectWeight(Model model, User user,
			@PathVariable String process,
			@PathVariable Integer inboundInvoiceSeq) {

		InboundInvoice inboundInvoice = inboundService
				.getInboundInvoiceBasicInfo(inboundInvoiceSeq);

		model.addAttribute("inboundInvoiceSeq", inboundInvoiceSeq);

		model.addAttribute("inboundInvoiceBasicInfo", inboundInvoice);

		model.addAttribute("weightList",
				inboundService.getWeightList(inboundInvoice.getGblSeq()));

		return process + "/custom/invoiceAddWeight";
	}

	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')")
	@RequestMapping(value = "/{process}/custom/invoice/invoiceSelectWeightAdd.json", method = RequestMethod.POST)
	@ResponseBody
	public void inboundInvoiceWeightAdd(
			@RequestBody Map<String, String> inboundInvoiceWeightMap) {
		inboundService.inboundInvoiceWeightAdd(inboundInvoiceWeightMap);
	}

	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')")
	@RequestMapping(value = "/{process}/custom/declarationList", method = RequestMethod.GET)
	public String declarationList(Model model, User user,
			@PathVariable String process,
			@ModelAttribute InboundFilter inboundFilter) {

		user.setSubProcess("declare");
		inboundFilter.getPagination().setNumItems(
				inboundService.getDeclarationListCount(inboundFilter));

		List<InboundInvoice> declarationList = inboundService
				.getDeclarationList(inboundFilter);
		model.addAttribute("declarationList", declarationList);
		model.addAttribute("user", user);

		return process + "/custom/declarationList";
	}

	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')")
	@RequestMapping(value = "/{process}/custom/declarationListSelect", method = RequestMethod.GET)
	public String declarationListSelect(Model model, User user,
			@PathVariable String process,
			@ModelAttribute InboundFilter inboundFilter) {

		user.setSubProcess("declare");

		List<InboundInvoice> inboundInvoiceList = inboundService
				.getInboundInvoiceDeclarationList();
		model.addAttribute("inboundInvoiceList", inboundInvoiceList);
		model.addAttribute("user", user);

		return process + "/custom/declarationSelect";
	}

	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')")
	@RequestMapping(value = "/{process}/custom/invoice/declarationListSelectAdd.json", method = RequestMethod.POST)
	@ResponseBody
	public void declarationListSelectAdd(
			@RequestBody Map<String, String> inboundInvoiceMap) {
		inboundService.declarationListSelectAdd(inboundInvoiceMap);
	}

	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')")
	@RequestMapping(value = "/{process}/custom/declarationListDelete.json", method = RequestMethod.POST)
	@ResponseBody
	public void declarationListDelete(
			@RequestBody Map<String, Integer> inboundInvoiceMap) {
		inboundService.declarationListDelete(inboundInvoiceMap);
	}

	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')")
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
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')")
	@RequestMapping(value = "/{process}/onHand/onHandList", method = RequestMethod.GET)
	public String onHandList(Model model, User user,
			@PathVariable String process,
			@ModelAttribute InboundFilter inboundFilter) {

		user.setSubProcess("onHandList");
		inboundFilter.getPagination().setNumItems(
				inboundService.getOnHandListCount(inboundFilter));

		model.addAttribute("onHandList",
				inboundService.getOnHandList(inboundFilter));
		model.addAttribute("user", user);

		return process + "/onHand/onHandList";
	}

	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')")
	@RequestMapping(value = "/{process}/onHand/checkSelectOnHandList.json")
	@ResponseBody
	public boolean checkSelectOnHandList(@RequestBody Map<String, Integer> map) {
		return inboundService.checkSelectonHandList(map);
	}

	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')")
	@RequestMapping(value = "/{process}/onHand/onHandListAddSetting", method = RequestMethod.GET)
	public String onHandListAddSetting(Model model, User user,
			@PathVariable String process) {

		user.setSubProcess("onHandList");
		model.addAttribute("user", user);

		return process + "/onHand/onHandListAddSetting";
	}

	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')")
	@RequestMapping(value = "/{process}/onHand/onHandListAdd.json")
	@ResponseBody
	public Integer onHandListAdd(@RequestBody OnHandList onHandList) {
		return inboundService.onHandListAdd(onHandList);
	}

	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')")
	@RequestMapping(value = "/{process}/onHand/{seq}/onHandListSelect", method = RequestMethod.GET)
	public String onHandListContentList(Model model, User user,
			@PathVariable String process, @PathVariable Integer seq) {

		user.setSubProcess("onHandList");
		model.addAttribute("onHandListSeq", seq);
		model.addAttribute("inboundInvoiceList",
				inboundService.getInboundInvoiceOnHandList(seq));
		model.addAttribute("user", user);

		return process + "/onHand/onHandListSelect";
	}

	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')")
	@RequestMapping(value = "/{process}/onHand/checkOnHandListContentWeight.json")
	@ResponseBody
	public boolean checkSelectOnHandListContentWeight(
			@RequestBody Map<String, Integer> map) {
		return inboundService.checkSelectonHandListContentWeight(map);
	}

	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')")
	@RequestMapping(value = "/{process}/onHand/{seq}/{gblSeq}/getWeight", method = RequestMethod.GET)
	public String getOnHandListContentWeightList(Model model, User user,
			@PathVariable String process, @PathVariable Integer seq,
			@PathVariable Integer gblSeq) {

		user.setSubProcess("onHandList");
		model.addAttribute("onHandListSeq", seq);
		model.addAttribute("gblSeq", gblSeq);
		
		model.addAttribute("weightList",
				inboundService.getWeightList(gblSeq));
		model.addAttribute("user", user);

		return process + "/onHand/onHandListContentWeightSelect";
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')")
	@RequestMapping(value = "/{process}/onHand/{seq}/{gblSeq}/{onHandListContentSeq}/getWeight", method = RequestMethod.GET)
	public String getOnHandListContentWeightListByContentSeq(Model model, User user,
			@PathVariable String process, @PathVariable Integer seq,
			@PathVariable Integer gblSeq, @PathVariable Integer onHandListContentSeq) {

		user.setSubProcess("onHandList");
		model.addAttribute("onHandListSeq", seq);
		model.addAttribute("onHandListContentSeq", onHandListContentSeq);
		model.addAttribute("gblSeq", gblSeq);
		
		model.addAttribute("checkWeightList", inboundService.getCheckWeightList(onHandListContentSeq));
		model.addAttribute("weightList",
				inboundService.getWeightList(gblSeq));
		model.addAttribute("user", user);

		return process + "/onHand/onHandListContentWeightSelect";
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')")
	@RequestMapping(value = "/{process}/onHand/onHandListContentWeightAdd.json")
	@ResponseBody
	public void onHandListContentWeightAdd(
			@RequestBody Map<String, String> map) {
		inboundService.onHandListContentWeightAdd(map);
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')")
	@RequestMapping(value = "/{process}/onHand/onHandListContentSelectAdd.json")
	@ResponseBody
	public void onHandListContentSelectAdd(
			@RequestBody Map<String, String> map) {
		inboundService.onHandListContentSelectAdd(map);
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')")
	@RequestMapping(value = "/{process}/onHand/onHandListDelete.json")
	@ResponseBody
	public void onHandListDelete(
			@RequestBody Map<String, String> map) {
		inboundService.onHandListDelete(map);
	}

	// -process
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')")
	@RequestMapping(value = "/{process}/freight/{seq}", method = RequestMethod.GET)
	public String gblSelect(Model model, User user,
			@PathVariable String process, @PathVariable Integer seq) {

		model.addAttribute("process",
				inboundService.getGblProcessAndUpload(seq));
		model.addAttribute("seq", seq);
		model.addAttribute("fileList", inboundService.getGblFileList(seq));

		return process + "/freight/processAndUpload";
	}

	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')")
	@RequestMapping(value = "/{process}/freight/{seq}/delivery", method = RequestMethod.GET)
	public String gblDelivery(Model model, User user,
			@PathVariable String process, @PathVariable String seq,
			@ModelAttribute(value = "gbl") GBL gbl) {

		model.addAttribute("seq", seq);

		return process + "/freight/delivery";
	}

	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')")
	@RequestMapping(value = "/{process}/freight/{seq}/memorandumList", method = RequestMethod.GET)
	public String memorandumList(Model model, User user,
			@PathVariable String process, @PathVariable String seq) {

		List<MemorandumList> memorandumList = memorandumService
				.getMemorandumAllList(seq, "inbound");

		model.addAttribute("seq", seq);
		model.addAttribute("memroandumList", memorandumList);

		return process + "/freight/memorandumAllList";
	}

	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')")
	@RequestMapping(value = "/{process}/freight/{seq}/upload", method = RequestMethod.GET)
	public String gblSelectUplaod(Model model, User user,
			@PathVariable String process, @PathVariable String seq,
			@ModelAttribute(value = "gbl") GBL gbl) {

		model.addAttribute("seq", seq);
		return process + "/freight/upload";
	}

	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')")
	@RequestMapping(value = "/{process}/freight/{seq}/upload", method = RequestMethod.POST)
	public void gblSelectUplaodPost(Model model, User user,
			@PathVariable String process, @PathVariable String seq,
			@ModelAttribute(value = "gbl") GBL gbl) {

		model.addAttribute("seq", seq);
		inboundService.insertGblFile(gbl);
	}

	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')")
	@RequestMapping(value = "/{process}/freight/{seq}/bl", method = RequestMethod.GET)
	public String blSeperate(Model model, User user,
			@PathVariable String process, @PathVariable String seq,
			@ModelAttribute(value = "gbl") GBL gbl) {

		model.addAttribute("seq", seq);
		return process + "/freight/bl";
	}

	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')")
	@RequestMapping(value = "/{process}/freight/addMemorandumAndDd619.json")
	@ResponseBody
	public MemorandumList addMemorandumAndDd619(
			@RequestBody MemorandumList memorandumList, User user) {
		return memorandumService.addMemorandumAndDd619(memorandumList, user,
				"inbound");
	}

	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')")
	@RequestMapping(value = "/{process}/freight/deleteMemorandumAllList.json")
	@ResponseBody
	public void deleteMemorandumAllList(
			@RequestBody MemorandumList memorandumList) {
		memorandumService.deleteMemorandumAllList(memorandumList, "inbound");
	}

	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')")
	@RequestMapping(value = "/{process}/freight/{seq}/{memorandumSeq}/memorandum", method = RequestMethod.GET)
	public String gblMemorandum(Model model, User user,
			@PathVariable String process, @PathVariable String seq,
			@PathVariable Integer memorandumSeq) {

		GBL gbl = inboundService.getGbl(Integer.parseInt(seq));

		List<Code> memorandumList = codeService.getCodeList("03");
		Map<String, Memorandum> checkMemorandumMap = memorandumService
				.getMemorandumMap(seq, memorandumSeq, "inbound");

		model.addAttribute("seq", seq);
		model.addAttribute("memorandumSeq", memorandumSeq);
		model.addAttribute("gbl", gbl);
		model.addAttribute("memorandumList", memorandumList);

		if (checkMemorandumMap.get("02") != null
				&& checkMemorandumMap.get("02").getArticles() != null)
			model.addAttribute("articles", checkMemorandumMap.get("02")
					.getArticles());

		model.addAttribute("checkMemorandumMap", checkMemorandumMap);

		return process + "/freight/memorandumList";
	}

	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')")
	@RequestMapping(value = "/{process}/freight/{seq}/{memorandumSeq}/memorandum/{type}", method = RequestMethod.GET)
	public String gblMemorandumForm(Model model, User user,
			@PathVariable String process, @PathVariable String seq,
			@PathVariable Integer memorandumSeq, @PathVariable String type) {

		GBL gbl = inboundService.getGbl(Integer.parseInt(seq));

		Code code = codeService.getCode("03", type);

		Memorandum memorandom = memorandumService.getMemorandum(seq, type,
				memorandumSeq, "inbound");

		model.addAttribute("seq", seq);
		model.addAttribute("gbl", gbl);
		model.addAttribute("memorandumSeq", memorandumSeq);
		model.addAttribute("type", type);
		model.addAttribute("memorandum", code);
		model.addAttribute("checkMemorandum", memorandom);

		return process + "/freight/memorandumForm";
	}

	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')")
	@RequestMapping(value = "/{process}/freight/{seq}/{memorandumSeq}/memorandum/{type}/{article}", method = RequestMethod.GET)
	public String gblMemorandumFormArticle(Model model, User user,
			@PathVariable String process, @PathVariable String seq,
			@PathVariable Integer memorandumSeq, @PathVariable String type,
			@PathVariable String article) {

		GBL gbl = inboundService.getGbl(Integer.parseInt(seq));

		Code code = codeService.getCode("03", type);

		Memorandum memorandom = memorandumService.getMemorandum(seq, type,
				memorandumSeq, "inbound");

		String[] articles = article.split(",");

		model.addAttribute("seq", seq);
		model.addAttribute("gbl", gbl);
		model.addAttribute("type", type);
		model.addAttribute("memorandumSeq", memorandumSeq);
		model.addAttribute("memorandum", code);
		model.addAttribute("articleComa", article);
		model.addAttribute("articles", articles);
		model.addAttribute("checkMemorandum", memorandom);

		return process + "/freight/memorandumForm";
	}

	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')")
	@RequestMapping(value = "/{process}/freight/{seq}/memorandum/memorandumInput.json", method = RequestMethod.POST)
	@ResponseBody
	public void gblMemorandumInput(@RequestBody Memorandum memorandum) {
		memorandumService.insertMemorandum(memorandum, "inbound");
	}

	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')")
	@RequestMapping(value = "/{process}/freight/{seq}/memorandum/memorandumUpdate.json", method = RequestMethod.POST)
	@ResponseBody
	public void gblMemorandumUpdate(@RequestBody Memorandum memorandum) {
		memorandumService.updateMemorandum(memorandum, "inbound");
	}

	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')")
	@RequestMapping(value = "/{process}/freight/{seq}/{memorandumSeq}/memorandum/{type}/delete.json", method = RequestMethod.POST)
	@ResponseBody
	public void gblMemorandumDelete(@PathVariable String seq,
			@PathVariable String type, @PathVariable Integer memorandumSeq) {
		memorandumService.deleteMemorandum(seq, type, memorandumSeq, "inbound");
	}

	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')")
	@RequestMapping(value = "/{process}/freight/memorandum/invoice/{article}/insert.json", method = RequestMethod.POST)
	@ResponseBody
	public void insertInvoiceMemorandum(@RequestBody Memorandum memorandum,
			@PathVariable String article) {
		memorandumService.insertInvoiceMemorandum(memorandum, "inbound");
	}

	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')")
	@RequestMapping(value = "/{process}/freight/memorandum/invoice/{article}/modify.json", method = RequestMethod.POST)
	@ResponseBody
	public void modifyInvoiceMemorandum(@RequestBody Memorandum memorandum,
			@PathVariable String article) {
		memorandumService.modifyInvoiceMemorandum(memorandum, "inbound");
	}

	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')")
	@RequestMapping(value = "/{process}/freight/{seq}/{memorandumSeq}/memorandum/{type}/print", method = RequestMethod.GET)
	public String gblMemorandumPrint(Model model, User user,
			@PathVariable String process, @PathVariable String seq,
			@PathVariable Integer memorandumSeq, @PathVariable String type) {

		GBL gbl = inboundService.getGbl(Integer.parseInt(seq));

		Code code = codeService.getCode("03", type);

		Memorandum memorandom = memorandumService.getMemorandum(seq, type,
				memorandumSeq, "inbound");

		model.addAttribute("seq", seq);
		model.addAttribute("gbl", gbl);
		model.addAttribute("memorandumSeq", memorandumSeq);
		model.addAttribute("type", type);
		model.addAttribute("memorandum", code);
		model.addAttribute("checkMemorandum", memorandom);

		return process + "/freight/memorandumPrint";
	}

	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')")
	@RequestMapping(value = "/{process}/freight/{seq}/{memorandumSeq}/memorandum/{type}/{article}/print", method = RequestMethod.GET)
	public String gblMemorandumPrintArticle(Model model, User user,
			@PathVariable String process, @PathVariable String seq,
			@PathVariable Integer memorandumSeq, @PathVariable String type,
			@PathVariable String article) {

		GBL gbl = inboundService.getGbl(Integer.parseInt(seq));

		Code code = codeService.getCode("03", type);

		Memorandum memorandom = memorandumService.getMemorandum(seq, type,
				memorandumSeq, "inbound");

		String[] articles = article.split(",");

		model.addAttribute("seq", seq);
		model.addAttribute("gbl", gbl);
		model.addAttribute("memorandumSeq", memorandumSeq);
		model.addAttribute("type", type);
		model.addAttribute("memorandum", code);
		model.addAttribute("articleComa", article);
		model.addAttribute("articles", articles);
		model.addAttribute("checkMemorandum", memorandom);

		return process + "/freight/memorandumPrint";
	}

	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')")
	@RequestMapping(value = "/{process}/freight/{seq}/dd619List", method = RequestMethod.GET)
	public String dd619List(Model model, User user,
			@PathVariable String process, @PathVariable String seq) {

		List<Dd619> dd619List = inboundService.getDd619List(seq);

		model.addAttribute("seq", seq);
		model.addAttribute("dd619List", dd619List);

		return process + "/freight/dd619List";
	}

	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')")
	@RequestMapping(value = "/{process}/freight/{seq}/{dd619Seq}/dd619Modify", method = RequestMethod.GET)
	public String dd619Modify(Model model, User user,
			@PathVariable String process, @PathVariable String seq,
			@PathVariable Integer dd619Seq, @ModelAttribute Dd619 dd619) {

		dd619 = inboundService.getDd619ListSelectOne(dd619Seq);

		model.addAttribute("user", user);
		model.addAttribute("gbl", inboundService.getGbl(Integer.parseInt(seq)));
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

	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')")
	@RequestMapping(value = "/{process}/freight/{seq}/dd619/modify.json", method = RequestMethod.POST)
	@ResponseBody
	public void gblDd619ModifySubmit(@RequestBody Dd619 dd619) {
		inboundService.modifyDd619(dd619);
	}

	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')")
	@RequestMapping(value = "/{process}/freight/{seq}/dd619/{listSeq}/print", method = RequestMethod.GET)
	public String dd619(Model model, User user, @PathVariable String process,
			@PathVariable String seq, @PathVariable Integer listSeq,
			@ModelAttribute Dd619 dd619) {

		dd619 = inboundService.getDd619ListSelectOne(listSeq);

		model.addAttribute("user", user);
		model.addAttribute("gbl", inboundService.getGbl(Integer.parseInt(seq)));
		model.addAttribute("dd619", dd619);
		model.addAttribute(
				"remarkList",
				memorandumService.getMemorandumList(seq,
						dd619.getMemorandumListSeq(), process));
		model.addAttribute("seq", seq);

		return process + "/freight/dd619";
	}

	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')")
	@RequestMapping(value = "/{process}/freight/{seq}/additional", method = RequestMethod.GET)
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

		// List<Addition> additionList = outboundService.getAddtionList(seq);

		model.addAttribute("checkMemorandumMap", checkMemorandumMap);
		// model.addAttribute("additionList", additionList);

		return process + "/gbl/additionalDecide";
	}
}
