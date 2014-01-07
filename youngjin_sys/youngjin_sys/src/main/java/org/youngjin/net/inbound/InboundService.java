package org.youngjin.net.inbound;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.youngjin.net.GBL;
import org.youngjin.net.GBLAttachment;
import org.youngjin.net.GBLStatus;
import org.youngjin.net.code.Code;
import org.youngjin.net.code.CodeDao;
import org.youngjin.net.login.User;
import org.youngjin.net.process.GBlock;

@Service
public class InboundService {
	
	@Resource
	private InboundDao inboundDao;
	
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
				gblStatusMap.put(gblStatus.getNo(), gblStatus.getCurrentState());
		}
		
		return gblStatusMap;
	}

	public GBLStatus getGblProcessAndUpload(Integer seq) {
		return inboundDao.getGblProcess(seq); 
	}

	public List<GBLAttachment> getGblFileList(Integer seq) {
		return inboundDao.getGblFileList(seq);
	}
	
}
