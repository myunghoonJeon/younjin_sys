package org.youngjin.net.invoice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.youngjin.net.GBL;
import org.youngjin.net.InvoiceController;
import org.youngjin.net.memorandum.Memorandum;
import org.youngjin.net.memorandum.MemorandumDao;
import org.youngjin.net.outbound.Addition;
import org.youngjin.net.outbound.OutboundDao;
import org.youngjin.net.outbound.Weightcertificate;
import org.youngjin.net.util.CalcUtil;
import org.youngjin.net.util.DateUtil;

@Service
public class InvoiceService {
	
	@Resource
	private InvoiceDao invoiceDao;
	
	@Resource
	private OutboundDao outboundDao;
	
	@Resource
	private MemorandumDao memorandumDao;

	public void checkNewYearRate() {
		//SIT charge Rate schedule check
		Integer SITcheck = invoiceDao.getSitListCount();
		if(SITcheck == 0){
			List<Rate> sitRateList = new ArrayList<Rate>();
			
			sitRateList.add(new Rate("SIT-FIRST DAY - IT13 item 519A", "UB", "OUTBOUND"));
			sitRateList.add(new Rate("SIT-EACH ADDITIONALDAY - IT13 item 519C", "UB", "OUTBOUND"));
			sitRateList.add(new Rate("SIT-DELIVERY CHARGE & ADM FEE", "UB", "OUTBOUND"));
			sitRateList.add(new Rate("30mile 이하 - IT13 item 521I", "UB", "OUTBOUND"));
			sitRateList.add(new Rate("30mile 이하  - IT13 item 521K (minimum per shipment)", "UB", "OUTBOUND"));
			sitRateList.add(new Rate("30mile 이상  - IT13 item 521J (minimum per shipment)", "UB", "OUTBOUND"));
			sitRateList.add(new Rate("ADM FEE - IT13 item 521L", "UB", "OUTBOUND"));
			sitRateList.add(new Rate("SIT-FIRST DAY -IT13 item 518C", "HHG", "OUTBOUND"));
			sitRateList.add(new Rate("SIT-EACH ADDITIONALDAY - IT13 item 518D", "HHG", "OUTBOUND"));
			
			sitRateList.add(new Rate("SIT-FIRST DAY - IT13 item 519A", "UB", "INBOUND"));
			sitRateList.add(new Rate("SIT-EACH ADDITIONALDAY - IT13 item 519C", "UB", "INBOUND"));
			sitRateList.add(new Rate("SIT-DELIVERY CHARGE & ADM FEE", "UB", "INBOUND"));
			sitRateList.add(new Rate("30mile 이하 - IT13 item 521I", "UB", "INBOUND"));
			sitRateList.add(new Rate("30mile 이하  - IT13 item 521K (minimum per shipment)", "UB", "INBOUND"));
			sitRateList.add(new Rate("30mile 이상  - IT13 item 521J (minimum per shipment)", "UB", "INBOUND"));
			sitRateList.add(new Rate("ADM FEE - IT13 item 521L", "UB", "INBOUND"));
			sitRateList.add(new Rate("SIT-FIRST DAY -IT13 item 518C", "HHG", "INBOUND"));
			sitRateList.add(new Rate("SIT-EACH ADDITIONALDAY - IT13 item 518D", "HHG", "INBOUND"));
			
			invoiceDao.insertNewYearSIT(sitRateList);
		}
		
		//Other charge Schedule check
		Integer otherCheck = invoiceDao.getOtherListCount();
		if(otherCheck == 0){
			List<Rate> otherRateList = new ArrayList<Rate>();
			
			otherRateList.add(new Rate("EXTRA PICKUP CHARGE - IT13 item 2222", "UB", "OUTBOUND"));
			otherRateList.add(new Rate("REWEIGHT CHARGE - IT13 item 505B", "UB", "OUTBOUND"));
			otherRateList.add(new Rate("TERMINATION CHARGE - IT13 item 523A", "UB", "OUTBOUND"));
			otherRateList.add(new Rate("termination discount", "UB", "OUTBOUND"));
			otherRateList.add(new Rate("DIVERSION CHARGE - IT13 item 526A", "UB", "OUTBOUND"));
			
			otherRateList.add(new Rate("EXTRA PICKUP CHARGE -IT13 item 1111", "HHG", "OUTBOUND"));
			otherRateList.add(new Rate("REWEIGHT CHARGE - IT13 item 505A", "HHG", "OUTBOUND"));
			otherRateList.add(new Rate("TERMINATION CHARGE - IT13 item 522A", "HHG", "OUTBOUND"));
			otherRateList.add(new Rate("termination discount", "HHG", "OUTBOUND"));
			otherRateList.add(new Rate("DIVERSION CHARGE - IT13 item 526A", "HHG", "OUTBOUND"));
			otherRateList.add(new Rate("LONG CARRY - IT13 item xxxxxx", "HHG", "OUTBOUND"));
			otherRateList.add(new Rate("DEBRIS REMOVAL - IT13 item", "HHG", "OUTBOUND"));
			
			otherRateList.add(new Rate("EXTRA PICKUP CHARGE - IT13 item 2222", "UB", "INBOUND"));
			otherRateList.add(new Rate("REWEIGHT CHARGE - IT13 item 505B", "UB", "INBOUND"));
			otherRateList.add(new Rate("TERMINATION CHARGE - IT13 item 523A", "UB", "INBOUND"));
			otherRateList.add(new Rate("termination discount", "UB", "INBOUND"));
			otherRateList.add(new Rate("DIVERSION CHARGE - IT13 item 526A", "UB", "INBOUND"));
			
			otherRateList.add(new Rate("EXTRA PICKUP CHARGE -IT13 item 1111", "HHG", "INBOUND"));
			otherRateList.add(new Rate("REWEIGHT CHARGE - IT13 item 505A", "HHG", "INBOUND"));
			otherRateList.add(new Rate("TERMINATION CHARGE - IT13 item 522A", "HHG", "INBOUND"));
			otherRateList.add(new Rate("termination discount", "HHG", "INBOUND"));
			otherRateList.add(new Rate("DIVERSION CHARGE - IT13 item 526A", "HHG", "INBOUND"));
			otherRateList.add(new Rate("LONG CARRY - IT13 item xxxxxx", "HHG", "INBOUND"));
			otherRateList.add(new Rate("DEBRIS REMOVAL - IT13 item", "HHG", "INBOUND"));
			
			invoiceDao.insertNewYearOther(otherRateList);
		}
	}

	public Map<String, Map<String, List<Rate>>> getSitMap(Rate rate) {
		Map<String, Map<String, List<Rate>>> sitMap = new HashMap<String, Map<String,List<Rate>>>();
		List<Rate> sitList = invoiceDao.getSitList(rate);

		Map<String, List<Rate>> sitInboundMap = new HashMap<String, List<Rate>>();
		Map<String, List<Rate>> sitOutboundMap = new HashMap<String, List<Rate>>();
		
		List<Rate> sitOBUBList = new ArrayList<Rate>();
		List<Rate> sitOBHHGList = new ArrayList<Rate>();
		
		List<Rate> sitIBUBList = new ArrayList<Rate>();
		List<Rate> sitIBHHGList = new ArrayList<Rate>();
		
		for( Rate paramRate : sitList){
			if("OUTBOUND".equals(paramRate.getProcess())){
				if("UB".equals(paramRate.getCode())){
					sitOBUBList.add(paramRate);
				} else if("HHG".equals(paramRate.getCode())){
					sitOBHHGList.add(paramRate);
				}
			} else if ("INBOUND".equals(paramRate.getProcess())){
				
				if("UB".equals(paramRate.getCode())){
					sitIBUBList.add(paramRate);
				} else if("HHG".equals(paramRate.getCode())){
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
		Map<String, Map<String, List<Rate>>> otherMap = new HashMap<String, Map<String,List<Rate>>>();
		List<Rate> otherList = invoiceDao.getOtherList(rate);	

		Map<String, List<Rate>> otherInboundMap = new HashMap<String, List<Rate>>();
		Map<String, List<Rate>> otherOutboundMap = new HashMap<String, List<Rate>>();
		
		List<Rate> otherOBUBList = new ArrayList<Rate>();
		List<Rate> otherOBHHGList = new ArrayList<Rate>();
		
		List<Rate> otherIBUBList = new ArrayList<Rate>();
		List<Rate> otherIBHHGList = new ArrayList<Rate>();
		
		for( Rate paramRate : otherList){
			if("OUTBOUND".equals(paramRate.getProcess())){
				if("UB".equals(paramRate.getCode())){
					otherOBUBList.add(paramRate);
				} else if("HHG".equals(paramRate.getCode())){
					otherOBHHGList.add(paramRate);
				}
			} else if ("INBOUND".equals(paramRate.getProcess())){
				
				if("UB".equals(paramRate.getCode())){
					otherIBUBList.add(paramRate);
				} else if("HHG".equals(paramRate.getCode())){
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
		
		if(checkRate == 0){
			invoiceDao.basicInsert(rate);
		} else {
			invoiceDao.basicUpdate(rate);
		}
	}

	public Map<String, Map<String, Map<String, Rate>>> getBasicMap(Rate rate) {
		Map<String, Map<String, Map<String, Rate>>> basicMap = new HashMap<String, Map<String,Map<String,Rate>>>();
		
		List<Rate> rateList = invoiceDao.getBasicRateList(rate);
		String beforeTsp;
		String currentTsp;
		
		int rateListSize = rateList.size();
		
		if(rateListSize == 0){
			beforeTsp = new String();
		} else {
			beforeTsp = rateList.get(0).getTsp();
		}
		
		//inbound List Map
		Map<String, Map<String, Rate>> inboundMap = new HashMap<String, Map<String,Rate>>();
		Map<String, Rate> inboundBasicMap = new HashMap<String, Rate>();
		for(int i = 0 ; i < rateListSize ; i ++ ){
			Rate rateTemp = rateList.get(i);
			
			currentTsp = rateTemp.getTsp();
			
			if(!beforeTsp.equals(currentTsp)){
				inboundMap.put(beforeTsp, inboundBasicMap);
				inboundBasicMap = new HashMap<String, Rate>();
				beforeTsp = currentTsp;
			}
			
			if( "inbound".equals(rateTemp.getProcess())){
				inboundBasicMap.put(rateTemp.getCode(), rateTemp);				
			}
			
			if( i != rateListSize - 1 && rateListSize > 1 && "outbound".equals(rateList.get(i + 1).getProcess())){
				inboundMap.put(beforeTsp, inboundBasicMap);			
				break;
			} else if ( i == rateListSize - 1 ){
				inboundMap.put(currentTsp, inboundBasicMap);
			}
		}
		
		//outbound List Map
		Map<String, Rate> outboundBasicMap = new HashMap<String, Rate>(); 
		Map<String, Map<String, Rate>> outboundMap = new HashMap<String, Map<String,Rate>>();
		for(int i = 0 ; i < rateListSize ; i ++ ){
			Rate rateTemp = rateList.get(i);
			
			if("inbound".equals(rateTemp.getProcess()))
				continue;
			
			currentTsp = rateTemp.getTsp();
			
			if(!beforeTsp.equals(currentTsp)){
				outboundMap.put(beforeTsp, outboundBasicMap);
				outboundBasicMap = new HashMap<String, Rate>();
				beforeTsp = currentTsp;
			}
			
			if( "outbound".equals(rateTemp.getProcess())){
				if(rateTemp.getObType() != null){
					outboundBasicMap.put(rateTemp.getCode() + rateTemp.getObType(), rateTemp);
					outboundMap.put(rateTemp.getTsp(), outboundBasicMap);
				} else {
					outboundBasicMap.put(rateTemp.getCode(), rateTemp);
					outboundMap.put(rateTemp.getTsp(), outboundBasicMap);
				}
			}
			
			if( i == rateListSize - 1){
				outboundMap.put(currentTsp, outboundBasicMap);				
			}
		}
		
		basicMap.put("inbound", inboundMap);
		basicMap.put("outbound", outboundMap);
		
		return basicMap;
	}

	public void etcInsert(Rate rate) {
		int checkRate = invoiceDao.getEtcCheck(rate);
		
		if(checkRate == 0){
			invoiceDao.etcInsert(rate);
		} else {
			invoiceDao.etcUpdate(rate);
		}
	}

	public void containerInsert(Rate rate) {
		int checkRate = invoiceDao.getContainerCheck(rate);
		
		if(checkRate == 0){
			invoiceDao.containerInsert(rate);
		} else {
			invoiceDao.containerUpdate(rate);
		}
	}

	public Map<String, Map<String, Rate>> getContainerMap(Rate rate) {
		Map<String, Map<String, Rate>> containerMap = new HashMap<String, Map<String,Rate>>();
		Map<String, Rate> statusMap = new HashMap<String, Rate>();
		
		String beforeTsp;
		String currentTsp;
		
		List<Rate> containerList = invoiceDao.getContainerList(rate);
		int containerListSize = containerList.size();
		
		if(containerListSize == 0){
			beforeTsp = new String();
		} else {
			beforeTsp = containerList.get(0).getTsp();
		}
		
		for( int i = 0 ; i < containerListSize; i ++ ){			
			Rate rateTemp = containerList.get(i);
			
			currentTsp = rateTemp.getTsp();
			if( !currentTsp.equals(beforeTsp)){
				containerMap.put(beforeTsp, statusMap);
				statusMap = new HashMap<String, Rate>();
				beforeTsp = currentTsp;
			}
			
			statusMap.put(rateTemp.getContainerStatus(), rateTemp);
			
			if( i == containerListSize - 1){
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
		List<InvoiceGbl> settingGblList = invoiceDao.getSettingGblList(invoice);	
		
		if(settingGblList.size() > 0){
			invoice.setProcess(process);			
			invoiceDao.insertInvoice(invoice);	
			for ( InvoiceGbl invoiceGblTemp : settingGblList){
				invoiceGblTemp.setInvoiceListSeq(invoice.getSeq());
				invoiceDao.insertInvoiceGbl(invoiceGblTemp);
				
				Map<String, Integer> map = new HashMap<String, Integer>();
				map.put("invoice", 1);
				map.put("seq", invoiceGblTemp.getGblSeq());
				
				outboundDao.updateGblStatus(map);
			}
		}
		
		Invoice returnInvoice = invoiceDao.getInvoice(invoice);
		
		return returnInvoice;
	}

	public List<InvoiceGbl> getInvoiceGblList(Integer seq) {
		return invoiceDao.getInvoiceGblList(seq);
	}

	public void invoiceDelete(Invoice invoice) {		
		List<InvoiceGbl> invoiceGblList = invoiceDao.getInvoiceGblListByInvoice(invoice);	
		
		if(invoiceGblList.size() > 0){
			for ( InvoiceGbl invoiceGblTemp : invoiceGblList){
				
				Map<String, Integer> map = new HashMap<String, Integer>();
				map.put("invoice", 0);
				map.put("seq", invoiceGblTemp.getGblSeq());
				
				outboundDao.updateGblStatus(map);
			}
		}
		
		invoiceDao.deleteInvoice(invoice);
	}

	public List<InvoiceGblContent> getInvoiceGblContentList(Integer invoiceSeq,
			Integer invoiceGblSeq, Integer gblSeq, String process) {
		
		List<InvoiceGblContent> invoiceGblContentList = new ArrayList<InvoiceGblContent>();
		
		if(process.equals("outbound")){
			Double totalAmount = 0.0;
			
			Integer checkInvoiceGblContentCount = invoiceDao.getInvoiceGblContentCount(invoiceGblSeq);
			
			GBL gbl = outboundDao.getGbl(gblSeq);
			
			//1. PACKING CHARGE
			InvoiceGblContent packingChargeContent = new InvoiceGblContent();
			Rate rate = new Rate();
			Double totalGblWeight = 0.0;
			Double gblWeight = 0.0;
			Double packingCharge = 0.0;
			
			String codeStr = null;
			if(gbl.getCode().equals("3") || gbl.getCode().equals("4") || gbl.getCode().equals("T")){
				codeStr = "HHG";
			} else if (gbl.getCode().equals("J") || gbl.getCode().equals("8") || gbl.getCode().equals("7")){
				codeStr = "UB";
			}
			
			List<Weightcertificate> weightcertificateList = outboundDao.getWeightcertificateList(gbl.getSeq().toString());
			
			for(Weightcertificate weightcertificate : weightcertificateList){
				if("HHG".equals(codeStr)){
					gblWeight = Double.parseDouble(weightcertificate.getNet());
					rate.setObType(weightcertificate.getType());
					if(gblWeight < 500){
						gblWeight = 500.0;
					}
				} else if ("UB".equals(codeStr)){
					gblWeight = Double.parseDouble(weightcertificate.getGross());
					if(gblWeight < 300){
						gblWeight = 300.0;
					}
				}
				
				rate.setCode(gbl.getCode());
				rate.setTsp(gbl.getScac());
				rate.setProcess(process);
				rate.setWriteYear(gbl.getPud());
				
				Rate gblRate = invoiceDao.getBasicRate(rate);
				
				packingCharge += gblWeight * gblRate.getRate();
				
				totalGblWeight += gblWeight;
			}
				
			totalAmount += packingCharge;
				
			packingChargeContent.setChargingItem("PACKING CHARGE");
			packingChargeContent.setQuantity("LBS weight");
			packingChargeContent.setAmount(packingCharge.toString());
			packingChargeContent.setInvoiceGblSeq(invoiceGblSeq);					
				
			invoiceGblContentList.add(packingChargeContent);
			
			Integer checkInvoiceContentGetSeq = invoiceDao.checkInvoiceContent(packingChargeContent);
			
			if(checkInvoiceContentGetSeq != null){
				packingChargeContent.setSeq(checkInvoiceContentGetSeq);
				invoiceDao.updateInvoiceGblContent(packingChargeContent);
			} else {
				invoiceDao.insertInvoiceGblContent(packingChargeContent);
			}
			
			//2. CONTAINER
			InvoiceGblContent containerInvoiceGblContent = new InvoiceGblContent();
			
			int usedUnit = 0;
			int newUnit = 0;
			int repairedUnit = 0;
			Integer containerCharge = 0;
			
			for( Weightcertificate weightCertificate : weightcertificateList){
				if("NEW".equals(weightCertificate.getStatus())){
					newUnit += 1;
				} else if("USED".equals(weightCertificate.getStatus())){
					usedUnit += 1;					
				} else if("REPAIRED".equals(weightCertificate.getStatus())){
					repairedUnit += 1;
				}
			}
			
			Rate containerRateParam = new Rate();
			containerRateParam.setWriteYear(gbl.getPud());
			containerRateParam.setTsp(gbl.getScac());
			
			containerRateParam.setContainerStatus("new");
			Rate newRate = invoiceDao.getContainerRate(containerRateParam);
			
			containerRateParam.setContainerStatus("used");
			Rate usedRate = invoiceDao.getContainerRate(containerRateParam);
			
			containerRateParam.setContainerStatus("repair");
			Rate repairedRate = invoiceDao.getContainerRate(containerRateParam);
			
			int newCharge = (int) (newUnit * Double.parseDouble(newRate.getContainerRate()));
			int usedCharge = (int) (usedUnit * Double.parseDouble(usedRate.getContainerRate()));
			int repairedCharge = (int) (repairedUnit * Double.parseDouble(repairedRate.getContainerRate()));
			
			containerCharge += newCharge + usedCharge + repairedCharge;
			
			totalAmount += containerCharge;
			
			containerInvoiceGblContent.setChargingItem("CONTAINER");
			containerInvoiceGblContent.setQuantity("Piece");
			containerInvoiceGblContent.setAmount(containerCharge.toString());
			containerInvoiceGblContent.setInvoiceGblSeq(invoiceGblSeq);
			
			invoiceGblContentList.add(containerInvoiceGblContent);
			
			checkInvoiceContentGetSeq = invoiceDao.checkInvoiceContent(containerInvoiceGblContent);
			
			if(checkInvoiceContentGetSeq != null){
				containerInvoiceGblContent.setSeq(checkInvoiceContentGetSeq);
				invoiceDao.updateInvoiceGblContent(containerInvoiceGblContent);
			} else {
				invoiceDao.insertInvoiceGblContent(containerInvoiceGblContent);
			}
			
			//3. EXTRA PICKUP CHARGE
			InvoiceGblContent extraPickUpGblContent = new InvoiceGblContent();
			Double extraPickUpCharge = 0.0;
			
			Rate otherRateParam = new Rate();
			otherRateParam.setProcess(process);
			otherRateParam.setWriteYear(gbl.getPud());
			
			Rate comprate = invoiceDao.getEtc("comprate1", gbl.getPud());
			
			Memorandum paramMemorandum = new Memorandum();
			paramMemorandum.setGblSeq(gbl.getSeq());
			paramMemorandum.setType("04");
			
			Memorandum memorandum = memorandumDao.getMemorandum(paramMemorandum);
			if(memorandum != null){
			} else {
				if("HHG".equals(codeStr)){
					otherRateParam.setTitle("EXTRA PICKUP CHARGE -IT13 item 1111");
					otherRateParam.setCode("HHG");
					Rate extraRate = invoiceDao.getOther(otherRateParam);
					extraPickUpCharge = comprate.getRate() * extraRate.getRate();
				} else if ("UB".equals(codeStr)){
					otherRateParam.setTitle("EXTRA PICKUP CHARGE - IT13 item 2222");
					otherRateParam.setCode("UB");
					Rate extraRate = invoiceDao.getOther(otherRateParam);
					extraPickUpCharge = comprate.getRate() * extraRate.getRate();					
				}
				
				totalAmount += extraPickUpCharge;
				
				extraPickUpGblContent.setChargingItem("EXTRA PICKUP CHARGE");
				extraPickUpGblContent.setQuantity("");
				extraPickUpGblContent.setAmount(extraPickUpCharge.toString());
				extraPickUpGblContent.setInvoiceGblSeq(invoiceGblSeq);
				
				invoiceGblContentList.add(extraPickUpGblContent);
				
				checkInvoiceContentGetSeq = invoiceDao.checkInvoiceContent(extraPickUpGblContent);
				
				if(checkInvoiceContentGetSeq != null){
					extraPickUpGblContent.setSeq(checkInvoiceContentGetSeq);
					invoiceDao.updateInvoiceGblContent(extraPickUpGblContent);
				} else {
					invoiceDao.insertInvoiceGblContent(extraPickUpGblContent);
				}					
				
			}
			
			//4. TERMINATION CHARGE			
			InvoiceGblContent terminationContent = new InvoiceGblContent();
			Double terminationCharge = 0.0;
			
			paramMemorandum = new Memorandum();
			paramMemorandum.setGblSeq(gbl.getSeq());
			paramMemorandum.setType("05");
			
			memorandum = memorandumDao.getMemorandum(paramMemorandum);		
			if(memorandum != null){
			} else {
				if("HHG".equals(codeStr)){
					otherRateParam.setTitle("TERMINATION CHARGE - IT13 item 522A");
					otherRateParam.setCode("HHG");
					Rate terminationRate = invoiceDao.getOther(otherRateParam);
					terminationCharge = comprate.getRate() * terminationRate.getRate();
				} else if ("UB".equals(codeStr)){
					otherRateParam.setTitle("TERMINATION CHARGE - IT13 item 523A");
					otherRateParam.setCode("UB");
					Rate terminationRate = invoiceDao.getOther(otherRateParam);
					terminationCharge = comprate.getRate() * terminationRate.getRate();					
				}
				
				totalAmount += terminationCharge;
				
				terminationContent.setChargingItem("TERMINATION CHARGE");
				terminationContent.setQuantity("");
				terminationContent.setAmount(terminationCharge.toString());
				terminationContent.setInvoiceGblSeq(invoiceGblSeq);
				
				invoiceGblContentList.add(terminationContent);
				
				checkInvoiceContentGetSeq = invoiceDao.checkInvoiceContent(terminationContent);
				
				if(checkInvoiceContentGetSeq != null){
					terminationContent.setSeq(checkInvoiceContentGetSeq);
					invoiceDao.updateInvoiceGblContent(terminationContent);
				} else {
					invoiceDao.insertInvoiceGblContent(terminationContent);
				}						
			}
			
			//5. DIVERSION CHARGE
			
			//6. SIT_FIRST DAY(ORIGIN SIT)
			InvoiceGblContent sitFirstDayContent = new InvoiceGblContent();
			Double sitFirstDayCharge = 0.0;
			
			Rate sitRateParam = new Rate();
			sitRateParam.setWriteYear(gbl.getPud());
			sitRateParam.setProcess(process);
			
			Memorandum sitFirstDayMemoParam = new Memorandum();
			sitFirstDayMemoParam.setType("06");
			sitFirstDayMemoParam.setGblSeq(gbl.getSeq());
			
			Memorandum checkInvoiceSitFirstDay = memorandumDao.getMemorandum(sitFirstDayMemoParam);
			
			if(checkInvoiceSitFirstDay != null){
				if("HHG".equals(codeStr)){
					sitRateParam.setTitle("SIT-FIRST DAY -IT13 item 518C");
					sitRateParam.setCode("HHG");
					Rate sitFirstDayRate = invoiceDao.getSit(sitRateParam);
					sitFirstDayCharge = totalGblWeight * comprate.getRate() * sitFirstDayRate.getRate();
				} else if ("UB".equals(codeStr)){
					sitRateParam.setTitle("SIT-FIRST DAY - IT13 item 519A");
					sitRateParam.setCode("UB");
					Rate sitFirstDayRate = invoiceDao.getSit(sitRateParam);
					sitFirstDayCharge = totalGblWeight * comprate.getRate() * sitFirstDayRate.getRate();					
				}
				
				totalAmount += sitFirstDayCharge;
				
				sitFirstDayContent.setChargingItem("SIT-FIRST DAY");
				sitFirstDayContent.setQuantity("day");
				sitFirstDayContent.setAmount(sitFirstDayCharge.toString());
				sitFirstDayContent.setInvoiceGblSeq(invoiceGblSeq);
				
				invoiceGblContentList.add(sitFirstDayContent);
				
				checkInvoiceContentGetSeq = invoiceDao.checkInvoiceContent(sitFirstDayContent);
				
				if(checkInvoiceContentGetSeq != null){
					sitFirstDayContent.setSeq(checkInvoiceContentGetSeq);
					invoiceDao.updateInvoiceGblContent(sitFirstDayContent);
				} else {
					invoiceDao.insertInvoiceGblContent(sitFirstDayContent);
				}						
			}
			
			//7. SIT EACH ADDITIONAL DAY(ORIGIN SIT)
			InvoiceGblContent sitEachContent = new InvoiceGblContent();
			Double sitEachDayCharge = 0.0;
			
			Memorandum sitEachDayMemoParam = new Memorandum();
			sitEachDayMemoParam.setType("07");
			sitEachDayMemoParam.setGblSeq(gbl.getSeq());
			
			Memorandum checkInvoiceEachDay = memorandumDao.getMemorandum(sitEachDayMemoParam);
			
			if(checkInvoiceEachDay != null){
				if("HHG".equals(codeStr)){
					sitRateParam.setTitle("SIT-EACH ADDITIONALDAY - IT13 item 518D");
					sitRateParam.setCode("HHG");
					Rate sitEachDayRate = invoiceDao.getSit(sitRateParam);
					
					Integer eachDayCount = DateUtil.getDaysBetween(checkInvoiceSitFirstDay.getSitStartDate(), checkInvoiceEachDay.getSitEndDate()) - 1;
					
					sitEachDayCharge = totalGblWeight * eachDayCount * comprate.getRate() * sitEachDayRate.getRate();
				} else if ("UB".equals(codeStr)){
					sitRateParam.setTitle("SIT-EACH ADDITIONALDAY - IT13 item 519C");
					sitRateParam.setCode("UB");
					
					Integer eachDayCount = DateUtil.getDaysBetween(checkInvoiceSitFirstDay.getSitStartDate(), checkInvoiceEachDay.getSitEndDate()) - 1;
					
					Rate sitEachDayRate = invoiceDao.getSit(sitRateParam);
					sitEachDayCharge = totalGblWeight * eachDayCount * comprate.getRate() * sitEachDayRate.getRate();	
					
				}
				
				totalAmount += sitEachDayCharge;
				
				sitEachContent.setChargingItem("SIT-EACH ADDITIONAL DAY");
				sitEachContent.setQuantity("days");
				sitEachContent.setAmount(sitEachDayCharge.toString());
				sitEachContent.setInvoiceGblSeq(invoiceGblSeq);
				
				invoiceGblContentList.add(sitEachContent);
				
				checkInvoiceContentGetSeq = invoiceDao.checkInvoiceContent(sitEachContent);
				
				if(checkInvoiceContentGetSeq != null){
					sitEachContent.setSeq(checkInvoiceContentGetSeq);
					invoiceDao.updateInvoiceGblContent(sitEachContent);
				} else {
					invoiceDao.insertInvoiceGblContent(sitEachContent);
				}					
			}			
			//8. LONG CARRY
			if("HHG".equals(codeStr)){
				InvoiceGblContent longCarryContent = new InvoiceGblContent();
				Double lognCarryCharge = 0.0;
				
				Memorandum longCarryMemoParam = new Memorandum();
				longCarryMemoParam.setType("08");
				longCarryMemoParam.setGblSeq(gbl.getSeq());
				
				
			}				
			
			//9. ACCESSORIAL SERVICE CHARGE
			
			List<Addition> additionList = outboundDao.getAddtionList(gbl.getSeq().toString());
			for ( Addition addition : additionList){
				InvoiceGblContent additionContent = new InvoiceGblContent();
				additionContent.setChargingItem(addition.getTitle());
				additionContent.setQuantity("");
				additionContent.setAmount(addition.getCost().toString());
				additionContent.setInvoiceGblSeq(invoiceGblSeq);
				
				invoiceGblContentList.add(additionContent);
				
				checkInvoiceContentGetSeq = invoiceDao.checkInvoiceContent(additionContent);
				
				if(checkInvoiceContentGetSeq != null){
					additionContent.setSeq(checkInvoiceContentGetSeq);
					invoiceDao.updateInvoiceGblContent(additionContent);
				} else {
					invoiceDao.insertInvoiceGblContent(additionContent);
				}						
				
				totalAmount += addition.getCost();
			}
			
			//Total Amount
			InvoiceGblContent totalContent = new InvoiceGblContent();
			totalContent.setChargingItem("Total Amount");
			totalContent.setQuantity("");
			totalContent.setAmount(totalAmount.toString());
			
			invoiceGblContentList.add(totalContent);		
			
			InvoiceGbl invoiceGbl = new InvoiceGbl();
			invoiceGbl.setSeq(invoiceGblSeq);
			invoiceGbl.setAmount(totalAmount.toString());
			invoiceGbl.setComplete(true);
			
			invoiceDao.updateInvoiceGbl(invoiceGbl);
			
			invoiceDao.checkAndUpdateInvoice(invoiceSeq);
		}
		
		return invoiceGblContentList;
	}

	public Map<String, Rate> getEtcMap(Rate rate) {
		Map<String, Rate> etcMap = new HashMap<String, Rate>();
		
		List<Rate> etcList = invoiceDao.getEtcList(rate);
		for(Rate rateTemp : etcList){
			etcMap.put(rateTemp.getTitle(), rateTemp);
		}
		
		return etcMap;
	}

}
