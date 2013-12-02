package org.youngjin.net.outbound;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.youngjin.net.GBL;
import org.youngjin.net.GBLStatus;
import org.youngjin.net.code.Code;
import org.youngjin.net.code.CodeDao;
import org.youngjin.net.process.GBlock;

@Service
public class OutboundService {

	@Resource
	private OutboundDao outboundDao;
	
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

	public GBLStatus getGblProcessAndUpload(String seq) {
		return outboundDao.getGblProcess(seq); 
	}

}
