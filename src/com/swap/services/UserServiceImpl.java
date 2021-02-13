package com.swap.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.swap.beans.UserBean;
import com.swap.daos.UserDao;
import com.swap.modals.Users;
import com.swap.modals.userOcr;


@Service("userService")
@Transactional
public class UserServiceImpl implements UserService
{
	@Autowired
	private UserDao userDao;

	@Override
	public String authUser(String userId, String password)
	{
		return userDao.authUser(userId, password);
	}

	@Override
	public UserBean getUserBean(String userId)
	{
		UserBean uBean=new UserBean();
		Users user=userDao.getUserDetails(userId);
		uBean.setUserId(user.getUserId());
		uBean.setUserType(user.getUserType());
		uBean.setDepartments(user.getDepartments());
		uBean.setUpload(user.getUpload());
		uBean.setView(user.getView());
		uBean.setDownload(user.getDownload());
		uBean.setUpdate(user.getUpdate());
		uBean.setReport(user.getReport());
		uBean.setLogs(user.getLogs());
		return uBean;
	}

	@Override
	public boolean createOrUpdateUser(Users user, boolean updateFlage)
	{
		if(!updateFlage)
		{
			if(userDao.isUserIdExists(user.getUserId()))
				return false;
		}
		userDao.createOrUpdateUser(user);
		return true;
	}

	@Override
	public void removeUser(String userId)
	{
		userDao.removeUser(userId);		
	}

	@Override
	public Users getUserDetails(String userId)
	{
		return userDao.getUserDetails(userId);
	}

	@Override
	public boolean createOrUpdateOcr(userOcr Ocr, boolean updateFlage) {
		if(!updateFlage)
		{
			if(userDao.isOcrIdExists(Ocr.getUserId()))
				return false;
		}
		userDao.createOrUpdateOcr(Ocr);
		return true;
	}
}
