package com.swap.beans;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/*
 *	@Author
 *	Swapril Tyagi 
*/

@Component("keys")
public class Keys {
	@Value("#{'${departments}'.split(',')}")
	private String[] departments;

	@Value("#{'${pensionHeader}'.split(',')}")
	private String[] PensionHeader;

	@Value("#{'${gpfHeader}'.split(',')}")
	private String[] gpfHeader;

	@Value("#{'${nominationHeader}'.split(',')}")
	private String[] nominationHeader;

	@Value("#{'${debitVoucherHeader}'.split(',')}")
	private String[] DebitVoucherHeader;

	@Value("${dataLocation}")
	private String dataLocation;

	@Value("${repository}")
	private String repository;

	@Value("${reports}")
	private String reports;

	@Value("${signNeelDll}")
	private String signNeelDll;

	@Value("${signAGDll}")
	private String signAGDll;

	public void setDepartments(String[] departments) {
		this.departments = departments;
	}

	public ArrayList<String> getDepartments() {
		ArrayList<String> departments = new ArrayList<String>();
		for (String department : this.departments)
			departments.add(department);
		return departments;
	}

	public String[] getPensionHeader() {
		return PensionHeader;
	}

	public void setPensionHeader(String[] pensionHeader) {
		PensionHeader = pensionHeader;
	}

	public String[] getGpfHeader() {
		return gpfHeader;
	}

	public void setGpfHeader(String[] gpfHeader) {
		this.gpfHeader = gpfHeader;
	}

	public String[] getDebitVoucherHeader() {
		return DebitVoucherHeader;
	}

	public void setDebitVoucherHeader(String[] debitVoucherHeader) {
		DebitVoucherHeader = debitVoucherHeader;
	}

	public void setNominationHeader(String[] nominationHeader) {
		this.nominationHeader = nominationHeader;
	}

	public String[] getNominationHeader() {
		return nominationHeader;
	}

	public void setDataLocation(String dataLocation) {
		this.dataLocation = dataLocation;
	}

	public String getDataLocation() {
		return dataLocation;
	}

	public void setRepository(String repository) {
		this.repository = repository;
	}

	public String getRepository() {
		return repository;
	}

	public void setReports(String reports) {
		this.reports = reports;
	}

	public String getReports() {
		return reports;
	}

	public void setSignNeelDll(String signNeelDll) {
		this.signNeelDll = signNeelDll;
	}

	public String getSignNeelDll() {
		return signNeelDll;
	}

	public void setSignAGDll(String signAGDll) {
		this.signAGDll = signAGDll;
	}

	public String getSignAGDll() {
		return signAGDll;
	}

	public String getSignDll(String obj) {
		if (obj.equals("neel"))
			return signNeelDll;
		else
			return signAGDll;
	}
}