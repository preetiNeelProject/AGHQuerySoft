package com.swap.modals;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/*
 *	@Author
 *	Swapril Tyagi 
*/

@Entity
@Table(name="DebitVouchers")
public class DebitVouchers
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="Sno")
	private int sno;
	
	@Column(name="Series")
	private String series;
	
	@Column(name="Account_No")
	private String accountNo;
	
	@Column(name="Name")
	private String name;
	
	@Column(name="VR_No")
	private String vrNo;
	
	@Column(name="DebitDate")
	private String date;
	
	@Column(name="Amount")
	private String amount;
	
	@Column(name="Treasury")
	private String treasury;
	
	@Column(name="Location")
	private String location;
	
	@Column(name="UploadDate")
	private String uploadDate;

	public int getSno() {
		return sno;
	}
	public void setSno(int sno) {
		this.sno = sno;
	}

	public String getSeries() {
		return series;
	}
	public void setSeries(String series) {
		this.series = series;
	}

	public String getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getVrNo() {
		return vrNo;
	}
	public void setVrNo(String vrNo) {
		this.vrNo = vrNo;
	}

	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}

	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	
	public String getTreasury() {
		return treasury;
	}
	public void setTreasury(String treasury) {
		this.treasury = treasury;
	}

	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}

	public String getUploadDate() {
		return uploadDate;
	}
	public void setUploadDate(String uploadDate) {
		this.uploadDate = uploadDate;
	}
}