package org.youngjin.net.inbound;

public class Reweight {
	private Integer seq;
	private String reweightName;
	private String reweightDate;

	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	public String getReweightName() {
		return reweightName;
	}

	public void setReweightName(String reweightName) {
		this.reweightName = reweightName;
	}

	public String getReweightDate() {
		return reweightDate;
	}

	public void setReweightDate(String reweightDate) {
		this.reweightDate = reweightDate;
	}

	@Override
	public String toString() {
		return "Reweight [seq=" + seq + ", reweightName=" + reweightName
				+ ", reweightDate=" + reweightDate + "]";
	}

}
