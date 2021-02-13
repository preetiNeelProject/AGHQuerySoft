package com.swap.controllers;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.swap.beans.ModelInitializer;
import com.swap.modals.DebitVouchers;
import com.swap.modals.GPFs;
import com.swap.modals.Nominations;
import com.swap.modals.Pension;
import com.swap.services.DebNomService;
import com.swap.services.PenGPService;

/*
 *	@Author
 *	Swapril Tyagi 
*/

@Controller
public class RetrievalController
{
	@Autowired
	private ModelInitializer modelInitializer;
	@Autowired
	private PenGPService pgService;
	@Autowired
	private DebNomService dnService;
	
	@RequestMapping(value="/retrieveP",method=RequestMethod.GET)
	public String retrieveP(ModelMap model,HttpServletRequest request,@ModelAttribute("pensionForm")Pension p)
	{
		model=modelInitializer.initializeModel(request,model);
		if(model==null)
			return "error";
		ArrayList<Pension> records=pgService.retrievePRecords(p);
		if(records.isEmpty())
			model.addAttribute("msg","No Record Found!");
		else
		{
			request.getSession(false).setAttribute("pForm",p);
			model.addAttribute("records",records);
		}
		return "departments/Pension/retrieval";
	}
	
	@RequestMapping(value="/retrieveG",method=RequestMethod.GET)
	public String retrieveG(ModelMap model,HttpServletRequest request,@ModelAttribute("gpfForm")GPFs gpf)
	{
		model=modelInitializer.initializeModel(request,model);
		if(model==null)
			return "error";
		ArrayList<GPFs> records=pgService.retrieveGRecords(gpf);
		if(records.isEmpty())
			model.addAttribute("msg","No Record Found!");
		else
		{
			request.getSession(false).setAttribute("gForm",gpf);
			model.addAttribute("records",records);
		}
		return "departments/GPF Ledger Cards/retrieval";
	}
	
	@RequestMapping(value="/retrieveN",method=RequestMethod.GET)
	public String retrieveN(ModelMap model,HttpServletRequest request,@ModelAttribute("nomForm")Nominations nom)
	{
		model=modelInitializer.initializeModel(request,model);
		if(model==null)
			return "error";
		ArrayList<Nominations> records=dnService.retrieveNRecords(nom);
		if(records.isEmpty())
			model.addAttribute("msg","No Record Found!");
		else
		{
			request.getSession(false).setAttribute("nForm",nom);
			model.addAttribute("records",records);
		}
		return "departments/Nominations/retrieval";
	}
	
	@RequestMapping(value="/retrieveD",method=RequestMethod.GET)
	public String retrieveD(ModelMap model,HttpServletRequest request,@ModelAttribute("dvForm")DebitVouchers dv)
	{
		model=modelInitializer.initializeModel(request,model);
		if(model==null)
			return "error";
		ArrayList<DebitVouchers> records=dnService.retrieveDRecords(dv);
		if(records.isEmpty())
			model.addAttribute("msg","No Record Found!");
		else
		{
			request.getSession(false).setAttribute("dForm",dv);
			model.addAttribute("records",records);
		}
		return "departments/Debit Vouchers/retrieval";
	}
}