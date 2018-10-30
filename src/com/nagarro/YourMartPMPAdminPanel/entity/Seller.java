package com.nagarro.YourMartPMPAdminPanel.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="seller")
public class Seller {
	public Seller() {
		
	}
	@Id
    @Column(name = "id")
	private int id;
	@Column(name="name")
	private String name ; 
	@Column(name="status")
	private String status ; 
	@Column(name="companyName")
	private String companyName;
	@Column(name="owner")
	private String owner ;
	@Column(name="gstNumber")
	private String gstNumber ; 
	@Column(name="address")
	private String address ;
	@Column(name="email")
	private String email;
	@Column(name="pincode")
	private int pincode ; 
	@Column(name="telephone")
	private int telephone ;
	@Column(name="registeredOn")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date registeredOn ;
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
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	public String getGstNumber() {
		return gstNumber;
	}
	public void setGstNumber(String gstNumber) {
		this.gstNumber = gstNumber;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getPincode() {
		return pincode;
	}
	public void setPincode(int pincode) {
		this.pincode = pincode;
	}
	public int getTelephone() {
		return telephone;
	}
	public void setTelephone(int telephone) {
		this.telephone = telephone;
	}
	public Date getRegisteredOn() {
		return registeredOn;
	}
	public void setRegisteredOn(Date registeredOn) {
		this.registeredOn = registeredOn;
	}
	public Seller(int id, String name, String status, String companyName, String owner, String gstNumber, String address,
			String email, int pincode, int telephone, Date registeredOn) {
		super();
		this.id = id;
		this.name = name;
		this.status = status;
		this.companyName = companyName;
		this.owner = owner;
		this.gstNumber = gstNumber;
		this.address = address;
		this.email = email;
		this.pincode = pincode;
		this.telephone = telephone;
		this.registeredOn = registeredOn;
	}
	
	
}
