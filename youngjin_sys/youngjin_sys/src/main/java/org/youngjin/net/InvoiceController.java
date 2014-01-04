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
import org.youngjin.net.code.Code;
import org.youngjin.net.code.CodeService;
import org.youngjin.net.invoice.Invoice;
import org.youngjin.net.invoice.InvoiceFilter;
import org.youngjin.net.invoice.InvoiceGbl;
import org.youngjin.net.invoice.InvoiceGblContent;
import org.youngjin.net.invoice.InvoiceService;
import org.youngjin.net.login.User;

@Controller
public class InvoiceController {

	@Resource
	private InvoiceService invoiceService;

	@Resource
	private CodeService codeService;

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/{process}/invoice", method = RequestMethod.GET)
	public String invoiceMain(Model model, User user,
			@ModelAttribute InvoiceFilter invoiceFilter,
			@PathVariable String process) {

		user.setSubProcess("invoice");

		invoiceFilter.getPagination().setNumItems(
				invoiceService.getInvoiceListCount(invoiceFilter, process));

		List<Invoice> invoiceList = invoiceService
				.getInvoiceList(invoiceFilter);

		model.addAttribute("user", user);
		model.addAttribute("invoiceList", invoiceList);

		return process + "/invoice/main";
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/{process}/invoice/invoiceAddSetting", method = RequestMethod.GET)
	public String invoiceAddSetting(Model model, User user,
			@PathVariable String process) {

		List<Code> tspList = codeService.getAllCarrierList();

		model.addAttribute("tspList", tspList);

		return process + "/invoice/invoiceAddSettingPop";
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/{process}/invoice/invoiceListAdd.json", method = RequestMethod.POST)
	@ResponseBody
	public Invoice invoiceListAdd(@RequestBody Invoice invoice,
			@PathVariable String process) {
		return invoiceService.invoiceListAdd(invoice, process);
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/{process}/invoice/{seq}/invoiceGblList", method = RequestMethod.GET)
	public String invoiceGblList(Model model, User user,
			@PathVariable String process, @PathVariable Integer seq) {

		List<InvoiceGbl> invoiceGblList = invoiceService.getInvoiceGblList(seq);

		model.addAttribute("invoicSeq", seq);

		model.addAttribute("invoiceListSeq", seq);
		model.addAttribute("invoiceGblList", invoiceGblList);

		return process + "/invoice/invoiceGblList";
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/{process}/invoice/invoiceDelete.json", method = RequestMethod.POST)
	@ResponseBody
	public void invoiceDelete(@RequestBody Invoice invoice) {
		invoiceService.invoiceDelete(invoice);
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/{process}/invoice/{seq}/{invoiceGblSeq}/{gblSeq}/invoiceGblContent", method = RequestMethod.GET)
	public String invoiceGblContent(Model model, User user,
			@PathVariable String process, @PathVariable Integer seq,
			@PathVariable Integer invoiceGblSeq, @PathVariable Integer gblSeq) {
		
		List<InvoiceGblContent> invoiceGblContentList = invoiceService
				.getInvoiceGblContentList(invoiceGblSeq, gblSeq, process);
		
		model.addAttribute("invoiceGblContentList", invoiceGblContentList);

		return process + "/invoice/invoiceGblContent";
	}
}
