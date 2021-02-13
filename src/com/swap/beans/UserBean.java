package com.swap.beans;

import java.util.ArrayList;

/*
 *	@Author
 *	Swapril Tyagi 
*/

public class UserBean
{
	private String userId,userType;
	private ArrayList<String> departments;
	private boolean upload,view,download,report,update,logs;
	
	public String getUserId() 
	{
		return userId;
	}
	public void setUserId(String userId) 
	{
		this.userId=userId;
	}
	
	public String getUserType() 
	{
		return userType;
	}
	public void setUserType(String userType) 
	{
		this.userType=userType;
	}
	
	public void setDepartments(ArrayList<String> departments)
	{
		this.departments=departments;
	}
	public ArrayList<String> getDepartments()
	{
		return departments;
	}
	
	public void setUpload(boolean upload)
	{
		this.upload=upload;
	}
	public boolean getUpload()
	{
		return upload;
	}
	
	public boolean getView()
	{
		return view;
	}
	public void setView(boolean view)
	{
		this.view=view;
	}
	
	public boolean getDownload()
	{
		return download;
	}
	public void setDownload(boolean download)
	{
		this.download=download;
	}
	
	public boolean getUpdate()
	{
		return update;
	}
	public void setUpdate(boolean update)
	{
		this.update=update;
	}
	
	public void setReport(boolean report)
	{
		this.report=report;
	}
	public boolean getReport()
	{
		return report;
	}
	
	public boolean getLogs()
	{
		return logs;
	}
	public void setLogs(boolean logs)
	{
		this.logs=logs;
	}
}