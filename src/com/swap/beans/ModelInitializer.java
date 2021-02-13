package com.swap.beans;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;

import com.swap.modals.DebitVouchers;
import com.swap.modals.GPFs;
import com.swap.modals.Nominations;
import com.swap.modals.Pension;

/*
 *	@Author
 *	Swapril Tyagi 
*/

@Component("modelInitializer")
public class ModelInitializer
{
	@Autowired
	private Keys keys;
	
	public void checkDirs()
	{
		File dir=new File(keys.getDataLocation());
		if(!dir.exists())
			dir.mkdirs();
		dir=new File(keys.getRepository());
		if(!dir.exists())
			dir.mkdirs();
		dir=new File(keys.getReports());
		if(!dir.exists())
			dir.mkdirs();
	}
	
	public ModelMap initializeModel(HttpServletRequest request,ModelMap model)
	{
		UserBean uBean=getUserBean(request);
		if(uBean==null)
			return null;
		model.addAttribute("user",uBean.getUserId());
		if(uBean.getUserType().equals("Administrator"))
			model.addAttribute("admin","1");
		else
			model.addAttribute("userModule","1");
		if(getUserModule(request).equals("Administrator"))
			model.addAttribute("departments",keys.getDepartments());
		else
			model.addAttribute("departments",uBean.getDepartments());
		model=getUserRights(request,model);
		return model;
	}
	
	public UserBean getUserBean(HttpServletRequest request)
	{
		HttpSession session=request.getSession(false);
		if(session==null)
			return null;
		UserBean uBean=(UserBean)session.getAttribute("uBean");
		if(uBean==null)
			return null;
		else
			return uBean;
	}
	
	public String getUserId(HttpServletRequest request)
	{
		UserBean uBean=getUserBean(request);
		if(uBean==null)
			return null;
		return uBean.getUserId();
	}
	
	public String getUserModule(HttpServletRequest request)
	{
		UserBean uBean=getUserBean(request);
		if(uBean==null)
			return null;
		return uBean.getUserType();
	}
	
	public ModelMap getUserRights(HttpServletRequest request,ModelMap model)
	{
		UserBean uBean=(UserBean)request.getSession(false).getAttribute("uBean");
		if(uBean.getUpload())
			model.addAttribute("upload","1");
		if(uBean.getView())
			model.addAttribute("view","1");
		if(uBean.getDownload())
			model.addAttribute("download","1");
		if(uBean.getReport())
			model.addAttribute("report","1");
		if(uBean.getUpdate())
			model.addAttribute("update","1");
		if(uBean.getLogs())
			model.addAttribute("lg","1");
		return model;
	}
	
	public ModelMap getDepartmentObject(ModelMap model,String department)
	{
		if(department.equals("Pension"))
			model.addAttribute("pensionForm",new Pension());
		if(department.equals("GPF Ledger Cards"))
			model.addAttribute("gpfForm",new GPFs());
		if(department.equals("Nominations"))
			model.addAttribute("nomForm",new Nominations());
		if(department.equals("Debit Vouchers"))
			model.addAttribute("dvForm",new DebitVouchers());
		return model;
	}
}