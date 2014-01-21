package org.youngjin.net.basic;

public class Carrier {
	private Integer seq;
	private String scac;
	private String scacFullName;
	private String address;
	private String telNo;
	private String faxNo;
	private String president;
	private String manager;
	private String mainEmail;
	private String trafficEmail;
	private String agent;

	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	public String getScac() {
		return scac;
	}

	public void setScac(String scac) {
		this.scac = scac;
	}

	public String getScacFullName() {
		return scacFullName;
	}

	public void setScacFullName(String scacFullName) {
		this.scacFullName = scacFullName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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

	public String getMainEmail() {
		return mainEmail;
	}

	public void setMainEmail(String mainEmail) {
		this.mainEmail = mainEmail;
	}

	public String getTrafficEmail() {
		return trafficEmail;
	}

	public void setTrafficEmail(String trafficEmail) {
		this.trafficEmail = trafficEmail;
	}

	public String getAgent() {
		return agent;
	}

	public void setAgent(String agent) {
		this.agent = agent;
	}

	@Override
	public String toString() {
		return "CARRIER [scac=" + scac + ", scacFullName=" + scacFullName
				+ ", address=" + address + ", telNo=" + telNo + ", faxNo="
				+ faxNo + ", president=" + president + ", manager=" + manager
				+ ", mainEmail=" + mainEmail + ", trafficEmail=" + trafficEmail
				+ ", agent=" + agent + "]";
	}

}
