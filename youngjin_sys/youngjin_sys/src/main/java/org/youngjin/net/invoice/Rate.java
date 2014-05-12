package org.youngjin.net.invoice;

public class Rate {
	private Integer seq;
	private String tsp;
	private String title;
	private String code;
	private Double rate;
	private String process;
	private String obType;
	private String containerStatus;
	private String containerRate = "1";
	private String writeYear;
	
	public Rate() {
		super();
	}

	//invoice_rate
	public Rate(String tsp, String code, Double rate, String process,
			String obType, String containerStatus, String containerRate) {
		super();
		this.tsp = tsp;
		this.code = code;
		this.rate = rate;
		this.process = process;
		this.obType = obType;
		this.containerStatus = containerStatus;
		this.containerRate = containerRate;
	}
	
	

	public Rate(String title, String code, String process) {
		super();
		this.title = title;
		this.code = code;
		this.process = process;
	}

	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	public String getTsp() {
		return tsp;
	}

	public void setTsp(String tsp) {
		this.tsp = tsp;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Double getRate() {
		if( this.rate == null || this.rate == 0.0)
			return 1.0;
		else 
			return rate;
	}

	public void setRate(Double rate) {
		this.rate = rate;
	}

	public String getProcess() {
		return process;
	}

	public void setProcess(String process) {
		this.process = process;
	}

	public String getObType() {
		return obType;
	}

	public void setObType(String obType) {
		this.obType = obType;
	}

	public String getContainerStatus() {
		return containerStatus;
	}

	public void setContainerStatus(String containerStatus) {
		this.containerStatus = containerStatus;
	}

	public String getContainerRate() {
		return containerRate;
	}

	public void setContainerRate(String containerRate) {
		this.containerRate = containerRate;
	}

	public String getWriteYear() {
		return writeYear;
	}

	public void setWriteYear(String wy) {
		System.out.println("[[[[[[[[[[[[setWriteYear : "+wy+"]]]]]]]]]]]]]");
		this.writeYear = wy;
	}

	@Override
	public String toString() {
		return "Rate [seq=" + seq + ", tsp=" + tsp + ", title=" + title
				+ ", code=" + code + ", rate=" + rate + ", process=" + process
				+ ", obType=" + obType + ", containerStatus=" + containerStatus
				+ ", containerRate=" + containerRate + ", writeYear="
				+ writeYear + "]";
	}

}
