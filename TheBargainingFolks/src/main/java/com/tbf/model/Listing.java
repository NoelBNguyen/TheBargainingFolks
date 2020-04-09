package com.tbf.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "ProductListings")
public class Listing {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@NotNull
	private long sellerId;
	private String title;
	private String category;
	private String quality;
	private String description;
	@NotNull
	private Double price;
	private byte[] img;
	
	@CreationTimestamp
	private Date uploadTime;
	
	private String status;
	@Column(nullable=true)
	private Long buyerId;
	
	@UpdateTimestamp
	private Date resolvedTime;
	
	public Listing() {
		
	}
	
	public Listing(long sellerId, String title, String category, String quality, String description, Double price,
			Date uploadTime, byte[] img, String status, long buyerId, Date resolvedTime) {
		this.sellerId = sellerId;
		this.title = title;
		this.category = category;
		this.quality = quality;
		this.description = description;
		this.price = price;
		this.uploadTime = new Date();
		this.img = img;
		this.status = status;
		this.buyerId=buyerId;
		this.resolvedTime = resolvedTime;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getSellerId() {
		return sellerId;
	}

	public void setSellerId(long sellerId) {
		this.sellerId = sellerId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getQuality() {
		return quality;
	}

	public void setQuality(String quality) {
		this.quality = quality;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Date getUploadTime() {
		return uploadTime;
	}

	public void setUploadTime(Date uploadTime) {
		this.uploadTime = uploadTime;
	}

	public byte[] getImg() {
		return img;
	}

	public void setImg(byte[] img) {
		this.img = img;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Long getBuyerId() {
		return buyerId;
	}

	public void setBuyerId(Long buyerId) {
		this.buyerId = buyerId;
	}

	public Date getResolvedTime() {
		return resolvedTime;
	}

	public void setResolvedTime(Date resolvedTime) {
		this.resolvedTime = resolvedTime;
	}

	@Override
	public String toString() {
		return "Listing [id=" + id + ", sellerId=" + sellerId + ", title=" + title + ", category=" + category
				+ ", quality=" + quality + ", description=" + description + ", price=" + price + ", uploadTime="
				+ uploadTime + "]";
	}
}
