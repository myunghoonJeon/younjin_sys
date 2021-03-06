package org.youngjin.net.outbound;

import java.util.List;

public class DeliveryGbl {
	private String seq;
	private String pud;
	private String scac;
	private String code;
	private String rank;
	private String ssn;
	private String shipper;
	private String no;
	private String milSvc;
	private String area;
	private String originGblock;
	private String pcs;
	private String pod;
	private String cuft;
	private String gross;
	private String net;
	private String destPort;
	private String destState;
	private String memo;
	private String usNo;
	private Integer type11=0;
	private Integer type125=0;
	private Integer type91=0;
	private Integer type49=0;
	private Integer typeOf1=0;
	private Integer typeOf2=0;
	private Integer typeSp=0;
	private List<Weightcertificate> containerList;
	public Integer getType11() {
		return type11;
	}

	public void setType11() {
		this.type11++;
	}

	public Integer getType125() {
		return type125;
	}

	public void setType125() {
		this.type125++;
	}

	public Integer getType91() {
		return type91;
	}

	public void setType91() {
		this.type91++;
	}

	public Integer getType49() {
		return type49;
	}

	public void setType49() {
		this.type49++;
	}

	public Integer getTypeOf1() {
		return typeOf1;
	}

	public void setTypeOf1() {
		this.typeOf1++;
	}

	public Integer getTypeOf2() {
		return typeOf2++;
	}

	public void setTypeOf2() {
		this.typeOf2++;
	}

	public Integer getTypeSp() {
		return typeSp;
	}

	public void setTypeSp() {
		this.typeSp++;
	}

	public String getPud() {
		return pud;
	}

	public void setPud(String pud) {
		this.pud = pud;
	}

	public String getScac() {
		return scac;
	}

	public void setScac(String scac) {
		this.scac = scac;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getShipper() {
		return shipper;
	}

	public void setShipper(String shipper) {
		this.shipper = shipper;
	}

	public String getRank() {
		return rank;
	}

	public void setRank(String rank) {
		this.rank = rank;
	}

	public String getSsn() {
		return ssn;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	public String getNo() {
		if (this.no.contains("sub")) {
			this.no = this.no.substring(0, this.no.length() - 5);
		}
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getMilSvc() {
		return milSvc;
	}

	public void setMilSvc(String milSvc) {
		this.milSvc = milSvc;
	}

	public String getOriginGblock() {
		return originGblock;
	}

	public void setOriginGblock(String originGblock) {
		this.originGblock = originGblock;
	}

	public String getPcs() {
		return pcs;
	}

	public void setPcs(String pcs) {
		this.pcs = pcs;
	}

	public String getCuft() {
		return cuft;
	}

	public void setCuft(String cuft) {
		this.cuft = cuft;
	}

	public String getGross() {
		return gross;
	}

	public void setGross(String gross) {
		this.gross = gross;
	}

	public String getNet() {
		return net;
	}

	public void setNet(String net) {
		this.net = net;
	}

	public String getDestPort() {
		return destPort;
	}

	public void setDestPort(String destPort) {
		this.destPort = destPort;
	}

	public String getDestState() {
		return destState;
	}

	public void setDestState(String destState) {
		this.destState = destState;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getSeq() {
		return seq;
	}

	public void setSeq(String seq) {
		this.seq = seq;
	}

	public List<Weightcertificate> getContainerList() {
		return containerList;
	}

	public void setContainerList(List<Weightcertificate> containerList) {
		this.containerList = containerList;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getPod() {
		return pod;
	}

	public void setPod(String pod) {
		this.pod = pod;
	}

	public String getUsNo() {
		return usNo;
	}

	public void setUsNo(String usNo) {
		this.usNo = usNo;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("DeliveryGbl [seq : ").append(seq).append(" ;\npud : ")
				.append(pud).append(" ;\nscac : ").append(scac)
				.append(" ;\ncode : ").append(code).append(" ;\nshipper : ")
				.append(shipper).append(" ;\nno : ").append(no)
				.append(" ;\nmilSvc : ").append(milSvc).append(" ;\narea : ")
				.append(area).append(" ;\noriginGblock : ")
				.append(originGblock).append(" ;\npcs : ").append(pcs)
				.append(" ;\npod : ").append(pod).append(" ;\ncuft : ")
				.append(cuft).append(" ;\ngross : ").append(gross)
				.append(" ;\nnet : ").append(net).append(" ;\ndestPort : ")
				.append(destPort).append(" ;\ndestState : ").append(destState)
				.append(" ;\nmemo : ").append(memo)
				.append(" ;\ncontainerList : ").append(containerList)
				.append(" ]");
		return builder.toString();
	}
}
