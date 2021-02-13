
package com.swap.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.pdfbox.exceptions.COSVisitorException;
import org.apache.pdfbox.util.PDFMergerUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import com.itextpdf.text.Image;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfGState;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.xml.xmp.XmpWriter;
import com.swap.beans.Keys;
import com.swap.modals.DebitVouchers;
import com.swap.modals.GPFs;
import com.swap.modals.Nominations;
import com.swap.modals.Pension;

/*
 *	@Author
 *	Swapril Tyagi 
*/

@Component("fileUtils")
public class FileUtils
{
	@Autowired
	private Keys keys;
	@Autowired
	private Utils utils;
	
	
	public void addAttributes(String location,Pension p)
	{
		try
		{
			System.out.println("location"+location);
			PdfReader pdfReader=new PdfReader(location+p.getFileId()+".pdf");
			PdfStamper pdfStamper=new PdfStamper(pdfReader,new FileOutputStream(p.getLocation()+p.getFileId()+".pdf"));
			HashMap<String, String> info=pdfReader.getInfo();
			info.put("Keywords"," PPO NO.: "+p.getFileId()+"\tName of Pensioner: "+p.getPensionerName()+"\nMAC Address: "+Utils.getMacAddress()+"\tDevice Name: "+Utils.getDeviceName()+"\n\n\n\n\n\n");
			pdfStamper.setMoreInfo(info);
			ByteArrayOutputStream baos=new ByteArrayOutputStream();
			XmpWriter xmpWriter=new XmpWriter(baos,info);
			xmpWriter.close();
			pdfStamper.setXmpMetadata(baos.toByteArray());
			pdfStamper.close();
			pdfReader.close();
			new File(location+p.getFileId()+".pdf").delete();
		}
		catch(Exception e)
		{e.printStackTrace();}
	}

	

	
	/*
	 * public void addAttributes(String location,Pension p) { try { PdfReader
	 * pdfReader=new PdfReader(location+p.getFileId()+".pdf");
	 * 
	 * PdfStamper pdfStamper=new PdfStamper(pdfReader,new
	 * FileOutputStream(p.getLocation()+p.getFileId()+".pdf"));
	 * 
	 * HashMap<String, String> info=pdfReader.getInfo();
	 * info.put("Keywords"," File Id: "+p.getFileId()+"\tName of Pensioner: "+p.
	 * getPensionerName()+"\nMAC Address: "+Utils.getMacAddress()+"\tDevice Name: "
	 * +Utils.getDeviceName()+"\n\n\n\n\n\n"); pdfStamper.setMoreInfo(info);
	 * ByteArrayOutputStream baos=new ByteArrayOutputStream(); XmpWriter
	 * xmpWriter=new XmpWriter(baos,info); xmpWriter.close();
	 * pdfStamper.setXmpMetadata(baos.toByteArray()); pdfStamper.close();
	 * pdfReader.close(); new File(location+p.getFileId()+".pdf").delete(); }
	 * catch(Exception e) {e.printStackTrace();} }
	 */
	
	public void addAttributes(String location,GPFs gpf)
	{
		try
		{
			
			
			System.out.println("location"+location);
			PdfReader pdfReader=new PdfReader(location+gpf.getAccountNo()+".pdf");
			PdfStamper pdfStamper=new PdfStamper(pdfReader,new FileOutputStream(gpf.getLocation()+gpf.getAccountNo()+".pdf"));
			HashMap<String, String> info=pdfReader.getInfo();
			info.put("Keywords"," ACCOUNT NO.: "+gpf.getAccountNo()+"\tName of Pensioner: "+gpf.getName()+"\nMAC Address: "+Utils.getMacAddress()+"\tDevice Name: "+Utils.getDeviceName()+"\n\n\n\n\n\n");
			pdfStamper.setMoreInfo(info);
			ByteArrayOutputStream baos=new ByteArrayOutputStream();
			XmpWriter xmpWriter=new XmpWriter(baos,info);
			xmpWriter.close();
			pdfStamper.setXmpMetadata(baos.toByteArray());
			pdfStamper.close();
			pdfReader.close();
			new File(location+gpf.getAccountNo()+".pdf").delete();
		}
		catch(Exception e)
		{e.printStackTrace();}
	}
	
	public void addAttributes(String location,Nominations nom)
	{
		try
		{
			System.out.println("location"+location);
			PdfReader pdfReader=new PdfReader(location+nom.getSeries()+"-"+nom.getAccountNo()+".pdf");
			PdfStamper pdfStamper=new PdfStamper(pdfReader,new FileOutputStream(nom.getLocation()+nom.getSeries()+"-"+nom.getAccountNo()+".pdf"));
			HashMap<String, String> info=pdfReader.getInfo();
			info.put("Keywords","account NO.: "+nom.getAccountNo()+"\tName of Pensioner: "+nom.getName()+"\nMAC Address: "+Utils.getMacAddress()+"\tDevice Name: "+Utils.getDeviceName()+"\n\n\n\n\n\n");
			pdfStamper.setMoreInfo(info);
			ByteArrayOutputStream baos=new ByteArrayOutputStream();
			XmpWriter xmpWriter=new XmpWriter(baos,info);
			xmpWriter.close();
			pdfStamper.setXmpMetadata(baos.toByteArray());
			pdfStamper.close();
			pdfReader.close();
			new File(location+nom.getSeries()+"-"+nom.getAccountNo()+".pdf").delete();
		}
		catch(Exception e)
		{e.printStackTrace();}
	}
	
	public void addAttributes(String location,DebitVouchers dv)
	{
		try
		{
			System.out.println("location"+location);
			PdfReader pdfReader=new PdfReader(location+dv.getAccountNo()+".pdf");
			PdfStamper pdfStamper=new PdfStamper(pdfReader,new FileOutputStream(dv.getLocation()+dv.getAccountNo()+".pdf"));
			HashMap<String, String> info=pdfReader.getInfo();
			info.put("Keywords"," account no.: "+dv.getAccountNo()+"\tName of Pensioner: "+dv.getName()+"\nMAC Address: "+Utils.getMacAddress()+"\tDevice Name: "+Utils.getDeviceName()+"\n\n\n\n\n\n");
			pdfStamper.setMoreInfo(info);
			ByteArrayOutputStream baos=new ByteArrayOutputStream();
			XmpWriter xmpWriter=new XmpWriter(baos,info);
			xmpWriter.close();
			pdfStamper.setXmpMetadata(baos.toByteArray());
			pdfStamper.close();
			pdfReader.close();
			new File(location+dv.getAccountNo()+".pdf").delete();

		}
		catch(Exception e)
		{e.printStackTrace();}
	}
	
	public static void createFailedFile(StringBuffer errors)
	{
		String msg=errors.toString();
		byte [] msgdata=msg.getBytes();
    	BufferedReader reader=new BufferedReader(new InputStreamReader(new ByteArrayInputStream(msgdata)));
    	String msgLine="";
    	String errorFileLocation="C:/Resources/ErrorFile.txt";
    	File file=new File(errorFileLocation+File.separator);
    	if(file.exists())
    		file.delete();
    	try
    	{
    		BufferedWriter writer=new BufferedWriter(new FileWriter(file,true));
        	while((msgLine=reader.readLine())!=null) 
        	{
    			writer.write(msgLine);
    			writer.newLine();
    		}
        	writer.flush();
    		writer.close();
    	}
    	catch(IOException ioe)
    	{ioe.printStackTrace();}
	}
	
	public static void createErrorFile(File csvFile,List<Integer> lines)
	{
		File errorFile=new File("C:/Resources/ErrorRecords.csv"+File.separator);
		String[] allLines=readLines(csvFile);
		try
		{
			FileWriter writer=new FileWriter(errorFile.getAbsolutePath());
			writer.write(allLines[0]);
			for(int line:lines)
			{
				writer.write("\n");
				writer.write(allLines[line]);
			}
			writer.flush();
			writer.close();
		}
		catch(IOException ioe)
		{ioe.printStackTrace();}
	}
	
	private static String[] readLines(File file)
	{
		String line;
		List<String> lines=new ArrayList<>();
        try
        {
        	FileReader fileReader=new FileReader(file);
            BufferedReader bufferedReader=new BufferedReader(fileReader);
            while((line=bufferedReader.readLine())!=null)
                lines.add(line);
            bufferedReader.close();
        }
        catch(IOException ioe)
        {ioe.printStackTrace();}
        return lines.toArray(new String[lines.size()]);
    }
	
	public void csvTemplate(String department)
	{
		File csvTemplate=new File("C:/Resources/"+department+"_Template.csv");
		String[] csvHeader=utils.getCsvHeader(department);
		try
		{
			String data=null;
			for(int i=0;i<csvHeader.length;i++)
			{
				if(data==null)
					data=csvHeader[i]+",";
				else
				{
					if(i==(csvHeader.length-1))
						data=data+csvHeader[i];
					else
						data=data+csvHeader[i]+",";
				}
			}
			if(csvTemplate.exists())
				org.apache.commons.io.FileUtils.forceDelete(csvTemplate);
			FileWriter fileWriter=new FileWriter(csvTemplate.getAbsolutePath());
			fileWriter.write(data);
			fileWriter.flush();
			fileWriter.close();
		}
		catch(Exception e)
		{e.printStackTrace();}
	}
	
	public void viewFile(String webLocation,String fileLocation,String fileName)
	{
		File copyFrom=new File(fileLocation+fileName);
		File copyTo=new File(webLocation+fileName);
		FileInputStream fin=null;
		BufferedInputStream bin=null;
		FileOutputStream fout=null;
		BufferedOutputStream bout=null;
		try
		{
			fin=new FileInputStream(copyFrom);
			bin=new BufferedInputStream(fin);
			fout=new FileOutputStream(copyTo);
			bout=new BufferedOutputStream(fout);
			byte[]  buff=new byte[8192];
			int numChars;
			while((numChars=bin.read(buff,0,buff.length))!=-1)
				bout.write(buff,0,numChars);
		}
		catch(Exception e)
		{e.printStackTrace();}
		finally
		{
			try
			{
				bout.flush();
				bout.close();
				fout.flush();
				fout.close();
				bin.close();
				fin.close();
			}
			catch(Exception e)
			{e.printStackTrace();}
		}
	}
	
	 public void downloadFile(String fileLocation,String fileName,HttpServletResponse response,boolean waterFlage)throws IOException
		{
		   System.out.println("fileLocation"+fileLocation+"fileName"+fileName);
			File file=null;
			BufferedInputStream bin=null;
			FileInputStream fin=null;
			ServletOutputStream sos=response.getOutputStream();
			try
			{
				
				
					file=new File(fileLocation+fileName);
				fin=new FileInputStream(file);
				bin=new BufferedInputStream(fin);
				response.setContentType("application/pdf");
				response.setHeader("Content-Disposition","Attachment; filename=\""+fileName+"\"");
				response.setHeader("Pragma","public");
				response.setHeader("Cache-Control","max-age=-1");
				response.setContentLengthLong((long)file.length());
				sos=response.getOutputStream();
				byte[]  buff=new byte[8192];
				int numChars;
				while((numChars=bin.read(buff,0,buff.length))!=-1)
					sos.write(buff,0,numChars);
			}
			catch(Exception e)
			{e.printStackTrace();}
			finally
			{
				try
				{
					if(bin!=null)
						bin.close();
					if(fin!=null)
						fin.close();
					if(sos!=null)
						sos.flush();
				}
				catch(Exception e)
				{e.printStackTrace();}
			}
		}
	 
	
	private void addWaterMark(String location,String fileId)
	{
		try
		{
			PdfReader pdfReader=new PdfReader(location+fileId);
			PdfStamper pdfStamper=new PdfStamper(pdfReader,new FileOutputStream(keys.getRepository()+fileId));
			Image image=Image.getInstance("C:/Resources/logo.jpg");
			float width=image.getScaledWidth(),height=image.getScaledHeight();
			PdfContentByte pdfContent;Rectangle pagesize;float x, y;
			for(int i=1;i<=pdfReader.getNumberOfPages();i++)
			{
				pagesize=pdfReader.getPageSizeWithRotation(i);
				x=(pagesize.getLeft()+pagesize.getRight())/2;
				y=(pagesize.getTop()+pagesize.getBottom())/2;
				pdfContent=pdfStamper.getOverContent(i);
				pdfContent.saveState();
				PdfGState state=new PdfGState();
				state.setFillOpacity(0.4f);
				pdfContent.setGState(state);
				pdfContent.addImage(image,width,0,0,height,x-(width/2),y-(height/2));
				pdfContent.restoreState();
			}
			pdfStamper.close();
			pdfReader.close();
		}
		catch(Exception e)
		{e.printStackTrace();}
	}
	
	public void mergeFiles(String source1,String source2,String destination)throws IOException,COSVisitorException
	{
		PDFMergerUtility pdfMerger=new PDFMergerUtility();
		pdfMerger.setDestinationFileName(destination);
		pdfMerger.addSource(source1);
		pdfMerger.addSource(source2);
		pdfMerger.mergeDocuments();
	}
	
	public void generatePReport(HttpServletResponse response,ArrayList<Pension> records)
	{
		HSSFWorkbook workBook=new HSSFWorkbook();
		HSSFSheet sheet=workBook.createSheet("Records Report");
		HSSFFont font=workBook.createFont();
        font.setBold(true);
		HSSFCellStyle style=workBook.createCellStyle();
		style.setLocked(true);
		style.setFont(font);
		HSSFRow rowHead=sheet.createRow(0);
		HSSFCell cell=rowHead.createCell(0);
		cell.setCellValue("S.No");
		cell.setCellStyle(style);
		cell=rowHead.createCell(1);
		cell.setCellValue("File Id");
		cell.setCellStyle(style);
		cell=rowHead.createCell(2);
		cell.setCellValue("Old File No.");
		cell.setCellStyle(style);
		cell=rowHead.createCell(3);
		cell.setCellValue("Name of Pensioner");
		cell.setCellStyle(style);
		cell=rowHead.createCell(4);
		cell.setCellValue("PPO No.");
		cell.setCellStyle(style);
		cell=rowHead.createCell(5);
		cell.setCellValue("GPO No.");
		cell.setCellStyle(style);
		cell=rowHead.createCell(6);
		cell.setCellValue("Remarks/Others");
		cell.setCellStyle(style);
		int r=0,i=1;
		for(int j=0;j<records.size();j++)
		{
			r=0;
			HSSFRow row=sheet.createRow(i);
			row.createCell(r).setCellValue(i);
			r++;
			row.createCell(r).setCellValue(records.get(j).getFileId());
			r++;
			row.createCell(r).setCellValue(records.get(j).getOldFileNo());
			r++;
			row.createCell(r).setCellValue(records.get(j).getPensionerName());
			r++;
			row.createCell(r).setCellValue(records.get(j).getPpoNo());
			r++;
			row.createCell(r).setCellValue(records.get(j).getGpoNo());
			r++;
			row.createCell(r).setCellValue(records.get(j).getRemarks());
			i++;
		}
		try
		{
			FileOutputStream out=new FileOutputStream(keys.getReports()+"Report.xls");
			workBook.write(out);
			out.close();
			workBook.close();
			downloadFile(keys.getReports(),"Report.xls",response,false);
		}
		catch(Exception e)
		{e.printStackTrace();}
	}
	
	public void generateGReport(HttpServletResponse response,ArrayList<GPFs> records)
	{
		HSSFWorkbook workBook=new HSSFWorkbook();
		HSSFSheet sheet=workBook.createSheet("Records Report");
		HSSFFont font=workBook.createFont();
        font.setBold(true);
		HSSFCellStyle style=workBook.createCellStyle();
		style.setLocked(true);
		style.setFont(font);
		HSSFRow rowHead=sheet.createRow(0);
		HSSFCell cell=rowHead.createCell(0);
		cell.setCellValue("S.No");
		cell.setCellStyle(style);
		cell=rowHead.createCell(1);
		cell.setCellValue("Series");
		cell.setCellStyle(style);
		cell=rowHead.createCell(2);
		cell.setCellValue("Account No.");
		cell.setCellStyle(style);
		cell=rowHead.createCell(3);
		cell.setCellValue("Name");
		cell.setCellStyle(style);
		cell=rowHead.createCell(4);
		cell.setCellValue("Date of Birth");
		cell.setCellStyle(style);
		cell=rowHead.createCell(5);
		cell.setCellValue("Remarks");
		cell.setCellStyle(style);
		int r=0,i=1;
		for(int j=0;j<records.size();j++)
		{
			r=0;
			HSSFRow row=sheet.createRow(i);
			row.createCell(r).setCellValue(i);
			r++;
			row.createCell(r).setCellValue(records.get(j).getSeries());
			r++;
			row.createCell(r).setCellValue(records.get(j).getAccountNo());
			r++;
			row.createCell(r).setCellValue(records.get(j).getName());
			r++;
			row.createCell(r).setCellValue(records.get(j).getDob());
			r++;
			row.createCell(r).setCellValue(records.get(j).getRemarks());
			i++;
		}
		try
		{
			FileOutputStream out=new FileOutputStream(keys.getReports()+"Report.xls");
			workBook.write(out);
			out.close();
			workBook.close();
			downloadFile(keys.getReports(),"Report.xls",response,false);
		}
		catch(Exception e)
		{e.printStackTrace();}
	}
	
	public void generateNReport(HttpServletResponse response,ArrayList<Nominations> records)
	{
		HSSFWorkbook workBook=new HSSFWorkbook();
		HSSFSheet sheet=workBook.createSheet("Records Report");
		HSSFFont font=workBook.createFont();
        font.setBold(true);
		HSSFCellStyle style=workBook.createCellStyle();
		style.setLocked(true);
		style.setFont(font);
		HSSFRow rowHead=sheet.createRow(0);
		HSSFCell cell=rowHead.createCell(0);
		cell.setCellValue("S.No");
		cell.setCellStyle(style);
		cell=rowHead.createCell(1);
		cell.setCellValue("Series");
		cell.setCellStyle(style);
		cell=rowHead.createCell(2);
		cell.setCellValue("Account No.");
		cell.setCellStyle(style);
		cell=rowHead.createCell(3);
		cell.setCellValue("Name");
		cell.setCellStyle(style);
		int r=0,i=1;
		for(int j=0;j<records.size();j++)
		{
			r=0;
			HSSFRow row=sheet.createRow(i);
			row.createCell(r).setCellValue(i);
			r++;
			row.createCell(r).setCellValue(records.get(j).getSeries());
			r++;
			row.createCell(r).setCellValue(records.get(j).getAccountNo());
			r++;
			row.createCell(r).setCellValue(records.get(j).getName());
			i++;
		}
		try
		{
			FileOutputStream out=new FileOutputStream(keys.getReports()+"Report.xls");
			workBook.write(out);
			out.close();
			workBook.close();
			downloadFile(keys.getReports(),"Report.xls",response,false);
		}
		catch(Exception e)
		{e.printStackTrace();}
	}
	
	public void generateDReport(HttpServletResponse response,ArrayList<DebitVouchers> records)
	{
		HSSFWorkbook workBook=new HSSFWorkbook();
		HSSFSheet sheet=workBook.createSheet("Records Report");
		HSSFFont font=workBook.createFont();
        font.setBold(true);
		HSSFCellStyle style=workBook.createCellStyle();
		style.setLocked(true);
		style.setFont(font);
		HSSFRow rowHead=sheet.createRow(0);
		HSSFCell cell=rowHead.createCell(0);
		cell.setCellValue("S.No");
		cell.setCellStyle(style);
		cell=rowHead.createCell(1);
		cell.setCellValue("Series");
		cell.setCellStyle(style);
		cell=rowHead.createCell(2);
		cell.setCellValue("Account No.");
		cell.setCellStyle(style);
		cell=rowHead.createCell(3);
		cell.setCellValue("Namer");
		cell.setCellStyle(style);
		cell=rowHead.createCell(4);
		cell.setCellValue("VR No.");
		cell.setCellStyle(style);
		cell=rowHead.createCell(5);
		cell.setCellValue("Date");
		cell.setCellStyle(style);
		cell=rowHead.createCell(6);
		cell.setCellValue("Amount");
		cell.setCellStyle(style);
		cell=rowHead.createCell(7);
		cell.setCellValue("Treasury");
		cell.setCellStyle(style);
		int r=0,i=1;
		for(int j=0;j<records.size();j++)
		{
			r=0;
			HSSFRow row=sheet.createRow(i);
			row.createCell(r).setCellValue(i);
			r++;
			row.createCell(r).setCellValue(records.get(j).getSeries());
			r++;
			row.createCell(r).setCellValue(records.get(j).getAccountNo());
			r++;
			row.createCell(r).setCellValue(records.get(j).getName());
			r++;
			row.createCell(r).setCellValue(records.get(j).getVrNo());
			r++;
			row.createCell(r).setCellValue(records.get(j).getDate());
			r++;
			row.createCell(r).setCellValue(records.get(j).getAmount());
			r++;
			row.createCell(r).setCellValue(records.get(j).getTreasury());
			i++;
		}
		try
		{
			FileOutputStream out=new FileOutputStream(keys.getReports()+"Report.xls");
			workBook.write(out);
			out.close();
			workBook.close();
			downloadFile(keys.getReports(),"Report.xls",response,false);
		}
		catch(Exception e)
		{e.printStackTrace();}
	}
	
	public void createZip(String obj)
	{
		File dir=new File(keys.getRepository()+"dsi/dest/"+obj+"/");
		FileInputStream fin=null;
		try
		{
			FileOutputStream fout=new FileOutputStream(keys.getRepository()+"signedFiles.zip");
			ZipOutputStream zip=new ZipOutputStream(fout);
			ZipEntry zipEntry=null;
			byte[] bytes=new byte[1024];
			int length;
			for(File file:dir.listFiles())
			{
				fin=new FileInputStream(file);
				zipEntry=new ZipEntry(file.getName());
				zip.putNextEntry(zipEntry);
				while((length=fin.read(bytes))>=0)
					zip.write(bytes,0,length);
				zip.closeEntry();
				fin.close();
			}
			zip.close();
			fout.flush();
			fout.close();
		}
		catch(Exception e)
		{e.printStackTrace();}
	}
	
	public void flushLocations()
	{
		for(File file:new File(keys.getRepository()+"dsi/src").listFiles())
			file.delete();
		for(File file:new File(keys.getRepository()+"dsi/dest/ag/").listFiles())
			file.delete();
		for(File file:new File(keys.getRepository()+"dsi/dest/neel/").listFiles())
			file.delete();
	}
}