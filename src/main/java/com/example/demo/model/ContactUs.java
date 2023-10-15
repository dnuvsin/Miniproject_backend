package com.example.demo.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="ContactUs")
public class ContactUs {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer ContactUsId;
	
	private String userName;
	private String userEmail;
	private String userPhone;
	private String detail;
	private LocalDate dateContact;
	
	
	public ContactUs() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ContactUs(Integer contactUsId, String userName, String userEmail, String userPhone, String detail,
			LocalDate dateContact) {
		super();
		ContactUsId = contactUsId;
		this.userName = userName;
		this.userEmail = userEmail;
		this.userPhone = userPhone;
		this.detail = detail;
		this.dateContact = dateContact;
	}

	public Integer getContactUsId() {
		return ContactUsId;
	}

	public void setContactUsId(Integer contactUsId) {
		ContactUsId = contactUsId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public LocalDate getDateContact() {
		return dateContact;
	}

	public void setDateContact(LocalDate dateContact) {
		this.dateContact = dateContact;
	}
	
	
}
