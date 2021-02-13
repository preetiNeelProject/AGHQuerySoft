package com.swap.services;

import com.swap.beans.UserBean;
import com.swap.modals.Users;
import com.swap.modals.userOcr;

public interface UserService
{
	public String authUser(String userId,String password);
	public UserBean getUserBean(String userId);
	
	public boolean createOrUpdateUser(Users user,boolean updateFlage);
	public boolean createOrUpdateOcr(userOcr Ocr,boolean updateFlage);
	
	public void removeUser(String userId);
	public Users getUserDetails(String userId);
}
