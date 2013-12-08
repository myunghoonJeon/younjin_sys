package org.youngjin.net.outbound;

import java.util.List;

import org.youngjin.net.GBL;

public class TruckManifast {
	private String seq;

	private List<GBL> gblList;

	public String getSeq() {
		return seq;
	}

	public void setSeq(String seq) {
		this.seq = seq;
	}

	public List<GBL> getGblList() {
		return gblList;
	}

	public void setGblList(List<GBL> gblList) {                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        
		this.gblList = gblList;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TruckManifast [seq : ").append(seq)
				.append(" ;\ngblList : ").append(gblList).append(" ]");
		return builder.toString();
	}

}
