package org.youngjin.net;

public class GBLStatus {
	private String no;
	private String input;
	private String preperation;
	private String delivery;
	private String truckManifast;
	private String bookingList;
	private String invoice;
	private String arrival;
	private String process;

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
	
	public String getCurrentState(){
		if( this.input.equals("1")){
			if(this.preperation.equals("1")){
				if(this.truckManifast.equals("1")){
					if(this.bookingList.equals("1")){
						if(this.invoice.equals("1")){
							return "COMPLETE";
						} else {
							return "INVOICE";
						}
					} else {
						return "BOOKINGLIST";
					}
				} else if(this.bookingList.equals("1")){
					if(this.truckManifast.equals("1")){
						if(this.invoice.equals("1")){
							return "COMPLETE";
						} else {
							return "INVOICE";
						}
					} else {
						return "TRUCKMANIFAST";
					}
				}else{
					return "DELIVERY";
				}
			}else
				return "PREPARATION";
		}
		
		return "INPUT";
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("GBLStatus [no : ").append(no).append(" ;\ninput : ")
				.append(input).append(" ;\npreperation : ").append(preperation)
				.append(" ;\ndelivery : ").append(delivery)
				.append(" ;\ntruckManifast : ").append(truckManifast)
				.append(" ;\nbookingList : ").append(bookingList)
				.append(" ;\ninvoice : ").append(invoice)
				.append(" ;\narrival : ").append(arrival)
				.append(" ;\nprocess : ").append(process).append(" ]");
		return builder.toString();
	}

}
