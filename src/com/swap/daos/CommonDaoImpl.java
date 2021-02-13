package com.swap.daos;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.swap.modals.Logs;

/*
 *	@Author
 *	Swapril Tyagi 
*/

@Repository("commonDao")
public class CommonDaoImpl implements CommonDao
{
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public ArrayList<String> getAllUsers()
	{
		ArrayList<String> users=new ArrayList<String>();
		List list=sessionFactory.getCurrentSession().createSQLQuery("Select userId from Users").list();
		for(Iterator it=list.iterator();it.hasNext();)
			users.add((String)it.next());
		return users;
	}

	@Override
	public void insertLogs(Logs log)
	{
		sessionFactory.getCurrentSession().saveOrUpdate(log);		
	}

	@Override
	public ArrayList<Logs> retrieveLogs(String param, String value)
	{
		ArrayList<Logs> logs=new ArrayList<Logs>();
		String q=null;
		if(param.equals("user"))
			q="From Logs log where log.userId='"+value+"'";
		else
			q="From Logs log where log.activity like '%"+value+"%'";
		q=q+" order by sno desc";
		List list=sessionFactory.getCurrentSession().createQuery(q).setMaxResults(100).list();
		for(Iterator it=list.iterator();it.hasNext();)
			logs.add((Logs)it.next());
		return logs;
	}

	@Override
	public String getHelpBox(String obj, String param, String value)
	{
		String helpBox="",q="Select distinct "+param+" from "+obj+" where "+param+" like '"+value+"%' or "+param+" like '%"+value+"' or "+param+" like '"+value+"%'";
		List list=sessionFactory.getCurrentSession().createSQLQuery(q).setMaxResults(10).list();
		for(Iterator it=list.iterator();it.hasNext();)
			helpBox=helpBox+(String)it.next()+"<@>";
		return helpBox;
	}
}