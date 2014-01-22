package org.youngjin.net.inbound;

public class OnHandListContent {
	private Integer seq;
	private Integer gblSeq;
	private Integer onHandListSeq;
	private String by;

	private OnHandListContentWeight onHandListContentWeight;

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

	public Integer getOnHandListSeq() {
		return onHandListSeq;
	}

	public void setOnHandListSeq(Integer onHandListSeq) {
		this.onHandListSeq = onHandListSeq;
	}

	public OnHandListContentWeight getOnHandListContentWeight() {
		return onHandListContentWeight;
	}

	public void setOnHandListContentWeight(
			OnHandListContentWeight onHandListContentWeight) {
		this.onHandListContentWeight = onHandListContentWeight;
	}

	public String getBy() {
		return by;
	}

	public void setBy(String by) {
		this.by = by;
	}

	@Override
	public String toString() {
		return "OnHandListContent [seq=" + seq + ", gblSeq=" + gblSeq
				+ ", onHandListSeq=" + onHandListSeq + ", by=" + by
				+ ", onHandListContentWeight=" + onHandListContentWeight + "]";
	}

}
