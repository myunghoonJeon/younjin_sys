package org.youngjin.net.basic;

public class Company {
	private Integer seq;
	private String companyCode;
	private String companyName;
	private String companyNameAcronym;
	private String companyFullName;
	private String address;
	private String president;
	private String manager;
	private String telNo;
	private String faxNo;
	private String eMailAddress;
	private String businessLicenceNo;

	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCompanyNameAcronym() {
		return companyNameAcronym;
	}

	public void setCompanyNameAcronym(String companyNameAcronym) {
		this.companyNameAcronym = companyNameAcronym;
	}

	public String getCompanyFullName() {
		return companyFullName;
	}

	public void setCompanyFullName(String companyFullName) {
		this.companyFullName = companyFullName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPresident() {
		return president;
	}

	public void setPresident(String president) {
		this.president = president;
	}

	public String getManager() {
		return manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
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

	public String getBusinessLicenceNo() {
		return businessLicenceNo;
	}

	public void setBusinessLicenceNo(String businessLicenceNo) {
		this.businessLicenceNo = businessLicenceNo;
	}

	@Override
	public String toString() {
		return "Company [seq=" + seq + ", companyCode=" + companyCode
				+ ", companyName=" + companyName + ", companyNameAcronym="
				+ companyNameAcronym + ", companyFullName=" + companyFullName
				+ ", address=" + address + ", president=" + president
				+ ", manager=" + manager + ", telNo=" + telNo + ", faxNo="
				+ faxNo + ", eMailAddress=" + eMailAddress
				+ ", businessLicenceNo=" + businessLicenceNo + "]";
	}

}
