package com.swap.services;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.swap.beans.Keys;
import com.swap.daos.DebNomDao;
import com.swap.daos.PenGPDao;
import com.swap.modals.DebitVouchers;
import com.swap.modals.GPFs;
import com.swap.modals.Nominations;
import com.swap.modals.Pension;
import com.swap.utils.FileUtils;
import com.swap.utils.Utils;

/*
 *	@Author
 *	Swapril Tyagi 
*/

@Transactional
@Service("mrService")
public class MultipleRecordService
{
	@Autowired
	private PenGPDao pgDao;
	@Autowired
	private DebNomDao dnDao;
	@Autowired
	private Utils utils;
	@Autowired
	private Keys keys;
	@Autowired
	private FileUtils fileUtils;
	
	
	public String parseCsv(MultipartFile csvFile,MultipartFile[] files,String department)throws IOException
	{
		System.out.println("hi it is parseCv");
		System.out.println("hi"+department);
		String flage="Success";
		String[] csvHeader=utils.getCsvHeader(department);
		System.out.println("hicsvHeader"+csvHeader);
		BufferedReader csvReader=new BufferedReader(new InputStreamReader(new ByteArrayInputStream(csvFile.getBytes())));
		String line="";
	//	Date line1="";
		int countLine=0;
		boolean invalidLine=false;
		MultipartFile file=null;
		StringBuffer errors=new StringBuffer("Errors: \n");
		List<Integer> lines=new ArrayList<Integer>();
		System.out.println("jjjjjjjjjjjj");
		outer:while((line=csvReader.readLine())!=null)
		{
			if(csvHeader==null)
			{
				flage="Failed";
				errors.append("\tDepartment "+department+" can't be instantiated!");
				break;
			}
			String[] data=line.split(",");
			invalidLine=false;
	        if(data.length!=csvHeader.length)
	        {
	        	if(countLine==0)
	        	{
	        		flage="Failed";
	        		errors.append("\tInvalid Csv Header!\n");
	        		break outer;
	        	}
	        	else
	        	{
	        		flage="";
	        		errors.append("\tInvalid Line No.:"+countLine+".\n");
	        		lines.add(countLine);
	        		invalidLine=true;
	        	}
	        }
	        else
	        {
	        	if(countLine==0)
	        	{
	        		for(int i=0;i<csvHeader.length;i++)
	        		{
	        			if(!csvHeader[i].trim().equalsIgnoreCase(data[i].trim()))
	        			{
	        				flage="Failed";
	        				errors.append("\tInvalid Header "+data[i].trim()+". It should be as "+csvHeader[i].trim()+".\n");
	        				invalidLine=true;
	        			}
	        		}
	        		if(invalidLine)
	        			break outer;
	        	}
	        	else
	        	{
	        		if(!invalidLine)
	        		{
	        			for(int i=0;i<data.length;i++)
		        		{
		        			if(data[i].trim().length()==0)
		        			{
		        				flage="";
		        				errors.append("\tLine No.:"+countLine+" contain empty entries.\n");
		        				invalidLine=true;
		        				lines.add(countLine);
		        				break;
		        			}
		        		}
	        			if(!invalidLine)
	        			{
	        				System.out.println("data1"+data[1].trim());
	        				
	        				if(department.equals("Pension"))
	        					invalidLine=pgDao.isRecordExists(data[1].trim(),"Pension","fileId");
	        				if(department.equals("GPF Ledger Cards"))
	        					invalidLine=pgDao.isRecordExists(data[2].trim(),"GPFs","account_No");
	        				if(department.equals("Nominations"))
	        					invalidLine=dnDao.idRecordExists(data[1].trim()+"-"+data[2].trim(),"Nominations","pdfName");
	        				if(department.equals("Debit Vouchers"))
	        					invalidLine=dnDao.idRecordExists(data[2].trim(),"DebitVouchers","account_No");
							 
	        				if(invalidLine)
	        				{
	        					flage="";
	        					lines.add(countLine);
	        					errors.append("\tDuplicate Entry for Line No.:"+countLine+".\n");
	        				}
	        				else
	        				{
	        					file=null;
	        					fileChecker:for(MultipartFile f:files)
	        					{
	        						if(department.equals("Pension"))
	        						{
	        							if(f.getOriginalFilename().equals(data[1].trim()+".pdf") || f.getOriginalFilename().equals(data[1].trim()+".PDF"))
	        								{file=f;break fileChecker;}
	        						}
	        						if(department.equals("Nominations"))
	        						{
	        							if(f.getOriginalFilename().equals(data[1].trim()+"-"+data[2].trim()+".pdf") || f.getOriginalFilename().equals(data[1].trim()+"-"+data[2].trim()+".PDF"))
	        								{file=f;break fileChecker;}
	        						}
	        						else
	        						{
	        							if(f.getOriginalFilename().equals(data[2].trim()+".pdf") || f.getOriginalFilename().equals(data[2].trim()+".PDF"))
	        							{file=f;break fileChecker;}
	        						}

	        					}
	        					if(file!=null)
	        					{
	        						if(department.equals("Pension Cases"))
	        						{
	        					      pRecord(data,file);
	        					   
	        						}	
	        						if(department.equals("Pension"))
		        						pRecord(data,file);
		        					if(department.equals("GPF Ledger Cards"))
		        						gRecord(data,file);
		        					if(department.equals("Nominations"))
		        						nRecord(data,file);
		        					if(department.equals("Debit Vouchers"))
		        						dRecord(data,file);
		        					
	        					}
	        					else
	        					{
	        						flage="";
	        						lines.add(countLine);
	        						errors.append("\tPdf File not found for Line No.:"+countLine+".\n");
	        					}
	        				}
	        			}
	        		}
	        	}
	        	
	        }
			
	        countLine++;
		}
		
		if(!flage.equals("Success"))
			FileUtils.createFailedFile(errors);
		
		return flage;
		
	}
		

	
	
	
	private void pRecord(String[] data,MultipartFile file)
	{
		Pension p=new Pension();
		p.setFileId(data[1].trim());
		p.setOldFileNo(data[2]);
		p.setPensionerName(data[3]);
		p.setPpoNo(data[4]);
		p.setGpoNo(data[5]);
		p.setRetirementDate(data[6]);
		p.setDeathDate(data[7]);
		p.setRemarks(data[8]);
		p.setUploadDate(Utils.getDate());
		p.setLocation(utils.generateFileLocation("Pension"));
		try
		{Files.write(Paths.get(keys.getRepository()+p.getFileId()+".pdf"),file.getBytes());}
		catch(Exception e)
		{e.printStackTrace();}
		fileUtils.addAttributes(keys.getRepository(),p);
		pgDao.insertOrUpdatePRecord(p);
	}
	
	private void gRecord(String[] data,MultipartFile file)
	{
		GPFs gpf=new GPFs();
		gpf.setSeries(data[1]);
		gpf.setAccountNo(data[2].trim());
		gpf.setName(data[3]);
		gpf.setDob(data[4]);
		gpf.setRemarks(data[5]);
		gpf.setUploadDate(Utils.getDate());
		gpf.setLocation(utils.generateFileLocation("GPF Ledger Cards"));
		try
		{Files.write(Paths.get(keys.getRepository()+gpf.getAccountNo()+".pdf"),file.getBytes());}
		catch(Exception e)
		{}
		fileUtils.addAttributes(keys.getRepository(),gpf);
		pgDao.insertOrUpdateGRecord(gpf);
	}
	
	private void nRecord(String[] data,MultipartFile file)
	{
		Nominations nom=new Nominations();
		nom.setSeries(data[1]);
		nom.setAccountNo(data[2].trim());
		nom.setName(data[3]);
		nom.setUploadDate(Utils.getDate());
		nom.setPdfName(data[1]+"-"+data[2].trim());
		nom.setLocation(utils.generateFileLocation("Nominations"));
		try
		{Files.write(Paths.get(keys.getRepository()+nom.getSeries()+"-"+nom.getAccountNo()+".pdf"),file.getBytes());}
		catch(Exception e)
		{}
		fileUtils.addAttributes(keys.getRepository(),nom);
		dnDao.insertOrUpdateNRecord(nom);
	}
	
	private void dRecord(String[] data,MultipartFile file)
	{
		DebitVouchers dv=new DebitVouchers();
		dv.setSeries(data[1]);
		dv.setAccountNo(data[2].trim());
		dv.setName(data[3]);
		dv.setVrNo(data[4]);
		dv.setDate(data[5]);
		dv.setAmount(data[6]);
		dv.setTreasury(data[7]);
		dv.setUploadDate(Utils.getDate());
		dv.setLocation(utils.generateFileLocation("Debit Vouchers"));
		try
		{Files.write(Paths.get(keys.getRepository()+dv.getAccountNo()+".pdf"),file.getBytes());}
		catch(Exception e)
		{}
		fileUtils.addAttributes(keys.getRepository(),dv);
		dnDao.insertOrUpdateDRecord(dv);
	}
}