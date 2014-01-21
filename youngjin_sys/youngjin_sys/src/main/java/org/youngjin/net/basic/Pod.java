package org.youngjin.net.basic;

public class Pod {
	private Integer seq;
	private String podAcronym;
	private String podFull;
	private String destAddress;
	private String podAgentName;
	private String remark;

	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	public String getPodAcronym() {
		return podAcronym;
	}

	public void setPodAcronym(String podAcronym) {
		this.podAcronym = podAcronym;
	}

	public String getPodFull() {
		return podFull;
	}

	public void setPodFull(String podFull) {
		this.podFull = podFull;
	}

	public String getDestAddress() {
		return destAddress;
	}

	public void setDestAddress(String destAddress) {
		this.destAddress = destAddress;
	}

	public String getPodAgentName() {
		return podAgentName;
	}

	public void setPodAgentName(String podAgentName) {
		this.podAgentName = podAgentName;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public String toString() {
		return "Pod [seq=" + seq + ", podAcronym=" + podAcronym + ", podFull="
				+ podFull + ", destAddress=" + destAddress + ", podAgentName="
				+ podAgentName + ", remark=" + remark + "]";
	}
}
