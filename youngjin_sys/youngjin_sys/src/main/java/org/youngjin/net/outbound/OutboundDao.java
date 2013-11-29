package org.youngjin.net.outbound;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

@Repository
public class OutboundDao extends SqlSessionDaoSupport {

	public int getGblListCount(OutboundFilter outboundFilter) {
		return getSqlSession().selectOne("outboundMapper.getGblListCount", outboundFilter);
	}

	public List<GBL> getGblList(OutboundFilter outboundFilter) {
		return getSqlSession().selectList("outboundMapper.getGblList", outboundFilter);
	}
	
}
