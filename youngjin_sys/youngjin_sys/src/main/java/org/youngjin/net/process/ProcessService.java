package org.youngjin.net.process;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

@Service
public class ProcessService {

	@Resource
	private ProcessDao processDao;
	
	public void gblockAdd(GBlock gblock) {
		processDao.gblockAdd(gblock);
	}

	public List<GBlock> getGBlockList() {
		return processDao.getGBlockList();
	}

	public void gblockUpdate(GBlock gblock) {
		processDao.gblockUpdate(gblock);
		
	}

}
