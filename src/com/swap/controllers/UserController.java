package com.swap.controllers;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.captcha.botdetect.web.servlet.Captcha;
import com.swap.beans.Keys;
import com.swap.beans.ModelInitializer;
import com.swap.beans.UserBean;
import com.swap.modals.Users;
import com.swap.services.CommonService;
import com.swap.services.UserService;

/*
 *	@Author
 *	Swapril Tyagi 
*/

@Controller
public class UserController {

	private HttpSession session;
	@Autowired
	private UserService userService;
	@Autowired
	private CommonService commonService;
	@Autowired
	private ModelInitializer modelInitializer;
	@Autowired
	private Keys keys;

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String authUser(ModelMap model, HttpServletRequest request, HttpServletResponse response,
			@RequestParam("userId") String userId, @RequestParam("password") String password) {
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
			// authenticate the user
			boolean isAuthenticated = authenticate(userId, password);
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
				String flage = userService.authUser(userId, password);
				if (flage.equals("Success")) {
					commonService.insertLogs(userId, "Logged In...");
					UserBean uBean = userService.getUserBean(userId);
					HttpSession session = request.getSession();
					session.setAttribute("uBean", uBean);
					return "redirect:/home";
				} else {
					if (flage.equals("Disable"))
						messages.put("disableError", "Your Id has been disabled.Contact your Administrator.");
						//model.addAttribute("msg", "Your Id has been disabled.Contact your Administrator.");
					else {
						messages.put("wrongInputError", "Wrong Credentials!");
						//model.addAttribute("msg", "Wrong Credentials!");
					}
					return "index";
				}
			}
		}
		return "index";
	}

	private boolean authenticate(String username, String password) {
		return (username.length() > 2) && (password.length() > 2);
	}

	@RequestMapping(value = "/addUser", method = RequestMethod.POST)
	public String saveUser(HttpServletRequest request, @ModelAttribute("userForm") Users user,
			RedirectAttributes flashAttributes) {
		System.out.println(user.getPassword());
		String uId = modelInitializer.getUserId(request);
		if (uId == null)
			return "error";
		boolean flage = userService.createOrUpdateUser(user, false);
		if (!flage)
			flashAttributes.addFlashAttribute("msg", "User Id already exists!");
		else
			flashAttributes.addFlashAttribute("msg", "User Id inserted successfully.");
		return "redirect:/addUser";
	}

	// for save the hashcode password
	private String hashPassword(String plainTextPassword) {
		return BCrypt.hashpw(plainTextPassword, BCrypt.gensalt());
	}

	/*-------------------------------------------------------------------*/
	@RequestMapping(value = "/addOcr", method = RequestMethod.POST)
	public String saveOcr(HttpServletRequest request, @ModelAttribute("UserOcr") Users user,
			RedirectAttributes flashAttributes) {
		/*
		 * String uId=modelInitializer.getUserId(request); if(uId==null) return "error";
		 */
		boolean flage = userService.createOrUpdateUser(user, false);
		if (!flage)
			flashAttributes.addFlashAttribute("msg", "User Id already exists!");
		else
			flashAttributes.addFlashAttribute("msg", "User Id inserted successfully.");
		return "redirect:/retrieval";
	}
	/*-------------------------------------------------------------------*/

	@RequestMapping(value = "/userDetails", method = RequestMethod.POST)
	public String userDetails(HttpServletRequest request, @RequestParam("userId") String userId,
			RedirectAttributes flashAttributes) {
		String uId = modelInitializer.getUserId(request);
		if (uId == null)
			return "error";
		Users user = userService.getUserDetails(userId);
		if (user.getUserType().equals("Administrator"))
			user.setDepartments(keys.getDepartments());
		flashAttributes.addFlashAttribute("userForm", user);
		return "redirect:/currentUsers";
	}

	@RequestMapping(value = "/updateUser", method = RequestMethod.POST)
	public String updateUser(HttpServletRequest request, @ModelAttribute("userForm") Users user,
			RedirectAttributes flashAttributes) {
		String uId = modelInitializer.getUserId(request);
		if (uId == null)
			return "error";
		commonService.insertLogs(uId, "Updated User with User Id:" + user.getUserId());
		userService.createOrUpdateUser(user, true);
		flashAttributes.addFlashAttribute("msg", "User Id is updated successfully.");
		return "redirect:/currentUsers";
	}

	@RequestMapping(value = "/removeUser", method = RequestMethod.GET)
	public String removeUser(HttpServletRequest request, @RequestParam("userId") String userId,
			RedirectAttributes flashAttributes) {
		String uId = modelInitializer.getUserId(request);
		if (uId == null)
			return "error";
		commonService.insertLogs(uId, "Removed User with User Id:" + userId);
		userService.removeUser(userId);
		commonService.insertLogs(uId, "Removed User Id:" + userId + ".");
		flashAttributes.addFlashAttribute("msg", "User Id is removed successfully.");
		return "redirect:/currentUsers";
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		UserBean uBean = (UserBean) session.getAttribute("uBean");
		if (session != null && uBean != null) {
			Enumeration<String> attributes = session.getAttributeNames();
			while (attributes.hasMoreElements())
				session.removeAttribute(attributes.nextElement());
			commonService.insertLogs(uBean.getUserId(), "Logged out...");
			session.invalidate();
		}
		return "index";
	}
}