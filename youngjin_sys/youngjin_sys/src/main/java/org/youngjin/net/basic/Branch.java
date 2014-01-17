package org.youngjin.net.basic;

/**
 * @author kten
 * 
 */
public class Branch {
	private Integer seq;
	private String branchAcronym;
	private String branch;
	private String branchManager;
	private String telNo;
	private String faxNo;
	private String eMailAddress;
	private String itoTelNo;
	private String itoDsnNo;
	private String itoChief;
	private String itoAddress;
	private String dGbloc;
	private String dodac;

	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	public String getBranchAcronym() {
		return branchAcronym;
	}

	public void setBranchAcronym(String branchAcronym) {
		this.branchAcronym = branchAcronym;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public String getBranchManager() {
		return branchManager;
	}

	public void setBranchManager(String branchManager) {
		this.branchManager = branchManager;
	}

	public String getTelNo() {
		return telNo;
	}

	public void setTelNo(String telNo) {
		this.telNo = telNo;
	}

	public String getFaxNo() {
		return faxNo;
	}

	public void setFaxNo(String faxNo) {
		this.faxNo = faxNo;
	}

	public String geteMailAddress() {
		return eMailAddress;
	}

	public void seteMailAddress(String eMailAddress) {
		this.eMailAddress = eMailAddress;
	}

	public String getItoTelNo() {
		return itoTelNo;
	}

	public void setItoTelNo(String itoTelNo) {
		this.itoTelNo = itoTelNo;
	}

	public String getItoDsnNo() {
		return itoDsnNo;
	}

	public void setItoDsnNo(String itoDsnNo) {
		this.itoDsnNo = itoDsnNo;
	}

	public String getItoChief() {
		return itoChief;
	}

	public void setItoChief(String itoChief) {
		this.itoChief = itoChief;
	}

	public String getItoAddress() {
		return itoAddress;
	}

	public void setItoAddress(String itoAddress) {
		this.itoAddress = itoAddress;
	}

	public String getdGbloc() {
		return dGbloc;
	}

	public void setdGbloc(String dGbloc) {
		this.dGbloc = dGbloc;
	}

	public String getdodac() {
		return dodac;
	}

	public void setdDodac(String dodac) {
		this.dodac = dodac;
	}

	@Override
	public String toString() {
		return "Branch [seq=" + seq + ", branchAcronym=" + branchAcronym
				+ ", branch=" + branch + ", branchManager=" + branchManager
				+ ", telNo=" + telNo + ", faxNo=" + faxNo + ", eMailAddress="
				+ eMailAddress + ", itoTelNo=" + itoTelNo + ", itoDsnNo="
				+ itoDsnNo + ", itoChief=" + itoChief + ", itoAddress="
				+ itoAddress + ", dGbloc=" + dGbloc + ", dodac=" + dodac + "]";
	}

}
