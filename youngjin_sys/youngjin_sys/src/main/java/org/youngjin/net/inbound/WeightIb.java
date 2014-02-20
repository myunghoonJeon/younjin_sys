package org.youngjin.net.inbound;

public class WeightIb {
	private Integer seq;
	private String piece;
	private String type;
	private String gross;
	private String grossKg;
	private String tare;
	private String net;
	private String cuft;
	private String reweight;
	private String remark;

	private String reGross;
	private String reTare;
	private String reNet;

	private Integer gblSeq;

	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	public String getPiece() {
		return piece;
	}

	public void setPiece(String piece) {
		this.piece = piece;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getGross() {
		return gross;
	}

	public void setGross(String gross) {
		this.gross = gross;
	}

	public String getGrossKg() {
		return grossKg;
	}

	public void setGrossKg(String grossKg) {
		this.grossKg = grossKg;
	}

	public String getTare() {
		return tare;
	}

	public void setTare(String tare) {
		this.tare = tare;
	}

	public String getNet() {
		return net;
	}

	public void setNet(String net) {
		this.net = net;
	}

	public String getCuft() {
		return cuft;
	}

	public void setCuft(String cuft) {
		this.cuft = cuft;
	}

	public String getReweight() {
		return reweight;
	}

	public void setReweight(String reweight) {
		this.reweight = reweight;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getGblSeq() {
		return gblSeq;
	}

	public void setGblSeq(Integer gblSeq) {
		this.gblSeq = gblSeq;
	}

	public String getReGross() {
		return reGross;
	}

	public void setReGross(String reGross) {
		this.reGross = reGross;
	}

	public String getReTare() {
		return reTare;
	}

	public void setReTare(String reTare) {
		this.reTare = reTare;
	}

	public String getReNet() {
		return reNet;
	}

	public void setReNet(String reNet) {
		this.reNet = reNet;
	}

	@Override
	public String toString() {
		return "WeightIb [seq=" + seq + ", piece=" + piece + ", type=" + type
				+ ", gross=" + gross + ", grossKg=" + grossKg + ", tare="
				+ tare + ", net=" + net + ", cuft=" + cuft + ", reweight="
				+ reweight + ", remark=" + remark + ", gblSeq=" + gblSeq + "]";
	}

}
