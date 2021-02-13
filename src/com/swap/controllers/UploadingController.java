package com.swap.controllers;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.swap.beans.ModelInitializer;
import com.swap.modals.DebitVouchers;
import com.swap.modals.GPFs;
import com.swap.modals.Nominations;
import com.swap.modals.Pension;
import com.swap.services.CommonService;
import com.swap.services.DebNomService;
import com.swap.services.MultipleRecordService;
import com.swap.services.PenGPService;
import com.swap.utils.FileUtils;

/*
 *	@Author
 *	Swapril Tyagi 
*/

@Controller
public class UploadingController
{
	@Autowired
	private ModelInitializer modelInitializer;
	@Autowired
	private CommonService commonService;
	@Autowired
	private PenGPService pgService;
	@Autowired
	private DebNomService dnService;
	@Autowired
	private MultipleRecordService mrService;
	@Autowired
	private FileUtils fileUtils;
	
	@RequestMapping(value="/uploadBulk",method=RequestMethod.POST)
	public String uploadBulk(HttpServletRequest request,@RequestParam("folder")MultipartFile[] files,@RequestParam("csvFile")MultipartFile csvFile,@RequestParam("department")String department,RedirectAttributes flashAttributes)throws IOException
	{
		if(modelInitializer.getUserId(request)==null)
			return "error";
		if(csvFile.getOriginalFilename().indexOf(".csv")==-1)
			flashAttributes.addFlashAttribute("msg","Select a valid csv file.");
		else
		{
			String flage=mrService.parseCsv(csvFile,files,department);
			if(flage.equals("Success"))
				flashAttributes.addFlashAttribute("msg","All Records are uploaded successfully.");
			else
			{
				flashAttributes.addFlashAttribute("err","1");
				if(flage.equals("Failed"))
					flashAttributes.addFlashAttribute("msg","Records couldn't be uploaded");
				else
					flashAttributes.addFlashAttribute("msg","All records can't be uploaded successfully.");
			}
		}
		return "redirect:/uploadBulk";
	}
	
	@RequestMapping(value="/errFile",method=RequestMethod.GET)
	public String downloadErrorFile(HttpServletRequest request,HttpServletResponse response)throws IOException
	{
		
		if(modelInitializer.getUserId(request)==null)
			return "error";
	try
		{
		fileUtils.downloadFile("C:/Resources/","ErrorFile.txt", response,false);}
		catch(Exception e)
		{e.printStackTrace();}
		return null;
	}
	@RequestMapping(value="/uploadP",method=RequestMethod.POST)
	public String uploadP(HttpServletRequest request,@ModelAttribute("pensionForm")Pension p,@RequestParam("file")MultipartFile file,RedirectAttributes flashAttributes)
	{
		String uId=modelInitializer.getUserId(request);
		if(uId==null)
			return "error";
		if(file==null || !file.getOriginalFilename().equals(p.getFileId()+".pdf"))
			flashAttributes.addFlashAttribute("msg","Invalid Pdf File!");
		else
		{
			if(!pgService.insertOrUpdatePRecord(p,false,file))
				flashAttributes.addFlashAttribute("msg","Duplicate Entry!");
			else
			{
				commonService.insertLogs(uId,"Uploaded Record of Department: Pension with name: "+p.getFileId()+".");
				flashAttributes.addFlashAttribute("msg","Record is uploaded successfully.");
			}
		}
		return "redirect:/upload?department=Pension";
	}
	
	@RequestMapping(value="/uploadG",method=RequestMethod.POST)
	public String uploadG(HttpServletRequest request,@ModelAttribute("gpfForm")GPFs gpf,@RequestParam("file")MultipartFile file,RedirectAttributes flashAttributes)
	{
		String uId=modelInitializer.getUserId(request);
		if(uId==null)
			return "error";
		if(file==null || !file.getOriginalFilename().equals(gpf.getAccountNo()+".pdf"))
			flashAttributes.addFlashAttribute("msg","Invalid Pdf File!");
		else
		{
			if(!pgService.insertOrUpdateGRecord(gpf,false,file))
				flashAttributes.addFlashAttribute("msg","Duplicate Entry!");
			else
			{
				commonService.insertLogs(uId,"Uploaded Record of Department: GPF Ledger Card with name: "+gpf.getAccountNo()+".");
				flashAttributes.addFlashAttribute("msg","Record is uploaded successfully.");
			}
		}
		return "redirect:/upload?department=GPF Ledger Cards";
	}
	
	@RequestMapping(value="/uploadN",method=RequestMethod.POST)
	public String uploadN(HttpServletRequest request,@ModelAttribute("nomForm")Nominations nom,@RequestParam("file")MultipartFile file,RedirectAttributes flashAttributes)
	{
		String uId=modelInitializer.getUserId(request);
		if(uId==null)
			return "error";
		if(file==null || !file.getOriginalFilename().equals(nom.getSeries()+"-"+nom.getAccountNo()+".pdf"))
			flashAttributes.addFlashAttribute("msg","Invalid Pdf File!");
		else
		{
			if(!dnService.insertOrUpdateNRecord(nom,false,file))
				flashAttributes.addFlashAttribute("msg","Duplicate Entry!");
			else
			{
				commonService.insertLogs(uId,"Uploaded Record of Department: Nominations with filename: "+nom.getAccountNo()+".");
				flashAttributes.addFlashAttribute("msg","Record is uploaded successfully.");
			}
		}
		return "redirect:/upload?department=Nominations";
	}
	
	
	/*
	@RequestMapping(value="/uploadN",method=RequestMethod.POST)
	public String uploadN(HttpServletRequest request,@ModelAttribute("nomForm")Nominations nom,@RequestParam("file")MultipartFile file,RedirectAttributes flashAttributes)
	{
		String uId=modelInitializer.getUserId(request);
		if(uId==null)
			return "error";
		if(file==null || !file.getOriginalFilename().equals(nom.getAccountNo()+".pdf"))
			flashAttributes.addFlashAttribute("msg","Invalid Pdf File!");
		else
		{
			if(!dnService.insertOrUpdateNRecord(nom,false,file))
				flashAttributes.addFlashAttribute("msg","Duplicate Entry!");
			else
			{
				commonService.insertLogs(uId,"Uploaded Record of Department: Nominations with name: "+nom.getAccountNo()+".");
				flashAttributes.addFlashAttribute("msg","Record is uploaded successfully.");
			}
		}
		return "redirect:/upload?department=Nominations";
	}
	
	*/
	
	
	
	
	
	
	
	
	@RequestMapping(value="/uploadD",method=RequestMethod.POST)
	public String uploadD(HttpServletRequest request,@ModelAttribute("dvForm")DebitVouchers dv,@RequestParam("file")MultipartFile file,RedirectAttributes flashAttributes)
	{
		String uId=modelInitializer.getUserId(request);
		if(uId==null)
			return "error";
		if(file==null || !file.getOriginalFilename().equals(dv.getAccountNo()+".pdf"))
			flashAttributes.addFlashAttribute("msg","Invalid Pdf File!");
		else
		{
			if(!dnService.insertOrUpdateDRecord(dv,false,file))
				flashAttributes.addFlashAttribute("msg","Duplicate Entry!");
			else
			{
				commonService.insertLogs(uId,"Uploaded Record of Department: Debit Vouchers with name: "+dv.getAccountNo()+".");
				flashAttributes.addFlashAttribute("msg","Record is uploaded successfully.");
			}
		}
		return "redirect:/upload?department=Debit Vouchers";
	}
	
	@RequestMapping(value="/updateP",method=RequestMethod.POST)
	public String updateP(HttpServletRequest request,@ModelAttribute("pensionForm")Pension p,@RequestParam("file")MultipartFile file,RedirectAttributes flashAttributes)
	{
		String uId=modelInitializer.getUserId(request);
		if(uId==null)
			return "error";
		if(file!=null && file.getOriginalFilename().trim().length()>0)
		{
			if(file.getOriginalFilename().equals(p.getFileId()+".pdf"))
				flashAttributes.addFlashAttribute("msg","Invalid Pdf File!");
		}
		pgService.insertOrUpdatePRecord(p,true,file);
		commonService.insertLogs(uId,"Updated Record of Department: Pension with name: "+p.getFileId()+".");
		flashAttributes.addFlashAttribute("msg","Record is uploaded successfully.");
		return "redirect:/updateFile?department=Pension&sno="+p.getSno();
	}
	
	@RequestMapping(value="/updateG",method=RequestMethod.POST)
	public String updateG(HttpServletRequest request,@ModelAttribute("gpfForm")GPFs gpf,@RequestParam("file")MultipartFile file,RedirectAttributes flashAttributes)
	{
		String uId=modelInitializer.getUserId(request);
		if(uId==null)
			return "error";
		if(file!=null && file.getOriginalFilename().trim().length()>0)
		{
			if(file.getOriginalFilename().equals(gpf.getAccountNo()+".pdf"))
				flashAttributes.addFlashAttribute("msg","Invalid Pdf File!");
		}
		pgService.insertOrUpdateGRecord(gpf,true,file);
		commonService.insertLogs(uId,"Updated Record of Department: GPF Ledger Card with name: "+gpf.getAccountNo()+".");
		flashAttributes.addFlashAttribute("msg","Record is uploaded successfully.");
		return "redirect:/updateFile?department=GPF Ledger Cards&sno="+gpf.getSno();
	}
	
	@RequestMapping(value="/updateN",method=RequestMethod.POST)
	public String updateN(HttpServletRequest request,@ModelAttribute("nomForm")Nominations nom,@RequestParam("file")MultipartFile file,RedirectAttributes flashAttributes)
	{
		String uId=modelInitializer.getUserId(request);
		if(uId==null)
			return "error";
		if(file!=null && file.getOriginalFilename().trim().length()>0)
		{
			if(file.getOriginalFilename().equals(nom.getAccountNo()+".pdf"))
				flashAttributes.addFlashAttribute("msg","Invalid Pdf File!");
		}
		dnService.insertOrUpdateNRecord(nom,true,file);
		commonService.insertLogs(uId,"Updated Record of Department: Nominations with name: "+nom.getAccountNo()+".");
		flashAttributes.addFlashAttribute("msg","Record is uploaded successfully.");
		return "redirect:/updateFile?department=Nominations&sno="+nom.getSno();
	}
	
	@RequestMapping(value="/updateD",method=RequestMethod.POST)
	public String updateD(HttpServletRequest request,@ModelAttribute("dvForm")DebitVouchers dv,@RequestParam("file")MultipartFile file,RedirectAttributes flashAttributes)
	{
		String uId=modelInitializer.getUserId(request);
		if(uId==null)
			return "error";
		if(file!=null && file.getOriginalFilename().trim().length()>0)
		{
			if(file.getOriginalFilename().equals(dv.getAccountNo()+".pdf"))
				flashAttributes.addFlashAttribute("msg","Invalid Pdf File!");
		}
		dnService.insertOrUpdateDRecord(dv,true,file);
		commonService.insertLogs(uId,"Updated Record of Department: Debit Vouchers with name: "+dv.getAccountNo()+".");
		flashAttributes.addFlashAttribute("msg","Record is uploaded successfully.");
		return "redirect:/updateFile?department=Debit Vouchers&sno="+dv.getSno();
	}
}