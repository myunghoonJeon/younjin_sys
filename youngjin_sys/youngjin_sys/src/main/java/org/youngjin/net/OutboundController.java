package org.youngjin.net;

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
import org.youngjin.net.login.User;
import org.youngjin.net.outbound.OutboundFilter;
import org.youngjin.net.outbound.OutboundService;
import org.youngjin.net.process.GBlock;
import org.youngjin.net.upload.DownloadView;

@Controller
@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER') or hasRole('ROLE_NORMAL')")
public class OutboundController {

	@Resource
	private OutboundService outboundService;

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
	@RequestMapping(value = "/{process}/{seq}/preperation", method = RequestMethod.GET) 
	public String gblPreperation(Model model, User user,
			@PathVariable String process, @PathVariable String seq){
		
			model.addAttribute("seq", seq);
			
			return process + "/gbl/preperation";			
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')")
	@RequestMapping(value = "/{process}/findUsNo.json", method = RequestMethod.POST)
	@ResponseBody
	public GBlock findUsNo(@RequestBody GBlock gBlock) {

		return outboundService.findUsNo(gBlock);
	}

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
