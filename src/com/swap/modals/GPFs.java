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
@Table(name="GPFs")
public class GPFs
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
	
	@Column(name="DOB")
	private String dob;
	
	@Column(name="Remarks")
	private String remarks;
	
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

	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	
	public String getRemarks(){
		return remarks;
	}
	public void setRemarks(String remarks){
		this.remarks=remarks;
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