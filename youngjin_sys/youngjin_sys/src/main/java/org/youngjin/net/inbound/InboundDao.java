package org.youngjin.net.inbound;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;
import org.youngjin.net.GBL;
import org.youngjin.net.GBLAttachment;
import org.youngjin.net.GBLStatus;

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
		// TODO Auto-generated method stub
		return null;
	}

	public List<GBLAttachment> getGblFileList(Integer seq) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<GBLStatus> getGblStatus(InboundFilter inboundFilter) {
		return getSqlSession().selectList("inboundMapper.getGblStatusList", inboundFilter);
	}
	
}
