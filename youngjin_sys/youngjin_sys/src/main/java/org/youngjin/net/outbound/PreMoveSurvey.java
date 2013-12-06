package org.youngjin.net.outbound;

public class PreMoveSurvey {
	private Integer seq;
	private Double estimateWeight;
	private String specialItem;
	private String esContainer;
	private String thirdParty;
	private String fireArms;
	private String remark;

	private Integer gblSeq;

	public Double getEstimateWeight() {
		return estimateWeight;
	}

	public void setEstimateWeight(Double estimateWeight) {
		this.estimateWeight = estimateWeight;
	}

	public String getSpecialItem() {
		return specialItem;
	}

	public void setSpecialItem(String specialItem) {
		this.specialItem = specialItem;
	}

	public String getThirdParty() {
		return thirdParty;
	}

	public void setThirdParty(String thirdParty) {
		this.thirdParty = thirdParty;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	public Integer getGblSeq() {
		return gblSeq;
	}

	public void setGblSeq(Integer gblSeq) {
		this.gblSeq = gblSeq;
	}

	public String getEsContainer() {
		return esContainer;
	}

	public void setEsContainer(String esContainer) {
		this.esContainer = esContainer;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PreMoveSurvey [seq : ").append(seq)
				.append(" ;\nestimateWeight : ").append(estimateWeight)
				.append(" ;\nspecialItem : ").append(specialItem)
				.append(" ;\nesContainer : ").append(esContainer)
				.append(" ;\nthirdParty : ").append(thirdParty)
				.append(" ;\nfireArms : ").append(fireArms)
				.append(" ;\nremark : ").append(remark).append(" ;\ngblSeq : ")
				.append(gblSeq).append(" ]");
		return builder.toString();
	}

	public String getFireArms() {
		return fireArms;
	}

	public void setFireArms(String fireArms) {
		this.fireArms = fireArms;
	}

}
