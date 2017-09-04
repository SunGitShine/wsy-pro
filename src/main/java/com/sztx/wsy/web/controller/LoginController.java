package com.sztx.wsy.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sztx.wsy.common.domain.Result;
import com.sztx.wsy.core.service.AccountService;
import com.sztx.wsy.dataaccess.domain.AccountDO;
import com.sztx.wsy.util.ParameterUtil;

@Controller
public class LoginController {
	
	@Autowired
	private AccountService accountService;
	
	@RequestMapping(value = "/")
	public String toIndex(){
		return "/web/index";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
    public String toLogin() {
        return "/login";
    }

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public Result login(HttpServletRequest request){
		
//		String username = ParameterUtil.getString("username");
//        String password = ParameterUtil.getString("password");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
        AccountDO account = accountService.login(username, password);
        
        HttpSession session = request.getSession();
        session.setAttribute("loginUser", account);
        return ParameterUtil.commonSuccessResult("loginUser", account);
	}
	
	@RequestMapping(value = "/logout")
	public Result logout(HttpServletRequest request){
		
		HttpSession session = request.getSession();
        session.removeAttribute("loginUser");
        return ParameterUtil.commonSuccessResult();
	}
}
