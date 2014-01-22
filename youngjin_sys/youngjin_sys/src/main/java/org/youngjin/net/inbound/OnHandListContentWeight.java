package org.youngjin.net.inbound;

public class OnHandListContentWeight {
	private Integer seq;
	private Integer onHandListContentSeq;
	private Integer weightSeq;

	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	public Integer getOnHandListContentSeq() {
		return onHandListContentSeq;
	}

	public void setOnHandListContentSeq(Integer onHandListContentSeq) {
		this.onHandListContentSeq = onHandListContentSeq;
	}

	public Integer getWeightSeq() {
		return weightSeq;
	}

	public void setWeightSeq(Integer weightSeq) {
		this.weightSeq = weightSeq;
	}

	@Override
	public String toString() {
		return "OnHandListContentWeight [seq=" + seq
				+ ", onHandListContentSeq=" + onHandListContentSeq
				+ ", weightSeq=" + weightSeq + "]";
	}
}
