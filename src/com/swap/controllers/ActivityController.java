package com.swap.controllers;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.swap.beans.ModelInitializer;
import com.swap.beans.UserBean;
import com.swap.services.CommonService;
import com.swap.services.UserService;
import com.swap.utils.FileUtils;
import com.swap.modals.Logs;
import com.swap.modals.Users;

/*
 *	@Author
 *	Swapril Tyagi 
*/

@Controller
public class ActivityController
{
	@Autowired
	private CommonService commonService;
	@Autowired
	private UserService userService;
	@Autowired
	private ModelInitializer modelInitializer;
	@Autowired
	private FileUtils fileUtils;
	
	@RequestMapping(value="/home",method=RequestMethod.GET)
	public String home(ModelMap model,HttpServletRequest request)
	{
		model=modelInitializer.initializeModel(request,model);
		if(model==null)
			return "error";
		return "home";
	}
	
	@RequestMapping(value="/click",method=RequestMethod.GET)
	public String click(ModelMap model,HttpServletRequest request)
	{
		return "click";
	}
	
	@RequestMapping(value="/currentUsers",method=RequestMethod.GET)
	public String users(ModelMap model,HttpServletRequest request)
	{
		model=modelInitializer.initializeModel(request,model);
		if(model==null)
			return "error";
		UserBean uBean=(UserBean)request.getSession(false).getAttribute("uBean");
		if(uBean.getUserType().equals("Administrator"))
		{
			ArrayList<String> users=commonService.getAllUsers();
			if(!uBean.getUserId().equals("Swapril"))
				users.remove("Swapril");
			model.addAttribute("users",users);
			return "users/currentUsers";
		}
		else
			model.addAttribute("userForm",userService.getUserDetails(uBean.getUserId()));
		return "users/userProfile";
	}
	
	@RequestMapping(value="/addUser",method=RequestMethod.GET)
	public String addUser(ModelMap model,HttpServletRequest request)
	{
		model=modelInitializer.initializeModel(request,model);
		if(model==null)
			return "error";
		model.addAttribute("userForm",new Users());
		return "users/addUser";
	}
	
	@RequestMapping(value="/dsi",method=RequestMethod.GET)
	public String dsi(ModelMap model,HttpServletRequest request,@RequestParam("obj")String obj)
	{
		model=modelInitializer.initializeModel(request,model);
		if(model==null)
			return "error";
		return "departments/"+obj+"Dsi";
	}
	
	@RequestMapping(value="/upload",method=RequestMethod.GET)
	public String upload(ModelMap model,HttpServletRequest request,@RequestParam("department")String department)
	{
		model=modelInitializer.initializeModel(request,model);
		if(model==null)
			return "error";
		model=modelInitializer.getDepartmentObject(model,department);
		return "departments/"+department+"/upload";
	}
	
	@RequestMapping(value="/retrieval",method=RequestMethod.GET)
	public String retrieval(ModelMap model,HttpServletRequest request,@RequestParam("department")String department)
	{
		model=modelInitializer.initializeModel(request,model);
		if(model==null)
			return "error";
		model=modelInitializer.getDepartmentObject(model,department);
		return "departments/"+department+"/retrieval";
	}
	
	@RequestMapping(value="/uploadBulk",method=RequestMethod.GET)
	public String uploadBulk(ModelMap model,HttpServletRequest request)
	{
		model=modelInitializer.initializeModel(request,model);
		if(model==null)
			return "error";
		modelInitializer.checkDirs();
		return "departments/bulkUpload";
	}
	
	@RequestMapping(value="/csvTemplate",method=RequestMethod.GET)
	public String csvTemplate(HttpServletRequest request,@RequestParam("department")String department,HttpServletResponse response)throws IOException
	{
		if(modelInitializer.getUserId(request)==null)
			return "error";
		fileUtils.csvTemplate(department);
		fileUtils.downloadFile("C:/Resources/",department+"_Template.csv",response,false);
		return null;
	}
	
	@RequestMapping(value="/logs",method=RequestMethod.GET)
	public String logs(ModelMap model,HttpServletRequest request)
	{
		model=modelInitializer.initializeModel(request,model);
		if(model==null)
			return "error";
		ArrayList<String> users=commonService.getAllUsers();
		if(!modelInitializer.getUserModule(request).equals("Swapril"))
			users.remove("Swapril");
		model.addAttribute("users",users);
		return "tracking/logs";
	}
	
	@RequestMapping(value="/logs",method=RequestMethod.POST)
	public String logs(ModelMap model,HttpServletRequest request,@RequestParam("logType")String logType)
	{
		model=modelInitializer.initializeModel(request,model);
		if(model==null)
			return "error";
		model=modelInitializer.getUserRights(request,model);
		ArrayList<String> users=commonService.getAllUsers();
		if(!modelInitializer.getUserId(request).equals("Swapril"))
			users.remove("Swapril");
		model.addAttribute("users",users);
		String value=null;
		if(logType.equals("user"))
			value=request.getParameter("userId");
		else
			value=request.getParameter("fileNo");
		ArrayList<Logs> logs=commonService.retrieveLogs(logType,value);
		if(logs.isEmpty())
			model.addAttribute("msg","No Logs found!");
		else
			model.addAttribute("logs",logs);
		return "tracking/logs";
	}
	
	@RequestMapping(value="/getHelp",method=RequestMethod.GET)
	public String getHelp(ModelMap model,HttpServletRequest request,HttpServletResponse response,@RequestParam("obj")String obj,@RequestParam("param")String param,@RequestParam("value")String value)throws IOException
	{
		model=modelInitializer.initializeModel(request,model);
		if(model==null)
			return "error";
		return null;
	}
}