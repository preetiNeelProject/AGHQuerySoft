package com.swap.utils;

import java.util.Date;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.swap.beans.Keys;
import com.swap.modals.DebitVouchers;
import com.swap.modals.GPFs;
import com.swap.modals.Nominations;
import com.swap.modals.Pension;

import java.io.File;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

/*
 *	@Author
 *	Swapril Tyagi 
*/

@Component("utils")
public class Utils
{
	@Autowired
	private Keys keys;
	
	public static String getDate()
	{
		DateFormat df=new SimpleDateFormat("dd-MM-yyyy");
    	Date today=Calendar.getInstance().getTime();
    	return df.format(today);
	}
	
	public static String getTime()
	{
		DateFormat df=new SimpleDateFormat("hh:mm:ss");
    	Date today=Calendar.getInstance().getTime();
    	return df.format(today);
	}
	
	public static String getMacAddress()
	{
		String mac=null;
		try
		{
			InetAddress ip=InetAddress.getLocalHost();
			NetworkInterface network=NetworkInterface.getByInetAddress(ip);
			byte[] m=network.getHardwareAddress();
			StringBuilder sb=new StringBuilder();
			for(int i=0;i<m.length;i++)
				sb.append(String.format("%02X%s",m[i],(i<m.length-1)?"-":""));
			mac=sb.toString();
		}
		catch(Exception e)
		{mac="N.A.";}
		return mac;
	}
	
	public static String getDeviceName()
	{
		return System.getProperty("user.name");
	}
	
	public String[] getCsvHeader(String department)
	{
		if(department.equals("Pension"))
			return keys.getPensionHeader();
		if(department.equals("GPF Ledger Cards"))
			return keys.getGpfHeader();
		if(department.equals("Nominations"))
			return keys.getNominationHeader();
		if(department.equals("Debit Vouchers"))
			return keys.getDebitVoucherHeader();
		return null;
	}

	/* ============================generate folder====================== */
	public String generateFileLocation(String department)
	{
		String path=keys.getDataLocation()+department+"/"+new Random().nextInt(10)+"/";
		File dir=new File(path);
		if(!dir.exists())
			dir.mkdirs();
		return path;
	}
	
	public ArrayList<String> generateParams(Pension p)
	{
		ArrayList<String> params=new ArrayList<String>();
		if(p.getFileId()!=null && p.getFileId().trim().length()>0)
			params.add(p.getFileId()+"@fileId");
		if(p.getOldFileNo()!=null && p.getOldFileNo().trim().length()>0)
			params.add(p.getOldFileNo()+"@oldFileNo");
		if(p.getPpoNo()!=null && p.getPpoNo().trim().length()>0)
			params.add(p.getPpoNo()+"@ppoNo");
		if(p.getGpoNo()!=null && p.getGpoNo().trim().length()>0)
			params.add(p.getGpoNo()+"@gpoNo");
		if(p.getPensionerName()!=null && p.getPensionerName().trim().length()>0)
			params.add(p.getPensionerName()+"@pensionerName");
		if(p.getRetirementDate()!=null && p.getRetirementDate().trim().length()>0)
			params.add(p.getRetirementDate()+"@retirementDate");
		if(p.getDeathDate()!=null && p.getDeathDate().trim().length()>0)
			params.add(p.getDeathDate()+"@deathDate");
		if(p.getRemarks()!=null && p.getRemarks().trim().length()>0)
			params.add(p.getRemarks()+"@remarks");
		return params;
	}
	
	public ArrayList<String> generateParams(GPFs gpf)
	{
		ArrayList<String> params=new ArrayList<String>();
		if(gpf.getSeries()!=null && gpf.getSeries().trim().length()>0)
			params.add(gpf.getSeries()+"@series");
		if(gpf.getAccountNo()!=null && gpf.getAccountNo().trim().length()>0)
			params.add(gpf.getAccountNo()+"@accountNo");
		if(gpf.getName()!=null && gpf.getName().trim().length()>0)
			params.add(gpf.getName()+"@name");
		if(gpf.getDob()!=null && gpf.getDob().trim().length()>0)
			params.add(gpf.getDob()+"@dob");
		if(gpf.getRemarks()!=null && gpf.getRemarks().trim().length()>0)
			params.add(gpf.getRemarks()+"@remarks");
		return params;
	}
	
	public ArrayList<String> generateParams(Nominations nom)
	{
		ArrayList<String> params=new ArrayList<String>();
		if(nom.getSeries()!=null && nom.getSeries().trim().length()>0)
			params.add(nom.getSeries()+"@series");
		if(nom.getAccountNo()!=null && nom.getAccountNo().trim().length()>0)
			params.add(nom.getAccountNo()+"@accountNo");
		if(nom.getName()!=null && nom.getName().trim().length()>0)
			params.add(nom.getName()+"@name");
		return params;
	}
	
	public ArrayList<String> generateParams(DebitVouchers dv)
	{
		ArrayList<String> params=new ArrayList<String>();
		if(dv.getSeries()!=null && dv.getSeries().trim().length()>0)
			params.add(dv.getSeries()+"@series");
		if(dv.getAccountNo()!=null && dv.getAccountNo().trim().length()>0)
			params.add(dv.getAccountNo()+"@accountNo");
		if(dv.getName()!=null && dv.getName().trim().length()>0)
			params.add(dv.getName()+"@name");
		if(dv.getVrNo()!=null && dv.getVrNo().trim().length()>0)
			params.add(dv.getVrNo()+"@vrNo");
		
		if(dv.getDate()!=null && dv.getDate().trim().length()>0)
			params.add(dv.getDate()+"@date");
		if(dv.getAmount()!=null && dv.getAmount().trim().length()>0)
			params.add(dv.getAmount()+"@amount");
		if(dv.getTreasury()!=null && dv.getTreasury().trim().length()>0)
			params.add(dv.getTreasury()+"@treasury");
		return params;
	}
}