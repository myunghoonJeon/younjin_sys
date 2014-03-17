package org.youngjin.net.process;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

@Repository
public class ProcessDao extends SqlSessionDaoSupport {

	public void gblockAdd(GBlock gblock) {
		getSqlSession().insert("processMapper.gblockAdd", gblock);
	}

	public List<GBlock> getGBlockList() {
		return getSqlSession().selectList("processMapper.getGBlock", null);
	}

	public void gblockUpdate(GBlock gblock) {
		getSqlSession().update("processMapper.gblockUpdate", gblock);
	}

	public GBlock getGblocByGbloc(String gbloc) {
		return getSqlSession().selectOne("processMapper.getGblocByGbloc", gbloc);
	}

}
