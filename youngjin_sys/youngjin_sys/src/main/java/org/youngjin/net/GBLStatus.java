package org.youngjin.net;

public class GBLStatus {
	private String no;
	private String input;

	// outbound
	private String preperation;
	private String truckManifast;
	private String bookingList;
	private String invoice;
	private String arrival;
	private String process;

	// inbound
	private String weight;
	private String custom;
	private String onHandList;
	private String delivery;

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getInput() {
		return input;
	}

	public void setInput(String input) {
		this.input = input;
	}

	public String getPreperation() {
		return preperation;
	}

	public void setPreperation(String preperation) {
		this.preperation = preperation;
	}

	public String getDelivery() {
		return delivery;
	}

	public void setDelivery(String delivery) {
		this.delivery = delivery;
	}

	public String getInvoice() {
		return invoice;
	}

	public void setInvoice(String invoice) {
		this.invoice = invoice;
	}

	public String getArrival() {
		return arrival;
	}

	public void setArrival(String arrival) {
		this.arrival = arrival;
	}

	public String getProcess() {
		return process;
	}

	public void setProcess(String process) {
		this.process = process;
	}

	public String getTruckManifast() {
		return truckManifast;
	}

	public void setTruckManifast(String truckManifast) {
		this.truckManifast = truckManifast;
	}

	public String getBookingList() {
		return bookingList;
	}

	public void setBookingList(String bookingList) {
		this.bookingList = bookingList;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getCustom() {
		return custom;
	}

	public void setCustom(String custom) {
		this.custom = custom;
	}

	public String getOnHandList() {
		return onHandList;
	}

	public void setOnHandList(String onHandList) {
		this.onHandList = onHandList;
	}

	public String getCurrentState() {
		if (this.input.equals("1")) {
			if (this.preperation.equals("1")) {
				if (this.truckManifast.equals("1")) {
					if (this.bookingList.equals("1")) {
						if (this.invoice.equals("1")) {
							return "COMPLETE";
						} else {
							return "INVOICE";
						}
					} else {
						return "BOOKINGLIST";
					}
				} else if (this.bookingList.equals("1")) {
					if (this.truckManifast.equals("1")) {
						if (this.invoice.equals("1")) {
							return "COMPLETE";
						} else {
							return "INVOICE";
						}
					} else {
						return "TRUCKMANIFAST";
					}
				} else {
					return "DELIVERY";
				}
			} else
				return "PREPARATION";
		}

		return "INPUT";
	}
	
	public String getInboundCurrentState(){
		if (this.input.equals("1")) {
			if(this.weight.equals("1")){
				if(this.custom.equals("1")){
					if(this.onHandList.equals("1")){
						if(this.delivery.equals("1")){
							return "COMPLETE";
						} else {
							return "DELIVERY";
						}
					} else {
						return "ON HAND LIST";
					}
				} else {
					return "CUSTOM";
				}
			} else {
				return "WEIGHT";
			}
		}
		
		return "INPUT";
	}

	@Override
	public String toString() {
		return "GBLStatus [no=" + no + ", input=" + input + ", preperation="
				+ preperation + ", truckManifast=" + truckManifast
				+ ", bookingList=" + bookingList + ", invoice=" + invoice
				+ ", arrival=" + arrival + ", process=" + process + ", weight="
				+ weight + ", custom=" + custom + ", onHandList=" + onHandList
				+ ", delivery=" + delivery + "]";
	}

}
