package org.youngjin.net;

import java.util.List;
import java.util.Map;

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
import org.youngjin.net.basic.Carrier;
import org.youngjin.net.basic.Company;
import org.youngjin.net.code.CodeService;
import org.youngjin.net.invoice.Invoice;
import org.youngjin.net.invoice.InvoiceCollection;
import org.youngjin.net.invoice.InvoiceFilter;
import org.youngjin.net.invoice.InvoiceGbl;
import org.youngjin.net.invoice.InvoiceGblContent;
import org.youngjin.net.invoice.InvoiceGblFilter;
import org.youngjin.net.invoice.InvoicePdfPrintView;
import org.youngjin.net.invoice.InvoiceService;
import org.youngjin.net.login.User;

@Controller
public class InvoiceController {

	@Resource
	private InvoiceService invoiceService;

	@Resource
	private CodeService codeService;
	
	@Resource
	private BasicService basicService;

	@PreAuthorize("hasRole('ROLE_LEVEL4') or hasRole('ROLE_LEVEL3')")
	@RequestMapping(value = "/{process}/invoice", method = RequestMethod.GET)
	public String invoiceMain(Model model, User user,
			@ModelAttribute InvoiceFilter invoiceFilter,
			@PathVariable String process) {

		user.setSubProcess("invoice");
		
		invoiceFilter.setProcess(process);

//		invoiceFilter.getPagination().setNumItems(
//				invoiceService.getInvoiceListCount(invoiceFilter, process));
//
//		List<Invoice> invoiceList = invoiceService
//				.getInvoiceList(invoiceFilter);
//
//		model.addAttribute("filterMap", invoiceService.getInvoiceFilterMap(invoiceFilter));
//
//		model.addAttribute("user", user);
//		model.addAttribute("invoiceList", invoiceList);
		invoiceFilter.getPagination().setNumItems(invoiceService.getInvoiceListCount(invoiceFilter, process));
		List<Invoice> invoiceList = invoiceService.getInvoiceList(invoiceFilter);
		System.out.println("======== PAGINATION SIZE : "+invoiceFilter.getPagination().getNumItems()+" ============");
		System.out.println("======== CURRENT PAGE : "+invoiceFilter.getPagination().getCurrentPage()+" ======");
		System.out.println("======== INVOICE LIST SIZE : "+invoiceList.size()+" ============");
		model.addAttribute("filterMap", invoiceService.getInvoiceFilterMap(invoiceFilter));
		model.addAttribute("user", user);
		model.addAttribute("invoiceList", invoiceList);
		return process + "/invoice/main";
	}
	
	@PreAuthorize("hasRole('ROLE_LEVEL4') or hasRole('ROLE_LEVEL3')")
	@RequestMapping(value = "/{process}/invoice", method = RequestMethod.POST)
	public String invoiceMainPost(Model model, User user,
			@ModelAttribute InvoiceFilter invoiceFilter,
			@PathVariable String process) {

		user.setSubProcess("invoice");

		invoiceFilter.setProcess(process);

		invoiceFilter.getPagination().setNumItems(
				invoiceService.getInvoiceListCount(invoiceFilter, process));
		
		List<Invoice> invoiceList = invoiceService
				.getInvoiceList(invoiceFilter);

		model.addAttribute("filterMap", invoiceService.getInvoiceFilterMap(invoiceFilter));

		model.addAttribute("user", user);
		model.addAttribute("invoiceList", invoiceList);

		return process + "/invoice/main";
	}	

	@PreAuthorize("hasRole('ROLE_LEVEL4') or hasRole('ROLE_LEVEL3')")
	@RequestMapping(value = "/{process}/invoice/invoiceAddSetting", method = RequestMethod.GET)
	public String invoiceAddSetting(Model model, User user, @ModelAttribute InvoiceGblFilter invoiceGblFilter,
			@PathVariable String process) {
		
		invoiceGblFilter.setProcess(process);
		
		invoiceGblFilter.getPagination().setNumItems(
				invoiceService.getInvoiceSettingGblListCount(invoiceGblFilter));
		
		List<GBL> invoiceGblList = invoiceService.getInvoiceSettingGblList(invoiceGblFilter);

		model.addAttribute("filterMap", invoiceService.getFilterMap(invoiceGblFilter));
		
		model.addAttribute("gblList", invoiceGblList);

		return process + "/invoice/invoiceAddSettingPop";
	}

	@PreAuthorize("hasRole('ROLE_LEVEL4') or hasRole('ROLE_LEVEL3')")
	@RequestMapping(value = "/{process}/invoice/invoiceAddSetting", method = RequestMethod.POST)
	public String invoiceAddSettingPost(Model model, User user, @ModelAttribute InvoiceGblFilter invoiceGblFilter,
			@PathVariable String process) {
		
		invoiceGblFilter.setProcess(process);
		invoiceGblFilter.getPagination().setNumItems(invoiceService.getInvoiceSettingGblListCount(invoiceGblFilter));
		List<GBL> invoiceGblList = invoiceService.getInvoiceSettingGblList(invoiceGblFilter);
		model.addAttribute("filterMap", invoiceService.getFilterMap(invoiceGblFilter));
		model.addAttribute("gblList", invoiceGblList);

		return process + "/invoice/invoiceAddSettingPop";
	}

	@PreAuthorize("hasRole('ROLE_LEVEL4') or hasRole('ROLE_LEVEL3')")
	@RequestMapping(value = "/{process}/invoice/invoiceListAdd.json", method = RequestMethod.POST)
	@ResponseBody
	public Invoice invoiceListAdd(@RequestBody Invoice invoice,
			@PathVariable String process) {
		return invoiceService.invoiceListAdd(invoice, process);
	}

	@PreAuthorize("hasRole('ROLE_LEVEL4') or hasRole('ROLE_LEVEL3')")
	@RequestMapping(value = "/{process}/invoice/{seq}/invoiceGblList", method = RequestMethod.GET)
	public String invoiceGblList(Model model, User user,
			@PathVariable String process, @PathVariable Integer seq) {

		List<InvoiceGbl> invoiceGblList = invoiceService.getInvoiceGblList(seq, process);

		model.addAttribute("invoicSeq", seq);

		model.addAttribute("invoiceListSeq", seq);
		model.addAttribute("invoiceGblList", invoiceGblList);

		return process + "/invoice/invoiceGblList";
	}
	
	@PreAuthorize("hasRole('ROLE_LEVEL4') or hasRole('ROLE_LEVEL3')")
	@RequestMapping(value = "/{process}/invoice/{seq}/invoicePrint", method = RequestMethod.GET)
	public String invoicePrint(Model model, User user,
			@PathVariable String process, @PathVariable Integer seq) {

		List<InvoiceGbl> invoiceGblList = invoiceService.getInvoiceGblList(seq, process);
		Invoice invoice = invoiceService.getInvoiceByInvoiceSeq(seq);
		
		Map<Integer, List<InvoiceGblContent>> map = invoiceService.getInvoicePrintMap(invoiceGblList, process);
		
		Carrier carrier = basicService.getCarrier(invoice.getTsp());
		
		Company company = basicService.getCompanyByCode("YJ");

		model.addAttribute("invoicSeq", seq);
		
		invoice.setProcess(process);
		model.addAttribute("invoice", invoice);
		
		model.addAttribute("scac", carrier);
		
		model.addAttribute("company", company);

		model.addAttribute("invoiceListSeq", seq);
		model.addAttribute("invoiceGblList", invoiceGblList);
		
		model.addAttribute("invoiceGblContentMap", map);

		return process + "/invoice/invoicePrint";
	}	
	
	@PreAuthorize("hasRole('ROLE_LEVEL4') or hasRole('ROLE_LEVEL3')")
	@RequestMapping(value = "/{process}/invoice/{seq}/invoiceGblListCommon", method = RequestMethod.GET)
	public String invoiceGblListCommon(Model model, User user,
			@PathVariable String process, @PathVariable Integer seq) {
		System.out.println("PROCESS : "+process+" SEQ : "+seq);
		List<InvoiceGbl> invoiceGblList = invoiceService.getInvoiceGblList(seq, process);
		model.addAttribute("invoicSeq", seq);
		model.addAttribute("invoiceListSeq", seq);
		model.addAttribute("invoiceGblList", invoiceGblList);

		return process + "/invoice/invoiceGblListCommon";
	}	

	@PreAuthorize("hasRole('ROLE_LEVEL4') or hasRole('ROLE_LEVEL3')")
	@RequestMapping(value = "/{process}/invoice/invoiceDelete.json", method = RequestMethod.POST)
	@ResponseBody
	public void invoiceDelete(@RequestBody Invoice invoice) {
		invoiceService.invoiceDelete(invoice);
	}
	
	@PreAuthorize("hasRole('ROLE_LEVEL4') or hasRole('ROLE_LEVEL3')")
	@RequestMapping(value = "/{process}/invoice/{seq}/invoiceDelete", method = RequestMethod.GET)
	public String invoiceDeleteAll(Model model, User user,
			@PathVariable String process, @PathVariable Integer seq, @ModelAttribute InvoiceFilter invoiceFilter) {
		Invoice invoice = new Invoice();
		invoice.setSeq(seq);
		
		invoiceService.invoiceDelete(invoice, process);

		user.setSubProcess("invoice");

		invoiceFilter.setProcess(process);

		invoiceFilter.getPagination().setNumItems(
				invoiceService.getInvoiceListCount(invoiceFilter, process));
		
		List<Invoice> invoiceList = invoiceService.getInvoiceList(invoiceFilter);
		System.out.println("======== INVOICE LIST SIZE : "+invoiceList.size()+" ============");
		model.addAttribute("filterMap", invoiceService.getInvoiceFilterMap(invoiceFilter));
		model.addAttribute("user", user);
		model.addAttribute("invoiceList", invoiceList);
		
		return process + "/invoice/main";
	}	

	@PreAuthorize("hasRole('ROLE_LEVEL4') or hasRole('ROLE_LEVEL3')")
	@RequestMapping(value = "/{process}/invoice/{seq}/{invoiceGblSeq}/{gblSeq}/invoiceGblContent", method = RequestMethod.GET)
	public String invoiceGblContent(Model model, User user,
			@PathVariable String process, @PathVariable Integer seq,
			@PathVariable Integer invoiceGblSeq, @PathVariable Integer gblSeq) {
		
		List<InvoiceGblContent> invoiceGblContentList = invoiceService
				.getInvoiceGblContentList(seq, invoiceGblSeq, gblSeq, process);
		
		model.addAttribute("invoiceGblContentInfo", invoiceService.getInvoiceGblContentInfo(invoiceGblSeq, process));
		
		model.addAttribute("invoiceGblContentList", invoiceGblContentList);

		return process + "/invoice/invoiceGblContent";
	}

	@PreAuthorize("hasRole('ROLE_LEVEL4') or hasRole('ROLE_LEVEL3')")
	@RequestMapping(value = "/{process}/invoiceCollection", method = RequestMethod.GET)
	public String invoicCollectionMain(Model model, User user,
			@ModelAttribute InvoiceFilter invoiceFilter,
			@PathVariable String process) {

		user.setSubProcess("collection");
		
		invoiceFilter.setProcess(process);

		invoiceFilter.getPagination().setNumItems(
				invoiceService.getInvoiceListCount(invoiceFilter, process));

		List<Invoice> invoiceList = invoiceService
				.getInvoiceList(invoiceFilter);

		model.addAttribute("filterMap", invoiceService.getInvoiceFilterMap(invoiceFilter));

		Map<Integer, InvoiceCollection> invoiceCollectionMap = invoiceService.getInvoiceCollectionMap(invoiceFilter);
		model.addAttribute("user", user);
		model.addAttribute("invoiceList", invoiceList);
		model.addAttribute("invoiceCollectionMap", invoiceCollectionMap);

		return process + "/invoice/invoiceCollection";
	}
	
	@PreAuthorize("hasRole('ROLE_LEVEL4') or hasRole('ROLE_LEVEL3')")
	@RequestMapping(value = "/{process}/invoiceCollection", method = RequestMethod.POST)
	public String invoiceCollectionMainPost(Model model, User user,
			@ModelAttribute InvoiceFilter invoiceFilter,
			@PathVariable String process) {

		user.setSubProcess("collection");

		invoiceFilter.setProcess(process);

		invoiceFilter.getPagination().setNumItems(
				invoiceService.getInvoiceCollectionListCount(invoiceFilter, process));

		List<Invoice> invoiceList = invoiceService
				.getInvoiceCollectionList(invoiceFilter);

		Map<Integer, InvoiceCollection> invoiceCollectionMap = invoiceService.getInvoiceCollectionMap(invoiceFilter);
		model.addAttribute("filterMap", invoiceService.getInvoiceFilterMap(invoiceFilter));

		model.addAttribute("user", user);
		model.addAttribute("invoiceList", invoiceList);
		model.addAttribute("invoiceCollectionMap", invoiceCollectionMap);

		return process + "/invoice/invoiceCollection";
	}
	
	@PreAuthorize("hasRole('ROLE_LEVEL4') or hasRole('ROLE_LEVEL3')")
	@RequestMapping(value = "/{process}/inputCollectionNet.json", method = RequestMethod.POST)
	@ResponseBody
	public void inputCollectionNet(@RequestBody Map<String, String> invoiceCollection){
		invoiceService.inputCollection(invoiceCollection);
	}	
	
	@PreAuthorize("hasRole('ROLE_LEVEL4') or hasRole('ROLE_LEVEL3')")
	@RequestMapping(value = "/{process}/invoiceCollectionFlowDelete.json", method = RequestMethod.POST)
	@ResponseBody
	public void invoiceCollectionDelete(@RequestBody Map<String, String> invoiceCollection){
		invoiceService.invoiceCollectionDelete(invoiceCollection);
	}
	
	@PreAuthorize("hasRole('ROLE_LEVEL4') or hasRole('ROLE_LEVEL3')")
	@RequestMapping(value = "/{process}/invoice/{seq}/invoiceCollectionGbl", method = RequestMethod.GET)
	public String invoiceCollectionGbl(Model model, User user,
			@PathVariable String process, @PathVariable Integer seq) {

		List<InvoiceGbl> invoiceGblList = invoiceService.getInvoiceGblList(seq, process);
		
		Map<Integer, InvoiceCollection> invoiceCollectionGblMap = invoiceService.getInvoiceCollectionGblMap(seq);

		model.addAttribute("invoicSeq", seq);

		model.addAttribute("invoiceListSeq", seq);
		model.addAttribute("invoiceGblList", invoiceGblList);
		model.addAttribute("invoiceCollectionGblMap", invoiceCollectionGblMap);

		return process + "/invoice/invoiceCollectionGbl";
	}
	
	@PreAuthorize("hasRole('ROLE_LEVEL4') or hasRole('ROLE_LEVEL3')")
	@RequestMapping(value = "/{process}/inputGblCollectionNet.json", method = RequestMethod.POST)
	@ResponseBody
	public void inputGblCollectionNet(@RequestBody Map<String, String> invoiceCollection){
		invoiceService.inputGblCollection(invoiceCollection);
	}	
	
	@PreAuthorize("hasRole('ROLE_LEVEL4') or hasRole('ROLE_LEVEL3')")
	@RequestMapping(value = "/{process}/invoiceGblCollectionFlowDelete.json", method = RequestMethod.POST)
	@ResponseBody
	public void invoiceGblCollectionDelete(@RequestBody Map<String, String> invoiceCollection){
		invoiceService.invoiceGblCollectionDelete(invoiceCollection);
	}

	@PreAuthorize("hasRole('ROLE_LEVEL4') or hasRole('ROLE_LEVEL3')")
	@RequestMapping(value = "/{process}/invoice/{seq}/{invoiceGblSeq}/{gblSeq}/invoiceGblCollectionContent", method = RequestMethod.GET)
	public String invoiceGblCollectionContent(Model model, User user,
			@PathVariable String process, @PathVariable Integer seq,
			@PathVariable Integer invoiceGblSeq, @PathVariable Integer gblSeq) {
		
		List<InvoiceGblContent> invoiceGblContentList = invoiceService
				.getInvoiceGblContentList(seq, invoiceGblSeq, gblSeq, process);
		
		model.addAttribute("gblSeq", gblSeq);
		model.addAttribute("invoiceSeq", seq);
		model.addAttribute("invoiceGblSeq", invoiceGblSeq);
		
		model.addAttribute("invoiceGblContentInfo", invoiceService.getInvoiceGblContentInfo(invoiceGblSeq, process));
		
		model.addAttribute("invoiceGblContentList", invoiceGblContentList);

		return process + "/invoice/invoiceGblCollectionContent";
	}	
	
	@PreAuthorize("hasRole('ROLE_LEVEL4') or hasRole('ROLE_LEVEL3')")
	@RequestMapping(value = "/{process}/invoice/collectionRemarkInput.json", method = RequestMethod.POST)
	@ResponseBody
	public void invoiceCollectionRemarkInput(@RequestBody Map<String, String> invoiceCollection){
		invoiceService.invoiceCollectionRemarkInput(invoiceCollection);
	}

	@Resource
	private InvoicePdfPrintView invoicePdfPrintView;
	
	@PreAuthorize("hasRole('ROLE_LEVEL4') or hasRole('ROLE_LEVEL3')")
	@RequestMapping(value = "/{process}/invoice/{seq}/{invoiceGblSeq}/{gblSeq}/invoiceContentPdfView", method = RequestMethod.GET)
	public InvoicePdfPrintView invoiceContentPdfView(Model model, User user,
			@PathVariable String process, @PathVariable Integer seq,
			@PathVariable Integer invoiceGblSeq, @PathVariable Integer gblSeq) {
		
		List<InvoiceGblContent> invoiceGblContentList = invoiceService
				.getInvoiceGblContentList(seq, invoiceGblSeq, gblSeq, process);
		
		model.addAttribute("invoiceGblContentInfo", invoiceService.getInvoiceGblContentInfo(invoiceGblSeq, process));
		
		model.addAttribute("invoiceGblContentList", invoiceGblContentList);
		
		return invoicePdfPrintView;
	}
}
