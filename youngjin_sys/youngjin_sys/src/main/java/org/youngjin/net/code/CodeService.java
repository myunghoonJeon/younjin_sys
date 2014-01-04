package org.youngjin.net.code;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.youngjin.net.outbound.OutboundDao;

@Service
public class CodeService {

	@Resource
	private CodeDao codeDao;

	public List<Code> getAllAuthList() {
		return codeDao.getAllAuthList();
	}

	public List<Code> getAllAreaList() {
		return codeDao.getAllAreaList();
	}
	
	public List<Code> getAllCarrierList(){
		return codeDao.getCarrierList();
	}

	public List<Code> getCodeList(String mainCode){
		return codeDao.getCodeList(mainCode);
	}

	public Code getCode(String mainCode, String subCode) {
		Code code = new Code();
		code.setMainCode(mainCode);
		code.setSubCode(subCode);
		
		return codeDao.getCode(code);
	}
}
