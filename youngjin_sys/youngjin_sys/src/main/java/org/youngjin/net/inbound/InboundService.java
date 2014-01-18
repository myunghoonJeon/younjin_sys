package org.youngjin.net.inbound;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.youngjin.net.Dd619;
import org.youngjin.net.GBL;
import org.youngjin.net.GBLAttachment;
import org.youngjin.net.GBLStatus;
import org.youngjin.net.code.Code;
import org.youngjin.net.code.CodeDao;
import org.youngjin.net.login.User;
import org.youngjin.net.process.GBlock;
import org.youngjin.net.upload.UploadService;

@Service
public class InboundService {
	
	@Resource
	private InboundDao inboundDao;
	
	@Resource
	private UploadService uploadService;
	
	@Resource
	private CodeDao codeDao;
	
	public int getFreightListCount(InboundFilter inboundFilter, User user) {	
		if(!"ADMIN".equals(user.getAuthStr())){
			inboundFilter.setArea("0" + user.getArea().toString());
		}
		
		return inboundDao.getGblListCount(inboundFilter);
	}

	public List<GBL> getFreightList(InboundFilter inboundFilter, User user) {
		if(!"ADMIN".equals(user.getAuthStr())){
			inboundFilter.setArea("0" + user.getArea().toString());
		}
		
		return inboundDao.getFreightList(inboundFilter);
	}

	/*public GBlock findUsNo(GBlock gBlock) {
		return inboundDao.findUsNo(gBlock);
	}
	*/
	public void insertFreight(GBL gbl) {
		inboundDao.insertFreight(gbl);
		
		inboundDao.insertFreightStatus(gbl);
	}
	/*
	public Map<String, List<Code>> getFilterMap() {
		Map<String, List<Code>> filterMap = new HashMap<String, List<Code>>();
		List<Code> branchList = codeDao.getAllAreaList();		
		filterMap.put("branchList", branchList);
		
		List<Code> carrierList = inboundDao.getCarrierList();
		filterMap.put("carrierList", carrierList);
		
		List<Code> codeList = inboundDao.getCodeList();
		filterMap.put("codeList", codeList);
		
		
		return filterMap;
	}*/

	public Map<String, String> getGblStatus(InboundFilter inboundFilter) {
		Map<String, String> gblStatusMap = new HashMap<String, String>();
		
		List<GBLStatus> gblStatusList = inboundDao.getGblStatus(inboundFilter);
		
		for ( GBLStatus gblStatus : gblStatusList ){
				gblStatusMap.put(gblStatus.getNo(), gblStatus.getInboundCurrentState());
		}
		
		return gblStatusMap;
	}

	public Map<String, List<Code>> getFilterMap() {
		Map<String, List<Code>> filterMap = new HashMap<String, List<Code>>();
		List<Code> branchList = codeDao.getAllAreaList();		
		filterMap.put("branchList", branchList);
		
		List<Code> carrierList = inboundDao.getCarrierList();
		filterMap.put("carrierList", carrierList);
		
		List<Code> codeList = inboundDao.getCodeList();
		filterMap.put("codeList", codeList);
		
		
		return filterMap;
	}

	public Boolean checkWeight(Map<String, Integer> param) {
		return inboundDao.checkWeight(param);
	}

	public List<WeightIb> getWeightList(Integer seq) {
		return inboundDao.getWeightList(seq);
	}

	public void insertWeightAdd(WeightIb weightIb) {
		String [] pcs = weightIb.getPiece().split(",");

		int count = pcs.length;		
		
		String [] type = weightIb.getType().split(",", count);
		String [] gross = weightIb.getGross().split(",", count);
		String [] grossKg = weightIb.getGrossKg().split(",", count);
		String [] tare = weightIb.getTare().split(",", count);
		String [] net = weightIb.getNet().split(",", count);
		String [] cuft = weightIb.getCuft().split(",", count);
		String [] remark = weightIb.getRemark().split(",", count);
		Integer gblSeq = weightIb.getGblSeq();
		
		for( int i = 0 ; i < count ; i ++ ){
			WeightIb weightParam = new WeightIb();
			weightParam.setPiece(pcs[i]);
			weightParam.setType(type[i]);
			weightParam.setGross(gross[i]);
			weightParam.setGrossKg(grossKg[i]);
			weightParam.setTare(tare[i]);
			weightParam.setNet(net[i]);
			weightParam.setCuft(cuft[i]);
			weightParam.setRemark(remark[i]);
			weightParam.setGblSeq(gblSeq);
			
			inboundDao.insertWeightAdd(weightParam);
		}
	}	

	public int getCustomInvoiceGblListCount(InboundFilter inboundFilter) {
		return inboundDao.getCustomInvoiceGblListCount(inboundFilter);
	}	
	
	//이전버전
	
	public List<GBLAttachment> getGblFileList(Integer seq) {
		return inboundDao.getGblFileList(seq);
	}

	public GBLStatus getGblProcessAndUpload(Integer seq) {
		return inboundDao.getGblProcess(seq); 
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
			String filePath = uploadService.transferFile(uploadedFile, "inbound", gbl.getNo());
			
			gblAttachment.setFilePath(filePath);	
			gblAttachment.setGblNo(gbl.getNo());
			gblAttachment.setGblFileNo(gbl.getGblFileNo());
			
			inboundDao.insertAttachment(gblAttachment);
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
	
	public GBL getGbl(Integer seq) {
		return inboundDao.getGbl(seq);
	}

	public List<Dd619> getDd619List(String seq) {
		return inboundDao.getDd619List(seq);
	}

	public int getInboundInvoiceListCount(InboundFilter inboundFilter) {
		return inboundDao.getInboundInvoiceListCount(inboundFilter);
	}

	public List<InboundInvoice> getInboundInvoiceList(InboundFilter inboundFilter) {
		return inboundDao.getInboundInvoiceList(inboundFilter);
	}

	public List<GBL> getCustomInvoiceGblList(InboundFilter inboundFilter) {
		return inboundDao.getCustomInvoiceGblList(inboundFilter);
	}

	public Map<String, String> getInboundInvoiceSettingMap(Integer gblSeq) {
		Map<String, String> settingMap = new HashMap<String, String>();
		GBL gbl = inboundDao.getGbl(gblSeq);
		
		settingMap.put("gblNo", gbl.getGblNo());
		
		InboundInvoice lastInboundInvoice = inboundDao.getLastInboundInvoiceNo(gblSeq);
		
		String inboundInvoiceNo = "";
		if(lastInboundInvoice.getInboundInvoiceNo() == null){
			inboundInvoiceNo = lastInboundInvoice.getInvoiceDate() + "-00001";
		} else {
			if(lastInboundInvoice.getInboundInvoiceNo().length() == 1){
				inboundInvoiceNo = lastInboundInvoice.getInvoiceDate() + "-0000" + lastInboundInvoice.getInboundInvoiceNo();
			} else if(lastInboundInvoice.getInboundInvoiceNo().length() == 2){
				inboundInvoiceNo = lastInboundInvoice.getInvoiceDate() + "-000" + lastInboundInvoice.getInboundInvoiceNo();
			} else if(lastInboundInvoice.getInboundInvoiceNo().length() == 3){
				inboundInvoiceNo = lastInboundInvoice.getInvoiceDate() + "-00" + lastInboundInvoice.getInboundInvoiceNo();
			} else if(lastInboundInvoice.getInboundInvoiceNo().length() == 4){
				inboundInvoiceNo = lastInboundInvoice.getInvoiceDate() + "-0" + lastInboundInvoice.getInboundInvoiceNo();
			} else {
				inboundInvoiceNo = lastInboundInvoice.getInvoiceDate() + lastInboundInvoice.getInboundInvoiceNo();
			}
		}
		
		settingMap.put("inboundInvoiceNo", inboundInvoiceNo);
		
		return settingMap;
	}

	public Integer inputCustomInboundInvoiceAddSetting(
			InboundInvoice inboundInvoice) {
		String inboundInvoiceNo = inboundInvoice.getInboundInvoiceNo().substring(3, 8);
		inboundInvoice.setInboundInvoiceNo(inboundInvoiceNo);
		
		return inboundDao.inputCustomInboundInvoiceAddSetting(inboundInvoice);
	}

	public InboundInvoice getInboundInvoiceBasicInfo(Integer inboundInvoiceSeq) {
		return inboundDao.getInboundInvoice(inboundInvoiceSeq);
	}
}
