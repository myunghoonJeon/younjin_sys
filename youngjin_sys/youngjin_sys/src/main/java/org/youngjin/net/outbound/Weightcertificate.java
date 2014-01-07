package org.youngjin.net.outbound;

public class Weightcertificate {
	private Integer seq;
	private String piece;
	private String type;
	private String status;
	private String gross;
	private String grossKg;
	private String tare;
	private String net;
	private String cuft;
	private String remark;
	private Integer gblSeq;
	private String date;

	private Boolean truckCheck;
	private Integer truckSeq;

	private String proGear;
	private String sealNo;
	private String lbs;

	private Integer baseWeightSeq;

	private Integer weightListSeq;

	public Integer getBaseWeightSeq() {
		return baseWeightSeq;
	}

	public void setBaseWeightSeq(Integer baseWeightSeq) {
		this.baseWeightSeq = baseWeightSeq;
	}

	public Integer getWeightListSeq() {
		return weightListSeq;
	}

	public void setWeightListSeq(Integer weightListSeq) {
		this.weightListSeq = weightListSeq;
	}

	public String getProGear() {
		return proGear;
	}

	public void setProGear(String proGear) {
		this.proGear = proGear;
	}

	public String getSealNo() {
		return sealNo;
	}

	public void setSealNo(String sealNo) {
		this.sealNo = sealNo;
	}

	public String getLbs() {
		return lbs;
	}

	public void setLbs(String lbs) {
		this.lbs = lbs;
	}

	public Boolean getTruckCheck() {
		return truckCheck;
	}

	public void setTruckCheck(Boolean truckCheck) {
		this.truckCheck = truckCheck;
	}

	public Integer getTruckSeq() {
		return truckSeq;
	}

	public void setTruckSeq(Integer truckSeq) {
		this.truckSeq = truckSeq;
	}

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
				+ type + ", status=" + status + ", gross=" + gross
				+ ", grossKg=" + grossKg + ", tare=" + tare + ", net=" + net
				+ ", cuft=" + cuft + ", remark=" + remark + ", gblSeq="
				+ gblSeq + ", date=" + date + ", truckCheck=" + truckCheck
				+ ", truckSeq=" + truckSeq + ", proGear=" + proGear
				+ ", sealNo=" + sealNo + ", lbs=" + lbs + ", baseWeightSeq="
				+ baseWeightSeq + ", weightListSeq=" + weightListSeq
				+ ", count=" + count + "]";
	}

}
