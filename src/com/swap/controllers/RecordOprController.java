package com.swap.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.swap.beans.Keys;
import com.swap.beans.ModelInitializer;
import com.swap.modals.DebitVouchers;
import com.swap.modals.GPFs;
import com.swap.modals.Logs;
import com.swap.modals.Nominations;
import com.swap.modals.Pension;
import com.swap.services.CommonService;
import com.swap.services.DebNomService;
import com.swap.services.PenGPService;
import com.swap.utils.Dsi;
import com.swap.utils.FileUtils;

/*
 *	@Author
 *	Swapril Tyagi 
*/

@Controller
public class RecordOprController
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
	private FileUtils fileUtils;
	@Autowired
	private Dsi dsi;
	@Autowired
	private Keys keys;
	
	@RequestMapping(value="/viewFile",method=RequestMethod.GET)
	public String viewFile(HttpServletRequest request,HttpServletResponse response,@RequestParam("department")String department,@RequestParam("sno")String sno,@RequestParam("fileId")String fileId)throws IOException
	{   System.out.println("viewFile1");
		String uId=modelInitializer.getUserId(request);
		if(uId==null)
			return null;
		if(department.equals("Pension") || department.equals("GPFs"))
			System.out.println("viewFile2");
			fileUtils.viewFile(request.getServletContext().getRealPath("/")+"staticResources/pdfs/",pgService.getLocation(sno, department),fileId+".pdf");
			System.out.println("viewFile3");
			
			if(department.equals("Nominations") || department.equals("DebitVouchers"))
			fileUtils.viewFile(request.getServletContext().getRealPath("/")+"staticResources/pdfs/",dnService.getLocation(sno, department),fileId+".pdf");
	
			System.out.println("viewFile4");
			commonService.insertLogs(uId,"Viewed File of "+department+" with name:"+fileId+".");
			System.out.println("viewFile5");
		response.getWriter().write(fileId);
		System.out.println("viewFile6");
		return null;
	}
	
	@RequestMapping(value="/viewFile2",method=RequestMethod.GET)
	public String viewFile2(HttpServletRequest request,HttpServletResponse response,@RequestParam("department")String department,@RequestParam("sno")String sno,@RequestParam("accountNo")String accountNo)throws IOException
	{
		String uId=modelInitializer.getUserId(request);
		if(uId==null)
			return null;
			
		if(department.equals("Nominations") || department.equals("DebitVouchers"))
			
			fileUtils.viewFile(request.getServletContext().getRealPath("/")+"staticResources/pdfs/",dnService.getLocation(sno, department),accountNo+".pdf");
		    commonService.insertLogs(uId,"Viewed File of "+department+" with name:"+accountNo+".");
		
		response.getWriter().write(accountNo);
		return null;
	}
	
	
	
	@RequestMapping(value="/downloadFile",method=RequestMethod.GET)
	public String downloadFile(HttpServletRequest request,HttpServletResponse response,@RequestParam("department")String department,@RequestParam("sno")String sno,@RequestParam("fileId")String fileId)throws IOException
	{
		String uId=modelInitializer.getUserId(request);
		if(uId==null)
			return "error";
		if(department.equals("Pension") || department.equals("GPFs"))
			fileUtils.downloadFile(pgService.getLocation(sno, department),fileId+".pdf",response,true);
		
		if(department.equals("Nominations") || department.equals("DebitVouchers"))
			fileUtils.downloadFile(dnService.getLocation(sno, department),fileId+".pdf",response,true);
		commonService.insertLogs(uId,"Downloaded File of "+department+" with name:"+fileId+".");
		
		return null;
	}
	
	@RequestMapping(value="/generateReport",method=RequestMethod.GET)
	public String home(HttpServletRequest request,HttpServletResponse response,@RequestParam("department")String department)
	{
		String uId=modelInitializer.getUserId(request);
		if(uId==null)
			return "error";
		String[] snos=request.getParameterValues("snos");
		if(department.equals("Pension"))
		{   System.out.println("report:1");
			if(snos!=null && snos.length>0)
				//System.out.println("report:1");
				fileUtils.generatePReport(response,pgService.retrievePRecords(snos));
			else
				fileUtils.generatePReport(response,pgService.retrievePRecords((Pension)request.getSession(false).getAttribute("pForm")));
			System.out.println("report:2");
		}
		
		
		if(department.equals("GPF Ledger Cards"))
		{    System.out.println("report:3");
			if(snos!=null && snos.length>0)
				
				fileUtils.generateGReport(response,pgService.retrieveGRecords(snos));
			else
				fileUtils.generateGReport(response,pgService.retrieveGRecords((GPFs)request.getSession(false).getAttribute("gForm")));
			System.out.println("report:4");
		}
		
		
		if(department.equals("Nominations"))
		{    System.out.println("report:5");
			if(snos!=null && snos.length>0)
				fileUtils.generateNReport(response,dnService.retrieveNRecords(snos));
			else
				fileUtils.generateNReport(response,dnService.retrieveNRecords((Nominations)request.getSession(false).getAttribute("nForm")));
			System.out.println("report:6");
		}
		if(department.equals("Debit Vouchers"))
		{   System.out.println("report:7");
			if(snos!=null && snos.length>0)
				fileUtils.generateDReport(response,dnService.retrieveDRecords(snos));
			else
				fileUtils.generateDReport(response,dnService.retrieveDRecords((DebitVouchers)request.getSession(false).getAttribute("dForm")));
			System.out.println("report:8");
		}
		return null;
	}
	
	@RequestMapping(value="/trackRecord",method=RequestMethod.GET)
	public String trackFile(ModelMap model,HttpServletRequest request,HttpServletResponse response,@RequestParam("fileId")String fileId,@RequestParam("department")String department)
	{
		if(modelInitializer.getUserId(request)==null)
			return "error";
		ArrayList<Logs> logs=commonService.retrieveLogs("",fileId);
		if(logs.isEmpty())
			model.addAttribute("msg","No Logs Found!");
		else
			model.addAttribute("logs",logs);
		model.addAttribute("fileId",fileId);
		return "tracking/documentAudit";
	}
	
	@RequestMapping(value="/updateFile",method=RequestMethod.GET)
	public String updateFile(ModelMap model,HttpServletRequest request,@RequestParam("department")String department,@RequestParam("sno")String sno)
	{
		model=modelInitializer.initializeModel(request,model);
		if(model==null)
			return "error";
		if(department.equals("Pension"))
			model.addAttribute("pensionForm",pgService.getPRecord(sno));
		if(department.equals("GPF Ledger Cards"))
			model.addAttribute("gpfForm",pgService.getGRecord(sno));
		if(department.equals("Nominations"))
			model.addAttribute("nomForm",dnService.getNRecord(sno));
		if(department.equals("Debit Vouchers"))
			model.addAttribute("dvForm",dnService.getDRecords(sno));
		return "departments/"+department+"/update";
	}
	
	@RequestMapping(value="/dsi",method=RequestMethod.POST)
	public String dsi(HttpServletRequest request,HttpServletResponse response,@RequestParam("folder")MultipartFile[] folder,@RequestParam("pin")String pin,@RequestParam("obj")String obj,RedirectAttributes flashAttributes)throws IOException
	{
		/*
		 * String uId=modelInitializer.getUserId(request); if(uId==null) return "error";
		 * fileUtils.flushLocations(); for(MultipartFile file:folder)
		 * Files.write(Paths.get(keys.getRepository()+"dsi/src/"+file.
		 * getOriginalFilename()),file.getBytes()); boolean
		 * flage=dsi.signPdf(pin,keys.getRepository()+"dsi/src/",keys.getRepository()+
		 * "dsi/dest/"+obj+"/",obj); if(!flage)
		 * flashAttributes.addFlashAttribute("msg","Signature verification failed!");
		 * else { fileUtils.createZip(obj);
		 * fileUtils.downloadFile(keys.getRepository(),"signedFiles.zip",response,false)
		 * ; return null; } return "redirect:/dsi?obj="+obj;
		 */
		return null;
	}
}