package org.youngjin.net.invoice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

@Service
public class InvoiceService {
	
	@Resource
	private InvoiceDao invoiceDao;

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
			
			if( rateListSize > 1 && "outbound".equals(rateList.get(i + 1).getProcess())){
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

}
