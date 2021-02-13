package com.swap.services;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.swap.beans.Keys;
import com.swap.daos.PenGPDao;
import com.swap.modals.GPFs;
import com.swap.modals.Pension;
import com.swap.utils.FileUtils;
import com.swap.utils.Utils;

/*
 *	@Author
 *	Swapril Tyagi 
*/

@Transactional
@Service("pgService")
public class PenGPServiceImpl implements PenGPService
{
	@Autowired
	private PenGPDao pgDao;
	@Autowired
	private Utils utils;
	@Autowired
	private FileUtils fileUtils;
	@Autowired
	private Keys keys;

	@Override
	public boolean insertOrUpdatePRecord(Pension p, boolean updateFlage, MultipartFile file)
	{
		if(!updateFlage)
		{  System.out.println("IOrU1");
			if(pgDao.isRecordExists(p.getFileId(),"Pension","fileId"))
				return false;
			System.out.println("IOrU2");
			p.setLocation(utils.generateFileLocation("Pension"));
			System.out.println("IOrU3");
			p.setUploadDate(Utils.getDate());
			System.out.println("IOrU4");
		}
		if(file!=null)
		{
			try
			{
				if(!updateFlage)
				{   
					Files.write(Paths.get(keys.getRepository()+p.getFileId()+".pdf"),file.getBytes());
					
					fileUtils.addAttributes(keys.getRepository(),p);
					
				}
				else
				{  
					Files.write(Paths.get(keys.getRepository()+p.getFileId()+"-1.pdf"),file.getBytes());
					
					new File(p.getLocation()+p.getFileId()+".pdf").renameTo(new File(keys.getRepository()+p.getFileId()+".pdf"));
					
					fileUtils.mergeFiles(keys.getRepository()+p.getFileId()+".pdf",keys.getRepository()+p.getFileId()+"-1.pdf",p.getLocation()+p.getFileId()+".pdf");
				}
			}
			catch(Exception e)
			{e.printStackTrace();}
		}
		pgDao.insertOrUpdatePRecord(p);
		return true;
	}

	@Override
	public boolean insertOrUpdateGRecord(GPFs gpf, boolean updateFlage, MultipartFile file)
	{
		if(!updateFlage)
		{
			if(pgDao.isRecordExists(gpf.getAccountNo(),"GPFs","account_No"))
				return false;
			gpf.setLocation(utils.generateFileLocation("GPFs"));
			gpf.setUploadDate(Utils.getDate());
		}
		if(file!=null)
		{
			try
			{
				if(!updateFlage)
				{
					Files.write(Paths.get(keys.getRepository()+gpf.getAccountNo()+".pdf"),file.getBytes());
					fileUtils.addAttributes(keys.getRepository(),gpf);
					
				}
				else
				{
					Files.write(Paths.get(keys.getRepository()+gpf.getAccountNo()+"-1.pdf"),file.getBytes());
					new File(gpf.getLocation()+gpf.getAccountNo()+".pdf").renameTo(new File(keys.getRepository()+gpf.getAccountNo()+".pdf"));
					fileUtils.mergeFiles(keys.getRepository()+gpf.getAccountNo()+".pdf",keys.getRepository()+gpf.getAccountNo()+"-1.pdf",gpf.getLocation()+gpf.getAccountNo()+".pdf");
				}
			}
			catch(Exception e)
			{e.printStackTrace();}
		}
		pgDao.insertOrUpdateGRecord(gpf);
		return true;
	}

	@Override
	public ArrayList<Pension> retrievePRecords(Pension p)
	{
		return pgDao.retrievePRecords(utils.generateParams(p));
	}

	@Override
	public ArrayList<GPFs> retrieveGRecords(GPFs gpf)
	{
		return pgDao.retrieveGRecords(utils.generateParams(gpf));
	}

	@Override
	public String getLocation(String sno, String department)
	{
		return pgDao.getLocation(sno, department);
	}

	@Override
	public Pension getPRecord(String sno)
	{
		return pgDao.getPRecord(sno);
	}

	@Override
	public GPFs getGRecord(String sno)
	{
		return pgDao.getGRecord(sno);
	}

	@Override
	public ArrayList<Pension> retrievePRecords(String[] snos)
	{
		return pgDao.retrievePRecords(snos);
	}

	@Override
	public ArrayList<GPFs> retrieveGRecords(String[] snos)
	{
		return pgDao.retrieveGRecords(snos);
	}	
}