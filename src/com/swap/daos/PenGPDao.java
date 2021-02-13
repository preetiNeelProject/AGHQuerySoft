package com.swap.daos;

import java.util.ArrayList;

import com.swap.modals.GPFs;
import com.swap.modals.Pension;

/*
 *	@Author
 *	Swapril Tyagi 
*/

public interface PenGPDao
{
	public boolean isRecordExists(String fileId,String department,String col);
	public void insertOrUpdatePRecord(Pension p);
	public void insertOrUpdateGRecord(GPFs gpf);
	public ArrayList<Pension> retrievePRecords(ArrayList<String> params);
	public ArrayList<GPFs> retrieveGRecords(ArrayList<String> params);
	public String getLocation(String sno,String department);
	public Pension getPRecord(String sno);
	public GPFs getGRecord(String sno);
	public ArrayList<Pension> retrievePRecords(String[] snos);
	public ArrayList<GPFs> retrieveGRecords(String[] snos);
}
