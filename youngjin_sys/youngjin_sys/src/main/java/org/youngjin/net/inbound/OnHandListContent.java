package org.youngjin.net.inbound;

import org.youngjin.net.GBL;

/**
 * @author kten
 * 
 */
public class OnHandListContent {
	private Integer seq;
	private Integer gblSeq;
	private Integer onHandListSeq;
	private String by;
	private OnhandSum os;
	private OnHandListContentWeight onHandListContentWeight;

	public OnhandSum getOs() {
		return os;
	}

	public void setOs(OnhandSum os) {
		this.os = os;
	}

	private WeightIb weight;

	private GBL gbl;

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

	public WeightIb getWeight() {
		return weight;
	}

	public void setWeight(WeightIb weight) {
		this.weight = weight;
	}

	public GBL getGbl() {
		return gbl;
	}

	public void setGbl(GBL gbl) {
		this.gbl = gbl;
	}

	@Override
	public String toString() {
		return "OnHandListContent [seq=" + seq + ", gblSeq=" + gblSeq
				+ ", onHandListSeq=" + onHandListSeq + ", by=" + by
				+ ", onHandListContentWeight=" + onHandListContentWeight
				+ ", weight=" + weight + ", gbl=" + gbl + "]";
	}

}
