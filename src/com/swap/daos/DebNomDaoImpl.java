package com.swap.daos;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.swap.modals.DebitVouchers;
import com.swap.modals.Nominations;

/*
 *	@Author
 *	Swapril Tyagi 
*/

@Repository("dnDao")
public class DebNomDaoImpl implements DebNomDao
{
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public boolean idRecordExists(String fileId, String department, String col)
	{    System.out.println("idRecords:1");
		if(!sessionFactory.getCurrentSession().createSQLQuery("Select sno from "+department+" where "+col+"='"+fileId+"'").list().isEmpty())
			return true;
		return false;
	}
	@Override
	public boolean isRecordExists(String fileId, String department,String col)
	{
		if(!sessionFactory.getCurrentSession().createSQLQuery("Select sno from "+department+" where "+col+"='"+fileId+"'").list().isEmpty())
			return true;
		return false;
	}
	
	
	@Override
	public void insertOrUpdateNRecord(Nominations nom)
	{    System.out.println("insertOrUpdateNRecord:1");
		sessionFactory.getCurrentSession().saveOrUpdate(nom);		
	}

	@Override
	public void insertOrUpdateDRecord(DebitVouchers dv)
	{    System.out.println("insertOrUpdateDRecord(DebitVouchers dv):1");
		sessionFactory.getCurrentSession().saveOrUpdate(dv);
		 System.out.println("insertOrUpdateDRecord(DebitVouchers dv):2");
	}

	@Override
	public ArrayList<Nominations> retrieveNRecords(ArrayList<String> params)
	{
		ArrayList<Nominations> records=new ArrayList<Nominations>();
		String q=null;
		if(params.isEmpty())
			q="From Nominations nom";
		else
		{
			for(String param:params)
			{
				if(q==null)
					q="From Nominations nom where (nom."+param.substring(param.indexOf("@")+1)+" like '"+param.substring(0,param.indexOf("@"))+"%' or nom."+param.substring(param.indexOf("@")+1)+" like '%"+param.substring(0,param.indexOf("@"))+"%' or nom."+param.substring(param.indexOf("@")+1)+" like '%"+param.substring(0,param.indexOf("@"))+"')";
				else
					q=q+"and (nom."+param.substring(param.indexOf("@")+1)+" like '"+param.substring(0,param.indexOf("@"))+"%' or nom."+param.substring(param.indexOf("@")+1)+" like '%"+param.substring(0,param.indexOf("@"))+"%' or nom."+param.substring(param.indexOf("@")+1)+" like '%"+param.substring(0,param.indexOf("@"))+"')";
			}
		}
		List list=sessionFactory.getCurrentSession().createQuery(q).setMaxResults(200).list();
		for(Iterator it=list.iterator();it.hasNext();)
			records.add((Nominations)it.next());
		return records;
	}

	@Override
	public ArrayList<DebitVouchers> retrieveDRecords(ArrayList<String> params)
	{
		
		System.out.println("it is debit vouchers");
		ArrayList<DebitVouchers> records=new ArrayList<DebitVouchers>();
		String q=null;
		if(params.isEmpty())
			q="From DebitVouchers dv";
		else
		{
			for(String param:params)
			{
				
				if(q==null)
				{
					
					System.out.println("it is if part");
						q="From DebitVouchers dv where (dv."+param.substring(param.indexOf("@")+1)+" like '"+param.substring(0,param.indexOf("@"))+"%' or dv."+param.substring(param.indexOf("@")+1)+" like '%"+param.substring(0,param.indexOf("@"))+"%' or dv."+param.substring(param.indexOf("@")+1)+" like '%"+param.substring(0,param.indexOf("@"))+"')";
				}
				else
				{
					System.out.println("it is else part");
					
					q=q+"and (dv."+param.substring(param.indexOf("@")+1)+" like '"+param.substring(0,param.indexOf("@"))+"%' or dv."+param.substring(param.indexOf("@")+1)+" like '%"+param.substring(0,param.indexOf("@"))+"%' or dv."+param.substring(param.indexOf("@")+1)+" like '%"+param.substring(0,param.indexOf("@"))+"')";
				}
			}
		}
		List list=sessionFactory.getCurrentSession().createQuery(q).setMaxResults(200).list();
		for(Iterator it=list.iterator();it.hasNext();)
			records.add((DebitVouchers)it.next());
		return records;
	}

	@Override
	public String getLocation(String sno, String department)
	{
		for(Iterator it=sessionFactory.getCurrentSession().createSQLQuery("Select location from "+department+" where sno="+sno+"").list().iterator();it.hasNext();)
			return (String)it.next();
		return null;
	}

	@Override
	public Nominations getNRecord(String sno)
	{
		for(Iterator it=sessionFactory.getCurrentSession().createQuery("From Nominations nom where nom.sno="+sno+"").list().iterator();it.hasNext();)
			return (Nominations)it.next();
		return null;
	}

	@Override
	public DebitVouchers getDRecords(String sno)
	{
		for(Iterator it=sessionFactory.getCurrentSession().createQuery("From DebitVouchers dv where dv.sno="+sno+"").list().iterator();it.hasNext();)
			return (DebitVouchers)it.next();
		return null;
	}

	@Override
	public ArrayList<Nominations> retrieveNRecords(String[] snos)
	{
		Session session=sessionFactory.getCurrentSession();
		ArrayList<Nominations> records=new ArrayList<Nominations>();
		for(String sno:snos)
		{
			for(Iterator it=session.createQuery("From Nominations nom where nom.sno="+sno+"").list().iterator();it.hasNext();)
				records.add((Nominations)it.next());
		}
		return records;
	}

	@Override
	public ArrayList<DebitVouchers> retrieveDRecords(String[] snos)
	{
		Session session=sessionFactory.getCurrentSession();
		ArrayList<DebitVouchers> records=new ArrayList<DebitVouchers>();
		for(String sno:snos)
		{
			for(Iterator it=session.createQuery("From DebitVouchers dv where dv.sno="+sno+"").list().iterator();it.hasNext();)
				records.add((DebitVouchers)it.next());
		}
		return records;
	}	
}