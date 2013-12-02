package org.youngjin.net.outbound;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.youngjin.net.GBL;
import org.youngjin.net.GBLAttachment;
import org.youngjin.net.GBLStatus;
import org.youngjin.net.code.Code;
import org.youngjin.net.code.CodeDao;
import org.youngjin.net.process.GBlock;
import org.youngjin.net.upload.UploadService;

@Service
public class OutboundService {

	@Resource
	private OutboundDao outboundDao;
	
	@Resource
	private UploadService uploadService;
	
	@Resource
	private CodeDao codeDao;
	
	public int getGblListCount(OutboundFilter outboundFilter) {		
		System.out.println(outboundFilter.toString());
		return outboundDao.getGblListCount(outboundFilter);
	}

	public List<GBL> getGblList(OutboundFilter outboundFilter) {
		return outboundDao.getGblList(outboundFilter);
	}

	public GBlock findUsNo(GBlock gBlock) {
		return outboundDao.findUsNo(gBlock);
	}

	public void insertGbl(GBL gbl) {
		outboundDao.insertGbl(gbl);
		
		outboundDao.insertGblStatus(gbl);
	}

	public Map<String, List<Code>> getFilterMap() {
		Map<String, List<Code>> filterMap = new HashMap<String, List<Code>>();
		List<Code> branchList = codeDao.getAllAreaList();		
		filterMap.put("branchList", branchList);
		
		List<Code> carrierList = outboundDao.getCarrierList();
		filterMap.put("carrierList", carrierList);
		
		List<Code> codeList = outboundDao.getCodeList();
		filterMap.put("codeList", codeList);
		
		
		return filterMap;
	}

	public GBLStatus getGblProcessAndUpload(Integer seq) {
		return outboundDao.getGblProcess(seq); 
	}
	
	public void insertGblFile(GBL gbl){
		insertAttachments(gbl);
	}
	
	private void insertAttachments(GBL gbl) {
		List<GBLAttachment> files = gblAttachments(gbl.getAttachments());
		gbl.setAttachmentList(files);
		gbl.setNo(getGbl(gbl.getSeq()).getNo());
		
		for (GBLAttachment gblAttachment : files) {
			CommonsMultipartFile uploadedFile = gblAttachment.getCommonsMultipartFile();
			if (uploadedFile.isEmpty()) continue;
			String filePath = uploadService.transferFile(uploadedFile, "outbound", gbl.getNo());
			
			gblAttachment.setFilePath(filePath);	
			gblAttachment.setGblNo(gbl.getNo());
			gblAttachment.setGblFileNo(gbl.getGblFileNo());
			
			outboundDao.insertAttachment(gblAttachment);
		}
	}
	
	public static List<GBLAttachment> gblAttachments(CommonsMultipartFile attachments[]) {
		ArrayList<GBLAttachment> list = new ArrayList<GBLAttachment>();
		try {
			if (attachments == null) throw new Exception();
			for (CommonsMultipartFile commonsMultipartFile : attachments) {
				list.add(new GBLAttachment(commonsMultipartFile));
			}			
		} catch (Exception e) {
		}
		return list;
	}
	

	public void deleteGblFile(GBL gbl) {
		deleteArticle(gbl.getSeq());
	}

	public void deleteArticle(Integer seq) {
		GBL gbl = getGbl(seq);
		//outboundDao.deleteArticleBySeq(gbl.getSeq());
		
		for (GBLAttachment attachment : gbl.getAttachmentList()) {
			try {
				File file = new File(attachment.getFilePath());
				file.delete();
			} catch (Exception e) {
			}
		}
		//outboundDao.deleteAttachmentByArticleId(seq);
	}

	public GBL getGbl(Integer seq) {
		return outboundDao.getGbl(seq);
	}

	public List<GBLAttachment> getGblFileList(Integer seq) {
		return outboundDao.getGblFileList(seq);
	}

}
