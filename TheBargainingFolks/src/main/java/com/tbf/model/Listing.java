package com.tbf.model;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ProductListings")
public class Listing {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private long sellerId;
	private String title;
	private String category;
	private String quality;
	private String description;
	private Double price;
	private LocalDateTime uploadTime;
	
	public Listing() {
		
	}
	
	public Listing(long sellerId, String title, String category, String quality, String description, Double price,
			LocalDateTime uploadTime) {
		this.sellerId = sellerId;
		this.title = title;
		this.category = category;
		this.quality = quality;
		this.description = description;
		this.price = price;
		this.uploadTime = LocalDateTime.now();
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

	public LocalDateTime getUploadTime() {
		return uploadTime;
	}

	public void setUploadTime(LocalDateTime uploadTime) {
		this.uploadTime = uploadTime;
	}

	@Override
	public String toString() {
		return "Listing [id=" + id + ", sellerId=" + sellerId + ", title=" + title + ", category=" + category
				+ ", quality=" + quality + ", description=" + description + ", price=" + price + ", uploadTime="
				+ uploadTime + "]";
	}
}
