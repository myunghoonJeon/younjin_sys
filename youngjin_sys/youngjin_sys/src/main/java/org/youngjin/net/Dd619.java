package org.youngjin.net;

public class Dd619 {
	private Integer seq;
	private String gblNo;
	private String date;
	private String name;
	private String ssn;
	private String rank;
	private String originOfShipment;
	private String destination;
	private String orderingActivityName;
	private String carrierName;
	private String agentName;
	private String signature;
	private String carrierShipmentReference;
	private String code;
	private String other;
	private String total;
	private String remark;
	private String officerMaterial;
	private String officerSignature;
	private String officerDate;
	private String rankAndName;
	private String transportationDate;
	private Integer gblSeq;
	private String writeDate;
	private String writeUser;

	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	public String getGblNo() {
		return gblNo;
	}

	public void setGblNo(String gblNo) {
		this.gblNo = gblNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSsn() {
		return ssn;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	public String getRank() {
		return rank;
	}

	public void setRank(String rank) {
		this.rank = rank;
	}

	public String getOriginOfShipment() {
		return originOfShipment;
	}

	public void setOriginOfShipment(String originOfShipment) {
		this.originOfShipment = originOfShipment;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public String getOrderingActivityName() {
		return orderingActivityName;
	}

	public void setOrderingActivityName(String orderingActivityName) {
		this.orderingActivityName = orderingActivityName;
	}

	public String getCarrierName() {
		return carrierName;
	}

	public void setCarrierName(String carrierName) {
		this.carrierName = carrierName;
	}

	public String getAgentName() {
		return agentName;
	}

	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public String getCarrierShipmentReference() {
		return carrierShipmentReference;
	}

	public void setCarrierShipmentReference(String carrierShipmentReference) {
		this.carrierShipmentReference = carrierShipmentReference;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getOther() {
		return other;
	}

	public void setOther(String other) {
		this.other = other;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getOfficerMaterial() {
		return officerMaterial;
	}

	public void setOfficerMaterial(String officerMaterial) {
		this.officerMaterial = officerMaterial;
	}

	public String getOfficerSignature() {
		return officerSignature;
	}

	public void setOfficerSignature(String officerSignature) {
		this.officerSignature = officerSignature;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getRankAndName() {
		return rankAndName;
	}

	public void setRankAndName(String rankAndName) {
		this.rankAndName = rankAndName;
	}

	public Integer getGblSeq() {
		return gblSeq;
	}

	public void setGblSeq(Integer gblSeq) {
		this.gblSeq = gblSeq;
	}

	public String getWriteDate() {
		return writeDate;
	}

	public void setWriteDate(String writeDate) {
		this.writeDate = writeDate;
	}

	public String getWriteUser() {
		return writeUser;
	}

	public void setWriteUser(String writeUser) {
		this.writeUser = writeUser;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Dd619 [seq : ").append(seq).append(" ;\ngblNo : ")
				.append(gblNo).append(" ;\ndate : ").append(date)
				.append(" ;\nname : ").append(name).append(" ;\nssn : ")
				.append(ssn).append(" ;\nrank : ").append(rank)
				.append(" ;\noriginOfShipment : ").append(originOfShipment)
				.append(" ;\ndestination : ").append(destination)
				.append(" ;\norderingActivityName : ")
				.append(orderingActivityName).append(" ;\ncarrierName : ")
				.append(carrierName).append(" ;\nagentName : ")
				.append(agentName).append(" ;\nsignature : ").append(signature)
				.append(" ;\ncarrierShipmentReference : ")
				.append(carrierShipmentReference).append(" ;\ncode : ")
				.append(code).append(" ;\nother : ").append(other)
				.append(" ;\ntotal : ").append(total).append(" ;\nremark : ")
				.append(remark).append(" ;\nofficerMaterial : ")
				.append(officerMaterial).append(" ;\nofficerSignature : ")
				.append(officerSignature).append(" ;\nofficerDate : ")
				.append(officerDate).append(" ;\nrankAndName : ")
				.append(rankAndName).append(" ;\ntransportationDate : ")
				.append(transportationDate).append(" ;\ngblSeq : ")
				.append(gblSeq).append(" ;\nwriteDate : ").append(writeDate)
				.append(" ;\nwriteUser : ").append(writeUser).append(" ]");
		return builder.toString();
	}

	public String getOfficerDate() {
		return officerDate;
	}

	public void setOfficerDate(String officerDate) {
		this.officerDate = officerDate;
	}

	public String getTransportationDate() {
		return transportationDate;
	}

	public void setTransportationDate(String transportationDate) {
		this.transportationDate = transportationDate;
	}

}
