package com.swap.daos;

import java.util.ArrayList;

import com.swap.modals.Logs;

/*
 *	@Author
 *	Swapril Tyagi 
*/

public interface CommonDao
{
	public ArrayList<String> getAllUsers();
	public void insertLogs(Logs log);
	public ArrayList<Logs> retrieveLogs(String param,String value);
	public String getHelpBox(String obj,String param,String value);
}
