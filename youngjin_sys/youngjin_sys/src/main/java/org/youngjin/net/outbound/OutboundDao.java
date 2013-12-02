package org.youngjin.net.outbound;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;
import org.youngjin.net.GBL;
import org.youngjin.net.GBLAttachment;
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

	public GBLStatus getGblProcess(Integer seq) {
		return getSqlSession().selectOne("outboundMapper.getGblProcess", seq);
	}

	public GBL getGbl(Integer seq) {
		return getSqlSession().selectOne("outboundMapper.getGbl", seq);
	}

	public void insertAttachment(GBLAttachment gblAttachment) {
		getSqlSession().insert("outboundMapper.insertAttachment", gblAttachment);		
	}

	public List<GBLAttachment> getGblFileList(Integer seq) {
		return getSqlSession().selectList("outboundMapper.getGblFileList", seq);
	}
	
}
