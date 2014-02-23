package org.youngjin.net.basic;

public class Mileage {
	private Integer seq;
	private String storedAt;
	private String destination;
	private String miles;
	private String minWeight;
	private String minRate;
	private String maxWeight;
	private String maxRate;

	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	public String getStoredAt() {
		return storedAt;
	}

	public void setStoredAt(String storedAt) {
		this.storedAt = storedAt;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public String getMiles() {
		return miles;
	}

	public void setMiles(String miles) {
		this.miles = miles;
	}

	public String getMinWeight() {
		return minWeight;
	}

	public void setMinWeight(String minWeight) {
		this.minWeight = minWeight;
	}

	public String getMinRate() {
		return minRate;
	}

	public void setMinRate(String minRate) {
		this.minRate = minRate;
	}

	public String getMaxWeight() {
		return maxWeight;
	}

	public void setMaxWeight(String maxWeight) {
		this.maxWeight = maxWeight;
	}

	public String getMaxRate() {
		return maxRate;
	}

	public void setMaxRate(String maxRate) {
		this.maxRate = maxRate;
	}

	@Override
	public String toString() {
		return "Mileage [seq=" + seq + ", storedAt=" + storedAt
				+ ", destination=" + destination + ", miles=" + miles
				+ ", minWeight=" + minWeight + ", minRate=" + minRate
				+ ", maxWeight=" + maxWeight + ", maxRate=" + maxRate + "]";
	}

}
