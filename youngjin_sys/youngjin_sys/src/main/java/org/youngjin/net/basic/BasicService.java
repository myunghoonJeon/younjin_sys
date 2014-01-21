package org.youngjin.net.basic;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

@Service
public class BasicService {
	@Resource
	private BasicDao basicDao;
	
	public List<Branch> getBranchList(){
		return basicDao.getBranch(null);
	}

	public void branchAdd(Branch branch) {
		basicDao.insertBranch(branch);
	}

	public Branch getBranch(Integer seq) {
		Branch branch = new Branch();
		branch.setSeq(seq);
		
		return basicDao.getBranch(branch).get(0);
	}
}
