package org.youngjin.net.outbound;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;
import org.youngjin.net.GBL;
import org.youngjin.net.GBLStatus;
import org.youngjin.net.code.Code;
import org.youngjin.net.process.GBlock;

@Repository
public class OutboundDao extends SqlSessionDaoSupport {

	public int getGblListCount(OutboundFilter outboundFilter) {
		return getSqlSession().selectOne("outboundMapper.getGblListCount", outboundFilter);
	}

	public List<GBL> getGblList(OutboundFilter outboundFilter) {
		return getSqlSession().selectList("outboundMapper.getGblList", outboundFilter);
	}

	public GBlock findUsNo(GBlock gBlock) {
		return getSqlSession().selectOne("processMapper.getGBlock", gBlock);
	}

	public void insertGbl(GBL gbl) {
		getSqlSession().insert("outboundMapper.insertGbl", gbl);		
	}

	public List<Code> getCarrierList() {
		return getSqlSession().selectList("outboundMapper.getCarrierList");
	}

	public List<Code> getCodeList() {
		return getSqlSession().selectList("outboundMapper.getCodeList");
	}

	public void insertGblStatus(GBL gbl) {
		getSqlSession().insert("outboundMapper.insertGblStatus", gbl);
		
	}

	public GBLStatus getGblProcess(String seq) {
		return getSqlSession().selectOne("outboundMapper.getGblProcess", seq);
	}
	
}
