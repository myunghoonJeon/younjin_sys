package org.youngjin.net.inbound;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;
import org.youngjin.net.GBL;

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
	
}
