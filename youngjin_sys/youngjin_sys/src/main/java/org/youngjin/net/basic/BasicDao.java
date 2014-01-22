package org.youngjin.net.basic;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

@Repository
public class BasicDao extends SqlSessionDaoSupport {

	public List<Branch> getBranch(Branch branch) {
		return getSqlSession().selectList("basicMapper.getBranch", branch);
	}

	public void insertBranch(Branch branch) {
		getSqlSession().insert("basicMapper.insertBranch", branch);
	}

	public void updateBranch(Branch branch) {
		getSqlSession().update("basicMapper.updateBranch", branch);
	}

	public List<Pod> getPodList(Pod pod) {
		return getSqlSession().selectList("basicMapper.getPod", pod);
	}

	public void podAdd(Pod pod) {
		getSqlSession().insert("basicMapper.insertPod", pod);
	}

	public void updatePod(Pod pod) {
		getSqlSession().update("basicMapper.updatePod", pod);		
	}

	public List<Company> getCompanyList(Company company) {
		return getSqlSession().selectList("basicMapper.getCompany", company);
	}

	public List<Carrier> getCarrierList(Carrier carrier) {
		return getSqlSession().selectList("basicMapper.getCarrier", carrier);
	}

	public void insertCarrier(Carrier carrier) {
		getSqlSession().insert("basicMapper.insertCarrier", carrier);		
	}

	public void updateCarrier(Carrier carrier) {
		getSqlSession().update("basicMapper.updateCarrier", carrier);
	}

	public void insertCompany(Company company) {
		getSqlSession().insert("basicMapper.insertCompany", company);
	}

	public void updateCompany(Company company) {
		getSqlSession().update("basicMapper.updateCompany", company);
	}
}
