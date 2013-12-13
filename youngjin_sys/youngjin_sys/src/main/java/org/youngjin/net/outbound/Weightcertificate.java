package org.youngjin.net.outbound;

public class Weightcertificate {
	private Integer seq;
	private String piece;
	private String type;
	private String status;
	private String gross;
	private String tare;
	private String net;
	private String cuft;
	private String remark;
	private Integer gblSeq;
	private String date;

	private int count = 0;

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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getGross() {
		return gross;
	}

	public void setGross(String gross) {
		this.gross = gross;
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

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	@Override
	public String toString() {
		return "Weightcertificate [seq=" + seq + ", piece=" + piece + ", type="
				+ type + ", gross=" + gross + ", tare=" + tare + ", net=" + net
				+ ", cuft=" + cuft + ", remark=" + remark + ", gblSeq="
				+ gblSeq + ", writeDate=" + date + ", count=" + count
				+ "]";
	}

}
