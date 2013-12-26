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
			List<Rate> rateList = new ArrayList<Rate>();
			
			rateList.add(new Rate("SIT-FIRST DAY - IT13 item 519A", "UB", "OUTBOUND"));
			rateList.add(new Rate("SIT-EACH ADDITIONALDAY - IT13 item 519C", "UB", "OUTBOUND"));
			rateList.add(new Rate("SIT-DELIVERY CHARGE & ADM FEE", "UB", "OUTBOUND"));
			rateList.add(new Rate("30mile 이하 - IT13 item 521I", "UB", "OUTBOUND"));
			rateList.add(new Rate("30mile 이하  - IT13 item 521K (minimum per shipment)", "UB", "OUTBOUND"));
			rateList.add(new Rate("30mile 이상  - IT13 item 521J (minimum per shipment)", "UB", "OUTBOUND"));
			rateList.add(new Rate("ADM FEE - IT13 item 521L", "UB", "OUTBOUND"));
			rateList.add(new Rate("SIT-FIRST DAY -IT13 item 518C", "HHG", "OUTBOUND"));
			rateList.add(new Rate("SIT-EACH ADDITIONALDAY - IT13 item 518D", "HHG", "OUTBOUND"));
			
			rateList.add(new Rate("SIT-FIRST DAY - IT13 item 519A", "UB", "INBOUND"));
			rateList.add(new Rate("SIT-EACH ADDITIONALDAY - IT13 item 519C", "UB", "INBOUND"));
			rateList.add(new Rate("SIT-DELIVERY CHARGE & ADM FEE", "UB", "INBOUND"));
			rateList.add(new Rate("30mile 이하 - IT13 item 521I", "UB", "INBOUND"));
			rateList.add(new Rate("30mile 이하  - IT13 item 521K (minimum per shipment)", "UB", "INBOUND"));
			rateList.add(new Rate("30mile 이상  - IT13 item 521J (minimum per shipment)", "UB", "INBOUND"));
			rateList.add(new Rate("ADM FEE - IT13 item 521L", "UB", "INBOUND"));
			rateList.add(new Rate("SIT-FIRST DAY -IT13 item 518C", "HHG", "INBOUND"));
			rateList.add(new Rate("SIT-EACH ADDITIONALDAY - IT13 item 518D", "HHG", "INBOUND"));
			
			invoiceDao.insertNewYearSIT(rateList);
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

}
