package com.swap.services;

import java.util.ArrayList;

import org.springframework.web.multipart.MultipartFile;

import com.swap.modals.GPFs;
import com.swap.modals.Pension;

/*
 *	@Author
 *	Swapril Tyagi 
*/

public interface PenGPService
{
	public boolean insertOrUpdatePRecord(Pension p,boolean updateFlage,MultipartFile file);
	public boolean insertOrUpdateGRecord(GPFs gpf,boolean updateFlage,MultipartFile file);
	public ArrayList<Pension> retrievePRecords(Pension p);
	public ArrayList<GPFs> retrieveGRecords(GPFs gpf);
	public String getLocation(String sno,String department);
	public Pension getPRecord(String sno);
	public GPFs getGRecord(String sno);
	public ArrayList<Pension> retrievePRecords(String[] snos);
	public ArrayList<GPFs> retrieveGRecords(String[] snos);
}
