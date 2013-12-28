package org.youngjin.net.outbound;

import org.youngjin.net.util.AbstractListFilter;

public class OutboundFilter extends AbstractListFilter {

	private String branch;
	private String carrier;
	private String code;

	private String startPud;
	private String endPud;

	private String area;

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

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
		return "OutboundFilter [branch=" + branch + ", carrier=" + carrier
				+ ", code=" + code + ", startPud=" + startPud + ", endPud="
				+ endPud + ", area=" + area + "]";
	}
}
