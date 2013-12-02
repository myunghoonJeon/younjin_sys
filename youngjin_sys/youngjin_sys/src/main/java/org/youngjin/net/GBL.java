package org.youngjin.net;

public class GBL {
	private Integer seq;

	private String no;
	private String customerName;
	private String pud;
	private String rank;
	private String code;
	private String scac;
	private String originAddress;
	private String ssn;
	private String areaLocal;
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

	private String originGBlock;
	private String destGBlock;

	private String originCity;

	private String milSVC;

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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
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

	public String getAreaLocal() {
		return areaLocal;
	}

	public void setAreaLocal(String areaLocal) {
		this.areaLocal = areaLocal;
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

	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	public String getOriginGBlock() {
		return originGBlock;
	}

	public void setOriginGBlock(String originGBlock) {
		this.originGBlock = originGBlock;
	}

	public String getDestGBlock() {
		return destGBlock;
	}

	public void setDestGBlock(String destGBlock) {
		this.destGBlock = destGBlock;
	}

	public String getMilSVC() {
		return milSVC;
	}

	public void setMilSVC(String milSVC) {
		this.milSVC = milSVC;
	}

	public String getOriginCity() {
		return originCity;
	}

	public void setOriginCity(String originCity) {
		this.originCity = originCity;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("GBL [seq : ").append(seq).append(" ;\nno : ")
				.append(no).append(" ;\ncustomerName : ").append(customerName)
				.append(" ;\npud : ").append(pud).append(" ;\nrank : ")
				.append(rank).append(" ;\ncode : ").append(code)
				.append(" ;\nscac : ").append(scac)
				.append(" ;\noriginAddress : ").append(originAddress)
				.append(" ;\nssn : ").append(ssn).append(" ;\nareaLocal : ")
				.append(areaLocal).append(" ;\nphoneNo : ").append(phoneNo)
				.append(" ;\nemail : ").append(email).append(" ;\nusNo : ")
				.append(usNo).append(" ;\nrdd : ").append(rdd)
				.append(" ;\npod : ").append(pod).append(" ;\npoe : ")
				.append(poe).append(" ;\nvessel : ").append(vessel)
				.append(" ;\nconsoleCompany : ").append(consoleCompany)
				.append(" ;\nhbBookingNo : ").append(hbBookingNo)
				.append(" ;\nclpNo : ").append(clpNo).append(" ;\nsealNo : ")
				.append(sealNo).append(" ;\noriginPort : ").append(originPort)
				.append(" ;\ndestPort : ").append(destPort)
				.append(" ;\ndestState : ").append(destState)
				.append(" ;\noriginGBlock : ").append(originGBlock)
				.append(" ;\ndestGBlock : ").append(destGBlock)
				.append(" ;\noriginCity : ").append(originCity)
				.append(" ;\nmilSVC : ").append(milSVC).append(" ]");
		return builder.toString();
	}
}
