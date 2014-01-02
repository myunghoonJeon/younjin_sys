package org.youngjin.net.outbound;

import java.io.File;
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
public class OutboundService {

	@Resource
	private OutboundDao outboundDao;
	
	@Resource
	private UploadService uploadService;
	
	@Resource
	private CodeDao codeDao;
	
	public int getGblListCount(OutboundFilter outboundFilter, User user) {	
		if(!"ADMIN".equals(user.getAuthStr())){
			outboundFilter.setArea("0" + user.getArea().toString());
		}
		
		return outboundDao.getGblListCount(outboundFilter);
	}

	public List<GBL> getGblList(OutboundFilter outboundFilter, User user) {
		if(!"ADMIN".equals(user.getAuthStr())){
			outboundFilter.setArea("0" + user.getArea().toString());
		}
		
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

	public GBLAttachment getFileInfo(String seq, String flag) {
		Map<String, String> filter = new HashMap<String, String>(); 
		filter.put("seq", seq);
		filter.put("flag", flag);
		
		return outboundDao.getFileInfo(filter);
	}

	public Map<String, String> getGblStatus(OutboundFilter outboundFilter) {
		Map<String, String> gblStatusMap = new HashMap<String, String>();
		
		List<GBLStatus> gblStatusList = outboundDao.getGblStatus(outboundFilter);
		
		for ( GBLStatus gblStatus : gblStatusList ){
				gblStatusMap.put(gblStatus.getNo(), gblStatus.getCurrentState());
		}
		
		return gblStatusMap;
	}

	public void insertPreMoveSurvey(PreMoveSurvey preMoveSurvey) {
		outboundDao.insertPreMoveSurvey(preMoveSurvey);
	}

	public PreMoveSurvey getPreMoveSurvey(Integer seq) {
		return outboundDao.getPreMoveSurvey(seq);
	}

	public void updatePreMoveSurvey(PreMoveSurvey preMoveSurvey) {
		outboundDao.updatePreMoveSurvey(preMoveSurvey);
	}

	public List<Dd619> getDd619List(String seq) {
		return outboundDao.getDd619List(seq);
	}

	public void insertDd619(Dd619 dd619) {
		outboundDao.insertDd619(dd619);
	}

	public void updateDd619(Dd619 dd619) {
		outboundDao.updateDd619(dd619);
	}

	public List<Weightcertificate> getWeightcertificateList(String seq) {
		return outboundDao.getWeightcertificateList(seq);
	}

	public void insertWeightcertificate(Weightcertificate weightcertificate) {
		Weightcertificate paramWeightcertificate = new Weightcertificate();
		
		int count = weightcertificate.getCount();
		
		String [] pieceList = weightcertificate.getPiece().split(",", count);
		String [] typeList = weightcertificate.getType().split(",", count);
		String [] grossList = weightcertificate.getGross().split(",", count);
		String [] tareList = weightcertificate.getTare().split(",", count);
		String [] netList = weightcertificate.getNet().split(",", count);
		String [] cuftList = weightcertificate.getCuft().split(",", count);
		String [] remarkList = weightcertificate.getRemark().split(",", count);
		String [] statusList = weightcertificate.getStatus().split(",", count);
		
		for( int i = 0 ; i < count; i ++ ){
			paramWeightcertificate.setPiece(pieceList[i]);
			paramWeightcertificate.setType(typeList[i]);
			paramWeightcertificate.setGross(grossList[i]);
			paramWeightcertificate.setTare(tareList[i]);
			paramWeightcertificate.setNet(netList[i]);
			paramWeightcertificate.setCuft(cuftList[i]);
			paramWeightcertificate.setRemark(remarkList[i]);
			paramWeightcertificate.setStatus(statusList[i]);
			
			paramWeightcertificate.setGblSeq(weightcertificate.getGblSeq());
			paramWeightcertificate.setDate(weightcertificate.getDate());
			
			outboundDao.insertWeightcertificate(paramWeightcertificate);
		}				
		
		/*Map<String, Integer> param = new HashMap<String, Integer>();
		param.put("seq", weightcertificate.getGblSeq());
		param.put("status", 1);
		outboundDao.updateGblStatus(param);*/
	}

	public int getTruckListCount(OutboundFilter outboundFilter) {
		return outboundDao.getTruckListCount(outboundFilter);
	}

	public List<TruckManifast> getTruckList(OutboundFilter outboundFilter) {
		return outboundDao.getTruckList(outboundFilter);
	}

	public List<GBL> getTruckGblList(OutboundFilter outboundFilter) {
		return outboundDao.getTruckGblList(outboundFilter);
	}

	public List<Weightcertificate> getTruckWeightList(String seq) {
		return outboundDao.getWeightcertificateList(seq);
	}

	public void insertTruckManifast(Map<String, String> gblSeq) {
		
		String [] gblSeqList = gblSeq.get("gblSeq").split(",");
		
		GBL gbl = getGbl(Integer.parseInt(gblSeqList[0]));
		
		TruckManifast truckManifast = new TruckManifast();
		truckManifast.setBranch(gbl.getAreaLocal());
		truckManifast.setCode(gbl.getCode());
		
		outboundDao.insertTurckManifast(truckManifast);
		gbl.setTruckSeq(truckManifast.getSeq());
		
		for( int i = 0 ; i < gblSeqList.length ; i ++ ){
			gbl.setSeq(Integer.parseInt(gblSeqList[i]));
			outboundDao.updateGbl(gbl);
			outboundDao.updateWeightcertificate(gbl);
			
			Map<String, Integer> paramMap = new HashMap<String, Integer>();
			paramMap.put("truckmanifast", 1);	
			paramMap.put("seq", gbl.getSeq());
			outboundDao.updateGblStatus(paramMap);
		}
	}

	public int getBookingListCount(OutboundFilter outboundFilter) {
		return outboundDao.getBookingListCount(outboundFilter);
	}

	public List<BookingList> getBookingList(OutboundFilter outboundFilter) {
		return outboundDao.getBookingList(outboundFilter);
	}

	public List<GBL> getBookingGblList(OutboundFilter outboundFilter) {
		return outboundDao.getBookingGblList(outboundFilter);
	}

	public void additionComplete(Addition addition) {
		String titleList[] = addition.getTitle().split(",");
		String priceList[] = addition.getPrice().split(",");
		
		int titleListSize = titleList.length;
		int priceListSize = priceList.length;
		
		if( titleListSize != priceListSize ){
			if ( titleListSize > priceListSize ){
				titleList[titleListSize - 1] = null;
			} else {
				priceList[priceListSize - 1] = null;
			}
		}
		
		Addition paramAddition = new Addition();
		paramAddition.setGblSeq(addition.getGblSeq());
		
		for( int i = 0 ; i < titleListSize ; i ++ ){
			paramAddition.setTitle(titleList[i]);
			paramAddition.setCost(Double.parseDouble(priceList[i]));
			
			outboundDao.additionComplete(paramAddition);
		}
		
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("preparation", 1);
		map.put("seq", addition.getGblSeq());
		outboundDao.updateGblStatus(map);
	}

	public void insertBookingList(Map<String, String> gblSeq) {		
		String [] gblSeqList = gblSeq.get("gblSeq").split(",");
		
		GBL gbl = getGbl(Integer.parseInt(gblSeqList[0]));
		
		BookingList bookingList = new BookingList();
		
		outboundDao.insertBookingList(bookingList);
		gbl.setBookingSeq(bookingList.getSeq());
		
		for( int i = 0 ; i < gblSeqList.length ; i ++ ){
			gbl.setSeq(Integer.parseInt(gblSeqList[i]));
			outboundDao.updateGbl(gbl);
			outboundDao.updateWeightcertificate(gbl);
			
			Map<String, Integer> paramMap = new HashMap<String, Integer>();
			paramMap.put("booking", 1);	
			paramMap.put("seq", gbl.getSeq());
			outboundDao.updateGblStatus(paramMap);
		}		
	}

	public List<DeliveryGbl> getBookingListPrint(Integer seq) {
		List<DeliveryGbl> bookingGblList = outboundDao.getBookingListPrint(seq);
		
		return bookingGblList;
	}

	public BookingList getBookingListOne(Integer seq) {
		return outboundDao.getBookingListOne(seq);
	}

	public List<DeliveryGbl> getTruckManifastPrint(Integer seq) {
		return outboundDao.getTruckManifastPrint(seq);
	}

	public TruckManifast getTruckManifastOne(Integer seq) {
		return outboundDao.getTruckManifastOne(seq);
	}

	public void modifyGbl(GBL gbl) {
		outboundDao.modifyGbl(gbl);
	}

	public Dd619 getDd619ListSelectOne(Integer dd619Seq) {
		return outboundDao.getDd619ListSelectOne(dd619Seq);
	}
}
