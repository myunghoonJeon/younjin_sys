package org.youngjin.net.outbound;

public class Addition {
	private Integer seq;
	
	private String title;
	private String price;

	private Double cost;

	private Integer gblSeq;
	
	private Integer dd619Seq;
	
	private Integer memorandumSeq;

	public Integer getMemorandumSeq() {
		return memorandumSeq;
	}

	public void setMemorandumSeq(Integer memorandumSeq) {
		this.memorandumSeq = memorandumSeq;
	}

	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public Integer getGblSeq() {
		return gblSeq;
	}

	public void setGblSeq(Integer gblSeq) {
		this.gblSeq = gblSeq;
	}

	public Double getCost() {
		return cost;
	}

	public void setCost(Double cost) {
		this.cost = cost;
	}

	public Integer getDd619Seq() {
		return dd619Seq;
	}

	public void setDd619Seq(Integer dd619Seq) {
		this.dd619Seq = dd619Seq;
	}

	@Override
	public String toString() {
		return "Addition [seq=" + seq + ", title=" + title + ", price=" + price
				+ ", cost=" + cost + ", gblSeq=" + gblSeq + ", dd619Seq="
				+ dd619Seq + ", memorandumSeq=" + memorandumSeq + "]";
	}

}
