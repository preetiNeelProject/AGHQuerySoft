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
@Table(name="Logs")
public class Logs
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="Sno")
	private int sno;
	
	@Column(name="UserId")
	private String userId;
	
	@Column(name="Date")
	private String date;
	
	@Column(name="Time")
	private String time;
	
	@Column(name="DeviceName")
	private String deviceName;
	
	@Column(name="MACAddress")
	private String macAddress;
	
	@Column(name="Activity")
	private String activity;

	public int getSno() {
		return sno;
	}
	public void setSno(int sno) {
		this.sno = sno;
	}

	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}

	public String getDeviceName() {
		return deviceName;
	}
	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public String getMacAddress() {
		return macAddress;
	}
	public void setMacAddress(String macAddress) {
		this.macAddress = macAddress;
	}

	public String getActivity() {
		return activity;
	}
	public void setActivity(String activity) {
		this.activity = activity;
	}	
}