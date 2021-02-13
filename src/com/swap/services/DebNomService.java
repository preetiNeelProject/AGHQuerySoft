package com.swap.services;

import java.util.ArrayList;

import org.springframework.web.multipart.MultipartFile;

import com.swap.modals.DebitVouchers;
import com.swap.modals.Nominations;

/*
 *	@Author
 *	Swapril Tyagi 
*/

public interface DebNomService
{
	public boolean insertOrUpdateNRecord(Nominations nom,boolean updateFlage,MultipartFile file);
	public boolean insertOrUpdateDRecord(DebitVouchers dv,boolean updateFlage,MultipartFile file);
	public ArrayList<Nominations> retrieveNRecords(Nominations nom);
	public ArrayList<DebitVouchers> retrieveDRecords(DebitVouchers dv);
	public String getLocation(String sno,String department);
	public Nominations getNRecord(String sno);
	public DebitVouchers getDRecords(String sno);
	public ArrayList<Nominations> retrieveNRecords(String[] snos);
	public ArrayList<DebitVouchers> retrieveDRecords(String[] snos);
}
