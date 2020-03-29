package com.tbf.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "bargain_trans")
public class Bargain {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private long buyerId;
	private long sellerId;
	private long value;
	private String dateTime;
	
	public Bargain() {
		
	}
	
	public Bargain(long id, long buyerId, long sellerId, long value, String dateTime) {
		super();
		this.id = id;
		this.buyerId = buyerId;
		this.sellerId = sellerId;
		this.value = value;
		this.dateTime = dateTime;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getBuyerId() {
		return buyerId;
	}

	public void setBuyerId(long buyerId) {
		this.buyerId = buyerId;
	}

	public long getSellerId() {
		return sellerId;
	}

	public void setSellerId(long sellerId) {
		this.sellerId = sellerId;
	}

	public long getValue() {
		return value;
	}

	public void setValue(long value) {
		this.value = value;
	}

	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}

	
	@Override
	public String toString() {
		return "User [id=" + id + ", buyerId=" + buyerId + ", sellerId=" + sellerId + ", value=" + value + ", dateTime="
				+ dateTime + "]";
	}
	
}
