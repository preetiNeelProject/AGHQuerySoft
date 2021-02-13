package com.swap.daos;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.swap.modals.GPFs;
import com.swap.modals.Pension;

/*
 *	@Author
 *	Swapril Tyagi 
*/

@Repository("pgDao")
public class PenGPDaoImpl implements PenGPDao
{
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public boolean isRecordExists(String fileId, String department,String col)
	{
		if(!sessionFactory.getCurrentSession().createSQLQuery("Select sno from "+department+" where "+col+"='"+fileId+"'").list().isEmpty())
			return true;
		return false;
	}

	@Override
	public void insertOrUpdatePRecord(Pension p)
	{
		sessionFactory.getCurrentSession().saveOrUpdate(p);		
	}

	@Override
	public void insertOrUpdateGRecord(GPFs gpf)
	{
		sessionFactory.getCurrentSession().saveOrUpdate(gpf);		
	}

	@Override
	public ArrayList<Pension> retrievePRecords(ArrayList<String> params)
	{
		ArrayList<Pension> records=new ArrayList<Pension>();
		String q=null;
		if(params.isEmpty())
			q="From Pension p";
		else
		{
			for(String param:params)
			{
				if(q==null)
				{
					
						q="From Pension p where (p."+param.substring(param.indexOf("@")+1)+" like '"+param.substring(0,param.indexOf("@"))+"%' or p."+param.substring(param.indexOf("@")+1)+" like '%"+param.substring(0,param.indexOf("@"))+"%' or p."+param.substring(param.indexOf("@")+1)+" like '%"+param.substring(0,param.indexOf("@"))+"')";
				}
				else
				{
					
						q=q+"and (p."+param.substring(param.indexOf("@")+1)+" like '"+param.substring(0,param.indexOf("@"))+"%' or p."+param.substring(param.indexOf("@")+1)+" like '%"+param.substring(0,param.indexOf("@"))+"%' or p."+param.substring(param.indexOf("@")+1)+" like '%"+param.substring(0,param.indexOf("@"))+"')";
				}
			}
		}
		List list=sessionFactory.getCurrentSession().createQuery(q).setMaxResults(200).list();
		for(Iterator it=list.iterator();it.hasNext();)
			records.add((Pension)it.next());
		return records;
	}

	@Override
	public ArrayList<GPFs> retrieveGRecords(ArrayList<String> params)
	{
		ArrayList<GPFs> records=new ArrayList<GPFs>();
		String q=null;
		if(params.isEmpty())
			q="From GPFs gpf";
		else
		{
			for(String param:params)
			{
				if(q==null)
				{
						q="From GPFs gpf where (gpf."+param.substring(param.indexOf("@")+1)+" like '"+param.substring(0,param.indexOf("@"))+"%' or gpf."+param.substring(param.indexOf("@")+1)+" like '%"+param.substring(0,param.indexOf("@"))+"%' or gpf."+param.substring(param.indexOf("@")+1)+" like '%"+param.substring(0,param.indexOf("@"))+"')";
				}
				else
				{
											q=q+"and (gpf."+param.substring(param.indexOf("@")+1)+" like '"+param.substring(0,param.indexOf("@"))+"%' or gpf."+param.substring(param.indexOf("@")+1)+" like '%"+param.substring(0,param.indexOf("@"))+"%' or gpf."+param.substring(param.indexOf("@")+1)+" like '%"+param.substring(0,param.indexOf("@"))+"')";
				}
			}
		}
		List list=sessionFactory.getCurrentSession().createQuery(q).setMaxResults(200).list();
		for(Iterator it=list.iterator();it.hasNext();)
			records.add((GPFs)it.next());
		return records;
	}

	@Override
	public String getLocation(String sno, String department)
	{   System.out.println("location");
		for(Iterator it=sessionFactory.getCurrentSession().createSQLQuery("Select location from "+department+" where sno="+sno+"").list().iterator();it.hasNext();)
		return (String)it.next();
		
		return null;
	}

	@Override
	public Pension getPRecord(String sno)
	{
		for(Iterator it=sessionFactory.getCurrentSession().createQuery("From Pension p where p.sno="+sno+"").list().iterator();it.hasNext();)
			return (Pension)it.next();
		return null;
	}

	@Override
	public GPFs getGRecord(String sno)
	{
		for(Iterator it=sessionFactory.getCurrentSession().createQuery("From GPFs gpf where gpf.sno="+sno+"").list().iterator();it.hasNext();)
			return (GPFs)it.next();
		return null;
	}

	@Override
	public ArrayList<Pension> retrievePRecords(String[] snos)
	{
		Session session=sessionFactory.getCurrentSession();
		ArrayList<Pension> records=new ArrayList<Pension>();
		for(String sno:snos)
		{
			for(Iterator it=session.createQuery("From Pension p where p.sno="+sno+"").list().iterator();it.hasNext();)
				records.add((Pension)it.next());
		}
		return records;
	}

	@Override
	public ArrayList<GPFs> retrieveGRecords(String[] snos)
	{
		Session session=sessionFactory.getCurrentSession();
		ArrayList<GPFs> records=new ArrayList<GPFs>();
		for(String sno:snos)
		{
			for(Iterator it=session.createQuery("From GPFs gpf where gpf.sno="+sno+"").list().iterator();it.hasNext();)
				records.add((GPFs)it.next());
		}
		return records;
	}	
}