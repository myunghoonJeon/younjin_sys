package org.youngjin.net.inbound;

public class TruckManifast {
	private Integer seq;
	private String truckManifastDate;
	private String area;

	private Integer onHandListContentSeq;

	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	public String getTruckManifastDate() {
		return truckManifastDate;
	}

	public void setTruckManifastDate(String truckManifastDate) {
		this.truckManifastDate = truckManifastDate;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public Integer getOnHandListContentSeq() {
		return onHandListContentSeq;
	}

	public void setOnHandListContentSeq(Integer onHandListContentSeq) {
		this.onHandListContentSeq = onHandListContentSeq;
	}

	@Override
	public String toString() {
		return "TruckManifast [seq=" + seq + ", truckManifastDate="
				+ truckManifastDate + ", area=" + area
				+ ", onHandListContentSeq=" + onHandListContentSeq + "]";
	}

}
