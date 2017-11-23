package com.mart.dto;

public class ProductDetails {
	
	private long id;
	private String name;
	private boolean specialFlag;
	private long price;
	private long specialQuantity;
	private long specialPrice;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isSpecialFlag() {
		return specialFlag;
	}
	public void setSpecialFlag(boolean specialFlag) {
		this.specialFlag = specialFlag;
	}
	public long getPrice() {
		return price;
	}
	public void setPrice(long price) {
		this.price = price;
	}
	public long getSpecialQuantity() {
		return specialQuantity;
	}
	public void setSpecialQuantity(long specialQuantity) {
		this.specialQuantity = specialQuantity;
	}
	public long getSpecialPrice() {
		return specialPrice;
	}
	public void setSpecialPrice(long specialPrice) {
		this.specialPrice = specialPrice;
	}
	
	

}
