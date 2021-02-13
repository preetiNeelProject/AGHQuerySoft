package com.swap.daos;

import com.swap.modals.Users;
import com.swap.modals.userOcr;



public interface UserDao
{
	public String authUser(String userId,String password);
	public boolean isUserIdExists(String userId);
	public boolean isOcrIdExists(String userId);
	
	
	public void createOrUpdateUser(Users user);
	public void createOrUpdateOcr(userOcr ocr);
	
	public Users getUserDetails(String userId);
	public void removeUser(String userId);
}
