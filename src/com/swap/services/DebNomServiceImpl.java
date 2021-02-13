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
import com.swap.daos.DebNomDao;
import com.swap.modals.DebitVouchers;
import com.swap.modals.GPFs;
import com.swap.modals.Nominations;
import com.swap.utils.FileUtils;
import com.swap.utils.Utils;

/*
 *	@Author
 *	Swapril Tyagi 
*/

@Transactional
@Service("dNService")
public class DebNomServiceImpl implements DebNomService
{
	@Autowired
	private DebNomDao dnDao;
	@Autowired
	private Utils utils;
	@Autowired
	private FileUtils fileUtils;
	@Autowired
	private Keys keys;


	@Override
	public boolean insertOrUpdateNRecord(Nominations nom, boolean updateFlage, MultipartFile file)
	{
		if(!updateFlage)
		{
			if(dnDao.isRecordExists(nom.getSeries()+"-"+nom.getAccountNo(),"Nominations","pdfName"))
				return false;
			nom.setLocation(utils.generateFileLocation("Nominations"));
			nom.setUploadDate(Utils.getDate());
			nom.setPdfName(nom.getSeries()+"-"+nom.getAccountNo());
		}
		if(file!=null)
		{
			try
			{
				if(!updateFlage)
				{
					Files.write(Paths.get(keys.getRepository()+nom.getSeries()+"-"+nom.getAccountNo()+".pdf"),file.getBytes());
					fileUtils.addAttributes(keys.getRepository(),nom);
				}
				else
				{
					Files.write(Paths.get(keys.getRepository()+nom.getSeries()+"-"+nom.getAccountNo()+"-1.pdf"),file.getBytes());
					new File(nom.getLocation()+nom.getSeries()+"-"+nom.getAccountNo()+".pdf").renameTo(new File(keys.getRepository()+nom.getSeries()+"-"+nom.getAccountNo()+".pdf"));
					fileUtils.mergeFiles(keys.getRepository()+nom.getSeries()+"-"+nom.getAccountNo()+".pdf",keys.getRepository()+nom.getSeries()+"-"+nom.getAccountNo()+"-1.pdf",nom.getLocation()+nom.getSeries()+"-"+nom.getAccountNo()+".pdf");
				}
			}
			catch(Exception e)
			{e.printStackTrace();}
		}
		dnDao.insertOrUpdateNRecord(nom);
		return true;
	}
	
	@Override
	public boolean insertOrUpdateDRecord(DebitVouchers dv, boolean updateFlage, MultipartFile file)
	{
		if(!updateFlage)//true
		{
			if(dnDao.idRecordExists(dv.getAccountNo(),"DebitVouchers","account_No"))
				return false;
			dv.setLocation(utils.generateFileLocation("Debit Vouchers"));
			dv.setUploadDate(Utils.getDate());
		}
		if(file!=null)
		{
			try
			{
				if(!updateFlage)
				{
					Files.write(Paths.get(keys.getRepository()+dv.getAccountNo()+".pdf"),file.getBytes());
				
					fileUtils.addAttributes(keys.getRepository(),dv);
				}
				else
				{
            Files.write(Paths.get(keys.getRepository()+dv.getAccountNo()+"-1.pdf"),file.getBytes());
           new File(dv.getLocation()+dv.getAccountNo()+".pdf").renameTo(new File(keys.getRepository()+dv.getAccountNo()+".pdf"));
             fileUtils.mergeFiles(keys.getRepository()+dv.getAccountNo()+".pdf",keys.getRepository()+dv.getAccountNo()+"-1.pdf",dv.getLocation()+dv.getAccountNo()+".pdf");
				}
			}catch(Exception e)
			{e.printStackTrace();}
		}
		dnDao.insertOrUpdateDRecord(dv);
		return true;
	}

	@Override
	public ArrayList<Nominations> retrieveNRecords(Nominations nom)
	{
		return dnDao.retrieveNRecords(utils.generateParams(nom));
	}

	@Override
	public ArrayList<DebitVouchers> retrieveDRecords(DebitVouchers dv)
	{
		return dnDao.retrieveDRecords(utils.generateParams(dv));
	}

	@Override
	public String getLocation(String sno, String department)
	{
		return dnDao.getLocation(sno, department);
	}

	@Override
	public Nominations getNRecord(String sno)
	{
		return dnDao.getNRecord(sno);
	}

	@Override
	public DebitVouchers getDRecords(String sno)
	{
		return dnDao.getDRecords(sno);
	}

	@Override
	public ArrayList<Nominations> retrieveNRecords(String[] snos)
	{
		return dnDao.retrieveNRecords(snos);
	}

	@Override
	public ArrayList<DebitVouchers> retrieveDRecords(String[] snos)
	{
		return dnDao.retrieveDRecords(snos);
	}	
}