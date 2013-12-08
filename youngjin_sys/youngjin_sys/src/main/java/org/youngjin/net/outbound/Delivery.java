package org.youngjin.net.outbound;

public class Delivery {
	private Integer seq;
	private String containerCount;
	private String containerType;
	private String truckingDate;
	private String bookingDate;
	private String portAgent;
	private String pod;

	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	public String getContainerCount() {
		return containerCount;
	}

	public void setContainerCount(String containerCount) {
		this.containerCount = containerCount;
	}

	public String getContainerType() {
		return containerType;
	}

	public void setContainerType(String containerType) {
		this.containerType = containerType;
	}

	public String getTruckingDate() {
		return truckingDate;
	}

	public void setTruckingDate(String truckingDate) {
		this.truckingDate = truckingDate;
	}

	public String getBookingDate() {
		return bookingDate;
	}

	public void setBookingDate(String bookingDate) {
		this.bookingDate = bookingDate;
	}

	public String getPortAgent() {
		return portAgent;
	}

	public void setPortAgent(String portAgent) {
		this.portAgent = portAgent;
	}

	public String getPod() {
		return pod;
	}

	public void setPod(String pod) {
		this.pod = pod;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Delivery [seq : ").append(seq)
				.append(" ;\ncontainerCount : ").append(containerCount)
				.append(" ;\ncontainerType : ").append(containerType)
				.append(" ;\ntruckingDate : ").append(truckingDate)
				.append(" ;\nbookingDate : ").append(bookingDate)
				.append(" ;\nportAgent : ").append(portAgent)
				.append(" ;\npod : ").append(pod).append(" ]");
		return builder.toString();
	}

}
