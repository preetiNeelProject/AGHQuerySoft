package com.swap.daos;

import java.util.Iterator;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Repository;
import com.swap.modals.Users;
import com.swap.modals.userOcr;


@Repository("userDao")
public class UserDaoImpl implements UserDao
{
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public String authUser(String userId, String password)
	{
		String flage="Null";
		for(Iterator it=sessionFactory.getCurrentSession().createSQLQuery("Select disable from Users where userId='"+userId+"' and password='"+password+"'").list().iterator();it.hasNext();)
		{
			if(!(Boolean)it.next())
				flage="Success";
			else
				flage="Disable";
		}
		return flage;
	}
	
	//for check password in database
			private void checkPass(String plainPassword, String hashedPassword) {
				if (BCrypt.checkpw(plainPassword, hashedPassword))
					System.out.println("The password matches.");
				else
					System.out.println("Invalid Username/Password");
			}

	@Override
	public boolean isUserIdExists(String userId)
	{
		if(sessionFactory.getCurrentSession().createSQLQuery("Select userId from Users where userId='"+userId+"'").list().iterator().hasNext())
			return true;
		return false;
	}
	/*----------------------------------------------------------------*/
	@Override
	public boolean isOcrIdExists(String userId) {
		if(sessionFactory.getCurrentSession().createSQLQuery("Select userId from userOcr where userId='"+userId+"'").list().iterator().hasNext())
			return true;
		return false;
	}
	/*----------------------------------------------------------------*/
	@Override
	public void createOrUpdateUser(Users user)
	{
		sessionFactory.getCurrentSession().saveOrUpdate(user);		
	}

	/*----------------------------------------------------------------*/
	@Override
	public void createOrUpdateOcr(userOcr Ocr) {
		sessionFactory.getCurrentSession().saveOrUpdate(Ocr);		
		
	}
	/*----------------------------------------------------------------*/
	
	@Override
	public Users getUserDetails(String userId)
	{
		for(Iterator it=sessionFactory.getCurrentSession().createQuery("From Users user where user.userId='"+userId+"'").list().iterator();it.hasNext();)
			return (Users)it.next();
		return null;
	}
	

	@Override
	public void removeUser(String userId)
	{
		sessionFactory.getCurrentSession().createSQLQuery("Delete from Users where userId='"+userId+"'").executeUpdate();		
	}

	

	
}