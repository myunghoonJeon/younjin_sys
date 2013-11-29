package org.youngjin.net.outbound;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

@Service
public class OutboundService {

	@Resource
	private OutboundDao outboundDao;
	
	public int getGblListCount(OutboundFilter outboundFilter) {
		return outboundDao.getGblListCount(outboundFilter);
	}

	public List<GBL> getGblList(OutboundFilter outboundFilter) {
		return outboundDao.getGblList(outboundFilter);
	}

}
