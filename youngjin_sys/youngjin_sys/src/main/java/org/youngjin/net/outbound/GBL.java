package org.youngjin.net.outbound;

public class GBL {
	private String no;
	private String customerName;
	private String pud;
	private String rank;
	private Integer code;
	private String scac;
	private String originAddress;
	private String ssn;
	private String area;
	private String phoneNo;
	private String email;
	private String usNo;

	private String rdd;
	private String pod;
	private String poe;
	private String vessel;
	private String consoleCompany;
	private Integer hbBookingNo;
	private String clpNo;
	private String sealNo;
	private String originPort;
	private String destPort;
	private String destState;

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getPud() {
		return pud;
	}

	public void setPud(String pud) {
		this.pud = pud;
	}

	public String getRank() {
		return rank;
	}

	public void setRank(String rank) {
		this.rank = rank;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getOriginAddress() {
		return originAddress;
	}

	public void setOriginAddress(String originAddress) {
		this.originAddress = originAddress;
	}

	public String getScac() {
		return scac;
	}

	public void setScac(String scac) {
		this.scac = scac;
	}

	public String getSsn() {
		return ssn;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsNo() {
		return usNo;
	}

	public void setUsNo(String usNo) {
		this.usNo = usNo;
	}

	public String getRdd() {
		return rdd;
	}

	public void setRdd(String rdd) {
		this.rdd = rdd;
	}

	public String getPod() {
		return pod;
	}

	public void setPod(String pod) {
		this.pod = pod;
	}

	public String getPoe() {
		return poe;
	}

	public void setPoe(String poe) {
		this.poe = poe;
	}

	public String getVessel() {
		return vessel;
	}

	public void setVessel(String vessel) {
		this.vessel = vessel;
	}

	public String getConsoleCompany() {
		return consoleCompany;
	}

	public void setConsoleCompany(String consoleCompany) {
		this.consoleCompany = consoleCompany;
	}

	public Integer getHbBookingNo() {
		return hbBookingNo;
	}

	public void setHbBookingNo(Integer hbBookingNo) {
		this.hbBookingNo = hbBookingNo;
	}

	public String getClpNo() {
		return clpNo;
	}

	public void setClpNo(String clpNo) {
		this.clpNo = clpNo;
	}

	public String getSealNo() {
		return sealNo;
	}

	public void setSealNo(String sealNo) {
		this.sealNo = sealNo;
	}

	public String getOriginPort() {
		return originPort;
	}

	public void setOriginPort(String originPort) {
		this.originPort = originPort;
	}

	public String getDestPort() {
		return destPort;
	}

	public void setDestPort(String destPort) {
		this.destPort = destPort;
	}

	public String getDestState() {
		return destState;
	}

	public void setDestState(String destState) {
		this.destState = destState;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("GBL [no : ").append(no).append(" ;\ncustomerName : ")
				.append(customerName).append(" ;\npud : ").append(pud)
				.append(" ;\nrank : ").append(rank).append(" ;\ncode : ")
				.append(code).append(" ;\nscac : ").append(scac)
				.append(" ;\noriginAddress : ").append(originAddress)
				.append(" ;\nssn : ").append(ssn).append(" ;\narea : ")
				.append(area).append(" ;\nphoneNo : ").append(phoneNo)
				.append(" ;\nemail : ").append(email).append(" ;\nusNo : ")
				.append(usNo).append(" ;\nrdd : ").append(rdd)
				.append(" ;\npod : ").append(pod).append(" ;\npoe : ")
				.append(poe).append(" ;\nvessel : ").append(vessel)
				.append(" ;\nconsoleCompany : ").append(consoleCompany)
				.append(" ;\nhbBookingNo : ").append(hbBookingNo)
				.append(" ;\nclpNo : ").append(clpNo).append(" ;\nsealNo : ")
				.append(sealNo).append(" ;\noriginPort : ").append(originPort)
				.append(" ;\ndestPort : ").append(destPort)
				.append(" ;\ndestState : ").append(destState).append(" ]");
		return builder.toString();
	}
}
