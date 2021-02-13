package com.swap.services;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.swap.daos.CommonDao;
import com.swap.modals.Logs;
import com.swap.utils.Utils;

/*
 *	@Author
 *	Swapril Tyagi 
*/

@Service("commonService")
@Transactional
public class CommonServiceImpl implements CommonService
{
	@Autowired
	private CommonDao commonDao;

	@Override
	public ArrayList<String> getAllUsers()
	{
		return commonDao.getAllUsers();
	}

	@Override
	public void insertLogs(String userId, String activity)
	{
		Logs log=new Logs();
		log.setUserId(userId);
		log.setDate(Utils.getDate());
		log.setTime(Utils.getTime());
		log.setActivity(activity);
		log.setMacAddress(Utils.getMacAddress());
		log.setDeviceName(Utils.getDeviceName());
		commonDao.insertLogs(log);
	}

	@Override
	public ArrayList<Logs> retrieveLogs(String param, String value)
	{
		return commonDao.retrieveLogs(param, value);
	}

	@Override
	public String getHelpBox(String obj, String param, String value)
	{
		return commonDao.getHelpBox(obj, param, value);
	}
}