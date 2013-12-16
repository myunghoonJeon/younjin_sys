package org.youngjin.net.outbound;

public class Addition {
	private Integer seq;
	
	private String title;
	private String price;

	private Integer gblSeq;

	private Double cost;

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

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Addition [seq : ").append(seq).append(" ;\ntitle : ")
				.append(title).append(" ;\nprice : ").append(price)
				.append(" ;\ngblSeq : ").append(gblSeq).append(" ;\ncost : ")
				.append(cost).append(" ]");
		return builder.toString();
	}

	public Double getCost() {
		return cost;
	}

	public void setCost(Double cost) {
		this.cost = cost;
	}

}
