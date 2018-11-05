package com.nagarro.YourMartPMPAdminPanel.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Indexed	
@Table(name = "product")
public class Product {
	public Product() {

	}

	@DocumentId
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@JoinColumn(name = "sellerProductCode", referencedColumnName = "id")
    @IndexedEmbedded
	@ManyToOne
	private Seller seller;
    @Field
	@Column(name = "name")
	private String name;
	@Column(name = "primaryImg")
	private String primaryImg;
	@Column(name = "GalleryImages")
	private String galleryImages;
	@Field
	@Column(name = "status")
	private String status;
	
	@IndexedEmbedded
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
     @Column(name="comment")
     private String comment ;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd/MM/yyyy", timezone="Asia/Kolkata")
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
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

	public String[] getGalleryImages() {
		if(galleryImages!= null) {
		String arr[]= galleryImages.split("HEENA");
		return arr;}
		return null;
	}

	public void setGalleryImages(String[] galleryImagesArr) {
		System.out.println(galleryImagesArr.length);
	String s = galleryImagesArr[0];
		for(int i =1;i<galleryImagesArr.length ;i++) {
			
	s =	s.concat("HEENA");
		System.out.println(s);
		s= s.concat(galleryImagesArr[i]);
		}
		this.galleryImages= s;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
	

}
