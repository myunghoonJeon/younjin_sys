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
		
		try {
			return basicDao.getBranch(branch).get(0);
		} catch (IndexOutOfBoundsException e) {
			return new Branch();
		}		
	}

	public Branch getBranch(String areaLocal) {
		Branch branch = new Branch();
		branch.setBranchAcronym(areaLocal);
		
		try {
			return basicDao.getBranch(branch).get(0);			
		} catch (IndexOutOfBoundsException e) {
			return new Branch();
		}
		
	}

	public void branchModify(Branch branch) {
		basicDao.updateBranch(branch);
	}

	public List<Pod> getPodList() {
		return basicDao.getPodList(null);
	}

	public void podAdd(Pod pod) {
		basicDao.podAdd(pod);
	}

	public Pod getPod(Integer seq) {
		Pod pod = new Pod();
		pod.setSeq(seq);
		
		try {
			return basicDao.getPodList(pod).get(0);			
		} catch (IndexOutOfBoundsException e) {
			return new Pod();
		}
	}

	public Pod getPod(String podParam) {
		Pod pod = new Pod();
		pod.setPodAcronym(podParam);
		
		try {
			return basicDao.getPodList(pod).get(0);			
		} catch (IndexOutOfBoundsException e) {
			return new Pod();
		}
	}

	public void podModify(Pod pod) {
		basicDao.updatePod(pod);
	}
	
	public List<Carrier> getCarrierList(){
		return basicDao.getCarrierList(null);
	}
	
	public List<Company> getCompanyList(){
		return basicDao.getCompanyList(null);
	}

	public void carrierAdd(Carrier carrier) {
		basicDao.insertCarrier(carrier);
	}

	public Carrier getCarrier(Integer seq) {
		Carrier carrier = new Carrier();
		carrier.setSeq(seq);
		
		try {
			return basicDao.getCarrierList(carrier).get(0);			
		} catch (IndexOutOfBoundsException e) {
			return new Carrier();
		}
	}

	public void carrierModify(Carrier carrier) {
		basicDao.updateCarrier(carrier);
	}

	public void companyAdd(Company company) {
		basicDao.insertCompany(company);
	}

	public Company getCompany(Integer seq) {
		Company company = new Company();
		company.setSeq(seq);
		
		try {
			return basicDao.getCompanyList(company).get(0);			
		} catch (IndexOutOfBoundsException e) {
			return new Company();
		}
	}
	
	public Company getCompanyByCode(String code){
		Company company = new Company();
		company.setCompanyCode(code);
		
		try {
			return basicDao.getCompanyList(company).get(0);					
		} catch (IndexOutOfBoundsException e) {
			return new Company();
		}
	}

	public void companyModify(Company company) {
		basicDao.updateCompany(company);	
	}
}
