package com.swap.services;

import java.util.ArrayList;
import com.swap.modals.Logs;

/*
 *	@Author
 *	Swapril Tyagi 
*/

public interface CommonService
{
	public ArrayList<String> getAllUsers();
	public void insertLogs(String userId,String activity);
	public ArrayList<Logs> retrieveLogs(String param,String value);
	public String getHelpBox(String obj,String param,String value);
}
