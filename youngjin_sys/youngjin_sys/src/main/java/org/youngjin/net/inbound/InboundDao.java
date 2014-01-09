package org.youngjin.net.inbound;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;
import org.youngjin.net.Dd619;
import org.youngjin.net.GBL;
import org.youngjin.net.GBLAttachment;
import org.youngjin.net.GBLStatus;
import org.youngjin.net.code.Code;

@Repository
public class InboundDao extends SqlSessionDaoSupport {

	public int getGblListCount(InboundFilter inboundFilter) {
		return getSqlSession().selectOne("inboundMapper.getFreightListCount", inboundFilter);
	}

	public List<GBL> getFreightList(InboundFilter inboundFilter) {
		return getSqlSession().selectList("inboundMapper.getFreightList", inboundFilter);
	}

	public void insertFreight(GBL gbl) {
		getSqlSession().insert("inboundMapper.insertFreight", gbl);
	}

	public void insertFreightStatus(GBL gbl) {
		getSqlSession().insert("inboundMapper.insertFreightStatus", gbl);
	}

	public GBLStatus getGblProcess(Integer seq) {
		return getSqlSession().selectOne("inboundMapper.getGblProcess", seq);
	}

	public List<GBLAttachment> getGblFileList(Integer seq) {
		return getSqlSession().selectList("inboundMapper.getGblFileList", seq);
	}

	public List<GBLStatus> getGblStatus(InboundFilter inboundFilter) {
		return getSqlSession().selectList("inboundMapper.getGblStatusList", inboundFilter);
	}

	public List<Code> getCarrierList() {
		return getSqlSession().selectList("inboundMapper.getCarrierList");
	}

	public List<Code> getCodeList() {
		return getSqlSession().selectList("inboundMapper.getCodeList");
	}

	public void insertAttachment(GBLAttachment gblAttachment) {
		getSqlSession().insert("inboundMapper.insertAttachment", gblAttachment);		
	}

	public GBL getGbl(Integer seq) {
		return getSqlSession().selectOne("inboundMapper.getGbl", seq);
	}
	
	public void insertDd619(Dd619 dd619) {
		getSqlSession().insert("inboundMapper.insertDd619", dd619);
	}

	public void deleteDd619(Dd619 dd619) {
		getSqlSession().delete("inboundMapper.deleteDd619", dd619);
	}

	public List<Dd619> getDd619List(String seq) {
		return getSqlSession().selectList("inboundMapper.getDd619List", seq);
	}
}
