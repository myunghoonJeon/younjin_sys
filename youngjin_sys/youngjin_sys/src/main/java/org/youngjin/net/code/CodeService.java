package org.youngjin.net.code;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

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
}
