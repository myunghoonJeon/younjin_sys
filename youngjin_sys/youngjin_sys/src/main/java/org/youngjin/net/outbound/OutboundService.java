package org.youngjin.net.outbound;

import java.awt.Container;
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
import org.youngjin.net.util.DateUtil;

@Service
public class OutboundService {

	@Resource
	private OutboundDao outboundDao;

	@Resource
	private UploadService uploadService;

	@Resource
	private CodeDao codeDao;

	public int getGblListCount(OutboundFilter outboundFilter, User user) {
		if (!"ADMIN".equals(user.getAuthStr())) {
			outboundFilter.setArea("0" + user.getArea().toString());
		}

		return outboundDao.getGblListCount(outboundFilter);
	}

	public List<GBL> getGblList(OutboundFilter outboundFilter, User user) {
		if (!"ADMIN".equals(user.getAuthStr())) {
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

	public void insertGblFile(GBL gbl) {
		insertAttachments(gbl);
	}

	private void insertAttachments(GBL gbl) {
		List<GBLAttachment> files = gblAttachments(gbl.getAttachments());
		gbl.setAttachmentList(files);
		gbl.setNo(getGbl(gbl.getSeq()).getNo());

		for (GBLAttachment gblAttachment : files) {
			CommonsMultipartFile uploadedFile = gblAttachment
					.getCommonsMultipartFile();
			if (uploadedFile.isEmpty())
				continue;
			String filePath = uploadService.transferFile(uploadedFile,
					"outbound", gbl.getNo());

			gblAttachment.setFilePath(filePath);
			gblAttachment.setGblNo(gbl.getNo());
			gblAttachment.setGblFileNo(gbl.getGblFileNo());

			outboundDao.insertAttachment(gblAttachment);
		}
	}

	public static List<GBLAttachment> gblAttachments(
			CommonsMultipartFile attachments[]) {
		ArrayList<GBLAttachment> list = new ArrayList<GBLAttachment>();
		try {
			if (attachments == null)
				throw new Exception();
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
		// outboundDao.deleteArticleBySeq(gbl.getSeq());

		for (GBLAttachment attachment : gbl.getAttachmentList()) {
			try {
				File file = new File(attachment.getFilePath());
				file.delete();
			} catch (Exception e) {
			}
		}
		// outboundDao.deleteAttachmentByArticleId(seq);
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

		List<GBLStatus> gblStatusList = outboundDao
				.getGblStatus(outboundFilter);

		for (GBLStatus gblStatus : gblStatusList) {
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

		Double grossSum = 0.0;

		String[] pieceList = weightcertificate.getPiece().split(",", count);
		String[] typeList = weightcertificate.getType().split(",", count);
		String[] grossKgList = weightcertificate.getGrossKg().split(",", count);
		String[] grossList = weightcertificate.getGross().split(",", count);
		String[] tareList = weightcertificate.getTare().split(",", count);
		String[] netList = weightcertificate.getNet().split(",", count);
		String[] cuftList = weightcertificate.getCuft().split(",", count);
		String[] remarkList = weightcertificate.getRemark().split(",", count);
		String[] statusList = weightcertificate.getStatus().split(",", count);

		for (int i = 0; i < count; i++) {
			paramWeightcertificate.setPiece(pieceList[i]);
			paramWeightcertificate.setType(typeList[i]);
			paramWeightcertificate.setGrossKg(grossKgList[i]);
			paramWeightcertificate.setGross(grossList[i]);
			paramWeightcertificate.setTare(tareList[i]);
			paramWeightcertificate.setNet(netList[i]);
			paramWeightcertificate.setCuft(cuftList[i]);
			paramWeightcertificate.setRemark(remarkList[i]);
			paramWeightcertificate.setStatus(statusList[i]);

			paramWeightcertificate.setGblSeq(weightcertificate.getGblSeq());
			paramWeightcertificate.setDate(weightcertificate.getDate());

			Integer checkWeightcertificateAndGetSeq = outboundDao
					.getCheckWeightCertificateAndGetSeq(paramWeightcertificate);

			if (checkWeightcertificateAndGetSeq == null) {
				outboundDao.insertWeightcertificate(paramWeightcertificate);

				outboundDao.minusContainerByStatus(paramWeightcertificate
						.getStatus());
			} else {
				paramWeightcertificate.setSeq(checkWeightcertificateAndGetSeq);
				Weightcertificate weightContainerTemp = outboundDao
						.getWeightcertificateOne(checkWeightcertificateAndGetSeq);
				String beforeStatus = weightContainerTemp.getStatus();

				outboundDao
						.updateWeightcertificateNormal(paramWeightcertificate);
				outboundDao.minusContainerByStatus(paramWeightcertificate
						.getStatus());
				outboundDao.plusContainerByStatus(beforeStatus);
			}

			grossSum += Double.parseDouble(grossList[i]);
		}

		GBL gbl = new GBL();
		gbl.setSeq(weightcertificate.getGblSeq());
		gbl.setLbs(grossSum.toString());

		outboundDao.updateGbl(gbl);

		/*
		 * Map<String, Integer> param = new HashMap<String, Integer>();
		 * param.put("seq", weightcertificate.getGblSeq()); param.put("status",
		 * 1); outboundDao.updateGblStatus(param);
		 */
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

		String[] gblSeqList = gblSeq.get("gblSeq").split(",");

		GBL gbl = getGbl(Integer.parseInt(gblSeqList[0]));

		TruckManifast truckManifast = new TruckManifast();
		truckManifast.setBranch(gbl.getAreaLocal());
		truckManifast.setCode(gbl.getCode());

		outboundDao.insertTurckManifast(truckManifast);

		for (int i = 0; i < gblSeqList.length; i++) {
			GBL gblTemp = getGbl(Integer.parseInt(gblSeqList[i]));
			gblTemp.setTruckSeq(truckManifast.getSeq());
			outboundDao.updateGbl(gblTemp);
			outboundDao.updateWeightcertificate(gblTemp);

			Map<String, Integer> paramMap = new HashMap<String, Integer>();
			paramMap.put("truckmanifast", 1);
			paramMap.put("seq", gbl.getSeq());
			outboundDao.updateGblStatus(paramMap);
			if (gblTemp.getNo().contains("-sub")) {
				String gblNo = gblTemp.getNo().substring(0,
						gblTemp.getNo().length() - 5);
				int checkSeperateComplete = outboundDao
						.checkSeperateComplete(gblNo);
				if (checkSeperateComplete == 0) {
					outboundDao.updateGblStatusByGblNo(gblNo);
				}
			}
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

		if (titleListSize != priceListSize) {
			if (titleListSize > priceListSize) {
				titleList[titleListSize - 1] = null;
			} else {
				priceList[priceListSize - 1] = null;
			}
		}

		Addition paramAddition = new Addition();
		paramAddition.setGblSeq(addition.getGblSeq());

		for (int i = 0; i < titleListSize; i++) {
			paramAddition.setTitle(titleList[i]);
			paramAddition.setCost(Double.parseDouble(priceList[i]));

			Integer checkAddition = outboundDao
					.checkAddtionComplete(paramAddition);

			if (checkAddition == 0) {
				outboundDao.additionComplete(paramAddition);
			} else {
				outboundDao.additionCompleteUpdate(paramAddition);
			}
		}

		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("preparation", 1);
		map.put("seq", addition.getGblSeq());
		outboundDao.updateGblStatus(map);
	}

	public void insertBookingList(Map<String, String> gblSeq) {
		String[] gblSeqList = gblSeq.get("gblSeq").split(",");

		GBL gbl = getGbl(Integer.parseInt(gblSeqList[0]));

		BookingList bookingList = new BookingList();

		outboundDao.insertBookingList(bookingList);
		gbl.setBookingSeq(bookingList.getSeq());

		for (int i = 0; i < gblSeqList.length; i++) {
			GBL gblTemp = getGbl(Integer.parseInt(gblSeqList[0]));
			gblTemp.setSeq(Integer.parseInt(gblSeqList[i]));
			gblTemp.setBookingSeq(bookingList.getSeq());
			gblTemp.setLbs(null);
			outboundDao.updateGbl(gblTemp);
			outboundDao.updateWeightcertificate(gblTemp);

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

	public void modifyDd619(Dd619 dd619) {
		outboundDao.updateDd619(dd619);

		Addition paramAddition = new Addition();
		paramAddition.setDd619Seq(dd619.getSeq());
		paramAddition.setGblSeq(dd619.getGblSeq());
		paramAddition.setMemorandumSeq(dd619.getMemorandumListSeq());

		Integer additionCheck = outboundDao.checkAddtionComplete(paramAddition);

		String[] invoiceMemorandumType = dd619.getInvoiceMemorandumType()
				.split(",", dd619.getCount());
		String[] invoiceMemorandumValue = dd619.getInvoiceMemorandumValue()
				.split(",", dd619.getCount());

		for (int i = 0; i < dd619.getCount(); i++) {
			paramAddition.setTitle(invoiceMemorandumType[i]);
			paramAddition.setPrice(invoiceMemorandumValue[i]);

			if (additionCheck > 0) {
				outboundDao.additionCompleteUpdate(paramAddition);
			} else {
				outboundDao.additionComplete(paramAddition);
			}
		}
	}

	public List<Addition> getAddtionList(String seq) {
		return outboundDao.getAddtionList(seq);
	}

	public Map<String, Double> getRemarkValue(String seq,
			Integer memorandumListSeq) {
		Map<String, Double> remarkValue = new HashMap<String, Double>();
		List<Addition> additionList = outboundDao.getRemarkValue(seq,
				memorandumListSeq);

		for (Addition addition : additionList) {
			remarkValue.put(addition.getTitle(), (addition.getCost() == null || addition.getCost().equals("")) ? Double.parseDouble(addition.getPrice()) : addition.getCost());
		}

		return remarkValue;
	}

	public void seperateGbl(Map<String, String> gblMap) {
		GBL gbl = outboundDao.getGbl(Integer.parseInt(gblMap.get("seq")));
		String[] weightSeqList = gblMap.get("weightSeqCommaList").split(",");

		List<Weightcertificate> seperateWeightList = outboundDao
				.getWeightcertificateList(gbl.getSeq().toString());

		List<Weightcertificate> firstSeperateWeightList = new ArrayList<Weightcertificate>();
		List<Weightcertificate> secondSeperateWeightList = new ArrayList<Weightcertificate>();

		for (Weightcertificate weightParam : seperateWeightList) {
			boolean checkInputValue = false;
			for (String firstSeq : weightSeqList) {
				if (weightParam.getSeq() == Integer.parseInt(firstSeq)) {
					firstSeperateWeightList.add(weightParam);
					checkInputValue = true;
					break;
				}
			}

			if (!checkInputValue) {
				secondSeperateWeightList.add(weightParam);
			}
		}
		GBLStatus gblStatus = outboundDao.getGblProcess(gbl.getSeq());

		Integer gblSeq = gbl.getSeq();
		String gblNo = gbl.getNo();
		Double lbs = Double.parseDouble(gbl.getLbs());
		Double seperateWeightFirst = 0.0;
		Double seperateWeightSecond = 0.0;

		for (Weightcertificate first : firstSeperateWeightList) {
			seperateWeightFirst += Double.parseDouble(first.getGross());
		}

		for (Weightcertificate second : secondSeperateWeightList) {
			seperateWeightSecond += Double.parseDouble(second.getGross());
		}

		if (lbs > seperateWeightFirst) {
			GBL gblSeperateFirst = gbl;
			gblSeperateFirst.setNo(gblNo + "-sub1");
			gblSeperateFirst.setLbs(seperateWeightFirst.toString());

			outboundDao.insertGbl(gblSeperateFirst);
			for (Weightcertificate weightcertificate : firstSeperateWeightList) {
				weightcertificate.setGblSeq(gblSeperateFirst.getSeq());
				outboundDao.insertWeightcertificate(weightcertificate);
			}

			GBLStatus gblStatusFirst = gblStatus;
			gblStatusFirst.setNo(gblSeperateFirst.getNo());
			outboundDao.insertGblStatusCopy(gblStatusFirst);

			GBL gblSeperateSecond = gbl;
			gblSeperateSecond.setNo(gblNo + "-sub2");
			gblSeperateSecond.setLbs(seperateWeightSecond.toString());

			outboundDao.insertGbl(gblSeperateSecond);
			for (Weightcertificate weightcertificate : secondSeperateWeightList) {
				weightcertificate.setGblSeq(gblSeperateSecond.getSeq());
				outboundDao.insertWeightcertificate(weightcertificate);
			}

			GBLStatus gblStatusSecond = gblStatus;
			gblStatusSecond.setNo(gblSeperateSecond.getNo());
			outboundDao.insertGblStatusCopy(gblStatusSecond);

			GBL gblTemp = new GBL();
			gblTemp.setSeq(gblSeq);
			gblTemp.setSeperateFlag(true);
			gblTemp.setLbs(lbs.toString());
			outboundDao.updateGbl(gblTemp);
		}
	}

	public void deletManifast(Integer seq) {
		outboundDao.deleteGblStatusByTruckManifast(seq);

		outboundDao.deleteWeightCertificateByTruckManiafast(seq);

		outboundDao.deleteTruckGbl(seq);

		outboundDao.deleteTruckManifast(seq);
	}

	public void mergeSubmit(Map<String, String> gblMap) {
		GBL gbl = new GBL();
		gbl.setNo(gblMap.get("no").substring(0, gblMap.get("no").length() - 5));

		outboundDao.mergeGblWeight(gbl);

		outboundDao.mergeGblStatus(gbl);

		outboundDao.mergeGbl(gbl);

		outboundDao.setSeperatedFlag(gbl);
	}

	public List<Container> getContainerList() {
		return outboundDao.getContainerList();
	}

	public List<Tcmd> getTcmdList() {
		return outboundDao.getTcmdList();
	}

	public int getTcmdGblListCount(OutboundFilter outboundFilter) {
		return outboundDao.getTcmdGblListCount(outboundFilter);
	}

	public List<GBL> getTcmdGblList(OutboundFilter outboundFilter) {
		return outboundDao.getTcmdGblList(outboundFilter);
	}

	public void insertTcmdList(Map<String, String> gblSeq) {
		String[] gblSeqList = gblSeq.get("seqList").split(",");
		
		List<GBL> gblArmyList = new ArrayList<GBL>();
		List<GBL> gblForceList = new ArrayList<GBL>();
		for( String seq : gblSeqList){
			GBL gblArmy = outboundDao.getMilSvcGbl(seq, "A");
			if(gblArmy != null)
				gblArmyList.add(gblArmy);
		}

		for( String seq : gblSeqList){			
			GBL gblForce = outboundDao.getMilSvcGbl(seq, "F");
			if(gblForce != null)
				gblForceList.add(gblForce);
		}
		
		String oneTcmdFlag = "";
		if(gblArmyList.size() > 0){
			oneTcmdFlag = gblArmyList.get(0).getNo();
		} else if (gblForceList.size() > 0 ){
			oneTcmdFlag = gblForceList.get(0).getNo();
		}
		
		if(gblArmyList.size() > 0){			
			GBL gbl = gblArmyList.get(0);
	
			Tcmd tcmd = setTcmd(gbl);
			tcmd.setOneTcmdFlag(oneTcmdFlag);
			
			outboundDao.insertTcmd(tcmd);
			
			for( GBL gblTemp : gblArmyList){
				Map<String, Integer> seqMap = new HashMap<String, Integer>();
				seqMap.put("gblSeq", gblTemp.getSeq());
				seqMap.put("tcmdSeq", tcmd.getSeq());
							
				outboundDao.insertTcmdGblList(seqMap);
			}
		}
		
		if(gblForceList.size() > 0){		
			GBL gbl = gblForceList.get(0);
	
			Tcmd tcmd = setTcmd(gbl);
			tcmd.setOneTcmdFlag(oneTcmdFlag);
			
			outboundDao.insertTcmd(tcmd);
			
			for( GBL gblTemp : gblForceList){
				Map<String, Integer> seqMap = new HashMap<String, Integer>();
				seqMap.put("gblSeq", gblTemp.getSeq());
				seqMap.put("tcmdSeq", tcmd.getSeq());
							
				outboundDao.insertTcmdGblList(seqMap);
			}			
		}
	}

	public Tcmd getTcmdContent(Integer seq) {
		return outboundDao.getTcmdContent(seq);
	}

	public List<GBL> getTcmdContentGblList(Integer seq) {
		return outboundDao.getTcmdContentGblList(seq);
	}

	private Tcmd setTcmd(GBL gbl) {
		Tcmd tcmd = new Tcmd();

		if (gbl.getCode().equals("J")) {
			tcmd.setDocId("TFD");
		} else if (gbl.getCode().equals("T")) {
			tcmd.setDocId("TH1");
		}

		// 용산,동두천, 대구,평택,부산,의정부
		if (gbl.getAreaLocal().equals("YS") || gbl.getAreaLocal().equals("TDC")
				|| gbl.getAreaLocal().equals("DG")
				|| gbl.getAreaLocal().equals("PYT")
				|| gbl.getAreaLocal().equals("BS")
				|| gbl.getAreaLocal().equals("UJB")) {
			tcmd.setConsignor("W81LYE");
		} else if(gbl.getAreaLocal().equals("OS")){
			tcmd.setConsignor("FB5294");
		} else if(gbl.getAreaLocal().equals("KS")){
			tcmd.setConsignor("FB5284");
		}
		
		tcmd.setCommSpecHdig("JZ");
		tcmd.setAirDim("A");
		tcmd.setPoe("OSN");
		tcmd.setPod(gbl.getPod());
		tcmd.setMode("P");
		tcmd.setPack("PT");
		tcmd.setTransControlNo1("00");
		tcmd.setTransControlNo2(gbl.getScac());
		
		String year = Integer.toString(DateUtil.getYear());
		String getJulianDate = year.substring(3, 4) + DateUtil.getDaysBetween(year + "0101", DateUtil.getToday("YYYYMMDD"));
		tcmd.setTransControlNo3(getJulianDate);		
		tcmd.setTransControlNo4(gbl.getPcs());
		tcmd.setTransControlNo5("LSG");
		
		String pod = gbl.getPod();
		if(pod.equals("SUU")){
			tcmd.setConsignee("FB4427");
		} else if(pod.equals("RMS")){
			tcmd.setConsignee("FY1372");
		} else if(pod.equals("DNA")){
			tcmd.setConsignee("FB5270");
		} else if(pod.equals("MSJ")){
			tcmd.setConsignee("FB5205");
		} else if(pod.equals("GUM")){
			tcmd.setConsignee("FB5240");
		} else if(pod.equals("HIK")){
			tcmd.setConsignee("N00604");
		}
		
		tcmd.setPri("2");
		tcmd.setCarrier(gbl.getScac());
		if( gbl.getCode().equals("T") ){
			tcmd.setRemark(gbl.getScac() + " / YOUNGJIN T&t CO.,LTD / HHG");
		} else {
			tcmd.setRemark(gbl.getScac() + " / YOUNGJIN T&t CO.,LTD");
		}
		
		tcmd.setTransportationCa(gbl.getMilSVC() + getJulianDate + gbl.getSsn() + gbl.getCode() + "XX");
		
		return tcmd;
	}

	public void updateTcmdGbl(Map<String, String> map) {
		outboundDao.updateTcmdGbl(map);
	}

	public void updateTcmd(Map<String, String> map) {
		outboundDao.updateTcmd(map);
	}
}
