package com.swap.controllers;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.swap.modals.GPFs;
import com.swap.services.PenGPService;

/*
 *	@Author
 *	Swapril Tyagi 
*/

@Controller
public class FreeGpfController
{
	@Autowired
	private PenGPService pgService;
	
	@RequestMapping(value="/gpfView",method=RequestMethod.GET)
	public String gpfView(ModelMap model,HttpServletRequest request)
	{
		model.addAttribute("gpfForm",new GPFs());
		return "gpfView";
	}
	
	@RequestMapping(value="/retrieveFG",method=RequestMethod.GET)
	public String retrieveG(ModelMap model,HttpServletRequest request,@ModelAttribute("gpfForm")GPFs gpf)
	{
		ArrayList<GPFs> records=pgService.retrieveGRecords(gpf);
		if(records.isEmpty())
			model.addAttribute("msg","No Record Found!");
		else
			model.addAttribute("records",records);
		return "gpfView";
	}
}