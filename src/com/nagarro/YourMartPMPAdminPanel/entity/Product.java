package com.nagarro.YourMartPMPAdminPanel.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity

@Table(name = "product")
public class Product {
	public Product() {

	}

	@Id
	@Column(name = "id")

	private String id;

	@JoinColumn(name = "sellerProductCode", referencedColumnName = "id")
	@ManyToOne
	private Seller seller;

	@Column(name = "name")
	private String name;
	@Column(name = "primaryImg")
	private String primaryImg;
	@Column(name = "GalleryImages")
	private String galleryImages;
	@Column(name = "status")
	private String status;
	@ManyToOne
	@JoinColumn(name = "categoryId", referencedColumnName = "id")
	private Category category;
	@Column(name = "MRP")
	private double mrp;
	@Column(name = "SSP")
	private double ssp;
	@Column(name = "YMP")
	private double ymp;
	@Column(name = "shortDescription")
	private String shortDescription;
	@Column(name = "longDescription")
	private String longDescription;
	@Column(name = "created")
	private Date created;

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Seller getSeller() {
		return seller;
	}

	public void setSeller(Seller seller) {
		this.seller = seller;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public double getMrp() {
		return mrp;
	}

	public void setMrp(double mrp) {
		this.mrp = mrp;
	}

	public double getSsp() {
		return ssp;
	}

	public void setSsp(double ssp) {
		this.ssp = ssp;
	}

	public double getYmp() {
		return ymp;
	}

	public void setYmp(double ymp) {
		this.ymp = ymp;
	}

	public String getShortDescription() {
		return shortDescription;
	}

	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}

	public String getLongDescription() {
		return longDescription;
	}

	public void setLongDescription(String longDescription) {
		this.longDescription = longDescription;
	}

	public String getPrimaryImg() {
		return primaryImg;
	}

	public void setPrimaryImg(String primaryImg) {
		this.primaryImg = primaryImg;
	}

	public String getGalleryImages() {
		return galleryImages;
	}

	public void setGalleryImages(String galleryImages) {
		this.galleryImages = galleryImages;
	}

}
