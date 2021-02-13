package com.swap.daos;

import java.util.ArrayList;

import com.swap.modals.DebitVouchers;
import com.swap.modals.Nominations;

/*
 *	@Author
 *	Swapril Tyagi 
*/

public interface DebNomDao
{
	public boolean idRecordExists(String fileId,String department,String col);
	
	public void insertOrUpdateNRecord(Nominations nom);
	public void insertOrUpdateDRecord(DebitVouchers dv);
	public ArrayList<Nominations> retrieveNRecords(ArrayList<String> params);
	public ArrayList<DebitVouchers> retrieveDRecords(ArrayList<String> params);
	public String getLocation(String sno,String department);
	public Nominations getNRecord(String sno);
	public DebitVouchers getDRecords(String sno);
	public ArrayList<Nominations> retrieveNRecords(String[] snos);
	public ArrayList<DebitVouchers> retrieveDRecords(String[] snos);
	/**
	 * @param fileId
	 * @param department
	 * @param col
	 * @return
	 */
public 	boolean isRecordExists(String fileId, String department, String col);
}
