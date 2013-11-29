package org.youngjin.net.outbound;

import org.youngjin.net.util.AbstractListFilter;

public class OutboundFilter extends AbstractListFilter {

	private String branch;
	private String carrier;
	private String code;
	
	private String startPud;
	private String endPud;

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public String getCarrier() {
		return carrier;
	}

	public void setCarrier(String carrier) {
		this.carrier = carrier;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getStartPud() {
		return startPud;
	}

	public void setStartPud(String startPud) {
		this.startPud = startPud;
	}

	public String getEndPud() {
		return endPud;
	}

	public void setEndPud(String endPud) {
		this.endPud = endPud;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("OutboundFilter [branch : ").append(branch)
				.append(" ;\ncarrier : ").append(carrier).append(" ;\ncode : ")
				.append(code).append(" ;\nstartPud : ").append(startPud)
				.append(" ;\nendPud : ").append(endPud).append(" ]");
		return builder.toString();
	}
}
