package org.youngjin.net.inbound;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;
import org.youngjin.net.GBL;

@Repository
public class InboundDao extends SqlSessionDaoSupport {

	public int getGblListCount(InboundFilter inboundFilter) {
		return getSqlSession().selectOne("inboundMapper.getGblListCount", inboundFilter);
	}

	public List<GBL> getGblList(InboundFilter inboundFilter) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
