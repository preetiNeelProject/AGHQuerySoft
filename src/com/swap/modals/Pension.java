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
@Table(name="Pension")
public class Pension
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="Sno")
	private int sno;
	
	@Column(name="FileId")
	private String fileId;
	
	@Column(name="Old_File_No")
	private String oldFileNo;
	
	@Column(name="PPO_No")
	private String ppoNo;
	
	@Column(name="GPO_No")
	private String gpoNo;
	
	@Column(name="Pensioner_Name")
	private String pensionerName;
	
	@Column(name="Retirement_Date")
	private String retirementDate;
	
	@Column(name="Death_Date")
	private String deathDate;
	
	@Column(name="Remarks")
	private String remarks;
	
	@Column(name="Location")
	private String location;
	
	@Column(name="UploadDate")
	private String uploadDate;

	public int getSno()
	{
		return sno;
	}
	public void setSno(int sno)
	{
		this.sno=sno;
	}

	public String getFileId()
	{
		return fileId;
	}
	public void setFileId(String fileId)
	{
		this.fileId=fileId;
	}
	
	public String getOldFileNo()
	{
		return oldFileNo;
	}
	public void setOldFileNo(String oldFileNo)
	{
		this.oldFileNo=oldFileNo;
	}

	public String getPpoNo()
	{
		return ppoNo;
	}
	public void setPpoNo(String ppoNo)
	{
		this.ppoNo=ppoNo;
	}

	public String getGpoNo()
	{
		return gpoNo;
	}
	public void setGpoNo(String gpoNo)
	{
		this.gpoNo=gpoNo;
	}

	public String getPensionerName()
	{
		return pensionerName;
	}
	public void setPensionerName(String pensionerName)
	{
		this.pensionerName=pensionerName;
	}

	public String getRetirementDate()
	{
		return retirementDate;
	}
	public void setRetirementDate(String retirementDate)
	{
		this.retirementDate=retirementDate;
	}

	public String getDeathDate()
	{
		return deathDate;
	}
	public void setDeathDate(String deathDate)
	{
		this.deathDate=deathDate;
	}

	public String getRemarks()
	{
		return remarks;
	}
	public void setRemarks(String remarks)
	{
		this.remarks=remarks;
	}

	public String getLocation()
	{
		return location;
	}
	public void setLocation(String location)
	{
		this.location=location;
	}

	public String getUploadDate()
	{
		return uploadDate;
	}
	public void setUploadDate(String uploadDate)
	{
		this.uploadDate=uploadDate;
	}
}