package org.youngjin.net.invoice;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.youngjin.net.GBL;
import org.youngjin.net.basic.BasicService;
import org.youngjin.net.code.Code;
import org.youngjin.net.code.CodeDao;
import org.youngjin.net.inbound.InboundDao;
import org.youngjin.net.inbound.WeightIb;
import org.youngjin.net.memorandum.Memorandum;
import org.youngjin.net.memorandum.MemorandumDao;
import org.youngjin.net.outbound.Addition;
import org.youngjin.net.outbound.OutboundDao;
import org.youngjin.net.outbound.Weightcertificate;
import org.youngjin.net.util.DateUtil;

@Service
public class InvoiceService {

	@Resource
	private InvoiceDao invoiceDao;

	@Resource
	private OutboundDao outboundDao;

	@Resource
	private InboundDao inboundDao;

	@Resource
	private MemorandumDao memorandumDao;

	@Resource
	private CodeDao codeDao;
	
	@Resource
	private BasicService basicService;

	public void checkNewYearRate() {
		// SIT charge Rate schedule check
		Integer SITcheck = invoiceDao.getSitListCount();
		if (SITcheck == 0) {
			List<Rate> sitRateList = new ArrayList<Rate>();

			sitRateList.add(new Rate("SIT-FIRST DAY - IT13 item 519A", "UB",
					"OUTBOUND"));
			sitRateList.add(new Rate("SIT-EACH ADDITIONALDAY - IT13 item 519C",
					"UB", "OUTBOUND"));
			sitRateList.add(new Rate("SIT-DELIVERY CHARGE & ADM FEE", "UB",
					"OUTBOUND"));
			sitRateList.add(new Rate("30mile 이하 - IT13 item 521I", "UB",
					"OUTBOUND"));
			sitRateList.add(new Rate(
					"30mile 이하  - IT13 item 521K (minimum per shipment)", "UB",
					"OUTBOUND"));
			sitRateList.add(new Rate(
					"30mile 이상  - IT13 item 521J (minimum per shipment)", "UB",
					"OUTBOUND"));
			sitRateList.add(new Rate("ADM FEE - IT13 item 521L", "UB",
					"OUTBOUND"));
			sitRateList.add(new Rate("SIT-FIRST DAY -IT13 item 518C", "HHG",
					"OUTBOUND"));
			sitRateList.add(new Rate("SIT-EACH ADDITIONALDAY - IT13 item 518D",
					"HHG", "OUTBOUND"));

			sitRateList.add(new Rate("SIT-FIRST DAY - IT13 item 519A", "UB",
					"INBOUND"));
			sitRateList.add(new Rate("SIT-EACH ADDITIONALDAY - IT13 item 519C",
					"UB", "INBOUND"));
			sitRateList.add(new Rate("SIT-DELIVERY CHARGE & ADM FEE", "UB",
					"INBOUND"));
			sitRateList.add(new Rate("30mile 이하 - IT13 item 521I", "UB",
					"INBOUND"));
			sitRateList.add(new Rate(
					"30mile 이하  - IT13 item 521K (minimum per shipment)", "UB",
					"INBOUND"));
			sitRateList.add(new Rate(
					"30mile 이상  - IT13 item 521J (minimum per shipment)", "UB",
					"INBOUND"));
			sitRateList.add(new Rate("ADM FEE - IT13 item 521L", "UB",
					"INBOUND"));
			sitRateList.add(new Rate("SIT-FIRST DAY -IT13 item 518C", "HHG",
					"INBOUND"));
			sitRateList.add(new Rate("SIT-EACH ADDITIONALDAY - IT13 item 518D",
					"HHG", "INBOUND"));

			invoiceDao.insertNewYearSIT(sitRateList);
		}

		// Other charge Schedule check
		Integer otherCheck = invoiceDao.getOtherListCount();
		if (otherCheck == 0) {
			List<Rate> otherRateList = new ArrayList<Rate>();

			otherRateList.add(new Rate("EXTRA PICKUP CHARGE - IT13 item 2222",
					"UB", "OUTBOUND"));
			otherRateList.add(new Rate("REWEIGHT CHARGE - IT13 item 505B",
					"UB", "OUTBOUND"));
			otherRateList.add(new Rate("TERMINATION CHARGE - IT13 item 523A",
					"UB", "OUTBOUND"));
			otherRateList
					.add(new Rate("termination discount", "UB", "OUTBOUND"));
			otherRateList.add(new Rate("DIVERSION CHARGE - IT13 item 526A",
					"UB", "OUTBOUND"));

			otherRateList.add(new Rate("EXTRA PICKUP CHARGE -IT13 item 1111",
					"HHG", "OUTBOUND"));
			otherRateList.add(new Rate("REWEIGHT CHARGE - IT13 item 505A",
					"HHG", "OUTBOUND"));
			otherRateList.add(new Rate("TERMINATION CHARGE - IT13 item 522A",
					"HHG", "OUTBOUND"));
			otherRateList.add(new Rate("termination discount", "HHG",
					"OUTBOUND"));
			otherRateList.add(new Rate("DIVERSION CHARGE - IT13 item 526A",
					"HHG", "OUTBOUND"));
			otherRateList.add(new Rate("LONG CARRY - IT13 item xxxxxx", "HHG",
					"OUTBOUND"));
			otherRateList.add(new Rate("DEBRIS REMOVAL - IT13 item", "HHG",
					"OUTBOUND"));

			otherRateList.add(new Rate("EXTRA PICKUP CHARGE - IT13 item 2222",
					"UB", "INBOUND"));
			otherRateList.add(new Rate("REWEIGHT CHARGE - IT13 item 505B",
					"UB", "INBOUND"));
			otherRateList.add(new Rate("TERMINATION CHARGE - IT13 item 523A",
					"UB", "INBOUND"));
			otherRateList
					.add(new Rate("termination discount", "UB", "INBOUND"));
			otherRateList.add(new Rate("DIVERSION CHARGE - IT13 item 526A",
					"UB", "INBOUND"));

			otherRateList.add(new Rate("EXTRA PICKUP CHARGE -IT13 item 1111",
					"HHG", "INBOUND"));
			otherRateList.add(new Rate("REWEIGHT CHARGE - IT13 item 505A",
					"HHG", "INBOUND"));
			otherRateList.add(new Rate("TERMINATION CHARGE - IT13 item 522A",
					"HHG", "INBOUND"));
			otherRateList
					.add(new Rate("termination discount", "HHG", "INBOUND"));
			otherRateList.add(new Rate("DIVERSION CHARGE - IT13 item 526A",
					"HHG", "INBOUND"));
			otherRateList.add(new Rate("LONG CARRY - IT13 item xxxxxx", "HHG",
					"INBOUND"));
			otherRateList.add(new Rate("DEBRIS REMOVAL - IT13 item", "HHG",
					"INBOUND"));

			invoiceDao.insertNewYearOther(otherRateList);
		}
	}

	public Map<String, Map<String, List<Rate>>> getSitMap(Rate rate) {
		Map<String, Map<String, List<Rate>>> sitMap = new HashMap<String, Map<String, List<Rate>>>();
		List<Rate> sitList = invoiceDao.getSitList(rate);

		Map<String, List<Rate>> sitInboundMap = new HashMap<String, List<Rate>>();
		Map<String, List<Rate>> sitOutboundMap = new HashMap<String, List<Rate>>();

		List<Rate> sitOBUBList = new ArrayList<Rate>();
		List<Rate> sitOBHHGList = new ArrayList<Rate>();

		List<Rate> sitIBUBList = new ArrayList<Rate>();
		List<Rate> sitIBHHGList = new ArrayList<Rate>();

		for (Rate paramRate : sitList) {
			if ("OUTBOUND".equals(paramRate.getProcess())) {
				if ("UB".equals(paramRate.getCode())) {
					sitOBUBList.add(paramRate);
				} else if ("HHG".equals(paramRate.getCode())) {
					sitOBHHGList.add(paramRate);
				}
			} else if ("INBOUND".equals(paramRate.getProcess())) {

				if ("UB".equals(paramRate.getCode())) {
					sitIBUBList.add(paramRate);
				} else if ("HHG".equals(paramRate.getCode())) {
					sitIBHHGList.add(paramRate);
				}
			}
		}

		sitOutboundMap.put("UB", sitOBUBList);
		sitOutboundMap.put("HHG", sitOBHHGList);

		sitInboundMap.put("UB", sitIBUBList);
		sitInboundMap.put("HHG", sitIBHHGList);

		sitMap.put("OUTBOUND", sitOutboundMap);
		sitMap.put("INBOUND", sitInboundMap);

		return sitMap;
	}

	public Map<String, Map<String, List<Rate>>> getOtherMap(Rate rate) {
		Map<String, Map<String, List<Rate>>> otherMap = new HashMap<String, Map<String, List<Rate>>>();
		List<Rate> otherList = invoiceDao.getOtherList(rate);

		Map<String, List<Rate>> otherInboundMap = new HashMap<String, List<Rate>>();
		Map<String, List<Rate>> otherOutboundMap = new HashMap<String, List<Rate>>();

		List<Rate> otherOBUBList = new ArrayList<Rate>();
		List<Rate> otherOBHHGList = new ArrayList<Rate>();

		List<Rate> otherIBUBList = new ArrayList<Rate>();
		List<Rate> otherIBHHGList = new ArrayList<Rate>();

		for (Rate paramRate : otherList) {
			if ("OUTBOUND".equals(paramRate.getProcess())) {
				if ("UB".equals(paramRate.getCode())) {
					otherOBUBList.add(paramRate);
				} else if ("HHG".equals(paramRate.getCode())) {
					otherOBHHGList.add(paramRate);
				}
			} else if ("INBOUND".equals(paramRate.getProcess())) {

				if ("UB".equals(paramRate.getCode())) {
					otherIBUBList.add(paramRate);
				} else if ("HHG".equals(paramRate.getCode())) {
					otherIBHHGList.add(paramRate);
				}
			}
		}

		otherOutboundMap.put("UB", otherOBUBList);
		otherOutboundMap.put("HHG", otherOBHHGList);

		otherInboundMap.put("UB", otherIBUBList);
		otherInboundMap.put("HHG", otherIBHHGList);

		otherMap.put("OUTBOUND", otherOutboundMap);
		otherMap.put("INBOUND", otherInboundMap);

		return otherMap;
	}

	public void basicInsert(Rate rate) {
		int checkRate = invoiceDao.getBasicRateCheck(rate);

		if (checkRate == 0) {
			invoiceDao.basicInsert(rate);
		} else {
			invoiceDao.basicUpdate(rate);
		}
	}

	public Map<String, Map<String, Map<String, Rate>>> getBasicMap(Rate rate) {
		Map<String, Map<String, Map<String, Rate>>> basicMap = new HashMap<String, Map<String, Map<String, Rate>>>();

		List<Rate> rateList = invoiceDao.getBasicRateList(rate);
		String beforeTsp;
		String currentTsp;

		int rateListSize = rateList.size();

		if (rateListSize == 0) {
			beforeTsp = new String();
		} else {
			beforeTsp = rateList.get(0).getTsp();
		}

		// inbound List Map
		Map<String, Map<String, Rate>> inboundMap = new HashMap<String, Map<String, Rate>>();
		Map<String, Rate> inboundBasicMap = new HashMap<String, Rate>();
		for (int i = 0; i < rateListSize; i++) {
			Rate rateTemp = rateList.get(i);

			currentTsp = rateTemp.getTsp();

			if (!beforeTsp.equals(currentTsp)) {
				inboundMap.put(beforeTsp, inboundBasicMap);
				inboundBasicMap = new HashMap<String, Rate>();
				beforeTsp = currentTsp;
			}

			if ("inbound".equals(rateTemp.getProcess())) {
				inboundBasicMap.put(rateTemp.getCode(), rateTemp);
			}

			if (i != rateListSize - 1 && rateListSize > 1
					&& "outbound".equals(rateList.get(i + 1).getProcess())) {
				inboundMap.put(beforeTsp, inboundBasicMap);
				break;
			} else if (i == rateListSize - 1) {
				inboundMap.put(currentTsp, inboundBasicMap);
			}
		}

		// outbound List Map
		Map<String, Rate> outboundBasicMap = new HashMap<String, Rate>();
		Map<String, Map<String, Rate>> outboundMap = new HashMap<String, Map<String, Rate>>();
		for (int i = 0; i < rateListSize; i++) {
			Rate rateTemp = rateList.get(i);

			if ("inbound".equals(rateTemp.getProcess()))
				continue;

			currentTsp = rateTemp.getTsp();

			if (!beforeTsp.equals(currentTsp)) {
				outboundMap.put(beforeTsp, outboundBasicMap);
				outboundBasicMap = new HashMap<String, Rate>();
				beforeTsp = currentTsp;
			}

			if ("outbound".equals(rateTemp.getProcess())) {
				if (rateTemp.getObType() != null) {
					outboundBasicMap
							.put(rateTemp.getCode() + rateTemp.getObType(),
									rateTemp);
					outboundMap.put(rateTemp.getTsp(), outboundBasicMap);
				} else {
					outboundBasicMap.put(rateTemp.getCode(), rateTemp);
					outboundMap.put(rateTemp.getTsp(), outboundBasicMap);
				}
			}

			if (i == rateListSize - 1) {
				outboundMap.put(currentTsp, outboundBasicMap);
			}
		}

		basicMap.put("inbound", inboundMap);
		basicMap.put("outbound", outboundMap);

		return basicMap;
	}

	public void etcInsert(Rate rate) {
		int checkRate = invoiceDao.getEtcCheck(rate);

		if (checkRate == 0) {
			invoiceDao.etcInsert(rate);
		} else {
			invoiceDao.etcUpdate(rate);
		}
	}

	public void containerInsert(Rate rate) {
		int checkRate = invoiceDao.getContainerCheck(rate);

		if (checkRate == 0) {
			invoiceDao.containerInsert(rate);
		} else {
			invoiceDao.containerUpdate(rate);
		}
	}

	public Map<String, Map<String, Rate>> getContainerMap(Rate rate) {
		Map<String, Map<String, Rate>> containerMap = new HashMap<String, Map<String, Rate>>();
		Map<String, Rate> statusMap = new HashMap<String, Rate>();

		String beforeTsp;
		String currentTsp;

		List<Rate> containerList = invoiceDao.getContainerList(rate);
		int containerListSize = containerList.size();

		if (containerListSize == 0) {
			beforeTsp = new String();
		} else {
			beforeTsp = containerList.get(0).getTsp();
		}

		for (int i = 0; i < containerListSize; i++) {
			Rate rateTemp = containerList.get(i);

			currentTsp = rateTemp.getTsp();
			if (!currentTsp.equals(beforeTsp)) {
				containerMap.put(beforeTsp, statusMap);
				statusMap = new HashMap<String, Rate>();
				beforeTsp = currentTsp;
			}

			statusMap.put(rateTemp.getContainerStatus(), rateTemp);

			if (i == containerListSize - 1) {
				containerMap.put(currentTsp, statusMap);
			}
		}

		return containerMap;
	}

	public void sitInsert(Rate rate) {
		invoiceDao.sitUpdate(rate);
	}

	public void otherInsert(Rate rate) {
		invoiceDao.otherUpdate(rate);
	}

	public int getInvoiceListCount(InvoiceFilter invoiceFilter, String process) {
		invoiceFilter.setProcess(process);

		return invoiceDao.getInvoiceListCount(invoiceFilter);
	}

	public List<Invoice> getInvoiceList(InvoiceFilter invoiceFilter) {
		return invoiceDao.getInvoiceList(invoiceFilter);
	}

	public Invoice invoiceListAdd(Invoice invoice, String process) {
		String[] invoiceGblSeqList = invoice.getSeqList().split(",");

		List<InvoiceGbl> settingGblList = new ArrayList<InvoiceGbl>();

		for (String gblSeq : invoiceGblSeqList) {
			if (process.equals("outbound")) {
				settingGblList.add(invoiceDao
						.getInvoiceSettingGblListContent(gblSeq));
			} else if (process.equals("inbound")) {
				settingGblList.add(invoiceDao
						.getInvoiceSettingGblListContentIb(gblSeq));
			}
		}

		invoice.setTsp(settingGblList.get(0).getTsp());
		invoice.setStartDate(settingGblList.get(0).getPud());
		invoice.setEndDate(settingGblList.get(settingGblList.size() - 1)
				.getPud());

		/*if (invoiceDao.checkTodayInvoiceNo() != null) {
			String invoiceNo = invoiceDao.checkTodayInvoiceNo();
			String invoiceNoCount = invoiceNo.substring(7, invoiceNo.length());

			invoice.setInvoiceNo(DateUtil.getToday("YYYYMMDD")
					+ Integer.toString(Integer.parseInt(invoiceNoCount) + 1));
		} else {
			invoice.setInvoiceNo(DateUtil.getToday("YYYYMMDD") + "1");
		}*/

		if (settingGblList.size() > 0) {
			invoice.setProcess(process);
			invoiceDao.insertInvoice(invoice);
			for (InvoiceGbl invoiceGblTemp : settingGblList) {
				invoiceGblTemp.setInvoiceListSeq(invoice.getSeq());
				invoiceDao.insertInvoiceGbl(invoiceGblTemp);
				Map<String, Integer> map = new HashMap<String, Integer>();
				map.put("invoice", 1);
				map.put("seq", invoiceGblTemp.getGblSeq());
				System.out.println("[[[[[SELECTED GBL SEQ : "+invoiceGblTemp.getGblSeq());
				System.out.println("[[[[[SELECTED GBL NO : "+invoiceGblTemp.getGblNo());
				if(process.equals("outbound")){
					outboundDao.updateGblStatus(map);
				} else if (process.equals("inbound")){
					inboundDao.updateGblStatus(map);
				}
			}
		}

		Invoice returnInvoice = invoiceDao.getInvoice(invoice);

		return returnInvoice;
	}

	public List<InvoiceGbl> getInvoiceGblList(Integer seq, String process) {
		if(process.equals("outbound"))
			return invoiceDao.getInvoiceGblList(seq);
		else 
			return invoiceDao.getInvoiceGblListIb(seq);
	}

	public void invoiceDelete(Invoice invoice) {
		List<InvoiceGbl> invoiceGblList = invoiceDao
				.getInvoiceGblListByInvoice(invoice);

		if (invoiceGblList.size() > 0) {
			for (InvoiceGbl invoiceGblTemp : invoiceGblList) {

				Map<String, Integer> map = new HashMap<String, Integer>();
				map.put("invoice", 0);
				map.put("seq", invoiceGblTemp.getGblSeq());

				outboundDao.updateGblStatus(map);
			}
		}
		
		invoiceDao.deleteInvoiceCollection(invoice);

		invoiceDao.deleteInvoice(invoice);
	}
	
	public void invoiceDelete(Invoice invoice, String process) {
		List<InvoiceGbl> invoiceGblList = invoiceDao
				.getInvoiceGblListByInvoice(invoice);

		if (invoiceGblList.size() > 0) {
			for (InvoiceGbl invoiceGblTemp : invoiceGblList) {

				Map<String, Integer> map = new HashMap<String, Integer>();
				map.put("invoice", 0);
				map.put("seq", invoiceGblTemp.getGblSeq());

				if(process.equals("outbound")){
					outboundDao.updateGblStatus(map);
				} else if(process.equals("inbound")) {
					inboundDao.updateGblStatus(map);
				}
			}
		}

		invoice.setProcess(process);
		
		invoiceDao.deleteInvoice(invoice);
	}
	public String getYear(String pud){
		String result="";
		int year = Integer.parseInt(pud.substring(0,4));
		int month = Integer.parseInt(pud.substring(4,6));
		int day = Integer.parseInt(pud.substring(6,8));
		System.out.println("[[[[[ CHECK YEAR ]]]]]]]]]");
		System.out.println("[[[[[ YEAR : "+year+" MONTH : "+month+" DAY : "+day+" ]]]]]]");
		String tempMonth="";
		String tempDay="";
		if(month>5){
			
		}
		else if(month==5){
			if(day>14){
				
			}
			else{
				year--;
			}
		}
		else if(month < 5){
			year--;
		}
		if(month<10){
			tempMonth="0"+month;
		}
		else{
			tempMonth=month+"";
		}
		if(day<10){
			tempDay="0"+day;
		}
		else{
			tempDay=day+"";
		}
		result = year+""+tempMonth+""+tempDay+"";
		return result;
	}
	//계산하는부분
	public List<InvoiceGblContent> getInvoiceGblContentList(Integer invoiceSeq,
			Integer invoiceGblSeq, Integer gblSeq, String process) {

		List<InvoiceGblContent> invoiceGblContentList = new ArrayList<InvoiceGblContent>();

		if (process.equals("outbound")){//====&&outbound&&==================================================================================================BEGIN OUTBOUND
			double totalAmount = 0.0;

			Integer checkInvoiceGblContentCount = invoiceDao
					.getInvoiceGblContentCount(invoiceGblSeq);

			GBL gbl = outboundDao.getGbl(gblSeq);

			// 1. PACKING CHARGE
			InvoiceGblContent packingChargeContent = new InvoiceGblContent();
			Rate rate = new Rate();
			double totalGblWeight = 0.0;
			double gblWeight = 0.0;
			double typeIIWeight = 0.0;
			double overFlowWeight = 0.0;
			double packingCharge = 0.0;
			double typeIICharge = 0.0;
			double overFlowCharge = 0.0;

			String codeStr = null;
			if (gbl.getCode().equals("3") || gbl.getCode().equals("4")
					|| gbl.getCode().equals("T")||gbl.getCode().equals("5")) {
				codeStr = "HHG";
			} else if (gbl.getCode().equals("J") || gbl.getCode().equals("8")
					|| gbl.getCode().equals("7")) {
				codeStr = "UB";
			}
			System.out.println("[[[[[[ BEGIN GBL SEQ : "+gbl.getSeq()+" INVOICE ]]]]]]");
			List<Weightcertificate> weightcertificateList = outboundDao
					.getWeightcertificateList(gbl.getSeq().toString());
			int type2Count=0;
			int overFlowCount=0;
			int countFlag=0;
			////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			for(Weightcertificate weightcertificate : weightcertificateList){
				
				if ("HHG".equals(codeStr)) {
					gblWeight = Double.parseDouble(weightcertificate.getNet());//NET을 저장하고 
					String type= weightcertificate.getType();
					if(type.equals("O/F")||type.equals("W/D")||type.equals("CTN")||type.equals("WODEN")||type.equals("S/P")||
							type.equals("o/f")||type.equals("w/d")||type.equals("ctn")||type.equals("woden")||type.equals("s/p")||
							type.equals("W/B")||type.equals("w/b")||type.equals("C/B")||type.equals("c/b")||type.equals("cotton")||type.equals("COTTON")){
						System.out.println("INPUT TYPE : ["+type+"]");
						type = "O/F";
						System.out.println("SET TYPE : ["+type+"]");
						rate.setObType(type);/////////////그때그때 레이트에 맞는 타입을 설정한다.
					}
					else if(type.equals("typeII")||type.equals("TYPEII")||type.equals("typeii")){
						System.out.println("INPUT TYPE : ["+type+"]");
						type = "typeII";
						System.out.println("INPUT TYPE : ["+type+"]");
						rate.setObType(type);/////////////그때그때 레이트에 맞는 타입을 설정한다.
					}
					else{
						if(Integer.parseInt(weightcertificate.getCuft())>179){
							System.out.println("INPUT TYPE : ["+type+"]");
							type = "O/F";
							System.out.println("INPUT TYPE : ["+type+"]");
							rate.setObType(type);/////////////그때그때 레이트에 맞는 타입을 설정한다.
						}
					}

					System.out.println(" =================[ HHG : "+gblWeight+" ] [ TYPE : "+weightcertificate.getType()+" ] =================");
				} else if ("UB".equals(codeStr)) {
					
					gblWeight = Double.parseDouble(weightcertificate.getGross());
					System.out.println(" =================[ UB : "+gblWeight+" ] [ TYPE : "+weightcertificate.getType()+" ] =================");
				}
				
				rate.setCode(gbl.getCode());
				rate.setTsp(gbl.getScac());
				rate.setProcess(process.toUpperCase());
				rate.setWriteYear(getYear(gbl.getPud()));
				System.out.println("CHECK CHECK CHECK ======================= : "+getYear(gbl.getPud()));
				Rate gblRate = invoiceDao.getBasicRate(rate); // rate 기간에 맞게 가져왔나 모르겠지만 어쨌던 그때그때 해당되는 비율을 계속 가져온다.
				if(codeStr.equals("HHG")){//USING NET
					
					if(rate.getObType().equals("typeII")){
						System.out.println("[[[[[[[[[ TYPE II CALCURATE ]]]]]]]");
						type2Count++;
						typeIIWeight += gblWeight;
						typeIICharge += (gblWeight/100) * gblRate.getRate();//100으로 나누고 해당 비율 곱하기
						typeIICharge = getRoundResult(typeIICharge);//2자리 반올림
					} else if (rate.getObType().equals("O/F") || rate.getObType().equals("W/D") ||rate.getObType().equals("W/B") ||
							rate.getObType().equals("ctn")|| rate.getObType().equals("WODEN")|| rate.getObType().equals("CTN")||
							rate.getObType().equals("S/P")|| rate.getObType().equals("woden")|| rate.getObType().equals("s/p")||
							rate.getObType().equals("w/d")||rate.getObType().equals("w/b")||rate.getObType().equals("C/B") ||rate.getObType().equals("c/b")||
							rate.getObType().equals("COTTON") ||rate.getObType().equals("cotton")
							){
						System.out.println("[[[[[[[[[ O/F CALCURATE ]]]]]]]");
						overFlowCount++;
						overFlowWeight += gblWeight;
						System.out.println("[[[[[[[[[ Weight : "+gblWeight+" O/F GBL RATE : "+gblRate.getRate()+" % ]]]]]]]");
						overFlowCharge += (gblWeight/100) * gblRate.getRate();//100으로 나누고 해당 비율 곱하기
						overFlowCharge = getRoundResult(overFlowCharge);//2자리 반올림 
					}
					else{
						System.out.println("[[[[[[[[[[ NONE TYPE CALCURATE ]]]]]]]]");
						System.out.println("[[[[[[[[[[ CUFT : "+weightcertificate.getCuft()+"]]]]]]");
						System.out.println("[[[[[[[[[[ NO SELECT STATUS]]]]]]]]]]]]");
						if(Integer.parseInt(weightcertificate.getCuft()) >= 180){
							System.out.println("[[[[[[[[[ O/F CALCURATE ]]]]]]]");
							System.out.println("[[[[[[[[[ Weight : "+gblWeight+" O/F GBL RATE : "+gblRate.getRate()+" % ]]]]]]]");
							overFlowCount++;
							overFlowWeight += gblWeight;
							overFlowCharge += (gblWeight/100) * gblRate.getRate();//100으로 나누고 해당 비율 곱하기
							overFlowCharge = getRoundResult(overFlowCharge);//2자리 반올림
						}
					}
					
					//PACKING CHARGE CALRURATE after weight roof
					packingCharge = overFlowCharge + typeIICharge;
					packingCharge = getRoundResult(packingCharge);
					
					System.out.println("==================[ TOTAL HHG PACKING CHARGE : "+packingCharge+" ]============");
				}//HHG 
				else if(codeStr.equals("UB")){
					System.out.println("[[[[[[[[[[[[[[[[[[UB CALCURATE]]]]]]]]]]]]]]]]]");
					System.out.println("[[[[[[[[[[[ WEIGHT : "+gblWeight+" ]]]]]]]]]]]]");
					System.out.println("[[[[[[[[[[[ RATE : "+gblRate.getRate()+" ]]]]]]]]]");
					packingCharge += (gblWeight/100) * gblRate.getRate();
					packingCharge = getRoundResult(packingCharge);
					System.out.println("==================[ TOTAL UB PACKING CHARGE : "+packingCharge+" ]============");
				}//UB
				totalGblWeight += gblWeight;
				countFlag ++;
				
				if(countFlag == weightcertificateList.size()){//ROOF 의 마지막이면 무조건 검사
					System.out.println("[[[[[[[[[[[[[[[[[최종 무게 확인 TYPE II : "+typeIIWeight+" LBS ]]]]]]]]]]]]]]]]]");
					System.out.println("[[[[[[[[[[[[[[[[[최종 무게 확인 OVER FL : "+overFlowWeight+" LBS ]]]]]]]]]]]]]]]]]");
					System.out.println("[[[[[[[[[[[[[[[[[최종 무게 확인 TOTAL W : "+totalGblWeight+" LBS ]]]]]]]]]]]]]]]]]");
					if(totalGblWeight<500.0){
						if(codeStr.equals("HHG")){
							if(totalGblWeight < 500.0){
							System.out.println("[[[[[[[[[[[[[ MINIMUM WEIGHT SITUATION (HHG) UNDER 500: "+totalGblWeight+ "]]]]]]]]]]]]]]]]]]]");
								if(typeIIWeight > overFlowWeight){
									typeIIWeight = 500 - overFlowWeight;
								}
								else if(typeIIWeight == overFlowWeight){
									overFlowWeight = 500 - typeIIWeight;
								}
								else if(typeIIWeight < overFlowWeight){//over weight big than typeII
									overFlowWeight = 500 - typeIIWeight;
								}
								if(overFlowWeight == 0.0){
									typeIIWeight = 500.0;
								}
								if(typeIIWeight == 0.0){
									overFlowWeight = 500.0;
								}
								if(type2Count >0){
									rate.setCode(gbl.getCode());
									rate.setTsp(gbl.getScac());
									rate.setProcess(process.toUpperCase());
									rate.setWriteYear(getYear(getYear(gbl.getPud())));
									rate.setObType("typeII");
									Rate minimumRate = invoiceDao.getBasicRate(rate);
									packingCharge = (typeIIWeight/100) * rate.getRate();
								}
								if(overFlowCount>0){
									rate.setCode(gbl.getCode());
									rate.setTsp(gbl.getScac());
									rate.setProcess(process.toUpperCase());
									rate.setWriteYear(getYear(gbl.getPud()));
									rate.setObType("O/F");
									Rate minimumRate = invoiceDao.getBasicRate(rate);
									packingCharge = (overFlowWeight/100) * rate.getRate();
								}
							}
						}
						else if(codeStr.equals("UB")){
							if(totalGblWeight <300.0){
								System.out.println("[[[[[[[[[[[[[ MINIMUM WEIGHT SITUATION (UB) UNDER 300: "+totalGblWeight+ "]]]]]]]]]]]]]]]]]]]");
								packingCharge = (300/100)* gblRate.getRate();
							}
						}
					}//if(total weight <500.0)
					
				}
			}//////////weight ROOF
			getRoundResult(packingCharge);
			totalAmount += packingCharge;
			totalAmount = getRoundResult(totalAmount);
			System.out.println("==================[ TOTAL PACKING CHARGE : "+totalAmount+" ]============");
			
			Integer checkInvoiceContentGetSeq;
			if("HHG".equals(codeStr)){
				if(typeIIWeight >0){
					packingChargeContent.setChargingItem("PACKING CHARGE TYPEII");
					packingChargeContent.setQuantity(getIntValue(typeIIWeight)  + "LBS");
					System.out.println("==================[ O/F weight : "+typeIIWeight+" ]============");
					System.out.println("SET PACKIGN TYPE II 직전 : "+new DecimalFormat("######.00").format(typeIICharge));
					packingChargeContent.setAmount(new DecimalFormat("######.00").format(typeIICharge));
					packingChargeContent.setInvoiceGblSeq(invoiceGblSeq);
					invoiceGblContentList.add(packingChargeContent);
					checkInvoiceContentGetSeq = invoiceDao.checkInvoiceContent(packingChargeContent);
					
					if (checkInvoiceContentGetSeq != null) {
						packingChargeContent.setSeq(checkInvoiceContentGetSeq);
						invoiceDao.updateInvoiceGblContent(packingChargeContent);
					} else {
						invoiceDao.insertInvoiceGblContent(packingChargeContent);
					}
				}
				if(overFlowWeight>0){
					packingChargeContent = new InvoiceGblContent();
					packingChargeContent.setChargingItem("PACKING CHARGE O/F");
					packingChargeContent.setQuantity(getIntValue(overFlowWeight) + "LBS");
					System.out.println("==================[ O/F weight : "+overFlowWeight+" ]============");
					System.out.println("SET OVERFLOW 직전 : "+new DecimalFormat("######.00").format(overFlowCharge));
					packingChargeContent.setAmount(new DecimalFormat("######.00").format(overFlowCharge));
					packingChargeContent.setInvoiceGblSeq(invoiceGblSeq);
					invoiceGblContentList.add(packingChargeContent);
		
					Integer checkInvoiceContentGetSeq1 = invoiceDao
							.checkInvoiceContent(packingChargeContent);
		
					if (checkInvoiceContentGetSeq1 != null) {
						packingChargeContent.setSeq(checkInvoiceContentGetSeq1);
						invoiceDao.updateInvoiceGblContent(packingChargeContent);
					} else {
						invoiceDao.insertInvoiceGblContent(packingChargeContent);
					}
				}//HHG IF END
			} else {//code UB
				packingChargeContent.setChargingItem("PACKING CHARGE");
				packingChargeContent.setQuantity(totalGblWeight + "LBS");
				System.out.println("UB PACKING CHARGE 직전 : "+new DecimalFormat("######.00").format(packingCharge));
				packingChargeContent.setAmount(new DecimalFormat("######.00").format(packingCharge));
				packingChargeContent.setInvoiceGblSeq(invoiceGblSeq);
	
				invoiceGblContentList.add(packingChargeContent);
	
				checkInvoiceContentGetSeq = invoiceDao
						.checkInvoiceContent(packingChargeContent);
	
				if (checkInvoiceContentGetSeq != null) {
					packingChargeContent.setSeq(checkInvoiceContentGetSeq);
					invoiceDao.updateInvoiceGblContent(packingChargeContent);
				} else {
					invoiceDao.insertInvoiceGblContent(packingChargeContent);
				}				
			}//UB IF END

			// 2. CONTAINER
			InvoiceGblContent containerInvoiceGblContent = new InvoiceGblContent();

			int usedUnitTypeII = 0;
			int newUnitTypeII = 0;
			int repairedUnitTypeII = 0;
			int usedUnitOverflow = 0;
			int newUnitOverflow = 0;
			int repairedUnitOverflow = 0;
			double containerCharge = 0;

			for (Weightcertificate weightCertificate : weightcertificateList) {
					if(weightCertificate.getType().equals("typeII")){
						if ("NEW".equals(weightCertificate.getStatus())) {/////////////////////////////// 여기다가 컨테이너 상태 선택 하는거 추가 woden , 이런거 ㅋㅋ
							newUnitTypeII += 1;
						} else if ("USED".equals(weightCertificate.getStatus())) {
							usedUnitTypeII += 1;
						} else if ("REPAIRED".equals(weightCertificate.getStatus())) {
							repairedUnitTypeII += 1;
						}
					} else{
						if ("NEW".equals(weightCertificate.getStatus())) {
							newUnitOverflow += 1;
						} else if ("USED".equals(weightCertificate.getStatus())) {
							usedUnitOverflow += 1;
						} else if ("REPAIRED".equals(weightCertificate.getStatus())) {
							repairedUnitOverflow += 1;
						}						
					}
			}

			Rate containerRateParam = new Rate();
			System.out.println("[[[[[[[[[[[[[[ CONTAINER RATE SETTING ]]]]]]]]]]]]]]]]]]");
			containerRateParam.setWriteYear(getYear(gbl.getPud()));
			System.out.println("[[[[[[[[[[[[[[ CONTAINER TSP SETTING ]]]]]]]]]]]]]]]]]]");
			containerRateParam.setTsp(gbl.getScac());

			containerRateParam.setContainerStatus("new");
			Rate newRate = invoiceDao.getContainerRate(containerRateParam);

			containerRateParam.setContainerStatus("used");
			Rate usedRate = invoiceDao.getContainerRate(containerRateParam);

			containerRateParam.setContainerStatus("repair");
			Rate repairedRate = invoiceDao.getContainerRate(containerRateParam);

			double newCharge = newUnitTypeII * Double.parseDouble(newRate.getContainerRate());
			double usedCharge = usedUnitTypeII * Double.parseDouble(usedRate.getContainerRate());
			double repairedCharge = repairedUnitTypeII * Double.parseDouble(repairedRate.getContainerRate());
			
			System.out.println("[[[[[[[[[[[[[[[[[ CONTAINER STATUS CHARGE ]]]]]]]]]]]]]]]]]]]]]]");
			System.out.println("[[[[[[[[[[[[[[[[[ NEW CHARGE  : "+newCharge+" - COUNT :  "+newUnitTypeII +"pcs - RATE : "+newRate.getContainerRate()+" ]]]]]]]]]]]]]]]]]]]");
			System.out.println("[[[[[[[[[[[[[[[[[ USED CHARGE : "+usedCharge+"- COUNT :  "+usedUnitTypeII +"pcs - RATE : "+usedRate.getContainerRate()+" ]]]]]]]]]]]]]]]]]]]");
			System.out.println("[[[[[[[[[[[[[[[[[ REPAIRED CHARGE : "+repairedCharge+"- COUNT :  "+repairedUnitTypeII +"pcs - RATE : "+repairedRate.getContainerRate()+" ]]]]]]]]]]]]]]]]]]]");
			containerCharge += newCharge + usedCharge + repairedCharge;
			containerCharge = getRoundResult(containerCharge);
			totalAmount += containerCharge;
			if(newUnitTypeII>0){
				containerInvoiceGblContent.setChargingItem("CONTAINER TYPE II - NEW");
				containerInvoiceGblContent.setQuantity((newUnitTypeII) + "pcs");
				System.out.println("CONTAINER TYPE II 직전 : "+new DecimalFormat("######.00").format(getRoundResult(newCharge)));
				containerInvoiceGblContent.setAmount(new DecimalFormat("######.00").format(getRoundResult(newCharge)));
				containerInvoiceGblContent.setInvoiceGblSeq(invoiceGblSeq);
				if(newCharge > 0)
					invoiceGblContentList.add(containerInvoiceGblContent);
	
				checkInvoiceContentGetSeq = invoiceDao
						.checkInvoiceContent(containerInvoiceGblContent);
	
				if (checkInvoiceContentGetSeq != null) {
					containerInvoiceGblContent.setSeq(checkInvoiceContentGetSeq);
					invoiceDao.updateInvoiceGblContent(containerInvoiceGblContent);
				} else {
					invoiceDao.insertInvoiceGblContent(containerInvoiceGblContent);
				}
			}
			if(usedUnitTypeII>0){
				containerInvoiceGblContent = new InvoiceGblContent();
				containerInvoiceGblContent.setChargingItem("CONTAINER TYPE II - USED");
				containerInvoiceGblContent.setQuantity(usedUnitTypeII + "pcs");
				containerInvoiceGblContent.setAmount(new DecimalFormat("######.00").format(getRoundResult(usedCharge)));
				containerInvoiceGblContent.setInvoiceGblSeq(invoiceGblSeq);
				if(newCharge > 0)
					invoiceGblContentList.add(containerInvoiceGblContent);
	
				checkInvoiceContentGetSeq = invoiceDao
						.checkInvoiceContent(containerInvoiceGblContent);
	
				if (checkInvoiceContentGetSeq != null) {
					containerInvoiceGblContent.setSeq(checkInvoiceContentGetSeq);
					invoiceDao.updateInvoiceGblContent(containerInvoiceGblContent);
				} else {
					invoiceDao.insertInvoiceGblContent(containerInvoiceGblContent);
				}
			}
			if(repairedUnitTypeII>0){
				containerInvoiceGblContent = new InvoiceGblContent();
				containerInvoiceGblContent.setChargingItem("CONTAINER TYPE II - REPAIRED");
				containerInvoiceGblContent.setQuantity(repairedUnitTypeII + "pcs");
				containerInvoiceGblContent.setAmount(new DecimalFormat("######.00").format(getRoundResult(repairedCharge)));
				containerInvoiceGblContent.setInvoiceGblSeq(invoiceGblSeq);
				if(newCharge > 0)
					invoiceGblContentList.add(containerInvoiceGblContent);
	
				checkInvoiceContentGetSeq = invoiceDao
						.checkInvoiceContent(containerInvoiceGblContent);
	
				if (checkInvoiceContentGetSeq != null) {
					containerInvoiceGblContent.setSeq(checkInvoiceContentGetSeq);
					invoiceDao.updateInvoiceGblContent(containerInvoiceGblContent);
				} else {
					invoiceDao.insertInvoiceGblContent(containerInvoiceGblContent);
				}
			}
			/*
			containerInvoiceGblContent = new InvoiceGblContent();
			
			containerCharge = 0;

			newCharge = (int) (newUnitOverflow * Double.parseDouble(newRate
					.getContainerRate()));
			usedCharge = (int) (usedUnitOverflow * Double.parseDouble(usedRate
					.getContainerRate()));
			repairedCharge = (int) (repairedUnitOverflow * Double
					.parseDouble(repairedRate.getContainerRate()));

			containerCharge += newCharge + usedCharge + repairedCharge;

			totalAmount += containerCharge;

			containerInvoiceGblContent.setChargingItem("CONTAINER O/F");
			containerInvoiceGblContent.setQuantity(newUnitOverflow + usedUnitOverflow + repairedUnitOverflow + "pcs");
			containerInvoiceGblContent.setAmount(containerCharge + "");
			containerInvoiceGblContent.setInvoiceGblSeq(invoiceGblSeq);

			if(containerCharge > 0)
				invoiceGblContentList.add(containerInvoiceGblContent);

			checkInvoiceContentGetSeq = invoiceDao
					.checkInvoiceContent(containerInvoiceGblContent);

			if (checkInvoiceContentGetSeq != null) {
				containerInvoiceGblContent.setSeq(checkInvoiceContentGetSeq);
				invoiceDao.updateInvoiceGblContent(containerInvoiceGblContent);
			} else {
				invoiceDao.insertInvoiceGblContent(containerInvoiceGblContent);
			}
			 */
			// 3. EXTRA PICKUP CHARGE
			InvoiceGblContent extraPickUpGblContent = new InvoiceGblContent();
			double extraPickUpCharge = 0.0;

			Rate otherRateParam = new Rate();
			otherRateParam.setProcess(process);
			otherRateParam.setWriteYear(getYear(gbl.getPud()));
			System.out.println("[[[[[[ EXTRA CHARGE ADDAPTION YEAR : "+getYear(gbl.getPud())+" ]]]]]]");
			Rate comprate = invoiceDao.getEtc("comprate1", getYear(gbl.getPud()));
			
			String extraPickupCheck=null;
			extraPickupCheck = invoiceDao.getExtraPickupCheck(gbl.getSeq());
			
			if (extraPickupCheck == null) {
			} else {
				
				if ("HHG".equals(codeStr)) {
					otherRateParam.setTitle("EXTRA PICKUP CHARGE -IT13 item 1111");
					otherRateParam.setCode("HHG");
					Rate extraRate = invoiceDao.getOther(otherRateParam);
					System.out.println("[[[[[[[[[[[ EXTRA PICKUP RATE : "+extraRate.getRate()+" ]]]]]]");
					extraPickUpCharge = comprate.getRate()* extraRate.getRate();
				} else if ("UB".equals(codeStr)) {
					otherRateParam.setTitle("EXTRA PICKUP CHARGE - IT13 item 2222");
					otherRateParam.setCode("UB");
					Rate extraRate = invoiceDao.getOther(otherRateParam);
					System.out.println("[[[[[[[[[[[ EXTRA PICKUP RATE : "+extraRate.getRate()+" ]]]]]]");
					extraPickUpCharge = comprate.getRate() * extraRate.getRate();
				}
				extraPickUpCharge = getRoundResult(extraPickUpCharge);
				totalAmount += extraPickUpCharge;
				System.out.println("[[[[[[[[[[[ COMPRATE 1 RATE : "+comprate.getRate()+" ]]]]]]");
				System.out.println("[[[[[[[[[[[ EXTRA PICKUP CHARGE : "+extraPickUpCharge+" ]]]]]]");
				extraPickUpGblContent.setChargingItem("EXTRA PICKUP CHARGE");
				extraPickUpGblContent.setQuantity("");
				extraPickUpGblContent.setAmount(new DecimalFormat("######.00").format(extraPickUpCharge));
				extraPickUpGblContent.setInvoiceGblSeq(invoiceGblSeq);

				invoiceGblContentList.add(extraPickUpGblContent);

				checkInvoiceContentGetSeq = invoiceDao
						.checkInvoiceContent(extraPickUpGblContent);

				if (checkInvoiceContentGetSeq != null) {
					extraPickUpGblContent.setSeq(checkInvoiceContentGetSeq);
					invoiceDao.updateInvoiceGblContent(extraPickUpGblContent);
				} else {
					invoiceDao.insertInvoiceGblContent(extraPickUpGblContent);
				}

			}

			// 4. TERMINATION CHARGE
			InvoiceGblContent terminationContent = new InvoiceGblContent();
			double terminationCharge = 0.0;

			String checkTermination=null;
			checkTermination = invoiceDao.getTerminationReason(gbl.getSeq());
			System.out.println("[[[[[[[[[CHECK TERMINATION CHECK : "+checkTermination+"]]]]]]]]]");
			if (checkTermination == null) {
			} else {
				System.out.println("[[[[[[ CALCURATE TERMINATION ]]]]]]]");
				if ("HHG".equals(codeStr)) {
					otherRateParam
							.setTitle("TERMINATION CHARGE - IT13 item 523A");
					otherRateParam.setCode("HHG");
					Rate terminationRate = invoiceDao.getOther(otherRateParam);
					System.out.println("COMPRATE RAGE : "+comprate.getRate());
					System.out.println("TERMINATIONRATE RAGE : "+terminationRate.getRate());
					terminationCharge = comprate.getRate()
							* terminationRate.getRate();
				} else if ("UB".equals(codeStr)) {
					otherRateParam
							.setTitle("TERMINATION CHARGE - IT13 item 522A");
					otherRateParam.setCode("UB");
					Rate terminationRate = invoiceDao.getOther(otherRateParam);
					System.out.println("COMPRATE RAGE : "+comprate.getRate());
					System.out.println("TERMINATIONRATE RAGE : "+terminationRate.getRate());
					terminationCharge = comprate.getRate()
							* terminationRate.getRate();
				}
				terminationCharge = getRoundResult(terminationCharge);
				totalAmount += terminationCharge;

				terminationContent.setChargingItem("TERMINATION CHARGE");
				terminationContent.setQuantity("");
				terminationContent.setAmount(new DecimalFormat("######.00").format(terminationCharge));
				terminationContent.setInvoiceGblSeq(invoiceGblSeq);

				invoiceGblContentList.add(terminationContent);

				checkInvoiceContentGetSeq = invoiceDao
						.checkInvoiceContent(terminationContent);

				if (checkInvoiceContentGetSeq != null) {
					terminationContent.setSeq(checkInvoiceContentGetSeq);
					invoiceDao.updateInvoiceGblContent(terminationContent);
				} else {
					invoiceDao.insertInvoiceGblContent(terminationContent);
				}
			}

			// 5. DIVERSION CHARGE

			// 6. SIT_FIRST DAY(ORIGIN SIT)
			InvoiceGblContent sitFirstDayContent = new InvoiceGblContent();
			double sitFirstDayCharge = 0.0;

			Rate sitRateParam = new Rate();
			sitRateParam.setWriteYear(getYear(gbl.getPud()));
			sitRateParam.setProcess(process);

			
			String sitNo = invoiceDao.getSitNumber(gbl.getSeq());
			String sitStartDate = invoiceDao.getSitStartDay(gbl.getSeq());
			String sitEndDate = invoiceDao.getSitEndDay(gbl.getSeq());
						
			if (sitStartDate != null) {
				if ("HHG".equals(codeStr)) {
					sitRateParam.setTitle("SIT-FIRST DAY -IT13 item 518C");
					sitRateParam.setCode("HHG");
					Rate sitFirstDayRate = invoiceDao.getSit(sitRateParam);
					double tempWeight = (totalGblWeight/100);
					tempWeight = getRoundResult(tempWeight);
					sitFirstDayCharge =  tempWeight * comprate.getRate()
							* sitFirstDayRate.getRate();
					System.out.println("[[[[[[[ TOTAL WEIGHT : "+totalGblWeight+" ]]]]]]");
					System.out.println("[[[[[[[ HHG SIT RATE : "+sitFirstDayRate.getRate()+" ]]]]]]]]");
					System.out.println("[[[[[[[ COMPRATE : "+comprate.getRate()+" ]]]]]]]]");
				} else if ("UB".equals(codeStr)) {
					sitRateParam.setTitle("SIT-FIRST DAY - IT13 item 519A");
					sitRateParam.setCode("UB");
					Rate sitFirstDayRate = invoiceDao.getSit(sitRateParam);
					double tempWeight = (totalGblWeight/100);
					tempWeight = getRoundResult(tempWeight);
					sitFirstDayCharge = tempWeight * comprate.getRate()
							* sitFirstDayRate.getRate();
					System.out.println("[[[[[[[ TOTAL WEIGHT : "+totalGblWeight+" ]]]]]]");
					System.out.println("[[[[[[[ UB SIT RATE : "+sitFirstDayRate.getRate()+"]]]]]]]]");
					System.out.println("[[[[[[[ COMPRATE : "+comprate.getRate()+"]]]]]]]]");
				}
				sitFirstDayCharge = getRoundResult(sitFirstDayCharge);
				System.out.println("[[[[[[ SIT FIRST DAY CHARGE : "+sitFirstDayCharge+" ]]]]]]");
				
				totalAmount += sitFirstDayCharge;

				sitFirstDayContent.setChargingItem("SIT-FIRST DAY CHARGE");
				sitFirstDayContent.setQuantity("");
				sitFirstDayContent.setAmount(new DecimalFormat("######.00").format(sitFirstDayCharge));
				sitFirstDayContent.setInvoiceGblSeq(invoiceGblSeq);

				invoiceGblContentList.add(sitFirstDayContent);

				checkInvoiceContentGetSeq = invoiceDao
						.checkInvoiceContent(sitFirstDayContent);

				if (checkInvoiceContentGetSeq != null) {
					sitFirstDayContent.setSeq(checkInvoiceContentGetSeq);
					invoiceDao.updateInvoiceGblContent(sitFirstDayContent);
				} else {
					invoiceDao.insertInvoiceGblContent(sitFirstDayContent);
				}
			}

			// 7. SIT EACH ADDITIONAL DAY(ORIGIN SIT)
			InvoiceGblContent sitEachContent = new InvoiceGblContent();
			double sitEachDayCharge = 0.0;

			
			if (sitStartDate != null) {
				if ("HHG".equals(codeStr)) {
					sitRateParam
							.setTitle("SIT-EACH ADDITIONALDAY - IT13 item 518D");
					sitRateParam.setCode("HHG");
					Rate sitEachDayRate = invoiceDao.getSit(sitRateParam);
					Integer eachDayCount = DateUtil.getDaysBetween(sitStartDate, sitEndDate);
					System.out.println("[[[[ START DATE : "+sitStartDate+" ]]]]]");
					System.out.println("[[[[ END DATE : "+sitEndDate+" ]]]]]");
					System.out.println("[[[[ BETWEEN  : "+eachDayCount+" ]]]]]");
					System.out.println("[[[[ COMPRATE : "+comprate.getRate()+" ]]]]]");
					System.out.println("[[[[ SIT EACH DATE : "+sitEachDayRate.getRate()+" ]]]]]");
					double tempWeight = (totalGblWeight/100);
					tempWeight = getRoundResult(tempWeight);
					sitEachDayCharge = tempWeight * eachDayCount* comprate.getRate() * sitEachDayRate.getRate();
				} else if ("UB".equals(codeStr)) {
					sitRateParam
							.setTitle("SIT-EACH ADDITIONALDAY - IT13 item 519C");
					sitRateParam.setCode("UB");

					Integer eachDayCount = DateUtil.getDaysBetween(sitStartDate, sitEndDate);
					System.out.println("[[[[ START DATE : "+sitStartDate+" ]]]]]");
					System.out.println("[[[[ END DATE : "+sitEndDate+" ]]]]]");
					System.out.println("[[[[ BETWEEN  : "+eachDayCount+" ]]]]]");
					System.out.println("[[[[ COMPRATE : "+comprate.getRate()+" ]]]]]");
					Rate sitEachDayRate = invoiceDao.getSit(sitRateParam);
					System.out.println("[[[[ SIT EACH DATE : "+sitEachDayRate.getRate()+" ]]]]]");
					double tempWeight = (totalGblWeight/100);
					tempWeight = getRoundResult(tempWeight);
					sitEachDayCharge = tempWeight * eachDayCount
							* comprate.getRate() * sitEachDayRate.getRate();

				}
				sitEachDayCharge = getRoundResult(sitEachDayCharge);
				System.out.println("[[[[[[[ SIT EACH DAY CHARGE : "+sitEachDayCharge+" ]]]]]]]]]");
				totalAmount += sitEachDayCharge;
				
				sitEachContent.setChargingItem("SIT-EACH ADDITIONAL DAY");
				sitEachContent.setQuantity((DateUtil.getDaysBetween(sitStartDate, sitEndDate))+"days");
				sitEachContent.setAmount(new DecimalFormat("######.00").format(sitEachDayCharge));
				sitEachContent.setInvoiceGblSeq(invoiceGblSeq);

				invoiceGblContentList.add(sitEachContent);

				checkInvoiceContentGetSeq = invoiceDao
						.checkInvoiceContent(sitEachContent);

				if (checkInvoiceContentGetSeq != null) {
					sitEachContent.setSeq(checkInvoiceContentGetSeq);
					invoiceDao.updateInvoiceGblContent(sitEachContent);
				} else {
					invoiceDao.insertInvoiceGblContent(sitEachContent);
				}
			}
			// 8. LONG CARRY
			if ("HHG".equals(codeStr)) {
				InvoiceGblContent longCarryContent = new InvoiceGblContent();
				double longCarryCharge = 0.0;
				int inputGblSeq = gbl.getSeq();
				String lc ="";
				lc = invoiceDao.getLongcarryAmount(inputGblSeq);
				if(lc == null){
					System.out.println("[[[[[[[ LONG CARRAY NULL ]]]]]]]]");
				}
				else{
					System.out.println("[[[[[[[ LONG CARRAY AMOUNT : "+lc+"]]]]]]]]]]]]");
					longCarryCharge = Double.parseDouble(lc);
					longCarryCharge = getRoundResult(longCarryCharge);
					longCarryContent.setAmount(new DecimalFormat("######.00").format(longCarryCharge));
					longCarryContent.setChargingItem("LONG CARRAY");
					longCarryContent.setInvoiceGblSeq(invoiceGblSeq);
					
					invoiceGblContentList.add(longCarryContent);
					
					checkInvoiceContentGetSeq = invoiceDao
							.checkInvoiceContent(longCarryContent);
	
					if (checkInvoiceContentGetSeq != null) {
						longCarryContent.setSeq(checkInvoiceContentGetSeq);
						invoiceDao.updateInvoiceGblContent(longCarryContent);
					} else {
						invoiceDao.insertInvoiceGblContent(longCarryContent);
					}
					
					totalAmount += longCarryCharge;
					totalAmount = getRoundResult(totalAmount);
				}
			}

			// 9. ACCESSORIAL SERVICE CHARGE

			List<Addition> additionList = outboundDao.getAddtionList(gbl
					.getSeq().toString());
			for (Addition addition : additionList) {
				InvoiceGblContent additionContent = new InvoiceGblContent();
				if(addition.getCost()>0){
					additionContent.setChargingItem(addition.getTitle());
					additionContent.setQuantity("");
					additionContent.setAmount(addition.getCost().toString());
					additionContent.setInvoiceGblSeq(invoiceGblSeq);
					
					invoiceGblContentList.add(additionContent);
	
					checkInvoiceContentGetSeq = invoiceDao
							.checkInvoiceContent(additionContent);
	
					if (checkInvoiceContentGetSeq != null) {
						additionContent.setSeq(checkInvoiceContentGetSeq);
						invoiceDao.updateInvoiceGblContent(additionContent);
					} else {
						invoiceDao.insertInvoiceGblContent(additionContent);
					}
	
					totalAmount += addition.getCost();
					getRoundResult(totalAmount);
				}
			}
//			System.out.println("before total : "+totalAmount);
//			totalAmount *= 0.85123412;
			System.out.println("TOTAL FINAL : "+totalAmount);
			System.out.println("NEW DECIMAL ADAPTION : "+new DecimalFormat("######.00").format(totalAmount));
			// Total Amount
			InvoiceGblContent totalContent = new InvoiceGblContent();
			totalContent.setChargingItem("Total Amount");
			totalContent.setQuantity("");
			totalContent.setAmount(new DecimalFormat("######.00").format(totalAmount));

			invoiceGblContentList.add(totalContent);

			InvoiceGbl invoiceGbl = new InvoiceGbl();
			invoiceGbl.setSeq(invoiceGblSeq);
			String finalInvoiceAmount = new DecimalFormat("######.00").format(totalAmount);
			invoiceGbl.setAmount(finalInvoiceAmount);
			invoiceGbl.setComplete(true);
			
			invoiceDao.updateInvoiceGbl(invoiceGbl);

			invoiceDao.checkAndUpdateInvoice(invoiceSeq);
		} else {//====&&inbound&&========================================================================================		
			double totalAmount = 0.0;
			
			GBL gbl = inboundDao.getGbl(gblSeq);
			System.out.println("[[[[[[[[[[[[ GBL SEQ INVOICE START : "+gbl.getGblNo()+" ]]]]]]]]]]]");
			String codeStr = null;
			if (gbl.getCode().equals("3") || gbl.getCode().equals("4")
					|| gbl.getCode().equals("5") || gbl.getCode().equals("T")||gbl.getCode().equals("6")) {
				codeStr = "HHG";
				System.out.println("[[[[[[[[[[[ SELECTE TYPE : HHG ]]]]]]]]]]]]]]]");
			} else if (gbl.getCode().equals("J") || gbl.getCode().equals("8")|| gbl.getCode().equals("7")) {
				codeStr = "UB";
				System.out.println("[[[[[[[[[[[ SELECTE TYPE : UB ]]]]]]]]]]]]]]]");
			}
			
			List<WeightIb> weightList = inboundDao.getWeightList(gbl.getSeq());
			System.out.println("[[[[[[[[[[[ WEIGHT LIST SIZE :"+weightList.size()+"  ]]]]]]]]]]]]]]]");
			Rate comprate1 = invoiceDao.getEtc("comprate1", getYear(gbl.getPud()));
			System.out.println("[[[[[[[[[[[ CHECK YEAR : "+getYear(gbl.getPud())+" ]]]]]]]]");
			System.out.println("[[[[[[[[[[[ COMPRATE1 RATE : "+comprate1+" ]]]]]]]]]]]]]");
			
			// 1. Destination Service Charge
			InvoiceGblContent destinationServiceChargeContent = new InvoiceGblContent();
			System.out.println("[[[[[[[[[ SET RATE ]]]]]]]]]]]");
			Rate rate = new Rate();
			rate.setCode(gbl.getCode());
			System.out.println("[[[[[[[[[ SET CODE : "+gbl.getCode()+" ]]]]]]]]]]");
			rate.setTsp(gbl.getTsp());
			System.out.println("[[[[[[[[[ SET TSP : "+gbl.getTsp()+" ]]]]]]]]]]]");
			rate.setProcess(process.toUpperCase());
			System.out.println("[[[[[[[[[ SET PROCESS : "+process.toUpperCase()+" ]]]]]]]]]");
			rate.setWriteYear(getYear(gbl.getPud()));
			System.out.println("[[[[[[[[[[[ CHECK YEAR : "+getYear(gbl.getPud())+" ]]]]]]]]");
			Rate gblRate = invoiceDao.getBasicRate(rate);
			
			Map<String, Double> invoiceReturnMap = getDestinationServiceCharge(gblRate.getRate(), weightList, codeStr);
			totalAmount = getRoundResult(totalAmount);
			totalAmount += invoiceReturnMap.get("amount");
			
			destinationServiceChargeContent.setChargingItem("DESTINATION SERVICE CHARGE");
			double lbsDoubleTemp = Double.parseDouble(invoiceReturnMap.get("quantity").toString());
			int lbsTemp = (int)lbsDoubleTemp;
			destinationServiceChargeContent.setQuantity(lbsTemp+" LBS");
			destinationServiceChargeContent.setAmount(new DecimalFormat("######.00").format(invoiceReturnMap.get("amount")));
			destinationServiceChargeContent.setInvoiceGblSeq(invoiceGblSeq);
			
			invoiceGblContentList.add(destinationServiceChargeContent);
			
			Integer checkInvoiceContentGetSeq = invoiceDao
					.checkInvoiceContent(destinationServiceChargeContent);

			if (checkInvoiceContentGetSeq != null) {
				destinationServiceChargeContent.setSeq(checkInvoiceContentGetSeq);
				invoiceDao.updateInvoiceGblContent(destinationServiceChargeContent);
			} else {
				invoiceDao.insertInvoiceGblContent(destinationServiceChargeContent);
			}
			
			// 2.SIT-FIRST DAY AND WAREHOUSE HANDLING CHARGE
			InvoiceGblContent sitFirstContent = new InvoiceGblContent();		
			
			Memorandum sitFirstMemoParam = new Memorandum();
			sitFirstMemoParam.setType("06");
			sitFirstMemoParam.setGblSeq(gbl.getSeq());			
			Memorandum sitFirstMemo = memorandumDao.getMemorandumIb(sitFirstMemoParam);
			
			if(sitFirstMemo != null){
				
				invoiceReturnMap = getSitFirstDayAndWarehouseHandlingCharge(sitFirstMemo.getSitStartDate(), weightList, codeStr, comprate1.getRate());
				totalAmount += invoiceReturnMap.get("amount");
				totalAmount = getRoundResult(totalAmount);
				sitFirstContent.setChargingItem("SIT-FIRST DAY AND WAREHOUSE HANDLING CHARGE");
				sitFirstContent.setAmount(new DecimalFormat("######.00").format(invoiceReturnMap.get("amount")));
				sitFirstContent.setInvoiceGblSeq(invoiceGblSeq);
				
				invoiceGblContentList.add(sitFirstContent);
				
				checkInvoiceContentGetSeq = invoiceDao.checkInvoiceContent(sitFirstContent);
	
				if (checkInvoiceContentGetSeq != null) {
					sitFirstContent.setSeq(checkInvoiceContentGetSeq);
					invoiceDao.updateInvoiceGblContent(sitFirstContent);
				} else {
					invoiceDao.insertInvoiceGblContent(sitFirstContent);
				}				
			}
			
			// 3. SIT-EACH ADDTIONAL DAY
			InvoiceGblContent sitEachContent = new InvoiceGblContent();		
			
			Memorandum sitEachParam = new Memorandum();
			sitEachParam.setType("07");
			sitEachParam.setGblSeq(gbl.getSeq());			
			Memorandum sitEachMemo = memorandumDao.getMemorandumIb(sitEachParam);

			if(sitEachMemo != null){
				Integer eachDayCount = DateUtil.getDaysBetween(
						sitFirstMemo.getSitStartDate(),
						sitEachMemo.getSitEndDate()) - 1;
		
				invoiceReturnMap = getSitEachAdditionalDay(sitEachMemo.getSitEndDate(), eachDayCount, weightList, codeStr, comprate1.getRate());
				double tempAmount =invoiceReturnMap.get("amount");
				tempAmount = getRoundResult(tempAmount);
				totalAmount += tempAmount;
				
				sitEachContent.setChargingItem("SIT-EACH ADDITIONAL DAY");
				sitEachContent.setAmount(new DecimalFormat("######.00").format(tempAmount));
				sitEachContent.setInvoiceGblSeq(invoiceGblSeq);
				
				invoiceGblContentList.add(sitEachContent);
				
				checkInvoiceContentGetSeq = invoiceDao
						.checkInvoiceContent(sitEachContent);
	
				if (checkInvoiceContentGetSeq != null) {
					sitEachContent.setSeq(checkInvoiceContentGetSeq);
					invoiceDao.updateInvoiceGblContent(sitEachContent);
				} else {
					invoiceDao.insertInvoiceGblContent(sitEachContent);
				}	
			}
			
			//4. SIT-DELIVERY CHARGE & ADM FEE
			if ("UB".equals(codeStr) && sitFirstMemo !=null) { //코드가 UB이고 SIT가 있을경우만
				InvoiceGblContent sitDeliveryContent = new InvoiceGblContent();		
				
				boolean thirtyMile = basicService.getComareMile(gbl);
				
				invoiceReturnMap = getSitDeliveryChargeAddFee(thirtyMile, weightList, comprate1.getRate(), gbl);
				
				totalAmount += invoiceReturnMap.get("amount");
				
				sitDeliveryContent.setChargingItem("SIT-DELIVERY CHARGE & ADM FEE");
				sitDeliveryContent.setAmount(invoiceReturnMap.get("amount").toString());
				sitDeliveryContent.setInvoiceGblSeq(invoiceGblSeq);
				
				invoiceGblContentList.add(sitDeliveryContent);
				
				checkInvoiceContentGetSeq = invoiceDao
						.checkInvoiceContent(sitDeliveryContent);
	
				if (checkInvoiceContentGetSeq != null) {
					sitDeliveryContent.setSeq(checkInvoiceContentGetSeq);
					invoiceDao.updateInvoiceGblContent(sitDeliveryContent);
				} else {
					invoiceDao.insertInvoiceGblContent(sitDeliveryContent);
				}	
				
			}
			
			//5. REWEIGHT CHARGE
			if(weightList.get(0).getReweight() != null && !weightList.get(0).getReweight().equals("")){
				InvoiceGblContent reweightContent = new InvoiceGblContent();		
				
				invoiceReturnMap = reweightCharge(weightList, comprate1.getRate(), codeStr);
				
				if(invoiceReturnMap.get("reweight") == 1.0){
					totalAmount += invoiceReturnMap.get("amount");
					
					reweightContent.setChargingItem("REWEIGHT CHARGE");
					reweightContent.setQuantity(invoiceReturnMap.get("quantity").toString());
					reweightContent.setAmount(invoiceReturnMap.get("amount").toString());
					reweightContent.setInvoiceGblSeq(invoiceGblSeq);
					
					invoiceGblContentList.add(reweightContent);
					
					checkInvoiceContentGetSeq = invoiceDao
							.checkInvoiceContent(reweightContent);
		
					if (checkInvoiceContentGetSeq != null) {
						reweightContent.setSeq(checkInvoiceContentGetSeq);
						invoiceDao.updateInvoiceGblContent(reweightContent);
					} else {
						invoiceDao.insertInvoiceGblContent(reweightContent);
					}
				}
			}
			
			//6. Termination charge
			InvoiceGblContent terminationContent = new InvoiceGblContent();
			double terminationCharge = 0.0;

			Memorandum terminationMemorandumParam = new Memorandum();
			terminationMemorandumParam.setGblSeq(gbl.getSeq());
			terminationMemorandumParam.setType("05");
			Memorandum terminationMemorandum = memorandumDao.getMemorandumIb(terminationMemorandumParam);
			
			if (terminationMemorandum == null || terminationMemorandum.getTermination().equals("0")) {//터미네이션이 없으면
			} 
			else {//터미네이션이 있으면
				Rate otherRateParam = new Rate();
				if ("HHG".equals(codeStr)) {
					otherRateParam
							.setTitle("TERMINATION CHARGE - IT13 item 523A");
					otherRateParam.setCode("HHG");
					Rate terminationRate = invoiceDao.getOther(otherRateParam);
					terminationCharge = comprate1.getRate()
							* terminationRate.getRate();
				} else if ("UB".equals(codeStr)) {
					otherRateParam
							.setTitle("TERMINATION CHARGE - IT13 item 522A");
					otherRateParam.setCode("UB");
					Rate terminationRate = invoiceDao.getOther(otherRateParam);
					terminationCharge = comprate1.getRate()
							* terminationRate.getRate();
				}
				
				totalAmount += terminationCharge;

				terminationContent.setChargingItem("TERMINATION CHARGE");
				terminationContent.setQuantity(terminationMemorandum.getTermination());
				terminationContent.setAmount(new DecimalFormat("######.00").format(terminationCharge));
				terminationContent.setInvoiceGblSeq(invoiceGblSeq);

				invoiceGblContentList.add(terminationContent);

				checkInvoiceContentGetSeq = invoiceDao
						.checkInvoiceContent(terminationContent);

				if (checkInvoiceContentGetSeq != null) {
					terminationContent.setSeq(checkInvoiceContentGetSeq);
					invoiceDao.updateInvoiceGblContent(terminationContent);
				} else {
					invoiceDao.insertInvoiceGblContent(terminationContent);
				}
			}			

			//11. Accessorial Service Charge
			List<Addition> additionList = inboundDao.getAddtionList(gbl.getSeq().toString());
			for (Addition addition : additionList) {
				String itemTitle = addition.getTitle();
				double itemCost = addition.getCost();
				if(itemCost>0){
					System.out.println("[[[ CHECK ADDITIONAL SERVICE CHARGE ]]]");
					System.out.println("[ TITLE : "+itemTitle+" ]");
					System.out.println("[ COST : "+itemCost+" ]");
					InvoiceGblContent additionContent = new InvoiceGblContent();
					additionContent.setChargingItem(addition.getTitle());
					additionContent.setQuantity("");
					additionContent.setAmount(addition.getCost().toString());
					additionContent.setInvoiceGblSeq(invoiceGblSeq);
					
					invoiceGblContentList.add(additionContent);
	
					checkInvoiceContentGetSeq = invoiceDao
							.checkInvoiceContent(additionContent);
	
					if (checkInvoiceContentGetSeq != null) {
						additionContent.setSeq(checkInvoiceContentGetSeq);
						invoiceDao.updateInvoiceGblContent(additionContent);
					} else {
						invoiceDao.insertInvoiceGblContent(additionContent);
					}
	
					totalAmount += addition.getCost();
				}
			}
			System.out.println("before total : "+totalAmount);
//			totalAmount *= 0.85;
			System.out.println("TOTAL FINAL : "+totalAmount);
			System.out.println("NEW DECIMAL ADAPTION : "+new DecimalFormat("######.00").format(totalAmount));
			// Total Amount
			InvoiceGblContent totalContent = new InvoiceGblContent();
			totalContent.setChargingItem("Total Amount");
			totalContent.setQuantity("");
			totalContent.setAmount(totalAmount+"");
			
			invoiceGblContentList.add(totalContent);

			InvoiceGbl invoiceGbl = new InvoiceGbl();
			invoiceGbl.setSeq(invoiceGblSeq);
			invoiceGbl.setAmount(totalAmount+"");
			invoiceGbl.setComplete(true);

			invoiceDao.updateInvoiceGbl(invoiceGbl);

			invoiceDao.checkAndUpdateInvoice(invoiceSeq);
		}

		return invoiceGblContentList;
	}
	private int getIntValue(double d){
		String testWeight = d+"";
		int intWeight = (int)d;
		return intWeight;
	}
	private int getUBHHGType(String value_UB_HHG) {

		// UB인지 HHG인지 종류를 구분하는 정적 메소드.
		// 1.0 -- (2014-02-21) 강정규 : 첫 작성

		if (value_UB_HHG.equals("UB"))
			return 0;
		else if (value_UB_HHG.equals("HHG"))
			return 1;
		else
			return -1;

	}

	private double getGblGrossNetWeight(WeightIb weight, int type) {

		// GBL weight를 사용할 때 필요한 1-5, 1-6번 과정을 수행하는 메소드
		// 1.0 -- (2014-02-21) 강정규 : 첫 작성

		// ///////////////////////////////////////////////////////////////////

		double gbl_weight = 0.0;

		// 1-5번 과정
		if (type == 0){ // UB
			gbl_weight = Double.parseDouble(weight.getGross());
		}
		else if (type == 1){ // HHG
			gbl_weight = Double.parseDouble(weight.getNet());
		}
		
		return gbl_weight;
	}	
	
	private double[] getGBLWeight(double weight, int type, boolean checkLast) {

		// GBL weight를 사용할 때 필요한 1-5, 1-6번 과정을 수행하는 메소드
		// 1.0 -- (2014-02-21) 강정규 : 첫 작성
		// 1.1 -- (2014-04-09) 전명훈 : gbl_weight[2] 로 변경 getGBLweight 메소드 ( 최소값 계산과 100분율 및 곱하기 과정 추가 후 계산값 [0] 디스플레이 값[1] 로 리턴 )
		// ///////////////////////////////////////////////////////////////////

		double[] gbl_weight = new double[2];
		gbl_weight[0]= weight;
		gbl_weight[1] = weight;
		double minimum_weight[] = { 300.0, // UB
				500.0 }; // HHG		

		// 1-6번 과정
		if (gbl_weight[0] < minimum_weight[type]){//미니멈보다 작다면
			gbl_weight[0] = minimum_weight[type];
			gbl_weight[1] = minimum_weight[type];
			if(type==0){//UB 100으로 나누고 3을 곱한다. UB경우 여기다가 추가적으로 Rate를 곱한다.
				gbl_weight[0]=3;
			}
			else if(type==1){//HHG 100으로 나누고 5를 곱한다 HHG 경우 여기다가 추가적으로 Rate를 곱한다.
				gbl_weight[0]=5;
			}
		}
		else{//정상이라면 그냥 나누기 100을 한다.
			gbl_weight[0] /= 100;
		}
		return gbl_weight;//0에다가 진짜 계산할돈 1에다가 디스플레이할 돈
	}

	private Map<String, Double> getDestinationServiceCharge(double rate, List<WeightIb> weightList,
			String value_UB_HHG) {
		System.out.println("[[[[[[[[[[[ START DESTINATION SERVICE CHARGE ]]]]]]]]]]]]]]");
		// 1.0 -- (2014-02-21) 강정규 : 첫 작성
		// 1.1 -- (2014-02-23) 박광열 : Map으로 return type change, 단일 weight 계산을 weight List로 변경
		// 1.2 -- (2014-04-09) 전명훈 : gbl_weight[2] 로 변경 getGBLweight 메소드 ( 최소값 계산과 100분율 및 곱하기 과정 추가 후 계산값 [0] 디스플레이 값[1] 로 리턴 )
		// ///////////////////////////////////////////////////////////////////

		Map<String, Double> returnMap = new HashMap<String, Double>();
		
		// 계산에 필요한 변수들
		double weight_temp = 0.0;
		double[] gbl_weight = new double[2];
		double gbl_rate = 0.0;
		
		// UB냐 HHG냐 구분하는 변수. (0: UB, 1: HHG)
		int ub_hhg_type = getUBHHGType(value_UB_HHG);

		// ///////////////////////////////////////////////////////////////////

		// 1 & 2번.
		gbl_rate = rate;

		// 5 & 6번. 사용할 Weight 종류 확인
		for( WeightIb weight : weightList ){
			weight_temp += getGblGrossNetWeight(weight, ub_hhg_type);
			System.out.println("[[[[[[[[ WEIGHT : "+weight+" ]]]]]]]");
		}
		gbl_weight[0] = weight_temp;
		gbl_weight[1] = weight_temp;
		if(weightList.get(0).getReweight() != null && !weightList.get(0).getReweight().equals("")){
			String [] reweightList = weightList.get(0).getReweight().split("/");
			double tare = Double.parseDouble(reweightList[0]) - Double.parseDouble(reweightList[1]);
			if(ub_hhg_type == 0){//UB
				if(Double.parseDouble(reweightList[0]) < weight_temp){//gross 비교
					gbl_weight[1] = Double.parseDouble(reweightList[0]);
					System.out.println("[[[[[[[[[[[[[ REWEIGHT CHECK: "+gbl_weight[1]+" ]]]]]]]]]]]");
				}
			}
			else if(ub_hhg_type==1){//HHG
				if(tare < weight_temp){//tare 비교
					gbl_weight[1] = tare;
					System.out.println("[[[[[[[[[[[[[ REWEIGHT CHECK: "+gbl_weight[1]+" ]]]]]]]]]]]");
				}
			}
		}

		gbl_weight = getGBLWeight(gbl_weight[1], ub_hhg_type, true); //최저값 계산헀고 100을 나누기 까지했다. 최저일경우 타입에 맞는 곱하기를 해줬다.
		System.out.println("[[[[[[[[[[ DESTINATION CHARGE TOTAL ]]]]]]]]]]]]]]]");
		System.out.println("[[[[[[[[[[ WEIGHT : "+gbl_weight[1]+" ]]]]]]]]]]]]]");
		System.out.println("[[[[[[[[[[ ADDAPTIVE WEIGHT : "+gbl_weight[0]+" ]]]]]]]]]]]");
		System.out.println("[[[[[[[[[[ RATE : "+gbl_rate+" ]]]]]]]]]]]]");
		System.out.println("[[[[[[[[[[ AMOUNT : "+getRoundResult(gbl_weight[0] * gbl_rate)+" ]]]]]]]]]]");
		returnMap.put("quantity", gbl_weight[1]);
		returnMap.put("amount", getRoundResult(gbl_weight[0] * gbl_rate));
		// 최종 결과 값 반환
		return returnMap;
	}
	
	private Map<String, Double> getSitFirstDayAndWarehouseHandlingCharge(
			String sit_no, List<WeightIb> weightList, String value_UB_HHG,
			double comprate1) {
		///////////////start////////////////
		System.out.println("[[[ SIT FIRST DAY CHARGE START ]]]");
		// 1.0 -- (2014-02-21) 강정규 : 첫 작성
		// 1.1 -- (2014-02-23) 박광열 : Map으로 return type change, 단일 weight 계산을 weight List로 변경
		// 1.2 -- (2014-04-09) 전명훈 : gbl_weight[2] 로 변경 getGBLweight 메소드 ( 최소값 계산과 100분율 및 곱하기 과정 추가 후 계산값 [0] 디스플레이 값[1] 로 리턴 )
		// ///////////////////////////////////////////////////////////////////

		Map<String, Double> returnMap = new HashMap<String, Double>();

		// 계산에 필요한 변수들
		double weight_temp = 0.0;
		double[] gbl_weight = new double[2];
		double sit_first_day = 0.0;

		// UB냐 HHG냐 구분하는 변수. (0: UB, 1: HHG)
		int ub_hhg_type = getUBHHGType(value_UB_HHG);

		// ///////////////////////////////////////////////////////////////////

		// 1 & 2번 (sit_no 유무 확인)
		if (sit_no.isEmpty() == true){
			return returnMap;
		}

		// 5 & 6번. 사용할 Weight 종류 확인
		for( WeightIb weight : weightList ){
			System.out.println();
			double tempWeight =getGblGrossNetWeight(weight, ub_hhg_type);//GROSS 인지 NET인지 확인
			System.out.println("[[ WEIGHT : "+tempWeight+" ]]");
			weight_temp += tempWeight;
		}
		
		gbl_weight = getGBLWeight(weight_temp, ub_hhg_type, true);//무게 최저인지 확인하고  100으로 나누고 이런것
		
		if (ub_hhg_type == 0) {			// 4번 (UB)
			Rate rate = new Rate();
			rate.setTitle("SIT-FIRST DAY - IT13 item 519A");
			rate.setCode(value_UB_HHG);
			sit_first_day = invoiceDao.getSit(rate).getRate();
			System.out.println("[[[[[[[[[[[SIT EACH ADDITIONALDAY RATE : "+sit_first_day+"% ]]]]]]]");
		} else if (ub_hhg_type == 1) {	// 3번 (HHG)
			Rate rate = new Rate();
			rate.setTitle("SIT-FIRST DAY - IT13 item 518C");
			rate.setCode(value_UB_HHG);
			sit_first_day = invoiceDao.getSit(rate).getRate();
			System.out.println("[[[[[[[[[[[SIT EACH ADDITIONALDAY RATE : "+sit_first_day+"% ]]]]]]]");
		}
		double sitFristDayResult =getRoundResult(gbl_weight[0] * sit_first_day * comprate1);
		System.out.println("[[[[[[[[[[[ COMPRATE : "+comprate1+"% ]]]]]]]");
		System.out.println("[[[[[[[[[[[ WEIGHT : "+gbl_weight[0]+" ]]]]]]");
		System.out.println("[[[[[[[[[[[ RESULT : "+sitFristDayResult+" ]]]]]]");
		sitFristDayResult = getRoundResult(sitFristDayResult);
		returnMap.put("amount", sitFristDayResult);
		// 최종 결과 값 반환
		return returnMap;
	}
	
	private Map<String, Double> getSitEachAdditionalDay(String sit_no,
			int sit_day, List<WeightIb> weightList, String value_UB_HHG,
			double comprate1) {

		// 1.0 -- (2014-02-21) 강정규 : 첫 작성
		// 1.1 -- (2014-02-23) 박광열 : Map으로 return type change, 단일 weight 계산을 weight List로 변경
		// 1.2 -- (2014-04-09) 전명훈 : gbl_weight[2] 로 변경 getGBLweight 메소드 ( 최소값 계산과 100분율 및 곱하기 과정 추가 후 계산값 [0] 디스플레이 값[1] 로 리턴 )
		// ///////////////////////////////////////////////////////////////////

		Map<String, Double> returnMap = new HashMap<String, Double>();

		// 계산에 필요한 변수들
		double weight_temp = 0.0;
		double[] gbl_weight = new double[2];
		double addition_day = 0.0;

		// UB냐 HHG냐 구분하는 변수. (0: UB, 1: HHG)
		int ub_hhg_type = getUBHHGType(value_UB_HHG);

		// ///////////////////////////////////////////////////////////////////

		// 1 & 2번 (sit_no, additionSitDay 유무 확인)
		if (sit_no.isEmpty() == true && sit_day > 0){
			return returnMap;
		}

		// 5 & 6번. 사용할 Weight 종류 확인
		for( WeightIb weight : weightList ){
			weight_temp += getGblGrossNetWeight(weight, ub_hhg_type);
		}
		
		gbl_weight = getGBLWeight(weight_temp, ub_hhg_type, true);
		System.out.println("[[[[[[[[[[[ WEIGHT : "+gbl_weight+" ]]]]]]]]]]]]");
		if (ub_hhg_type == 0) {			// 4번 (UB)
			Rate rate = new Rate();
			rate.setTitle("SIT-EACH ADDITIONALDAY - IT13 item 518D");
			rate.setCode(value_UB_HHG);
			addition_day = invoiceDao.getSit(rate).getRate();
			
			System.out.println("[[[[[[[[[[[ SIT EACH ADDITIONALDAY RATE : "+addition_day+"% ]]]]]]]");
		} else if (ub_hhg_type == 1) {	// 3번 (HHG)
			Rate rate = new Rate();
			rate.setTitle("SIT-EACH ADDITIONALDAY - IT13 item 519C");
			rate.setCode(value_UB_HHG);
			addition_day = invoiceDao.getSit(rate).getRate();
			System.out.println("[[[[[[[[[[[SIT EACH ADDITIONALDAY RATE : "+addition_day+"% ]]]]]]]");
		}
		double origin = (gbl_weight[0] * addition_day * sit_day * comprate1);
		double after = getRoundResult(origin);
		System.out.println("[[ SIT EACH ADDTIONALDAY AMOUNT : "+origin+" ]] ");
		System.out.println("[[ ROWN ACC AMOUNT : "+after+" ]]");
		returnMap.put("amount", after);
		// 최종 결과 값 반환
		
		return returnMap;
	}
	
	public double getRoundResult(double input){
		double returnValue = Math.round(input*100d)/100d;
		return returnValue;
	}
	
	private Map<String, Double> getSitDeliveryChargeAddFee(boolean thirtyMile, List<WeightIb> weightList, double comprate1, GBL gbl) {
		
		Map<String, Double> returnMap = new HashMap<String, Double>();
		
		double weight_temp = 0.0;
		double[] gbl_weight = new double[2];
		double delivery = 0.0;
		
		int ub_hhg_type = getUBHHGType("UB");
		
		// 5 & 6번. 사용할 Weight 종류 확인
		for( WeightIb weight : weightList ){
			weight_temp += getGblGrossNetWeight(weight, ub_hhg_type);
		}
		
		gbl_weight = getGBLWeight(weight_temp, ub_hhg_type, true);
		
		//thirtyMile = true 이상 . false 이하
		if(thirtyMile){//이상
			Rate otherParam = new Rate();
			otherParam
					.setTitle("30mile 이하 - IT13 item 521I");
			otherParam.setCode("UB");
			Rate otherRate = invoiceDao.getOther(otherParam);
			double origin = otherRate.getRate() * gbl_weight[0];
			otherParam
			.setTitle("30mile 이상  - IT13 item 521J (minimum per shipment)");
			otherRate = invoiceDao.getOther(otherParam);
			double minimum = otherRate.getRate();
			otherParam.setTitle("ADM FEE - IT13 item 521L");
			Rate admFeeRate = invoiceDao.getOther(otherParam);
			double admFee = admFeeRate.getRate()*comprate1;
			if(origin>minimum){
				delivery = origin * comprate1 + admFee;
			}
			else{
				delivery = minimum * comprate1 + admFee;
			}
		} 
		else {//이하
			Rate otherParam = new Rate();
			otherParam
					.setTitle("30mile 이하 - IT13 item 521I");
			otherParam.setCode("UB");
			Rate otherRate = invoiceDao.getOther(otherParam);
			
			double origin = otherRate.getRate() * gbl_weight[0];
			

			otherParam
					.setTitle("30mile 이하  - IT13 item 521K (minimum per shipment)");
			otherRate = invoiceDao.getOther(otherParam);
			double minimum = otherRate.getRate();
			
			if(origin > minimum){
				delivery = origin * comprate1;
			} else {
				delivery = minimum * comprate1;
			}			
		}
		
		returnMap.put("amount", delivery);
		
		return returnMap;
	}
	
	private Map<String, Double> reweightCharge(List<WeightIb> weightList, double comprate1, String value_UB_HHG) {

		Map<String, Double> returnMap = new HashMap<String, Double>();

		double weight_temp = 0.0;
		double gbl_reweight = 0.0;
		double reweight_charge = 0.0;

		int ub_hhg_type = getUBHHGType(value_UB_HHG);
		
		for( WeightIb weight : weightList ){
			weight_temp += getGblGrossNetWeight(weight, ub_hhg_type);
		}
		
//		gbl_weight = getGBLWeight(weight_temp, ub_hhg_type, true);
		
		String [] reweightList = weightList.get(0).getReweight().split("/");
		gbl_reweight = Double.parseDouble(reweightList[0]);

		double subtraction = weight_temp - gbl_reweight;
		if(subtraction<0){
			subtraction*=-1;//일단 양수로 변환
		}
		if(ub_hhg_type == 1){//HHG
			Rate otherParam = new Rate();
			otherParam
					.setTitle("REWEIGHT CHARGE - IT13 item 505A");
			otherParam.setCode("HHG");
			Rate otherRate = invoiceDao.getOther(otherParam);
			if(weight_temp <= 5000.0){
				
				if(subtraction < 100 && subtraction >= 0){//해당된다면 적용한다.
					reweight_charge = otherRate.getRate()*comprate1;
					returnMap.put("reweight", 1.0);
				}
				else {					
					returnMap.put("reweight", 0.0);
				}
			} else {//5000 보다 클경우
				if(subtraction <= (weight_temp * 0.02)){//이내일경우
					reweight_charge = otherRate.getRate()*comprate1;
					returnMap.put("reweight", 1.0);
				} else {
					returnMap.put("reweight", 0.0);					
				}
			}
		} else if (ub_hhg_type == 0){
			Rate otherParam = new Rate();
			otherParam
					.setTitle("REWEIGHT CHARGE - IT13 item 505B");
			otherParam.setCode("UB");
			Rate otherRate = invoiceDao.getOther(otherParam);
			
			if(subtraction <= 25.0 && subtraction >= 0){
				reweight_charge = otherRate.getRate()*comprate1;
				returnMap.put("reweight", 1.0);
			} else {				
				returnMap.put("reweight", 0.0);
			}
		}	
				
		returnMap.put("quantity", subtraction);
		returnMap.put("amount", reweight_charge);
		return returnMap;	
	}
	
	/* Rate */

	public Map<String, Rate> getEtcMap(Rate rate) {
		Map<String, Rate> etcMap = new HashMap<String, Rate>();

		List<Rate> etcList = invoiceDao.getEtcList(rate);
		for (Rate rateTemp : etcList) {
			etcMap.put(rateTemp.getTitle(), rateTemp);
		}

		return etcMap;
	}

	public InvoiceGbl getInvoiceGblContentInfo(Integer invoiceGblSeq, String process) {
		if( "outbound".equals(process))
			return invoiceDao.getInvoiceGblcontentInfo(invoiceGblSeq);
		else if( "inbound".equals(process)){
			return invoiceDao.getInvoiceGblcontentInfoIb(invoiceGblSeq);
		}
		
		return new InvoiceGbl();
	}

	public int getInvoiceSettingGblListCount(InvoiceGblFilter invoiceGblFilter) {
		if (invoiceGblFilter.getProcess().equals("outbound")) {
			return invoiceDao.getInvoiceSettingGblListCount(invoiceGblFilter);
		} else if (invoiceGblFilter.getProcess().equals("inbound")) {
			return invoiceDao.getInvoiceSettingGblListIbCount(invoiceGblFilter);
		}

		return 0;
	}

	public List<GBL> getInvoiceSettingGblList(InvoiceGblFilter invoiceGblFilter) {
		if (invoiceGblFilter.getProcess().equals("outbound")) {
			System.out.println("[[[[[[ outbound invoice CALL ]]]]]]");
			return invoiceDao.getInvoiceSettingGblList(invoiceGblFilter);
		} else if (invoiceGblFilter.getProcess().equals("inbound")) {
			System.out.println("[[[[[[ inbound invoice CALL ]]]]]]");
			return invoiceDao.getInvoiceSettingGblListIb(invoiceGblFilter);
		}

		return new ArrayList<GBL>();
	}

	public Map<String, List<Code>> getInvoiceFilterMap(
			InvoiceFilter invoiceFilter) {
		Map<String, List<Code>> filterMap = new HashMap<String, List<Code>>();
		List<Code> carrierList = invoiceDao.getCarrierList(invoiceFilter);
		filterMap.put("tspList", carrierList);

		return filterMap;
	}

	public Map<String, List<Code>> getFilterMap(InvoiceGblFilter invoiceGblFilter) {
		Map<String, List<Code>> filterMap = new HashMap<String, List<Code>>();
		
		List<Code> branchList = codeDao.getAllAreaList();
		filterMap.put("branchList", branchList);

		List<Code> carrierList = new ArrayList<Code>();
		List<Code> codeList = new ArrayList<Code>();
		
		if(invoiceGblFilter.getProcess().equals("outbound")){
			carrierList = outboundDao.getCarrierList();
			codeList = outboundDao.getCodeList();
		} else {
			carrierList = inboundDao.getCarrierList();
			codeList = inboundDao.getCodeList();			
		}
		
		filterMap.put("carrierList", carrierList);
		filterMap.put("codeList", codeList);

		return filterMap;
	}

	public int getInvoiceCollectionListCount(InvoiceFilter invoiceFilter,
			String process) {
		invoiceFilter.setProcess(process);

		return invoiceDao.getInvoiceCollectionListCount(invoiceFilter);
	}

	public List<Invoice> getInvoiceCollectionList(InvoiceFilter invoiceFilter) {
		return invoiceDao.getInvoiceCollectionList(invoiceFilter);
	}

	public Map<Integer, InvoiceCollection> getInvoiceCollectionMap(
			InvoiceFilter invoiceFilter) {
		Map<Integer, InvoiceCollection> map = new HashMap<Integer, InvoiceCollection>();
		List<InvoiceCollection> list = invoiceDao
				.getInvoiceCollectionListAndFlow(invoiceFilter);

		for (InvoiceCollection invoiceCollection : list) {
			map.put(invoiceCollection.getInvoiceSeq(), invoiceCollection);
		}

		return map;

	}

	public void inputCollection(Map<String, String> invoiceCollectionMap) {
		InvoiceCollection invoiceCollection = new InvoiceCollection();
		invoiceCollection.setNet(invoiceCollectionMap.get("net"));
		invoiceCollection.setDifference(invoiceCollectionMap.get("difference"));
		invoiceCollection.setState(invoiceCollectionMap.get("state"));
		invoiceCollection.setInvoiceSeq(Integer.parseInt(invoiceCollectionMap
				.get("invoiceSeq")));

		InvoiceCollection collectionParam = invoiceDao
				.checkAndGetCollectionSeq(invoiceCollectionMap
						.get("invoiceSeq"));
		if (collectionParam != null
				&& !invoiceCollectionMap.get("flowState").equals("CLAIM")) {
			Integer net = Integer.parseInt(collectionParam.getNet())
					+ Integer.parseInt(invoiceCollection.getNet());
			Integer difference = net
					- Integer.parseInt(invoiceCollectionMap.get("amount"));
			invoiceCollection.setSeq(collectionParam.getSeq());
			if (difference == 0
					&& invoiceCollectionMap.get("flowState").equals("DEPOSIT")) {
				invoiceCollection.setState("COMPLETE");
				invoiceCollection.setNet(net.toString());
				invoiceCollection.setDifference(difference.toString());
			} else if (invoiceCollectionMap.get("flowState").equals("ACCEPT")) {
				invoiceCollection.setState("COMPLETE");
				invoiceCollection.setNet(collectionParam.getNet());
				invoiceCollection.setDifference(difference.toString());
			} else {
				invoiceCollection.setState("RESENT");
				invoiceCollection.setNet(net.toString());
				invoiceCollection.setDifference(difference.toString());
			}
		} else if (invoiceCollectionMap.get("flowState").equals("DEPOSIT")) {

		} else if (invoiceCollectionMap.get("flowState").equals("CLAIM")) {
		}

		InvoiceCollectionFlow invoiceCollectionFlow = new InvoiceCollectionFlow();
		invoiceCollectionFlow.setAmount(invoiceCollectionMap.get("flowAmount"));
		invoiceCollectionFlow.setState(invoiceCollectionMap.get("flowState"));
		invoiceCollectionFlow.setRemark(invoiceCollectionMap.get("flowRemark"));
		if (invoiceCollection.getSeq() != null) {
			invoiceCollectionFlow.setInvoiceCollectionSeq(invoiceCollection
					.getSeq());
		} else {
			invoiceCollectionFlow.setInvoiceCollectionSeq(collectionParam
					.getSeq());
		}

		invoiceDao.inputCollectionFlow(invoiceCollectionFlow);
	}

	public void invoiceCollectionDelete(Map<String, String> invoiceCollection) {
		String invoiceSeq = invoiceCollection.get("invoiceSeq");
		String flowSeq = invoiceCollection.get("flowSeq");
		String collectionSeq = invoiceCollection.get("collectionSeq");
		String state = invoiceCollection.get("state");
		String amount = invoiceCollection.get("amount");

		Integer count = Integer.parseInt(invoiceCollection.get("count"));

		if (state.equals("DEPOSIT")) {
			if (count == 1) {
				invoiceDao.deleteInvoiceCollection(collectionSeq);
			} else {
				invoiceDao.deleteInvoiceCollectionFlow(flowSeq);

				InvoiceCollection collection = invoiceDao
						.checkAndGetCollectionSeq(invoiceSeq);
				InvoiceCollection collectionParam = new InvoiceCollection();
				Integer net = Integer.parseInt(collection.getNet())
						- Integer.parseInt(amount);
				collectionParam.setNet(net.toString());

				Integer difference = Integer.parseInt(collection
						.getDifference()) - Integer.parseInt(amount);
				collectionParam.setDifference(difference.toString());
				collectionParam.setState("RESENT");

				collectionParam.setSeq(Integer.parseInt(collectionSeq));

				invoiceDao.updateCollectionNet(collectionParam);
			}
		} else if (state.equals("ACCEPT")) {
			invoiceDao.deleteInvoiceCollectionFlow(flowSeq);
			InvoiceCollection collection = invoiceDao
					.checkAndGetCollectionSeq(invoiceSeq);
			InvoiceCollection collectionParam = new InvoiceCollection();

			Integer difference = Integer.parseInt(collection.getDifference())
					- Integer.parseInt(amount);
			collectionParam.setDifference(difference.toString());
			collectionParam.setState("RESENT");

			collectionParam.setNet(collection.getNet());

			collectionParam.setSeq(Integer.parseInt(collectionSeq));

			invoiceDao.updateCollectionNet(collectionParam);

		} else if (state.equals("CLAIM")) {
			invoiceDao.deleteInvoiceCollectionFlow(flowSeq);
		}
	}

	public Map<Integer, InvoiceCollection> getInvoiceCollectionGblMap(
			Integer seq) {
		Map<Integer, InvoiceCollection> map = new HashMap<Integer, InvoiceCollection>();
		List<InvoiceCollection> list = invoiceDao
				.getInvoiceCollectionGblListAndFlow(seq);

		for (InvoiceCollection invoiceCollection : list) {
			map.put(invoiceCollection.getInvoiceSeq(), invoiceCollection);
		}

		return map;

	}

	public void inputGblCollection(Map<String, String> invoiceCollectionMap) {
		InvoiceCollection invoiceCollection = new InvoiceCollection();
		invoiceCollection.setNet(invoiceCollectionMap.get("net"));
		invoiceCollection.setDifference(invoiceCollectionMap.get("difference"));
		invoiceCollection.setState(invoiceCollectionMap.get("state"));
		invoiceCollection.setInvoiceSeq(Integer.parseInt(invoiceCollectionMap
				.get("invoiceSeq")));

		InvoiceCollection collectionParam = invoiceDao
				.checkAndGetGblCollectionSeq(invoiceCollectionMap
						.get("invoiceSeq"));
		
		int count = Integer.parseInt(invoiceCollectionMap.get("count"));
		
		if (collectionParam != null
				&& !invoiceCollectionMap.get("flowState").equals("CLAIM")) {
			double net = Double.parseDouble(collectionParam.getNet())
					+ Double.parseDouble(invoiceCollection.getNet());
			double difference = net - Double
					.parseDouble(invoiceCollectionMap.get("amount"));
			invoiceCollection.setSeq(collectionParam.getSeq());
			if (difference == 0
					&& invoiceCollectionMap.get("flowState").equals("DEPOSIT")) {
				invoiceCollection.setState("COMPLETE");
				invoiceCollection.setNet(net+"");
				invoiceCollection.setDifference(difference+"");
			} else if (invoiceCollectionMap.get("flowState").equals("ACCEPT")) {
				invoiceCollection.setState("COMPLETE");
				invoiceCollection.setNet(collectionParam.getNet());
				invoiceCollection.setDifference(difference+"");
			} else {
				invoiceCollection.setState("RESENT");
				invoiceCollection.setNet(net+"");
				invoiceCollection.setDifference(difference+"");
			}

			invoiceDao.updateGblCollectionNet(invoiceCollection);
		} else if ((invoiceCollectionMap.get("flowState").equals("DEPOSIT") && count == 0) || (invoiceCollectionMap.get("flowState").equals("ACCEPT") && count == 0 )) {
			invoiceDao.inputGblCollectionNet(invoiceCollection);
		} else if (invoiceCollectionMap.get("flowState").equals("CLAIM")) {
			if(count == 0){
				invoiceDao.inputGblCollectionNet(invoiceCollection);
			} else {
				
			}
		}

		InvoiceCollectionFlow invoiceCollectionFlow = new InvoiceCollectionFlow();
		invoiceCollectionFlow.setAmount(invoiceCollectionMap.get("flowAmount"));
		invoiceCollectionFlow.setState(invoiceCollectionMap.get("flowState"));
		invoiceCollectionFlow.setRemark(invoiceCollectionMap.get("flowRemark"));
		
		if (invoiceCollection.getSeq() != null) {
			invoiceCollectionFlow.setInvoiceCollectionSeq(invoiceCollection
					.getSeq());
		} else if ( collectionParam.getSeq() != null ){
			invoiceCollectionFlow.setInvoiceCollectionSeq(collectionParam
				.getSeq());			
		} else {
			invoiceCollectionFlow.setInvoiceCollectionSeq(Integer.parseInt(invoiceCollectionMap
					.get("gblSeq")));
		}

		invoiceDao.inputGblCollectionFlow(invoiceCollectionFlow);

		// 내부 GBL 입력 완료후 합이나, 상태를 invoice로 업데이트
		InvoiceCollection parentInvoiceCollection = new InvoiceCollection();
		String invoiceSeq = invoiceCollectionMap.get("invoiceNormalSeq");
		List<InvoiceCollection> invoiceCollectionGblList = invoiceDao
				.getInvoiceCollectionGblListAndFlow(Integer
						.parseInt(invoiceSeq));

		double invoiceCollectionNetSum = 0.0;
		double invoiceCollectionDifferencSum = 0.0;
		boolean checkResent = false;
		int completeCount = 0;

		for (InvoiceCollection invoiceCollectionGbl : invoiceCollectionGblList) {
			invoiceCollectionNetSum += Double.parseDouble(invoiceCollectionGbl
					.getNet());
			invoiceCollectionDifferencSum += Double
					.parseDouble(invoiceCollectionGbl.getDifference());
			if (invoiceCollectionGbl.getState().equals("RESENT")
					|| invoiceCollectionGbl.getState() == null
					|| invoiceCollectionGbl.getState().equals("")) {
				checkResent = true;
			}
			
			if(invoiceCollectionGbl.getState().equals("COMPLETE")){
				completeCount ++;
			}
		}
		
		Invoice invoiceParam = new Invoice();
		invoiceParam.setSeq(Integer.parseInt(invoiceSeq));
		List<InvoiceGbl> invoiceAllCollectionList = invoiceDao.getInvoiceGblListByInvoice(invoiceParam);
		
		parentInvoiceCollection.setNet(invoiceCollectionNetSum+"");		
		
		if((invoiceCollectionMap.get("flowState").equals("DEPOSIT") || invoiceCollectionMap.get("flowState").equals("ACCEPT")) && (completeCount != invoiceCollectionGblList.size())){	
			double totalAmount = 0.0;
			for(InvoiceGbl invoiceGbl : invoiceAllCollectionList){
				totalAmount += Double.parseDouble(invoiceGbl.getAmount());
			}
			
			if(totalAmount > Double.parseDouble(invoiceCollection.getNet())){				
				double reDifference = invoiceCollectionNetSum - totalAmount;
				parentInvoiceCollection.setDifference(reDifference+"");
			}
			
			checkResent = true;
		} else {
			parentInvoiceCollection.setDifference(invoiceCollectionDifferencSum
					+"");
		}

		if (checkResent) {
			parentInvoiceCollection.setState("RESENT");
		} else {
			parentInvoiceCollection.setState("COMPLETE");
		}

		parentInvoiceCollection.setInvoiceSeq(Integer.parseInt(invoiceSeq));

		Integer invoiceCollectionSeq = invoiceDao
				.getInvoiceCollectionSeq(Integer.parseInt(invoiceSeq));
		if (invoiceCollectionSeq != null && invoiceCollectionSeq > 0) {
			parentInvoiceCollection.setSeq(invoiceCollectionSeq);
			invoiceDao.updateCollectionNet(parentInvoiceCollection);
		} else {
			invoiceDao.inputCollectionNet(parentInvoiceCollection);
		}
	}

	public void invoiceGblCollectionDelete(Map<String, String> invoiceCollection) {
		String invoiceSeq = invoiceCollection.get("invoiceSeq");
		String flowSeq = invoiceCollection.get("flowSeq");
		String collectionSeq = invoiceCollection.get("collectionSeq");
		String state = invoiceCollection.get("state");
		String amount = invoiceCollection.get("amount");

		Integer count = Integer.parseInt(invoiceCollection.get("count"));

		if (state.equals("DEPOSIT")) {
			/*if (count == 1) {
				//invoiceDao.deleteGblInvoiceCollection(collectionSeq);
			} else {
				invoiceDao.deleteGblInvoiceCollectionFlow(flowSeq);

				InvoiceCollection collection = invoiceDao
						.checkAndGetGblCollectionSeq(invoiceSeq);
				InvoiceCollection collectionParam = new InvoiceCollection();
				double net = Double.parseDouble(collection.getNet())
						- Double.parseDouble(amount);
				collectionParam.setNet(net.toString());

				double difference = Double.parseDouble(collection
						.getDifference()) - Double.parseDouble(amount);
				collectionParam.setDifference(difference.toString());
				collectionParam.setState("RESENT");

				collectionParam.setSeq(Integer.parseInt(collectionSeq));

				invoiceDao.updateGblCollectionNet(collectionParam);
			}*/
			invoiceDao.deleteGblInvoiceCollectionFlow(flowSeq);

			InvoiceCollection collection = invoiceDao
					.checkAndGetGblCollectionSeq(invoiceSeq);
			InvoiceCollection collectionParam = new InvoiceCollection();
			double net = Double.parseDouble(collection.getNet())
					- Double.parseDouble(amount);
			collectionParam.setNet(net+"");

			double difference = Double.parseDouble(collection
					.getDifference()) - Double.parseDouble(amount);
			collectionParam.setDifference(difference+"");
			collectionParam.setState("RESENT");

			collectionParam.setSeq(Integer.parseInt(collectionSeq));

			invoiceDao.updateGblCollectionNet(collectionParam);
		} else if (state.equals("ACCEPT")) {			
			/*if (count == 1) {
				invoiceDao.deleteGblInvoiceCollection(collectionSeq);
			} else {
				invoiceDao.deleteGblInvoiceCollectionFlow(flowSeq);
				InvoiceCollection collection = invoiceDao
						.checkAndGetGblCollectionSeq(invoiceSeq);
				InvoiceCollection collectionParam = new InvoiceCollection();
	
				double difference = Double.parseDouble(collection.getDifference())
						- Double.parseDouble(amount);
				collectionParam.setDifference(difference.toString());
				collectionParam.setState("RESENT");
	
				collectionParam.setNet(collection.getNet());
	
				collectionParam.setSeq(Integer.parseInt(collectionSeq));
	
				invoiceDao.updateGblCollectionNet(collectionParam);
			}*/
			invoiceDao.deleteGblInvoiceCollectionFlow(flowSeq);
			InvoiceCollection collection = invoiceDao
					.checkAndGetGblCollectionSeq(invoiceSeq);
			InvoiceCollection collectionParam = new InvoiceCollection();

			double difference = Double.parseDouble(collection.getDifference())
					- Double.parseDouble(amount);
			collectionParam.setDifference(difference+"");
			collectionParam.setState("RESENT");

			collectionParam.setNet(collection.getNet());

			collectionParam.setSeq(Integer.parseInt(collectionSeq));

			invoiceDao.updateGblCollectionNet(collectionParam);

		} else if (state.equals("CLAIM")) {
			if (count == 1) {
				invoiceDao.deleteGblInvoiceCollection(collectionSeq);
			} else {
				invoiceDao.deleteGblInvoiceCollectionFlow(flowSeq);
			}
		}

		InvoiceCollection parentInvoiceCollection = new InvoiceCollection();
		String invoiceNormalSeq = invoiceCollection.get("invoiceNormalSeq");
		List<InvoiceCollection> invoiceCollectionGblList = invoiceDao
				.getInvoiceCollectionGblListAndFlow(Integer
						.parseInt(invoiceNormalSeq));

		double invoiceCollectionNetSum = 0.0;
		double invoiceCollectionDifferencSum = 0.0;
		int checkResent = 0; // 0 : complete , 1 : resent , 2 : ''

		for (InvoiceCollection invoiceCollectionGbl : invoiceCollectionGblList) {
			
			invoiceCollectionNetSum += Double.parseDouble(invoiceCollectionGbl
					.getNet());
			invoiceCollectionDifferencSum += Double
					.parseDouble(invoiceCollectionGbl.getDifference());
			if (invoiceCollectionGbl.getState().equals("RESENT")) {
				checkResent = 1;
			} else if ((invoiceCollectionGbl.getState() == null
					|| invoiceCollectionGbl.getState().equals("")) && checkResent == 0) {
				checkResent = 2;
			} else if(invoiceCollectionGbl.equals("COMPLETE") && checkResent == 0) {
				checkResent = 0;
			}
		}

		parentInvoiceCollection.setNet(invoiceCollectionNetSum+"");
		parentInvoiceCollection.setDifference(invoiceCollectionDifferencSum
				+"");

		if (checkResent == 1) {
			parentInvoiceCollection.setState("RESENT");
		} else if (checkResent == 2) {
			parentInvoiceCollection.setState("");
			parentInvoiceCollection.setNet("");
		} else {
			parentInvoiceCollection.setState("COMPLETE");
		}

		parentInvoiceCollection.setInvoiceSeq(Integer
				.parseInt(invoiceNormalSeq));

		Integer invoiceCollectionSeq = invoiceDao
				.getInvoiceCollectionSeq(Integer.parseInt(invoiceNormalSeq));

		parentInvoiceCollection.setSeq(invoiceCollectionSeq);
		invoiceDao.updateCollectionNet(parentInvoiceCollection);
	}

	public void invoiceCollectionRemarkInput(
			Map<String, String> invoiceCollection) {
		invoiceDao.invoiceCollectionRemarkInput(invoiceCollection);
	}

	public Invoice getInvoiceByInvoiceSeq(Integer seq) {
		Invoice invoice = new Invoice();
		invoice.setSeq(seq);
		return invoiceDao.getInvoice(invoice);
	}

	public List<String> getYearList() {
		return invoiceDao.getYearList();
	}

	public Map<Integer, List<InvoiceGblContent>> getInvoicePrintMap(
			List<InvoiceGbl> invoiceGblList, String process) {
		
		Map<Integer, List<InvoiceGblContent>> returnMap = new HashMap<Integer, List<InvoiceGblContent>>();
		
		for(InvoiceGbl invoiceGbl : invoiceGblList){
			returnMap.put(invoiceGbl.getSeq(), getInvoiceGblContentList(invoiceGbl.getInvoiceListSeq(), invoiceGbl.getSeq(), invoiceGbl.getGblSeq(), process));
		}
		
		return returnMap;
	}

	public List<Integer> getInvoiceSeqListByGblSeq(Integer seq) {
		return invoiceDao.getInvoiceSeqListByGblSeq(seq);
	}

	public void deleteInvoice(Integer invoiceSeq, String process) {
		Invoice invoice = new Invoice();
		
		invoice.setSeq(invoiceSeq);
		invoice.setProcess(process);
		
		invoiceDao.deleteInvoice(invoice);
	}
}
