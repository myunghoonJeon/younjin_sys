package org.youngjin.net.inbound;

public class OnHandList {
	private Integer seq;
	private String onHandDate;
	private String firstArrivalableDeliverDate;
	private String writeDate;
	private OnHandListContent onHandListContent;

	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	public String getOnHandDate() {
		return onHandDate;
	}

	public void setOnHandDate(String onHandDate) {
		this.onHandDate = onHandDate;
	}

	public String getFirstArrivalableDeliverDate() {
		return firstArrivalableDeliverDate;
	}

	public void setFirstArrivalableDeliverDate(
			String firstArrivalableDeliverDate) {
		this.firstArrivalableDeliverDate = firstArrivalableDeliverDate;
	}

	public String getWriteDate() {
		return writeDate;
	}

	public void setWriteDate(String writeDate) {
		this.writeDate = writeDate;
	}

	public OnHandListContent getOnHandListContent() {
		return onHandListContent;
	}

	public void setOnHandListContent(OnHandListContent onHandListContent) {
		this.onHandListContent = onHandListContent;
	}

	@Override
	public String toString() {
		return "OnHandList [seq=" + seq + ", onHandDate=" + onHandDate
				+ ", firstArrivalableDeliverDate="
				+ firstArrivalableDeliverDate + ", writeDate=" + writeDate
				+ ", onHandListContent=" + onHandListContent + "]";
	}

}
