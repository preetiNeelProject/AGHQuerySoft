package com.swap.controllers;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestParam;

import com.captcha.botdetect.web.servlet.Captcha;
import com.swap.beans.Keys;
import com.swap.beans.ModelInitializer;
import com.swap.beans.UserBean;
import com.swap.services.CommonService;
import com.swap.services.UserService;

@WebServlet("/loginFormAction")
public class LoginFormAction extends HttpServlet {
	  
	  /**
	 * 
	 */
	private HttpSession session;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private CommonService commonService;
	
	  @Override
	  protected void doPost(HttpServletRequest request, HttpServletResponse response)
	      throws ServletException, IOException {
	    session = request.getSession(true);
	    Map<String, String> messages = new HashMap<String, String>();
	    request.setAttribute("messages", messages);
	    
	    boolean isCaptchaSolved = (session.getAttribute("isCaptchaSolved") != null);
	 
	    // CAPTCHA user input validation
	    if (!isCaptchaSolved) {
	      Captcha captcha = Captcha.load(request, "loginCaptcha");
	      // validate the Captcha to check we're not dealing with a bot
	      boolean isHuman = captcha.validate(request.getParameter("captchaCode"));
	      if (!isHuman) {
	        // Captcha validation failed, show error message
	        messages.put("captchaIncorrect", "Incorrect code");
	      } else {
	        isCaptchaSolved = true;
	        session.setAttribute("isCaptchaSolved", true);
	      }
	    }
	    
	    // Captcha validation passed, only now do we perform the protected action 
	    // (try to authenticate the user)
	    if (isCaptchaSolved) {
	      // sumbitted login data
	      String username = request.getParameter("userId");
	      String password = request.getParameter("password");

	      // check login format
	    //  boolean isValidLoginFormat = validateLogin(username, password);
	   
			/*
			 * if (!isValidLoginFormat) { // invalid login format, show error message
			 * messages.put("loginError", "Invalid authentication info"); }
			 */
	      
	      // authenticate the user
	     // if (isValidLoginFormat) {
	        boolean isAuthenticated = authenticate(username, password);
	       if (!isAuthenticated) {
	          // failing authentication 3 times shows the Captcha again
	          int count = 0;
	          if (session.getAttribute("failedAuthCount") != null) {
	            count = (Integer) session.getAttribute("failedAuthCount");
	          }
	          count++;
	          if (count > 2) {
	            session.removeAttribute("isCaptchaSolved");
	            count = 0;
	          }
	          session.setAttribute("failedAuthCount", count);
	          
	          messages.put("loginError", "Authentication failed");
	        } else {
	          request.setAttribute("isLoggedIn", true);
	          session.removeAttribute("isCaptchaSolved");
	          authUser(request, username, password);
	          //new UserController().authUser(request, username, password);
	          
	        }
	      }
	    
	 System.out.println(request.getServletContext().getRealPath("/")+"views/index.jsp");
	    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/views/index.jsp");
	    dispatcher.forward(request, response);
	  }

	  private boolean validateLogin(String username, String password) {
	    String pattern = "^[a-zA-Z0-9_]+$"; // alphanumeric chars and underscores only
	    if ((username != null) && (password != null)) {
	      return (username.matches(pattern) && password.matches(pattern));
	    } else {
	      return false;
	    }
	  }

	  private boolean authenticate(String username, String password) {
	    return (username.length() > 2) && (password.length() > 2);
	  }
	  
	  public String authUser(HttpServletRequest request,String userId,String password)
		{
			ModelMap model=null;
			String flage=userService.authUser(userId,password);
			if(flage.equals("Success"))
			{
				commonService.insertLogs(userId,"Logged In...");
				UserBean uBean=userService.getUserBean(userId);
				HttpSession session=request.getSession();
				session.setAttribute("uBean",uBean);
				return "redirect:/home";
			}
			else
			{
				if(flage.equals("Disable"))
					model.addAttribute("msg","Your Id has been disabled.Contact your Administrator.");
				else
					model.addAttribute("msg","Wrong Credentials!");
				return "index";
			}
		}
	}
