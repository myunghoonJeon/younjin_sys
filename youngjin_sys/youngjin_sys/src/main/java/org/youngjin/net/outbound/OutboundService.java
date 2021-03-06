package org.youngjin.net.outbound;

import java.awt.Container;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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
import org.youngjin.net.basic.BasicService;
import org.youngjin.net.basic.Company;
import org.youngjin.net.code.Code;
import org.youngjin.net.code.CodeDao;
import org.youngjin.net.invoice.InvoiceService;
import org.youngjin.net.login.User;
import org.youngjin.net.process.GBlock;
import org.youngjin.net.upload.UploadService;
import org.youngjin.net.util.DateUtil;

@Service
public class OutboundService {

	@Resource
	private OutboundDao outboundDao;

	@Resource
	private InvoiceService invoiceService;
	
	@Resource
	private UploadService uploadService;
	
	@Resource
	private BasicService basicService;
	
	@Resource
	private CodeDao codeDao;

	public int getGblListCount(OutboundFilter outboundFilter, User user) {
		if(!"LEVEL4".equals(user.getAuthStr()) && !"LEVEL3".equals(user.getAuthStr()) && !"LEVEL2".equals(user.getAuthStr())){
			outboundFilter.setArea("0" + user.getArea().toString());
		}

		return outboundDao.getGblListCount(outboundFilter);
	}
	
	
	public List<GBL> getGblList(OutboundFilter outboundFilter, User user) {
		if(!"LEVEL4".equals(user.getAuthStr()) && !"LEVEL3".equals(user.getAuthStr()) && !"LEVEL2".equals(user.getAuthStr())){
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
//		try {
//			FileWriter fw = new FileWriter("test.txt");
//			BufferedWriter bw = new BufferedWriter(fw);
//			
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
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
	
	public String getGblTruckDate(Integer seq){
		return outboundDao.getGblTruckDate(seq);
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
	public Map<String, String> getGblStatus2(OutboundFilter outboundFilter,List<GBL> gblList) {
		Map<String, String> gblStatusMap = new HashMap<String, String>();
		for(GBL gbl : gblList){
			outboundFilter.setGblNoForGetOutboundList(gbl.getNo());
			GBLStatus gblStatus = outboundDao.getGblStatus2(outboundFilter);
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

		Integer grossSum = 0;
		Integer netSum = 0;
		Integer cuftSum = 0;

		String[] pieceList = weightcertificate.getPiece().split(",", count);
		String[] typeList = weightcertificate.getType().split(",", count);
		String[] grossKgList = weightcertificate.getGrossKg().split(",", count);
		String[] grossList = weightcertificate.getGross().split(",", count);
		String[] tareList = weightcertificate.getTare().split(",", count);
		String[] netList = weightcertificate.getNet().split(",", count);
		String[] cuftList = weightcertificate.getCuft().split(",", count);
		String[] remarkList = weightcertificate.getRemark().split(",", count);
		String[] statusList = weightcertificate.getStatus().split(",", count);
		
		String progear = weightcertificate.getProGear();

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
			
			paramWeightcertificate.setProGear(progear);

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

			grossSum += Integer.parseInt(grossList[i]);
			netSum += Integer.parseInt(netList[i]);
			cuftSum += Integer.parseInt(cuftList[i]);
		}

		GBL gbl = new GBL();
		gbl.setSeq(weightcertificate.getGblSeq());
		gbl.setLbs(grossSum.toString());
		gbl.setGrossWeight(grossSum.toString());
		gbl.setNetWeight(netSum.toString());
		gbl.setCuft(cuftSum.toString());
		gbl.setTotalPcs(Integer.toString(count));

		outboundDao.updateGbl(gbl);

		/*
		 * Map<String, Integer> param = new HashMap<String, Integer>();
		 * param.put("seq", weightcertificate.getGblSeq()); param.put("status",
		 * 1); outboundDao.updateGblStatus(param);
		 */
	}

	public void deleteWeightCertificate(Weightcertificate weightcertificate) {
		outboundDao.deleteWeightCertificate(weightcertificate);
	}

	public int getTruckListCount(OutboundFilter outboundFilter) {
		return outboundDao.getTruckListCount(outboundFilter);
	}

	public List<TruckManifast> getTruckList(OutboundFilter outboundFilter) {
		return outboundDao.getTruckList(outboundFilter);
	}
	
	public List<String> getTruckGblNo(Integer seq) {
		return outboundDao.getTruckGblNo(seq);
	}
	public List<String> getBookingListGblNo(Integer seq) {
		return outboundDao.getBookingListGblNo(seq);
	}
	
	public List<GBL> getTruckGblList(OutboundFilter outboundFilter) {
		return outboundDao.getTruckGblList(outboundFilter);
	}

	public List<Weightcertificate> getTruckWeightList(String seq) {
		return outboundDao.getWeightcertificateList(seq);
	}

	public void insertTruckManifast(Map<String, String> gblSeq) {

		String[] gblSeqList = gblSeq.get("gblSeq").split(",");
		System.out.println("[[[[[[[[[[[[ gblSeqList size : "+gblSeqList.length+" ]]]]]]]]]]]]");
		GBL gbl = getGbl(Integer.parseInt(gblSeqList[0]));
		
		TruckManifast truckManifast = new TruckManifast();
		truckManifast.setBranch(gbl.getAreaLocal());
		truckManifast.setCode(gbl.getCode());
		truckManifast.setDate(gblSeq.get("truckdate"));
		
		outboundDao.insertTurckManifast(truckManifast);

		for (int i = 0; i < gblSeqList.length; i++) {
			GBL gblTemp = getGbl(Integer.parseInt(gblSeqList[i]));
			gblTemp.setTruckSeq(truckManifast.getSeq());
			outboundDao.updateGbl(gblTemp);
			outboundDao.updateWeightcertificate(gblTemp);
			System.out.println("[[[[[[[[[ gbl no : "+gblTemp.getGblNo()+" ]]]]]]]]");
			Map<String, Integer> paramMap = new HashMap<String, Integer>();
			paramMap.put("truckmanifast", 1);
			paramMap.put("seq", gblTemp.getSeq());
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
		int gblSeq = addition.getGblSeq();
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("preparation", 1);
		map.put("seq", gblSeq);
		outboundDao.updateGblStatus(map);
		//여기다가 코드 T랑 J변하게 하자
		GBL gbl = outboundDao.getGbl(gblSeq);
		String code = gbl.getCode();
		if(code.equals("T") || code.equals("J")|| code.equals("8")){//만약 코드 T랑 J일경우 truck 이랑 booking 안하고 invoice 되도록 넘겨보자
			System.out.println("gblSeq : "+gblSeq);
			outboundDao.passBookingList(gbl.getNo());
			System.out.println("[[[[[[[[[[[[[GBL NO : "+gbl.getNo()+" ]]] [[[ booking list pass]]]]]]]]]]]");
			outboundDao.passWeightCertificateByBookingList(gblSeq);
			System.out.println("[[[[[[[[[[[[[WeightCertificate Booking Pass]]]]]]]]]]]");
			outboundDao.passBookingListGbl(gblSeq);
			System.out.println("[[[[[[[[[[[[[Bookinglist GBL Pass]]]]]]]]]]]");
//			outboundDao.deleteBookingList(bookingSeq);
//			System.out.println("[[[[[[[[[[[[[4]]]]]]]]]]]");

		}
	}

	public void insertBookingList(Map<String, String> gblSeq) {
		String[] gblSeqList = gblSeq.get("gblSeq").split(",");
		
		GBL gbl = getGbl(Integer.parseInt(gblSeqList[0]));
		String pud = gbl.getPud();
		
		BookingList bookingList = new BookingList();
		
		int yjCount = outboundDao.getBookingListYjCount()+1;
		bookingList.setYjCount(yjCount);
		
		outboundDao.insertBookingList(bookingList);
		gbl.setBookingSeq(bookingList.getSeq());

		for (int i = 0; i < gblSeqList.length; i++) {
			String tempGblSeq = gblSeqList[i];
			GBL gblTemp = getGbl(Integer.parseInt(tempGblSeq));
			gblTemp.setSeq(Integer.parseInt(tempGblSeq));
			gblTemp.setBookingSeq(bookingList.getSeq());
			gblTemp.setLbs(null);
			outboundDao.updateGbl(gblTemp);
			outboundDao.updateWeightcertificate(gblTemp);

			Map<String, Integer> paramMap = new HashMap<String, Integer>();
			paramMap.put("booking", 1);
			paramMap.put("seq", gblTemp.getSeq());
			outboundDao.updateGblStatus(paramMap);
		}
	}

	public void deleteBookingList(Map<String, String> bookingSeq) {
		// TODO:
		Integer seq = Integer.valueOf(bookingSeq.get("seq"));
		
		outboundDao.deleteGblStatusByBookingList(seq);
		System.out.println("[[[[[[[[[[[[[1]]]]]]]]]]]");
		outboundDao.deleteWeightCertificateByBookingList(seq);
		System.out.println("[[[[[[[[[[[[[2]]]]]]]]]]]");
		outboundDao.deleteBookingListGbl(seq);
		System.out.println("[[[[[[[[[[[[[3]]]]]]]]]]]");
		outboundDao.deleteBookingList(bookingSeq);
		System.out.println("[[[[[[[[[[[[[4]]]]]]]]]]]");
	}
	
	public void deleteTcmdList(Map<String,String> tcmdSeq){
		Integer seq = Integer.valueOf(tcmdSeq.get("seq"));
		System.out.println("[[[[[[[[ DELETE TCMD SEQ : "+seq+" ]]]]]]]]]]");
		outboundDao.deleteTcmdList(seq);
		System.out.println("[[[[[[[[ DELETE TCMD LIST ]]]]]]]]]]");
		outboundDao.deleteTcmdGblList(seq);
		System.out.println("[[[[[[[[ DELETE TCMD GBL LIST ]]]]]]]]]]");
	}
	public List<DeliveryGbl> getBookingListPrint(Integer seq) {
		List<DeliveryGbl> bookingGblList = outboundDao.getBookingListPrint(seq);
		return bookingGblList;
	}
	
	public List<PowerOfAttornyList> getPowerOfAttornyList(List<DeliveryGbl> list){
		List<PowerOfAttornyList> poalist = new ArrayList<PowerOfAttornyList>();
		PowerOfAttornyList poal;
		Company company;
		for(int i=0;i<list.size();i++){
			poal = new PowerOfAttornyList();
			int tempSeq = Integer.parseInt(list.get(i).getSeq());
			GBL gbl = getGbl(tempSeq);
			
			company = basicService.getCompanyByCode("YJ");
			poal.setGbl(gbl);
			poal.setCompany(company);
			poalist.add(poal);
		}
		return poalist;
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
//		outboundDao.modifyInvoice(gbl);
	}
	public void updateBookingListUpdate(Map map){
		outboundDao.updateBookingListUpdate(map);
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

		String[] invoiceMemorandumType = (dd619.getCount() > 0) ? dd619.getInvoiceMemorandumType()
				.split(",", dd619.getCount()) : null;
		String[] invoiceMemorandumValue = (dd619.getCount() > 0) ? dd619.getInvoiceMemorandumValue()
				.split(",", dd619.getCount()) : null;

		for (int i = 0; i < dd619.getCount(); i++) {
			paramAddition.setTitle(invoiceMemorandumType[i]);
			paramAddition.setPrice(invoiceMemorandumValue[i]);

			Integer additionCheck = outboundDao.checkAddtionComplete(paramAddition);

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
		System.out.println("[[[[[[[[[[[[[1]]]]]]]]]]]");
		outboundDao.deleteWeightCertificateByTruckManiafast(seq);
		System.out.println("[[[[[[[[[[[[[2]]]]]]]]]]]");
		outboundDao.deleteTruckGbl(seq);
		System.out.println("[[[[[[[[[[[[[3]]]]]]]]]]]");
		outboundDao.deleteTruckManifast(seq);
		System.out.println("[[[[[[[[[[[[[4]]]]]]]]]]]");
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
	
	public List<String> getTcmdGblSeqList(int seq){
		return outboundDao.getTcmdGblSeqList(seq);
	}
	
	public int getTcmdGblListCount(OutboundFilter outboundFilter) {
		return outboundDao.getTcmdGblListCount(outboundFilter);
	}
	public int getTcmdListCount(OutboundFilter outboundFilter) {
		return outboundDao.getTcmdListCount(outboundFilter);
	}
	public int getHouseListCount(OutboundFilter outboundFilter) {
		return outboundDao.getHouseListCount(outboundFilter);
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
			int gross=0;
			int cuft=0;
			int piece=0;
			for( GBL gblTemp : gblArmyList){
				gross += Integer.parseInt(gblTemp.getLbs());
				cuft += Integer.parseInt(gblTemp.getCuft());
				piece += Integer.parseInt(gblTemp.getPcs());
			}
			
			tcmd.setWeight(gross+"");
			tcmd.setPieces(piece+"");
			tcmd.setCube(cuft+"");
			outboundDao.insertTcmd(tcmd);
			
			for( GBL gblTemp : gblArmyList){
				Map<String, Integer> seqMap = new HashMap<String, Integer>();
				seqMap.put("gblSeq", gblTemp.getSeq());
				seqMap.put("tcmdSeq", tcmd.getSeq());
				outboundDao.insertTcmdGblList(seqMap);
			}
			
		}//gblArmyList case
		
		if(gblForceList.size() > 0){		
			GBL gbl = gblForceList.get(0);
				Tcmd tcmd = setTcmd(gbl);
			tcmd.setOneTcmdFlag(oneTcmdFlag);
			int gross=0;
			int cuft=0;
			int piece=0;
			for( GBL gblTemp : gblForceList){
				gross += Integer.parseInt(gblTemp.getLbs());
				cuft += Integer.parseInt(gblTemp.getCuft());
				piece += Integer.parseInt(gblTemp.getPcs());
			}
			tcmd.setWeight(gross+"");
			tcmd.setPieces(piece+"");
			tcmd.setCube(cuft+"");
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
			tcmd.setTrAcct("F48D");
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
		tcmd.setTransControlNo4("L00"+gbl.getPcs());
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
		///jmh addtion
		tcmd.setConsignee2(gbl.getGbloc());
		//////////////////////////////////////
		
		
		tcmd.setTransportationCa(gbl.getMilSVC() + getJulianDate + gbl.getSsn()+""+ gbl.getCode());
		
		return tcmd;
	}

	public void updateTcmdGbl(Map<String, String> map) {
		outboundDao.updateTcmdGbl(map);
	}

	public void updateTcmd(Map<String, String> map) {
		outboundDao.updateTcmd(map);
	}

	public void deleteGBL(GBL gbl) {
		outboundDao.deleteGblStatus(gbl.getSeq());
		
		GBL gblTemp = outboundDao.getGbl(gbl.getSeq());
				
		outboundDao.deleteTruckManifast(gblTemp.getTruckSeq());
		
		Map<String, String> bookingSeq = new HashMap<String, String>();
		bookingSeq.put("seq", gbl.getSeq().toString());
		outboundDao.deleteBookingList(bookingSeq);
		
		List<Integer> tcmdSeqList = outboundDao.getTcmdContentGblListByGblSeq(gbl.getSeq());
		
		for(Integer tcmdSeq : tcmdSeqList){
			outboundDao.deleteTcmd(tcmdSeq);
		}
		
		outboundDao.deleteHouse(gbl.getHouseSeq());
		
		List<Integer> getInvoiceSeqList = invoiceService.getInvoiceSeqListByGblSeq(gbl.getSeq());
		
		for(Integer invoiceSeq : getInvoiceSeqList){
			invoiceService.deleteInvoice(invoiceSeq, "outbound");
		}
		
		outboundDao.deleteGBL(gbl);
	}

	public List<GBL> getHouseGblList(OutboundFilter outboundFilter) {
		return outboundDao.getHouseGblList(outboundFilter);
	}

	public void houseSeperateGbl(Map<String, String> gblMap) {
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
			gblSeperateFirst.setNo(gblNo + "-house1");
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
			gblSeperateSecond.setNo(gblNo + "-house2");
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
			gblTemp.setHouseSeperateFlag(true);
			gblTemp.setLbs(lbs.toString());
			outboundDao.updateGbl(gblTemp);
		}
		
	}

	public void houseMergeSubmit(Map<String, String> gblMap) {
		GBL gbl = new GBL();
		gbl.setNo(gblMap.get("no"));

		outboundDao.houseMergeGblWeight(gbl);

		outboundDao.houseMergeGblStatus(gbl);

		outboundDao.houseMergeGbl(gbl);

		outboundDao.setHouseSeperatedFlag(gbl);		
	}

	public void insertHouse(Map<String, String> gblSeq) {

		String[] gblSeqList = gblSeq.get("gblSeq").split(",");
		String[] vesselList = gblSeq.get("vessel").split(",");
		String[] voyageList = gblSeq.get("voyage").split(",");
		String[] company = gblSeq.get("company").split(",");
		
		String contNo = gblSeq.get("contNo");
		String sealNo = gblSeq.get("sealNo");
		String carrierBookingNo = gblSeq.get("carrierBookingNo");

		House house = new House();
		house.setContNo(contNo);
		house.setSealNo(sealNo);
		house.setCarrierBookingNo(carrierBookingNo);

		outboundDao.insertHouse(house);

		for (int i = 0; i < gblSeqList.length; i++) {
			GBL gblTemp = getGbl(Integer.parseInt(gblSeqList[i]));
			gblTemp.setHouseSeq(house.getSeq());
			gblTemp.setHouseVessel(vesselList[i]);
			gblTemp.setHouseVoyage(voyageList[i]);
			gblTemp.setHouseCompany(company[i]);
			outboundDao.updateGbl(gblTemp);
			outboundDao.updateWeightcertificate(gblTemp);
			
			/*if (gblTemp.getNo().contains("-house")) {
				String gblNo = gblTemp.getNo().substring(0,
						gblTemp.getNo().length() - 7);
				int checkSeperateComplete = outboundDao
						.checkSeperateComplete(gblNo);
				if (checkSeperateComplete == 0) {
					outboundDao.updateGblStatusByGblNo(gblNo);
				}
			}*/
		}
	}

	public List<House> getHouseList(OutboundFilter outboundFilter) {
		return outboundDao.getHouseList(outboundFilter);
	}

	public void deleteHouse(Map<String, String> gblSeq) {
		Integer seq = Integer.parseInt(gblSeq.get("seq"));
		
		outboundDao.deleteUpdateHouseWeight(seq);
		
		outboundDao.deleteHouse(seq);
	}

	public List<GBL> getGblListHouse(String seq) {
		return outboundDao.getGblListHouse(seq);
	}

	public House getHouse(String seq) {
		return outboundDao.getHouse(seq);
	}

	public GBL getGblInfoByNo(GBL gbl) {
		return outboundDao.getGblInfoByNo(gbl);
	}
}
